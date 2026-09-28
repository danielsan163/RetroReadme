# Retro README

Multi-game retro walkthrough app for the Retroid Pocket Nova (1280x960, 4:3, Android 13).
Kotlin + Jetpack Compose, no third-party deps, minSdk 26, landscape only, driven entirely by
D-pad and shoulder buttons. Package `com.retroreadme`. Built on the bones of
https://github.com/danielsan163/MMX2Guide.

Earlier work happened in a claude.ai chat (delivered as zips):
https://claude.ai/share/e3dcd836-b418-4f2d-a27d-e37c1851696d

## Structure

- `core/`: `Game`, `GameTab`, `Platform` (platform order = launcher heading order).
- `games/GameRegistry.kt`: every guide; the launcher groups by platform and sorts titles
  alphabetically, so add new games in any order.
- `games/<id>/`: one folder per guide (mmx2, smw, kdl3, wl4, mzm, aos), each with its own
  tabs, content, palette and screens. Checklist progress is stored per game in
  SharedPreferences `progress_<gameId>`. Never change existing checklist ids, or users lose
  their checkmarks.
- Launcher uses a dark SNES grey/purple palette (`LauncherPalette` in `ui/Theme.kt`); each game
  has its own palette.

## How we work

- The user builds in Android Studio on Windows and tests on the Nova over wireless debugging.
  Say which files changed and whether a Gradle sync is needed or Reload All from Disk is enough.
- Before writing guide content, propose the tab set, checklist scope and palette and get a yes.
- Verify game facts against at least two independent sources. Where only one source covers
  something, add the "worth confirming on the Nova" note on that page and say so.
- Log every user-visible change in `CHANGELOG.md` under the current version
  (`versionName` in `app/build.gradle.kts`, currently "0.2.0"). Ask before bumping it.

## Open items (as of 2026-09-28)

- Castlevania: Aria of Sorrow: HP/MP/Heart Max Ups aren't in the checklist yet (they'll be added
  as new entries; the soul ids must stay unchanged), and it still needs a Bosses tab with strategies.
- Metroid: Zero Mission: guide assumes Hard mode items are in the same places; unconfirmed.
