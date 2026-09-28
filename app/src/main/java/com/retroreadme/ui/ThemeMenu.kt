package com.retroreadme.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** The "Theme: SNES ▾" button at the right of the launcher's top bar. */
@Composable
fun ThemeButton(label: String, requester: FocusRequester, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    Row(
        Modifier
            .clip(PanelShape)
            .border(if (focused) 2.dp else 1.dp, if (focused) Palette.accent else Palette.line, PanelShape)
            .focusRequester(requester)
            .onFocusChanged { focused = it.isFocused }
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("Theme", color = Palette.muted, fontSize = 13.sp)
        Spacer(Modifier.width(8.dp))
        Text(label, color = Palette.text, fontFamily = Condensed, fontSize = 17.sp)
        Spacer(Modifier.width(6.dp))
        Text("\u25BE", color = Palette.muted, fontSize = 16.sp)
    }
}

/**
 * Drop-down list of themes, drawn in the launcher's own window so the controller remapping
 * (A = select, B = back) keeps working. Moving through it previews each theme live;
 * A keeps the highlighted one, B puts the saved one back.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ThemeMenu(
    currentId: String,
    onPreview: (String) -> Unit,
    onPick: (String) -> Unit,
    onCancel: () -> Unit,
) {
    val requesters = remember { LauncherTheme.entries.associate { it.name to FocusRequester() } }
    LaunchedEffect(Unit) {
        withFrameNanos { }
        runCatching { requesters.getValue(LauncherTheme.byId(currentId).name).requestFocus() }
    }
    BackHandler { onCancel() }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.45f))
            .focusProperties { canFocus = false }
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { onCancel() },
    ) {
        Column(
            Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp, end = 12.dp, bottom = 12.dp)
                .width(230.dp)
                .clip(PanelShape)
                .background(Palette.panel)
                .border(1.dp, Palette.line, PanelShape)
                // Keep D-pad focus inside the menu while it's open.
                .focusProperties { exit = { FocusRequester.Cancel } }
                .focusGroup()
                .padding(6.dp)
                // Scrolls on short (e.g. 16:9) screens; the focused theme is kept in view.
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            LauncherTheme.entries.forEach { theme ->
                ThemeRow(
                    theme = theme,
                    isCurrent = theme.name == LauncherTheme.byId(currentId).name,
                    requester = requesters.getValue(theme.name),
                    onFocus = { onPreview(theme.name) },
                    onPick = { onPick(theme.name) },
                )
            }
        }
    }
}

@Composable
private fun ThemeRow(
    theme: LauncherTheme,
    isCurrent: Boolean,
    requester: FocusRequester,
    onFocus: () -> Unit,
    onPick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    Row(
        Modifier
            .fillMaxWidth()
            .clip(PanelShape)
            .background(if (focused) Palette.selected else Color.Transparent)
            .focusRequester(requester)
            .onFocusChanged {
                focused = it.isFocused
                if (it.isFocused) onFocus()
            }
            .clickable(onClick = onPick)
            .padding(horizontal = 10.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // A little strip of the theme's own colors, so you can tell them apart before previewing.
        val p = theme.palette
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            listOf(p.bar, p.selected, p.accent, p.secret).forEach { c ->
                Box(Modifier.size(width = 9.dp, height = 18.dp).background(c))
            }
        }
        Spacer(Modifier.width(12.dp))
        Text(theme.label, color = Palette.text, fontFamily = Condensed, fontSize = 17.sp, modifier = Modifier.weight(1f))
        if (isCurrent) Text("\u2713", color = Palette.accent, fontSize = 16.sp)
    }
}
