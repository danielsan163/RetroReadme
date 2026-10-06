# Metroid: Zero Mission map tools

The in-app maps (`app/.../games/mzm/MzmMapData.kt`) are generated from the hand-transcribed
area files in `areas/`. Edit an area file, check it with `render.py`, then run `gen.py`.
Never edit `MzmMapData.kt` by hand; the next `gen.py` run overwrites it.

Needs Python 3 and Pillow (`pip install pillow`) for `render.py` and `crop.py`. `gen.py` only
needs Python.

## Files

- `areas/<area>.py`: rooms, doors and item positions for one area. The header of each file
  describes the format. Coordinates are map-grid cells `(col, row)`, one cell per square on the
  in-game map, with columns growing right and rows growing down. All areas share one grid.
- `gen.py`: writes `MzmMapData.kt`. Numbers each area's rooms (BR-01, BR-02...) in the order the
  100% route in `MzmRoute.kt` reaches them, starting from the room where you enter the area.
  Room codes will shift if you add, split or merge rooms, or reorder the route.
- `render.py <area> [--ref]`: draws an area to `out/<area>.png` and reports overlapping rooms,
  doors that don't join two rooms, and items outside any room. `--ref` puts the matching crop of
  the reference map alongside.
- `crop.py c0 c1 r0 r1`: crops the reference map to those cells, enlarged and labeled, for
  reading rooms off it.

## Reference map

The layouts were transcribed from Falcon Zero's complete map on Metroid Recon:
https://metroid.retropixel.net/games/metroidzm/metroidzm_map.jpg

It's their work and Nintendo's art, so it isn't in the repo and nothing from it ships in the app;
the app draws its own maps from the room data. To use `--ref` or `crop.py`, download it to
`ref/full.jpg` (ignored by git).

## Fixing a room

1. `python tools/mzm-maps/crop.py <c0> <c1> <r0> <r1>` around the room, and compare with the
   in-game pause map.
2. Edit the room's cells, doors or note in `areas/<area>.py`.
3. `python tools/mzm-maps/render.py <area> --ref` and check the drawing and the warnings.
4. `python tools/mzm-maps/gen.py`, then rebuild the app.

The Chozodia ship middle and the Chozo Ruins maze are the least certain transcriptions.
