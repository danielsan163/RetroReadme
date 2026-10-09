package com.retroreadme.games.sm

import com.retroreadme.core.Section
import com.retroreadme.core.Tone

object SmPages {

    /** The 16 upgrades in route order, for the Upgrades tab. */
    val upgradeOrder = listOf(
        "u_morph", "u_bomb", "u_charge", "u_spazer", "u_hijump", "u_varia", "u_speed", "u_ice",
        "u_grapple", "u_wave", "u_xray", "u_gravity", "u_space", "u_spring", "u_plasma", "u_screw",
    )

    /** What each upgrade opens up, shown on its Upgrades page. */
    val unlocks = mapOf(
        "u_morph" to "Narrow tunnels. Bombs and Power Bombs are laid in Morph Ball form.",
        "u_bomb" to "Bomb blocks, hidden floors and walls, and bomb-jumping.",
        "u_charge" to "Hold Fire for a stronger shot. Spin-jump while charged to hurt enemies you touch.",
        "u_spazer" to "Three shots side by side, stronger than the Power Beam. Can't be combined with the Plasma Beam.",
        "u_hijump" to "Higher jumps, onto ledges you couldn't reach before.",
        "u_varia" to "Norfair's heated rooms stop draining energy, and enemies hit for less.",
        "u_speed" to "Run long enough and you smash through enemies and Speed Booster blocks. Also the Shinespark (see Techniques).",
        "u_ice" to "Freeze enemies to stand on them. Needed against Metroids.",
        "u_grapple" to "Swing from Grapple blocks and some enemies, and pull electricity from Draygon's turrets.",
        "u_wave" to "Shots pass through walls. Opens some gates and shutters.",
        "u_xray" to "Hold Dash with it selected to see hidden blocks, passages and items. The game freezes while you look.",
        "u_gravity" to "Free movement underwater and in lava, and even less damage.",
        "u_space" to "Spin-jump again in mid-air, as many times as you like.",
        "u_spring" to "Jump in Morph Ball form.",
        "u_plasma" to "The strongest beam; shots pass through enemies. Can't be combined with the Spazer.",
        "u_screw" to "Spin-jumps destroy most enemies and Screw Attack blocks.",
    )

    val bosses: List<Boss> = listOf(
        Boss(
            "b_ceres", "Ridley (Ceres)", "Ceres Station",
            listOf("The opening on Ceres station, where Ridley steals the Metroid. You can't beat him here."),
            weakPoint = "None. Survive",
            strategy = listOf(
                "Jump over his fireballs and keep shooting. Enough hits, or letting your energy drop very low, ends the fight either way.",
                "Then you have one minute to get back to your ship.",
            ),
        ),
        Boss(
            "b_torizo", "Torizo", "Crateria",
            listOf("The Chozo Statue holding the Bombs. It comes to life once you take them."),
            weakPoint = "Missiles to the chest",
            strategy = listOf(
                "Fire Missiles at its chest while it faces you; shots from behind miss.",
                "Jump over it as it marches back and forth. Shoot its little bombs for refills.",
                "Once its head breaks it speeds up. Beam shots finish it.",
            ),
        ),
        Boss(
            "b_spore", "Spore Spawn", "Brinstar",
            listOf("Pink Brinstar, through the door at the top of the big pink room. Beating it opens the shaft to your first Super Missiles."),
            weakPoint = "Missiles into the open core",
            strategy = listOf(
                "It swings around the room. Stay in a corner, crouched or morphed, out of its path.",
                "When it opens up, fire Missiles into the core. Shoot the falling spores for refills.",
                "It swings faster as it weakens. Keep to the corners and wait for each opening.",
            ),
        ),
        Boss(
            "b_kraid", "Kraid", "Kraid's Lair (Brinstar)",
            listOf("The bottom of Kraid's Lair, reached from Brinstar's red area. Gives the Varia Suit."),
            weakPoint = "Super Missiles into his mouth",
            strategy = listOf(
                "When his mouth opens, fire a Super Missile in. The first one makes him rise up and break the ceiling into small platforms.",
                "Stay on the platforms above the spikes, dodging his claws and the spines from his belly.",
                "Keep hitting the mouth each time it opens. Missiles or charged shots work once the Supers run out.",
            ),
        ),
        Boss(
            "b_croc", "Crocomire", "Norfair",
            listOf("The long corridor in Norfair's west, through a door in the floor. Beating it opens the way to the Grappling Beam."),
            weakPoint = "Shots in the mouth push it back",
            strategy = listOf(
                "You can't damage it; push it back off the crumbling bridge on the right into the lava.",
                "Every shot into its open mouth pushes it back: Super Missiles furthest, then charged beam shots, then Missiles. Don't get pinned against the spikes on the left.",
                "Don't use Power Bombs: they make it charge.",
            ),
        ),
        Boss(
            "b_phantoon", "Phantoon", "Wrecked Ship",
            listOf("Deep in the Wrecked Ship, behind an eye door. Beating it brings back the ship's power."),
            weakPoint = "Its eye, when it opens",
            strategy = listOf(
                "It drifts around dropping blue flames, then stops and opens its eye: that's your only chance to hit it.",
                "Super Missiles hurt most, but make it rain flames from the top of the room. Morph and roll between corners to dodge them, or shoot them for refills.",
                "Charged beam shots are slower but safer.",
            ),
        ),
        Boss(
            "b_botwoon", "Botwoon", "Maridia",
            listOf("Halfway through Maridia, a serpent weaving between holes in the wall. Beating it opens the way on to Draygon."),
            weakPoint = "Its head",
            strategy = listOf(
                "Only hits to the head count. It's easiest when it stops to spit at you.",
                "Super Missiles are quickest. It speeds up as it weakens, so angle up and keep firing.",
            ),
        ),
        Boss(
            "b_draygon", "Draygon", "Maridia",
            listOf("The bottom of Maridia. Gives the Space Jump and unseals the way to the Plasma Beam."),
            weakPoint = "Its belly, or the turrets' electricity",
            strategy = listOf(
                "Before it shows up, destroy the four wall turrets.",
                "Shoot the grey slime it spits; if it sticks, mash left and right to break free.",
                "The quick way: let it grab you, then Grapple one of the broken, sparking turrets. The current runs through you into Draygon and finishes it fast.",
                "Otherwise, Super Missiles into its belly, morphing to dodge its swoops.",
            ),
        ),
        Boss(
            "b_gtorizo", "Golden Torizo", "Ridley's Lair (Norfair)",
            listOf("Ridley's Lair. It drops in when you walk up to a grey door. Behind it is the Screw Attack."),
            weakPoint = "Charged beam shots",
            strategy = listOf(
                "It catches Super Missiles and throws them back, and normal Missiles don't hurt it. Use charged beam shots.",
                "Shoot the bombs it spits for refills. It dulls from gold to grey as it weakens.",
            ),
        ),
        Boss(
            "b_ridley", "Ridley", "Ridley's Lair (Norfair)",
            listOf("The bottom of Ridley's Lair, after two grey guards that only take damage while they're yellow."),
            weakPoint = "Head and body, not the tail",
            strategy = listOf(
                "Angle up and empty your Super Missiles into him, then switch to charged beam shots.",
                "He flies around the room swinging his tail and spitting fire. Space Jump out of his swoops.",
                "If he grabs you, keep firing until he lets go. Full Reserve Tanks help here.",
            ),
        ),
        Boss(
            "b_mb", "Mother Brain", "Tourian",
            listOf("The end of Tourian. The statue in Crateria only opens the way once Kraid, Phantoon, Draygon and Ridley are beaten."),
            weakPoint = "The brain, then its head",
            strategy = listOf(
                "First the brain in its glass tank: break the glass with Missiles, then fire Supers into the hole. Watch for turret fire and the rings.",
                "It rises up on a huge body. Fire Supers and charged shots at the head and jump its beams and bombs.",
                "It's scripted to overpower you near the end. Hang on: help arrives, and you get a new beam to finish it.",
                "Then three minutes to get back to your ship.",
            ),
        ),
    )

    val techniques: List<SmPage> = listOf(
        SmPage("t_shine", "Shinespark", "Needed for a dozen items", listOf(
            Section(null, listOf(
                "Run until the Speed Booster kicks in, then press Down: Samus flashes, storing the charge for a few seconds. You can walk and spin-jump while it lasts.",
                "Press Jump to launch: straight up with no direction, sideways with Jump then Left or Right, diagonally with an angle button held.",
                "It smashes through Speed Booster blocks on the way.",
            ), numbered = true),
            Section("Watch it first", listOf(
                "The steps for the Shinespark items work, but they're hard to follow from text while you're playing.",
                "A short video makes the timing and positions obvious. Each Shinespark item's page suggests a YouTube search for it.",
            ), tone = Tone.SECRET),
        )),
        SmPage("t_walljump", "Wall-jump", "Climbing without upgrades", listOf(
            Section(null, listOf(
                "Spin-jump against a wall, press away from it, then press Jump straight after (one then the other, not together).",
                "The Etecoons (small green creatures) in Brinstar show it off in their shaft. A few items are much easier with it, and some upgrades can wait if you're good at it.",
            )),
        )),
        SmPage("t_bombs", "Bomb tricks", "Bomb-jumping and more", listOf(
            Section(null, listOf(
                "A bomb under you bounces you up. Lay them with a steady rhythm to climb before you have the Spring Ball.",
                "A Power Bomb's blast shows hidden blocks and tunnels it reaches.",
            )),
        )),
        SmPage("t_blocks", "Blocks and hidden things", "What breaks what", listOf(
            Section(null, listOf(
                "Many items hide in walls, ceilings and floors. Shoot or bomb anything that looks odd; the X-Ray Scope shows them all.",
                "Bomb, Missile-shaped, Super Missile and Power Bomb blocks only break to that weapon; Speed Booster blocks need a run or a Shinespark; Screw Attack blocks need the Screw Attack.",
                "Crumble blocks and false floors drop you, and some spikes are fake. Several items sit right next to one.",
                "Door colors: blue opens to any shot, red (pink) takes five Missiles or one Super Missile, green a Super Missile, yellow a Power Bomb.",
            )),
        )),
        SmPage("t_grapple", "Grappling and freezing", "Getting across gaps", listOf(
            Section(null, listOf(
                "Fire the Grappling Beam at a Grapple block (or a Ripper) and swing: hold left and right to build up the arc, and let go at the top.",
                "Frozen enemies are solid platforms. Wavers, Sovas and Boyons are all used as steps for items.",
            )),
        )),
    )

    val hints: List<SmPage> = listOf(
        SmPage("h_100", "What 100% takes", "100 items", listOf(
            Section(null, listOf(
                "16 upgrades, 14 Energy Tanks, 4 Reserve Tanks, 46 Missile Tanks, 10 Super Missile Tanks and 10 Power Bomb Tanks.",
                "Kraid's Lair is on Brinstar's map and Ridley's Lair on Norfair's, so this guide files them there too.",
            )),
        )),
        SmPage("h_reserve", "Reserve Tanks", "Backup energy", listOf(
            Section(null, listOf(
                "Reserve Tanks fill with energy you pick up once your Energy Tanks are full. When your energy runs out, they refill it.",
                "On the Samus screen (Start, then R) you can switch them to Auto, or use them by hand.",
            )),
        )),
        SmPage("h_toggle", "Switching gear off", "Spazer and Plasma", listOf(
            Section(null, listOf(
                "On the Samus screen you can switch any upgrade on or off. The Spazer and Plasma Beam can't be on together.",
                "Some places want a beam off, like the Spring Ball or Screw Attack in tight rooms.",
            )),
        )),
        SmPage("h_tourian", "The way into Tourian", "Four bosses first", listOf(
            Section(null, listOf(
                "The statue room in Crateria shows Kraid, Phantoon, Draygon and Ridley. Once all four are beaten, it opens the way down to Tourian.",
                "Clear up items before then: after Mother Brain you only have three minutes to reach your ship.",
            ), tone = Tone.WARNING),
        )),
    )
}
