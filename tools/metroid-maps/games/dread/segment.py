"""Prototype: finds terrain and door icons in a Dread reference image and draws what it found.

    python segment.py artaria      # -> ../../out/dread-artaria-seg.png
"""
import sys
from collections import deque
from pathlib import Path
from PIL import Image, ImageDraw

HERE = Path(__file__).resolve().parent
BG = (10, 22, 34)


def is_terrain(p):
    r, g, b = p
    return abs(r - BG[0]) + abs(g - BG[1]) + abs(b - BG[2]) > 30


def is_frame(p):
    r, g, b = p
    return min(r, g, b) > 140 and max(r, g, b) - min(r, g, b) < 50


def components(mask, w, h, min_size=1):
    seen = bytearray(w * h)
    out = []
    for i in range(w * h):
        if mask[i] and not seen[i]:
            q = deque([i]); seen[i] = 1; pts = []
            while q:
                j = q.popleft(); pts.append(j)
                x, y = j % w, j // w
                for nx, ny in ((x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1)):
                    if 0 <= nx < w and 0 <= ny < h:
                        k = ny * w + nx
                        if mask[k] and not seen[k]:
                            seen[k] = 1; q.append(k)
            if len(pts) >= min_size:
                out.append(pts)
    return out


def main():
    area = sys.argv[1]
    im = Image.open(HERE / 'ref' / f'{area}.png').convert('RGB')
    w, h = im.size
    px = im.load()
    frame = bytearray(w * h)
    for y in range(h):
        for x in range(w):
            if is_frame(px[x, y]):
                frame[y * w + x] = 1
    blobs = components(frame, w, h, min_size=6)
    boxes = []
    for b in blobs:
        xs = [j % w for j in b]; ys = [j // w for j in b]
        bw, bh = max(xs) - min(xs) + 1, max(ys) - min(ys) + 1
        boxes.append((min(xs), min(ys), bw, bh, len(b)))
    print(len(boxes), 'light blobs')
    from collections import Counter
    print(Counter((bw // 2 * 2, bh // 2 * 2) for _, _, bw, bh, _ in boxes).most_common(25))
    d = ImageDraw.Draw(im)
    for x, y, bw, bh, n in boxes:
        d.rectangle([x - 1, y - 1, x + bw, y + bh], outline=(255, 0, 255))
    out = HERE.parents[1] / 'out'
    im.save(out / f'dread-{area}-seg.png')


if __name__ == '__main__':
    main()
