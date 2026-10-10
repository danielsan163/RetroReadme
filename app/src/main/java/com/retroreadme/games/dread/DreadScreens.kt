package com.retroreadme.games.dread

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

const val DREAD_OVERVIEW_KEY = "overview"
const val DREAD_JUMP_KEY = "jump"

fun itemSubtitle(item: Item): String {
    val needs = if (item.needs.isEmpty()) "Nothing special" else item.needs.joinToString(", ")
    val room = DreadMaps.roomOf(item)?.code
    return listOfNotNull(room, needs).joinToString(" · ")
}

fun areaProgress(area: Area, progress: ProgressStore): String {
    val ids = DreadItems.all.filter { it.area == area }.map { it.id }
    return "${progress.countDone(ids)} of ${ids.size}"
}

@Composable
fun ItemDetail(item: Item, progress: ProgressStore) {
    val room = DreadMaps.roomOf(item)
    PageTitle(item.name, listOfNotNull(item.area.label, room?.code, item.kind.label).joinToString(" · "))
    val done = progress.isDone(item.id)
    Panel(stripe = item.kind.color, onClick = { progress.toggle(item.id) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(item.name, color = Palette.text, fontFamily = Condensed, fontSize = 21.sp)
                Text(
                    if (done) "Collected" else "Not collected yet",
                    color = if (done) Palette.accent else Palette.muted, fontSize = 13.sp,
                )
            }
            CheckBoxMark(done)
        }
        if (item.needs.isNotEmpty()) TagRow { item.needs.forEach { Tag(it, Palette.accent) } }
        Lines(item.steps, numbered = item.steps.size > 1, marker = item.kind.color)
        if (item.needsShinespark) {
            Text(
                "These steps work, but Shinesparks are hard to follow from text mid-game. A video is easier: search YouTube for \"${item.videoSearch}\".",
                color = Palette.secret, fontSize = 14.sp, lineHeight = 20.sp,
            )
        }
        if (item.confirm) {
            Text(
                "Placing this one from the guides' descriptions was a judgement call; the map position is exact. Worth confirming on the Nova.",
                color = Palette.warning, fontSize = 14.sp,
            )
        }
    }
    if (room != null) {
        // Close-up by default; A switches to the whole area and back.
        var zoomed by remember(item.id) { mutableStateOf(true) }
        Panel(onClick = { zoomed = !zoomed }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${item.area.label} map · ${room.code}", color = Palette.accent, fontFamily = Condensed, fontSize = 17.sp, modifier = Modifier.weight(1f))
                Text(if (zoomed) "A: whole area" else "A: close-up", color = Palette.muted, fontSize = 13.sp)
            }
            DreadMapView(item, progress, zoomed)
        }
    }
}

@Composable
fun UpgradeDetail(item: Item, progress: ProgressStore) {
    ItemDetail(item, progress)
    DreadPages.unlocks[item.id]?.let { text ->
        Panel(stripe = Palette.secret) {
            PanelHeading("What it opens up", Palette.secret)
            Text(text, color = Palette.text, fontSize = 16.sp, lineHeight = 22.sp)
        }
    }
}

@Composable
fun DreadOverviewDetail(progress: ProgressStore) {
    val all = DreadItems.all
    PageTitle("Items", "Press A on an item to check it off.")
    Panel(stripe = Palette.accent) {
        TagRow {
            Kind.entries.forEach { kind ->
                val ids = all.filter { it.kind == kind }.map { it.id }
                Tag("${kind.short} ${progress.countDone(ids)} of ${ids.size}", kind.color)
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Area.entries.forEach { area ->
                val items = all.filter { it.area == area }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.width(140.dp)) {
                        Text(area.label, color = Palette.text, fontFamily = Condensed, fontSize = 17.sp)
                        Text(areaProgress(area, progress), color = Palette.muted, fontSize = 13.sp)
                    }
                    EnergyCells(
                        colors = items.map { it.kind.color },
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
            if (armed) "Press again to clear every checkmark" else "Clear checklist",
            color = Palette.warning, fontFamily = Condensed, fontSize = 18.sp,
        )
    }
}

@Composable
fun AreaJumpDetail(progress: ProgressStore, onJump: (Area) -> Unit) {
    PageTitle("Jump to area", "Press A or Select to open the list, or tap an area here.")
    Area.entries.forEach { area ->
        Panel(stripe = Palette.accent, onClick = { onJump(area) }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(area.label, color = Palette.text, fontFamily = Condensed, fontSize = 19.sp, modifier = Modifier.weight(1f))
                Text(areaProgress(area, progress), color = Palette.muted, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun BossDetail(boss: Boss) {
    PageTitle(boss.name, boss.place)
    TagRow { Tag("Weak point: ${boss.weakPoint}", Palette.accent) }
    Panel(stripe = Palette.warning) {
        PanelHeading("Strategy", Palette.warning)
        Lines(boss.strategy, numbered = true, marker = Palette.warning)
    }
    Panel(stripe = Palette.line) {
        PanelHeading("Where and why")
        Lines(boss.lines)
    }
}

@Composable
fun DreadPageDetail(page: DreadPage) {
    PageTitle(page.title, page.subtitle)
    page.sections.forEach { SectionPanel(it) }
}

fun legProgress(leg: Leg, progress: ProgressStore): String =
    "${progress.countDone(leg.itemIds)} of ${leg.itemIds.size}"

@Composable
fun RouteOverviewDetail(progress: ProgressStore) {
    val all = DreadRoute.items
    PageTitle("100% route", "Every item in the order you'd pick it up. Press A on an item to check it off.")
    Panel(stripe = Palette.accent) {
        LabeledLine("Progress", "${progress.countDone(all.map { it.id })} of ${all.size} items", Palette.accent)
        val next = all.firstOrNull { !progress.isDone(it.id) }
        if (next != null) LabeledLine("Next", "${next.name} (${next.area.label}), ${DreadRoute.legOf.getValue(next.id).title}", Palette.text)
        Lines(
            listOf(
                "The story trips pick up the upgrades and what's on the way; once the Power Bomb opens everything, a cleanup trip per area gets the rest. Finish before the capsule to Itorash.",
                "Positions from MapGenie's map; directions from Gameranx's guides and walkthrough.",
            ),
        )
    }
    DreadRoute.legs.forEachIndexed { i, leg ->
        Panel(stripe = Palette.accent) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${i + 1}. ${leg.title}", color = Palette.text, fontFamily = Condensed, fontSize = 18.sp, modifier = Modifier.weight(1f))
                Text(legProgress(leg, progress), color = Palette.muted, fontSize = 14.sp)
            }
            Text(leg.note, color = Palette.muted, fontSize = 14.sp)
            EnergyCells(
                colors = leg.itemIds.map { DreadItems.byId.getValue(it).kind.color },
                filled = leg.itemIds.map { progress.isDone(it) },
                cellWidth = 8.dp, cellHeight = 18.dp,
            )
        }
    }
}

@Composable
fun LegJumpDetail(progress: ProgressStore, onJump: (Leg) -> Unit) {
    PageTitle("Jump to trip", "Press A or Select to open the list, or tap a trip here.")
    DreadRoute.legs.forEachIndexed { i, leg ->
        Panel(stripe = Palette.accent, onClick = { onJump(leg) }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${i + 1}. ${leg.title}", color = Palette.text, fontFamily = Condensed, fontSize = 19.sp, modifier = Modifier.weight(1f))
                Text(legProgress(leg, progress), color = Palette.muted, fontSize = 14.sp)
            }
        }
    }
}
