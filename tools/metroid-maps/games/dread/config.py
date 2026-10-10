# Metroid Dread map settings for gen.py. The area files come from extract_dread.py (see there),
# read off MapGenie's map (ref/, not in git); render.py works without --ref.
PACKAGE_DIR = 'dread'
ROUTE_FILE = 'DreadRoute.kt'
OUTPUT = 'DreadMapData.kt'
OBJECT = 'DreadMapData'

# (area key, label, room code prefix, area file, entry cell where you first arrive). Itorash has
# no items, so it has no map.
AREAS = [
    ("ARTARIA", "Artaria", "AR", "artaria", (57, 38)),
    ("CATARIS", "Cataris", "CA", "cataris", (51, 28)),
    ("DAIRON", "Dairon", "DA", "dairon", (67, 15)),
    ("BURENIA", "Burenia", "BU", "burenia", (33, 21)),
    ("FERENIA", "Ferenia", "FE", "ferenia", (26, 26)),
    ("GHAVORAN", "Ghavoran", "GH", "ghavoran", (16, 30)),
    ("ELUN", "Elun", "EL", "elun", (14, 17)),
    ("HANUBIA", "Hanubia", "HA", "hanubia", (14, 18)),
]

REF_CELL = 48
REF_ORIGIN = (0, 0)
