package com.retroreadme.games.smw

import com.retroreadme.core.CheckItem
import com.retroreadme.core.Game
import com.retroreadme.core.GameTab
import com.retroreadme.core.Platform
import com.retroreadme.ui.MasterDetail
import com.retroreadme.ui.MasterItem

/** Super Mario World: level guides organised around the 96 exits. */
object SmwGame {
    val game = Game(
        id = "smw",
        title = "Super Mario World",
        platform = Platform.SNES,
        badge = "SMW",
        palette = SmwPalette,
        about = listOf(
            "World-by-world guides to all 96 exits: 72 normal and 24 secret, with a checklist.",
            "Switch Palaces, Star World, the Special Zone, Yoshi and Cape locations, and hints.",
        ),
        checklist = SmwLevels.allExits.map { CheckItem(it.id, it.kind.color()) },
        celebrationTitle = "All 96 exits",
        celebrationMessage = "Every normal and secret exit in Dinosaur Land.\nYour save file has earned its star.",
        tabs = listOf(
            GameTab("Worlds", SMW_OVERVIEW_KEY) { ctx ->
                val progress = ctx.progress
                val levels = SmwLevels.all
                val total = SmwLevels.allExits.map { it.id }
                val items = listOf(
                    MasterItem(SMW_OVERVIEW_KEY, "Progress", "${progress.countDone(total)} of ${total.size} exits"),
                ) + levels.mapIndexed { i, level ->
                    MasterItem(
                        level.id, level.name, levelSubtitle(level),
                        cells = levelCells(level, progress).takeIf { level.exits.isNotEmpty() },
                        groupHeader = if (i == 0 || levels[i - 1].world != level.world) level.world.label else null,
                    )
                }
                MasterDetail(
                    items = items, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    if (k == SMW_OVERVIEW_KEY) SmwOverviewDetail(progress)
                    else LevelDetail(SmwLevels.byId.getValue(k), progress)
                }
            },

            GameTab("Secret exits", SmwLevels.secretExits.first().second.id) { ctx ->
                val secrets = SmwLevels.secretExits
                val items = secrets.mapIndexed { i, (level, exit) ->
                    MasterItem(
                        exit.id, level.name, "To ${exit.info.leadsTo}",
                        cells = listOf(SmwColors.SecretExit) to listOf(ctx.progress.isDone(exit.id)),
                        groupHeader = if (i == 0 || secrets[i - 1].first.world != level.world) level.world.label else null,
                    )
                }
                MasterDetail(
                    items = items, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    val (level, exit) = secrets.first { it.second.id == k }
                    SecretExitDetail(level, exit, ctx.progress)
                }
            },

            GameTab("Switch Palaces", SmwPages.palaces.first().id) { ctx ->
                val palaces = SmwPages.palaces
                val items = palaces.map { p ->
                    val exitIds = SmwLevels.byId.getValue(p.levelId).exits.map { it.id }
                    MasterItem(
                        p.id, p.name, p.openedBy,
                        cells = listOf(p.color) to listOf(ctx.progress.countDone(exitIds) == exitIds.size),
                    )
                }
                MasterDetail(
                    items = items, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> PalaceDetail(palaces.first { it.id == k }, ctx.progress) }
            },

            GameTab("Yoshi & Capes", SmwPages.yoshiAndCapes.first().id) { ctx ->
                val pages = SmwPages.yoshiAndCapes
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> PageDetail(pages.first { it.id == k }) }
            },

            GameTab("Hints", SmwPages.hints.first().id) { ctx ->
                val pages = SmwPages.hints
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> PageDetail(pages.first { it.id == k }) }
            },
        ),
    )
}
