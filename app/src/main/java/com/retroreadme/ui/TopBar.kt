package com.retroreadme.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Tab bar for a game: its badge, then the game's own tabs between L1 and R1 hints. */
@Composable
fun TopBar(badge: String, titles: List<String>, current: Int, onTab: (Int) -> Unit) {
    val listState = rememberLazyListState()
    // Keep the selected tab on screen, with the previous one peeking in for context.
    LaunchedEffect(current) {
        listState.animateScrollToItem((current - 1).coerceAtLeast(0))
    }

    Row(
        Modifier
            .fillMaxWidth()
            .background(Palette.bar)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            badge, color = Palette.accent, fontFamily = Condensed, fontSize = 26.sp,
            modifier = Modifier.padding(end = 10.dp),
        )
        ShoulderHint("L1")
        LazyRow(
            state = listState,
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            itemsIndexed(titles) { i, title ->
                val isCurrent = i == current
                Text(
                    title,
                    color = if (isCurrent) Palette.background else Palette.text,
                    fontFamily = Condensed, fontSize = 17.sp, maxLines = 1,
                    modifier = Modifier
                        .clip(PanelShape)
                        .background(if (isCurrent) Palette.accent else Color.Transparent)
                        // Tabs are touch targets only; the shoulder buttons handle them on the controller.
                        .focusProperties { canFocus = false }
                        .clickable { onTab(i) }
                        .padding(horizontal = 14.dp, vertical = 7.dp),
                )
            }
        }
        ShoulderHint("R1")
    }
}

@Composable
private fun ShoulderHint(label: String) {
    Text(
        label, color = Palette.muted, fontFamily = Condensed, fontSize = 13.sp, textAlign = TextAlign.Center,
        modifier = Modifier.width(26.dp),
    )
}
