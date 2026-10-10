# Metroid map tools

The in-app maps (`app/.../games/<game>/<Game>MapData.kt`) are generated from hand-checked area
files, one folder per game:

- `games/mzm/`: Metroid: Zero Mission (writes `MzmMapData.kt`)
- `games/mf/`: Metroid Fusion (writes `MfMapData.kt`)
- `games/sm/`: Super Metroid (writes `SmMapData.kt`)
- `games/dread/`: Metroid Dread (writes `DreadMapData.kt`; see below, it's built differently)

Each game folder has `config.py` (Kotlin package, output file, areas, entry rooms, reference
grid), `areas/` (one file per area) and `ref/` (the reference map, not in git).
Never edit a `*MapData.kt` by hand; the next `gen.py` run overwrites it.

Needs Python 3 and Pillow (`pip install pillow`) for everything except `gen.py`.

## Files

- `areas/<area>.py`: rooms, doors and item positions for one area. The header of each Zero
  Mission file describes the format; Fusion's follow it too. Coordinates are map-grid cells
  `(col, row)`, columns growing right and rows down.
- `gen.py <game>`: writes the game's map data. Numbers each area's rooms (BR-01, S2-14...) in the
  order the game's 100% route reaches them, starting from the room where you enter the area.
  Room codes shift if you add, split or merge rooms, or reorder the route, and item steps cite
  them, so re-check those steps after any change.
- `render.py <game> <area> [--ref]`: draws an area to `out/<game>-<area>.png` and reports
  overlapping rooms, doors that don't join two rooms, and items outside any room. `--ref` puts
  the matching crop of the reference map alongside.
- `crop.py <game> [<area>] c0 c1 r0 r1`: crops the reference map to those cells, enlarged and
  labeled, for reading rooms off it. Pass the area when it has its own grid origin.
- `extract.py <game> <area> c0 c1 r0 r1`: a first-draft area file read automatically off the
  reference (rooms, walls, doors). Then add room letters, exits, bosses and items with its
  `annotate()` helper (its `add` argument puts cells hidden under item icons back into rooms) and
  check with `render.py --ref`. See its docstring for what it reads.

## Reference maps

The layouts were transcribed from Falcon Zero's complete maps on Metroid Recon:

- Zero Mission: https://metroid.retropixel.net/games/metroidzm/metroidzm_map.jpg
- Fusion: https://metroid.retropixel.net/games/metroid4/metroidfusion_map.jpg
- Super Metroid: https://metroid.retropixel.net/games/metroid3/metroid3_map.gif (save it as
  `ref/full.jpg`; any format Pillow reads works)

They're their work and Nintendo's art, so they aren't in the repo and nothing from them ships in
the app; the app draws its own maps from the room data. To use `--ref`, `crop.py` or
`extract.py`, download the game's map to `games/<game>/ref/full.jpg` (ignored by git).

Grid: `REF_CELL` and `REF_ORIGIN` in the config give the cell size and where cell (0, 0) starts.
Fusion's sectors are each drawn a few pixels off from one another, so `REF_ORIGINS` sets an
origin per area; cell coordinates are only meaningful within one area file.

Super Metroid's map is all of Zebes in one picture, each area in its own color, with door
ovals on the walls and room splits drawn as short wall stubs. Its config supplies `REF_KIND` (the
pixel classifier), `REF_AREA_ROOM` (each area's room color), `REF_STUBS`, `REF_ROOM_MIN` and
`REF_DOOR_NEEDS_WALL` for `extract.py`; the defaults suit the Fusion and Zero Mission maps. The
elevator rails come out as tall one-column rooms: delete them from the area file.

## Fixing a room

1. `python tools/metroid-maps/crop.py <game> <area> <c0> <c1> <r0> <r1>` around the room, and
   compare with the in-game pause map.
2. Edit the room's cells, doors or note in `games/<game>/areas/<area>.py`.
3. `python tools/metroid-maps/render.py <game> <area> --ref` and check the drawing and warnings.
4. `python tools/metroid-maps/gen.py <game>`, then rebuild the app and re-run the web export.

Least certain transcriptions: Zero Mission's Chozodia (ship middle and Chozo Ruins maze), and
which tank is which in a few Fusion rooms where several fit the description (Sector 4's middle
rooms and Sector 5's west side).

## Metroid Dread

Dread's in-game map has no room outlines, so its area files are made by two scripts in
`games/dread/` instead of being hand-transcribed:

1. `fetch_ref.py` downloads MapGenie's Dread map data (item positions, area outlines) and stitches
   each area's map tiles into `ref/<area>.png` (zoom 13), with `ref/<area>.json` giving every item's
   pixel position and `ref/<area>.region.json` the area's outline. Reference only, not in git.
2. `extract_dread.py` cuts each area's open space at its door icons (light-grey framed boxes),
   splits it into rooms, lays a 48 px grid over it, and writes `areas/<area>.py` plus
   `ref/<area>.sheet.json` (each item's id, kind, cell and room). Item ids are numbered top to
   bottom on the map, so rerunning it keeps them stable.

`overlay.py <area>` draws an area file over its reference to check it, and `label.py <area>` labels
every item and station for writing the item text. Then `gen.py dread` as usual. Rerunning
`extract_dread.py` overwrites the area files, so make any hand fixes after the last run.
