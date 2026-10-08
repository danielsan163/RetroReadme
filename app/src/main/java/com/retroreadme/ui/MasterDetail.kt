package com.retroreadme.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val GUIDE_FOOTER =
    "D-pad moves, A selects or checks off, B goes back.\nL1 and R1 switch tabs, L2 and R2 page the right side."

/** Completion star on a launcher row: none yet, some difficulties done (silver), or all done (gold). */
enum class Star { NONE, SILVER, GOLD }

private val STAR_SLOT = 20.dp

data class MasterItem(
    val key: String,
    val title: String,
    val subtitle: String? = null,
    /** Optional segmented meter shown under the title (e.g. items collected in a stage). */
    val cells: Pair<List<Color>, List<Boolean>>? = null,
    /** Group label drawn above this row (e.g. boss categories). */
    val groupHeader: String? = null,
    /** Smaller label drawn above this row, below any group label (e.g. platforms under "Games"). */
    val subHeader: String? = null,
    /** 0 for a normal row, 1 for a child row drawn indented under its parent with tree lines. */
    val depth: Int = 0,
    /** For child rows: the last child, so its tree line stops halfway down. */
    val lastChild: Boolean = false,
    /**
     * For rows nested more than one level deep: one entry per outer level, true where that
     * level's vertical line carries on past this row (its parent wasn't the last child).
     */
    val outerLines: List<Boolean> = emptyList(),
    /** For rows that open and close a nested list: true when open. Null for ordinary rows. */
    val expanded: Boolean? = null,
    /**
     * Launcher rows: reserves a slot after the title for a completion star and keeps the title on
     * one line (scrolling when selected if it's too long). Null = ordinary row, no slot.
     */
    val star: Star? = null,
)

/**
 * Left: list you move through with the D-pad (moving = selecting, so the right side updates live).
 * Right: scrollable detail. D-pad right enters it, D-pad left or B returns to the selected row.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MasterDetail(
    items: List<MasterItem>,
    selectedKey: String,
    onSelect: (String) -> Unit,
    detailScroll: ScrollState,
    onDetailViewport: (Int) -> Unit,
    /** Called when A is pressed (or a row is tapped). Null = A just selects, as in the guide tabs. */
    onActivate: ((String) -> Unit)? = null,
    /** Control hint under the list. Null hides it. */
    footer: String? = GUIDE_FOOTER,
    /**
     * Change this value to move focus (and scroll) to the selected row, e.g. after a jump menu
     * changes the selection. Null does nothing.
     */
    focusRequestKey: Any? = null,
    detail: @Composable ColumnScope.(String) -> Unit,
) {
    val keys = items.map { it.key }
    val requesters = remember(keys) { keys.associateWith { FocusRequester() } }
    var detailHasFocus by remember { mutableStateOf(false) }

    // Land focus on the selected row whenever this tab appears.
    LaunchedEffect(Unit) {
        withFrameNanos { }
        runCatching { requesters[selectedKey]?.requestFocus() }
    }
    LaunchedEffect(focusRequestKey) {
        if (focusRequestKey != null) {
            withFrameNanos { }
            runCatching { requesters[selectedKey]?.requestFocus() }
        }
    }
    LaunchedEffect(selectedKey) { detailScroll.scrollTo(0) }
    BackHandler(enabled = detailHasFocus) {
        runCatching { requesters[selectedKey]?.requestFocus() }
    }

    Row(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .weight(0.36f)
                .fillMaxHeight()
                .background(Palette.bar)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 10.dp, vertical = 10.dp),
        ) {
            items.forEach { item ->
                if (item.groupHeader != null) {
                    Column(Modifier.padding(start = 4.dp, end = 4.dp, top = 18.dp, bottom = 8.dp)) {
                        Text(
                            item.groupHeader,
                            color = Palette.accent,
                            fontFamily = Condensed,
                            fontSize = 18.sp,
                            letterSpacing = 0.5.sp,
                        )
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp)
                                .height(2.dp)
                                .background(Palette.accent.copy(alpha = 0.4f)),
                        )
                    }
                }
                if (item.subHeader != null) {
                    Text(
                        item.subHeader,
                        color = Palette.muted,
                        fontFamily = Condensed,
                        fontSize = 15.sp,
                        letterSpacing = 0.5.sp,
                        modifier = Modifier.padding(
                            start = 6.dp, end = 4.dp,
                            top = if (item.groupHeader != null) 2.dp else 14.dp,
                            bottom = 4.dp,
                        ),
                    )
                }
                MasterRow(
                    item = item,
                    selected = item.key == selectedKey,
                    requester = requesters.getValue(item.key),
                    onSelect = { onSelect(item.key) },
                    onActivate = onActivate?.let { act -> { act(item.key) } },
                )
            }
            if (footer != null) {
                Spacer(Modifier.height(16.dp))
                Text(
                    footer,
                    color = Palette.muted.copy(alpha = 0.7f), fontSize = 12.sp, lineHeight = 16.sp,
                    modifier = Modifier.padding(horizontal = 12.dp),
                )
            }
        }

        Column(
            Modifier
                .weight(0.64f)
                .fillMaxHeight()
                .background(Palette.background)
                .onSizeChanged { onDetailViewport(it.height) }
                .onFocusChanged { detailHasFocus = it.hasFocus }
                .focusProperties {
                    exit = { direction ->
                        if (direction == FocusDirection.Left) requesters[selectedKey] ?: FocusRequester.Default
                        else FocusRequester.Default
                    }
                }
                .focusGroup()
                .verticalScroll(detailScroll)
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            detail(selectedKey)
            Spacer(Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MasterRow(
    item: MasterItem,
    selected: Boolean,
    requester: FocusRequester,
    onSelect: () -> Unit,
    onActivate: (() -> Unit)?,
) {
    var focused by remember { mutableStateOf(false) }
    // The outer row carries the vertical spacing so the tree line runs unbroken between child rows.
    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
    ) {
        item.outerLines.forEach { carriesOn -> TreeGutter(last = false, tick = false, line = carriesOn) }
        if (item.depth > 0) TreeGutter(item.lastChild)
        Row(
            Modifier
                .weight(1f)
                .padding(vertical = 2.dp)
                .clip(PanelShape)
                .background(if (selected) Palette.selected else Color.Transparent)
                .then(if (focused) Modifier.border(2.dp, Palette.accent, PanelShape) else Modifier)
                .focusRequester(requester)
                .onFocusChanged {
                    focused = it.isFocused
                    if (it.isFocused) onSelect()
                }
                .clickable {
                    onSelect()
                    onActivate?.invoke()
                }
                .padding(horizontal = 12.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                if (item.star == null) {
                    Text(item.title, color = Palette.text, fontSize = 17.sp, fontFamily = Condensed)
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.weight(1f)) {
                            if (selected) {
                                Text(
                                    item.title, color = Palette.text, fontSize = 17.sp, fontFamily = Condensed,
                                    maxLines = 1, softWrap = false,
                                    modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE, initialDelayMillis = 800),
                                )
                            } else {
                                Text(
                                    item.title, color = Palette.text, fontSize = 17.sp, fontFamily = Condensed,
                                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                        Box(Modifier.padding(start = 6.dp).size(STAR_SLOT), contentAlignment = Alignment.Center) {
                            if (item.star != Star.NONE) PixelStar(STAR_SLOT, silver = item.star == Star.SILVER)
                        }
                    }
                }
                if (item.subtitle != null) {
                    Text(item.subtitle, color = if (selected) Palette.text.copy(alpha = 0.75f) else Palette.muted, fontSize = 13.sp)
                }
            }
            item.cells?.let { (colors, filled) ->
                Spacer(Modifier.width(8.dp))
                EnergyCells(colors, filled)
            }
            item.expanded?.let { open ->
                Spacer(Modifier.width(8.dp))
                Text(if (open) "\u25BE" else "\u25B8", color = Palette.muted, fontFamily = Condensed, fontSize = 20.sp)
            }
        }
    }
}

/** Tree connector for a child row: a vertical line down from the parent and a tick into the row. */
@Composable
private fun TreeGutter(last: Boolean, tick: Boolean = true, line: Boolean = true) {
    val lineColor = Palette.muted.copy(alpha = 0.5f)
    Box(
        Modifier
            .width(26.dp)
            .fillMaxHeight()
            .drawBehind {
                val stroke = 2.dp.toPx()
                val x = 12.dp.toPx()
                val midY = size.height / 2
                if (line) drawLine(lineColor, Offset(x, 0f), Offset(x, if (last && tick) midY else size.height), stroke)
                if (tick) drawLine(lineColor, Offset(x, midY), Offset(size.width, midY), stroke)
            },
    )
}
