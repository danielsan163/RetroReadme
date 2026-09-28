package com.retroreadme.games.wl4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retroreadme.core.ProgressStore
import com.retroreadme.ui.CheckBoxMark
import com.retroreadme.ui.Condensed
import com.retroreadme.ui.EnergyCells
import com.retroreadme.ui.LabeledLine
import com.retroreadme.ui.Lines
import com.retroreadme.ui.PageTitle
import com.retroreadme.ui.Palette
import com.retroreadme.ui.Panel
import com.retroreadme.ui.PanelHeading
import com.retroreadme.ui.SectionPanel
import com.retroreadme.ui.Tag
import com.retroreadme.ui.TagRow

const val WL4_OVERVIEW_KEY = "overview"

fun levelCells(level: Wl4Level, mode: Mode, progress: ProgressStore): Pair<List<Color>, List<Boolean>> {
    val items = level.items(mode)
    return items.map { it.color } to items.map { progress.isDone(it.id) }
}

private fun Wl4Item.title() = when (kind) {
    ItemKind.JEWEL -> "Jewel piece $number"
    ItemKind.CD -> "CD"
    ItemKind.KEYZER -> "Keyzer"
}

@Composable
private fun HardNote(mode: Mode) {
    if (mode == Mode.HARD) {
        Text(
            "Hard locations come from one walkthrough. Worth confirming on the Nova.",
            color = Palette.warning, fontSize = 14.sp,
        )
    }
}

@Composable
fun ItemPanel(item: Wl4Item, done: Boolean, onToggle: () -> Unit) {
    Panel(stripe = item.color, onClick = onToggle) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(item.title(), color = Palette.text, fontFamily = Condensed, fontSize = 21.sp)
                Text(
                    if (done) "Collected" else "Not collected yet",
                    color = if (done) Palette.accent else Palette.muted, fontSize = 13.sp,
                )
            }
            CheckBoxMark(done)
        }
        Text(item.where, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
    }
}

@Composable
fun LevelDetail(level: Wl4Level, mode: Mode, progress: ProgressStore) {
    val d = level.data(mode)
    PageTitle(level.name, "${level.passage.label} · ${mode.label}")
    HardNote(mode)
    // Panels follow the route through the level, with the frog switch in its place.
    val items = level.items(mode).associateBy { it.id.substringAfterLast('_') }
    var switchShown = false
    d.steps.forEach { token ->
        if (token == "switch") {
            switchShown = true
            SwitchPanel(d)
        } else {
            val item = items[token] ?: return@forEach
            ItemPanel(item, progress.isDone(item.id)) { progress.toggle(item.id) }
        }
    }
    // Anything the order doesn't mention (shouldn't happen) still gets shown.
    val listed = d.steps.toSet()
    items.filterKeys { it !in listed }.values.forEach { item ->
        ItemPanel(item, progress.isDone(item.id)) { progress.toggle(item.id) }
    }
    if (!switchShown) SwitchPanel(d)
    if (level.notes.isNotEmpty()) {
        Panel(stripe = Palette.line) {
            PanelHeading("Worth knowing")
            Lines(level.notes)
        }
    }
}

@Composable
private fun SwitchPanel(d: ModeData) {
    Panel(stripe = Palette.warning) {
        PanelHeading("Frog switch", Palette.warning)
        LabeledLine("Timer", d.escapeTime, Palette.warning)
        Text(
            "Hitting it starts the escape. Everything below is collected on the way back.",
            color = Palette.muted, fontSize = 14.sp,
        )
        d.escapeTip?.let { Text(it, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp) }
    }
}

@Composable
fun CdDetail(level: Wl4Level, mode: Mode, progress: ProgressStore) {
    val item = level.items(mode).first { it.kind == ItemKind.CD }
    PageTitle(level.name, "${level.passage.label} · ${mode.label}")
    HardNote(mode)
    ItemPanel(item, progress.isDone(item.id)) { progress.toggle(item.id) }
    Text(
        "The level's jewel pieces and Keyzer are on its page in the Passages tab.",
        color = Palette.muted, fontSize = 14.sp,
    )
}

@Composable
fun Wl4OverviewDetail(mode: Mode, progress: ProgressStore) {
    val all = Wl4Levels.allItems(mode)
    fun count(kind: ItemKind) = all.filter { it.kind == kind }.map { it.id }

    PageTitle("Collection", "${mode.label} mode. Press A on an item inside a level to check it off.")
    HardNote(mode)
    Panel(stripe = Palette.accent) {
        TagRow {
            ItemKind.entries.forEach { kind ->
                val ids = count(kind)
                Tag("${kind.label}s ${progress.countDone(ids)} of ${ids.size}", Palette.accent)
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Passage.entries.forEach { passage ->
                val items = Wl4Levels.all.filter { it.passage == passage }.flatMap { it.items(mode) }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.width(160.dp)) {
                        Text(passage.label, color = Palette.text, fontFamily = Condensed, fontSize = 17.sp)
                        Text("${progress.countDone(items.map { it.id })} of ${items.size}", color = Palette.muted, fontSize = 13.sp)
                    }
                    EnergyCells(
                        colors = items.map { it.color },
                        filled = items.map { progress.isDone(it.id) },
                        cellWidth = 8.dp, cellHeight = 22.dp,
                    )
                }
            }
        }
    }
    var armed by remember { mutableStateOf(false) }
    Panel(
        stripe = Palette.warning,
        onClick = {
            if (armed) { progress.reset(); armed = false } else armed = true
        },
    ) {
        Text(
            if (armed) "Press again to clear every checkmark (${mode.label} only)" else "Clear checklist",
            color = Palette.warning, fontFamily = Condensed, fontSize = 18.sp,
        )
    }
}

@Composable
fun BossDetail(boss: Boss, mode: Mode) {
    PageTitle(boss.name, boss.passage.label)
    TagRow {
        Tag("Time ${if (mode == Mode.NORMAL) boss.timeNormal else boss.timeHard}", Palette.warning)
        boss.weakTo?.let { Tag("Weak to $it", Palette.accent) }
        boss.treasure?.let { Tag("Treasure: $it", Palette.secret) }
    }
    Panel(stripe = Palette.accent) {
        PanelHeading("Strategy")
        Lines(boss.strategy)
    }
    if (boss.weakTo != null) {
        Text(
            "Three treasure chests start disappearing when the timer reaches 1:00.",
            color = Palette.muted, fontSize = 14.sp,
        )
    }
}

@Composable
fun Wl4PageDetail(page: Wl4Page) {
    PageTitle(page.title, page.subtitle)
    page.sections.forEach { SectionPanel(it) }
}
