"""Crops a game's reference map to a block of grid cells, enlarged, with column and row numbers.

    python tools/metroid-maps/crop.py mzm 0 15 0 9            # cols 0..15, rows 0..9 -> out/crop.png
    python tools/metroid-maps/crop.py mf maindeck 12 34 23 47 # an area with its own grid offset

Use it to read rooms off the reference: each labeled square is one map cell. Needs the game's
reference image at games/<game>/ref/full.jpg (not in git, see README.md) and Pillow.

Grid: REF_CELL and REF_ORIGIN in games/<game>/config.py give the cell size in pixels and where
cell (0, 0)'s top-left corner is; REF_ORIGINS overrides the origin per area file. Columns grow right and rows grow down; cells left of or above
the origin are negative.
"""
import sys
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

HERE = Path(__file__).resolve().parent


def config(game):
    ns = {}
    exec((HERE / 'games' / game / 'config.py').read_text(encoding='utf-8'), ns)
    return ns


def crop(game, c0, c1, r0, r1, s=4, area=None):
    cfg = config(game)
    K = cfg['REF_CELL']
    X0, Y0 = cfg.get('REF_ORIGINS', {}).get(area, cfg['REF_ORIGIN'])
    im = Image.open(HERE / 'games' / game / 'ref' / 'full.jpg').convert('RGB')
    box = (round(X0 + K * c0), round(Y0 + K * r0), round(X0 + K * (c1 + 1)), round(Y0 + K * (r1 + 1)))
    sub = im.crop(box).resize(((box[2] - box[0]) * s * 16 // K, (box[3] - box[1]) * s * 16 // K), Image.NEAREST)
    m = 30
    out = Image.new('RGB', (sub.width + m, sub.height + m), (0, 0, 0))
    out.paste(sub, (m, m))
    d = ImageDraw.Draw(out)
    try:
        f = ImageFont.truetype('consola.ttf', 13)
    except OSError:
        f = ImageFont.load_default()
    for c in range(c0, c1 + 1):
        x = m + (c - c0) * 16 * s
        d.text((x + 16 * s // 2 - 8, 8), str(c), fill=(255, 255, 0), font=f)
        d.line([(x, m - 6), (x, m)], fill=(255, 255, 0))
    for r in range(r0, r1 + 1):
        y = m + (r - r0) * 16 * s
        d.text((2, y + 16 * s // 2 - 7), str(r), fill=(255, 255, 0), font=f)
        d.line([(m - 6, y), (m, y)], fill=(255, 255, 0))
    return out


if __name__ == '__main__':
    args = sys.argv[1:]
    area = args.pop(1) if not args[1].lstrip('-').isdigit() else None
    out = crop(args[0], *map(int, args[1:5]), area=area)
    (HERE / 'out').mkdir(exist_ok=True)
    out.save(HERE / 'out' / 'crop.png')
    print(HERE / 'out' / 'crop.png')
