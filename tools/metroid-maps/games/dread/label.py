"""Labels every item and station on a Dread reference image, for writing the item steps.

    python label.py artaria [parts]   # -> ../../out/dread-<area>-label-<n>.png, split into parts across
"""
import json
import sys
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

HERE = Path(__file__).resolve().parent
COLORS = {'missile': (255, 90, 90), 'etank': (90, 255, 90), 'epart': (150, 255, 150), 'pb': (255, 220, 60),
          'upgrade': (255, 160, 40), 'boss': (255, 60, 255)}


def main():
    area = sys.argv[1]
    parts = int(sys.argv[2]) if len(sys.argv) > 2 else 2
    im = Image.open(HERE / 'ref' / f'{area}.png').convert('RGB')
    items = json.loads((HERE / 'ref' / f'{area}.json').read_text(encoding='utf-8'))
    sheet = {s['mg']: s for s in json.loads((HERE / 'ref' / f'{area}.sheet.json').read_text(encoding='utf-8'))}
    d = ImageDraw.Draw(im)
    try:
        font = ImageFont.truetype('arialbd.ttf', 26)
        small = ImageFont.truetype('arial.ttf', 20)
    except OSError:
        font = small = ImageFont.load_default()
    for it in items:
        s = sheet.get(it['id'], {})
        x, y = it['x'], it['y']
        if it['kind'] in COLORS:
            label = s.get('id') or it['title']
            c = COLORS[it['kind']]
            d.ellipse([x - 10, y - 10, x + 10, y + 10], outline=c, width=4)
            d.text((x + 12, y - 14), (label[2:] if label.startswith('u_') else label), fill=c, font=font, stroke_width=3, stroke_fill=(0, 0, 0))
        else:
            d.text((x - 10, y - 10), it['kind'][:4].upper(), fill=(120, 220, 255), font=small, stroke_width=2, stroke_fill=(0, 0, 0))
    xs = [i['x'] for i in items]; ys = [i['y'] for i in items]
    x0, x1, y0, y1 = max(0, min(xs) - 150), min(im.width, max(xs) + 150), max(0, min(ys) - 150), min(im.height, max(ys) + 150)
    step = (x1 - x0) // parts
    for n in range(parts):
        part = im.crop((x0 + n * step - (40 if n else 0), y0, x0 + (n + 1) * step + 40, y1))
        part.thumbnail((1900, 1900))
        part.save(HERE.parents[1] / 'out' / f'dread-{area}-label-{n + 1}.png')
    print('ok')


if __name__ == '__main__':
    main()
