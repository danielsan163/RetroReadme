"""First draft of an area file, read automatically off a game's reference map.

    python tools/metroid-maps/extract.py mf srx 2 20 5 21     # game, area, cols c0..c1, rows r0..r1

Writes games/<game>/areas/<area>.py with every room, wall and door it can see, using the area's
grid origin from the game's config (REF_ORIGINS). It doesn't place items, room letters, exits or
bosses: add those with annotate() (below) after reading them off crop.py, then check the result
with render.py --ref and fix anything by hand.

Tuned for Falcon Zero's maps (purple and green rooms, white walls, doors shown as a gap in the
middle of a wall, colored lock doors). It reads:
  - a boundary with white across its middle as a wall,
  - white only in short segments at both ends as a wall with a door gap (a door),
  - white only at the very corners as the same room,
  - a colored oval in the middle as a lock door (blue, green, yellow, red).
Cells mostly covered by an item icon can be missed; add them by hand.
"""
import ast
import collections
import re
import sys
from pathlib import Path
from PIL import Image

HERE = Path(__file__).resolve().parent
im = P = None
X0 = Y0 = 0.0
K = 16
STUBS = (0.15, 0.2, 0.8, 0.85)   # where a split wall's end stubs are (fractions along it)
DOOR_NEEDS_WALL = False   # a colored door only counts on a wall (icons can look like doors)
ROOM_MIN = 3   # samples of a cell (of 8) that must be room color
AREA_ROOM = None   # the room kind this area is drawn in, when the config sets one per area


def setup(game, area):
    global im, P, X0, Y0, K, kind, AREA_ROOM, STUBS, ROOM_MIN, DOOR_NEEDS_WALL
    ns = {}
    exec((HERE / 'games' / game / 'config.py').read_text(encoding='utf-8'), ns)
    K = ns['REF_CELL']
    X0, Y0 = ns.get('REF_ORIGINS', {}).get(area, ns['REF_ORIGIN'])
    # A game whose map uses other colors supplies its own pixel classifier (see games/sm).
    kind = ns.get('REF_KIND', kind)
    AREA_ROOM = ns.get('REF_AREA_ROOM', {}).get(area)
    STUBS = ns.get('REF_STUBS', STUBS)
    ROOM_MIN = ns.get('REF_ROOM_MIN', ROOM_MIN)
    DOOR_NEEDS_WALL = ns.get('REF_DOOR_NEEDS_WALL', DOOR_NEEDS_WALL)
    im = Image.open(HERE / 'games' / game / 'ref' / 'full.jpg').convert('RGB')
    P = im.load()


def kind(p):
    r,g,b=p
    if r>200 and g>200 and b>200: return 'w'
    if r>100 and g<60 and b>100 and abs(r-b)<60: return 'P'      # purple room
    if g>200 and r<120 and b<120: return 'gd'                      # bright green door
    if g>110 and r<80 and b<80: return 'G'                         # green room
    if b>200 and r<70 and 90<g<170: return 'bd'                    # blue door
    if r>200 and g>180 and b<100: return 'yd'                      # yellow door / S letters
    if r>200 and g<80 and b<80: return 'rd'
    return '.'
def cell(c,r):
    x=X0+K*c; y=Y0+K*r; cnt=collections.Counter()
    for fx in (0.12,0.5,0.88):
        for fy in (0.12,0.5,0.88):
            if fx==0.5 and fy==0.5: continue
            cnt[kind(P[int(x+fx*K),int(y+fy*K)])]+=1
    if AREA_ROOM:
        return 'P' if cnt[AREA_ROOM]>=ROOM_MIN else None
    for k in ('P','G'):
        if cnt[k]>=3: return k
    return None
def boundary(c,r,vertical):
    """'|' wall, 'n' wall with a door gap, b/g/y/r colored door, ' ' same room."""
    def white_at(f):
        hits=0
        for d in (-1,0,1):
            if vertical: x=X0+K*(c+1)+d; y=Y0+K*(r+f)
            else: x=X0+K*(c+f); y=Y0+K*(r+1)+d
            hits+= kind(P[int(x),int(y)])=='w'
        return hits>0
    mid=collections.Counter()
    for f in (0.4,0.45,0.5,0.55,0.6):
        for d in (-3,-2,-1,0,1,2):
            if vertical: x=X0+K*(c+1)+d; y=Y0+K*(r+f)
            else: x=X0+K*(c+f); y=Y0+K*(r+1)+d
            mid[kind(P[int(x),int(y)])]+=1
    left=white_at(STUBS[0]) and white_at(STUBS[1])
    right=white_at(STUBS[2]) and white_at(STUBS[3])
    if left and right or not DOOR_NEEDS_WALL:
        for dk,name in (('bd','b'),('gd','g'),('yd','y'),('rd','r')):
            if mid[dk]>=4: return name
    mid['w']=sum(kind(P[int(X0+K*(c+1)+d) if vertical else int(X0+K*(c+f)), int(Y0+K*(r+f)) if vertical else int(Y0+K*(r+1)+d)])=='w' for f in (0.4,0.5,0.6) for d in (-1,0,1))
    if left and right:
        return '|' if mid['w']>=3 else 'n'
    return ' '
def extract(c0,c1,r0,r1):
    cells={(c,r):cell(c,r) for c in range(c0,c1+1) for r in range(r0,r1+1)}
    cells={k:v for k,v in cells.items() if v}
    bnd={}
    for (c,r),t in cells.items():
        for (n,vert) in (((c+1,r),True),((c,r+1),False)):
            if n in cells:
                b=boundary(c,r,vert)
                if cells[n]!=t and b==' ': b='|'
                bnd[((c,r),n)]=b
    par={k:k for k in cells}
    def f(a):
        while par[a]!=a: par[a]=par[par[a]]; a=par[a]
        return a
    for (a,b),v in bnd.items():
        if v==' ': par[f(a)]=f(b)
    rooms=collections.defaultdict(list)
    for k in cells: rooms[f(k)].append(k)
    return cells,bnd,list(rooms.values())
def emit(name,c0,c1,r0,r1):
    cells,bnd,rooms=extract(c0,c1,r0,r1)
    rooms.sort(key=lambda cs:(min(r for c,r in cs),min(c for c,r in cs)))
    lines=["def R(c0,c1,r0,r1): return [(c,r) for c in range(c0,c1+1) for r in range(r0,r1+1)]","N,S,H=0,1,2","rooms=["]
    for cs in rooms:
        sty='S' if cells[cs[0]]=='G' else 'N'
        lines.append(f"    ({sorted(cs)},{sty},\"\"),")
    lines.append("]")
    dk={'b':'n','g':'g','y':'y','r':'r','n':'n'}
    ds=[(a[1],a[0],dk[v]) for (a,b),v in bnd.items() if v in dk and b[1]==a[1]]
    hd=[(a,b,v) for (a,b),v in bnd.items() if v in dk and b[0]==a[0]]
    lines.append("doors="+repr(sorted(ds)))
    if hd: lines.append("# horizontal doors (floor/ceiling hatches), not drawn: "+repr(hd))
    lines.append("items={}")
    open(name,'w',encoding='utf-8').write("\n".join(lines)+"\n")
    print(name,len(cells),"cells",len(rooms),"rooms",len(ds),"doors",len(hd),"floor hatches")


def annotate(path, notes, items, add=None):
    """Adds notes ({cell: "save" | "mark:N" | "elev:Sector 2:right" | "boss:Name"}) and items
    ({id: cell}) to an area file, by cell. add ({cell: neighbor}) first puts cells the extractor
    missed (usually under an item icon) into the room of a neighboring cell, or (None) a room
    of its own."""
    nl = chr(10)
    s = open(path, encoding='utf-8').read()
    head, rest = s.split("rooms=[" + nl, 1)
    body, tail = rest.split(nl + "]" + nl, 1)
    rooms = [list(ast.literal_eval(l.strip().rstrip(',').replace(',S,', ',1,').replace(',N,', ',0,')))
             for l in body.split(nl) if l.strip()]
    own = {tuple(c): i for i, r in enumerate(rooms) for c in r[0]}
    for cellpos, nb in (add or {}).items():
        assert cellpos not in own, ("already in a room", cellpos)
        if nb is None:  # a one-cell room of its own
            rooms.append([[cellpos], 0, ""])
            own[cellpos] = len(rooms) - 1
            continue
        assert nb in own, ("no room at", nb)
        rooms[own[nb]][0].append(cellpos)
        own[cellpos] = own[nb]
    for cellpos, note in notes.items():
        i = own.get(cellpos)
        assert i is not None, ("no room at", cellpos)
        rooms[i][2] = ";".join(x for x in (rooms[i][2], note) if x)
    for iid, c in items.items():
        assert c in own, ("item off map", iid, c)
    lines = [f"    ({sorted(map(tuple, r[0]))},{'S' if r[1] == 1 else 'N'},{r[2]!r})," for r in rooms]
    tail = re.sub(r"items=\{.*\}" + nl + "?", "", tail)
    open(path, 'w', encoding='utf-8').write(
        head + "rooms=[" + nl + nl.join(lines) + nl + "]" + nl + tail.rstrip(nl) + nl + "items=" + repr(items) + nl)


if __name__ == '__main__':
    game, area = sys.argv[1], sys.argv[2]
    setup(game, area)
    emit(str(HERE / 'games' / game / 'areas' / f'{area}.py'), *map(int, sys.argv[3:7]))
