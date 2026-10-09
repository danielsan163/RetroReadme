"""Draws one area from games/<game>/areas/<name>.py to out/<game>-<name>.png, to check a
transcription by eye.

    python tools/metroid-maps/render.py mzm brinstar
    python tools/metroid-maps/render.py mzm brinstar --ref    # adds the reference crop alongside

Also reports overlapping rooms, doors that don't join two different rooms, and items outside
any room. --ref needs the game's reference map at games/<game>/ref/full.jpg (see README.md).
Needs Pillow (pip install pillow).
"""
import sys
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

HERE = Path(__file__).resolve().parent
OUT = HERE / 'out'
FILL = ["#1E3550", "#1B3A24", "#4A3A12"]  # normal, hidden, heated
DOOR = {'n': "#4FC3F7", 'r': "#D8342C", 'g': "#3DDC6A", 'y': "#F2C94C"}
ITEM = {'u': "#F28C28", 'e': "#7CFC6A", 'r': "#C9B8F0", 'm': "#E8604C", 's': "#4FC3F7", 'p': "#F2C94C"}


def font(size, bold=False):
    try:
        return ImageFont.truetype('consolab.ttf' if bold else 'consola.ttf', size)
    except OSError:
        return ImageFont.load_default()


def load(game, name):
    ns = {}
    exec((HERE / 'games' / game / 'areas' / f'{name}.py').read_text(encoding='utf-8'), ns)
    return ns['rooms'], ns['doors'], ns['items']


def check(rooms, doors, items):
    own = {}
    for i, room in enumerate(rooms):
        for c in room[0]:
            if c in own:
                print("overlap at", c)
            own[c] = i
    for row, col, _ in doors:
        a, b = (col, row), (col + 1, row)
        if a not in own or b not in own or own[a] == own[b]:
            print("door doesn't join two rooms: row", row, "col", col)
    for iid, c in items.items():
        if c not in own:
            print("item outside any room:", iid, c)
    return own


def draw(rooms, doors, items, s=48):
    own = check(rooms, doors, items)
    c0 = min(c for c, _ in own); c1 = max(c for c, _ in own)
    r0 = min(r for _, r in own); r1 = max(r for _, r in own)
    m = s
    im = Image.new('RGB', ((c1 - c0 + 1) * s + 2 * m, (r1 - r0 + 1) * s + 2 * m), "#0B0E14")
    d = ImageDraw.Draw(im)
    X = lambda c: m + (c - c0) * s
    Y = lambda r: m + (r - r0) * s
    for (c, r), i in own.items():
        d.rectangle([X(c), Y(r), X(c) + s, Y(r) + s], fill=FILL[rooms[i][1]])
    for (c, r), i in own.items():
        col = "#7CFC6A" if rooms[i][1] == 1 else "#E8ECF2"
        if own.get((c + 1, r)) != i: d.line([X(c + 1), Y(r), X(c + 1), Y(r + 1)], fill=col, width=2)
        if own.get((c - 1, r)) != i: d.line([X(c), Y(r), X(c), Y(r + 1)], fill=col, width=2)
        if own.get((c, r + 1)) != i: d.line([X(c), Y(r + 1), X(c + 1), Y(r + 1)], fill=col, width=2)
        if own.get((c, r - 1)) != i: d.line([X(c), Y(r), X(c + 1), Y(r)], fill=col, width=2)
    for row, col, k in doors:
        x = X(col + 1); y = Y(row)
        d.rectangle([x - 3, y + s // 4, x + 3, y + s - s // 4], fill=DOOR[k])
    for cells, _, note in rooms:
        c, r = min(cells, key=lambda p: (p[1], p[0]))
        marks = [part[5:] for part in note.split(';') if part.startswith('mark:')]
        for tag, letter, color in (('save', 'S', "#F2C94C"), ('map', 'M', "#F2C94C"), ('boss', 'B', "#D8342C"), ('elev', '^', "#F28C28")):
            if tag in note:
                d.text((X(c) + 4, Y(r) + 2), letter, fill=color, font=font(14, True))
        for mk in marks:
            d.text((X(c) + 4, Y(r) + 2), mk, fill="#F2C94C", font=font(14, True))
    for iid, (c, r) in items.items():
        k = 'u' if iid.startswith('u_') else iid.split('_')[1][0]
        cx = X(c) + s / 2; cy = Y(r) + s / 2; h = s * 0.14
        if k == 'u': d.polygon([(cx, cy - h * 1.3), (cx + h * 1.3, cy), (cx, cy + h * 1.3), (cx - h * 1.3, cy)], fill=ITEM[k])
        elif k in 'er': d.rectangle([cx - h, cy - h, cx + h, cy + h], fill=ITEM[k])
        else: d.ellipse([cx - h, cy - h, cx + h, cy + h], fill=ITEM[k])
    return im, (c0, c1, r0, r1)


def main():
    game, name = sys.argv[1], sys.argv[2]
    rooms, doors, items = load(game, name)
    im, bounds = draw(rooms, doors, items)
    if '--ref' in sys.argv:
        import crop
        ref = crop.crop(game, *bounds, s=3, area=name)
        both = Image.new('RGB', (ref.width + im.width + 10, max(ref.height, im.height)), "black")
        both.paste(ref, (0, 0)); both.paste(im, (ref.width + 10, 0)); im = both
    OUT.mkdir(exist_ok=True)
    im.save(OUT / f'{game}-{name}.png')
    print(f"{len(rooms)} rooms, {len(doors)} doors, {len(items)} items -> {OUT / (game + '-' + name + '.png')}")


if __name__ == '__main__':
    main()
