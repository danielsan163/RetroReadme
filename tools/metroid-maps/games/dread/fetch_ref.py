"""Builds Metroid Dread's reference images from MapGenie's interactive map (not in git).

    python tools/metroid-maps/games/dread/fetch_ref.py [zoom]

Downloads the map data (item positions) to ref/mg_data.json and stitches each area's map tiles
into ref/<area>.png, with ref/<area>.json giving the pixel position of every item in that image.
Reference only: nothing from MapGenie ships in the app.
"""
import json
import math
import sys
import urllib.request
from pathlib import Path
from PIL import Image

HERE = Path(__file__).resolve().parent
REF = HERE / 'ref'
UA = {'User-Agent': 'Mozilla/5.0', 'Referer': 'https://mapgenie.io/metroid-dread/maps/zdr-planet-map'}
TILE = 'https://tiles.mapgenie.io/games/metroid-dread/zdr-planet-map/default-v1/{z}/{x}/{y}.png'
KINDS = {4616: 'missile', 4617: 'etank', 4618: 'pb', 4619: 'epart', 4620: 'upgrade', 4623: 'boss', 4626: 'unit',
         4610: 'save', 4625: 'map', 4614: 'transport', 4609: 'teleportal', 4611: 'network', 4612: 'recharge', 4624: 'energy', 4613: 'ammo'}


def get(url):
    return urllib.request.urlopen(urllib.request.Request(url, headers=UA)).read()


def px(lat, lng, z):
    """Web Mercator world pixel coordinates at zoom z."""
    n = 256 * 2 ** z
    x = (lng + 180) / 360 * n
    s = math.sin(math.radians(lat))
    y = (0.5 - math.log((1 + s) / (1 - s)) / (4 * math.pi)) * n
    return x, y


def main():
    z = int(sys.argv[1]) if len(sys.argv) > 1 and sys.argv[1].isdigit() else 13
    REF.mkdir(exist_ok=True)
    data_path = REF / 'mg_data.json'
    if not data_path.exists():
        data_path.write_bytes(get('https://mapgenie.io/api/v1/maps/333/data'))
    d = json.loads(data_path.read_text(encoding='utf-8'))
    regions = {r['id']: r['title'] for r in d['regions']}
    by_area = {}
    for loc in d['locations']:
        kind = KINDS.get(loc['category_id'])
        if kind:
            by_area.setdefault(regions.get(loc['region_id'], '?'), []).append((loc, kind))
    for area, locs in by_area.items():
        pts = [px(float(l['latitude']), float(l['longitude']), z) for l, _ in locs]
        pad = 600
        x0 = min(p[0] for p in pts) - pad; x1 = max(p[0] for p in pts) + pad
        y0 = min(p[1] for p in pts) - pad; y1 = max(p[1] for p in pts) + pad
        tx0, tx1, ty0, ty1 = int(x0 // 256), int(x1 // 256), int(y0 // 256), int(y1 // 256)
        im = Image.new('RGB', ((tx1 - tx0 + 1) * 256, (ty1 - ty0 + 1) * 256), (10, 16, 24))
        for tx in (range(tx0, tx1 + 1) if not (REF / f'{area.lower()}.png').exists() or '--tiles' in sys.argv else ()):
            for ty in range(ty0, ty1 + 1):
                try:
                    tile = Image.open(__import__('io').BytesIO(get(TILE.format(z=z, x=tx, y=ty)))).convert('RGB')
                    im.paste(tile, ((tx - tx0) * 256, (ty - ty0) * 256))
                except Exception:
                    pass
        name = area.lower()
        if not (REF / f'{name}.png').exists() or '--tiles' in sys.argv:
            im.save(REF / f'{name}.png')
        items = [{'id': l['id'], 'kind': k, 'title': l['title'], 'desc': l.get('description') or '',
                  'x': round(p[0] - tx0 * 256), 'y': round(p[1] - ty0 * 256)} for (l, k), p in zip(locs, pts)]
        (REF / f'{name}.json').write_text(json.dumps(items, indent=1), encoding='utf-8')
        region = next(r for r in d['regions'] if r['title'] == area)
        polys = [[[round(a - tx0 * 256), round(b - ty0 * 256)] for a, b in (px(lat, lng, z) for lng, lat in ring)]
                 for f in region['features'] for ring in f['geometry']['coordinates']]
        (REF / f'{name}.region.json').write_text(json.dumps(polys), encoding='utf-8')
        print(area, im.size, len(items))


if __name__ == '__main__':
    main()
