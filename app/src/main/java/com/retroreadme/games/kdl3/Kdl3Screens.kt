package com.retroreadme.games.kdl3

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retroreadme.core.ProgressStore
import com.retroreadme.ui.CheckBoxMark
import com.retroreadme.ui.Condensed
import com.retroreadme.ui.EnergyCells
import com.retroreadme.ui.Lines
import com.retroreadme.ui.PageTitle
import com.retroreadme.ui.Palette
import com.retroreadme.ui.Panel
import com.retroreadme.ui.PanelHeading
import com.retroreadme.ui.SectionPanel
import com.retroreadme.ui.Tag
import com.retroreadme.ui.TagRow

const val KDL3_OVERVIEW_KEY = "overview"

/** Everything on the checklist, in order: 30 Heart Stars, then the four 100% extras. */
val kdl3ChecklistIds: List<String> = Kdl3Stages.all.map { it.heartId } + Kdl3Stages.extras.map { it.id }

fun stageNeeds(stage: Stage): String? {
    val parts = stage.friends.map { it.label } + stage.abilities.map { it.label }
    return if (parts.isEmpty()) null else parts.joinToString(" + ")
}

@Composable
private fun CheckPanel(title: String, done: Boolean, doneLabel: String, onToggle: () -> Unit, content: @Composable () -> Unit) {
    Panel(stripe = Kdl3Colors.HeartGold, onClick = onToggle) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(title, color = Palette.text, fontFamily = Condensed, fontSize = 21.sp)
                Text(
                    if (done) doneLabel else "Not yet",
                    color = if (done) Palette.accent else Palette.muted, fontSize = 13.sp,
                )
            }
            CheckBoxMark(done)
        }
        content()
    }
}

@Composable
fun StageDetail(stage: Stage, progress: ProgressStore) {
    PageTitle(stage.name, "Heart Star from ${stage.character}")
    val done = progress.isDone(stage.heartId)
    CheckPanel("Heart Star", done, "Collected", { progress.toggle(stage.heartId) }) {
        Text(stage.task, color = Palette.text, fontSize = 16.sp)
        if (stage.friends.isNotEmpty() || stage.abilities.isNotEmpty()) {
            TagRow {
                stage.friends.forEach { Tag(it.label, Palette.accent) }
                stage.abilities.forEach { Tag(it.label, Palette.secret) }
            }
        }
    }
    Panel(stripe = Palette.accent) {
        PanelHeading("How to get it")
        Lines(stage.steps, numbered = stage.steps.size > 1)
        if (stage.confirm) {
            Text(
                "The two guides disagree on part of this route. Worth confirming on the Nova.",
                color = Palette.warning, fontSize = 14.sp,
            )
        }
    }
    if (stage.notes.isNotEmpty()) {
        Panel(stripe = Palette.line) {
            PanelHeading("Worth knowing")
            Lines(stage.notes)
        }
    }
}

@Composable
fun ExtraDetail(extra: Extra, progress: ProgressStore) {
    PageTitle(extra.title, extra.subtitle)
    CheckPanel("Done", progress.isDone(extra.id), "Cleared", { progress.toggle(extra.id) }) {
        Lines(extra.steps, numbered = extra.steps.size > 1)
    }
}

@Composable
fun Kdl3OverviewDetail(progress: ProgressStore) {
    PageTitle("Progress", "Press A on a Heart Star or extra to check it off.")
    Panel(stripe = Palette.accent) {
        val hearts = Kdl3Stages.all.map { it.heartId }
        val extras = Kdl3Stages.extras.map { it.id }
        TagRow {
            Tag("Heart Stars ${progress.countDone(hearts)} of ${hearts.size}", Kdl3Colors.HeartGold)
            Tag("Extras ${progress.countDone(extras)} of ${extras.size}", Palette.accent)
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            World.entries.forEach { world ->
                val ids = Kdl3Stages.all.filter { it.world == world }.map { it.heartId }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.width(150.dp)) {
                        Text(world.label, color = Palette.text, fontFamily = Condensed, fontSize = 17.sp)
                        Text("${progress.countDone(ids)} of ${ids.size}", color = Palette.muted, fontSize = 13.sp)
                    }
                    EnergyCells(
                        colors = ids.map { Kdl3Colors.HeartGold },
                        filled = ids.map { progress.isDone(it) },
                        cellWidth = 14.dp, cellHeight = 22.dp,
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

@Composable
fun FriendDetail(friend: Friend, progress: ProgressStore) {
    PageTitle(friend.label, "Animal Friend")
    Panel(stripe = Palette.accent) { Text(friend.trait, color = Palette.text, fontSize = 16.sp) }
    NeededBy(Kdl3Stages.all.filter { friend in it.friends }, progress)
}

@Composable
fun AbilityDetail(ability: Ability, progress: ProgressStore) {
    PageTitle(ability.label, "Copy Ability")
    Panel(stripe = Palette.secret) {
        PanelHeading("Where to get it", Palette.secret)
        Text(ability.from, color = Palette.text, fontSize = 16.sp)
    }
    NeededBy(Kdl3Stages.all.filter { ability in it.abilities }, progress)
}

@Composable
private fun NeededBy(stages: List<Stage>, progress: ProgressStore) {
    Panel(stripe = Kdl3Colors.HeartGold) {
        PanelHeading("Heart Stars that use it", Kdl3Colors.HeartGold)
        if (stages.isEmpty()) {
            Text("None.", color = Palette.muted, fontSize = 15.sp)
        } else {
            Lines(stages.map { s -> (if (progress.isDone(s.heartId)) "\u2713 " else "") + "${s.name}: ${s.task}" })
        }
    }
}

@Composable
fun BossDetail(boss: Boss) {
    PageTitle(boss.name, boss.where)
    Panel(stripe = Palette.warning) {
        PanelHeading("Strategy", Palette.warning)
        Lines(boss.strategy, marker = Palette.warning)
    }
}

@Composable
fun Kdl3PageDetail(page: Kdl3Page) {
    PageTitle(page.title, page.subtitle)
    page.sections.forEach { SectionPanel(it) }
}
