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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

data class MenuOption(val id: String, val label: String, val detail: String? = null)

/**
 * A drop-down list drawn in the app's own window (so A/B keep working), with focus kept
 * inside it. A picks, B or a tap outside cancels. Opens with [currentId] highlighted.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListMenu(
    title: String,
    options: List<MenuOption>,
    currentId: String?,
    onPick: (String) -> Unit,
    onCancel: () -> Unit,
    alignment: Alignment = Alignment.TopStart,
    offsetTop: Int = 8,
) {
    val requesters = remember(options) { options.associate { it.id to FocusRequester() } }
    LaunchedEffect(Unit) {
        withFrameNanos { }
        runCatching { requesters.getValue(currentId?.takeIf { it in requesters } ?: options.first().id).requestFocus() }
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
                .align(alignment)
                .padding(top = offsetTop.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
                .width(300.dp)
                .clip(PanelShape)
                .background(Palette.panel)
                .border(1.dp, Palette.line, PanelShape)
                .focusProperties { exit = { FocusRequester.Cancel } }
                .focusGroup()
                .padding(6.dp),
        ) {
            Text(
                title, color = Palette.accent, fontFamily = Condensed, fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            )
            // Scrolls when the screen is too short for every option (e.g. 16:9 devices);
            // the focused row is scrolled into view as you move.
            Column(
                Modifier
                    .weight(1f, fill = false)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                options.forEach { option ->
                    MenuRow(option, option.id == currentId, requesters.getValue(option.id)) { onPick(option.id) }
                }
            }
        }
    }
}

@Composable
private fun MenuRow(option: MenuOption, isCurrent: Boolean, requester: FocusRequester, onPick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    Row(
        Modifier
            .fillMaxWidth()
            .clip(PanelShape)
            .background(if (focused) Palette.selected else Color.Transparent)
            .focusRequester(requester)
            .onFocusChanged { focused = it.isFocused }
            .clickable(onClick = onPick)
            .padding(horizontal = 10.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            option.label, color = if (isCurrent) Palette.accent else Palette.text,
            fontFamily = Condensed, fontSize = 17.sp, modifier = Modifier.weight(1f),
        )
        option.detail?.let { Text(it, color = Palette.muted, fontSize = 13.sp) }
    }
}
