package com.retroreadme.games.mmx2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
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

// MMX2's detail-pane screens. Moved here from the old ui/Details.kt and ui/WeaponDetail.kt;
// SectionPanel is now shared in ui/Components.kt, and ItemType.color() is in Mmx2Theme.kt.

// ---------------------------------------------------------------- Boss order & secrets

@Composable
fun InfoPageDetail(page: InfoPage) {
    PageTitle(page.title, page.subtitle)
    page.route.forEach { RouteStepPanel(it) }
    page.sections.forEach { SectionPanel(it) }
}

@Composable
private fun RouteStepPanel(step: RouteStep) {
    Panel(stripe = if (step.warning != null) Palette.warning else Palette.line) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "${step.number}", color = Palette.accent, fontFamily = Condensed, fontSize = 30.sp,
                modifier = Modifier.width(34.dp),
            )
            Column(Modifier.weight(1f)) {
                Text(step.maverick, color = Palette.text, fontFamily = Condensed, fontSize = 20.sp)
                Text(step.area, color = Palette.muted, fontSize = 14.sp)
            }
            Tag("Use ${step.useWeapon}", Palette.warning)
        }
        if (step.collect.isNotEmpty()) LabeledLine("Grab", step.collect.joinToString(", "), Mmx2Colors.SubTank)
        step.later?.let { LabeledLine("Not yet", it, Palette.muted) }
        step.thenDo.forEach { LabeledLine("Then", it, Mmx2Colors.Armor) }
        step.warning?.let { LabeledLine("Careful", it, Palette.warning) }
    }
}

// ---------------------------------------------------------------- Power-ups

const val OVERVIEW_KEY = "overview"

@Composable
fun StageDetail(stage: Stage, progress: ProgressStore) {
    PageTitle(stage.maverick, stage.area)
    if (stage.weakness != null || stage.weapon != null) {
        TagRow {
            stage.weakness?.let { Tag("Weak to $it", Palette.warning) }
            stage.weapon?.let { Tag("Drops $it", Palette.accent) }
        }
    }
    stage.powerUps.forEach { pu ->
        PowerUpPanel(pu, progress.isDone(pu.id)) { progress.toggle(pu.id) }
    }
    if (stage.miniBosses.isNotEmpty()) {
        Panel(stripe = Palette.line) {
            PanelHeading("Mini-bosses here")
            Lines(stage.miniBosses + "Strategies are in the Bosses tab.")
        }
    }
    if (stage.notes.isNotEmpty()) {
        Panel(stripe = Palette.warning) {
            PanelHeading("Worth knowing", Palette.warning)
            Lines(stage.notes, marker = Palette.warning)
        }
    }
    stage.xHunterDoor?.let { door ->
        Panel(stripe = Palette.secret) {
            PanelHeading("X-Hunter door", Palette.secret)
            if (stage.xHunterDoorRequires.isNotEmpty()) {
                TagRow { stage.xHunterDoorRequires.forEach { Tag("Needs $it", Palette.secret) } }
            }
            Text(door, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
            Text(
                "It only opens when the stage select map shows a Sigma icon on this stage.",
                color = Palette.muted, fontSize = 14.sp,
            )
        }
    }
}

@Composable
private fun PowerUpPanel(pu: PowerUp, done: Boolean, onToggle: () -> Unit) {
    val color = pu.type.color()
    Panel(stripe = color, onClick = onToggle) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TypeBadge(pu.type)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(pu.name, color = Palette.text, fontFamily = Condensed, fontSize = 21.sp)
                Text(if (done) "Collected" else pu.type.label, color = if (done) Palette.accent else Palette.muted, fontSize = 13.sp)
            }
            CheckBoxMark(done)
        }
        TagRow {
            if (pu.requires.isEmpty()) Tag("Nothing needed", Palette.muted)
            else pu.requires.forEach { Tag("Needs $it", Palette.warning) }
        }
        Lines(pu.steps, numbered = true, marker = color)
        pu.effect?.let { LabeledLine("Effect", it, color) }
    }
}

@Composable
private fun TypeBadge(type: ItemType) {
    Box(
        Modifier
            .size(34.dp)
            .background(type.color().copy(alpha = 0.2f), CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Text(type.symbol, color = type.color(), fontFamily = Condensed, fontSize = 18.sp)
    }
}

@Composable
fun OverviewDetail(progress: ProgressStore) {
    PageTitle("Collection", "Press A on any item inside a stage to check it off.")
    Panel(stripe = Palette.accent) {
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            ItemType.entries.forEach { type ->
                val ids = GuideData.allPowerUps.filter { it.type == type }.map { it.id }
                if (ids.isEmpty()) return@forEach
                val done = progress.countDone(ids)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.width(150.dp)) {
                        Text(type.label + if (ids.size > 1) "s" else "", color = Palette.text, fontFamily = Condensed, fontSize = 18.sp)
                        Text("$done of ${ids.size}", color = Palette.muted, fontSize = 13.sp)
                    }
                    EnergyCells(
                        colors = List(ids.size) { type.color() },
                        filled = List(ids.size) { it < done },
                        cellWidth = 16.dp, cellHeight = 26.dp,
                    )
                }
            }
        }
    }
    Panel(stripe = Mmx2Colors.Armor) {
        PanelHeading("Armor parts", Mmx2Colors.Armor)
        Lines(
            listOf(
                "Head: Crystal Snail's stage, no requirements.",
                "Arms: Wheel Gator's stage, needs Legs.",
                "Body: Morph Moth's stage, needs Spin Wheel.",
                "Legs: Overdrive Ostrich's stage, needs Spin Wheel.",
            ),
            marker = Mmx2Colors.Armor,
        )
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

// ---------------------------------------------------------------- Bosses

@Composable
fun BossDetail(boss: Boss) {
    PageTitle(boss.name, boss.location)
    TagRow {
        Tag("Weak to ${boss.weakness}", Palette.warning)
        boss.reward?.let { Tag(it, Palette.accent) }
    }
    boss.weaponNote?.let { note ->
        Panel(stripe = Palette.warning) {
            PanelHeading("Weapon notes", Palette.warning)
            Text(note, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
        }
    }
    Panel(stripe = Mmx2Colors.Heart) {
        PanelHeading("Attacks", Mmx2Colors.Heart)
        Lines(boss.attacks, marker = Mmx2Colors.Heart)
    }
    Panel(stripe = Palette.accent) {
        PanelHeading("Strategy")
        Lines(boss.strategy)
    }
}

/** Keeps the meter colors in a stage row matching item types. */
fun stageCells(stage: Stage, progress: ProgressStore) =
    stage.powerUps.map { it.type.color() } to stage.powerUps.map { progress.isDone(it.id) }

// ---------------------------------------------------------------- Weapons

@Composable
fun WeaponDetail(weapon: Weapon) {
    PageTitle(weapon.name, weapon.source)
    weapon.beats?.let { TagRow { Tag("Beats $it", Palette.warning) } }

    Panel(stripe = Palette.accent) {
        PanelHeading("Uncharged")
        Lines(weapon.uncharged)
    }
    Panel(stripe = Palette.warning) {
        PanelHeading("Charged", Palette.warning)
        Lines(weapon.charged, marker = Palette.warning)
        if (weapon.needsArmToCharge) {
            Text(
                "Charging special weapons needs the Arm parts from Wheel Gator's stage.",
                color = Palette.muted, fontSize = 14.sp,
            )
        }
    }
    if (weapon.uses.isNotEmpty()) {
        Panel(stripe = Mmx2Colors.Armor) {
            PanelHeading("Other uses", Mmx2Colors.Armor)
            Lines(weapon.uses, marker = Mmx2Colors.Armor)
        }
    }
}
