"""Metroid Dread area files, read automatically off the reference images from fetch_ref.py.

    python tools/metroid-maps/games/dread/extract_dread.py [area ...]

Dread's map has no room outlines: rooms are joined by doors, drawn as an icon between two short
light-grey bars. So this:
  1. takes every pixel that isn't background as terrain,
  2. finds the door bars and cuts the terrain there,
  3. splits what's left into connected pieces (rooms),
  4. lays a coarse grid over it (CELL px) and gives each cell to the room filling most of it,
  5. records a door wherever a bar sits between two cells of different rooms, and
  6. puts each item in the cell under its marker (or the nearest room cell), and marks stations.
Writes areas/<area>.py in the usual format. Check with render.py --ref (config REF_* lines up the
reference grid) and fix by hand.
"""
import json
import sys
from collections import Counter, deque
from pathlib import Path
from PIL import Image

HERE = Path(__file__).resolve().parent
BG = (10, 22, 34)
CELL = 48          # px per map cell at zoom 13
FILL = 0.10        # share of a cell a room must cover to claim it
MIN_ROOM = 150     # px; smaller pieces (door icons, specks) are dropped

ITEM_IDS = {'missile': 'm', 'etank': 'e', 'epart': 'q', 'pb': 'p'}
CODES = {'artaria': 'ar', 'cataris': 'ca', 'dairon': 'da', 'burenia': 'bu', 'ferenia': 'fe', 'ghavoran': 'gh',
         'elun': 'el', 'hanubia': 'ha', 'itorash': 'it'}


def terrain(p):
    """Open space: the lighter fill. The darker shade inside rooms is solid rock."""
    r, g, b = p
    return r + g + b > 200


def frame(p):
    r, g, b = p
    return min(r, g, b) > 140 and max(r, g, b) - min(r, g, b) < 50


def flood(mask, w, h):
    """Labels 4-connected pieces of mask; returns (label array, sizes)."""
    lab = [0] * (w * h)
    sizes = [0]
    n = 0
    for i in range(w * h):
        if mask[i] and not lab[i]:
            n += 1
            lab[i] = n
            q = deque([i])
            size = 0
            while q:
                j = q.popleft()
                size += 1
                x, y = j % w, j // w
                for k in (j + 1 if x + 1 < w else -1, j - 1 if x > 0 else -1, j + w if y + 1 < h else -1, j - w if y > 0 else -1):
                    if k >= 0 and mask[k] and not lab[k]:
                        lab[k] = n
                        q.append(k)
            sizes.append(size)
    return lab, sizes


def door_bars(px, w, h):
    """Door icons: light-grey frame pieces grouped (by a 2 px dilation) into a box about the
    size of a door icon. Returns (x, y0, y1, x0, x1) per door: centre x, and the box."""
    fm = bytearray(w * h)
    for y in range(h):
        for x in range(w):
            if frame(px[x, y]):
                for dy in (-2, -1, 0, 1, 2):
                    yy = y + dy
                    if 0 <= yy < h:
                        base = yy * w
                        for dx in (-2, -1, 0, 1, 2):
                            xx = x + dx
                            if 0 <= xx < w:
                                fm[base + xx] = 1
    lab, sizes = flood(fm, w, h)
    boxes = {}
    for i, l in enumerate(lab):
        if l:
            x, y = i % w, i // w
            b = boxes.setdefault(l, [x, y, x, y])
            b[0] = min(b[0], x); b[1] = min(b[1], y); b[2] = max(b[2], x); b[3] = max(b[3], y)
    bars = []
    for x0, y0, x1, y1 in boxes.values():
        bw, bh = x1 - x0 + 1, y1 - y0 + 1
        if bh >= 14 and bh <= 28 and bw <= 30:
            bars.append(((x0 + x1) // 2, y0 + 2, y1 - 2, x0 + 2, x1 - 2))
    return bars


def extract(area):
    im = Image.open(HERE / 'ref' / f'{area}.png').convert('RGB')
    w, h = im.size
    px = im.load()
    items = json.loads((HERE / 'ref' / f'{area}.json').read_text(encoding='utf-8'))
    # Only this area's own region (the reference crop also catches bits of its neighbours).
    from PIL import ImageDraw
    region = Image.new('L', (w, h), 0)
    for ring in json.loads((HERE / 'ref' / f'{area}.region.json').read_text(encoding='utf-8')):
        ImageDraw.Draw(region).polygon([tuple(p) for p in ring], fill=1)
    inside = region.load()
    mask = bytearray(w * h)
    for y in range(h):
        for x in range(w):
            if inside[x, y] and terrain(px[x, y]):
                mask[y * w + x] = 1
    bars = door_bars(px, w, h)
    for x, y0, y1, bx0, bx1 in bars:
        for y in range(max(0, y0 - 3), min(h, y1 + 4)):
            for xx in range(max(0, bx0 - 1), min(w, bx1 + 2)):
                mask[y * w + xx] = 0
    lab, sizes = flood(mask, w, h)
    # Grid cells: the room covering most of each cell.
    cols, rows = w // CELL + 1, h // CELL + 1
    count = {}
    for i, l in enumerate(lab):
        if l and sizes[l] >= MIN_ROOM:
            c = ((i % w) // CELL, (i // w) // CELL)
            count.setdefault(c, Counter())[l] += 1
    owner = {}
    for c, cnt in count.items():
        l, n = cnt.most_common(1)[0]
        if n >= CELL * CELL * FILL:
            owner[c] = l
    # A room that won no cell keeps its busiest one if that's free of others' claims.
    won = set(owner.values())
    for c, cnt in count.items():
        for l, n in cnt.items():
            if l not in won and c not in owner and n >= 20:
                owner[c] = l
                won.add(l)
    # Rooms: a room's cells must touch; split any that don't.
    rooms = {}
    for c, l in owner.items():
        rooms.setdefault(l, []).append(c)
    final = []
    for l, cells in rooms.items():
        left = set(cells)
        while left:
            start = left.pop()
            part = [start]; q = deque([start])
            while q:
                cx, cy = q.popleft()
                for n in ((cx + 1, cy), (cx - 1, cy), (cx, cy + 1), (cx, cy - 1)):
                    if n in left:
                        left.discard(n); part.append(n); q.append(n)
            final.append(part)
    own = {c: i for i, cells in enumerate(final) for c in cells}
    # Drop specks (map labels, stray pixels): tiny rooms touching no other room.
    def touches(i, cells):
        return any(own.get(n, i) != i for cx, cy in cells for n in ((cx + 1, cy), (cx - 1, cy), (cx, cy + 1), (cx, cy - 1)))
    final = [cells for i, cells in enumerate(final) if len(cells) > 3 or touches(i, cells)]
    own = {c: i for i, cells in enumerate(final) for c in cells}
    # Doors between horizontally neighbouring cells of different rooms.
    doors = set()
    for x, y0, y1, _, _ in bars:
        cy = ((y0 + y1) // 2) // CELL
        cx = x // CELL
        for a in ((cx - 1, cy), (cx, cy)):
            b = (a[0] + 1, a[1])
            if a in own and b in own and own[a] != own[b]:
                doors.add((a[1], a[0], 'n'))
    # Items and stations.
    def nearest(c):
        if c in own:
            return c
        return min(own, key=lambda o: (o[0] - c[0]) ** 2 + (o[1] - c[1]) ** 2)
    notes = {}
    out_items = {}
    sheet = []
    counters = Counter()
    marks = {'save': 'save', 'map': 'map', 'network': 'mark:N', 'recharge': 'mark:R', 'energy': 'mark:E',
             'ammo': 'mark:A', 'teleportal': 'mark:T'}
    for it in sorted(items, key=lambda i: (i['y'], i['x'])):
        c = nearest((it['x'] // CELL, it['y'] // CELL))
        k = it['kind']
        iid = None
        if k in ITEM_IDS:
            letter = 'x' if k == 'missile' and 'Missile+' in it['title'] else ITEM_IDS[k]
            counters[letter] += 1
            iid = f"{CODES[area]}_{letter}{counters[letter]}"
        elif k == 'upgrade':
            iid = 'u_' + ''.join(ch for ch in it['title'].lower() if ch.isalnum())
        elif k in marks:
            notes.setdefault(own[c], marks[k])
        elif k == 'transport':
            notes.setdefault(own[c], 'mark:↕')
        elif k == 'boss':
            notes.setdefault(own[c], 'boss:' + it['title'].replace(':', ''))
        if iid:
            out_items[iid] = c
        sheet.append({'id': iid, 'kind': k, 'title': it['title'], 'desc': it['desc'], 'cell': c, 'room': own[c], 'mg': it['id']})
    (HERE / 'ref' / f'{area}.sheet.json').write_text(json.dumps(sheet, indent=1), encoding='utf-8')
    return final, sorted(doors), out_items, notes


def write(area, rooms, doors, items, notes):
    lines = ["# Generated by extract_dread.py from the MapGenie reference; fix rooms by hand as needed.",
             "N,S,H=0,1,2", "rooms=["]
    for i, cells in enumerate(rooms):
        lines.append(f"    ({sorted(cells)},N,{notes.get(i, '')!r}),")
    lines.append("]")
    lines.append("doors=" + repr(doors))
    lines.append("items=" + repr(items))
    (HERE / 'areas' / f'{area}.py').write_text("\n".join(lines) + "\n", encoding='utf-8')


def main():
    areas = sys.argv[1:] or ['artaria', 'cataris', 'dairon', 'burenia', 'ferenia', 'ghavoran', 'elun', 'hanubia', 'itorash']
    for area in areas:
        rooms, doors, items, notes = extract(area)
        write(area, rooms, doors, items, notes)
        print(area, len(rooms), 'rooms', len(doors), 'doors', len(items), 'items')


if __name__ == '__main__':
    main()
