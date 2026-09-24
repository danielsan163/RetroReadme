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
import com.retroreadme.games.GameRegistry

/**
 * Top level: the launcher, or the open game's guide.
 *
 * B handling, most specific first: the celebration overlay, then the detail pane
 * (back to the list), then this handler (back to the launcher). On the launcher's
 * list nothing handles B, so it exits the app as usual.
 */
@Composable
fun RetroReadmeApp(stores: Map<String, ProgressStore>) {
    val games = GameRegistry.games
    var openId by rememberSaveable { mutableStateOf<String?>(null) }
    var launcherSelection by rememberSaveable { mutableStateOf(games.first().id) }
    // Which launcher groups are open. Kept here so it survives opening and closing a guide.
    val expandedGroups = remember { mutableStateListOf<String>() }
    val open = games.firstOrNull { it.id == openId }

    BackHandler(enabled = open != null) { openId = null }

    if (open == null) {
        GuideTheme(LauncherPalette) {
            LauncherScreen(
                games = games,
                stores = stores,
                selectedId = launcherSelection,
                expandedGroups = expandedGroups,
                onSelect = { launcherSelection = it },
                onToggleGroup = { key, isOpen ->
                    if (isOpen) expandedGroups.remove(key) else if (key !in expandedGroups) expandedGroups.add(key)
                },
                onOpen = {
                    launcherSelection = it
                    openId = it
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
