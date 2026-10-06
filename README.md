# Retro README

Offline walkthroughs for retro games, laid out for the Retroid Pocket Nova's 1280x960 (4:3) landscape
screen and driven entirely by its controls. Runs on Android 8.0 (API 26) and up.

Games: Mega Man X2 (SNES), Super Mario World (SNES), Kirby's Dream Land 3 (SNES), Wario Land 4 (GBA, Normal and Hard), Metroid: Zero Mission (GBA), Castlevania: Aria of Sorrow (GBA).

## Web version

`docs/` is a web version of every guide for phones and browsers, served by GitHub Pages. It works
offline once loaded and can be added to an iPhone's Home Screen (Share > Add to Home Screen).
Checkmarks are saved per device. Its data is exported from the guide content:

    gradle :app:testDebugUnitTest --tests com.retroreadme.web.WebExport

## Build

1. Open this folder in Android Studio and let it sync (it will fetch Gradle 8.11.1 and the Android plugin).
   If it complains about a missing Gradle wrapper, copy `gradlew`, `gradlew.bat` and
   `gradle/wrapper/gradle-wrapper.jar` from any other project (DockSwap works), or run `gradle wrapper`.
2. Build > Build APK(s), then `adb install -r app/build/outputs/apk/debug/app-debug.apk`.

## Controls

- Launcher: D-pad picks a game, A opens it (or opens and closes a console or group), B exits. Select (or D-pad up to the top bar) opens the theme menu.
- D-pad: move. Moving through the left list changes the right side immediately.
- D-pad right: step into the detail pane; D-pad down walks through it and scrolls.
- A: check off an item. B (or D-pad left): back to the list. B on the list: back to the launcher.
- L1 / R1: switch tabs. L2 / R2: page the detail pane up/down.
- Touch works everywhere too.

## Layout

- `core/`: the `Game` / `GameTab` model, per-game `ProgressStore`, and the shared `Section` text block.
- `ui/`: the app shell (launcher, guide screen, tab bar, two-pane list, panels, theme, 100% overlay).
- `games/<game>/`: one folder per game: its content, its models, its detail screens, and a
  `<Game>Game.kt` that declares its tabs and checklist.
- `games/GameRegistry.kt`: the list the launcher shows.

## Adding a game

1. Create `games/<game>/` with its content and a `Game(...)` definition (copy `mmx2/Mmx2Game.kt`).
2. Give it a unique, permanent `id`: checklist progress is stored under it.
3. Set its `platform` (see `core/Platform.kt`): the launcher lists it under that heading.
4. Add it to `GameRegistry.games`.

Checklist progress is stored per game in SharedPreferences (`progress_<gameId>`).
