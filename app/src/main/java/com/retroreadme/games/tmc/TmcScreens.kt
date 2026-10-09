package com.retroreadme.games.tmc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.retroreadme.ui.LabeledLine
import com.retroreadme.ui.Lines
import com.retroreadme.ui.PageTitle
import com.retroreadme.ui.Palette
import com.retroreadme.ui.Panel
import com.retroreadme.ui.PanelHeading
import com.retroreadme.ui.SectionPanel
import com.retroreadme.ui.Tag
import com.retroreadme.ui.TagRow

const val TMC_OVERVIEW_KEY = "overview"
const val TMC_JUMP_KEY = "jump"

/** Everything that counts toward 100%: Heart Pieces, fusions, bottles and upgrades. */
val tmcChecklistIds: List<String> =
    TmcData.hearts.map { it.id } + TmcFusions.all.map { it.id } + TmcData.upgrades.map { it.id }

fun countLine(ids: List<String>, progress: ProgressStore): String = "${progress.countDone(ids)} of ${ids.size}"

fun heartTitle(heart: Heart): String = "Heart Piece ${heart.id.drop(1).trimStart('0')}"

fun fusionTitle(fusion: Fusion): String = if (fusion.random) "Random fusion ${fusion.number}" else fusion.fuser

/** A checkable panel: title, done/not-done line, and whatever goes under it. */
@Composable
private fun CheckPanel(id: String, title: String, stripe: androidx.compose.ui.graphics.Color, progress: ProgressStore, doneText: String, content: @Composable () -> Unit) {
    val done = progress.isDone(id)
    Panel(stripe = stripe, onClick = { progress.toggle(id) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(title, color = Palette.text, fontFamily = Condensed, fontSize = 20.sp)
                Text(
                    if (done) doneText else "Not yet",
                    color = if (done) Palette.accent else Palette.muted, fontSize = 13.sp,
                )
            }
            CheckBoxMark(done)
        }
        content()
    }
}

@Composable
fun StepPanel(step: Step, progress: ProgressStore, current: Boolean) {
    CheckPanel(step.id, if (current) "This step" else "Step ${step.id.drop(1).trimStart('0')}", if (current) Palette.accent else Palette.line, progress, "Done") {
        Text(step.text, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
    }
}

/** A step's page: the whole chapter, so the steps around it are in view. */
@Composable
fun StepDetail(step: Step, progress: ProgressStore) {
    val chapter = TmcData.steps.filter { it.chapter == step.chapter }
    PageTitle(step.chapter, "Chapter ${TmcData.chapters.indexOf(step.chapter) + 1} of ${TmcData.chapters.size} · A checks a step off")
    chapter.forEach { StepPanel(it, progress, current = it.id == step.id) }
}

@Composable
fun WalkthroughOverview(progress: ProgressStore) {
    val ids = TmcData.steps.map { it.id }
    PageTitle("Walkthrough", "Where to go next, in a line or two. Dungeons get one line: what's inside.")
    Panel(stripe = Palette.accent) {
        LabeledLine("Steps", "${countLine(ids, progress)} done", Palette.accent)
        val next = TmcData.steps.firstOrNull { !progress.isDone(it.id) }
        if (next != null) {
            LabeledLine("Next", next.chapter, Palette.text)
            Text(next.text, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
        }
        Text("Steps are just for keeping your place; they don't count toward 100%.", color = Palette.muted, fontSize = 14.sp)
    }
    Panel(stripe = Palette.warning) {
        PanelHeading("One thing you can lose for good", Palette.warning)
        Text("The Light Arrows. See Hints before you head up Veil Falls.", color = Palette.text, fontSize = 15.sp)
    }
}

@Composable
fun HeartDetail(heart: Heart, progress: ProgressStore) {
    PageTitle(heartTitle(heart), heart.area)
    CheckPanel(heart.id, heartTitle(heart), TmcColors.Heart, progress, "Collected") {
        if (heart.needs.isNotEmpty()) TagRow { heart.needs.forEach { Tag(it, Palette.accent) } }
        Lines(listOf(heart.how), marker = TmcColors.Heart)
    }
}

@Composable
fun FusionDetail(fusion: Fusion, progress: ProgressStore) {
    PageTitle(fusionTitle(fusion), "${fusion.color.label} Kinstone · Stage ${fusion.stage}")
    CheckPanel(fusion.id, fusionTitle(fusion), fusion.color.color, progress, "Fused") {
        TagRow {
            Tag(fusion.color.label, fusion.color.color)
            Tag("Stage ${fusion.stage}", Palette.muted)
        }
        LabeledLine("With", if (fusion.random) "Anyone on the random list (see Hints)" else "${fusion.fuser}, ${fusion.location}", Palette.text)
        LabeledLine("Result", fusion.result, Palette.secret)
    }
}

@Composable
fun UpgradeDetail(upgrade: Upgrade, progress: ProgressStore) {
    PageTitle(upgrade.name, "${upgrade.kind.label} · ${upgrade.area}")
    CheckPanel(upgrade.id, upgrade.name, TmcColors.Upgrade, progress, "Got it") {
        Lines(listOf(upgrade.how), marker = TmcColors.Upgrade)
        upgrade.missable?.let { Text(it, color = Palette.warning, fontSize = 14.sp, lineHeight = 20.sp) }
        if (upgrade.confirm) Text("Only one of the two guides covers this one. Worth confirming on the Nova.", color = Palette.warning, fontSize = 14.sp)
    }
}

@Composable
private fun ClearChecklist(progress: ProgressStore) {
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

/** Progress page shared by the three checklist tabs. */
@Composable
fun ChecklistOverview(title: String, subtitle: String, progress: ProgressStore, groups: List<Pair<String, List<Pair<String, androidx.compose.ui.graphics.Color>>>>) {
    PageTitle(title, subtitle)
    Panel(stripe = Palette.accent) {
        LabeledLine("100%", "${countLine(tmcChecklistIds, progress)} (Heart Pieces, fusions and upgrades)", Palette.accent)
        TagRow {
            Tag("Hearts ${countLine(TmcData.hearts.map { it.id }, progress)}", TmcColors.Heart)
            Tag("Fusions ${countLine(TmcFusions.all.map { it.id }, progress)}", TmcColors.EzloGold)
            Tag("Upgrades ${countLine(TmcData.upgrades.map { it.id }, progress)}", TmcColors.Upgrade)
        }
        groups.forEach { (label, cells) ->
            Column {
                Text("$label · ${countLine(cells.map { it.first }, progress)}", color = Palette.text, fontFamily = Condensed, fontSize = 16.sp)
                EnergyCells(cells.map { it.second }, cells.map { progress.isDone(it.first) }, cellWidth = 8.dp, cellHeight = 16.dp)
            }
        }
    }
    ClearChecklist(progress)
}

@Composable
fun JumpDetail(title: String, options: List<Pair<String, String>>, onJump: (String) -> Unit) {
    PageTitle(title, "Press A or Select to open the list, or tap one here.")
    options.forEach { (id, label) ->
        Panel(stripe = Palette.accent, onClick = { onJump(id) }) {
            Text(label, color = Palette.text, fontFamily = Condensed, fontSize = 19.sp)
        }
    }
}

@Composable
fun BossDetail(boss: Boss) {
    PageTitle(boss.name, boss.dungeon)
    TagRow { Tag("Guards: ${boss.reward}", Palette.secret) }
    Panel(stripe = Palette.accent) {
        PanelHeading("Strategy")
        Lines(boss.strategy)
    }
}

@Composable
fun TmcPageDetail(page: TmcPage) {
    PageTitle(page.title, page.subtitle)
    page.sections.forEach { SectionPanel(it) }
}
