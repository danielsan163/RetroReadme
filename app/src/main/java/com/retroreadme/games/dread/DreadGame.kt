package com.retroreadme.games.dread

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

/** Metroid Dread: all 146 items in route order and by area, with simplified area maps. */
object DreadGame {
    val game = Game(
        id = "dread",
        title = "Metroid Dread",
        platform = Platform.SWITCH,
        badge = "MD",
        palette = DreadPalette,
        about = listOf(
            "All 146 items in a 100% order, and by area: 23 upgrades, 8 Energy Tanks, 16 Energy Parts, 75 Missile, 11 Missile+ and 13 Power Bomb Tanks, with simplified area maps.",
            "Upgrade order, bosses, how to deal with the E.M.M.I.s, and hints.",
        ),
        checklist = DreadItems.all.map { CheckItem(it.id, it.kind.color) },
        celebrationTitle = "100% items",
        celebrationMessage = "Every upgrade and every tank on Zebes.\nSee you next mission.",
        tabs = listOf(
            GameTab("Route", DREAD_OVERVIEW_KEY) { ctx ->
                val progress = ctx.progress
                val items = DreadRoute.items
                var menuOpen by remember { mutableStateOf(false) }
                // Bumped after every jump or cancel so the list takes focus back.
                var jumps by remember { mutableIntStateOf(0) }

                fun jumpTo(leg: Leg) {
                    ctx.onSelect(leg.itemIds.first())
                    menuOpen = false
                    jumps++
                }

                val rows = listOf(
                    MasterItem(DREAD_OVERVIEW_KEY, "Progress", "${progress.countDone(items.map { it.id })} of ${items.size} items"),
                    MasterItem(DREAD_JUMP_KEY, "Jump to trip", "A or Select", expanded = false),
                ) + items.map { item ->
                    val leg = DreadRoute.legOf.getValue(item.id)
                    MasterItem(
                        item.id, item.name, "${item.area.label} · ${itemSubtitle(item)}",
                        cells = listOf(item.kind.color) to listOf(progress.isDone(item.id)),
                        groupHeader = if (leg.itemIds.first() == item.id) "${DreadRoute.legs.indexOf(leg) + 1}. ${leg.title}" else null,
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
                        onActivate = { k -> if (k == DREAD_JUMP_KEY) menuOpen = true },
                        footer = GUIDE_FOOTER + "\nSelect jumps to a trip.",
                        focusRequestKey = jumps.takeIf { it > 0 },
                    ) { k ->
                        when (k) {
                            DREAD_OVERVIEW_KEY -> RouteOverviewDetail(progress)
                            DREAD_JUMP_KEY -> LegJumpDetail(progress) { jumpTo(it) }
                            else -> ItemDetail(DreadItems.byId.getValue(k), progress)
                        }
                    }
                    if (menuOpen) {
                        ListMenu(
                            title = "Jump to trip",
                            options = DreadRoute.legs.mapIndexed { i, leg -> MenuOption(leg.id, "${i + 1}. ${leg.title}", legProgress(leg, progress)) },
                            currentId = DreadRoute.legOf[ctx.selectedKey]?.id,
                            onPick = { id -> jumpTo(DreadRoute.legs.first { it.id == id }) },
                            onCancel = {
                                menuOpen = false
                                jumps++
                            },
                        )
                    }
                }
            },

            GameTab("Areas", DREAD_OVERVIEW_KEY) { ctx ->
                val progress = ctx.progress
                val items = DreadItems.all
                var menuOpen by remember { mutableStateOf(false) }
                // Bumped after every jump or cancel so the list takes focus back.
                var jumps by remember { mutableIntStateOf(0) }

                fun jumpTo(area: Area) {
                    ctx.onSelect(items.first { it.area == area }.id)
                    menuOpen = false
                    jumps++
                }

                val rows = listOf(
                    MasterItem(DREAD_OVERVIEW_KEY, "Progress", "${progress.countDone(items.map { it.id })} of ${items.size} items"),
                    MasterItem(DREAD_JUMP_KEY, "Jump to area", "A or Select", expanded = false),
                ) + items.mapIndexed { i, item ->
                    MasterItem(
                        item.id, item.name, itemSubtitle(item),
                        cells = listOf(item.kind.color) to listOf(progress.isDone(item.id)),
                        groupHeader = if (i == 0 || items[i - 1].area != item.area) item.area.label else null,
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
                        onActivate = { k -> if (k == DREAD_JUMP_KEY) menuOpen = true },
                        footer = GUIDE_FOOTER + "\nSelect jumps to an area.",
                        focusRequestKey = jumps.takeIf { it > 0 },
                    ) { k ->
                        when (k) {
                            DREAD_OVERVIEW_KEY -> DreadOverviewDetail(progress)
                            DREAD_JUMP_KEY -> AreaJumpDetail(progress) { jumpTo(it) }
                            else -> ItemDetail(DreadItems.byId.getValue(k), progress)
                        }
                    }
                    if (menuOpen) {
                        ListMenu(
                            title = "Jump to area",
                            options = Area.entries.map { MenuOption(it.name, it.label, areaProgress(it, progress)) },
                            currentId = DreadItems.byId[ctx.selectedKey]?.area?.name,
                            onPick = { jumpTo(Area.valueOf(it)) },
                            onCancel = {
                                menuOpen = false
                                jumps++
                            },
                        )
                    }
                }
            },

            GameTab("Upgrades", DreadPages.upgradeOrder.first()) { ctx ->
                val ups = DreadPages.upgradeOrder.map { DreadItems.byId.getValue(it) }
                MasterDetail(
                    items = ups.mapIndexed { i, u ->
                        MasterItem(
                            u.id, "${i + 1}. ${u.name}", u.area.label,
                            cells = listOf(u.kind.color) to listOf(ctx.progress.isDone(u.id)),
                        )
                    },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> UpgradeDetail(DreadItems.byId.getValue(k), ctx.progress) }
            },

            GameTab("Bosses", DreadPages.bosses.first().id) { ctx ->
                val bosses = DreadPages.bosses
                MasterDetail(
                    items = bosses.map { MasterItem(it.id, it.name, it.place) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> BossDetail(bosses.first { it.id == k }) }
            },

            GameTab("Hints", DreadPages.hints.first().id) { ctx ->
                val pages = DreadPages.hints
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> DreadPageDetail(pages.first { it.id == k }) }
            },
        ),
    )
}
