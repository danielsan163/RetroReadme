package com.retroreadme.games.aos

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

const val AOS_OVERVIEW_KEY = "overview"
const val AOS_JUMP_KEY = "jump"

/** Bosses by area. Strategies are on their way; for now this says where each one is. */
val areaBosses = mapOf(
    "Castle Corridor" to listOf("Creaking Skull"),
    "Chapel" to listOf("Manticore"),
    "Study" to listOf("Great Armor"),
    "Dance Hall" to listOf("Big Golem"),
    "Inner Quarters" to listOf("Headhunter"),
    "Floating Garden" to listOf("Belmont"),
    "Clock Tower" to listOf("Death"),
    "Underground Cemetery" to listOf("Legion"),
    "The Arena" to listOf("Balore"),
    "Top Floor" to listOf("Graham"),
    "Chaotic Realm" to listOf("Chaos"),
)

fun typeProgress(type: SoulType, progress: ProgressStore): String {
    val ids = AosSouls.all.filter { it.type == type }.map { it.id }
    return "${progress.countDone(ids)} of ${ids.size}"
}

fun soulsIn(area: String): List<Soul> = AosSouls.all.filter { area in it.areas }

@Composable
fun SoulDetail(soul: Soul, progress: ProgressStore) {
    PageTitle(soul.name, soul.type.label.removeSuffix("s") + (soul.number?.let { " · No. $it" } ?: " · Soul holder"))
    val done = progress.isDone(soul.id)
    Panel(stripe = soul.type.tint, onClick = { progress.toggle(soul.id) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(soul.name, color = Palette.text, fontFamily = Condensed, fontSize = 21.sp)
                Text(
                    if (done) "Absorbed" else "Not yet",
                    color = if (done) Palette.accent else Palette.muted, fontSize = 13.sp,
                )
            }
            CheckBoxMark(done)
        }
        Text(soul.effect, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
        soul.mp?.let { LabeledLine("MP", it, soul.type.tint) }
    }
    Panel(stripe = Palette.accent) {
        PanelHeading(if (soul.fromHolder) "Where" else "Where it drops")
        LabeledLine("Areas", soul.areas, Palette.accent)
        LabeledLine(if (soul.fromHolder) "Holder" else "Best spot", soul.best, Palette.accent)
    }
    soul.note?.let {
        Panel(stripe = Palette.secret) {
            PanelHeading("Worth knowing", Palette.secret)
            Text(it, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
        }
    }
}

@Composable
fun AosOverviewDetail(progress: ProgressStore) {
    PageTitle("Soul collection", "Press A on a soul to check it off.")
    Panel(stripe = Palette.accent) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SoulType.entries.forEach { type ->
                val souls = AosSouls.all.filter { it.type == type }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.width(150.dp)) {
                        Text(type.label, color = Palette.text, fontFamily = Condensed, fontSize = 17.sp)
                        Text(typeProgress(type, progress), color = Palette.muted, fontSize = 13.sp)
                    }
                    EnergyCells(
                        colors = souls.map { type.tint },
                        filled = souls.map { progress.isDone(it.id) },
                        cellWidth = 4.dp, cellHeight = 22.dp,
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
fun TypeJumpDetail(progress: ProgressStore, onJump: (SoulType) -> Unit) {
    PageTitle("Jump to soul type", "Press A or Select to open the list, or tap a type here.")
    SoulType.entries.forEach { type ->
        Panel(stripe = type.tint, onClick = { onJump(type) }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(type.label, color = Palette.text, fontFamily = Condensed, fontSize = 19.sp, modifier = Modifier.weight(1f))
                Text(typeProgress(type, progress), color = Palette.muted, fontSize = 14.sp)
            }
            Text(type.howToUse, color = Palette.muted, fontSize = 14.sp)
        }
    }
}

@Composable
fun AreaDetail(area: String, progress: ProgressStore) {
    val souls = soulsIn(area)
    PageTitle(area, "${progress.countDone(souls.map { it.id })} of ${souls.size} souls found here collected")
    areaBosses[area]?.let { bosses ->
        TagRow { bosses.forEach { Tag("Boss: $it", Palette.warning) } }
    }
    val keys = AosPages.route.map { it.first }.mapNotNull { AosSouls.byId[it] }.filter { area in it.areas }
    if (keys.isNotEmpty()) {
        Panel(stripe = Palette.secret) {
            PanelHeading("Key souls here", Palette.secret)
            Lines(keys.map { "${it.name}: ${it.effect}" })
        }
    }
    SoulType.entries.forEach { type ->
        val ofType = souls.filter { it.type == type }
        if (ofType.isNotEmpty()) {
            Panel(stripe = type.tint) {
                PanelHeading(type.label, type.tint)
                Lines(ofType.map { (if (progress.isDone(it.id)) "\u2713 " else "") + it.name })
            }
        }
    }
}

@Composable
fun RouteDetail(soul: Soul, text: String, step: Int, progress: ProgressStore) {
    PageTitle("$step. ${soul.name}", soul.areas)
    val done = progress.isDone(soul.id)
    Panel(stripe = soul.type.tint, onClick = { progress.toggle(soul.id) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp, modifier = Modifier.weight(1f))
            CheckBoxMark(done)
        }
    }
    Panel(stripe = Palette.accent) {
        LabeledLine("Where", soul.best, Palette.accent)
    }
}

@Composable
fun AosPageDetail(page: AosPage) {
    PageTitle(page.title, page.subtitle)
    page.sections.forEach { SectionPanel(it) }
}
