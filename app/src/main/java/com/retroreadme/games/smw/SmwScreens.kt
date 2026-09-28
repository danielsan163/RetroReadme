package com.retroreadme.games.smw

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

const val SMW_OVERVIEW_KEY = "overview"

/** Meter cells for a level row: one per exit, yellow for normal, red for secret. */
fun levelCells(level: Level, progress: ProgressStore): Pair<List<Color>, List<Boolean>> =
    level.exits.map { it.kind.color() } to level.exits.map { progress.isDone(it.id) }

/** Short row subtitle: the level type unless it's a plain level, plus a note if it has a secret exit. */
fun levelSubtitle(level: Level): String? {
    val parts = listOfNotNull(
        level.type.label.takeIf { level.type != LevelType.LEVEL },
        "Secret exit".takeIf { level.exits.any { it.kind == ExitKind.SECRET } },
        "Not counted".takeIf { level.exits.isEmpty() },
    )
    return parts.joinToString(" · ").ifEmpty { null }
}

// ---------------------------------------------------------------- Exits

@Composable
fun ExitPanel(exit: Exit, done: Boolean, onToggle: () -> Unit) {
    val color = exit.kind.color()
    Panel(stripe = color, onClick = onToggle) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(exit.kind.label, color = Palette.text, fontFamily = Condensed, fontSize = 21.sp)
                Text(
                    if (done) "Cleared" else "Not cleared yet",
                    color = if (done) Palette.accent else Palette.muted, fontSize = 13.sp,
                )
            }
            CheckBoxMark(done)
        }
        LabeledLine("Leads to", exit.info.leadsTo, color)
        if (exit.info.needs.isNotEmpty()) {
            TagRow {
                exit.info.needs.forEach { need ->
                    Tag(if (need.startsWith("or ")) need else "Needs $need", Palette.warning)
                }
            }
        }
        Lines(exit.info.steps, numbered = exit.info.steps.size > 1, marker = color)
        if (exit.info.confirm) {
            Text(
                "Only one guide describes this route in detail. Worth confirming on the Nova.",
                color = Palette.warning, fontSize = 14.sp,
            )
        }
    }
}

@Composable
fun LevelDetail(level: Level, progress: ProgressStore) {
    PageTitle(level.name, "${level.world.label} · ${level.type.label}")
    if (level.exits.isEmpty()) {
        Panel(stripe = Palette.line) {
            Text("No exit here counts toward the 96.", color = Palette.muted, fontSize = 15.sp)
        }
    }
    level.exits.forEach { exit ->
        ExitPanel(exit, progress.isDone(exit.id)) { progress.toggle(exit.id) }
    }
    if (level.boss.isNotEmpty()) {
        Panel(stripe = Palette.warning) {
            PanelHeading("Boss", Palette.warning)
            Lines(level.boss, marker = Palette.warning)
        }
    }
    if (level.notes.isNotEmpty()) {
        Panel(stripe = Palette.line) {
            PanelHeading("Worth knowing")
            Lines(level.notes)
        }
    }
}

@Composable
fun SecretExitDetail(level: Level, exit: Exit, progress: ProgressStore) {
    PageTitle(level.name, "${level.world.label} · ${level.type.label}")
    ExitPanel(exit, progress.isDone(exit.id)) { progress.toggle(exit.id) }
    Text(
        "The level's full page, with its normal exit, is in the Worlds tab.",
        color = Palette.muted, fontSize = 14.sp,
    )
}

// ---------------------------------------------------------------- Progress overview

@Composable
fun SmwOverviewDetail(progress: ProgressStore) {
    val all = SmwLevels.allExits
    val secretIds = all.filter { it.kind == ExitKind.SECRET }.map { it.id }
    val normalIds = all.filter { it.kind == ExitKind.NORMAL }.map { it.id }

    PageTitle("All 96 exits", "Press A on an exit inside a level to check it off.")
    Panel(stripe = Palette.accent) {
        Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
            Tag("Normal ${progress.countDone(normalIds)} of ${normalIds.size}", SmwColors.NormalExit)
            Tag("Secret ${progress.countDone(secretIds)} of ${secretIds.size}", SmwColors.SecretExit)
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            World.entries.forEach { world ->
                val exits = SmwLevels.all.filter { it.world == world }.flatMap { it.exits }
                val done = progress.countDone(exits.map { it.id })
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.width(160.dp)) {
                        Text(world.label, color = Palette.text, fontFamily = Condensed, fontSize = 17.sp)
                        Text("$done of ${exits.size}", color = Palette.muted, fontSize = 13.sp)
                    }
                    EnergyCells(
                        colors = exits.map { it.kind.color() },
                        filled = exits.map { progress.isDone(it.id) },
                        cellWidth = 10.dp, cellHeight = 22.dp,
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
            if (armed) "Press again to clear every checkmark" else "Clear checklist",
            color = Palette.warning, fontFamily = Condensed, fontSize = 18.sp,
        )
    }
}

// ---------------------------------------------------------------- Palaces and pages

@Composable
fun PalaceDetail(palace: Palace, progress: ProgressStore) {
    PageTitle(palace.name, "Opened by: ${palace.openedBy}")
    SmwLevels.byId[palace.levelId]?.exits?.forEach { exit ->
        ExitPanel(exit, progress.isDone(exit.id)) { progress.toggle(exit.id) }
    }
    palace.sections.forEach { SectionPanel(it) }
}

@Composable
fun PageDetail(page: Page) {
    PageTitle(page.title, page.subtitle)
    page.sections.forEach { SectionPanel(it) }
}

// ---------------------------------------------------------------- Jump to world

const val SMW_JUMP_KEY = "jump"

/** "d of n" exits for a world, for the jump menu and the jump page. */
fun worldProgress(world: World, progress: ProgressStore): String {
    val ids = SmwLevels.all.filter { it.world == world }.flatMap { it.exits }.map { it.id }
    return "${progress.countDone(ids)} of ${ids.size}"
}

/** Shown while the "Jump to world" row is highlighted: every world, tap one to jump there. */
@Composable
fun WorldJumpDetail(progress: ProgressStore, onJump: (World) -> Unit) {
    PageTitle("Jump to world", "Press A or Select to open the list, or tap a world here.")
    World.entries.forEach { world ->
        Panel(stripe = Palette.accent, onClick = { onJump(world) }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(world.label, color = Palette.text, fontFamily = Condensed, fontSize = 19.sp, modifier = Modifier.weight(1f))
                Text(worldProgress(world, progress), color = Palette.muted, fontSize = 14.sp)
            }
        }
    }
}
