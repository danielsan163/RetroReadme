"""Crops the reference map to a block of grid cells, enlarged, with column and row numbers.

    python tools/mzm-maps/crop.py 0 15 0 9         # cols 0..15, rows 0..9 -> out/crop.png

Use it to read rooms off the reference: each labeled square is one map cell. Needs the reference
image at ref/full.jpg (not in git, see README.md) and Pillow.

Grid: the reference's map cells are 16 px, and cell (0, 0) has its top-left corner at
(X0, Y0) below. Columns grow right and rows grow down; areas left of or above it go negative.
"""
import sys
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

HERE = Path(__file__).resolve().parent
X0, Y0 = 479.5, 454.5


def crop(c0, c1, r0, r1, s=4):
    im = Image.open(HERE / 'ref' / 'full.jpg').convert('RGB')
    box = (round(X0 + 16 * c0), round(Y0 + 16 * r0), round(X0 + 16 * (c1 + 1)), round(Y0 + 16 * (r1 + 1)))
    sub = im.crop(box).resize(((box[2] - box[0]) * s, (box[3] - box[1]) * s), Image.NEAREST)
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
    out = crop(*map(int, sys.argv[1:5]))
    (HERE / 'out').mkdir(exist_ok=True)
    out.save(HERE / 'out' / 'crop.png')
    print(HERE / 'out' / 'crop.png')
