package com.retroreadme.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retroreadme.core.Game
import com.retroreadme.core.ProgressStore
import android.view.KeyEvent as AndroidKeyEvent

private const val METER_SEGMENTS = 20
private const val GROUP_PREFIX = "group:"
private const val PLATFORM_PREFIX = "platform:"

/** One launcher row: a single guide, or a group of guides (e.g. a game's difficulties). */
private class Entry(val title: String, val games: List<Game>) {
    val isGroup get() = games.first().groupTitle != null
    val key get() = if (isGroup) GROUP_PREFIX + title else games.first().id
    val platform get() = games.first().platform
}

/** Games arrive sorted by platform; neighbours sharing a groupTitle fold into one entry. */
private fun entriesOf(games: List<Game>): List<Entry> {
    val out = mutableListOf<Entry>()
    for (g in games) {
        val last = out.lastOrNull()
        if (g.groupTitle != null && last != null && last.isGroup &&
            last.title == g.groupTitle && last.platform == g.platform
        ) {
            out[out.lastIndex] = Entry(last.title, last.games + g)
        } else {
            out += Entry(g.groupTitle ?: g.title, listOf(g))
        }
    }
    return out
}

/**
 * Game select. D-pad through the games on the left; the right side shows the highlighted one.
 * A (or a tap) opens a guide, or opens/closes a group's nested list. B on the list exits the app.
 */
@Composable
fun LauncherScreen(
    games: List<Game>,
    stores: Map<String, ProgressStore>,
    selectedId: String,
    expandedGroups: List<String>,
    onSelect: (String) -> Unit,
    /** Called with the group key and whether it is currently shown open. */
    onToggleGroup: (key: String, isOpen: Boolean) -> Unit,
    /** Platform names whose game lists are folded away. */
    collapsedPlatforms: Set<String>,
    onSetPlatformCollapsed: (platform: String, collapsed: Boolean) -> Unit,
    onOpen: (String) -> Unit,
    /** Label of the theme currently shown (including a live preview). */
    themeLabel: String,
    /** The saved theme's id; the menu marks it and returns to it on cancel. */
    savedThemeId: String,
    /** Live preview while moving through the menu; null clears it. */
    onPreviewTheme: (String?) -> Unit,
    onPickTheme: (String) -> Unit,
) {
    val detailScroll = rememberScrollState()
    var menuOpen by remember { mutableStateOf(false) }
    var returnFocusToButton by remember { mutableStateOf(false) }
    val themeButton = remember { FocusRequester() }
    LaunchedEffect(menuOpen) {
        if (!menuOpen && returnFocusToButton) {
            returnFocusToButton = false
            runCatching { themeButton.requestFocus() }
        }
    }
    fun closeMenu() {
        menuOpen = false
        returnFocusToButton = true
    }
    val entries = entriesOf(games)
    // Coming back from a guide inside a group: keep that group open so the row can take focus.
    val autoOpen = entries.filter { e -> e.isGroup && e.games.any { it.id == selectedId } }.map { it.key }
    // Same for a collapsed console: keep the selected guide's console open.
    val selectedPlatform = games.firstOrNull { it.id == selectedId }?.platform?.name
    fun platformOpen(name: String) = name !in collapsedPlatforms || name == selectedPlatform
    val platforms = entries.map { it.platform }.distinct()

    Box(Modifier.fillMaxSize()) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Palette.background)
            // Select opens the theme menu from anywhere on the launcher.
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown &&
                    event.nativeKeyEvent.keyCode == AndroidKeyEvent.KEYCODE_BUTTON_SELECT && !menuOpen
                ) {
                    menuOpen = true
                    true
                } else {
                    false
                }
            },
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Palette.bar)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Retro README", color = Palette.accent, fontFamily = Condensed, fontSize = 26.sp)
            Spacer(Modifier.weight(1f))
            ThemeButton(themeLabel, themeButton) { menuOpen = true }
        }

        val items = buildList {
            platforms.forEachIndexed { p, platform ->
                val inPlatform = entries.filter { it.platform == platform }
                val gameCount = inPlatform.sumOf { it.games.size }
                val pOpen = platformOpen(platform.name)
                add(
                    MasterItem(
                        PLATFORM_PREFIX + platform.name, platform.label,
                        if (gameCount == 1) "1 guide" else "$gameCount guides",
                        groupHeader = if (p == 0) "Games" else null,
                        expanded = pOpen,
                    )
                )
                if (!pOpen) return@forEachIndexed
                inPlatform.forEachIndexed { i, e ->
                    val lastInPlatform = i == inPlatform.lastIndex
                    if (!e.isGroup) {
                        val g = e.games.first()
                        add(
                            MasterItem(
                                g.id, g.title, progressLine(g, stores),
                                depth = 1, lastChild = lastInPlatform, star = starOf(listOf(g), stores),
                            )
                        )
                    } else {
                        val open = e.key in expandedGroups || e.key in autoOpen
                        add(
                            MasterItem(
                                e.key, e.title, e.games.joinToString(" · ") { it.variant ?: it.title },
                                depth = 1, lastChild = lastInPlatform, expanded = open,
                                // Silver once any difficulty is complete, gold once they all are.
                                star = starOf(e.games, stores),
                            )
                        )
                        if (open) {
                            e.games.forEachIndexed { j, g ->
                                add(
                                    MasterItem(
                                        g.id, g.variant ?: g.title, progressLine(g, stores),
                                        depth = 1, lastChild = j == e.games.lastIndex,
                                        outerLines = listOf(!lastInPlatform),
                                        star = starOf(listOf(g), stores),
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        Box(Modifier.weight(1f).fillMaxWidth()) {
            MasterDetail(
                items = items,
                selectedKey = selectedId,
                onSelect = onSelect,
                detailScroll = detailScroll,
                onDetailViewport = {},
                onActivate = { key ->
                    when {
                        key.startsWith(PLATFORM_PREFIX) -> {
                            val name = key.removePrefix(PLATFORM_PREFIX)
                            onSetPlatformCollapsed(name, platformOpen(name))
                        }
                        key.startsWith(GROUP_PREFIX) -> onToggleGroup(key, key in expandedGroups || key in autoOpen)
                        else -> onOpen(key)
                    }
                },
                footer = "D-pad moves. A opens a guide, or opens and closes a console or group.\nSelect changes the theme. B exits the app.",
            ) { key ->
                val group = entries.firstOrNull { it.isGroup && it.key == key }
                when {
                    key.startsWith(PLATFORM_PREFIX) -> {
                        val platform = platforms.first { it.name == key.removePrefix(PLATFORM_PREFIX) }
                        PlatformCard(platform.label, games.filter { it.platform == platform }, stores, onOpen)
                    }
                    group != null -> GroupCard(group, stores, onOpen)
                    else -> GameCard(games.first { it.id == key }, stores.getValue(key), onOpen)
                }
            }
        }
    }

    if (menuOpen) {
        ThemeMenu(
            currentId = savedThemeId,
            onPreview = { onPreviewTheme(it) },
            onPick = {
                onPickTheme(it)
                closeMenu()
            },
            onCancel = {
                onPreviewTheme(null)
                closeMenu()
            },
        )
    }
    }
}

/** Gold when every guide is complete, silver when only some are (difficulty groups), else none. */
private fun starOf(games: List<Game>, stores: Map<String, ProgressStore>): Star {
    val done = games.count { isComplete(it, stores) }
    return when {
        done == 0 -> Star.NONE
        done == games.size -> Star.GOLD
        else -> Star.SILVER
    }
}

/** Every checklist item done. Guides without a checklist never get a star. */
private fun isComplete(game: Game, stores: Map<String, ProgressStore>): Boolean {
    val ids = game.checklist.map { it.id }
    return ids.isNotEmpty() && stores.getValue(game.id).countDone(ids) == ids.size
}

private fun progressLine(game: Game, stores: Map<String, ProgressStore>): String? {
    val ids = game.checklist.map { it.id }
    return if (ids.isEmpty()) null else "${stores.getValue(game.id).countDone(ids)} of ${ids.size} checked"
}

@Composable
private fun ChecklistMeter(game: Game, progress: ProgressStore) {
    val ids = game.checklist.map { it.id }
    if (ids.isEmpty()) return
    val done = progress.countDone(ids)
    val lit = if (done == ids.size) METER_SEGMENTS else done * METER_SEGMENTS / ids.size
    val cellColor = Palette.accent
    Text("$done of ${ids.size} checked off", color = Palette.text, fontSize = 16.sp)
    EnergyCells(
        colors = List(METER_SEGMENTS) { cellColor },
        filled = List(METER_SEGMENTS) { it < lit },
        cellWidth = 16.dp, cellHeight = 26.dp,
    )
}

@Composable
private fun PlatformCard(label: String, games: List<Game>, stores: Map<String, ProgressStore>, onOpen: (String) -> Unit) {
    PageTitle(label, if (games.size == 1) "1 guide" else "${games.size} guides")
    Text("Press A on the list to fold this console away or open it again. Tap a guide here to open it.", color = Palette.muted, fontSize = 14.sp)
    games.forEach { g ->
        Panel(stripe = Palette.accent, onClick = { onOpen(g.id) }) {
            PanelHeading(g.title)
            ChecklistMeter(g, stores.getValue(g.id))
        }
    }
}

@Composable
private fun GroupCard(group: Entry, stores: Map<String, ProgressStore>, onOpen: (String) -> Unit) {
    PageTitle(group.title, group.platform.label)
    Text("Press A on the list to show the guides, or tap one here.", color = Palette.muted, fontSize = 14.sp)
    group.games.forEach { g ->
        Panel(stripe = Palette.accent, onClick = { onOpen(g.id) }) {
            PanelHeading(g.variant ?: g.title)
            ChecklistMeter(g, stores.getValue(g.id))
        }
    }
}

@Composable
private fun GameCard(game: Game, progress: ProgressStore, onOpen: (String) -> Unit) {
    PageTitle(game.title, game.platform.label)

    Panel(stripe = Palette.accent, onClick = { onOpen(game.id) }) {
        PanelHeading("Open guide")
        Text("Press A on the list, or tap here.", color = Palette.muted, fontSize = 14.sp)
    }

    if (game.checklist.isNotEmpty()) {
        Panel(stripe = Palette.secret) {
            PanelHeading("Checklist")
            ChecklistMeter(game, progress)
        }
    }

    if (game.about.isNotEmpty()) {
        Panel(stripe = Palette.line) {
            PanelHeading("About")
            Lines(game.about)
        }
    }
}
