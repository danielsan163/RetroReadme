package com.retroreadme.games.mf

import com.retroreadme.core.Section
import com.retroreadme.core.Tone
import com.retroreadme.games.mf.Sector.AQA
import com.retroreadme.games.mf.Sector.ARC
import com.retroreadme.games.mf.Sector.MAIN_DECK
import com.retroreadme.games.mf.Sector.NOC
import com.retroreadme.games.mf.Sector.PYR
import com.retroreadme.games.mf.Sector.SRX
import com.retroreadme.games.mf.Sector.TRO

object MfPages {

    /** Abilities in the order the story hands them back. */
    val abilities: List<Ability> = listOf(
        Ability("missiles", "Missiles", MAIN_DECK, "Data Room",
            "The first Data Room on the Main Deck, where the computer sends you at the start.",
            "Missile blocks and eye doors, and the first Missile Tanks."),
        Ability("morph", "Morph Ball", MAIN_DECK, "Arachnus-X",
            "Through the vents right of the Operations Deck, down the dark red shaft and right to the cargo hold.",
            "Narrow tunnels and pipes."),
        Ability("charge", "Charge Beam", SRX, "Charge Core-X",
            "After the fourth atmospheric stabilizer, up the shaft beside its room and through the eye door. The Core-X is hiding as a Chozo statue.",
            "Charged shots, the only thing some enemies and blockers take damage from."),
        Ability("bombs", "Bombs", TRO, "Data Room",
            "From the big green room near the entrance, bottom-left door, across the corridor and up to the top-left door. Needs the Level 1 doors open.",
            "Bomb blocks, Bomb-raised pillars, and most hidden tunnels."),
        Ability("hijump", "Hi-Jump and Jumpball", TRO, "Zazabi",
            "Deep in Sector 2, at the bottom of a long trip down through the maze.",
            "Higher ledges, and jumping (and bombing) in Morph Ball form."),
        Ability("speed", "Speed Booster", AQA, "Serris",
            "Near the old Serris tank in Sector 4.",
            "Speed Booster blocks, and Shinesparks (see the Shinespark hint)."),
        Ability("super", "Super Missiles", PYR, "Data Room",
            "Top of the huge red shaft lined with dragon heads, then left and along the upper hallway. Needs the Level 2 doors.",
            "Super Missile blocks, and Gerons (the purple blockers)."),
        Ability("varia", "Varia Suit", NOC, "Mega Core-X",
            "At the Sector 6 Data Room: a Core-X steals the download, and you take it back.",
            "Super-hot and super-cold rooms, and blue X stop hurting (they heal you instead)."),
        Ability("icemissile", "Ice Missiles", ARC, "Data Room",
            "Down from the big room of Chute Leeches, through the frozen room and past the shutter. Needs the Level 3 doors.",
            "Freezing enemies to use as steps."),
        Ability("wide", "Wide Beam", PYR, "Wide Core-X",
            "In the Main Boiler control room, during the Sector 3 meltdown.",
            "A three-shot spread that's much easier to aim."),
        Ability("pb", "Power Bombs", ARC, "Data Room",
            "Same Data Room as the Ice Missiles.",
            "Power Bomb blocks, and a flash that shows hidden blocks on screen."),
        Ability("space", "Space Jump", MAIN_DECK, "Yakuza",
            "Through the Central Reactor Core's top-right door, down the shaft past the eye door.",
            "Most heights; nearly every late item needs it."),
        Ability("plasma", "Plasma Beam", TRO, "Nettori",
            "Reached through the Reactor Silo, at the root of the overgrowth.",
            "Shots that pass through enemies."),
        Ability("gravity", "Gravity Suit", ARC, "Nightmare",
            "Deep in the unexplored parts of Sector 5.",
            "Free movement underwater, and lava stops hurting."),
        Ability("diffusion", "Diffusion Missiles", AQA, "Data Room",
            "Behind the Level 4 doors, after the Gravity Suit.",
            "Charge a missile (hold R) for a freezing blast; it also clears blowfish walls."),
        Ability("wave", "Wave Beam", NOC, "B.O.X. II",
            "In the watery corridor past the Restricted Zone.",
            "Shots through walls, handy for one-way panel doors."),
        Ability("screw", "Screw Attack", SRX, "Ridley-X",
            "Just after the elevator up from the Restricted Lab.",
            "Screw Attack blocks, and with the Space Jump, the rest of the station."),
        Ability("icebeam", "Ice Beam", MAIN_DECK, "The SA-X",
            "At the very end, in the Docking Bay.",
            "The last fight."),
    )

    val bosses: List<Boss> = listOf(
        Boss("arachnus", "Arachnus-X", MAIN_DECK, "Morph Ball", "Its front", listOf(
            "Missile its front; its back deflects shots.",
            "When it spits fire, hang from the holes in the wall until the flames die down. Jump its blue sonic waves.",
            "When it curls up and rolls, hang from a wall and let it pass.",
            "Then Missile the shelled Core-X and absorb it once the shell breaks.",
        )),
        Boss("chargecore", "Charge Core-X", SRX, "Charge Beam", "Its eye", listOf(
            "It sits disguised as a Chozo statue. Missile the eye when it opens, then jump the beam it fires back.",
            "Don't leave it alone too long: a closed eye builds up to a shot.",
        )),
        Boss("zazabi", "Zazabi", TRO, "Hi-Jump and Jumpball", "Inside its mouth", listOf(
            "Run under it as it hops around.",
            "When it opens its base wide at the top of a jump and floats down, Missile up into it, then step aside before it lands.",
            "If it swallows you, bomb until it spits you out. It shrinks as you hit it, then turns back into a Core-X.",
        )),
        Boss("serris", "Serris", AQA, "Speed Booster", "Its head", listOf(
            "Missile or charge-shoot its head. Stand on the middle platforms so you can grab the ceiling rungs.",
            "It glows and speeds up after every hit; wait for it to slow down. Underwater, a puff of dirt shows where its head will come out.",
            "It only takes a few hits.",
        )),
        Boss("box", "B.O.X.", PYR, null, "The center of its body", listOf(
            "Hang from the ceiling rungs and fire Super Missiles straight down into its middle.",
            "When it crouches it throws a bomb: get directly above the bomb as it bursts, and the two walls of fire miss you.",
            "It escapes through the ceiling when it's beaten; it's back later.",
        )),
        Boss("megacore", "Mega Core-X", NOC, "Varia Suit", "Its main body", listOf(
            "Only charged shots hurt it; Missiles do nothing until it's a plain Core-X.",
            "Charge-shoot the small cores around it out of the way, stay out of the water and dodge its rushes.",
        )),
        Boss("widecore", "Wide Core-X", PYR, "Wide Beam", "Its eye", listOf(
            "In the boiler control room. Missile the eye when it opens, then jump the beam.",
        )),
        Boss("yakuza", "Yakuza", MAIN_DECK, "Space Jump", "Its mouth", listOf(
            "Before it drops in, fire Missiles straight up for free hits. Stay rolled up in Morph Ball near a wall, and never let it grab you: it drains a lot.",
            "When it stops and opens its mouth, Missile up into it, then roll aside before its three fireballs land.",
            "Once its legs fall off it bounces around spitting debris: keep shooting into its mouth at the top of each bounce.",
        )),
        Boss("nettori", "Nettori", TRO, "Plasma Beam", "Its body", listOf(
            "Stand on the platform near it, out of the biting plants in the floor. If one catches you, jump straight up to escape, not Space Jump.",
            "Shoot the spores from the two flowers near the ceiling, and pound Nettori with Missiles or charged shots.",
            "Once the flowers are gone it fires Plasma bursts; duck or jump them.",
        )),
        Boss("nightmare", "Nightmare", ARC, "Gravity Suit", "The gravity device, then its face", listOf(
            "First, the round gravity device between its arms: hit it from below with Missiles, or charged shots once its gravity drags your Missiles down.",
            "Then its face: climb the left-wall ladder and fire across when it drifts toward you, and Space Jump away when it rushes.",
        )),
        Boss("box2", "B.O.X. II", NOC, "Wave Beam", "The center of its body", listOf(
            "The water is electrified. Power Bomb the ceiling to uncover a ladder and stay on it.",
            "Shoot down its homing missiles, and Super Missile its middle at an angle rather than from straight above.",
            "Its Core-X fires back: shoot when it opens, then jump.",
        )),
        Boss("ridley", "Ridley-X", SRX, "Screw Attack", "Anywhere but the tail", listOf(
            "Charged shots do the most damage (the Wave Beam passes through it); Super Missiles work too. Hold L to aim up.",
            "Dodge the fireballs and tail swipes. If it grabs you, keep firing until it lets go.",
        )),
        Boss("sax", "SA-X", MAIN_DECK, "Ice Beam (at the end)", "Anywhere, between Screw Attacks", listOf(
            "Keep your distance; up close it Screw Attacks you over and over.",
            "Hit it with charged shots as it lands, then get away before its next Screw Attack. Ice Missiles freeze it briefly.",
        )),
        Boss("omega", "Omega Metroid", MAIN_DECK, null, "Its body", listOf(
            "The first hit is scripted and leaves you at 1 energy. Absorb the SA-X's core to refill and get the Ice Beam.",
            "Hold L to aim up and keep firing. Its claws knock you down and stun you, so stay out of reach.",
        )),
    )

    val hints: List<MfPage> = listOf(
        MfPage("h_locks", "Security locks", "Which doors open when", listOf(
            Section(null, listOf(
                "Doors are locked by security level, and each level opens at a Security Room:",
                "Level 1 (blue): Sector 2. Level 2 (green): Sector 3. Level 3 (yellow): Sector 5. Level 4 (red): Sector 4.",
                "A Navigation Room briefing usually tells you which one is next.",
            )),
        )),
        MfPage("h_sax", "The SA-X", "Run, don't fight", listOf(
            Section(null, listOf(
                "Until the very end you can't beat the SA-X. When it shows up, get out of its sight: duck into a tunnel or keep running.",
                "The scripted encounters always leave you a way out; look for the gap or the floor you can shoot through.",
            ), tone = Tone.WARNING),
        )),
        MfPage("h_x", "X parasites", "Free-floating X", listOf(
            Section(null, listOf(
                "Absorbing a floating X restores energy or ammo.",
                "Blue X are cold: they hurt you until you have the Varia Suit, then they heal you.",
                "Kill an enemy and leave its X alone, and it re-forms as a new creature. Some items need that, like the Chute Leech step in Sector 5.",
                "Out of ammo in a Core-X fight? Shoot it with your beam to knock loose more X.",
            )),
        )),
        MfPage("h_walljump", "Wall jumping", "Climbing shafts early", listOf(
            Section(null, listOf(
                "Spin-jump into a wall, then press jump again and push away from it as you touch. Your feet need to be on the wall.",
                "A few early items need it; the Space Jump makes them easy later.",
            )),
        )),
        MfPage("h_shine", "Shinespark", "Needed for a lot of items", listOf(
            Section(null, listOf(
                "Run until the Speed Booster kicks in, then press Down to crouch: Samus flashes, holding the charge for a few seconds.",
                "Jump to launch in the direction you hold: straight up, sideways or diagonally.",
                "Shinesparking into a slope turns back into a run, so you can crouch and re-store the charge. That's how the long multi-room sparks are done.",
            ), numbered = true),
            Section("Watch it first", listOf(
                "The steps for the Shinespark items work, but they're hard to follow from text while you're playing.",
                "A short video makes the timing and positions obvious. Each Shinespark item's page suggests a YouTube search for it.",
            ), tone = Tone.SECRET),
        )),
    )
}
