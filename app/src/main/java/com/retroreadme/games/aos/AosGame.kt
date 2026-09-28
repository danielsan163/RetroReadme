package com.retroreadme.games.aos

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

/** Castlevania: Aria of Sorrow: every soul, the key-soul route, endings and hints. */
object AosGame {
    val game = Game(
        id = "aos",
        title = "Castlevania: Aria of Sorrow",
        platform = Platform.GBA,
        badge = "AoS",
        palette = AosPalette,
        about = listOf(
            "All 120 souls: what each does, where the enemy lives, and the best room to farm it.",
            "The key-soul route, souls by area, what the endings take, and farming tips.",
        ),
        checklist = AosSouls.all.map { CheckItem(it.id, it.type.tint) },
        celebrationTitle = "Every soul",
        celebrationMessage = "All 120 souls absorbed.\nThe Chaos Ring is waiting in the Chaotic Realm.",
        tabs = listOf(
            GameTab("Souls", AOS_OVERVIEW_KEY) { ctx ->
                val progress = ctx.progress
                val souls = AosSouls.all
                var menuOpen by remember { mutableStateOf(false) }
                // Bumped after every jump or cancel so the list takes focus back.
                var jumps by remember { mutableIntStateOf(0) }

                fun jumpTo(type: SoulType) {
                    ctx.onSelect(souls.first { it.type == type }.id)
                    menuOpen = false
                    jumps++
                }

                val rows = listOf(
                    MasterItem(AOS_OVERVIEW_KEY, "Progress", "${progress.countDone(souls.map { it.id })} of ${souls.size} souls"),
                    MasterItem(AOS_JUMP_KEY, "Jump to soul type", "A or Select", expanded = false),
                ) + souls.mapIndexed { i, s ->
                    MasterItem(
                        s.id, s.name, if (s.fromHolder) "Soul holder · ${s.areas}" else s.areas,
                        cells = listOf(s.type.tint) to listOf(progress.isDone(s.id)),
                        groupHeader = if (i == 0 || souls[i - 1].type != s.type) s.type.label else null,
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
                        onActivate = { k -> if (k == AOS_JUMP_KEY) menuOpen = true },
                        footer = GUIDE_FOOTER + "\nSelect jumps to a soul type.",
                        focusRequestKey = jumps.takeIf { it > 0 },
                    ) { k ->
                        when (k) {
                            AOS_OVERVIEW_KEY -> AosOverviewDetail(progress)
                            AOS_JUMP_KEY -> TypeJumpDetail(progress) { jumpTo(it) }
                            else -> SoulDetail(AosSouls.byId.getValue(k), progress)
                        }
                    }
                    if (menuOpen) {
                        ListMenu(
                            title = "Jump to soul type",
                            options = SoulType.entries.map { MenuOption(it.name, it.label, typeProgress(it, progress)) },
                            currentId = AosSouls.byId[ctx.selectedKey]?.type?.name,
                            onPick = { jumpTo(SoulType.valueOf(it)) },
                            onCancel = {
                                menuOpen = false
                                jumps++
                            },
                        )
                    }
                }
            },

            GameTab("Areas", AosPages.areas.first()) { ctx ->
                MasterDetail(
                    items = AosPages.areas.map { a ->
                        val ids = soulsIn(a).map { it.id }
                        MasterItem(a, a, "${ctx.progress.countDone(ids)} of ${ids.size} souls")
                    },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> AreaDetail(k, ctx.progress) }
            },

            GameTab("Route", AosPages.route.first().first) { ctx ->
                val steps = AosPages.route
                MasterDetail(
                    items = steps.mapIndexed { i, (id, _) ->
                        val s = AosSouls.byId.getValue(id)
                        MasterItem(id, "${i + 1}. ${s.name}", s.areas, cells = listOf(s.type.tint) to listOf(ctx.progress.isDone(id)))
                    },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k ->
                    val i = steps.indexOfFirst { it.first == k }
                    RouteDetail(AosSouls.byId.getValue(k), steps[i].second, i + 1, ctx.progress)
                }
            },

            GameTab("Endings", AosPages.endings.first().id) { ctx ->
                val pages = AosPages.endings
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> AosPageDetail(pages.first { it.id == k }) }
            },

            GameTab("Hints", AosPages.hints.first().id) { ctx ->
                val pages = AosPages.hints
                MasterDetail(
                    items = pages.map { MasterItem(it.id, it.title, it.subtitle) },
                    selectedKey = ctx.selectedKey, onSelect = ctx.onSelect,
                    detailScroll = ctx.detailScroll, onDetailViewport = ctx.onDetailViewport,
                ) { k -> AosPageDetail(pages.first { it.id == k }) }
            },
        ),
    )
}
