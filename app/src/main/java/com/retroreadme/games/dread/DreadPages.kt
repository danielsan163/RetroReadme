package com.retroreadme.games.dread

import com.retroreadme.core.Section
import com.retroreadme.core.Tone

object DreadPages {

    /** The 23 upgrades in story order, for the Upgrades tab. */
    val upgradeOrder = listOf(
        "u_chargebeam", "u_spidermagnet", "u_phantomcloak", "u_widebeam", "u_morphball", "u_variasuit",
        "u_diffusionbeam", "u_morphballbomb", "u_flashshift", "u_speedbooster", "u_grapplebeam",
        "u_supermissile", "u_plasmabeam", "u_spinboost", "u_icemissile", "u_pulseradar", "u_stormmissiles",
        "u_spacejump", "u_gravitysuit", "u_screwattack", "u_crossbomb", "u_wavebeam", "u_powerbomb",
    )

    /** What each upgrade opens up, shown on its Upgrades page. */
    val unlocks = mapOf(
        "u_chargebeam" to "Charged shots, and Charge Beam doors.",
        "u_spidermagnet" to "Cling to blue magnetic walls and ceilings.",
        "u_phantomcloak" to "Turn invisible to slip past E.M.M.I. and through Sensor doors. It drains Aeon energy, then health.",
        "u_widebeam" to "A wider shot that breaks Wide Beam covers and pushes the crates.",
        "u_morphball" to "Narrow passages and Morph Ball launchers.",
        "u_variasuit" to "Superheated rooms stop hurting.",
        "u_diffusionbeam" to "Charged shots spread out and hit things behind walls, like red pustules.",
        "u_morphballbomb" to "Bomb blocks, hidden floors, and bomb-jumping.",
        "u_flashshift" to "A quick dash in any direction, in the air too. Gets you over Shutter platforms.",
        "u_speedbooster" to "Run to smash Speed Booster blocks, and the Shinespark (see Hints).",
        "u_grapplebeam" to "Grapple points, swinging, and pulling away Grapple blocks.",
        "u_supermissile" to "Stronger missiles that break Super Missile covers.",
        "u_plasmabeam" to "A stronger beam that breaks Plasma covers.",
        "u_spinboost" to "A second jump in mid-air.",
        "u_icemissile" to "Freezing missiles that break Enki and Ice Missile covers.",
        "u_pulseradar" to "Shows hidden blocks nearby.",
        "u_stormmissiles" to "Charge your missiles to lock on to several targets; breaks Storm Missile crates.",
        "u_spacejump" to "Jump again and again in mid-air.",
        "u_gravitysuit" to "Free movement underwater and in lava, and freezing rooms stop hurting.",
        "u_screwattack" to "Spinning jumps destroy enemies and Screw Attack blocks.",
        "u_crossbomb" to "Bombs that blast in a cross and carry you over Pitfall blocks.",
        "u_wavebeam" to "Shots pass through walls; opens Wave Beam covers.",
        "u_powerbomb" to "Power Bomb blocks and doors, and a blast that shows hidden blocks.",
    )

    val bosses: List<Boss> = listOf(
        Boss("b_corpius", "Corpius", "Artaria",
            listOf("Behind the alien door at the top of the E.M.M.I. Zone shaft. Gives the Phantom Cloak."),
            weakPoint = "Its face, and the glowing tail while it's invisible",
            strategy = listOf(
                "Missiles and charged shots to the face; jump its tail strikes.",
                "When it goes invisible, shoot the glowing tail.",
                "When it charges a big attack, slide under and be ready to counter (melee as it flashes).",
                "Use the Spider Magnet wall that appears to dodge its poison breath, but drop off before it slams the wall.",
            )),
        Boss("b_kraid", "Kraid", "Cataris",
            listOf("Through the superheated cave by the Ammo Recharge Station, once you have the Varia Suit. Gives the Diffusion Beam."),
            weakPoint = "Inside his open mouth",
            strategy = listOf(
                "Shoot his eyes and mouth to make him open up, then fill the mouth with Missiles. Shoot his spit and claws for health and Missiles.",
                "Second phase: hit the purple spot, roll to dodge the spikes, then ride the Spider Magnet plate up and keep hitting the mouth.",
                "Counter his quick swipe. When his eyes glow red he punches: drop back down at once.",
            )),
        Boss("b_drogyga", "Drogyga", "Burenia",
            listOf("At the top of Burenia, after the Grapple Beam and a trip through Ferenia."),
            weakPoint = "The core, once the water's drained",
            strategy = listOf(
                "Shoot the blue orbs for pickups and jump the tentacles when they shake.",
                "Hit the weak spot until it collapses, shoot the switch to drain the water, grapple across on the magnetic strip and shoot the second switch.",
                "With the core exposed, Missile it and counter the tentacle it lashes with.",
            )),
        Boss("b_escue", "Escue", "Ferenia",
            listOf("Down the spire on Ferenia's far right; Pulse Radar shows the way. Gives the Storm Missile."),
            weakPoint = "Ice Missiles while its shield is down",
            strategy = listOf(
                "Its electric shield is up most of the time. After it rams a wall the shield drops: unload Ice Missiles or charged shots.",
                "Stay in the air when its energy ball shocks the floor, and Flash Shift away from its homing shots.",
                "Its final form is a big X: shoot it and absorb what comes out.",
            )),
        Boss("b_z57", "Experiment No. Z-57", "Cataris",
            listOf("At the bottom of Cataris, through a red pustule by the Network Station, once you have the Gravity Suit."),
            weakPoint = "Its head",
            strategy = listOf(
                "Missiles to the head. Jump to the far corner as it sweeps its claws, and wait out its pink beam before moving.",
                "When it fires the big beam, get under its head and counter the bite.",
                "Second phase: when it plants its tentacles round the room, Storm Missile all five points. Space Jump through its wind and swipes.",
            )),
        Boss("b_golzuna", "Golzuna", "Ghavoran",
            listOf("At the top of the long Morph Ball passage by the Green Teleportal, after the Screw Attack. Gives the Cross Bomb."),
            weakPoint = "Its back (or head in the middle form)",
            strategy = listOf(
                "It changes shape twice. Slide under it when it rears up and shoot its back.",
                "Space Jump clear of the cross-shaped blasts it drops.",
            )),
        Boss("b_chozo", "Chozo Soldiers", "Artaria, Elun, Hanubia",
            listOf("Several mini-bosses: a Chozo Soldier guards Elun, a shielded one waits in frozen Artaria, and the Elite Chozo Soldier is in Hanubia. The last regular one gives the Power Bomb."),
            weakPoint = "Missiles and charged shots, and counters",
            strategy = listOf(
                "Flash Shift away from its dives and slams; wall-jump to get over it when cornered.",
                "When its blade flashes, counter. When it glows red, dodge.",
                "Shielded ones: counter the shield as it flashes yellow to break it (the Elite one's needs a Grapple pull too).",
            )),
        Boss("b_robots", "Robot Chozo Soldiers", "Ghavoran, Ferenia, Burenia",
            listOf("Mini-bosses, sometimes in pairs."),
            weakPoint = "Missiles, and counters",
            strategy = listOf(
                "Counter when it draws its blade back and flashes; jump or Flash Shift over it when it glows red.",
                "With two, focus on one at a time.",
            )),
        Boss("b_raven", "Raven Beak", "Itorash",
            listOf("The final boss, past the capsule at the top of Hanubia. There's no going back afterwards."),
            weakPoint = "Counters, then everything you have",
            strategy = listOf(
                "Learn the counters: his red-glowing dash punch can be countered, and each counter opens him up for big damage.",
                "Shoot or dodge his dark sphere. Duck or morph under his screen-wide beam, then counter.",
                "He grows wings in the second phase: stay close, Space Jump over his beam and Flash Shift under his lunges.",
                "After he falls, finish him with the Omega Beam, then you have three minutes to reach your ship.",
            )),
    )

    /** E.M.M.I. pages, first in Hints. */
    private fun emmiPages(): List<DreadPage> = listOf(
        DreadPage("e_how", "Beating an E.M.M.I.", "Seven of them", listOf(
            Section(null, listOf(
                "You can't hurt an E.M.M.I. normally. Find its zone's Central Unit, destroy it, and you get the Omega Cannon for a while.",
                "Lure the E.M.M.I. somewhere with room, rapid-fire to strip its faceplate, then charge the Omega Cannon and blast its core.",
                "Each one gives an upgrade: Spider Magnet, Morph Ball, Speed Booster, Ice Missile and Wave Beam.",
            ), numbered = true),
        )),
        DreadPage("e_escape", "If it catches you", "Two chances to counter", listOf(
            Section(null, listOf(
                "When it grabs you, press Melee at the exact flash to break free. The window is very short.",
                "Use the Phantom Cloak to stay unseen, and leave the zone through any door to end the chase.",
            )),
        )),
    )

    val hints: List<DreadPage> = emmiPages() + listOf(
        DreadPage("h_100", "What 100% takes", "146 items", listOf(
            Section(null, listOf(
                "23 upgrades, 8 Energy Tanks, 16 Energy Parts (four make a tank), 75 Missile Tanks, 11 Missile+ Tanks and 13 Power Bomb Tanks.",
                "The map remembers every item you've seen; the map screen can highlight every icon of a type.",
            )),
        )),
        DreadPage("h_shine", "Shinespark", "Needed for many items", listOf(
            Section(null, listOf(
                "Run with the Speed Booster until it kicks in, then press Down to store the charge. Jump and press a direction to launch.",
                "You can keep the charge while sliding, and store it again by landing a Shinespark on a slope and pressing Down.",
                "As a Morph Ball you can launch too, through narrow passages.",
            ), numbered = true),
            Section("Watch it first", listOf(
                "The steps for Shinespark items work, but the timing is hard to follow from text. Each one's page suggests a YouTube search for a video.",
            ), tone = Tone.SECRET),
        )),
        DreadPage("h_counter", "The Melee Counter", "Your best move", listOf(
            Section(null, listOf(
                "Press Melee as an enemy flashes just before it hits you. Countered enemies drop more, and bosses take big damage.",
                "Most bosses have at least one attack you can counter; it's usually the quickest way through.",
            )),
        )),
        DreadPage("h_end", "Point of no return", "Before Itorash", listOf(
            Section(null, listOf(
                "The capsule at the top of Hanubia takes you to Itorash and the final fight. Finish your 100% cleanup first.",
            ), tone = Tone.WARNING),
        )),
        DreadPage("h_maps", "About the maps", "How they were drawn", listOf(
            Section(null, listOf(
                "Dread's own map has no room outlines, so these maps are simplified: the areas are cut into rooms at their doors and drawn on a coarse grid.",
                "Item positions are exact; room shapes are approximate, and the big E.M.M.I. Zones are mostly one room each.",
            )),
        )),
    )
}
