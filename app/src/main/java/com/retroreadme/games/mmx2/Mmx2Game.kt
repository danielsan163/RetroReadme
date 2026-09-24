package com.retroreadme.games.mmx2

import androidx.compose.runtime.remember
import com.retroreadme.core.CheckItem
import com.retroreadme.core.Game
import com.retroreadme.core.GameTab
import com.retroreadme.core.Platform
import com.retroreadme.ui.MasterDetail
import com.retroreadme.ui.MasterItem

/** Mega Man X2: the same five tabs the standalone MMX2 Guide had. */
object Mmx2Game {
    val game = Game(
        id = "mmx2",
        title = "Mega Man X2",
        platform = Platform.SNES,
        badge = "X2",
        palette = Mmx2Palette,
        about = listOf(
            "Boss order and weaknesses, and every Heart Tank, Sub Tank and armor part with a checklist.",
            "Boss and mini-boss strategies, weapon details and charging behavior, and hints.",
        ),
        checklist = GuideData.allPowerUps.map { CheckItem(it.id, it.type.color()) },
        celebrationTitle = "Fully upgraded",
        celebrationMessage = "Every Heart Tank, Sub Tank and armor part, the Shoryuken, and all three of Zero's parts.\nGo and ruin Sigma's day.",
        tabs = listOf(
            GameTab("Boss order", GuideData.orderPages.first().id) { ctx ->
                MasterDetail(
                    items = GuideData.orderPages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> InfoPageDetail(GuideData.orderPages.first { it.id == k }) }
            },

            GameTab("Power-ups", OVERVIEW_KEY) { ctx ->
                val progress = ctx.progress
                val allIds = remember { GuideData.allPowerUps.map { it.id } }
                val items = listOf(
                    MasterItem(OVERVIEW_KEY, "Collection", "${progress.countDone(allIds)} of ${allIds.size} found"),
                ) + GuideData.stages.map { st ->
                    MasterItem(st.id, st.maverick, st.area, cells = stageCells(st, progress))
                }
                MasterDetail(
                    items = items, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    if (k == OVERVIEW_KEY) OverviewDetail(progress)
                    else StageDetail(GuideData.stages.first { it.id == k }, progress)
                }
            },

            GameTab("Bosses", GuideData.bosses.first().id) { ctx ->
                // Categories keep their enum order; names are alphabetical inside each.
                val bosses = remember {
                    GuideData.bosses.sortedWith(compareBy({ it.category.ordinal }, { it.name }))
                }
                val items = bosses.mapIndexed { i, b ->
                    MasterItem(
                        b.id, b.name, "Weak to ${b.weakness}",
                        groupHeader = if (i == 0 || bosses[i - 1].category != b.category) b.category.label else null,
                    )
                }
                MasterDetail(
                    items = items, selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> BossDetail(bosses.first { it.id == k }) }
            },

            GameTab("Weapons", WeaponData.all.first().id) { ctx ->
                MasterDetail(
                    items = WeaponData.all.map { MasterItem(it.id, it.name, it.source) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> WeaponDetail(WeaponData.all.first { it.id == k }) }
            },

            GameTab("Hints", GuideData.secrets.first().id) { ctx ->
                MasterDetail(
                    items = GuideData.secrets.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> InfoPageDetail(GuideData.secrets.first { it.id == k }) }
            },
        ),
    )
}
