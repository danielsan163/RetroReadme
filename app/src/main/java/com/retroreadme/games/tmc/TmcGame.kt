package com.retroreadme.games.tmc

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

/**
 * The Legend of Zelda: The Minish Cap: a short "where next" walkthrough, and the 100% checklist
 * of Heart Pieces, Kinstone fusions, bottles and upgrades.
 */
object TmcGame {
    val game = Game(
        id = "tmc",
        title = "The Legend of Zelda: The Minish Cap",
        platform = Platform.GBA,
        badge = "TMC",
        palette = TmcPalette,
        about = listOf(
            "A walkthrough of short \"where next\" pointers you can check off, for when you're not sure where to go.",
            "All 44 Heart Pieces, all 100 Kinstone fusions, and the bottles and upgrades; plus bosses and the one thing you can lose for good.",
        ),
        checklist = TmcData.hearts.map { CheckItem(it.id, TmcColors.Heart) } +
            TmcFusions.all.map { CheckItem(it.id, it.color.color) } +
            TmcData.upgrades.map { CheckItem(it.id, TmcColors.Upgrade) },
        celebrationTitle = "100%",
        celebrationMessage = "Every Heart Piece, every fusion, every upgrade.\nEzlo would never admit he's impressed.",
        tabs = listOf(
            GameTab("Walkthrough", TMC_OVERVIEW_KEY) { ctx ->
                val steps = TmcData.steps
                JumpList(
                    ctx = ctx,
                    overview = MasterItem(TMC_OVERVIEW_KEY, "Where now", "${countLine(steps.map { it.id }, ctx.progress)} steps"),
                    jumpTitle = "Jump to chapter",
                    footerHint = "Select jumps to a chapter.",
                    rows = steps.mapIndexed { i, s ->
                        MasterItem(
                            s.id, s.text, null,
                            cells = listOf(TmcColors.Step) to listOf(ctx.progress.isDone(s.id)),
                            groupHeader = if (i == 0 || steps[i - 1].chapter != s.chapter) "${TmcData.chapters.indexOf(s.chapter) + 1}. ${s.chapter}" else null,
                        )
                    },
                    options = TmcData.chapters.mapIndexed { i, c ->
                        val ids = steps.filter { it.chapter == c }.map { it.id }
                        MenuOption(c, "${i + 1}. $c", countLine(ids, ctx.progress))
                    },
                    currentOption = { key -> TmcData.stepById[key]?.chapter },
                    firstOf = { c -> steps.first { it.chapter == c }.id },
                    overviewPage = { WalkthroughOverview(ctx.progress) },
                ) { k -> StepDetail(TmcData.stepById.getValue(k), ctx.progress) }
            },

            GameTab("Heart Pieces", TMC_OVERVIEW_KEY) { ctx ->
                val hearts = TmcData.hearts
                JumpList(
                    ctx = ctx,
                    overview = MasterItem(TMC_OVERVIEW_KEY, "Progress", "${countLine(hearts.map { it.id }, ctx.progress)} Heart Pieces"),
                    jumpTitle = "Jump to area",
                    footerHint = "Select jumps to an area.",
                    rows = hearts.mapIndexed { i, h ->
                        MasterItem(
                            h.id, heartTitle(h), h.needs.ifEmpty { listOf("Nothing special") }.joinToString(", "),
                            cells = listOf(TmcColors.Heart) to listOf(ctx.progress.isDone(h.id)),
                            groupHeader = if (i == 0 || hearts[i - 1].area != h.area) h.area else null,
                        )
                    },
                    options = TmcData.heartAreas.map { a -> MenuOption(a, a, countLine(hearts.filter { it.area == a }.map { it.id }, ctx.progress)) },
                    currentOption = { key -> TmcData.heartById[key]?.area },
                    firstOf = { a -> hearts.first { it.area == a }.id },
                    overviewPage = {
                        ChecklistOverview(
                            "Heart Pieces", "All 44, by area. Press A on one to check it off.", ctx.progress,
                            TmcData.heartAreas.map { a -> a to hearts.filter { it.area == a }.map { it.id to TmcColors.Heart } },
                        )
                    },
                ) { k -> HeartDetail(TmcData.heartById.getValue(k), ctx.progress) }
            },

            GameTab("Kinstones", TMC_OVERVIEW_KEY) { ctx ->
                val fusions = TmcFusions.all
                JumpList(
                    ctx = ctx,
                    overview = MasterItem(TMC_OVERVIEW_KEY, "Progress", "${countLine(fusions.map { it.id }, ctx.progress)} fusions"),
                    jumpTitle = "Jump to stage",
                    footerHint = "Select jumps to a story stage.",
                    rows = fusions.mapIndexed { i, f ->
                        MasterItem(
                            f.id, fusionTitle(f), if (f.random) f.result else "${f.location} · ${f.color.label}",
                            cells = listOf(f.color.color) to listOf(ctx.progress.isDone(f.id)),
                            groupHeader = if (i == 0 || fusions[i - 1].stage != f.stage) TmcFusions.stages[f.stage - 1] else null,
                        )
                    },
                    options = TmcFusions.stages.mapIndexed { i, label ->
                        MenuOption("${i + 1}", label, countLine(fusions.filter { it.stage == i + 1 }.map { it.id }, ctx.progress))
                    },
                    currentOption = { key -> TmcFusions.byId[key]?.stage?.toString() },
                    firstOf = { stage -> fusions.first { it.stage == stage.toInt() }.id },
                    overviewPage = {
                        ChecklistOverview(
                            "Kinstone fusions", "All 100, by the story stage they open at. Press A on one to check it off.", ctx.progress,
                            TmcFusions.stages.mapIndexed { i, label -> label to fusions.filter { it.stage == i + 1 }.map { it.id to it.color.color } },
                        )
                    },
                ) { k -> FusionDetail(TmcFusions.byId.getValue(k), ctx.progress) }
            },

            GameTab("Upgrades", TMC_OVERVIEW_KEY) { ctx ->
                val ups = TmcData.upgrades
                MasterDetail(
                    items = listOf(MasterItem(TMC_OVERVIEW_KEY, "Progress", "${countLine(ups.map { it.id }, ctx.progress)} upgrades")) +
                        ups.mapIndexed { i, u ->
                            MasterItem(
                                u.id, u.name, u.area,
                                cells = listOf(TmcColors.Upgrade) to listOf(ctx.progress.isDone(u.id)),
                                groupHeader = if (i == 0 || ups[i - 1].kind != u.kind) u.kind.label else null,
                            )
                        },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    if (k == TMC_OVERVIEW_KEY) {
                        ChecklistOverview(
                            "Bottles and upgrades", "Four bottles, three of each capacity upgrade, and four item upgrades.", ctx.progress,
                            UpgradeKind.entries.map { kind -> kind.label to ups.filter { it.kind == kind }.map { it.id to TmcColors.Upgrade } },
                        )
                    } else {
                        UpgradeDetail(TmcData.upgradeById.getValue(k), ctx.progress)
                    }
                }
            },

            GameTab("Bosses", TmcPages.bosses.first().id) { ctx ->
                val bosses = TmcPages.bosses
                MasterDetail(
                    items = bosses.map { MasterItem(it.id, it.name, it.dungeon) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> BossDetail(bosses.first { it.id == k }) }
            },

            GameTab("Hints", TmcPages.hints.first().id) { ctx ->
                val pages = TmcPages.hints
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> TmcPageDetail(pages.first { it.id == k }) }
            },
        ),
    )
}

/**
 * A list with an overview row, a "Jump to..." row and drop-down (A or Select), then [rows]
 * under group headings. Shared by the Walkthrough, Heart Pieces and Kinstones tabs.
 */
@Composable
private fun JumpList(
    ctx: TabContext,
    overview: MasterItem,
    jumpTitle: String,
    footerHint: String,
    rows: List<MasterItem>,
    options: List<MenuOption>,
    currentOption: (String) -> String?,
    firstOf: (String) -> String,
    overviewPage: @Composable () -> Unit,
    detail: @Composable (String) -> Unit,
) {
    var menuOpen by remember { mutableStateOf(false) }
    // Bumped after every jump or cancel so the list takes focus back.
    var jumps by remember { mutableIntStateOf(0) }

    fun jumpTo(option: String) {
        ctx.onSelect(firstOf(option))
        menuOpen = false
        jumps++
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
            items = listOf(overview, MasterItem(TMC_JUMP_KEY, jumpTitle, "A or Select", expanded = false)) + rows,
            selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
            detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
            onActivate = { k -> if (k == TMC_JUMP_KEY) menuOpen = true },
            footer = "$GUIDE_FOOTER\n$footerHint",
            focusRequestKey = jumps.takeIf { it > 0 },
        ) { k ->
            when (k) {
                overview.key -> overviewPage()
                TMC_JUMP_KEY -> JumpDetail(jumpTitle, options.map { it.id to it.label }) { jumpTo(it) }
                else -> detail(k)
            }
        }
        if (menuOpen) {
            ListMenu(
                title = jumpTitle,
                options = options,
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
