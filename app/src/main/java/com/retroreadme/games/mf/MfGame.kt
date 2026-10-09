package com.retroreadme.games.mf

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
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
import com.retroreadme.core.TabContext
import com.retroreadme.ui.GUIDE_FOOTER
import com.retroreadme.ui.ListMenu
import com.retroreadme.ui.MasterDetail
import com.retroreadme.ui.MasterItem
import com.retroreadme.ui.MenuOption
import android.view.KeyEvent as AndroidKeyEvent

/** Metroid Fusion: all 100 items in route order and by sector, with sector maps. */
object MfGame {
    val game = Game(
        id = "mf",
        title = "Metroid Fusion",
        platform = Platform.GBA,
        badge = "MF",
        palette = MfPalette,
        about = listOf(
            "All 100 items in 100% route order, and by sector: 48 Missile, 20 Energy and 32 Power Bomb Tanks, with maps.",
            "Every ability and where it comes from, boss strategies, and hints. No sequence breaks.",
        ),
        checklist = MfItems.all.map { CheckItem(it.id, it.kind.color) },
        celebrationTitle = "100% items",
        celebrationMessage = "Every tank on the B.S.L. station.\nNow get off it before it hits SR388.",
        tabs = listOf(
            GameTab("Route", MF_OVERVIEW_KEY) { ctx ->
                JumpList(
                    ctx = ctx,
                    items = MfRoute.items,
                    overviewTitle = "Progress",
                    jumpTitle = "Jump to trip",
                    footerHint = "Select jumps to a trip.",
                    subtitle = { "${it.sector.short} · ${itemSubtitle(it)}" },
                    group = { item -> MfRoute.legOf.getValue(item.id).let { leg -> (leg.itemIds.first() == item.id) to "${MfRoute.legs.indexOf(leg) + 1}. ${leg.title}" } },
                    options = { MfRoute.legs.mapIndexed { i, leg -> MenuOption(leg.id, "${i + 1}. ${leg.title}", legProgress(leg, ctx.progress)) } },
                    currentOption = { key -> MfRoute.legOf[key]?.id },
                    firstOf = { id -> MfRoute.legs.first { it.id == id }.itemIds.first() },
                    overview = { RouteOverviewDetail(ctx.progress) },
                    jumpPage = { jump -> LegJumpDetail(ctx.progress) { jump(it.id) } },
                )
            },

            GameTab("Sectors", MF_OVERVIEW_KEY) { ctx ->
                JumpList(
                    ctx = ctx,
                    items = MfItems.all,
                    overviewTitle = "Progress",
                    jumpTitle = "Jump to sector",
                    footerHint = "Select jumps to a sector.",
                    subtitle = { itemSubtitle(it) },
                    group = { item ->
                        val i = MfItems.all.indexOf(item)
                        (i == 0 || MfItems.all[i - 1].sector != item.sector) to item.sector.label
                    },
                    options = { Sector.entries.map { MenuOption(it.name, it.label, sectorProgress(it, ctx.progress)) } },
                    currentOption = { key -> MfItems.byId[key]?.sector?.name },
                    firstOf = { name -> MfItems.all.first { it.sector.name == name }.id },
                    overview = { SectorsOverviewDetail(ctx.progress) },
                    jumpPage = { jump -> SectorJumpDetail(ctx.progress) { jump(it.name) } },
                )
            },

            GameTab("Abilities", MfPages.abilities.first().id) { ctx ->
                val abilities = MfPages.abilities
                MasterDetail(
                    items = abilities.mapIndexed { i, a -> MasterItem(a.id, "${i + 1}. ${a.name}", "${a.sector.short} · ${a.from}") },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    val i = abilities.indexOfFirst { it.id == k }
                    AbilityDetail(abilities[i], i + 1)
                }
            },

            GameTab("Bosses", MfPages.bosses.first().id) { ctx ->
                val bosses = MfPages.bosses
                MasterDetail(
                    items = bosses.map { MasterItem(it.id, it.name, it.sector.label) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> BossDetail(bosses.first { it.id == k }) }
            },

            GameTab("Hints", MfPages.hints.first().id) { ctx ->
                val pages = MfPages.hints
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> MfPageDetail(pages.first { it.id == k }) }
            },
        ),
    )
}

/**
 * A list of items with a Progress row, a "Jump to..." row and drop-down (A or Select), and
 * group headings. Shared by the Route and Sectors tabs.
 */
@Composable
private fun JumpList(
    ctx: TabContext,
    items: List<Item>,
    overviewTitle: String,
    jumpTitle: String,
    footerHint: String,
    subtitle: (Item) -> String,
    /** (starts a new group?, group heading) for each item. */
    group: (Item) -> Pair<Boolean, String>,
    options: () -> List<MenuOption>,
    currentOption: (String) -> String?,
    firstOf: (String) -> String,
    overview: @Composable () -> Unit,
    jumpPage: @Composable ((String) -> Unit) -> Unit,
) {
    val progress = ctx.progress
    var menuOpen by remember { mutableStateOf(false) }
    // Bumped after every jump or cancel so the list takes focus back.
    var jumps by remember { mutableIntStateOf(0) }

    fun jumpTo(option: String) {
        ctx.onSelect(firstOf(option))
        menuOpen = false
        jumps++
    }

    val rows = listOf(
        MasterItem(MF_OVERVIEW_KEY, overviewTitle, "${progress.countDone(items.map { it.id })} of ${items.size} items"),
        MasterItem(MF_JUMP_KEY, jumpTitle, "A or Select", expanded = false),
    ) + items.map { item ->
        val (starts, heading) = group(item)
        MasterItem(
            item.id, item.name, subtitle(item),
            cells = listOf(item.kind.color) to listOf(progress.isDone(item.id)),
            groupHeader = heading.takeIf { starts },
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
            onActivate = { k -> if (k == MF_JUMP_KEY) menuOpen = true },
            footer = "$GUIDE_FOOTER\n$footerHint",
            focusRequestKey = jumps.takeIf { it > 0 },
        ) { k ->
            when (k) {
                MF_OVERVIEW_KEY -> overview()
                MF_JUMP_KEY -> jumpPage { jumpTo(it) }
                else -> ItemDetail(MfItems.byId.getValue(k), progress)
            }
        }
        if (menuOpen) {
            ListMenu(
                title = jumpTitle,
                options = options(),
                currentId = currentOption(ctx.selectedKey),
                onPick = { jumpTo(it) },
                onCancel = {
                    menuOpen = false
                    jumps++
                },
            )
        }
    }
}
