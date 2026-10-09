# Metroid: Zero Mission map settings for gen.py, render.py and crop.py.
PACKAGE_DIR = 'mzm'
ROUTE_FILE = 'MzmRoute.kt'
OUTPUT = 'MzmMapData.kt'
OBJECT = 'MzmMapData'

# (area key, label, room code prefix, area file, entry cell where you first arrive)
AREAS = [
    ("BRINSTAR", "Brinstar", "BR", "brinstar", (0, 13)),
    ("KRAID", "Kraid's Lair", "KR", "kraid", (-22, 15)),
    ("NORFAIR", "Norfair", "NO", "norfair", (28, 16)),
    ("RIDLEY", "Ridley's Lair", "RI", "ridley", (2, 20)),
    ("TOURIAN", "Tourian", "TO", "tourian", (-13, -1)),
    ("CRATERIA", "Crateria", "CR", "crateria", (-6, -15)),
    ("CHOZODIA", "Chozodia", "CZ", "chozodia", (35, -10)),
]

# Reference map (ref/full.jpg, not in git): 16 px cells, cell (0, 0) top-left corner here.
REF_CELL = 16
REF_ORIGIN = (479.5, 454.5)
