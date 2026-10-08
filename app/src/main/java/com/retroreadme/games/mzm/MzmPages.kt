package com.retroreadme.games.mzm

import com.retroreadme.core.Section
import com.retroreadme.core.Tone

object MzmPages {

    /** The 14 upgrades in the usual pickup order, for the Upgrades tab. */
    val upgradeOrder = listOf(
        "u_morph", "u_long", "u_charge", "u_bomb", "u_plasma", "u_grip", "u_ice",
        "u_spacejump", "u_speed", "u_hijump", "u_varia", "u_wave", "u_gravity", "u_screw",
    )

    /** What each upgrade opens up, shown on its Upgrades page. */
    val unlocks = mapOf(
        "u_morph" to "Narrow tunnels, and Morph Ball Launchers.",
        "u_long" to "Beam shots reach across the whole screen.",
        "u_charge" to "Hold B for a stronger shot; you can also spin-jump while charged.",
        "u_bomb" to "Bomb blocks, hidden floors and walls, and bomb-jumping.",
        "u_plasma" to "Breaks its engraved blocks right away. Works as a beam after the Ruins Test.",
        "u_grip" to "Grab and climb ledges, and jump in Morph Ball form.",
        "u_ice" to "Freeze enemies to stand on them. Needed against Metroids.",
        "u_spacejump" to "Breaks its engraved blocks right away. Infinite spin-jumps after the Ruins Test.",
        "u_speed" to "Speed Booster blocks, and the Shinespark.",
        "u_hijump" to "Higher jumps, the spring jump in Morph Ball form, and Morph Ball Shinesparks.",
        "u_varia" to "Super-heated rooms and acid, and less damage from enemies.",
        "u_wave" to "Shots pass through walls, revealing some hidden items.",
        "u_gravity" to "Breaks its engraved blocks right away. Free movement in liquids and lava after the Ruins Test.",
        "u_screw" to "Screw Attack blocks, and invincible spin-jumps that destroy enemies.",
    )

    val bosses: List<Boss> = listOf(
        Boss(
            "b_deorem", "Deorem", Area.BRINSTAR,
            listOf(
                "The giant worm in the corridor leading to Norfair's elevator. It attacks once you have your first Missiles; if it escapes, it turns up again in the corridors on the way to the Bombs.",
                "Beating it gives the Charge Beam.",
            ),
            weakPoint = "Missiles to the eye",
            strategy = listOf(
                "Its body boxes you in on both sides. When the pincers twitch, the head lunges down: at first straight down, later wherever you're standing. Step (or morph) out of the way.",
                "As the head pulls back up, the eye opens for a moment. Get under it and fire one Missile up into it.",
                "Between lunges the body fires small spikes; keep away from the sides, or shoot them for energy and Missiles.",
                "Three hits finish it. Take too long and it retreats to fight again later.",
            ),
        ),
        Boss(
            "b_acidworm", "Acid Worm", Area.KRAID,
            listOf(
                "Fought after you power up the Zip Lines. Once it's beaten the acid drains, opening the way to Missile Tank 4 underneath.",
            ),
            weakPoint = "Missiles to the glowing spots under its pincers",
            strategy = listOf(
                "Ride the Zip Line across to start. The worm destroys the middle platform, so you're left with one platform on each side.",
                "Stand on one side with the Zip Line's clamp on your side (shoot the switch if it isn't). When its pincers flex, grab the Zip Line and ride across.",
                "The worm bites the empty platform and gets stuck. Turn around and fire Missiles into the weak spot.",
                "After enough damage it makes the acid rise, then fall. Wait on high ground, and drop back down as soon as the acid clears to be ready for the next bite.",
                "Stay out of the acid, and shoot the spores it throws near the doors for refills.",
            ),
        ),
        Boss(
            "b_kraid", "Kraid", Area.KRAID,
            listOf("At the bottom of his lair. The Speed Booster is in the room right behind him."),
            weakPoint = "Missiles into his open mouth",
            strategy = listOf(
                "Hit his head with a Missile or a charged shot to make him open his mouth, then jump from the platform by the door and fire into it.",
                "He swipes with his claw and sends spikes floating at you; shoot the spikes for energy and Missiles.",
                "As the fight goes on, the platform you've been using breaks up. The big spikes he fires from his belly stick in the right wall: climb them to reach his mouth (they explode when they flash).",
                "The small ledge above the door is a safe spot to catch your breath, especially on Hard.",
            ),
        ),
        Boss(
            "b_larvae", "Kiru Giru larvae", Area.NORFAIR,
            listOf("Two caterpillar larvae in a long tunnel in Norfair; the door locks behind you. Beating both opens the right side and reveals Energy Tank 1 in the ceiling."),
            weakPoint = "Their undersides",
            strategy = listOf(
                "The first larva: fire the Wave Beam down through the floor into its underside.",
                "The second charges at you when you get close; back off left. Shooting its face only pushes it back.",
                "Push it back, morph, roll in front of it and lay bombs just as it rears up to charge, so they go off under it. Two or three rounds does it.",
            ),
        ),
        Boss(
            "b_kirugiru", "Ensnared Kiru Giru", Area.NORFAIR,
            listOf("Hangs from vines in a Norfair room. It isn't gone for good: it sinks through the floor and later becomes Imago."),
            weakPoint = "Missiles to the vines holding it up",
            strategy = listOf(
                "Its only attack is a spread of spores every few seconds; they're weak, and shooting them drops refills.",
                "Wait for the Ripper to drift to one side, freeze it, and stand on it to reach the vines. Re-freeze it before it thaws.",
                "The middle vine can't be destroyed and blocks your shots, so clear one side, then move the Ripper to the other and repeat.",
                "Don't stand right underneath when it drops.",
            ),
        ),
        Boss(
            "b_imago", "Imago", Area.RIDLEY,
            listOf(
                "Kiru Giru, grown up. Reached from Ensnared Kiru Giru's room, where it sank through the floor. Beating it gives Super Missile Tank 1, which you need to get deeper into Ridley's Lair.",
            ),
            weakPoint = "Missiles (or Super Missiles) to the stinger",
            strategy = listOf(
                "The room slopes down from left to right. Its main attack is simply ramming you.",
                "Wait at the high left end, morphed. As it flies over you heading right, pop up and run right behind it, firing at the stinger.",
                "When it gets out of reach, go back to the left end and wait for the next pass. Staying behind it also avoids the stinger shots it fires once hurt.",
                "It turns green, then red. When the stinger breaks off it flies away and crashes into the enclosure on the left, opening the way to the Super Missile Tank.",
            ),
        ),
        Boss(
            "b_ridley", "Ridley", Area.RIDLEY,
            listOf("At the end of his lair. Unknown Item 3 and Energy Tank 2 are in the room past his."),
            weakPoint = "Missiles and Super Missiles to his front; his tail can't be hurt",
            strategy = listOf(
                "He opens with a flood of fireballs: jump between them. Later he fires wavy small fireballs and straight lines of big ones.",
                "Avoid his tail, which does heavy damage. For his pogo attack (bouncing on his tail), run under him as he bounces up and hit him from the front.",
                "Stay on the main floor; both edges drop into lava.",
                "When he grabs you, he stops moving: fire Super Missiles straight into his face while he holds you. Save them for this.",
                "Don't hold back your regular Missiles. The fight is a race, and a long one goes badly.",
            ),
        ),
        Boss(
            "b_motherbrain", "Mother Brain", Area.TOURIAN,
            listOf(
                "The end of the original mission. After her, the escape sequence starts and the story continues in Chozodia.",
                "Later, a Super Missile into the floor under her remains opens the way to Tourian's Power Bomb Tank.",
            ),
            weakPoint = "Super Missiles to her eye",
            strategy = listOf(
                "First destroy the Zebetite barriers with Missiles, quickly: they regrow if you stop. Save your Super Missiles.",
                "At Mother Brain you have two small blocks over lava. Wall turrets fire constantly and Rinkas float at you; the Screw Attack protects you from the Rinkas, which also drop refills.",
                "Break her glass with regular Missiles.",
                "Once she's exposed, her eye opens and fires a big blast across the screen after flashing. Get out of its path (on top of the right-hand turret is safe), then crouch on a block and put a Super Missile in the eye while it stays open. About six hits.",
                "When she's destroyed, the escape timer starts.",
            ),
        ),
        Boss(
            "b_ruins", "Ruins Test", Area.CHOZODIA,
            listOf("The Chozo Warrior engraving at the top of the Chozo Ruins. Beating it restores your suit, fully powered, and switches on all three Unknown Items."),
            weakPoint = "The mirror, only while it shows a symbol",
            strategy = listOf(
                "Watch the mirror it holds. While it shows your reflection, don't shoot it: hitting the reflection (or the warrior itself) hurts you.",
                "When the reflection vanishes and a glowing symbol appears, hit the mirror with a fully charged shot. Each hit fills one of the four corner sockets.",
                "After the first hit, a ghostly copy of the warrior flies around the room. When the painting glows, lightning strikes right above you, so keep moving.",
                "Each hit shortens how long the symbol shows. By the last one, lightning also runs along the floor: jump over it.",
                "The fourth hit fills the last socket and your suit comes back.",
            ),
        ),
        Boss(
            "b_mecharidley", "Mecha Ridley", Area.CHOZODIA,
            listOf("The final boss, at the top of the Space Pirate ship. Energy Tank 1 in Chozodia is in the room next to it."),
            weakPoint = "Super Missiles to the core in its chest",
            strategy = listOf(
                "It stays on the right; you're on the left. Its claw swipe hits hard, so Space Jump up against the left wall: almost every attack misses you there.",
                "It fires fireballs, and later eye lasers, in an upper and a lower diagonal. Hover around the middle of the wall's height to slip between them.",
                "Break the glass over the core with regular Missiles, then switch to Super Missiles. You can only hit the core while the head is raised, right after a claw swipe.",
                "Once damaged it launches three homing missiles; Screw Attack them for refills.",
                "It changes color from blue to purple to red as it weakens. It's much tougher if you arrive with all 100 items.",
            ),
        ),
    )

    val techniques: List<MzmPage> = listOf(
        MzmPage("t_shine", "Shinespark", "Needed for a dozen items", listOf(
            Section(null, listOf(
                "Run until the Speed Booster kicks in, then press Down to crouch: Samus flashes, storing the charge for a few seconds.",
                "Jump (A) to launch in the direction you hold: straight up, sideways, or diagonally. With the Hi-Jump Boots you can also Shinespark in Morph Ball form.",
                "Shinesparking into a slope turns back into a run, so you can crouch again and re-store the charge. That's how the long multi-room sparks are done.",
                "You can also launch in mid-air: spin-jump and press A while charged.",
            ), numbered = true),
            Section("Watch it first", listOf(
                "The steps for the Shinespark items work, but they're hard to follow from text while you're playing, especially the long multi-room ones.",
                "A short video makes the timing and positions obvious. Each Shinespark item's page suggests a YouTube search for it.",
            ), tone = Tone.SECRET),
        )),
        MzmPage("t_bombjump", "Bomb jumping", "Reaching high places early", listOf(
            Section(null, listOf(
                "In Morph Ball form, lay bombs repeatedly with a slow, steady rhythm: each blast bumps you up onto the next bomb.",
                "Too fast and the bombs go off together; too slow and you fall. Energy Tank 1 in Brinstar is good practice.",
            )),
        )),
        MzmPage("t_blocks", "Blocks and launchers", "What breaks what", listOf(
            Section(null, listOf(
                "Speed Booster blocks (arrow symbols): run or Shinespark through them.",
                "Bomb blocks, Missile blocks, Super Missile blocks and Power Bomb blocks: only that weapon breaks them.",
                "Crumbling and fake blocks: some floors collapse after you stand on them, some aren't solid at all. Several items sit behind them.",
                "Engraved blocks for the Unknown Items can be broken as soon as you pick up the matching item.",
                "Morph Ball Launchers hide under bombable floors and fire you straight up.",
                "Zip Lines in Kraid's Lair only run once you roll into the power conduit near the Acid Worm's room.",
            )),
        )),
        MzmPage("t_grip", "Power Grip and freezing", "Getting onto ledges", listOf(
            Section(null, listOf(
                "Hold toward a ledge while falling or jumping to grab it, then climb up. It also lets you jump while in Morph Ball form.",
                "Frozen enemies are solid platforms. Ripper, bugs from floor pipes and jumping lava creatures are all used as steps for items.",
            )),
        )),
    )

    val hints: List<MzmPage> = listOf(
        MzmPage("h_100", "What 100% takes", "100 items", listOf(
            Section(null, listOf(
                "14 upgrades, 12 Energy Tanks, 50 Missile Tanks, 15 Super Missile Tanks and 9 Power Bomb Tanks.",
                "After beating the game, the pause screen shows how many expansions of each kind you've found in each area.",
            )),
        )),
        MzmPage("h_late", "Come back later", "Items tagged After Chozodia", listOf(
            Section(null, listOf(
                "Power Bombs only turn up after Mother Brain, in Chozodia, and the Gravity Suit and Space Jump only work after the Ruins Test.",
                "So a lot of items (all 9 Power Bomb Tanks, and several in every area) are a return trip. Their rows say so, and the Progress page counts them.",
                "It's common to beat the game once you reach Chozodia and sweep up the rest afterward.",
            ), tone = Tone.WARNING),
        )),
        MzmPage("h_unknown", "The Unknown Items", "Plasma Beam, Space Jump, Gravity Suit", listOf(
            Section(null, listOf(
                "The three Unknown Items can't be used when you find them, but each one already breaks the blocks engraved with its picture.",
                "Beating the Ruins Test restores your suit and switches all three on.",
            )),
        )),
        MzmPage("h_hard", "Hard mode", "Unlocked by beating the game", listOf(
            Section(null, listOf(
                "Enemies hit harder and are placed differently.",
                "Expansions give half as much: an Energy Tank adds 50, a Missile Tank 2, a Super Missile or Power Bomb Tank 1.",
                "Only what each expansion gives changes, not where it is, so this guide covers both modes.",
            )),
        )),
        MzmPage("h_zero", "The Zero Suit section", "After Mother Brain", listOf(
            Section(null, listOf(
                "After Mother Brain, Samus crash-lands and has to sneak through the Space Pirate ship without her Power Suit.",
                "Chozodia's items are collected once your suit is restored at the Ruins Test, so don't worry about missing them on the way in.",
            )),
        )),
    )
}
