# Super Metroid map settings for gen.py, render.py, crop.py and extract.py.
PACKAGE_DIR = 'sm'
ROUTE_FILE = 'SmRoute.kt'
OUTPUT = 'SmMapData.kt'
OBJECT = 'SmMapData'

# (area key, label, room code prefix, area file, entry cell where you first arrive). Tourian has
# no items, so it has no map.
AREAS = [
    ("CRATERIA", "Crateria", "CR", "crateria", (25, 5)),
    ("BRINSTAR", "Brinstar", "BR", "brinstar", (22, 32)),
    ("NORFAIR", "Norfair", "NO", "norfair", (37, 47)),
    ("WRECKED_SHIP", "Wrecked Ship", "WS", "wreckedship", (45, 5)),
    ("MARIDIA", "Maridia", "MA", "maridia", (36, 28)),
]

# Reference map (ref/full.jpg, not in git): one map of all of Zebes, 16 px cells. Every area
# shares the grid; the walls of the top two areas sit half a pixel lower.
REF_CELL = 16
REF_ORIGIN = (4, 11)
REF_ORIGINS = {'crateria': (4, 12), 'wreckedship': (4, 12)}

# Room splits are drawn as short stubs at both ends of the wall.
REF_STUBS = (0.07, 0.07, 0.875, 0.875)
# Letters and item icons cover most of some cells, so one room-colored sample is enough.
REF_ROOM_MIN = 1
# Item icons can look like doors; a real door sits on a wall.
REF_DOOR_NEEDS_WALL = True

# Each area is drawn in its own color, so extract.py only reads cells of that color.
REF_AREA_ROOM = {
    'crateria': 'brown', 'wreckedship': 'grey', 'brinstar': 'green',
    'maridia': 'blue', 'norfair': 'red',
}


def REF_KIND(p):
    """Classifies one pixel of the reference map for extract.py."""
    r, g, b = p
    if min(r, g, b) > 165 and r + g + b > 630: return 'w'           # walls are tinted by the area color
    if g > 160 and b > 220 and r < 90: return 'bd'                  # blue (normal) door
    if r > 185 and 60 < g < 120 and b > 130: return 'rd'           # pink missile door
    if g > 200 and r < 90 and b < 90: return 'gd'                   # green super door
    if r > 220 and g > 220 and b < 110: return 'yd'                 # yellow power bomb door
    if 110 < r < 160 and 60 < g < 95 and b < 60: return 'brown'
    if 70 < r < 100 and 80 < g < 105 and 85 < b < 115 and g > r and b > r + 8: return 'grey'  # not the pinkish glow
    if 130 < r < 175 and 40 < g < 75 and 95 < b < 135: return 'purple'
    if r < 60 and 110 < g < 150 and b < 30: return 'green'
    if r < 60 and 75 < g < 110 and b > 180: return 'blue'
    if 130 < r < 165 and g < 60 and b < 65: return 'red'
    return '.'
