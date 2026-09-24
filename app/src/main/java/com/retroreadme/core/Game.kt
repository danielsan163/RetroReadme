package com.retroreadme.core

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/**
 * A game the launcher can open. Each game supplies its own tab list, so games with
 * nothing in common (a boss-weakness chain vs. a world map) can still share the app shell.
 */
class Game(
    /**
     * Stable id. It namespaces this game's checklist progress (SharedPreferences file
     * "progress_<id>"), so never change it once people have progress saved.
     */
    val id: String,
    val title: String,
    /** Which launcher subheading this guide sits under. A game on two systems gets a guide per system. */
    val platform: Platform,
    /** Short label at the left end of the tab bar, e.g. "X2". */
    val badge: String,
    /** Colors for this game's screens. The launcher uses its own. */
    val palette: GuidePalette,
    /** A few lines shown on the launcher's detail pane. */
    val about: List<String>,
    val tabs: List<GameTab>,
    /** Everything that counts toward 100%. Empty = no checklist and no celebration. */
    val checklist: List<CheckItem>,
    val celebrationTitle: String,
    val celebrationMessage: String,
    /**
     * Guides sharing a groupTitle show on the launcher as one row that expands to list them
     * (e.g. Wario Land 4 → Normal, Hard). Each keeps its own id, checklist and progress.
     */
    val groupTitle: String? = null,
    /** This guide's name inside its group, e.g. "Hard". */
    val variant: String? = null,
)

/** One tab across the top of a game's guide. */
class GameTab(
    val title: String,
    /** Row selected the first time this tab is shown. */
    val initialKey: String,
    val content: @Composable (TabContext) -> Unit,
)

/** A checklist entry: its id (the SharedPreferences key) and its color in progress meters. */
data class CheckItem(val id: String, val color: Color)

/** What the shared guide screen hands each tab: selection, detail-pane scroll, and progress. */
class TabContext(
    val selectedKey: String,
    val onSelect: (String) -> Unit,
    val detailScroll: ScrollState,
    val onDetailViewport: (Int) -> Unit,
    val progress: ProgressStore,
)
