package com.retroreadme.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import com.retroreadme.core.Game
import com.retroreadme.core.ProgressStore
import com.retroreadme.core.TabContext
import kotlinx.coroutines.launch
import android.view.KeyEvent as AndroidKeyEvent

/**
 * One game's guide: its tabs across the top, the selected tab's content below.
 * Shoulder buttons are handled here so they work the same in every game.
 */
@Composable
fun GuideScreen(game: Game, progress: ProgressStore) {
    val tabs = game.tabs
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    val scrolls = remember { tabs.map { ScrollState(0) } }
    val selected = remember {
        mutableStateMapOf<Int, String>().apply { tabs.forEachIndexed { i, t -> put(i, t.initialKey) } }
    }
    var viewportPx by remember { mutableIntStateOf(800) }
    val scope = rememberCoroutineScope()
    val tab = tabs[tabIndex]
    val scroll = scrolls[tabIndex]

    val allIds = remember { game.checklist.map { it.id } }
    val complete = allIds.isNotEmpty() && progress.countDone(allIds) == allIds.size
    // Starts "already celebrated" if the guide opens at 100%, so it only fires on the transition.
    var celebrated by rememberSaveable { mutableStateOf(complete) }
    var showCelebration by remember { mutableStateOf(false) }

    LaunchedEffect(complete) {
        if (complete && !celebrated) {
            showCelebration = true
            celebrated = true
        } else if (!complete) {
            celebrated = false
        }
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Palette.background)
                .onPreviewKeyEvent { event ->
                    if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                    when (event.nativeKeyEvent.keyCode) {
                        AndroidKeyEvent.KEYCODE_BUTTON_L1 -> { tabIndex = (tabIndex + tabs.size - 1) % tabs.size; true }
                        AndroidKeyEvent.KEYCODE_BUTTON_R1 -> { tabIndex = (tabIndex + 1) % tabs.size; true }
                        AndroidKeyEvent.KEYCODE_BUTTON_L2 -> { scope.launch { scroll.animateScrollBy(-viewportPx * 0.8f) }; true }
                        AndroidKeyEvent.KEYCODE_BUTTON_R2 -> { scope.launch { scroll.animateScrollBy(viewportPx * 0.8f) }; true }
                        else -> false
                    }
                },
        ) {
            TopBar(game.badge, tabs.map { it.title }, tabIndex) { tabIndex = it }

            Box(Modifier.weight(1f).fillMaxWidth()) {
                key(tabIndex) {
                    val i = tabIndex
                    tab.content(
                        TabContext(
                            selectedKey = selected.getValue(i),
                            onSelect = { selected[i] = it },
                            detailScroll = scroll,
                            onDetailViewport = { viewportPx = it },
                            progress = progress,
                        )
                    )
                }
            }
        }

        if (showCelebration) {
            CelebrationOverlay(
                cells = game.checklist.map { it.color },
                title = game.celebrationTitle,
                message = game.celebrationMessage,
                onDismiss = { showCelebration = false },
            )
        }
    }
}
