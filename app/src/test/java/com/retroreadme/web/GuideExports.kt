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
import com.retroreadme.games.mmz.MmzData
import com.retroreadme.games.mmz.MmzPages
import com.retroreadme.games.mmz.Family as MmzFamily
import com.retroreadme.games.mmz.Source as MmzSource
import com.retroreadme.games.mmz.elfSubtitle as mmzElfSubtitle
import com.retroreadme.games.mmz.missionNumber as mmzMissionNumber
import com.retroreadme.games.mzm.Area
import com.retroreadme.games.mzm.Kind
import com.retroreadme.games.mzm.MzmItems
import com.retroreadme.games.mzm.MzmMaps
import com.retroreadme.games.mzm.MzmPages
import com.retroreadme.games.mzm.MzmRoute
import com.retroreadme.games.mzm.itemSubtitle
import com.retroreadme.games.sm.SmItems
import com.retroreadme.games.sm.SmMaps
import com.retroreadme.games.sm.SmPages
import com.retroreadme.games.sm.SmRoute
import com.retroreadme.games.sm.Area as SmArea
import com.retroreadme.games.sm.Kind as SmKind
import com.retroreadme.games.sm.itemSubtitle as smItemSubtitle
import com.retroreadme.games.smw.ExitKind
import com.retroreadme.games.smw.LevelType
import com.retroreadme.games.smw.SmwColors
import com.retroreadme.games.smw.SmwLevels
import com.retroreadme.games.smw.SmwPages
import com.retroreadme.games.smw.World
import com.retroreadme.games.smw.levelSubtitle
import com.retroreadme.games.tmc.TmcColors
import com.retroreadme.games.tmc.TmcData
import com.retroreadme.games.tmc.TmcFusions
import com.retroreadme.games.tmc.TmcPages
import com.retroreadme.games.tmc.fusionTitle
import com.retroreadme.games.tmc.heartTitle
import com.retroreadme.games.tmc.tmcChecklistIds
import com.retroreadme.games.tmc.UpgradeKind as TmcUpgradeKind
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
fun mzmMaps(): Map<String, Any?> = roomMaps(Area.entries.map { MzmMaps.of(it) })

// ---------------------------------------------------------------- Mega Man Zero

private fun PanelB.mmzElf(elf: com.retroreadme.games.mmz.Elf, showMission: Boolean) {
    check(elf.name, "Found", "Not found yet")
    tags(listOfNotNull(
        elf.family.label to hex(elf.family.color),
        elf.source.label to hex(elf.source.color),
        if (showMission) MmzData.missionById.getValue(elf.missionId).title to MUTED else null,
    ))
    lines(listOf(elf.how), marker = hex(elf.family.color))
    elf.missable?.let { x(it, "warn") }
}

private fun mmzElfPage(elf: com.retroreadme.games.mmz.Elf): Page {
    val mission = MmzData.missionById.getValue(elf.missionId)
    return Page(elf.name, "${elf.family.label} elf · ${mmzMissionNumber(mission)}. ${mission.title}").apply {
        panel(hex(elf.family.color), check = elf.id) { mmzElf(elf, showMission = false) }
        panel(SECRET) {
            h("What it does", SECRET)
            x(elf.group.effect)
            lab("Raise", if (elf.group.ec == 0) "Usable as soon as you find it" else "${elf.group.ec} E-Crystals before you can use it", MUTED)
            if (elf.group.lasting) x("Its effect lasts for the rest of the game.", "muted")
        }
    }
}

fun mmzTabs(): List<Tab> {
    val elves = MmzData.elves
    val missions = Tab("Missions").apply {
        row(Row(OVERVIEW, "Overview", count = elves.map { it.id } to "{d} of {n} elves"), Page("Missions", "In a good order, with the chip each boss is weak to and the elves you can find.").apply {
            panel(WARN) {
                h("Missions can't be replayed", WARN)
                x("Clearing or failing a mission closes it, and elves dropped by its enemies go with it. Boxes stay put. See Hints.")
            }
            meters(rows = MmzData.missions.map { m ->
                val of = MmzData.elvesIn(m)
                Meter("${mmzMissionNumber(m)}. ${m.title}", of.map { it.id }, of.map { hex(it.family.color) })
            })
        })
        MmzData.missions.forEach { m ->
            val of = MmzData.elvesIn(m)
            val sub = listOfNotNull(m.boss.ifEmpty { null }, m.weakness?.let { "weak to $it" }).joinToString(" · ").ifEmpty { m.area }
            row(Row(m.id, "${mmzMissionNumber(m)}. ${m.title}", sub, cells = of.map { it.id to hex(it.family.color) }), Page("${mmzMissionNumber(m)}. ${m.title}", m.area).apply {
                if (m.boss.isNotEmpty()) tags(*listOfNotNull("Boss: ${m.boss}" to ACCENT, m.weakness?.let { "Weak to $it" to SECRET }).toTypedArray())
                panel(ACCENT) { x(m.unlock); lines(m.tips) }
                if (of.isNotEmpty()) {
                    note("Cyber-elves", ACCENT)
                    of.forEach { elf -> panel(hex(elf.family.color), check = elf.id) { mmzElf(elf, showMission = false) } }
                }
            })
        }
    }
    val cyber = Tab("Cyber-elves").apply {
        row(Row(OVERVIEW, "Progress", count = elves.map { it.id } to "{d} of {n} elves"), Page("Cyber-elves", "All 78, by mission. Tap an elf to check it off.").apply {
            meters(
                tags = MmzFamily.entries.map { f -> Meter(f.label, elves.filter { it.family == f }.map { it.id }, listOf(hex(f.color))) },
                rows = emptyList(),
            )
            panel(ACCENT) { MmzFamily.entries.forEach { f -> lab(f.label, f.about, hex(f.color)) } }
            panel(WARN) { count(elves.filter { it.source == MmzSource.ENEMIES || it.source == MmzSource.MISSION }.map { it.id }, "{r} left that you can only get during their mission", style = null) }
            reset()
        })
        elves.forEachIndexed { i, elf ->
            val mission = MmzData.missionById.getValue(elf.missionId)
            val group = if (i == 0 || elves[i - 1].missionId != elf.missionId) "${mmzMissionNumber(mission)}. ${mission.title}" else null
            row(Row(elf.id, elf.name, mmzElfSubtitle(elf), group, cells = listOf(elf.id to hex(elf.family.color))), mmzElfPage(elf))
        }
    }
    val bosses = Tab("Bosses").apply {
        MmzPages.bosses.forEach { b ->
            val m = MmzData.missionById.getValue(b.missionId)
            row(Row(b.id, b.name, listOfNotNull(m.title, b.weakness?.let { "weak to $it" }).joinToString(" · ")), Page(b.name, "${mmzMissionNumber(m)}. ${m.title} · ${m.area}").apply {
                tags(*listOfNotNull("Weak to ${b.weakness ?: "nothing"}" to SECRET, b.reward?.let { "Gives $it" to ACCENT }).toTypedArray())
                panel(ACCENT) { h("Strategy"); lines(b.strategy) }
            })
        }
    }
    val weapons = pagesTab("Weapons", MmzPages.weapons.map { Triple(it.id, it.title, it.subtitle) }) { id -> MmzPages.weapons.first { it.id == id }.sections }
    val hints = pagesTab("Hints", MmzPages.hints.map { Triple(it.id, it.title, it.subtitle) }) { id -> MmzPages.hints.first { it.id == id }.sections }
    return listOf(missions, cyber, bosses, weapons, hints)
}

// ---------------------------------------------------------------- The Minish Cap

private fun tmcProgressPage(title: String, subtitle: String, groups: List<Pair<String, List<Pair<String, String>>>>) = Page(title, subtitle).apply {
    panel(ACCENT) {
        count(tmcChecklistIds, "{d} of {n} (Heart Pieces, fusions and upgrades)", style = null)
    }
    meters(
        tags = listOf(
            Meter("Hearts", TmcData.hearts.map { it.id }, listOf(hex(TmcColors.Heart))),
            Meter("Fusions", TmcFusions.all.map { it.id }, listOf(hex(TmcColors.EzloGold))),
            Meter("Upgrades", TmcData.upgrades.map { it.id }, listOf(hex(TmcColors.Upgrade))),
        ),
        rows = groups.map { (label, cells) -> Meter(label, cells.map { it.first }, cells.map { it.second }) },
    )
    reset()
}

fun tmcTabs(): List<Tab> {
    val walkthrough = Tab("Walkthrough").apply {
        val steps = TmcData.steps
        row(Row(OVERVIEW, "Where now", count = steps.map { it.id } to "{d} of {n} steps"), Page("Walkthrough", "Where to go next, in a line or two. Dungeons get one line: what's inside.").apply {
            panel(ACCENT) {
                count(steps.map { it.id }, "{d} of {n} steps done", style = null)
                next("Next", steps.map { it.id to "${it.chapter}: ${it.text}" })
                x("Steps are just for keeping your place; they don't count toward 100%.", "muted")
            }
            panel(WARN) { h("One thing you can lose for good", WARN); x("The Light Arrows. See Hints before you head up Veil Falls.") }
        })
        steps.forEachIndexed { i, step ->
            val group = if (i == 0 || steps[i - 1].chapter != step.chapter) "${TmcData.chapters.indexOf(step.chapter) + 1}. ${step.chapter}" else null
            row(Row(step.id, step.text, null, group, cells = listOf(step.id to hex(TmcColors.Step))), Page(step.chapter, "Chapter ${TmcData.chapters.indexOf(step.chapter) + 1} of ${TmcData.chapters.size} · tap a step to check it off").apply {
                steps.filter { it.chapter == step.chapter }.forEach { s ->
                    panel(if (s.id == step.id) ACCENT else LINE, check = s.id) {
                        check(if (s.id == step.id) "This step" else "Step ${s.id.drop(1).trimStart('0')}", "Done", "Not yet")
                        x(s.text)
                    }
                }
            })
        }
    }
    val hearts = Tab("Heart Pieces").apply {
        val all = TmcData.hearts
        row(Row(OVERVIEW, "Progress", count = all.map { it.id } to "{d} of {n} Heart Pieces"),
            tmcProgressPage("Heart Pieces", "All 44, by area. Tap one to check it off.", TmcData.heartAreas.map { a -> a to all.filter { it.area == a }.map { it.id to hex(TmcColors.Heart) } }))
        all.forEachIndexed { i, h ->
            val group = if (i == 0 || all[i - 1].area != h.area) h.area else null
            row(Row(h.id, heartTitle(h), h.needs.ifEmpty { listOf("Nothing special") }.joinToString(", "), group, cells = listOf(h.id to hex(TmcColors.Heart))), Page(heartTitle(h), h.area).apply {
                panel(hex(TmcColors.Heart), check = h.id) {
                    check(heartTitle(h), "Collected", "Not yet")
                    if (h.needs.isNotEmpty()) tags(h.needs.map { it to ACCENT })
                    lines(listOf(h.how), marker = hex(TmcColors.Heart))
                }
            })
        }
    }
    val kinstones = Tab("Kinstones").apply {
        val all = TmcFusions.all
        row(Row(OVERVIEW, "Progress", count = all.map { it.id } to "{d} of {n} fusions"),
            tmcProgressPage("Kinstone fusions", "All 100, by the story stage they open at. Tap one to check it off.", TmcFusions.stages.mapIndexed { i, label -> label to all.filter { it.stage == i + 1 }.map { it.id to hex(it.color.color) } }))
        all.forEachIndexed { i, f ->
            val group = if (i == 0 || all[i - 1].stage != f.stage) TmcFusions.stages[f.stage - 1] else null
            row(Row(f.id, fusionTitle(f), if (f.random) f.result else "${f.location} · ${f.color.label}", group, cells = listOf(f.id to hex(f.color.color))), Page(fusionTitle(f), "${f.color.label} Kinstone · Stage ${f.stage}").apply {
                panel(hex(f.color.color), check = f.id) {
                    check(fusionTitle(f), "Fused", "Not yet")
                    tags(f.color.label to hex(f.color.color), "Stage ${f.stage}" to MUTED)
                    lab("With", if (f.random) "Anyone on the random list (see Hints)" else "${f.fuser}, ${f.location}", "@text")
                    lab("Result", f.result, SECRET)
                }
            })
        }
    }
    val upgrades = Tab("Upgrades").apply {
        val all = TmcData.upgrades
        row(Row(OVERVIEW, "Progress", count = all.map { it.id } to "{d} of {n} upgrades"),
            tmcProgressPage("Bottles and upgrades", "Four bottles, three of each capacity upgrade, and four item upgrades.", TmcUpgradeKind.entries.map { k -> k.label to all.filter { it.kind == k }.map { it.id to hex(TmcColors.Upgrade) } }))
        all.forEachIndexed { i, u ->
            val group = if (i == 0 || all[i - 1].kind != u.kind) u.kind.label else null
            row(Row(u.id, u.name, u.area, group, cells = listOf(u.id to hex(TmcColors.Upgrade))), Page(u.name, "${u.kind.label} · ${u.area}").apply {
                panel(hex(TmcColors.Upgrade), check = u.id) {
                    check(u.name, "Got it", "Not yet")
                    lines(listOf(u.how), marker = hex(TmcColors.Upgrade))
                    u.missable?.let { x(it, "warn") }
                    if (u.confirm) x("Only one of the two guides covers this one. Worth confirming in-game.", "warn")
                }
            })
        }
    }
    val bosses = Tab("Bosses").apply {
        TmcPages.bosses.forEach { b ->
            row(Row(b.id, b.name, b.dungeon), Page(b.name, b.dungeon).apply {
                tags("Guards: ${b.reward}" to SECRET)
                panel(ACCENT) { h("Strategy"); lines(b.strategy) }
            })
        }
    }
    val hints = pagesTab("Hints", TmcPages.hints.map { Triple(it.id, it.title, it.subtitle) }) { id -> TmcPages.hints.first { it.id == id }.sections }
    return listOf(walkthrough, hearts, kinstones, upgrades, bosses, hints)
}

// ---------------------------------------------------------------- Super Metroid

private fun Page.smItem(item: com.retroreadme.games.sm.Item) {
    val c = hex(item.kind.color)
    panel(c, check = item.id) {
        check(item.name, "Collected", "Not collected yet")
        tags(item.needs.map { it to ACCENT })
        lines(item.steps, numbered = item.steps.size > 1, marker = c)
        if (item.needsShinespark) video("These steps work, but Shinesparks are hard to follow from text mid-game. A video is easier:", item.videoSearch)
        if (item.confirm) x("The two guides disagree here. Worth confirming in-game.", "warn")
    }
    map(item.area.name, item.id)
}

private fun smItemPage(item: com.retroreadme.games.sm.Item) =
    Page(item.name, listOfNotNull(item.area.label, SmMaps.roomOf(item)?.code, item.kind.label).joinToString(" · ")).apply { smItem(item) }

fun smTabs(): List<Tab> {
    val items = SmItems.all
    val route = Tab("Route").apply {
        val ordered = SmRoute.items
        row(Row(OVERVIEW, "Progress", count = ordered.map { it.id } to "{d} of {n} items"), Page("100% route", "Every item in the order you'd pick it up. Tap an item to check it off.").apply {
            panel(ACCENT) {
                count(ordered.map { it.id }, "{d} of {n} items", style = null)
                next("Next", ordered.map { it.id to "${it.name} (${it.area.label}), ${SmRoute.legOf.getValue(it.id).title}" })
                lines(listOf(
                    "No sequence breaks needed. Each item comes after the gear it needs, and Mother Brain comes last.",
                    "Follows Budwin's 100% walkthrough, checked against Metroid Recon.",
                ))
            }
            SmRoute.legs.forEachIndexed { i, leg ->
                panel(ACCENT) {
                    h("${i + 1}. ${leg.title}")
                    count(leg.itemIds, "{d} of {n}")
                    x(leg.note, "muted")
                    meter(leg.itemIds, leg.itemIds.map { hex(SmItems.byId.getValue(it).kind.color) })
                }
            }
        })
        ordered.forEach { item ->
            val leg = SmRoute.legOf.getValue(item.id)
            val group = if (leg.itemIds.first() == item.id) "${SmRoute.legs.indexOf(leg) + 1}. ${leg.title}" else null
            row(Row(item.id, item.name, "${item.area.label} · ${smItemSubtitle(item)}", group, cells = listOf(item.id to hex(item.kind.color))), smItemPage(item))
        }
    }
    val areas = Tab("Areas").apply {
        row(Row(OVERVIEW, "Progress", count = items.map { it.id } to "{d} of {n} items"), Page("Items", "Tap an item to check it off.").apply {
            meters(
                tags = SmKind.entries.map { k -> Meter(k.short, items.filter { it.kind == k }.map { it.id }, listOf(hex(k.color))) },
                rows = SmArea.entries.map { a ->
                    val of = items.filter { it.area == a }
                    Meter(a.label, of.map { it.id }, of.map { hex(it.kind.color) })
                },
            )
            reset()
        })
        items.forEachIndexed { i, item ->
            val group = if (i == 0 || items[i - 1].area != item.area) item.area.label else null
            row(Row(item.id, item.name, smItemSubtitle(item), group, cells = listOf(item.id to hex(item.kind.color))), smItemPage(item))
        }
    }
    val upgrades = Tab("Upgrades").apply {
        SmPages.upgradeOrder.forEachIndexed { i, id ->
            val u = SmItems.byId.getValue(id)
            row(Row(id, "${i + 1}. ${u.name}", u.area.label, cells = listOf(id to hex(u.kind.color))), smItemPage(u).apply {
                SmPages.unlocks[id]?.let { text -> panel(SECRET) { h("What it opens up", SECRET); x(text) } }
            })
        }
    }
    val bosses = Tab("Bosses").apply {
        SmPages.bosses.forEach { b ->
            row(Row(b.id, b.name, b.place), Page(b.name, b.place).apply {
                tags("Weak point: ${b.weakPoint}" to ACCENT)
                panel(WARN) {
                    h("Strategy", WARN)
                    lines(b.strategy, numbered = true, marker = WARN)
                }
                panel { h("Where and why"); lines(b.lines) }
            })
        }
    }
    val techniques = pagesTab("Techniques", SmPages.techniques.map { Triple(it.id, it.title, it.subtitle) }) { id -> SmPages.techniques.first { it.id == id }.sections }
    val hints = pagesTab("Hints", SmPages.hints.map { Triple(it.id, it.title, it.subtitle) }) { id -> SmPages.hints.first { it.id == id }.sections }
    return listOf(route, areas, upgrades, bosses, techniques, hints)
}

/** Every area's room layout, for the web map renderer. */
fun smMaps(): Map<String, Any?> = roomMaps(SmArea.entries.map { SmMaps.of(it) })

/** Room layouts in the web app's format, keyed by area. */
fun roomMaps(maps: List<com.retroreadme.ui.RoomMap>): Map<String, Any?> = maps.associate { m ->
    m.key to mapOf(
        "label" to m.label,
        "rooms" to m.rooms.map { r ->
            mapOf(
                "code" to r.code,
                "style" to r.style.name,
                "rects" to r.rects.map { listOf(it.c0, it.r0, it.c1, it.r1) },
                "save" to r.save.takeIf { it },
                "map" to r.map.takeIf { it },
                "mark" to r.mark,
                "exit" to r.exit?.let { listOf(it.to, it.dir.name) },
            )
        },
        "doors" to m.doors.map { listOf(it.row, it.col, it.kind.name) },
        "items" to m.items.mapValues { (_, c) -> listOf(com.retroreadme.ui.colOf(c), com.retroreadme.ui.rowOf(c)) },
    )
}

// ---------------------------------------------------------------- Metroid Fusion

private fun mfItemPage(item: com.retroreadme.games.mf.Item) = Page(
    item.name,
    listOfNotNull(item.sector.label, com.retroreadme.games.mf.MfMaps.roomOf(item)?.code, item.kind.label).joinToString(" · "),
).apply {
    val c = hex(item.kind.color)
    panel(c, check = item.id) {
        check(item.name, "Collected", "Not collected yet")
        tags(item.needs.map { it to ACCENT })
        lines(item.steps, numbered = item.steps.size > 1, marker = c)
        if (item.needsShinespark) video("These steps work, but Shinesparks are hard to follow from text mid-game. A video is easier:", item.videoSearch)
        if (item.confirm) x("Only one of the two guides backs this up. Worth confirming in-game.", "warn")
    }
    map(item.sector.name, item.id)
}

fun mfTabs(): List<Tab> {
    val items = com.retroreadme.games.mf.MfItems.all
    val route = com.retroreadme.games.mf.MfRoute
    val routeTab = Tab("Route").apply {
        row(Row(OVERVIEW, "Progress", count = route.items.map { it.id } to "{d} of {n} items"), Page("100% route", "Every item in the order you'd pick it up. Tap an item to check it off.").apply {
            panel(ACCENT) {
                count(route.items.map { it.id }, "{d} of {n} items", style = null)
                next("Next", route.items.map { it.id to "${it.name} (${it.sector.label}), ${route.legOf.getValue(it.id).title}" })
                lines(listOf(
                    "The story sends you sector to sector, so most items fall on the way. The rest wait for a cleanup once the Screw Attack opens everything up.",
                    "Follows Thonky's 100% order, checked against Metroid Recon.",
                ))
            }
            route.legs.forEachIndexed { i, leg ->
                panel(ACCENT) {
                    h("${i + 1}. ${leg.title}")
                    count(leg.itemIds, "{d} of {n}")
                    x(leg.note, "muted")
                    meter(leg.itemIds, leg.itemIds.map { hex(com.retroreadme.games.mf.MfItems.byId.getValue(it).kind.color) })
                }
            }
            reset()
        })
        route.items.forEach { item ->
            val leg = route.legOf.getValue(item.id)
            val group = if (leg.itemIds.first() == item.id) "${route.legs.indexOf(leg) + 1}. ${leg.title}" else null
            row(Row(item.id, item.name, "${item.sector.short} · ${com.retroreadme.games.mf.itemSubtitle(item)}", group, cells = listOf(item.id to hex(item.kind.color))), mfItemPage(item))
        }
    }
    val sectors = Tab("Sectors").apply {
        row(Row(OVERVIEW, "Progress", count = items.map { it.id } to "{d} of {n} items"), Page("Items", "Tap an item to check it off.").apply {
            meters(
                tags = com.retroreadme.games.mf.Kind.entries.map { k -> Meter(k.short, items.filter { it.kind == k }.map { it.id }, listOf(hex(k.color))) },
                rows = com.retroreadme.games.mf.Sector.entries.map { s ->
                    val of = items.filter { it.sector == s }
                    Meter(s.label, of.map { it.id }, of.map { hex(it.kind.color) })
                },
            )
            reset()
        })
        items.forEachIndexed { i, item ->
            val group = if (i == 0 || items[i - 1].sector != item.sector) item.sector.label else null
            row(Row(item.id, item.name, com.retroreadme.games.mf.itemSubtitle(item), group, cells = listOf(item.id to hex(item.kind.color))), mfItemPage(item))
        }
    }
    val abilities = Tab("Abilities").apply {
        com.retroreadme.games.mf.MfPages.abilities.forEachIndexed { i, a ->
            row(Row(a.id, "${i + 1}. ${a.name}", "${a.sector.short} · ${a.from}"), Page("${i + 1}. ${a.name}", "${a.sector.label} · ${a.from}").apply {
                panel(ACCENT) { h("Where"); x(a.where) }
                panel(SECRET) { h("What it opens up", SECRET); x(a.opens) }
            })
        }
    }
    val bosses = Tab("Bosses").apply {
        com.retroreadme.games.mf.MfPages.bosses.forEach { b ->
            row(Row(b.id, b.name, b.sector.label), Page(b.name, b.sector.label).apply {
                tags(*listOfNotNull("Weak point: ${b.weakPoint}" to ACCENT, b.reward?.let { "Gives $it" to SECRET }).toTypedArray())
                panel(WARN) { h("Strategy", WARN); lines(b.strategy, marker = WARN) }
            })
        }
    }
    val pages = com.retroreadme.games.mf.MfPages.hints
    val hints = pagesTab("Hints", pages.map { Triple(it.id, it.title, it.subtitle) }) { id -> pages.first { it.id == id }.sections }
    return listOf(routeTab, sectors, abilities, bosses, hints)
}

fun mfMaps(): Map<String, Any?> = roomMaps(com.retroreadme.games.mf.Sector.entries.map { com.retroreadme.games.mf.MfMaps.of(it) })
