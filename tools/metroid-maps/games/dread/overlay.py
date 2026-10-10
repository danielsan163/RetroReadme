"""Draws an area file's rooms, doors and items over its Dread reference image, to check it.

    python overlay.py artaria [x0 y0 x1 y1]   # -> ../../out/dread-<area>-overlay.png (optionally cropped, in px)
"""
import sys
from pathlib import Path
from PIL import Image, ImageDraw

HERE = Path(__file__).resolve().parent
CELL = 48


def main():
    area = sys.argv[1]
    ns = {}
    exec((HERE / 'areas' / f'{area}.py').read_text(encoding='utf-8'), ns)
    im = Image.open(HERE / 'ref' / f'{area}.png').convert('RGB')
    im = Image.blend(im, Image.new('RGB', im.size, (0, 0, 0)), 0.35)
    d = ImageDraw.Draw(im)
    own = {c: i for i, r in enumerate(ns['rooms']) for c in r[0]}
    for (cx, cy), i in own.items():
        x, y = cx * CELL, cy * CELL
        if own.get((cx + 1, cy)) != i: d.line([x + CELL, y, x + CELL, y + CELL], fill=(255, 255, 255), width=2)
        if own.get((cx - 1, cy)) != i: d.line([x, y, x, y + CELL], fill=(255, 255, 255), width=2)
        if own.get((cx, cy + 1)) != i: d.line([x, y + CELL, x + CELL, y + CELL], fill=(255, 255, 255), width=2)
        if own.get((cx, cy - 1)) != i: d.line([x, y, x + CELL, y], fill=(255, 255, 255), width=2)
    for i, (cells, _, note) in enumerate(ns['rooms']):
        cx, cy = min(cells, key=lambda p: (p[1], p[0]))
        d.text((cx * CELL + 3, cy * CELL + 2), str(i) + (' ' + note[:6] if note else ''), fill=(255, 255, 0))
    for r, c, _ in ns['doors']:
        x = (c + 1) * CELL; y = r * CELL
        d.rectangle([x - 3, y + 8, x + 3, y + CELL - 8], fill=(80, 200, 255))
    for iid, (cx, cy) in ns['items'].items():
        x, y = cx * CELL + CELL // 2, cy * CELL + CELL // 2
        d.ellipse([x - 6, y - 6, x + 6, y + 6], outline=(255, 0, 255), width=3)
    if len(sys.argv) > 2:
        im = im.crop(tuple(map(int, sys.argv[2:6])))
    out = HERE.parents[1] / 'out' / f'dread-{area}-overlay.png'
    im.save(out)
    print(out)


if __name__ == '__main__':
    main()
