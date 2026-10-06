package com.retroreadme.games.mzm

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

/** Metroid: Zero Mission: every one of the 100 items, in route order and by area. */
object MzmGame {
    val game = Game(
        id = "mzm",
        title = "Metroid: Zero Mission",
        platform = Platform.GBA,
        badge = "MZM",
        palette = MzmPalette,
        about = listOf(
            "All 100 items in 100% route order, and by area: 14 upgrades, 12 Energy Tanks, 50 Missile, 15 Super Missile and 9 Power Bomb Tanks.",
            "Upgrade route, bosses, Shinespark and other techniques, and hints. Covers Normal and Hard.",
        ),
        checklist = MzmItems.all.map { CheckItem(it.id, it.kind.color) },
        celebrationTitle = "100% items",
        celebrationMessage = "Every upgrade and every tank on Zebes.\nSee you next mission.",
        tabs = listOf(
            GameTab("Route", MZM_OVERVIEW_KEY) { ctx ->
                val progress = ctx.progress
                val items = MzmRoute.items
                var menuOpen by remember { mutableStateOf(false) }
                // Bumped after every jump or cancel so the list takes focus back.
                var jumps by remember { mutableIntStateOf(0) }

                fun jumpTo(leg: Leg) {
                    ctx.onSelect(leg.itemIds.first())
                    menuOpen = false
                    jumps++
                }

                val rows = listOf(
                    MasterItem(MZM_OVERVIEW_KEY, "Progress", "${progress.countDone(items.map { it.id })} of ${items.size} items"),
                    MasterItem(MZM_JUMP_KEY, "Jump to trip", "A or Select", expanded = false),
                ) + items.map { item ->
                    val leg = MzmRoute.legOf.getValue(item.id)
                    MasterItem(
                        item.id, item.name, "${item.area.label} · ${itemSubtitle(item)}",
                        cells = listOf(item.kind.color) to listOf(progress.isDone(item.id)),
                        groupHeader = if (leg.itemIds.first() == item.id) "${MzmRoute.legs.indexOf(leg) + 1}. ${leg.title}" else null,
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
                        onActivate = { k -> if (k == MZM_JUMP_KEY) menuOpen = true },
                        footer = GUIDE_FOOTER + "\nSelect jumps to a trip.",
                        focusRequestKey = jumps.takeIf { it > 0 },
                    ) { k ->
                        when (k) {
                            MZM_OVERVIEW_KEY -> RouteOverviewDetail(progress)
                            MZM_JUMP_KEY -> LegJumpDetail(progress) { jumpTo(it) }
                            else -> ItemDetail(MzmItems.byId.getValue(k), progress)
                        }
                    }
                    if (menuOpen) {
                        ListMenu(
                            title = "Jump to trip",
                            options = MzmRoute.legs.mapIndexed { i, leg -> MenuOption(leg.id, "${i + 1}. ${leg.title}", legProgress(leg, progress)) },
                            currentId = MzmRoute.legOf[ctx.selectedKey]?.id,
                            onPick = { id -> jumpTo(MzmRoute.legs.first { it.id == id }) },
                            onCancel = {
                                menuOpen = false
                                jumps++
                            },
                        )
                    }
                }
            },

            GameTab("Areas", MZM_OVERVIEW_KEY) { ctx ->
                val progress = ctx.progress
                val items = MzmItems.all
                var menuOpen by remember { mutableStateOf(false) }
                // Bumped after every jump or cancel so the list takes focus back.
                var jumps by remember { mutableIntStateOf(0) }

                fun jumpTo(area: Area) {
                    ctx.onSelect(items.first { it.area == area }.id)
                    menuOpen = false
                    jumps++
                }

                val rows = listOf(
                    MasterItem(MZM_OVERVIEW_KEY, "Progress", "${progress.countDone(items.map { it.id })} of ${items.size} items"),
                    MasterItem(MZM_JUMP_KEY, "Jump to area", "A or Select", expanded = false),
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
                        onActivate = { k -> if (k == MZM_JUMP_KEY) menuOpen = true },
                        footer = GUIDE_FOOTER + "\nSelect jumps to an area.",
                        focusRequestKey = jumps.takeIf { it > 0 },
                    ) { k ->
                        when (k) {
                            MZM_OVERVIEW_KEY -> MzmOverviewDetail(progress)
                            MZM_JUMP_KEY -> AreaJumpDetail(progress) { jumpTo(it) }
                            else -> ItemDetail(MzmItems.byId.getValue(k), progress)
                        }
                    }
                    if (menuOpen) {
                        ListMenu(
                            title = "Jump to area",
                            options = Area.entries.map { MenuOption(it.name, it.label, areaProgress(it, progress)) },
                            currentId = MzmItems.byId[ctx.selectedKey]?.area?.name,
                            onPick = { jumpTo(Area.valueOf(it)) },
                            onCancel = {
                                menuOpen = false
                                jumps++
                            },
                        )
                    }
                }
            },

            GameTab("Upgrades", MzmPages.upgradeOrder.first()) { ctx ->
                val ups = MzmPages.upgradeOrder.map { MzmItems.byId.getValue(it) }
                MasterDetail(
                    items = ups.mapIndexed { i, u ->
                        MasterItem(
                            u.id, "${i + 1}. ${u.name}", u.area.label,
                            cells = listOf(u.kind.color) to listOf(ctx.progress.isDone(u.id)),
                        )
                    },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> UpgradeDetail(MzmItems.byId.getValue(k), ctx.progress) }
            },

            GameTab("Bosses", MzmPages.bosses.first().id) { ctx ->
                val bosses = MzmPages.bosses
                MasterDetail(
                    items = bosses.map { MasterItem(it.id, it.name, it.area.label) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> BossDetail(bosses.first { it.id == k }) }
            },

            GameTab("Techniques", MzmPages.techniques.first().id) { ctx ->
                val pages = MzmPages.techniques
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> MzmPageDetail(pages.first { it.id == k }) }
            },

            GameTab("Hints", MzmPages.hints.first().id) { ctx ->
                val pages = MzmPages.hints
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> MzmPageDetail(pages.first { it.id == k }) }
            },
        ),
    )
}
