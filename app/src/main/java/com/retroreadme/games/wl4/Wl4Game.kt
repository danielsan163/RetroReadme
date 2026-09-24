package com.retroreadme.games.wl4

import com.retroreadme.core.CheckItem
import com.retroreadme.core.Game
import com.retroreadme.core.GameTab
import com.retroreadme.core.Platform
import com.retroreadme.ui.MasterDetail
import com.retroreadme.ui.MasterItem

/**
 * Wario Land 4, as two guides (Normal and Hard) sharing one set of screens.
 * Each has its own checklist of 106: 72 jewel pieces, 16 CDs and 18 Keyzers.
 */
object Wl4Game {
    val normal: Game = build(Mode.NORMAL)
    val hard: Game = build(Mode.HARD)

    private fun build(mode: Mode) = Game(
        id = if (mode == Mode.NORMAL) "wl4" else "wl4_hard",
        title = if (mode == Mode.NORMAL) "Wario Land 4" else "Wario Land 4 (Hard)",
        platform = Platform.GBA,
        badge = if (mode == Mode.NORMAL) "WL4" else "WL4 H",
        palette = Wl4Palette,
        about = listOf(
            "Every jewel piece, CD and Keyzer on ${mode.label} mode, with a checklist of 106.",
            "Boss strategies, the Mini-Game and Item Shops, and hints.",
        ),
        checklist = Wl4Levels.allItems(mode).map { CheckItem(it.id, it.color) },
        celebrationTitle = "Treasure hunter",
        celebrationMessage = "Every jewel piece, CD and Keyzer on ${mode.label} mode.\nThe Golden Diva doesn't stand a chance.",
        groupTitle = "Wario Land 4",
        variant = mode.label,
        tabs = listOf(
            GameTab("Passages", WL4_OVERVIEW_KEY) { ctx ->
                val levels = Wl4Levels.all
                val total = Wl4Levels.allItems(mode).map { it.id }
                val items = listOf(
                    MasterItem(WL4_OVERVIEW_KEY, "Collection", "${ctx.progress.countDone(total)} of ${total.size}"),
                ) + levels.mapIndexed { i, level ->
                    MasterItem(
                        level.id, level.name, "Escape ${level.data(mode).escapeTime}",
                        cells = levelCells(level, mode, ctx.progress),
                        groupHeader = if (i == 0 || levels[i - 1].passage != level.passage) level.passage.label else null,
                    )
                }
                MasterDetail(
                    items = items, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    if (k == WL4_OVERVIEW_KEY) Wl4OverviewDetail(mode, ctx.progress)
                    else LevelDetail(Wl4Levels.byId.getValue(k), mode, ctx.progress)
                }
            },

            GameTab("CDs", Wl4Levels.all.first { it.data(mode).cd != null }.id) { ctx ->
                val levels = Wl4Levels.all.filter { it.data(mode).cd != null }
                val items = levels.mapIndexed { i, level ->
                    val cdId = "${level.id}_cd"
                    MasterItem(
                        level.id, level.name, if (ctx.progress.isDone(cdId)) "Collected" else "Not yet",
                        cells = listOf(Wl4Colors.Cd) to listOf(ctx.progress.isDone(cdId)),
                        groupHeader = if (i == 0 || levels[i - 1].passage != level.passage) level.passage.label else null,
                    )
                }
                MasterDetail(
                    items = items, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> CdDetail(Wl4Levels.byId.getValue(k), mode, ctx.progress) }
            },

            GameTab("Bosses", Wl4Pages.bosses.first().id) { ctx ->
                val bosses = Wl4Pages.bosses
                MasterDetail(
                    items = bosses.map { b ->
                        MasterItem(b.id, b.name, "${b.passage.label} · ${if (mode == Mode.NORMAL) b.timeNormal else b.timeHard}")
                    },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> BossDetail(bosses.first { it.id == k }, mode) }
            },

            GameTab("Shop & Items", Wl4Pages.shop.first().id) { ctx ->
                val pages = Wl4Pages.shop
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> Wl4PageDetail(pages.first { it.id == k }) }
            },

            GameTab("Hints", Wl4Pages.hints.first().id) { ctx ->
                val pages = Wl4Pages.hints
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> Wl4PageDetail(pages.first { it.id == k }) }
            },
        ),
    )
}
