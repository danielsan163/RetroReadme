package com.retroreadme.games.kdl3

import com.retroreadme.core.CheckItem
import com.retroreadme.core.Game
import com.retroreadme.core.GameTab
import com.retroreadme.core.Platform
import com.retroreadme.ui.MasterDetail
import com.retroreadme.ui.MasterItem

/** Kirby's Dream Land 3: 30 Heart Stars plus the four extra 100% items (34). */
object Kdl3Game {
    private const val EXTRAS_HEADER = "Hyper Zone & extras"

    val game = Game(
        id = "kdl3",
        title = "Kirby's Dream Land 3",
        platform = Platform.SNES,
        badge = "KDL3",
        palette = Kdl3Palette,
        about = listOf(
            "All 30 Heart Stars, stage by stage, plus Zero, MG-5, Boss Butch and Jumping for 100%.",
            "Which Animal Friends and Copy Abilities each Heart Star needs, boss strategies, and sub-game tips.",
        ),
        checklist = Kdl3Stages.all.map { CheckItem(it.heartId, Kdl3Colors.HeartGold) } +
            Kdl3Stages.extras.map { CheckItem(it.id, Kdl3Colors.Pink) },
        celebrationTitle = "100% complete",
        celebrationMessage = "Every Heart Star, Zero, MG-5, Boss Butch and Jumping.\nPopstar is safe, and so is your save file.",
        tabs = listOf(
            GameTab("Worlds", KDL3_OVERVIEW_KEY) { ctx ->
                val p = ctx.progress
                val stages = Kdl3Stages.all
                val items = listOf(
                    MasterItem(KDL3_OVERVIEW_KEY, "Progress", "${p.countDone(kdl3ChecklistIds)} of ${kdl3ChecklistIds.size}"),
                ) + stages.mapIndexed { i, s ->
                    MasterItem(
                        s.id, s.name, stageNeeds(s) ?: s.task,
                        cells = listOf(Kdl3Colors.HeartGold) to listOf(p.isDone(s.heartId)),
                        groupHeader = if (i == 0 || stages[i - 1].world != s.world) s.world.label else null,
                    )
                } + Kdl3Stages.extras.mapIndexed { i, e ->
                    MasterItem(
                        e.id, e.title, e.subtitle,
                        cells = listOf(Kdl3Colors.Pink) to listOf(p.isDone(e.id)),
                        groupHeader = if (i == 0) EXTRAS_HEADER else null,
                    )
                }
                MasterDetail(
                    items = items, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    when {
                        k == KDL3_OVERVIEW_KEY -> Kdl3OverviewDetail(p)
                        Kdl3Stages.byId.containsKey(k) -> StageDetail(Kdl3Stages.byId.getValue(k), p)
                        else -> ExtraDetail(Kdl3Stages.extrasById.getValue(k), p)
                    }
                }
            },

            GameTab("Friends & Abilities", "f_" + Friend.entries.first().name) { ctx ->
                val items = Friend.entries.mapIndexed { i, f ->
                    MasterItem("f_" + f.name, f.label, null, groupHeader = if (i == 0) "Animal Friends" else null)
                } + Ability.entries.mapIndexed { i, a ->
                    MasterItem("a_" + a.name, a.label, null, groupHeader = if (i == 0) "Copy Abilities" else null)
                }
                MasterDetail(
                    items = items, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    if (k.startsWith("f_")) FriendDetail(Friend.valueOf(k.removePrefix("f_")), ctx.progress)
                    else AbilityDetail(Ability.valueOf(k.removePrefix("a_")), ctx.progress)
                }
            },

            GameTab("Bosses", Kdl3Pages.bosses.first().id) { ctx ->
                val bosses = Kdl3Pages.bosses
                MasterDetail(
                    items = bosses.map { MasterItem(it.id, it.name, it.where) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> BossDetail(bosses.first { it.id == k }) }
            },

            GameTab("Sub-games", Kdl3Pages.subGames.first().id) { ctx ->
                val pages = Kdl3Pages.subGames
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> Kdl3PageDetail(pages.first { it.id == k }) }
            },

            GameTab("Hints", Kdl3Pages.hints.first().id) { ctx ->
                val pages = Kdl3Pages.hints
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> Kdl3PageDetail(pages.first { it.id == k }) }
            },
        ),
    )
}
