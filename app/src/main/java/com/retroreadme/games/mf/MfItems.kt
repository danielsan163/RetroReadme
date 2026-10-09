package com.retroreadme.games.mf

import com.retroreadme.games.mf.Kind.ENERGY
import com.retroreadme.games.mf.Kind.MISSILE
import com.retroreadme.games.mf.Kind.POWER_BOMB
import com.retroreadme.games.mf.Sector.AQA
import com.retroreadme.games.mf.Sector.ARC
import com.retroreadme.games.mf.Sector.MAIN_DECK
import com.retroreadme.games.mf.Sector.NOC
import com.retroreadme.games.mf.Sector.PYR
import com.retroreadme.games.mf.Sector.SRX
import com.retroreadme.games.mf.Sector.TRO

/**
 * All 100 items: 48 Missile Tanks, 20 Energy Tanks and 32 Power Bomb Tanks. Numbering within
 * each sector follows Metroid Recon's lists. Every location was checked against Metroid Recon
 * and Thonky's item list; details only one of them gives are marked confirm.
 */
object MfItems {

    private val code = mapOf(
        MAIN_DECK to "md", SRX to "s1", TRO to "s2", PYR to "s3", AQA to "s4", ARC to "s5", NOC to "s6",
    )
    private val letter = mapOf(MISSILE to "m", ENERGY to "e", POWER_BOMB to "p")

    private fun t(
        sector: Sector, kind: Kind, n: Int, needs: List<String>, vararg steps: String, confirm: Boolean = false,
    ) = Item("${code.getValue(sector)}_${letter.getValue(kind)}$n", sector, kind, "${kind.label} $n", needs, steps.toList(), confirm)

    private const val SHINE = SHINESPARK

    val all: List<Item> = listOf(

        // ---------------------------------------------------------------- Main Deck

        t(MAIN_DECK, MISSILE, 1, emptyList(),
            "In the vents past the first Data Room (MD-78): at the bottom of the tall dark red shaft, in plain view on the right. The tank is in MD-11.",
            "One guide says you can't come back for it later, so take it on the way through.",
            confirm = true),
        t(MAIN_DECK, MISSILE, 2, listOf("Missiles"),
            "Below and left of Missile Tank 1, through the opening into a dead-end room: shoot the top block of the steps on the left. The tank is in MD-12.",
            "One guide says you can't come back for it later, so take it on the way through.",
            confirm = true),
        t(MAIN_DECK, MISSILE, 3, listOf("Morph Ball"),
            "Halfway up the tall dark shaft on the way to Sector 1, roll through the small opening in the right wall into a dark room. The tank is in MD-16."),
        t(MAIN_DECK, MISSILE, 4, listOf("Speed Booster"),
            "Behind the wall the SA-X blasts open left of the Main Elevator. Run from the corridor on the right and Speed Boost through the blocks. The tank is in MD-21."),
        t(MAIN_DECK, MISSILE, 5, emptyList(),
            "Habitation Deck (MD-22), once the animals are freed: shoot through the floor on the right, then the floor on the left, and it's at the bottom in the middle."),
        t(MAIN_DECK, MISSILE, 6, listOf("Power Bombs"),
            "During the blackout, past the dark corridor with slime-dripping creatures on the ceiling: Power Bomb by the boxes where it exits right to open a passage above. The tank is in MD-23."),
        t(MAIN_DECK, MISSILE, 7, listOf("Power Bombs", "Bombs"),
            "From the Central Reactor Core, top-right door: in the room of Space Pirates past Energy Tank 3, Power Bomb where the vertical pipes meet the third ledge, then bomb down through the narrow tunnels. The tank is in MD-28."),

        t(MAIN_DECK, ENERGY, 1, emptyList(),
            "On the climb past the eye door, on the way to Arachnus (MD-14): on a ledge in plain view."),
        t(MAIN_DECK, ENERGY, 2, listOf("Missiles"),
            "Same room as Energy Tank 1: a little to its right, past the hanging piece of ceiling, shoot two Missiles straight up. Climb through and follow the tunnel right. The tank is in MD-15."),
        t(MAIN_DECK, ENERGY, 3, emptyList(),
            "From the Central Reactor Core, top-right door: on a platform in the next corridor, full of Space Pirates. The tank is in MD-27."),

        t(MAIN_DECK, POWER_BOMB, 1, listOf("Power Bombs"),
            "During the blackout: drop through the wreck of Sub-Zero Containment and Power Bomb the dark corridor below to open tunnels on the left. Power Bomb again at their end; the tank is up a narrow shaft. The tank is in MD-24."),
        t(MAIN_DECK, POWER_BOMB, 2, listOf("Speed Booster", "Space Jump", SHINE),
            "Restricted Zone, after the lab is jettisoned (from the bottom left of Sector 6): Speed Boost as far left as you can, crouch, and Shinespark straight up through the shaft with the view of space. The tank is halfway up. The tank is in MD-29."),
        t(MAIN_DECK, POWER_BOMB, 3, listOf("Power Bombs"),
            "Top of the shaft up to the Habitation Deck (MD-22) elevator: Power Bomb the creature blocking the top-right door, then crawl through the hidden tunnels in the right wall. The tank is in MD-30."),

        // ---------------------------------------------------------------- Sector 1 (SRX)

        t(SRX, MISSILE, 1, emptyList(),
            "Bottom-right room of the sector, where the fourth atmospheric stabilizer is: once it's clear, through the top-left door. The tank is in S1-14."),
        t(SRX, MISSILE, 2, listOf("Missiles", "Morph Ball"),
            "Charge Beam room (S1-40), upper door: shoot out the next room's ceiling and climb up. Missile the upper-left wall, beam the rest open and roll through to the tank, underwater past the Zeelas. The tank is in S1-15."),
        t(SRX, MISSILE, 3, listOf("Morph Ball", "Wall jump or Space Jump"),
            "Rightmost Save Room (S1-41), below the top-right stabilizer room: shoot the cracked block in its right wall, roll through and wall-jump up the shaft beyond. The tank is in S1-18."),
        t(SRX, MISSILE, 4, emptyList(),
            "Top-left door of the tall shaft with Zebesians climbing the walls: in the lava cavern, cross on the ceiling ladder to the platform beyond. The tank is in S1-09."),
        t(SRX, MISSILE, 5, listOf("Space Jump or Speed Booster"),
            "Same lava cavern, in the top-left corner. Space Jump up, or Shinespark in from the right. The tank is in S1-09."),
        t(SRX, MISSILE, 6, listOf("Gravity Suit"),
            "Same lava cavern: sink to the bottom and roll through the right wall. The tank is in S1-09."),

        t(SRX, ENERGY, 1, listOf("Morph Ball"),
            "Right of the first atmospheric stabilizer, in the room of hopping Hornoads: shoot open the pipe on the right and roll through to the end. The tank is in S1-08."),
        t(SRX, ENERGY, 2, listOf("Space Jump", "Diffusion Missiles"),
            "After the Restricted Lab is jettisoned, ride its elevator up into Sector 1. In the room with metal tubes and Rippers, Space Jump to the top-left pillar and fire a charged Diffusion Missile at the right wall. The tank is in S1-22."),
        t(SRX, ENERGY, 3, listOf("Speed Booster", "Space Jump", SHINE),
            "Above where the Charge Beam statue stood, behind Speed Booster blocks. Charge from the Save Room below, crouch in the old eye-door room, Space Jump to its top-left door and Shinespark through.",
            "You land running in the Charge Beam room (S1-40): crouch before the edge, walk to the left wall and Shinespark straight up. The tank is to the right. The tank is in S1-25."),

        t(SRX, POWER_BOMB, 1, listOf("Charge Beam", "Missiles"),
            "After the lab is jettisoned: in the shaft with a Save Room at its base, shoot through the floor into a hidden corridor.",
            "Kill the creature in the box with charged shots, let its X re-form twice and finish the yellow Zebesian. The sealed door opens. The tank is in S1-24."),
        t(SRX, POWER_BOMB, 2, listOf("Speed Booster", SHINE),
            "Base of the orange shaft outside the Charge Beam room (S1-40): shoot open the right wall into a water shaft.",
            "Charge from the Save Room to the left, crouch on the way, drop down the water shaft and Shinespark straight up against its right wall. The tank is in S1-13."),
        t(SRX, POWER_BOMB, 3, listOf("Screw Attack"),
            "Green door at the top right of the big green room near the sector entrance: Screw Attack up through the middle of the next room's ceiling. The tank is in S1-27."),

        // ---------------------------------------------------------------- Sector 2 (TRO)

        t(TRO, MISSILE, 1, listOf("Bombs"),
            "In the big green chamber next to the Data Room (S2-32), top-right corner: bomb the cracked wall. The tank is in S2-04."),
        t(TRO, MISSILE, 2, listOf("Bombs"),
            "From the room next to the Data Room (S2-32), bomb through its bottom-left corner and take the corridor's left door into a tall shaft. Bomb the lone block on the upper platform to raise a pillar and crawl through the left wall.",
            "Inside, bomb the floor three blocks left of the lone block for a second pillar up to the tank. The tank is in S2-06."),
        t(TRO, MISSILE, 3, listOf("Bombs"),
            "Below that shaft, at the very bottom of the big room of plants: clear the Zeros, bomb the right corner at the base of the platform, then bomb the floor just inside the tunnel and drop to the tank. The tank is in S2-08."),
        t(TRO, MISSILE, 4, listOf("Hi-Jump"),
            "Tall green shaft by the room where you first see the SA-X: through the door across from the wrecked one into a flooded room. Cross on the ceiling ladder to the tank on the far side. The tank is in S2-16."),
        t(TRO, MISSILE, 5, listOf("Hi-Jump", "Jumpball", "Bombs"),
            "Same flooded room, at the bottom: bomb the floor at the left tree to raise a pillar, crawl through the left opening and take the door.",
            "Bomb one block left of the small tree for another pillar, Jumpball-bomb the block in the wall and crawl to the tank. The Gravity Suit makes the water easier. The tank is in S2-17."),
        t(TRO, MISSILE, 6, listOf("Hi-Jump", "Bombs"),
            "Top of the same tall shaft, through the next room: in the short shaft with a Sidehopper, bomb through the small tunnel high in the right wall. The tank is in S2-18."),
        t(TRO, MISSILE, 7, listOf("Hi-Jump", "Bombs"),
            "Big green room near the sector entrance, blue door on the right: in the dead end with four Owtch, bomb the right wall and roll through. The tank is in S2-19."),
        t(TRO, MISSILE, 8, listOf("Hi-Jump"),
            "Same big green room, middle door on the left: jump over the tall creature to the tank behind it. The tank is in S2-20."),
        t(TRO, MISSILE, 9, listOf("Screw Attack", "Speed Booster", "Space Jump", "Power Bombs", SHINE),
            "In the huge cavern behind Zazabi's room (S2-27) (see Power Bomb Tank 5): Power Bomb at the bottom of the narrow shaft to show the tank behind two Speed Booster blocks.",
            "Charge along the bottom, crouch, Space Jump up level with the blocks and Shinespark into them. The tank is in S2-29."),

        t(TRO, ENERGY, 1, listOf("Bombs"),
            "In a small room you pass on the way into Zazabi's chamber (S2-27), in plain view. The tank is in S2-14."),
        t(TRO, ENERGY, 2, listOf("Space Jump"),
            "Top of the tall green shaft nearest the Plasma Beam room (S2-59), right door: in the dead end with four Owtch, jump from the right wall into a hidden tunnel. The tank is in the narrow alcove on the right of the hidden room. The tank is in S2-23."),
        t(TRO, ENERGY, 3, listOf("Space Jump", "Screw Attack"),
            "Level 1 Security Room (S2-57): Screw Attack through its top-left wall into a huge room whose floor is all crumbling blocks (a Power Bomb shows them). Space Jump to the tank in the top-left corner without landing. The tank is in S2-24."),

        t(TRO, POWER_BOMB, 1, listOf("Space Jump"),
            "Giant metal room near the middle of the sector (no plants): Space Jump up the left side and shoot through the two walls past the small red creatures. The tank is in S2-21."),
        t(TRO, POWER_BOMB, 2, listOf("Ice Missiles or Space Jump"),
            "Tall shaft left of Power Bomb Tank 1's room, highest door on the left: the tank is on a ledge past a crumbling platform.",
            "Freeze the two Rippers as steps (the first above the platform's right end, the second under the pillar) and Jumpball over. Space Jump and Screw Attack make it easier. The tank is in S2-25."),
        t(TRO, POWER_BOMB, 3, listOf("Ice Missiles or Space Jump"),
            "Directly below Power Bomb Tank 2: drop through the crumbling floor and shoot down. The tank is in S2-25."),
        t(TRO, POWER_BOMB, 4, listOf("Space Jump", "Screw Attack"),
            "Same crumbling-floor room as Energy Tank 3: drop through the crumbling blocks right of the Energy Tank; it's to the left at the bottom. The blocks like to catch you, so it can take a few tries. The tank is in S2-24."),
        t(TRO, POWER_BOMB, 5, listOf("Screw Attack", "Speed Booster", "Space Jump", SHINE),
            "Zazabi's room (S2-27): Screw Attack through its top-right wall to a hidden door. In the huge cavern beyond, drop down the shaft and dash to the right corner.",
            "Climb the room at speed, Shinesparking into each small slope so you never stop on the crumbling blocks. The last wall at the top hides the tank. The tank is in S2-29."),

        // ---------------------------------------------------------------- Sector 3 (PYR)

        t(PYR, MISSILE, 1, listOf("Speed Booster"),
            "Bottom-left door of the big red room near the sector entrance: Speed Boost through the wall, shoot through the floor of the corridor beyond, then shoot the ceiling near the right wall below. The tank is in S3-08."),
        t(PYR, MISSILE, 2, listOf("Bombs", "Level 2 doors"),
            "Top of the huge red shaft lined with dragon heads, door on the left: bomb open the two narrow tunnels in the left wall and roll through the upper one. The tank is in S3-10."),
        t(PYR, MISSILE, 3, listOf("Super Missiles"),
            "After the Super Missiles, back in the bright corridor left of the Data Room (S3-40)'s neighbor: drop through the big hole and head right to a dead end. Super Missile the Geron in the way. The tank is in S3-14."),
        t(PYR, MISSILE, 4, listOf("Speed Booster", SHINE),
            "After B.O.X. escapes (S3-13), climb the shaft it leaves by. Clear the room above, open its far-left door, then charge back and Shinespark up-left from beside the hole you came in by; you break into the tank's room. The tank is in S3-15."),
        t(PYR, MISSILE, 5, emptyList(),
            "Bottom of the giant red shaft, room to the right: clear the Owtch and shoot the hidden block at the top middle of the big block structure. The tank is in S3-20."),
        t(PYR, MISSILE, 6, listOf("Space Jump", "Varia Suit", "Bombs"),
            "Bottom of the giant red shaft, room to the right: bomb the right corner into a hidden shaft and take the upper door into a huge heated room (the lower one needs the Wave Beam). Space Jump up to the tank. The tank is in S3-24."),
        t(PYR, MISSILE, 7, listOf("Varia Suit"),
            "Big red room near the entrance, the door above its bottom-left door: across the lava corridor, in plain view. The tank is in S3-27."),

        t(PYR, ENERGY, 1, listOf("Speed Booster", "Bombs"),
            "Corridor above B.O.X.'s room (S3-13): open the far-left door, run back right, and Shinespark left through the door to break the wall in the next room. Bomb down the small tunnel in its corner to the tank (or reach it from below). The tank is in S3-11."),
        t(PYR, ENERGY, 2, listOf("Space Jump", "Varia Suit"),
            "In the same huge heated room as Missile Tank 6, up in the top right. The tank is in S3-24."),
        t(PYR, ENERGY, 3, listOf("Varia Suit", "Speed Booster", "Bombs", SHINE),
            "Past Missile Tank 7: bomb the wall behind it and clear the hidden corridor's floor. Speed Boost back to the left wall and Shinespark up through the ceiling blocks; the tank is in the big room above. The tank is in S3-28."),

        t(PYR, POWER_BOMB, 1, listOf("Speed Booster", "Power Bombs", SHINE),
            "Same Shinespark through the far-left door as Energy Tank 1: in the small room you land in, Power Bomb to open a tunnel in the left wall. The tank is in S3-16."),
        t(PYR, POWER_BOMB, 2, listOf("Speed Booster", "Power Bombs"),
            "Bottom-right door of the big red room: open the shutter and Speed Boost right, through the floor near the end. In the room by the giant red shaft, Power Bomb to show a tunnel in the top-left corner. The tank is in S3-18."),
        t(PYR, POWER_BOMB, 3, listOf("Gravity Suit", "Power Bombs"),
            "Bottom of the giant red shaft, then left through the lava corridor: in the next room Power Bomb to open the shallow lava lake, and follow the tunnels down in the lava, starting top right. The tank is in S3-26."),
        t(PYR, POWER_BOMB, 4, listOf("Gravity Suit", "Screw Attack", "Speed Booster", SHINE),
            "In the huge heated room (Missile Tank 6), drop into the lava and Screw Attack the blocks on the right wall. Open the door, run down the slope through it to charge, and in the next room Shinespark straight up from the right wall: a very long climb to the tank. The tank is in S3-22."),
        t(PYR, POWER_BOMB, 5, listOf("Gravity Suit", "Speed Booster", SHINE),
            "In an alcove partway up that same Shinespark shaft. After you hit the top, drop back down and climb through the hidden tunnel above the wall. The tank is in S3-22."),
        t(PYR, POWER_BOMB, 6, listOf("Screw Attack", "Power Bombs"),
            "Above the door of the tunnel between Sectors 3 and 5: Screw Attack through the ceiling near the hatch and shoot the block to the right. You can also reach it from the Sector 5 side. The tank is in S3-30."),

        // ---------------------------------------------------------------- Sector 4 (AQA)

        t(AQA, MISSILE, 1, listOf("Bombs"),
            "Second long room of electrified water: bomb the platform hiding the ceiling ladder, cross, and go up through the hole in the ceiling. Climb the left-wall ladder and roll through the small opening at its top. The tank is in S4-14."),
        t(AQA, MISSILE, 2, listOf("Bombs"),
            "First room of electrified water near the entrance: jump up through the ceiling into a hidden chamber. Wall-jump up the narrow shaft on its right, bomb through, then bomb the small corner on the far left. The tank is in S4-07."),
        t(AQA, MISSILE, 3, listOf("Bombs", "Missiles"),
            "Right after Missile Tank 2: from the wall ladder, Missile the left wall, roll down the narrow shaft, jump the hole and shoot open the top of the right wall. The tank is in S4-08."),
        t(AQA, MISSILE, 4, listOf("Speed Booster"),
            "Once the Pump Control Unit (S4-16) lowers the water, right below the control station in plain view."),
        t(AQA, MISSILE, 5, listOf("Speed Booster"),
            "After lowering the water, in the big room nearest Pump Control (S4-16): Speed Boost right from the bottom-left corner through the wall. The tank is up in a long water tank in the corridor beyond. The tank is in S4-18."),
        t(AQA, MISSILE, 6, listOf("Speed Booster"),
            "Coming in from Sector 5 after Nightmare, keep your Speed Boost up the second ramp to break the blocks near the ceiling. Past the hatch, down in the tall coral shaft, second door down on the right: shoot through the cracked blocks to the tank at the end. The tank is in S4-20."),
        t(AQA, MISSILE, 7, listOf("Power Bombs"),
            "Right door at the base of the tall coral shaft: Power Bomb the big green tube in the corridor and drop through to the tank. The tank is in S4-29."),
        t(AQA, MISSILE, 8, listOf("Gravity Suit", "Speed Booster", "Bombs"),
            "After the Level 4 doors (S4-45) open: from the underwater room with two blowfish, take the top-right door to a corridor blocked by a green pillar. Run back left into the blowfish room to Speed Boost through its floor, and follow the door below to the tank. The tank is in S4-30."),
        t(AQA, MISSILE, 9, listOf("Diffusion Missiles", "Super Missiles", "Bombs"),
            "After the Diffusion Missiles: freeze the blowfish in the next room with a charged Diffusion Missile and roll past. Through the opening in the left corner, Super Missile the Geron, then bomb the alcove under the broken power conduit on the far left. The tank is in S4-31."),

        t(AQA, ENERGY, 1, emptyList(),
            "Tall chamber left of the big electrified room near the entrance, upper (underwater) door: follow the winding room to the tank in a broken piece of roof. The tank is in S4-11."),
        t(AQA, ENERGY, 2, listOf("Space Jump", "Power Bombs"),
            "After the Level 4 doors (S4-45) open: halfway up the tall underwater shaft next door, roll through the hidden tunnel in the right-wall alcove and swim to the bottom of the shaft beyond.",
            "Power Bomb the creatures by the sealed door, let their X re-form into crabs and kill those. Inside, Power Bomb to open the way up to the tank. The tank is in S4-27."),

        t(AQA, POWER_BOMB, 1, listOf("Bombs"),
            "Tall coral shaft, entering from Sector 5 after Nightmare: partway down, roll through the hidden opening in the left wall into the next room. The tank is in S4-21."),
        t(AQA, POWER_BOMB, 2, listOf("Power Bombs", "Missiles"),
            "Bottom of the coral shaft, then left: Power Bomb the green tube in the next corridor and Missile the blocks above. In the area with small floating Evir, shoot open the top-right wall and crawl through. The tank is in S4-23."),
        t(AQA, POWER_BOMB, 3, listOf("Gravity Suit", "Speed Booster", SHINE),
            "Same start as Missile Tank 8. From the room below the hole you dropped through, run right, crouch just after the last mound, stand under the hole and Shinespark up. The tank is in S4-17."),
        t(AQA, POWER_BOMB, 4, listOf("Power Bombs"),
            "First big electrified room, left of the big empty blue room near the entrance: Power Bomb by the door to open a way down, crawl through, and Power Bomb again below. The tank is in a small tunnel in the left corner. The tank is in S4-31."),

        // ---------------------------------------------------------------- Sector 5 (ARC)

        t(ARC, MISSILE, 1, listOf("Ice Missiles", "Jumpball"),
            "Bottom of the tall icy shaft right of the Ice Missile Data Room (S5-45): Jumpball into the hidden tunnel low in the right wall and shoot the ceiling.",
            "Kill the Zeela without taking its X. Let it re-form as a Chute Leech, freeze it high when it leaps, and use it as a step. The tank is in S5-17."),
        t(ARC, MISSILE, 2, listOf("Bombs"),
            "Bottom of the tall shaft right of the entrance Recharge Room, right door: kill the Geruta on the ceiling and bomb the top-right corner. The tank is in S5-18."),
        t(ARC, MISSILE, 3, listOf("Space Jump"),
            "After Nightmare wrecks the big room where you saw its shadow, Space Jump to the top: through the top-left door. A Shinespark through that door also gets Power Bomb Tank 4 in the same move. The tank is in S5-24."),
        t(ARC, MISSILE, 4, listOf("Missiles"),
            "Bottom of the tall shaft near the entrance, yellow door on the left: clear the room and jump up through the hidden opening left of the boxed-in tank. The tank is in S5-37."),

        t(ARC, ENERGY, 1, listOf("Ice Missiles"),
            "Right of the Ice Missile Data Room (S5-45): shoot through the floor into a tall icy shaft, climb the ladder and freeze the Ripper to reach the ledge door. The tank inside is a fake that flies at you: shoot it, then take the real one from the wall. The tank is in S5-14."),
        t(ARC, ENERGY, 2, listOf("Power Bombs"),
            "Past the eye door guarding Nightmare's room (S5-55), straight ahead. Pit blocks on the direct route drop you into Nightmare's room, so Power Bomb to open the tunnel to it instead. The tank is in S5-32."),
        t(ARC, ENERGY, 3, listOf("Gravity Suit", "Missiles"),
            "Tall underwater shaft on the way to Nightmare: halfway down, Missile the left wall opposite the small opening in the right wall and crawl through.",
            "Mind the crumbling blocks, and don't lay bombs: one raises a pillar that blocks the tank. The tank is in S5-26."),

        t(ARC, POWER_BOMB, 1, listOf("Power Bombs"),
            "After the Power Bombs: past the corridor where the SA-X attacks, Power Bomb in the next room to open a small alcove. The tank is in S5-19."),
        t(ARC, POWER_BOMB, 2, listOf("Power Bombs", "Ice Missiles"),
            "Same room: below the two doors at the top, Power Bomb the ceiling. Climb up freezing the Rippers and take the door at the top. The tank is in S5-20."),
        t(ARC, POWER_BOMB, 3, listOf("Power Bombs", "Ice Missiles"),
            "First giant frozen room: Power Bomb the creature blocking the door in the left-wall alcove. In the long corridor beyond, Power Bomb halfway along to show the crumbling blocks, then freeze the Rippers to cross the narrow tunnels to the tank. The tank is in S5-23."),
        t(ARC, POWER_BOMB, 4, listOf("Space Jump", "Speed Booster", SHINE),
            "Behind the wall past Missile Tank 3: clear the Chute Leeches on the long top ledge of the wrecked room, open the top-left door, then charge from the right and Shinespark through it. The tank is in S5-25."),
        t(ARC, POWER_BOMB, 5, listOf("Screw Attack", "Power Bombs"),
            "Wrecked door at the top right of the big room that's no longer frozen: shoot up into the next room. Power Bomb the ceiling above the door to Sector 3, climb past two Zebesians and open the two shutters; the tank is inside the platform. The tank is in S5-36."),
        t(ARC, POWER_BOMB, 6, listOf("Power Bombs"),
            "On the way to Nightmare, in the room with two large creatures on the ceiling: kill them, shoot open the ceiling, and go through the open door up on the left. The tank is in S5-27."),
        t(ARC, POWER_BOMB, 7, listOf("Power Bombs", "Jumpball"),
            "The big room next door, top-right door: Power Bomb to open a tunnel in the left wall, then Power Bomb outside the Recharge Room door beyond. The tank is up a small tunnel near the left end of the first tunnel. The tank is in S5-29."),
        t(ARC, POWER_BOMB, 8, listOf("Gravity Suit", "Speed Booster", SHINE),
            "Long corridor of Zebesians and Skulteras near Nightmare's room (S5-55): clear it, open the right door, Speed Boost right through two walls, then crouch and Shinespark up into the ceiling between them. The tank is in S5-34."),

        // ---------------------------------------------------------------- Sector 6 (NOC)

        t(NOC, MISSILE, 1, listOf("Bombs"),
            "First big dark room, bottom-left corner: bomb partway up the wall and roll through. The tank is in S6-06."),
        t(NOC, MISSILE, 2, listOf("Bombs"),
            "After hiding from the SA-X: at the bottom of the shaft with a Save Room door, bomb into the bottom-left corner. The tank is in the top-left alcove of the hidden cave. The tank is in S6-14."),
        t(NOC, MISSILE, 3, listOf("Varia Suit"),
            "After the Varia Suit (S6-43): right door, up the next shaft and through the left door at its top. Jump straight onto the tank, as the floor before it crumbles. The tank is in S6-17."),
        t(NOC, MISSILE, 4, listOf("Varia Suit"),
            "When you fall after Missile Tank 3, hold left to catch a small opening halfway down. Shoot the ceiling of the hidden room just left of the middle. The tank is in S6-18."),
        t(NOC, MISSILE, 5, listOf("Varia Suit", "Bombs"),
            "Tall dark shaft past the first dark room, door about halfway down on the right: cross the blue-X corridor. The tank in the next room is a fake that flies off; shoot it and bomb the wall to the real one. The tank is in S6-23."),
        t(NOC, MISSILE, 6, listOf("Wave Beam"),
            "After B.O.X. II (S6-30), right door at the end of its corridor: shoot the switch to open the shutter. The tank is in S6-19."),

        t(NOC, ENERGY, 1, listOf("Bombs", "Jumpball"),
            "First big dark room, bottom right: bomb into the wall, then Jumpball-bomb high on the next wall to open the upper tunnel to the tank. A lower tunnel is a dead end. The tank is in S6-08."),
        t(NOC, ENERGY, 2, listOf("Speed Booster"),
            "Second big dark room, full of blue X, bottom-left door: Speed Boost through the blocks in the corridor, drop down the shaft and take the left door. The tank is in S6-11."),
        t(NOC, ENERGY, 3, listOf("Screw Attack", "Speed Booster", SHINE),
            "From B.O.X. II's corridor (S6-30): Screw Attack the floor blockage, run back right into the dark shaft and Shinespark through the far wall to a hidden door.",
            "The tank is at the end of a long corridor behind Speed Booster blocks; bridge the gaps with Shinesparks. The tank is in S6-22."),

        t(NOC, POWER_BOMB, 1, listOf("Screw Attack", "Speed Booster", SHINE),
            "Dark shaft near the first dark room, second door down on the left: Screw Attack the blockage on the corridor floor and open its left door. Charge right to left and Shinespark through; you fly through the next room and a wall to the tank. The tank is in S6-20."),
        t(NOC, POWER_BOMB, 2, listOf("Screw Attack", "Speed Booster", SHINE),
            "Same room as Power Bomb Tank 1, sealed in the bottom-left corner under Speed Booster blocks. From the bottom left, run right, crouch at the end, Shinespark into the slope and jump left onto the block platform while still charged. The tank is in S6-20."),
        t(NOC, POWER_BOMB, 3, listOf("Screw Attack", "Power Bombs"),
            "Above B.O.X. II's corridor (S6-30): Screw Attack through the right wall to a door. Inside, Power Bomb to show the tank in the top-right corner, then roll through and grab the alcove's edge before the crumbling blocks drop you. The tank is in S6-21."),
    )

    val byId: Map<String, Item> = all.associateBy { it.id }
}
