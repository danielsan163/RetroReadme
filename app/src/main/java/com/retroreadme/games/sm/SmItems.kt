package com.retroreadme.games.sm

import com.retroreadme.games.sm.Area.BRINSTAR
import com.retroreadme.games.sm.Area.CRATERIA
import com.retroreadme.games.sm.Area.MARIDIA
import com.retroreadme.games.sm.Area.NORFAIR
import com.retroreadme.games.sm.Area.WRECKED_SHIP
import com.retroreadme.games.sm.Kind.ENERGY
import com.retroreadme.games.sm.Kind.MAJOR
import com.retroreadme.games.sm.Kind.MISSILE
import com.retroreadme.games.sm.Kind.POWER_BOMB
import com.retroreadme.games.sm.Kind.RESERVE
import com.retroreadme.games.sm.Kind.SUPER

/**
 * All 100 items: 16 upgrades, 14 Energy Tanks, 4 Reserve Tanks, 46 Missile, 10 Super Missile
 * and 10 Power Bomb Tanks. Numbering within each area follows Metroid Recon's lists (Kraid's
 * Lair counts as Brinstar and Ridley's Lair as Norfair, as on the in-game maps). Every location
 * was checked against Metroid Recon and Budwin's 100% walkthrough.
 */
object SmItems {

    private val code = mapOf(
        CRATERIA to "cr", BRINSTAR to "br", NORFAIR to "no", WRECKED_SHIP to "ws", MARIDIA to "ma",
    )
    private val letter = mapOf(ENERGY to "e", RESERVE to "r", MISSILE to "m", SUPER to "s", POWER_BOMB to "p")

    private fun up(id: String, area: Area, name: String, needs: List<String>, vararg steps: String) =
        Item("u_$id", area, MAJOR, name, needs, steps.toList())

    private fun t(
        area: Area, kind: Kind, n: Int, needs: List<String>, vararg steps: String, confirm: Boolean = false,
    ) = Item("${code.getValue(area)}_${letter.getValue(kind)}$n", area, kind, "${kind.label} $n", needs, steps.toList(), confirm)

    private const val SHINE = SHINESPARK
    private const val PB = "Power Bombs"
    private const val SUPERS = "Super Missiles"

    val all: List<Item> = listOf(

        // ---------------------------------------------------------------- Crateria

        up("bomb", CRATERIA, "Bombs", listOf("Morphing Ball", "Missiles"),
            "From the landing site, through the caves to the left. Roll down the small opening in the floor near the Skree, then follow the corridor to the purple Missile door at the end.",
            "Take the Bombs from the Chozo Statue; it comes to life as Torizo (see Bosses). It's in CR-04."),

        t(CRATERIA, MISSILE, 1, listOf("Bombs"),
            "Down the main shaft, past the Save Room door. Through the next door down, bomb through the narrow tunnel and go left to the Chozo Statue holding it. The tank is in CR-05."),
        t(CRATERIA, MISSILE, 2, listOf("Grappling Beam"),
            "In the flooded cavern on the way to the Wrecked Ship, on top of a thin pillar above the water. Grapple across the blocks in the ceiling and drop onto it. The tank is in CR-10."),
        t(CRATERIA, MISSILE, 3, listOf("Bombs"),
            "Old Tourian: at the bottom of the shaft full of wall-crawling Space Pirates, go right into the room where Mother Brain sat in the first game. Bomb the middle of the big tank to open a way down to it. The tank is in CR-23."),
        t(CRATERIA, MISSILE, 4, emptyList(),
            "At the very top of the open sky above the Wrecked Ship, hidden in a rocky overhang. Ride the moving green platforms (Trippers) up until one passes a low bit of ceiling, shoot it and jump in. The tank is in CR-11."),
        t(CRATERIA, MISSILE, 5, listOf("X-Ray Scope", SUPERS),
            "Same open sky, far left: the X-Ray Scope shows a Super Missile block in the floor. Blast it, drop in and bomb left through the narrow tunnels. The tank is in CR-11."),
        t(CRATERIA, MISSILE, 6, listOf("Gravity Suit"),
            "The flooded cavern outside the Wrecked Ship, bottom-left corner under the water: shoot the base of the far wall and roll into the tunnel. The tank is in CR-12."),
        t(CRATERIA, MISSILE, 7, listOf(SHINE, PB),
            "The same trip as Energy Tank 2: past it, keep going left to a purple room that looks like a dead end. Shoot your way in.",
            "The floor is crumble blocks with an alcove on each side below. Drop down the middle and set off a Power Bomb as the alcoves come into view to open both. The tank is in CR-19."),
        t(CRATERIA, MISSILE, 8, listOf(SHINE, PB),
            "In the other alcove of the same crumbling shaft as Missile Tank 7, opposite it. The tank is in CR-20."),

        t(CRATERIA, SUPER, 1, listOf("Ice Beam", "Speed Booster", PB, SHINE),
            "Near the bottom of the shaft full of wall-crawling Space Pirates (old Tourian's escape shaft), bomb into the first ledge on the right and Power Bomb the door behind it.",
            "In the corridor beyond, freeze the four yellow Boyons flat against the floor, run from the left door, store a Shinespark and launch straight up the long shaft at the end. The tank is in CR-25."),

        t(CRATERIA, POWER_BOMB, 1, listOf("Speed Booster", PB, SHINE),
            "At the landing site, store a Shinespark running toward the ship, then launch diagonally up-right from the little mound left of it. You land by a yellow door at the top right; Power Bomb it and follow the corridor. The tank is in CR-13."),

        t(CRATERIA, ENERGY, 1, listOf("Bombs"),
            "Through the caves left of the landing site to the far end: bomb through the wall, go through the door and down the sloped cavern. The tank is at the bottom. The tank is in CR-07."),
        t(CRATERIA, ENERGY, 2, listOf(SHINE, "Bombs"),
            "Store a Shinespark running toward the ship, then launch diagonally up-left from the mound left of it to break into a hidden passage high in the wall.",
            "Through the door, bomb through the rocks along the boiling lava room (Power Bombs are quicker). The tank is in the room at the end. The tank is in CR-16."),

        // ---------------------------------------------------------------- Brinstar

        up("morph", BRINSTAR, "Morphing Ball", emptyList(),
            "Take the first elevator down into Brinstar and go left over the big rock, just like the start of the first Metroid. It's on a pedestal. It's in BR-01."),
        up("charge", BRINSTAR, "Charge Beam", listOf("Bombs"),
            "Bottom-left corner of the big pink room in Brinstar (the one with Spore Spawn's door at the top). Bomb the two small blocks side by side and drop down to the Chozo Statue. It's in BR-11."),
        up("spazer", BRINSTAR, "Spazer", listOf(SUPERS),
            "The water room with the Yapping Maws (claws in the water), next to the way into Maridia. Shoot the cracked block above its far left, climb up (wall-jump or Hi-Jump) and follow the tunnel to a green door. It's in BR-20."),
        up("varia", BRINSTAR, "Varia Suit", listOf(SUPERS),
            "Kraid's reward. Go through the door on the right side of his room. It's in BR-21."),
        up("xray", BRINSTAR, "X-Ray Scope", listOf(PB, "Grappling Beam"),
            "Halfway up the orange shaft full of Rippers in red Brinstar, a yellow door on the left. Power Bomb it and cross the dark spike room, freezing the glowing enemies (kill too many and it goes dark). Grapple over the last spikes.",
            "In the next room, bomb the small ledge to raise a pillar, roll left at the top and drop down to it. It's in BR-29."),

        t(BRINSTAR, MISSILE, 1, listOf("Morphing Ball"),
            "Right after the Morphing Ball: through the door on the right, shoot the sandstone bridge, drop down and go through the door at the bottom. The Chozo Statue holds it. Your first Missiles. The tank is in BR-02."),
        t(BRINSTAR, MISSILE, 2, listOf(PB),
            "In the long room above and right of Missile Tank 1, at the bottom of the big rock pile at the far end. If you can't get to it, Power Bomb the pile's base. The tank is in BR-48.",
            confirm = true),
        t(BRINSTAR, MISSILE, 3, listOf("Bombs"),
            "Green Brinstar's main shaft, first door down on the right. Drop through the crumbling bridge into the chambers below; it's in the second one. The tank is in BR-10."),
        t(BRINSTAR, MISSILE, 4, listOf("Grappling Beam"),
            "The big pink room, where the ceiling is lined with Grapple blocks: swing across to it on the left. (A wall-jump gets there too.) The tank is in BR-07."),
        t(BRINSTAR, MISSILE, 5, emptyList(),
            "The big pink room, bottom-left corner, in plain view. The tank is in BR-07."),
        t(BRINSTAR, MISSILE, 6, emptyList(),
            "The long green corridor of bug pipes leading to red Brinstar: one pipe high in a corner sends out no enemies. Wall-jump (or Hi-Jump) up to it and roll through. The tank is in BR-13."),
        t(BRINSTAR, MISSILE, 7, listOf("Speed Booster"),
            "Green Brinstar, first door down on the right (the room with Missile Tank 3): Speed Boost past the closing shutters and through the door at the end. Roll through the tunnel under the Chozo Statue. The tank is in BR-35."),
        t(BRINSTAR, MISSILE, 8, listOf("Speed Booster", PB),
            "Same room as Missile Tank 7: set off a Power Bomb to open the wall behind it. The tank is in BR-35."),
        t(BRINSTAR, MISSILE, 9, listOf("Speed Booster", PB, SHINE, "X-Ray Scope"),
            "Back at the rock pile of Missile Tank 2: once its base is blown open, store a Shinespark and launch straight up through the hole into the room above.",
            "In the ankle-deep water room, the X-Ray Scope shows an invisible bridge up and left. Cross it (falling boulders) to the room at the end. The tank is in BR-51."),
        t(BRINSTAR, MISSILE, 10, listOf("Speed Booster", PB, SHINE),
            "Same room as Missile Tank 9, hidden in the bottom-left block of the small platform under it. The tank is in BR-50."),
        t(BRINSTAR, MISSILE, 11, listOf(PB),
            "Same room as Power Bomb Tank 1: set off a Power Bomb to blow away the wall behind the Chozo Statue. The tank is in BR-30."),
        t(BRINSTAR, MISSILE, 12, listOf(PB, "Spring Ball"),
            "Kraid's Lair, the corridor with spiky branches and Kihunters: at its far right, Power Bomb to show a tunnel high up and Spring Ball into it. The tank is in BR-25."),

        t(BRINSTAR, SUPER, 1, emptyList(),
            "After Spore Spawn, climb the shaft above its body and go through the door at the top. At the end of the corridor, one pipe is crumble blocks; it drops you down a very long shaft to the Chozo Statue at the bottom. The tank is in BR-04."),
        t(BRINSTAR, SUPER, 2, listOf("Speed Booster"),
            "Green Brinstar, first door down on the right: past the shutters, shoot through the ceiling and go left along the hidden corridor above. The tank is in BR-36."),
        t(BRINSTAR, SUPER, 3, listOf(PB, "X-Ray Scope"),
            "Through the room with Energy Tank 2, behind the green door past it. The tank is in BR-40."),

        t(BRINSTAR, POWER_BOMB, 1, listOf(SUPERS, "Ice Beam"),
            "Top of the orange shaft full of Rippers, through the door and along the thorny corridor. Shoot through the floor at the base of the next shaft, drop down and blast the green door at the bottom.",
            "Freeze the Boyons to cross the spiky pit. The Chozo Statue at the far end holds your first Power Bombs. The tank is in BR-31."),
        t(BRINSTAR, POWER_BOMB, 2, listOf(PB),
            "Near the top of the orange shaft, the first door on the left under the elevator: beat the big Sidehoppers, then Power Bomb at the far left. The one Manflower without a Yapping Maw in it is fake; fall through it. The tank is in BR-33."),
        t(BRINSTAR, POWER_BOMB, 3, listOf(PB),
            "Up the long green corridor's top-right ledge, through the yellow door into blue Brinstar. Past the Sidehoppers, bomb under the wall that blocks the way to the Morphing Ball's room. The tank is in BR-47."),
        t(BRINSTAR, POWER_BOMB, 4, listOf(PB, "Speed Booster"),
            "Past Energy Tank 2's pit to the narrow shaft where three Etecoons show you the wall-jump. Climb it; at the top, a small opening in the right wall.",
            "Get in with the Spring Ball, or run from the left, spin-jump and morph in mid-air. The tank is in BR-41."),
        t(BRINSTAR, POWER_BOMB, 5, listOf("Grappling Beam", PB, SUPERS),
            "Past Missile Tank 4 in the pink room, Power Bomb the wall below it and go through the hidden door on the left. Beat the Sidehoppers, then Super Missile the hidden block in the floor at the left end. The tank is in BR-43."),

        t(BRINSTAR, ENERGY, 1, listOf(SUPERS),
            "Kraid's Lair, after Kraid: the metal door near the entrance is now flashing. Bomb the Beetoms, then shoot the ceiling four blocks in from the left wall. The tank is in BR-27."),
        t(BRINSTAR, ENERGY, 2, listOf(PB, "X-Ray Scope"),
            "Green Brinstar's main shaft: Power Bomb the floor at the very bottom and drop down the shaft below. Two doors on is a grey-brick room with the tank in plain view.",
            "There's a hidden pit right in front of it. Spot it with the X-Ray Scope and jump over. The tank is in BR-39."),
        t(BRINSTAR, ENERGY, 3, emptyList(),
            "The long room with Missile Tank 2: shoot the ceiling just left of the big rock pile and jump up into it (Hi-Jump or bomb-jump). The tank is in BR-48."),
        t(BRINSTAR, ENERGY, 4, listOf(PB, "Wave Beam"),
            "Big pink room, yellow door on the right wall. Beat the Sidehoppers, climb the tall wall, shoot the shutter at the top with the Wave Beam and drop through the door below. The tank is in BR-42."),
        t(BRINSTAR, ENERGY, 5, listOf(PB, "Speed Booster"),
            "In the Charge Beam's room, Power Bomb to open a tunnel and follow it down to a long half-flooded corridor. Kill the Zeros, then run left with the Speed Booster through the walls. The tank is in BR-46."),

        t(BRINSTAR, RESERVE, 1, listOf("Speed Booster"),
            "Green Brinstar, first door down on the right: Speed Boost past the closing shutters, through the red door at the end. The Chozo Statue holds it. The tank is in BR-34."),

        // ---------------------------------------------------------------- Norfair

        up("hijump", NORFAIR, "Hi-Jump Boots", listOf("Morphing Ball"),
            "From Norfair's elevator, drop to the bottom of the shaft and take the left door. Past Energy Tank 1, drop through the crumble block, roll left and down to a door, then shoot the top of the wall in the next room. It's in NO-04."),
        up("speed", NORFAIR, "Speed Booster", listOf("Hi-Jump Boots"),
            "Far right of Norfair's elevator, in the big bubble room. Get to its upper-right door from below, blow open the ceiling in the next room and run the long crumbling corridor to the door at the end.",
            "Picking it up floods the rooms with lava: run back left. It's in NO-14."),
        up("ice", NORFAIR, "Ice Beam", listOf("Speed Booster"),
            "First door on the upper left in the elevator shaft: Speed Boost past the shutters, cross the lava pits and climb the next shaft (fire-breathing heads).",
            "At the top, bomb the ledge in front of the door, morph and drop down, holding right to roll into the opening. The Ice Beam is through the door. It's in NO-16."),
        up("grapple", NORFAIR, "Grappling Beam", listOf("Speed Booster", PB),
            "Left of Crocomire's corridor: down the tall shaft and through the floor door at the bottom. Power Bomb the rubble, then Speed Boost left through the wall and jump at the cliff edge to fly across the huge cavern.",
            "It's through the door high in the top left. It's in NO-27."),
        up("wave", NORFAIR, "Wave Beam", listOf("Grappling Beam"),
            "Far right of the elevator: from the big green bubble room's lower-right door, take the top door on the right of the shaft below.",
            "Ride the sinking platforms right, shoot the blue shutter, then grapple over the gap to the door on the other side. It's in NO-30."),
        up("screw", NORFAIR, "Screw Attack", listOf("Space Jump"),
            "Ridley's Lair, behind Golden Torizo (see Bosses): the door on the right of its room. It's in NO-34."),

        t(NORFAIR, MISSILE, 1, listOf("Hi-Jump Boots"),
            "Right after the Hi-Jump Boots, climb to the top of the big cavern outside. The tank is in NO-03."),
        t(NORFAIR, MISSILE, 2, listOf("Hi-Jump Boots"),
            "Far right of the elevator, in the big green bubble room: at the far right, fall through the false floor and shoot the blocks below. Jump the spikes at the bottom. The tank is in NO-09."),
        t(NORFAIR, MISSILE, 3, listOf("Hi-Jump Boots"),
            "Just outside the Speed Booster's room, shoot the ceiling left of the door. The tank is in NO-13."),
        t(NORFAIR, MISSILE, 4, listOf("Ice Beam", SUPERS),
            "The big green bubble room's upper-left green door. Reach it by freezing a Waver as a step (or with the Grappling Beam). It's on top of a pillar. The tank is in NO-18."),
        t(NORFAIR, MISSILE, 5, listOf("Ice Beam", SUPERS),
            "Past Missile Tank 4: fire down by the left wall to raise a hidden pillar, bomb into the wall and cross the lava room beyond. Under Reserve Tank 1, shoot the leftmost green block above the lava. The tank is in NO-19."),
        t(NORFAIR, MISSILE, 6, listOf("Speed Booster", PB, "Ice Beam"),
            "Past the Ice Beam's shutters, Power Bomb the floor by the door and go left along the tunnel. Go through the next door slowly: the floor beyond is crumble blocks.",
            "Shoot the wall opposite to show the tank. Freeze the Sova on the platform below as a step, or come back with the Space Jump. The tank is in NO-31."),
        t(NORFAIR, MISSILE, 7, listOf("Speed Booster"),
            "Down the long shaft on the way to the Grappling Beam, the red door on the right near the bottom. Cross the rising lava and roll through the narrow passage to the end. The tank is in NO-24."),
        t(NORFAIR, MISSILE, 8, listOf("Grappling Beam"),
            "From Crocomire's corridor, up through the ceiling door and to the top of the shaft. In the fiery cavern above, swing across the Grapple blocks to the ledge on the left. The tank is in NO-29."),
        t(NORFAIR, MISSILE, 9, emptyList(),
            "On the way to the Wave Beam: across the sinking platforms, past the blue shutter, on the ledge. The tank is in NO-11."),
        t(NORFAIR, MISSILE, 10, listOf("Varia Suit"),
            "The second fiery cavern right of the elevator, in the third lava pit: shoot the bottom-right corner under the lava. The tank is in NO-06."),
        t(NORFAIR, MISSILE, 11, listOf("Speed Booster", SHINE),
            "The huge cavern outside the Grappling Beam's room, upper right. Clear the floor at the bottom, run left to the cliff, store a Shinespark, hop into the lava and launch straight up, holding right. The tank is in NO-26."),
        t(NORFAIR, MISSILE, 12, listOf("Space Jump"),
            "Ridley's Lair: straight ahead as you enter Golden Torizo's room, over a crumbling bridge. If you fall, it's a long way round. The tank is in NO-33."),
        t(NORFAIR, MISSILE, 13, listOf("Screw Attack"),
            "Ridley's Lair: up the shaft of yellow Space Pirates after Golden Torizo, shoot open the ceiling and go through the door. Don't Screw Attack in this room: shoot the platform below, bomb the left side of the next one and roll left. The tank is in NO-37."),
        t(NORFAIR, MISSILE, 14, listOf("Screw Attack"),
            "Ridley's Lair, top of the room full of Alcoons: past the dark dragon-head room and up through the wall left of the top. Head left and up, then right. The tank is in NO-54."),
        t(NORFAIR, MISSILE, 15, listOf(PB),
            "Through the left door across from Missile Tank 14, past the rising lava and shutters to the platform at the foot of the shaft. Bomb one block right of the dark spot in it and roll left. The tank is in NO-56."),

        t(NORFAIR, SUPER, 1, listOf("Screw Attack"),
            "Golden Torizo's room, top right: break through with the Screw Attack (or a Spring Ball and a Power Bomb). It's in the left wall of the alcove. The tank is in NO-33."),

        t(NORFAIR, POWER_BOMB, 1, listOf("Grappling Beam"),
            "The big room left of Crocomire's corridor: grapple onto the Ripper at the top and swing to the door in the top left. The tank is in NO-21."),
        t(NORFAIR, POWER_BOMB, 2, listOf(PB),
            "Ridley's Lair, the corridor of big statues at the top of the last shaft before Ridley: Power Bomb at the far left to destroy the middle statue and open a tunnel low in the left wall. The tank is in NO-43."),
        t(NORFAIR, POWER_BOMB, 3, listOf(PB, "Spring Ball"),
            "Behind Missile Tank 14, bomb the top corner of the wall, Spring Ball up into the tunnels and keep bouncing right to a door in the floor. Shoot the statue head and break the crumble blocks. The tank is in NO-55."),

        t(NORFAIR, ENERGY, 1, emptyList(),
            "Bottom of Norfair's elevator shaft, through the left door. In plain view. The tank is in NO-02."),
        t(NORFAIR, ENERGY, 2, listOf("Grappling Beam"),
            "Crocomire's corridor: grapple along the blocks above the lava to the far right end. The tank is in NO-28."),
        t(NORFAIR, ENERGY, 3, listOf(SUPERS, "Space Jump"),
            "Ridley's Lair, the dark shaft past the room that fills with lava: Super Missile the lowest fire-breathing head and walk through the wall where it was. Go down and left, then Space Jump right to the platform. The tank is in NO-52."),
        t(NORFAIR, ENERGY, 4, emptyList(),
            "After Ridley, in the room to the left (the broken Metroid jar): shoot the wall under the door. The tank is in NO-50."),

        t(NORFAIR, RESERVE, 1, listOf("Ice Beam", SUPERS),
            "Same lava room as Missile Tank 5, on a ledge at its far left. The Chozo Statue holds it. The tank is in NO-19."),

        // ---------------------------------------------------------------- Wrecked Ship

        up("gravity", WRECKED_SHIP, "Gravity Suit", listOf("Grappling Beam", "X-Ray Scope", SUPERS),
            "After Phantoon: leave by the top-left door into Crateria's open sky. At its far left, open the Super Missile block (Missile Tank 5's way in), then go right through the narrow tunnels back into the ship.",
            "Grapple over the spikes to the Chozo Statue with empty hands, stand on them and morph: it carries you down. The suit is through the door on the left. It's in WS-14."),

        t(WRECKED_SHIP, MISSILE, 1, listOf("Bombs"),
            "With the power still off: down the main shaft past the first door on the right. On the next platform, bomb the base of the left wall and cross the dead spikes to the end. The tank is in WS-03."),
        t(WRECKED_SHIP, MISSILE, 2, emptyList(),
            "After Phantoon, top of the main shaft through the ceiling door. Kill everything in the long corridor to unlock its doors, then go right and knock the robots into the spike pits. The tank is in WS-10."),
        t(WRECKED_SHIP, MISSILE, 3, listOf(PB),
            "Where the Chozo Statue dropped you: bomb the floor under the door and roll right. Power Bomb at the dead end, then knock the robots into the pits. The Chozo Statue holds it. The tank is in WS-11."),

        t(WRECKED_SHIP, SUPER, 1, emptyList(),
            "After Phantoon, the flashing door near the bottom of the main shaft. The tank is in WS-04."),
        t(WRECKED_SHIP, SUPER, 2, listOf(PB),
            "Opposite Super Missile Tank 1's door, bomb the base of the wall and go through the door behind it. Past the robots, Power Bomb at the dead end to show four tunnels; roll into the second one up. The tank is in WS-07."),

        t(WRECKED_SHIP, ENERGY, 1, listOf("Grappling Beam"),
            "The shaft on the ship's right side: shoot open the ceiling at the top and go through the door. Cross the flooded room on the sinking platforms and grapple to the tank at the far left. The tank is in WS-16."),

        t(WRECKED_SHIP, RESERVE, 1, listOf(PB, "Speed Booster", SHINE, "X-Ray Scope"),
            "From Missile Tank 3: Power Bomb the statue, run back left and Speed Boost right along the conveyor belts. Store a Shinespark and launch straight up the narrow shaft at the end.",
            "Go left and down. A false floor sits right in front of the tank (X-Ray shows it): jump over it, then drop through it to get out. The tank is in WS-12."),

        // ---------------------------------------------------------------- Maridia

        up("space", MARIDIA, "Space Jump", emptyList(),
            "Draygon's reward. Through the door on the left of its room. It's in MA-18."),
        up("spring", MARIDIA, "Spring Ball", listOf("Space Jump", PB, "Grappling Beam"),
            "Bottom of the big glass tube, right through the sandy tunnel to a room with one crumbling Grapple block. Hang on until it breaks, Space Jump up the shaft and drop down the next one.",
            "Power Bomb the purple wall so Shaktool (the two-armed robot) digs through the sand, then follow it. Drop down the hole in the next room and roll left to the bottom. It's in MA-26."),
        up("plasma", MARIDIA, "Plasma Beam", listOf("Space Jump"),
            "After Draygon, back to the first huge room past the glass tube. Space Jump up to the door on a platform in the top right (sealed until Draygon falls) and climb on to the room of red Space Pirates. It's at the bottom right. It's in MA-30."),

        t(MARIDIA, MISSILE, 1, listOf("Speed Booster", SUPERS, SHINE),
            "The hardest one in the game. From the big underwater shaft at Maridia's entrance, take the door at its base on the right. Super Missile the green shutter and clear the floor.",
            "Run left from the far right and store a Shinespark just before the door. Hurry back into the shaft, stand exactly at the left of the two plants that stick out, and launch straight up. The tank is in MA-01."),
        t(MARIDIA, MISSILE, 2, listOf("Speed Booster", PB, SHINE),
            "The giant room with the glass tube: from its long bottom platform, run right from the Save Room door. Store a Shinespark at the right wall and launch up through the first gap in the ceiling. The tank is in MA-04."),
        t(MARIDIA, MISSILE, 3, emptyList(),
            "Just before Draygon, in the room past the two big sand rooms. Its floor spikes are fake: go right and shoot the pipe in the middle of the far wall. The tank is in MA-15."),
        t(MARIDIA, MISSILE, 4, listOf("Gravity Suit"),
            "Past the huge Grapple room with the puffer fish, climb the long shaft (Scisers) and go through the ceiling door. In the sandy room, the dead end on the right is a fake wall. The tank is in MA-09."),
        t(MARIDIA, MISSILE, 5, listOf("Gravity Suit"),
            "From Missile Tank 4, go left through the fake wall at the dead end. Up the next shaft and left past the Zebbo pits to a ditch with a Choot; drop through the middle of it. Super Missile Tank 3 is here too. The tank is in MA-10."),
        t(MARIDIA, MISSILE, 6, listOf("Space Jump", PB, "Spring Ball"),
            "Bottom right of the glass tube room, Power Bomb the floor and drop into the tunnel. Sink into the first sand pit: it's at the top left of the cavern below. The tank is in MA-20."),
        t(MARIDIA, MISSILE, 7, listOf("Space Jump", PB, "Spring Ball"),
            "Same tunnel as Missile Tank 6, but sink into the leftmost sand pit. It's at the top left of that cavern, next to Reserve Tank 1. The tank is in MA-31."),
        t(MARIDIA, MISSILE, 8, listOf("Gravity Suit"),
            "The big entrance shaft, second door up on the right. Climb over the tall wall, drop to the bottom of the pit and walk through the right wall to a door. In the cavern of Tatori (turtles), shoot the right wall about halfway up. The tank is in MA-07."),

        t(MARIDIA, SUPER, 1, listOf("Gravity Suit"),
            "Visible in a closed-off cave in the big entrance shaft. From the top of the shaft go into the huge Grapple room, roll off the first cliff hugging the left wall, and roll left through the hidden tunnel. The tank is in MA-01."),
        t(MARIDIA, SUPER, 2, listOf("Speed Booster", PB, SHINE, "X-Ray Scope"),
            "Right of Missile Tank 2, at the end of the ledge. Jump over the crumble blocks in front of it (X-Ray shows them). The tank is in MA-04."),
        t(MARIDIA, SUPER, 3, listOf("Gravity Suit"),
            "Same hidden room as Missile Tank 5, in plain view. The tank is in MA-10."),

        t(MARIDIA, POWER_BOMB, 1, listOf("Space Jump", PB, "Spring Ball"),
            "The cavern under the first sand pit (Missile Tank 6's), on the other side, among the tunnels on the right. The tank is in MA-20."),

        t(MARIDIA, ENERGY, 1, listOf("X-Ray Scope"),
            "After Botwoon, the long sandy corridor through the door on the right. Hidden in the purple blocks overhead: get in at their far right and use the X-Ray Scope. Watch for the hole just before the tank. The tank is in MA-12."),
        t(MARIDIA, ENERGY, 2, listOf("Grappling Beam"),
            "The Tatori cavern (see Missile Tank 8), on top of the Grapple blocks near the ceiling. Ride the big Tatori up and swing onto them. The tank is in MA-07."),

        t(MARIDIA, RESERVE, 1, listOf("Space Jump", PB, "Spring Ball"),
            "Same cavern as Missile Tank 7, on the left. Get onto the ledge, morph and Spring Ball up and right into the tunnel without falling through the crumble block. The tank is in MA-31."),
    )

    val byId: Map<String, Item> = all.associateBy { it.id }

    init {
        require(byId.size == all.size) { "Duplicate item id" }
        require(all.size == 100) { "Super Metroid has 100 items, not ${all.size}" }
    }
}
