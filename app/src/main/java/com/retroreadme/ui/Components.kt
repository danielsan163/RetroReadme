package com.retroreadme.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retroreadme.core.Section
import com.retroreadme.core.Tone

/** Chamfered corners, like the frames of the game's menus. */
val PanelShape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)

/**
 * A detail-pane panel. Every panel is focusable so the D-pad can walk down the page
 * (and the scroll follows). A colored stripe on the left says what kind of content it is.
 */
@Composable
fun Panel(
    stripe: Color = Palette.line,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxWidth()
            .clip(PanelShape)
            .background(Palette.panel)
            .drawBehind { drawRect(stripe, size = Size(4.dp.toPx(), size.height)) }
            .then(if (focused) Modifier.border(2.dp, Palette.accent, PanelShape) else Modifier)
            .onFocusChanged { focused = it.isFocused }
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier.focusable())
            .padding(start = 18.dp, end = 16.dp, top = 14.dp, bottom = 14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = content,
    )
}

@Composable
fun PanelHeading(text: String, color: Color = Palette.accent) {
    Text(text, color = color, fontFamily = Condensed, fontSize = 17.sp)
}

@Composable
fun PageTitle(title: String, subtitle: String?) {
    Column(Modifier.padding(bottom = 2.dp)) {
        Text(title, color = Palette.text, fontFamily = Condensed, fontSize = 30.sp, lineHeight = 32.sp)
        if (subtitle != null) Text(subtitle, color = Palette.muted, fontSize = 15.sp)
    }
}

@Composable
fun Lines(lines: List<String>, numbered: Boolean = false, marker: Color = Palette.accent) {
    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
        lines.forEachIndexed { i, line ->
            Row {
                Text(
                    if (numbered) "${i + 1}" else "›",
                    color = marker, fontFamily = Condensed, fontSize = 16.sp,
                    modifier = Modifier.width(22.dp),
                )
                Text(line, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun LabeledLine(label: String, text: String, color: Color) {
    Row {
        Text(label, color = color, fontFamily = Condensed, fontSize = 15.sp, modifier = Modifier.width(76.dp))
        Text(text, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp, modifier = Modifier.weight(1f))
    }
}

@Composable
fun Tag(text: String, color: Color) {
    Text(
        text, color = color, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .border(1.dp, color.copy(alpha = 0.7f), CutCornerShape(topStart = 6.dp, bottomEnd = 6.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagRow(content: @Composable () -> Unit) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        content()
    }
}

/** Segmented meter in the style of X's life bar: one cell per item. */
@Composable
fun EnergyCells(colors: List<Color>, filled: List<Boolean>, cellWidth: Dp = 6.dp, cellHeight: Dp = 14.dp) {
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        colors.forEachIndexed { i, c ->
            Box(Modifier.size(cellWidth, cellHeight).background(if (filled[i]) c else c.copy(alpha = 0.18f)))
        }
    }
}

@Composable
fun CheckBoxMark(done: Boolean) {
    Box(
        Modifier
            .size(28.dp)
            .border(2.dp, if (done) Palette.accent else Palette.muted, RoundedCornerShape(4.dp))
            .background(if (done) Palette.accent.copy(alpha = 0.2f) else Color.Transparent),
        contentAlignment = Alignment.Center,
    ) {
        if (done) Text("✓", color = Palette.accent, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

/** A headed block of lines, colored by its tone. */
@Composable
fun SectionPanel(section: Section) {
    val color = when (section.tone) {
        Tone.NORMAL -> Palette.accent
        Tone.WARNING -> Palette.warning
        Tone.SECRET -> Palette.secret
    }
    Panel(stripe = color) {
        section.heading?.let { PanelHeading(it, color) }
        Lines(section.lines, numbered = section.numbered, marker = color)
    }
}
