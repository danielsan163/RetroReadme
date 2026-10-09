# Metroid Fusion map settings for gen.py, render.py and crop.py.
PACKAGE_DIR = 'mf'
ROUTE_FILE = 'MfRoute.kt'
OUTPUT = 'MfMapData.kt'
OBJECT = 'MfMapData'

# (area key, label, room code prefix, area file, entry cell where you first arrive)
AREAS = [
    ("MAIN_DECK", "Main Deck", "MD", "maindeck", (30, 37)),
    ("SRX", "Sector 1 (SRX)", "S1", "srx", (3, 8)),
    ("TRO", "Sector 2 (TRO)", "S2", "tro", (24, 9)),
    ("PYR", "Sector 3 (PYR)", "S3", "pyr", (44, 8)),
    ("AQA", "Sector 4 (AQA)", "S4", "aqa", (57, 31)),
    ("ARC", "Sector 5 (ARC)", "S5", "arc", (17, 51)),
    ("NOC", "Sector 6 (NOC)", "S6", "noc", (43, 49)),
]

# Reference map (ref/full.jpg, not in git): 16 px cells, cell (0, 0) top-left corner here.
REF_CELL = 16
REF_ORIGIN = (7.5, 13.5)
# Each sector is drawn on its own grid offset; cell coordinates are per area.
REF_ORIGINS = {
    'srx': (7.5, 13.5), 'maindeck': (6.5, 0.5), 'tro': (7.5, 14.5), 'pyr': (1.5, 7.5),
    'aqa': (4.5, 6.5), 'arc': (7.5, 6.5), 'noc': (13.5, 2.5),
}
