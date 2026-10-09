package com.retroreadme.games.mmz

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
import com.retroreadme.ui.LabeledLine
import com.retroreadme.ui.Lines
import com.retroreadme.ui.PageTitle
import com.retroreadme.ui.Palette
import com.retroreadme.ui.Panel
import com.retroreadme.ui.PanelHeading
import com.retroreadme.ui.SectionPanel
import com.retroreadme.ui.Tag
import com.retroreadme.ui.TagRow

const val MMZ_OVERVIEW_KEY = "overview"
const val MMZ_JUMP_KEY = "jump"

fun missionNumber(mission: Mission): Int = MmzData.missions.indexOf(mission) + 1

fun missionProgress(mission: Mission, progress: ProgressStore): String {
    val ids = MmzData.elvesIn(mission).map { it.id }
    return "${progress.countDone(ids)} of ${ids.size} elves"
}

fun elfSubtitle(elf: Elf): String = "${elf.family.label} · ${elf.source.label}"

/** One elf as a panel you can check off; used on its own page and in mission pages. */
@Composable
fun ElfPanel(elf: Elf, progress: ProgressStore, showMission: Boolean) {
    val done = progress.isDone(elf.id)
    Panel(stripe = elf.family.color, onClick = { progress.toggle(elf.id) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(elf.name, color = Palette.text, fontFamily = Condensed, fontSize = 20.sp)
                Text(
                    if (done) "Found" else "Not found yet",
                    color = if (done) Palette.accent else Palette.muted, fontSize = 13.sp,
                )
            }
            CheckBoxMark(done)
        }
        TagRow {
            Tag(elf.family.label, elf.family.color)
            Tag(elf.source.label, elf.source.color)
            if (showMission) Tag(MmzData.missionById.getValue(elf.missionId).title, Palette.muted)
        }
        Lines(listOf(elf.how), marker = elf.family.color)
        elf.missable?.let { Text(it, color = Palette.warning, fontSize = 14.sp, lineHeight = 20.sp) }
    }
}

@Composable
fun ElfDetail(elf: Elf, progress: ProgressStore) {
    val mission = MmzData.missionById.getValue(elf.missionId)
    PageTitle(elf.name, "${elf.family.label} elf · ${missionNumber(mission)}. ${mission.title}")
    ElfPanel(elf, progress, showMission = false)
    Panel(stripe = Palette.secret) {
        PanelHeading("What it does", Palette.secret)
        Text(elf.group.effect, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
        LabeledLine(
            "Raise",
            if (elf.group.ec == 0) "Usable as soon as you find it" else "${elf.group.ec} E-Crystals before you can use it",
            Palette.muted,
        )
        if (elf.group.lasting) Text("Its effect lasts for the rest of the game.", color = Palette.muted, fontSize = 14.sp)
    }
}

@Composable
fun MissionDetail(mission: Mission, progress: ProgressStore) {
    PageTitle("${missionNumber(mission)}. ${mission.title}", mission.area)
    if (mission.boss.isNotEmpty()) {
        TagRow {
            Tag("Boss: ${mission.boss}", Palette.accent)
            mission.weakness?.let { Tag("Weak to $it", Palette.secret) }
        }
    }
    Panel(stripe = Palette.accent) {
        Text(mission.unlock, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
        Lines(mission.tips)
    }
    val elves = MmzData.elvesIn(mission)
    if (elves.isNotEmpty()) {
        Text(
            "Cyber-elves · ${missionProgress(mission, progress)}",
            color = Palette.accent, fontFamily = Condensed, fontSize = 18.sp,
        )
        elves.forEach { ElfPanel(it, progress, showMission = false) }
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

@Composable
fun MissionsOverviewDetail(progress: ProgressStore) {
    val all = MmzData.elves
    PageTitle("Missions", "In a good order, with the chip each boss is weak to and the elves you can find.")
    Panel(stripe = Palette.warning) {
        PanelHeading("Missions can't be replayed", Palette.warning)
        Text(
            "Clearing or failing a mission closes it, and elves dropped by its enemies go with it. Boxes stay put. See Hints.",
            color = Palette.text, fontSize = 15.sp, lineHeight = 21.sp,
        )
    }
    Panel(stripe = Palette.accent) {
        LabeledLine("Elves", "${progress.countDone(all.map { it.id })} of ${all.size} found", Palette.accent)
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            MmzData.missions.forEach { mission ->
                val elves = MmzData.elvesIn(mission)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.width(220.dp)) {
                        Text("${missionNumber(mission)}. ${mission.title}", color = Palette.text, fontFamily = Condensed, fontSize = 16.sp)
                        Text(missionProgress(mission, progress), color = Palette.muted, fontSize = 13.sp)
                    }
                    EnergyCells(
                        colors = elves.map { it.family.color },
                        filled = elves.map { progress.isDone(it.id) },
                        cellWidth = 8.dp, cellHeight = 18.dp,
                    )
                }
            }
        }
    }
}

@Composable
fun ElvesOverviewDetail(progress: ProgressStore) {
    val all = MmzData.elves
    PageTitle("Cyber-elves", "All 78, by mission. Press A on an elf to check it off.")
    Panel(stripe = Palette.accent) {
        TagRow {
            Family.entries.forEach { family ->
                val ids = all.filter { it.family == family }.map { it.id }
                Tag("${family.label} ${progress.countDone(ids)} of ${ids.size}", family.color)
            }
        }
        Family.entries.forEach { family ->
            LabeledLine(family.label, family.about, family.color)
        }
        val missable = all.filter { it.source == Source.ENEMIES || it.source == Source.MISSION }.map { it.id }
        LabeledLine("Watch out", "${missable.size - progress.countDone(missable)} left that you can only get during their mission", Palette.warning)
    }
    ClearChecklist(progress)
}

@Composable
fun MissionJumpDetail(progress: ProgressStore, onJump: (Mission) -> Unit) {
    PageTitle("Jump to mission", "Press A or Select to open the list, or tap a mission here.")
    MmzData.missions.forEach { mission ->
        Panel(stripe = Palette.accent, onClick = { onJump(mission) }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${missionNumber(mission)}. ${mission.title}", color = Palette.text, fontFamily = Condensed, fontSize = 19.sp, modifier = Modifier.weight(1f))
                Text(missionProgress(mission, progress), color = Palette.muted, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun BossDetail(boss: Boss) {
    val mission = MmzData.missionById.getValue(boss.missionId)
    PageTitle(boss.name, "${missionNumber(mission)}. ${mission.title} · ${mission.area}")
    TagRow {
        Tag("Weak to ${boss.weakness ?: "nothing"}", Palette.secret)
        boss.reward?.let { Tag("Gives $it", Palette.accent) }
    }
    Panel(stripe = Palette.accent) {
        PanelHeading("Strategy")
        Lines(boss.strategy)
    }
}

@Composable
fun MmzPageDetail(page: MmzPage) {
    PageTitle(page.title, page.subtitle)
    page.sections.forEach { SectionPanel(it) }
}
