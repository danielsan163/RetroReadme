package com.retroreadme.games.mmz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import com.retroreadme.core.CheckItem
import com.retroreadme.core.Game
import com.retroreadme.core.GameTab
import com.retroreadme.core.Platform
import com.retroreadme.ui.GUIDE_FOOTER
import com.retroreadme.ui.ListMenu
import com.retroreadme.ui.MasterDetail
import com.retroreadme.ui.MasterItem
import com.retroreadme.ui.MenuOption
import android.view.KeyEvent as AndroidKeyEvent

/** Mega Man Zero (GBA): missions in a good order, all 78 Cyber-elves, bosses and weapons. */
object MmzGame {
    val game = Game(
        id = "mmz",
        title = "Mega Man Zero",
        platform = Platform.GBA,
        badge = "Z",
        palette = MmzPalette,
        about = listOf(
            "Every mission in a good order, with each boss's weakness, and all 78 Cyber-elves: where they are and which ones you can miss.",
            "Boss strategies, weapons and chips, and how ranks, Jackson and the extra modes work.",
        ),
        checklist = MmzData.elves.map { CheckItem(it.id, it.family.color) },
        celebrationTitle = "Every Cyber-elf",
        celebrationMessage = "All 78 found.\nNow raise them all, use none, and Jackson is yours.",
        tabs = listOf(
            GameTab("Missions", MMZ_OVERVIEW_KEY) { ctx ->
                val progress = ctx.progress
                val rows = listOf(
                    MasterItem(MMZ_OVERVIEW_KEY, "Overview", "${progress.countDone(MmzData.elves.map { it.id })} of ${MmzData.elves.size} elves"),
                ) + MmzData.missions.map { m ->
                    val elves = MmzData.elvesIn(m)
                    MasterItem(
                        m.id, "${missionNumber(m)}. ${m.title}",
                        listOfNotNull(m.boss.ifEmpty { null }, m.weakness?.let { "weak to $it" }).joinToString(" · ").ifEmpty { m.area },
                        cells = elves.map { it.family.color } to elves.map { progress.isDone(it.id) },
                    )
                }
                MasterDetail(
                    items = rows, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    if (k == MMZ_OVERVIEW_KEY) MissionsOverviewDetail(progress)
                    else MissionDetail(MmzData.missionById.getValue(k), progress)
                }
            },

            GameTab("Cyber-elves", MMZ_OVERVIEW_KEY) { ctx ->
                val progress = ctx.progress
                val elves = MmzData.elves
                var menuOpen by remember { mutableStateOf(false) }
                // Bumped after every jump or cancel so the list takes focus back.
                var jumps by remember { mutableIntStateOf(0) }

                fun jumpTo(mission: Mission) {
                    ctx.onSelect(MmzData.elvesIn(mission).first().id)
                    menuOpen = false
                    jumps++
                }

                val withElves = MmzData.missions.filter { MmzData.elvesIn(it).isNotEmpty() }
                val rows = listOf(
                    MasterItem(MMZ_OVERVIEW_KEY, "Progress", "${progress.countDone(elves.map { it.id })} of ${elves.size} elves"),
                    MasterItem(MMZ_JUMP_KEY, "Jump to mission", "A or Select", expanded = false),
                ) + elves.mapIndexed { i, elf ->
                    val mission = MmzData.missionById.getValue(elf.missionId)
                    MasterItem(
                        elf.id, elf.name, elfSubtitle(elf),
                        cells = listOf(elf.family.color) to listOf(progress.isDone(elf.id)),
                        groupHeader = if (i == 0 || elves[i - 1].missionId != elf.missionId) "${missionNumber(mission)}. ${mission.title}" else null,
                    )
                }
                Box(
                    Modifier
                        .fillMaxSize()
                        .onPreviewKeyEvent { event ->
                            if (event.type == KeyEventType.KeyDown && !menuOpen &&
                                event.nativeKeyEvent.keyCode == AndroidKeyEvent.KEYCODE_BUTTON_SELECT
                            ) {
                                menuOpen = true
                                true
                            } else {
                                false
                            }
                        },
                ) {
                    MasterDetail(
                        items = rows, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                        detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                        onActivate = { k -> if (k == MMZ_JUMP_KEY) menuOpen = true },
                        footer = GUIDE_FOOTER + "\nSelect jumps to a mission.",
                        focusRequestKey = jumps.takeIf { it > 0 },
                    ) { k ->
                        when (k) {
                            MMZ_OVERVIEW_KEY -> ElvesOverviewDetail(progress)
                            MMZ_JUMP_KEY -> MissionJumpDetail(progress) { jumpTo(it) }
                            else -> ElfDetail(MmzData.elfById.getValue(k), progress)
                        }
                    }
                    if (menuOpen) {
                        ListMenu(
                            title = "Jump to mission",
                            options = withElves.map { MenuOption(it.id, "${missionNumber(it)}. ${it.title}", missionProgress(it, progress)) },
                            currentId = MmzData.elfById[ctx.selectedKey]?.missionId,
                            onPick = { id -> jumpTo(MmzData.missionById.getValue(id)) },
                            onCancel = {
                                menuOpen = false
                                jumps++
                            },
                        )
                    }
                }
            },

            GameTab("Bosses", MmzPages.bosses.first().id) { ctx ->
                val bosses = MmzPages.bosses
                MasterDetail(
                    items = bosses.map { b ->
                        MasterItem(b.id, b.name, listOfNotNull(MmzData.missionById.getValue(b.missionId).title, b.weakness?.let { "weak to $it" }).joinToString(" · "))
                    },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> BossDetail(bosses.first { it.id == k }) }
            },

            GameTab("Weapons", MmzPages.weapons.first().id) { ctx ->
                val pages = MmzPages.weapons
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> MmzPageDetail(pages.first { it.id == k }) }
            },

            GameTab("Hints", MmzPages.hints.first().id) { ctx ->
                val pages = MmzPages.hints
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> MmzPageDetail(pages.first { it.id == k }) }
            },
        ),
    )
}
