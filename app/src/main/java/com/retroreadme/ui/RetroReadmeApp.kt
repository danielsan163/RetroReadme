package com.retroreadme.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.retroreadme.core.ProgressStore
import com.retroreadme.core.SettingsStore
import com.retroreadme.games.GameRegistry

/**
 * Top level: the launcher, or the open game's guide.
 *
 * B handling, most specific first: the celebration overlay, then the detail pane
 * (back to the list), then this handler (back to the launcher). On the launcher's
 * list nothing handles B, so it exits the app as usual.
 */
@Composable
fun RetroReadmeApp(stores: Map<String, ProgressStore>, settings: SettingsStore) {
    val games = GameRegistry.games
    var openId by rememberSaveable { mutableStateOf<String?>(null) }
    var launcherSelection by rememberSaveable { mutableStateOf(games.first().id) }
    // Which launcher groups are open. Kept here so it survives opening and closing a guide.
    val expandedGroups = remember { mutableStateListOf<String>() }
    // Theme being previewed in the dropdown, if any; otherwise the saved one shows.
    var previewTheme by remember { mutableStateOf<String?>(null) }
    val launcherTheme = LauncherTheme.byId(previewTheme ?: settings.launcherTheme)
    val open = games.firstOrNull { it.id == openId }

    BackHandler(enabled = open != null) { openId = null }

    if (open == null) {
        GuideTheme(launcherTheme.palette) {
            LauncherScreen(
                games = games,
                stores = stores,
                selectedId = launcherSelection,
                expandedGroups = expandedGroups,
                onSelect = { launcherSelection = it },
                onToggleGroup = { key, isOpen ->
                    if (isOpen) expandedGroups.remove(key) else if (key !in expandedGroups) expandedGroups.add(key)
                },
                collapsedPlatforms = settings.collapsedPlatforms,
                onSetPlatformCollapsed = { platform, collapsed -> settings.setPlatformCollapsed(platform, collapsed) },
                onOpen = {
                    launcherSelection = it
                    openId = it
                },
                themeLabel = launcherTheme.label,
                savedThemeId = settings.launcherTheme,
                onPreviewTheme = { previewTheme = it },
                onPickTheme = {
                    settings.saveLauncherTheme(it)
                    previewTheme = null
                },
            )
        }
    } else {
        // Keyed so tab and selection state start fresh for each game.
        key(open.id) {
            GuideTheme(open.palette) { GuideScreen(open, stores.getValue(open.id)) }
        }
    }
}
