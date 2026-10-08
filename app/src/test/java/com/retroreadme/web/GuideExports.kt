package com.retroreadme.web

import com.retroreadme.games.aos.AosPages
import com.retroreadme.games.aos.AosSouls
import com.retroreadme.games.aos.SoulType
import com.retroreadme.games.aos.areaBosses
import com.retroreadme.games.aos.soulsIn
import com.retroreadme.games.kdl3.Ability
import com.retroreadme.games.kdl3.Friend
import com.retroreadme.games.kdl3.Kdl3Colors
import com.retroreadme.games.kdl3.Kdl3Pages
import com.retroreadme.games.kdl3.Kdl3Stages
import com.retroreadme.games.kdl3.kdl3ChecklistIds
import com.retroreadme.games.kdl3.stageNeeds
import com.retroreadme.games.mmx2.BossCategory
import com.retroreadme.games.mmx2.GuideData
import com.retroreadme.games.mmx2.ItemType
import com.retroreadme.games.mmx2.Mmx2Colors
import com.retroreadme.games.mmx2.WeaponData
import com.retroreadme.games.mmx2.color
import com.retroreadme.games.mzm.Area
import com.retroreadme.games.mzm.Kind
import com.retroreadme.games.mzm.MzmItems
import com.retroreadme.games.mzm.MzmMaps
import com.retroreadme.games.mzm.MzmPages
import com.retroreadme.games.mzm.MzmRoute
import com.retroreadme.games.mzm.itemSubtitle
import com.retroreadme.games.smw.ExitKind
import com.retroreadme.games.smw.LevelType
import com.retroreadme.games.smw.SmwColors
import com.retroreadme.games.smw.SmwLevels
import com.retroreadme.games.smw.SmwPages
import com.retroreadme.games.smw.World
import com.retroreadme.games.smw.levelSubtitle
import com.retroreadme.games.wl4.ItemKind
import com.retroreadme.games.wl4.Mode
import com.retroreadme.games.wl4.Passage
import com.retroreadme.games.wl4.Wl4Colors
import com.retroreadme.games.wl4.Wl4Levels
import com.retroreadme.games.wl4.Wl4Pages
import com.retroreadme.games.wl4.items
import com.retroreadme.games.smw.Exit as SmwExit
import com.retroreadme.games.smw.color as smwColor

private const val OVERVIEW = "overview"

private fun pagesTab(title: String, pages: List<Triple<String, String, String>>, sections: (String) -> List<com.retroreadme.core.Section>) =
    Tab(title).apply {
        pages.forEach { (id, t, s) -> row(Row(id, t, s), Page(t, s).apply { sections(id).forEach { section(it) } }) }
    }

// ---------------------------------------------------------------- Mega Man X2

fun mmx2Tabs(): List<Tab> {
    val order = Tab("Boss order").apply {
        GuideData.orderPages.forEach { p ->
            row(Row(p.id, p.title, p.subtitle), Page(p.title, p.subtitle).apply {
                p.route.forEach { step ->
                    panel(if (step.warning != null) WARN else LINE) {
                        big(step.number, step.maverick, step.area, "Use ${step.useWeapon}" to WARN)
                        if (step.collect.isNotEmpty()) lab("Grab", step.collect.joinToString(", "), hex(Mmx2Colors.SubTank))
                        step.later?.let { lab("Not yet", it, MUTED) }
                        step.thenDo.forEach { lab("Then", it, hex(Mmx2Colors.Armor)) }
                        step.warning?.let { lab("Careful", it, WARN) }
                    }
                }
                p.sections.forEach { section(it) }
            })
        }
    }
    val all = GuideData.allPowerUps
    val powerUps = Tab("Power-ups").apply {
        row(Row(OVERVIEW, "Collection", count = all.map { it.id } to "{d} of {n} found"), Page("Collection", "Tap any item inside a stage to check it off.").apply {
            meters(rows = ItemType.entries.mapNotNull { type ->
                val ids = all.filter { it.type == type }.map { it.id }
                if (ids.isEmpty()) null else Meter(type.label + if (ids.size > 1) "s" else "", ids, ids.map { hex(type.color()) })
            })
            panel(hex(Mmx2Colors.Armor)) {
                h("Armor parts", hex(Mmx2Colors.Armor))
                lines(
                    listOf(
                        "Head: Crystal Snail's stage, no requirements.",
                        "Arms: Wheel Gator's stage, needs Legs.",
                        "Body: Morph Moth's stage, needs Spin Wheel.",
                        "Legs: Overdrive Ostrich's stage, needs Spin Wheel.",
                    ),
                    marker = hex(Mmx2Colors.Armor),
                )
            }
            reset()
        })
        GuideData.stages.forEach { st ->
            row(
                Row(st.id, st.maverick, st.area, cells = st.powerUps.map { it.id to hex(it.type.color()) }),
                Page(st.maverick, st.area).apply {
                    tags(*listOfNotNull(st.weakness?.let { "Weak to $it" to WARN }, st.weapon?.let { "Drops $it" to ACCENT }).toTypedArray())
                    st.powerUps.forEach { pu ->
                        val c = hex(pu.type.color())
                        panel(c, check = pu.id) {
                            check(pu.name, "Collected", pu.type.label, pu.type.symbol to c)
                            if (pu.requires.isEmpty()) tags("Nothing needed" to MUTED) else tags(pu.requires.map { "Needs $it" to WARN })
                            lines(pu.steps, numbered = true, marker = c)
                            pu.effect?.let { lab("Effect", it, c) }
                        }
                    }
                    if (st.miniBosses.isNotEmpty()) panel {
                        h("Mini-bosses here")
                        lines(st.miniBosses + "Strategies are in the Bosses tab.")
                    }
                    if (st.notes.isNotEmpty()) panel(WARN) {
                        h("Worth knowing", WARN)
                        lines(st.notes, marker = WARN)
                    }
                    st.xHunterDoor?.let { door ->
                        panel(SECRET) {
                            h("X-Hunter door", SECRET)
                            tags(st.xHunterDoorRequires.map { "Needs $it" to SECRET })
                            x(door)
                            x("It only opens when the stage select map shows a Sigma icon on this stage.", "muted")
                        }
                    }
                },
            )
        }
    }
    val bossList = GuideData.bosses.sortedWith(compareBy({ it.category.ordinal }, { it.name }))
    val bosses = Tab("Bosses").apply {
        bossList.forEachIndexed { i, b ->
            val group = if (i == 0 || bossList[i - 1].category != b.category) b.category.label else null
            row(Row(b.id, b.name, "Weak to ${b.weakness}", group), Page(b.name, b.location).apply {
                tags(*listOfNotNull("Weak to ${b.weakness}" to WARN, b.reward?.let { it to ACCENT }).toTypedArray())
                b.weaponNote?.let { panel(WARN) { h("Weapon notes", WARN); x(it) } }
                panel(hex(Mmx2Colors.Heart)) { h("Attacks", hex(Mmx2Colors.Heart)); lines(b.attacks, marker = hex(Mmx2Colors.Heart)) }
                panel(ACCENT) { h("Strategy"); lines(b.strategy) }
            })
        }
    }
    check(BossCategory.entries.isNotEmpty())
    val weapons = Tab("Weapons").apply {
        WeaponData.all.forEach { w ->
            row(Row(w.id, w.name, w.source), Page(w.name, w.source).apply {
                w.beats?.let { tags("Beats $it" to WARN) }
                panel(ACCENT) { h("Uncharged"); lines(w.uncharged) }
                panel(WARN) {
                    h("Charged", WARN)
                    lines(w.charged, marker = WARN)
                    if (w.needsArmToCharge) x("Charging special weapons needs the Arm parts from Wheel Gator's stage.", "muted")
                }
                if (w.uses.isNotEmpty()) panel(hex(Mmx2Colors.Armor)) { h("Other uses", hex(Mmx2Colors.Armor)); lines(w.uses, marker = hex(Mmx2Colors.Armor)) }
            })
        }
    }
    val hints = pagesTab("Hints", GuideData.secrets.map { Triple(it.id, it.title, it.subtitle) }) { id -> GuideData.secrets.first { it.id == id }.sections }
    return listOf(order, powerUps, bosses, weapons, hints)
}

// ---------------------------------------------------------------- Super Mario World

private fun Page.smwExit(exit: SmwExit) {
    val c = hex(exit.kind.smwColor())
    panel(c, check = exit.id) {
        check(exit.kind.label, "Cleared", "Not cleared yet")
        lab("Leads to", exit.info.leadsTo, c)
        tags(exit.info.needs.map { (if (it.startsWith("or ")) it else "Needs $it") to WARN })
        lines(exit.info.steps, numbered = exit.info.steps.size > 1, marker = c)
        if (exit.info.confirm) x("Only one guide describes this route in detail. Worth confirming in-game.", "warn")
    }
}

fun smwTabs(): List<Tab> {
    val exits = SmwLevels.allExits
    val worlds = Tab("Worlds").apply {
        row(Row(OVERVIEW, "Progress", count = exits.map { it.id } to "{d} of {n} exits"), Page("All 96 exits", "Tap an exit inside a level to check it off.").apply {
            val normal = exits.filter { it.kind == ExitKind.NORMAL }.map { it.id }
            val secret = exits.filter { it.kind == ExitKind.SECRET }.map { it.id }
            meters(
                tags = listOf(Meter("Normal", normal, listOf(hex(SmwColors.NormalExit))), Meter("Secret", secret, listOf(hex(SmwColors.SecretExit)))),
                rows = World.entries.map { w ->
                    val ex = SmwLevels.all.filter { it.world == w }.flatMap { it.exits }
                    Meter(w.label, ex.map { it.id }, ex.map { hex(it.kind.smwColor()) })
                },
            )
            reset()
        })
        SmwLevels.all.forEachIndexed { i, level ->
            val group = if (i == 0 || SmwLevels.all[i - 1].world != level.world) level.world.label else null
            row(
                Row(level.id, level.name, levelSubtitle(level), group, cells = level.exits.map { it.id to hex(it.kind.smwColor()) }.ifEmpty { null }),
                Page(level.name, "${level.world.label} · ${level.type.label}").apply {
                    if (level.exits.isEmpty()) panel { x("No exit here counts toward the 96.", "muted") }
                    level.exits.forEach { smwExit(it) }
                    if (level.boss.isNotEmpty()) panel(WARN) { h("Boss", WARN); lines(level.boss, marker = WARN) }
                    if (level.notes.isNotEmpty()) panel { h("Worth knowing"); lines(level.notes) }
                },
            )
        }
    }
    check(LevelType.entries.isNotEmpty())
    val secrets = Tab("Secret exits").apply {
        val list = SmwLevels.secretExits
        list.forEachIndexed { i, (level, exit) ->
            val group = if (i == 0 || list[i - 1].first.world != level.world) level.world.label else null
            row(Row(exit.id, level.name, "To ${exit.info.leadsTo}", group, cells = listOf(exit.id to hex(SmwColors.SecretExit))),
                Page(level.name, "${level.world.label} · ${level.type.label}").apply {
                    smwExit(exit)
                    note("The level's full page, with its normal exit, is in the Worlds tab.")
                })
        }
    }
    val palaces = Tab("Switch Palaces").apply {
        SmwPages.palaces.forEach { p ->
            val level = SmwLevels.byId.getValue(p.levelId)
            row(Row(p.id, p.name, p.openedBy, cells = level.exits.map { it.id to hex(p.color) }), Page(p.name, "Opened by: ${p.openedBy}").apply {
                level.exits.forEach { smwExit(it) }
                p.sections.forEach { section(it) }
            })
        }
    }
    val yoshi = pagesTab("Yoshi & Capes", SmwPages.yoshiAndCapes.map { Triple(it.id, it.title, it.subtitle) }) { id -> SmwPages.yoshiAndCapes.first { it.id == id }.sections }
    val hints = pagesTab("Hints", SmwPages.hints.map { Triple(it.id, it.title, it.subtitle) }) { id -> SmwPages.hints.first { it.id == id }.sections }
    return listOf(worlds, secrets, palaces, yoshi, hints)
}

// ---------------------------------------------------------------- Kirby's Dream Land 3

fun kdl3Tabs(): List<Tab> {
    val gold = hex(Kdl3Colors.HeartGold)
    val pink = hex(Kdl3Colors.Pink)
    val stages = Kdl3Stages.all
    val worlds = Tab("Worlds").apply {
        row(Row(OVERVIEW, "Progress", count = kdl3ChecklistIds to "{d} of {n}"), Page("Progress", "Tap a Heart Star or extra to check it off.").apply {
            val hearts = stages.map { it.heartId }
            val extras = Kdl3Stages.extras.map { it.id }
            meters(
                tags = listOf(Meter("Heart Stars", hearts, listOf(gold)), Meter("Extras", extras, listOf(ACCENT))),
                rows = com.retroreadme.games.kdl3.World.entries.map { w ->
                    val ids = stages.filter { it.world == w }.map { it.heartId }
                    Meter(w.label, ids, ids.map { gold })
                },
            )
            reset()
        })
        stages.forEachIndexed { i, s ->
            val group = if (i == 0 || stages[i - 1].world != s.world) s.world.label else null
            row(Row(s.id, s.name, stageNeeds(s) ?: s.task, group, cells = listOf(s.heartId to gold)), Page(s.name, "Heart Star from ${s.character}").apply {
                panel(gold, check = s.heartId) {
                    check("Heart Star", "Collected", "Not yet")
                    x(s.task)
                    tags(s.friends.map { it.label to ACCENT } + s.abilities.map { it.label to SECRET })
                }
                panel(ACCENT) {
                    h("How to get it")
                    lines(s.steps, numbered = s.steps.size > 1)
                    if (s.confirm) x("The two guides disagree on part of this route. Worth confirming in-game.", "warn")
                }
                if (s.notes.isNotEmpty()) panel { h("Worth knowing"); lines(s.notes) }
            })
        }
        Kdl3Stages.extras.forEachIndexed { i, e ->
            row(Row(e.id, e.title, e.subtitle, if (i == 0) "Hyper Zone & extras" else null, cells = listOf(e.id to pink)), Page(e.title, e.subtitle).apply {
                panel(gold, check = e.id) {
                    check("Done", "Cleared", "Not yet")
                    lines(e.steps, numbered = e.steps.size > 1)
                }
            })
        }
    }
    fun Page.neededBy(list: List<com.retroreadme.games.kdl3.Stage>) = panel(gold) {
        h("Heart Stars that use it", gold)
        if (list.isEmpty()) x("None.", "muted") else lines(list.map { "${it.name}: ${it.task}" }, checks = list.map { it.heartId })
    }
    val friends = Tab("Friends & Abilities").apply {
        Friend.entries.forEachIndexed { i, f ->
            row(Row("f_" + f.name, f.label, null, if (i == 0) "Animal Friends" else null), Page(f.label, "Animal Friend").apply {
                panel(ACCENT) { x(f.trait) }
                neededBy(stages.filter { f in it.friends })
            })
        }
        Ability.entries.forEachIndexed { i, a ->
            row(Row("a_" + a.name, a.label, null, if (i == 0) "Copy Abilities" else null), Page(a.label, "Copy Ability").apply {
                panel(SECRET) { h("Where to get it", SECRET); x(a.from) }
                neededBy(stages.filter { a in it.abilities })
            })
        }
    }
    val bosses = Tab("Bosses").apply {
        Kdl3Pages.bosses.forEach { b ->
            row(Row(b.id, b.name, b.where), Page(b.name, b.where).apply { panel(WARN) { h("Strategy", WARN); lines(b.strategy, marker = WARN) } })
        }
    }
    val sub = pagesTab("Sub-games", Kdl3Pages.subGames.map { Triple(it.id, it.title, it.subtitle) }) { id -> Kdl3Pages.subGames.first { it.id == id }.sections }
    val hints = pagesTab("Hints", Kdl3Pages.hints.map { Triple(it.id, it.title, it.subtitle) }) { id -> Kdl3Pages.hints.first { it.id == id }.sections }
    return listOf(worlds, friends, bosses, sub, hints)
}

// ---------------------------------------------------------------- Wario Land 4

fun wl4Tabs(mode: Mode): List<Tab> {
    val levels = Wl4Levels.all
    fun Page.item(item: com.retroreadme.games.wl4.Wl4Item) {
        val title = when (item.kind) {
            ItemKind.JEWEL -> "Jewel piece ${item.number}"
            ItemKind.CD -> "CD"
            ItemKind.KEYZER -> "Keyzer"
        }
        panel(hex(item.color), check = item.id) {
            check(title, "Collected", "Not collected yet")
            x(item.where)
        }
    }
    fun Page.switch(d: com.retroreadme.games.wl4.ModeData) = panel(WARN) {
        h("Frog switch", WARN)
        lab("Timer", d.escapeTime, WARN)
        x("Hitting it starts the escape. Everything below is collected on the way back.", "muted")
        d.escapeTip?.let { x(it) }
    }
    val all = Wl4Levels.allItems(mode)
    val passages = Tab("Passages").apply {
        row(Row(OVERVIEW, "Collection", count = all.map { it.id } to "{d} of {n}"), Page("Collection", "${mode.label} mode. Tap an item inside a level to check it off.").apply {
            meters(
                tags = ItemKind.entries.map { k -> Meter("${k.label}s", all.filter { it.kind == k }.map { it.id }, listOf(ACCENT)) },
                rows = Passage.entries.map { p ->
                    val items = levels.filter { it.passage == p }.flatMap { it.items(mode) }
                    Meter(p.label, items.map { it.id }, items.map { hex(it.color) })
                },
            )
            reset()
        })
        levels.forEachIndexed { i, level ->
            val group = if (i == 0 || levels[i - 1].passage != level.passage) level.passage.label else null
            val d = level.data(mode)
            val items = level.items(mode)
            row(Row(level.id, level.name, "Escape ${d.escapeTime}", group, cells = items.map { it.id to hex(it.color) }),
                Page(level.name, "${level.passage.label} · ${mode.label}").apply {
                    val byToken = items.associateBy { it.id.substringAfterLast('_') }
                    var switchShown = false
                    d.steps.forEach { token ->
                        if (token == "switch") { switchShown = true; switch(d) } else byToken[token]?.let { item(it) }
                    }
                    items.filter { it.id.substringAfterLast('_') !in d.steps }.forEach { item(it) }
                    if (!switchShown) switch(d)
                    if (level.notes.isNotEmpty()) panel { h("Worth knowing"); lines(level.notes) }
                })
        }
    }
    val cds = Tab("CDs").apply {
        val withCd = levels.filter { it.data(mode).cd != null }
        withCd.forEachIndexed { i, level ->
            val group = if (i == 0 || withCd[i - 1].passage != level.passage) level.passage.label else null
            val cd = level.items(mode).first { it.kind == ItemKind.CD }
            row(Row(level.id, level.name, null, group, cells = listOf(cd.id to hex(Wl4Colors.Cd)), subCheck = Triple(cd.id, "Collected", "Not yet")),
                Page(level.name, "${level.passage.label} · ${mode.label}").apply {
                    item(cd)
                    note("The level's jewel pieces and Keyzer are on its page in the Passages tab.")
                })
        }
    }
    val bosses = Tab("Bosses").apply {
        Wl4Pages.bosses.forEach { b ->
            val time = if (mode == Mode.NORMAL) b.timeNormal else b.timeHard
            row(Row(b.id, b.name, "${b.passage.label} · $time"), Page(b.name, b.passage.label).apply {
                tags(*listOfNotNull("Time $time" to WARN, b.weakTo?.let { "Weak to $it" to ACCENT }, b.treasure?.let { "Treasure: $it" to SECRET }).toTypedArray())
                panel(ACCENT) { h("Strategy"); lines(b.strategy) }
                if (b.weakTo != null) note("Three treasure chests start disappearing when the timer reaches 1:00.")
            })
        }
    }
    val shop = pagesTab("Shop & Items", Wl4Pages.shop.map { Triple(it.id, it.title, it.subtitle) }) { id -> Wl4Pages.shop.first { it.id == id }.sections }
    val hints = pagesTab("Hints", Wl4Pages.hints.map { Triple(it.id, it.title, it.subtitle) }) { id -> Wl4Pages.hints.first { it.id == id }.sections }
    return listOf(passages, cds, bosses, shop, hints)
}

// ---------------------------------------------------------------- Aria of Sorrow

fun aosTabs(): List<Tab> {
    val souls = AosSouls.all
    val soulTab = Tab("Souls").apply {
        row(Row(OVERVIEW, "Progress", count = souls.map { it.id } to "{d} of {n} souls"), Page("Soul collection", "Tap a soul to check it off.").apply {
            meters(rows = SoulType.entries.map { t ->
                val ids = souls.filter { it.type == t }.map { it.id }
                Meter(t.label, ids, ids.map { hex(t.tint) })
            })
            panel(ACCENT) {
                h("Soul types")
                SoulType.entries.forEach { lab(it.label.removeSuffix(" souls"), it.howToUse, hex(it.tint)) }
            }
            reset()
        })
        souls.forEachIndexed { i, s ->
            val group = if (i == 0 || souls[i - 1].type != s.type) s.type.label else null
            val tint = hex(s.type.tint)
            row(Row(s.id, s.name, if (s.fromHolder) "Soul holder · ${s.areas}" else s.areas, group, cells = listOf(s.id to tint)),
                Page(s.name, s.type.label.removeSuffix("s") + (s.number?.let { " · No. $it" } ?: " · Soul holder")).apply {
                    panel(tint, check = s.id) {
                        check(s.name, "Absorbed", "Not yet")
                        x(s.effect)
                        s.mp?.let { lab("MP", it, tint) }
                    }
                    panel(ACCENT) {
                        h(if (s.fromHolder) "Where" else "Where it drops")
                        lab("Areas", s.areas, ACCENT)
                        lab(if (s.fromHolder) "Holder" else "Best spot", s.best, ACCENT)
                    }
                    s.note?.let { panel(SECRET) { h("Worth knowing", SECRET); x(it) } }
                })
        }
    }
    val areas = Tab("Areas").apply {
        AosPages.areas.forEach { a ->
            val here = soulsIn(a)
            row(Row(a, a, count = here.map { it.id } to "{d} of {n} souls"), Page(a, "${here.size} souls found here").apply {
                areaBosses[a]?.let { b -> tags(*b.map { "Boss: $it" to WARN }.toTypedArray()) }
                val keys = AosPages.route.mapNotNull { AosSouls.byId[it.first] }.filter { a in it.areas }
                if (keys.isNotEmpty()) panel(SECRET) { h("Key souls here", SECRET); lines(keys.map { "${it.name}: ${it.effect}" }) }
                SoulType.entries.forEach { t ->
                    val of = here.filter { it.type == t }
                    if (of.isNotEmpty()) panel(hex(t.tint)) { h(t.label, hex(t.tint)); lines(of.map { it.name }, checks = of.map { it.id }) }
                }
            })
        }
    }
    val route = Tab("Route").apply {
        AosPages.route.forEachIndexed { i, (id, text) ->
            val s = AosSouls.byId.getValue(id)
            row(Row(id, "${i + 1}. ${s.name}", s.areas, cells = listOf(id to hex(s.type.tint))), Page("${i + 1}. ${s.name}", s.areas).apply {
                panel(hex(s.type.tint), check = id) {
                    check(s.name, "Absorbed", "Not yet")
                    x(text)
                }
                panel(ACCENT) { lab("Where", s.best, ACCENT) }
            })
        }
    }
    val endings = pagesTab("Endings", AosPages.endings.map { Triple(it.id, it.title, it.subtitle) }) { id -> AosPages.endings.first { it.id == id }.sections }
    val hints = pagesTab("Hints", AosPages.hints.map { Triple(it.id, it.title, it.subtitle) }) { id -> AosPages.hints.first { it.id == id }.sections }
    return listOf(soulTab, areas, route, endings, hints)
}

// ---------------------------------------------------------------- Metroid: Zero Mission

private fun Page.mzmItem(item: com.retroreadme.games.mzm.Item) {
    val c = hex(item.kind.color)
    panel(c, check = item.id) {
        check(item.name, "Collected", "Not collected yet")
        tags((if (item.late) listOf("After Chozodia" to WARN) else emptyList()) + item.needs.map { it to ACCENT })
        lines(item.steps, numbered = item.steps.size > 1, marker = c)
        if (item.needsShinespark) video("These steps work, but Shinesparks are hard to follow from text mid-game. A video is easier:", item.videoSearch)
    }
    map(item.area.name, item.id)
}

private fun mzmItemPage(item: com.retroreadme.games.mzm.Item) =
    Page(item.name, listOfNotNull(item.area.label, MzmMaps.roomOf(item)?.code, item.kind.label).joinToString(" · ")).apply { mzmItem(item) }

fun mzmTabs(): List<Tab> {
    val items = MzmItems.all
    val route = Tab("Route").apply {
        val ordered = MzmRoute.items
        row(Row(OVERVIEW, "Progress", count = ordered.map { it.id } to "{d} of {n} items"), Page("100% route", "Every item in the order you'd pick it up. Tap an item to check it off.").apply {
            panel(ACCENT) {
                count(ordered.map { it.id }, "{d} of {n} items", style = null)
                next("Next", ordered.map { it.id to "${it.name} (${it.area.label}), ${MzmRoute.legOf.getValue(it.id).title}" })
                lines(listOf(
                    "No sequence breaks needed. Items that need Chozodia gear wait for the cleanup trip near the end.",
                    "Follows Metroid Recon's 100% walkthrough.",
                ))
            }
            MzmRoute.legs.forEachIndexed { i, leg ->
                panel(ACCENT) {
                    h("${i + 1}. ${leg.title}")
                    count(leg.itemIds, "{d} of {n}")
                    x(leg.note, "muted")
                    meter(leg.itemIds, leg.itemIds.map { hex(MzmItems.byId.getValue(it).kind.color) })
                }
            }
        })
        ordered.forEach { item ->
            val leg = MzmRoute.legOf.getValue(item.id)
            val group = if (leg.itemIds.first() == item.id) "${MzmRoute.legs.indexOf(leg) + 1}. ${leg.title}" else null
            row(Row(item.id, item.name, "${item.area.label} · ${itemSubtitle(item)}", group, cells = listOf(item.id to hex(item.kind.color))), mzmItemPage(item))
        }
    }
    val areas = Tab("Areas").apply {
        row(Row(OVERVIEW, "Progress", count = items.map { it.id } to "{d} of {n} items"), Page("Items", "Tap an item to check it off.").apply {
            val late = items.filter { it.late }.map { it.id }
            meters(
                tags = Kind.entries.map { k -> Meter(k.short, items.filter { it.kind == k }.map { it.id }, listOf(hex(k.color))) },
                rows = Area.entries.map { a ->
                    val of = items.filter { it.area == a }
                    Meter(a.label, of.map { it.id }, of.map { hex(it.kind.color) })
                },
            )
            panel(WARN) { count(late, "{r} left that need Chozodia gear", style = null) }
            reset()
        })
        items.forEachIndexed { i, item ->
            val group = if (i == 0 || items[i - 1].area != item.area) item.area.label else null
            row(Row(item.id, item.name, itemSubtitle(item), group, cells = listOf(item.id to hex(item.kind.color))), mzmItemPage(item))
        }
    }
    val upgrades = Tab("Upgrades").apply {
        MzmPages.upgradeOrder.forEachIndexed { i, id ->
            val u = MzmItems.byId.getValue(id)
            row(Row(id, "${i + 1}. ${u.name}", u.area.label, cells = listOf(id to hex(u.kind.color))), mzmItemPage(u).apply {
                MzmPages.unlocks[id]?.let { text -> panel(SECRET) { h("What it opens up", SECRET); x(text) } }
            })
        }
    }
    val bosses = Tab("Bosses").apply {
        MzmPages.bosses.forEach { b ->
            row(Row(b.id, b.name, b.area.label), Page(b.name, b.area.label).apply {
                tags("Weak point: ${b.weakPoint}" to ACCENT)
                panel(WARN) {
                    h("Strategy", WARN)
                    lines(b.strategy, numbered = true, marker = WARN)
                }
                panel { h("Where and why"); lines(b.lines) }
            })
        }
    }
    val techniques = pagesTab("Techniques", MzmPages.techniques.map { Triple(it.id, it.title, it.subtitle) }) { id -> MzmPages.techniques.first { it.id == id }.sections }
    val hints = pagesTab("Hints", MzmPages.hints.map { Triple(it.id, it.title, it.subtitle) }) { id -> MzmPages.hints.first { it.id == id }.sections }
    return listOf(route, areas, upgrades, bosses, techniques, hints)
}

/** Every area's room layout, for the web map renderer. */
fun mzmMaps(): Map<String, Any?> = Area.entries.associate { area ->
    val m = MzmMaps.of(area)
    area.name to mapOf(
        "label" to area.label,
        "rooms" to m.rooms.map { r ->
            mapOf(
                "code" to r.code,
                "style" to r.style.name,
                "rects" to r.rects.map { listOf(it.c0, it.r0, it.c1, it.r1) },
                "save" to r.save.takeIf { it },
                "map" to r.map.takeIf { it },
                "exit" to r.exit?.let { listOf(it.to, it.dir.name) },
            )
        },
        "doors" to m.doors.map { listOf(it.row, it.col, it.kind.name) },
        "items" to m.items.mapValues { (_, c) -> listOf(com.retroreadme.games.mzm.colOf(c), com.retroreadme.games.mzm.rowOf(c)) },
    )
}
