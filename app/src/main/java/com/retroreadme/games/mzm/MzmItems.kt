package com.retroreadme.games.mzm

import com.retroreadme.games.mzm.Area.BRINSTAR
import com.retroreadme.games.mzm.Area.CHOZODIA
import com.retroreadme.games.mzm.Area.CRATERIA
import com.retroreadme.games.mzm.Area.KRAID
import com.retroreadme.games.mzm.Area.NORFAIR
import com.retroreadme.games.mzm.Area.RIDLEY
import com.retroreadme.games.mzm.Area.TOURIAN
import com.retroreadme.games.mzm.Kind.ENERGY
import com.retroreadme.games.mzm.Kind.MAJOR
import com.retroreadme.games.mzm.Kind.MISSILE
import com.retroreadme.games.mzm.Kind.POWER_BOMB
import com.retroreadme.games.mzm.Kind.SUPER

/**
 * All 100 items: 14 upgrades, 12 Energy Tanks, 50 Missile Tanks, 15 Super Missile Tanks and
 * 9 Power Bomb Tanks. Numbering within each area follows Metroid Recon's lists. Locations were
 * checked against Metroid Recon and Omega Metroid; items only one of them covers are marked confirm.
 */
object MzmItems {

    private val code = mapOf(
        BRINSTAR to "br", KRAID to "kr", NORFAIR to "no", RIDLEY to "ri",
        TOURIAN to "to", CRATERIA to "cr", CHOZODIA to "cz",
    )
    private val letter = mapOf(ENERGY to "e", MISSILE to "m", SUPER to "s", POWER_BOMB to "p")

    private fun up(id: String, area: Area, name: String, needs: List<String>, vararg steps: String) =
        Item("u_$id", area, MAJOR, name, needs, steps.toList())

    private fun t(
        area: Area, kind: Kind, n: Int, needs: List<String>, vararg steps: String,
        late: Boolean = false, confirm: Boolean = false,
    ) = Item("${code.getValue(area)}_${letter.getValue(kind)}$n", area, kind, "${kind.label} $n", needs, steps.toList(), late, confirm)

    private const val SHINE = "Shinespark"

    val all: List<Item> = listOf(

        // ---------------------------------------------------------------- Brinstar

        up("morph", BRINSTAR, "Morph Ball", emptyList(),
            "Right at the start: head left, climb over the big rock and it's on a pedestal."),
        up("long", BRINSTAR, "Long Beam", emptyList(),
            "The first Chozo Statue marks it. Climb the tall shaft ahead and cross the corridor level with the marker."),
        up("charge", BRINSTAR, "Charge Beam", listOf("Missiles"),
            "The reward for beating Deorem, the giant worm that shakes the corridor leading to Norfair's elevator. It shows up once you have your first Missiles."),
        up("bomb", BRINSTAR, "Bombs", listOf("Missiles"),
            "The second Chozo Statue marks it. Open the red door in the second tall shaft with a Missile and follow the long route to a dead-end shaft; the Bombs are through the upper left door."),
        up("varia", BRINSTAR, "Varia Suit", listOf("Hi-Jump Boots"),
            "From the Norfair elevator, go left to the second tall shaft, climb to the very top and go right.",
            "In the small empty room, break the fake ceiling blocks and climb the shaft; stand on the left edge of the blocks that reappear and jump to the left wall.",
            "Go left through the acid corridor to the Varia Suit. (The Ice Beam can stand in for the Hi-Jump Boots.)"),

        t(BRINSTAR, ENERGY, 1, listOf("Bombs"),
            "In the ceiling of the long corridor with the first Chozo Statue, a little left of the rocks where the Skree hang. Shoot up to find it, then bomb-jump up (or freeze a Skree with the Ice Beam and stand on it)."),
        t(BRINSTAR, ENERGY, 2, emptyList(),
            "On top of a pillar above the acid in the corridor where you meet Deorem. It's in plain view."),
        t(BRINSTAR, ENERGY, 3, listOf("Varia Suit", "Bombs"),
            "Right after the Varia Suit, drop into the acid in the room before it. Bomb the middle of the floor, then shoot the left wall to open a tunnel to the tank."),

        t(BRINSTAR, MISSILE, 1, emptyList(),
            "Your first one: in plain view on a pillar in the long corridor leading to Norfair's elevator. The room shakes: Deorem attacks when you come back."),
        t(BRINSTAR, MISSILE, 2, listOf("Missiles"),
            "Through the red door halfway up the second tall shaft, in the big cavern with bug nests. Walk through the top-left wall and destroy the nest in front of it."),
        t(BRINSTAR, MISSILE, 3, emptyList(),
            "Past the bug-nest cavern, in the corridor blocked by a big rock wall. Shoot the wall down; the tank is in its base."),
        t(BRINSTAR, MISSILE, 4, listOf("Bombs"),
            "In the Bomb room, bomb the blocks under the Chozo Statue and roll left, then bomb-jump up past the Bomb blocks."),
        t(BRINSTAR, MISSILE, 5, listOf("Bombs", "Ice Beam or Power Grip"),
            "In the tallest shaft, find the small ledge on the left wall near a Ripper (freeze the Ripper to reach it). Bomb through the alcove and follow the hidden shaft down."),
        t(BRINSTAR, MISSILE, 6, listOf("Bombs"),
            "At the bottom of the shaft down to Kraid's Lair, bomb the floor by the left wall to expose a Morph Ball Launcher. Launch up and push right near the top to tuck into the nook."),
        t(BRINSTAR, MISSILE, 7, listOf("Ice Beam", "Power Grip"),
            "Climb to the very top of the second tall shaft, freeze the Rippers and use them to reach the opening high on the left. A rocky tunnel leads down to it."),
        t(BRINSTAR, MISSILE, 8, listOf("Speed Booster", "Hi-Jump Boots", SHINE),
            "In the Deorem corridor, run left from the rock on the right, open the door, crouch in the next shaft to store a Shinespark, and dash back to the small slope by the door.",
            "Open the door, morph, and Shinespark left: you crash through Speed Booster blocks into a hidden room with this tank."),
        t(BRINSTAR, MISSILE, 9, listOf("Varia Suit"),
            "After the Varia Suit, go right past the Save Room; it's on a pillar in the next corridor."),
        t(BRINSTAR, MISSILE, 10, listOf("Bombs", "Power Grip"),
            "Left of the bug-nest cavern, bomb the small room's floor and drop down. Bomb the block over the alcove on the right and grab into it before the block reforms."),

        t(BRINSTAR, SUPER, 1, listOf("Speed Booster", "Hi-Jump Boots", SHINE),
            "Same route as Missile Tank 8, but keep the Shinespark going: in that room, re-store a charge off the slope, go around to the entry, and Shinespark back out left.",
            "You tear through the next shaft into Speed Booster blocks guarding this tank. One of the hardest items; bombing the floor near the first blocks opens a tunnel that makes it easier."),

        // ---------------------------------------------------------------- Kraid's Lair

        up("spacejump", KRAID, "Unknown Item 2 (Space Jump)", listOf("Zip Lines powered"),
            "From the main elevator, take the second door down on the left, then left to a shaft going up.",
            "Its base hides a second shaft going down; descend, go right through two rooms, and bomb the crumbling block at the foot of the left door.",
            "Go down, head right at the bottom, and break the wall above the far door using the floating platform. The statue is at the end of the tunnel. It only works after the Ruins Test."),
        up("speed", KRAID, "Speed Booster", listOf("Defeat Kraid"),
            "In the room right behind Kraid's. The Chozo Statue in Norfair after the Ice Beam points to it."),

        t(KRAID, ENERGY, 1, listOf("Bombs"),
            "Power the Zip Lines by rolling into the conduit next to the Save Room by the Acid Worm's room, then ride the ceiling Zip Line to the tank on a tall pillar."),
        t(KRAID, ENERGY, 2, listOf("Speed Booster"),
            "Leaving Kraid's Lair, take the second red door on the right below the main elevator. Run right from the door along the platform over the acid.",
            "At the edge, jump across while still running (or crouch and Shinespark right) to smash the Speed Booster blocks over the tank."),

        t(KRAID, MISSILE, 1, listOf("Bombs"),
            "In the Save Room on the first left door down the main shaft: bomb against the left wall and hold left as you pop up to slide into a hidden tunnel."),
        t(KRAID, MISSILE, 2, emptyList(),
            "Through the red door across from the Save Room by the elevator; it's on the highest pillar in the corridor."),
        t(KRAID, MISSILE, 3, listOf("Bombs"),
            "In the tall room with the glass tube and Rippers, bomb the floor next to the tube for a Morph Ball Launcher. Launch up and steer into the tunnel near the top."),
        t(KRAID, MISSILE, 4, listOf("Bombs", "Defeat Acid Worm"),
            "Once the acid drains after the Acid Worm, bomb down in the middle of its room, go left under the floor and shoot the upper-left wall."),
        t(KRAID, MISSILE, 5, listOf("Zip Lines powered"),
            "On the way to Unknown Item 2: beat the two giant Sidehoppers to unlock the door, then roll into the glass tube for this tank."),
        t(KRAID, MISSILE, 6, listOf("Bombs", "Zip Lines powered"),
            "In the corridor with a tank sealed in the roof: bomb the floor under each Speed Booster block on the Zip Line for hidden Morph Ball Launchers and blast the blocks away.",
            "Then launch into the Zip Line's clamp; it carries you to the tank."),
        t(KRAID, MISSILE, 7, listOf("Speed Booster"),
            "After Kraid, run right from under the Chozo Statue in his room to Speed Boost through several rooms into a tall shaft. Climb it, take the lower left door, and bomb the wall at the end of the corridor."),
        t(KRAID, MISSILE, 8, listOf("Bombs", "Zip Lines powered"),
            "Third door down on the left of the entrance shaft, in the Sidehopper room. Clear the enemies, bomb the Zip Line switch on the floating platform, then quickly bomb-jump into the moving clamp and drop onto the tank."),
        t(KRAID, MISSILE, 9, listOf("Gravity Suit", "Speed Booster", SHINE),
            "Through the door below the Save Room in the main shaft: roll through the tunnels to the second acid pit and break the blocks into a small shaft.",
            "Run right to charge, come back to the left of the slope, morph and Shinespark right into the alcove.",
            late = true),

        // ---------------------------------------------------------------- Norfair

        up("ice", NORFAIR, "Ice Beam", listOf("Power Grip"),
            "From Norfair's elevator up to Brinstar, go right past the Save Room and across the big room to a huge green shaft. Go up, through the first door on the left, and across the corridor."),
        up("hijump", NORFAIR, "Hi-Jump Boots", listOf("Speed Booster"),
            "Go right from the elevator to the end of the lava room, then run from the far left to Speed Boost through the door and down through the floor of the next shaft.",
            "Build speed in the left corridor to break the next row of blocks, continue down, and take the first door on the left."),
        up("wave", NORFAIR, "Wave Beam", listOf("Varia Suit"),
            "From the Hi-Jump Boots room, take the narrow tunnel left and keep going left through the heat until you can't go further.",
            "Drop through the base of the small shaft (the Map Room is behind the red door), go down, then right to a small room before a Save Room. Shoot through its floor, go left, and cross the corridor."),
        up("screw", NORFAIR, "Screw Attack", listOf("Speed Booster", "Bombs"),
            "Easiest after Ridley: when you ride back up to Norfair, the wall on the right of the elevator room collapses. Pass the ruined Chozo Statue and head down and left.",
            "Speed Boost left through the next rooms, smashing blocks over a Morph Ball Launcher at the far end, and launch up. Go left at the top and through the red door."),

        t(NORFAIR, ENERGY, 1, listOf("Defeat the larvae"),
            "In the long tunnel with two huge caterpillar larvae. Once they're gone, shoot diagonally up at the ceiling near the second one's skin to reveal the tank."),

        t(NORFAIR, MISSILE, 1, listOf("Power Grip or Bombs"),
            "In the part-lava corridor next to Norfair's elevator, sitting on a block in the ceiling near the end. Grab the ledge (or bomb-jump)."),
        t(NORFAIR, MISSILE, 2, listOf("Power Grip"),
            "Keep going to the shaft with the elevator to Crateria, go down and right; it's in plain view at the end of the long corridor."),
        t(NORFAIR, MISSILE, 3, listOf("Ice Beam", "Bombs"),
            "In the big lava room right of Norfair's entrance Save Room, far right: free the creature trapped under blocks, then freeze it at the top of its jump and climb up."),
        t(NORFAIR, MISSILE, 4, listOf("Ice Beam"),
            "Past the Chozo Statue that shows the Speed Booster, in the next corridor: shoot the right-hand wall near the narrow space where a Ripper flies, then freeze the Ripper to reach it."),
        t(NORFAIR, MISSILE, 5, listOf("Varia Suit"),
            "At the top of the big green shaft on the east side, at the end of a super-heated corridor."),
        t(NORFAIR, MISSILE, 6, listOf("Hi-Jump Boots"),
            "Right after the Hi-Jump Boots, back in the big shaft: jump over the tall wall in front of you."),
        t(NORFAIR, MISSILE, 7, listOf("Varia Suit", "Super Missiles"),
            "Through the green (Super Missile) door off the big green shaft: in the lava corridor full of pillars, shoot the top of the last pillar."),
        t(NORFAIR, MISSILE, 8, listOf("Ice Beam or Space Jump"),
            "From the Map Room, go right to the dead end, drop through the floor and go left. In the corridor with Super Missile Tank 2, shoot open the wall near the ceiling on the far left and roll through. Much easier with the Space Jump."),
        t(NORFAIR, MISSILE, 9, listOf("Varia Suit"),
            "In the super-heated corridor below the Wave Beam's room: shoot the spire hanging from the ceiling halfway along."),
        t(NORFAIR, MISSILE, 10, listOf("Varia Suit"),
            "At the far end of the same corridor as Missile Tank 9, in plain view."),
        t(NORFAIR, MISSILE, 11, listOf("Speed Booster"),
            "Past the larvae, run from the tunnel into the green shaft to smash the Speed Booster floor; the tank is in the left wall."),
        t(NORFAIR, MISSILE, 12, listOf("Screw Attack"),
            "In the corridor just before the Screw Attack's room, in a ceiling alcove behind Screw Attack blocks. Grab it on the way back."),
        t(NORFAIR, MISSILE, 13, listOf("Gravity Suit", "Power Bombs"),
            "At the bottom of the shaft by the Map Room, break into the lava and take the door on the left. Partway up the lava shaft, a Power Bomb reveals the tank on the right wall; mind the crumbling blocks under it.",
            late = true),

        t(NORFAIR, SUPER, 1, listOf("Varia Suit", "Super Missiles", "Bombs"),
            "Past Missile Tank 7's pillar corridor, in the next chamber's top-left corner behind Bomb blocks. The floor below crumbles: bomb the far-right platform first to buy time, then run and jump over."),
        t(NORFAIR, SUPER, 2, listOf("Ice Beam or Space Jump"),
            "From the Map Room, go right to the dead end, drop through the floor and go left. It's on a floating pillar; freeze a bug from the floor pipe as a step, or use the Space Jump."),

        t(NORFAIR, POWER_BOMB, 1, listOf("Gravity Suit", "Power Bombs", "Space Jump"),
            "In the same lava shaft as Missile Tank 13: shoot through the Missile blocks the Power Bomb reveals on the left wall, crawl through, and Space Jump to the top.",
            late = true),

        // ---------------------------------------------------------------- Ridley's Lair

        up("gravity", RIDLEY, "Unknown Item 3 (Gravity Suit)", listOf("Super Missiles"),
            "In the room just past Ridley's lair, on the left. You can get it before fighting Ridley; it only works after the Ruins Test."),

        t(RIDLEY, ENERGY, 1, listOf("Super Missiles"),
            "Early in Ridley's Lair, in plain view in a narrow hall. The four floor tiles in front of it are fake: jump from the fourth tile from the door (the Power Grip helps)."),
        t(RIDLEY, ENERGY, 2, emptyList(),
            "In the same room as Unknown Item 3, to the left past the Chozo Statue."),
        t(RIDLEY, ENERGY, 3, listOf("Bombs"),
            "In the long green corridor with bug pipes (right from the lower-middle Save Room), bomb the left side of the alcove near the entrance to open a tunnel down to it."),

        t(RIDLEY, MISSILE, 1, listOf("Super Missiles", "Bombs"), "In the tall shaft with a Save Room halfway down, roll through the narrow tunnels in the floor near the two doors; it's on a pillar below.",
            confirm = true),
        t(RIDLEY, MISSILE, 2, listOf("Speed Booster", SHINE, "Power Grip"), "Break into the roof of the long corridor right of the Map Room, run left, store a Shinespark, and release it off the slope by the Map Room door to smash through the floor of the shaft beyond.",
            "Through the door at the bottom, Shinespark up the left side and work right through the tunnels. Shoot the blocks below at an angle, then roll quickly over crumbling blocks to the lower tank.",
            confirm = true),
        t(RIDLEY, MISSILE, 3, listOf("Speed Booster", SHINE, "Power Grip"), "The upper tank in the same room as Missile Tank 2: stand on the middle block, clear the two blocks above, hop off before it crumbles, and repeat once it reappears to shoot your way up.",
            confirm = true),
        t(RIDLEY, MISSILE, 4, emptyList(), "On a pillar in plain view in the giant corridor right of the Map Room.",
            confirm = true),
        t(RIDLEY, MISSILE, 5, listOf("Bombs"), "From the tall shaft past the long corridor's Save Room, take the second door up on the left. At the last hole near the left door, drop hugging the right side, crawl through, and bomb into the fake lava pit; the tank is in its right corner.",
            confirm = true),
        t(RIDLEY, MISSILE, 6, listOf("Bombs"), "One room left of Missile Tank 5, guarded by bouncing enemies. Bomb up through the floor from underneath.",
            confirm = true),
        t(RIDLEY, MISSILE, 7, listOf("Ice Beam"), "Next room left: on a pillar over a big hole. Freeze one of the creatures circling the pillar at its lower right and use it as a step.",
            confirm = true),
        t(RIDLEY, MISSILE, 8, listOf("Ice Beam", "Power Grip"), "From Missile Tank 7's pillar, drop left while holding left to grab the ledge by the door. In the room with Super Missile Tank 3, enter the hidden tunnel above the right door; the tank is in the hidden room above.",
            confirm = true),
        t(RIDLEY, MISSILE, 9, listOf("Bombs"), "Next room left: roll through the tunnel and break the blocks on the left of the structure. Watch the crumbling blocks under the left edge.",
            confirm = true),
        t(RIDLEY, MISSILE, 10, listOf("Bombs"), "Same room: clear the left blocks, hang off the edge until a single block appears, stand on it, shoot down-right to open the corner, then roll down before the block reforms.",
            confirm = true),
        t(RIDLEY, MISSILE, 11, listOf("Ice Beam"), "From the Map Room, cross the long corridor, climb the tall shaft and go left. In the lava cavern, lure a bug from the pipe up under the ceiling opening, freeze it, and jump up to the hidden platform.",
            confirm = true),
        t(RIDLEY, MISSILE, 12, listOf("Speed Booster", SHINE, "Space Jump"), "Store a Shinespark on the long corridor's roof, release it through the Save Room into the shaft, and Shinespark into the wall opposite the first door up to reveal a hidden room.",
            "In the pipe room, charge again, morph into the gap by the right wall and Shinespark straight up. Follow the crumbling pipes, staying airborne with the Space Jump, then slip down past the narrow pillar on the left.",
            confirm = true, late = true),
        t(RIDLEY, MISSILE, 13, listOf("Speed Booster", SHINE), "Right after Missile Tank 12: charge from the right of that room, crouch under the small ceiling opening next door and Shinespark up.",
            "In the room to the right, run in shooting the blocks (take out the hidden Missile block), then jump just before the bump in the floor so your arc Shinesparks through the Speed Booster blocks. Very fiddly.",
            confirm = true, late = true),

        t(RIDLEY, SUPER, 1, listOf("Defeat Imago"),
            "Guarded by the giant wasp Imago; beating it gives you this tank. You'll likely get it first, since you need Super Missiles to go deeper."),
        t(RIDLEY, SUPER, 2, listOf("Bombs", "Super Missiles"),
            "Above the room with Missile Tank 6: break up through the roof, blow the Super Missile block, roll through, and bomb the rubble in the alcove."),
        t(RIDLEY, SUPER, 3, listOf("Ice Beam", "Power Grip"),
            "Through the door left of Missile Tank 7's pillar room (drop left from the pillar and grab the ledge), in a small room past the corridor."),

        // ---------------------------------------------------------------- Tourian

        t(TOURIAN, MISSILE, 1, listOf("Speed Booster", SHINE), "After the Ruins Test, go to the Save Room nearest Mother Brain's corridor and run left in the next corridor to charge.",
            "At Mother Brain's corridor, spin-jump until level with the top of the door and Shinespark left in mid-air, past her remains into the escape shaft and through the far wall.",
            confirm = true, late = true),
        t(TOURIAN, POWER_BOMB, 1, listOf("Super Missiles"),
            "Shoot a Super Missile into the floor right under Mother Brain's remains and drop into the room below."),

        // ---------------------------------------------------------------- Crateria

        up("plasma", CRATERIA, "Unknown Item 1 (Plasma Beam)", emptyList(),
            "From the Norfair elevator, go right into the big water cavern, climb to its top-left corner and up the shaft.",
            "Follow the narrow tunnels right to a blue door; the item is at the far end of the watery room. It lets you break its matching blocks now, but only works as a beam after the Ruins Test."),
        up("grip", CRATERIA, "Power Grip", listOf("Bombs"),
            "After Unknown Item 1, go up through the tunnels to the open area and through the door on the upper left. Drop down the huge shaft, shoot the wall at the bottom left, and roll through to the big Chozo Statue."),

        t(CRATERIA, MISSILE, 1, listOf("Bombs"),
            "Underwater in the bottom-left of the big water cavern. Shoot and bomb the walls there to open a nook; the tank is behind the left wall."),
        t(CRATERIA, MISSILE, 2, listOf("Unknown Item 1"),
            "In Unknown Item 1's room: once you have it, destroy the flashing block nearest the door and drop down."),
        t(CRATERIA, MISSILE, 3, listOf("Power Bombs", "Speed Booster", SHINE), "Power Bomb the yellow door on the right of Crateria's big north-east area and charge in the long corridor to Chozodia, running back toward Crateria.",
            "Crouch as you come through the door, drop to the ledge below it, morph and Shinespark left through the Speed Booster blocks.",
            late = true),

        t(CRATERIA, SUPER, 1, listOf("Power Bombs", "Speed Booster", "Space Jump", SHINE), "Above the yellow door to Chozodia in the top right of Crateria. Charge in the corridor beyond the door, crouch as you come back out, Space Jump to the ledge above, and Shinespark into the slope to run through the wall.",
            late = true),

        t(CRATERIA, POWER_BOMB, 1, listOf("Speed Booster", SHINE, "Hi-Jump Boots"), "Take the Morph Ball Launcher under the starting platform in Brinstar up to Crateria, climb, and Speed Boost through the wall into the canyon where the ship landed.",
            "Store a Shinespark, bounce it off the slope by the small mound to keep it, stand on the mound's peak, morph and Shinespark up-left into the Speed Booster blocks high on the left wall. Power Bomb the last block.",
            "Easier: run left from the far end of the corridor by the Norfair elevator on the right, through the next room, to arrive charged.",
            late = true),

        // ---------------------------------------------------------------- Chozodia

        t(CHOZODIA, ENERGY, 1, listOf("Speed Booster", SHINE), "In the room next to Mecha Ridley's: run from the corridor on the left and spin-jump onto the raised platform by the door to smash Speed Booster blocks into the corridor below.",
            "The corridor is full of alarm lasers; touching one shuts the tank away. Come back charged, drop down, and mid-air Shinespark right at platform height to fly straight into it.",
            late = true),
        t(CHOZODIA, ENERGY, 2, listOf("Power Bombs", "Speed Booster", SHINE), "Power Bomb the glass tube between the ship and the ruins, go to the bottom left, Power Bomb the wall and drop down. Go left to the room with a big slope and clear it.",
            "Run down the slope back toward the shaft, crouch, walk to the middle of the shaft and Shinespark straight up.",
            late = true),
        t(CHOZODIA, ENERGY, 3, listOf("Speed Booster", "Screw Attack", "Space Jump", SHINE), "The hardest item in the game. In the big room by the Chozo Warrior's room, first blow open both Missile blocks in the shaft through the right door.",
            "Charge at the bottom of the underwater part, then keep the charge alive by Shinesparking into slopes as you climb. Shinespark right through the door, drop into the tunnel past the Screw Attack blocks, and re-store on the small slope in the left-wall alcove.",
            "Drop to the small platform on the right wall and Shinespark right across several screens to the tank.",
            late = true),

        t(CHOZODIA, MISSILE, 1, listOf("Fully powered suit"), "In the big shaft through the top right door of the water room next to the Chozo Warrior's room: in the small alcove on the left wall, blow the hidden Missile block and crawl in.",
            confirm = true, late = true),

        t(CHOZODIA, SUPER, 1, listOf("Speed Booster"), "After the Ruins Test, go down to the underwater part of the next room (blow the Missile blocks by the Save Room door) and run right along the bottom to smash through the wall.",
            late = true),
        t(CHOZODIA, SUPER, 2, listOf("Gravity Suit"), "Out the top right door of Super Missile Tank 1's room, down the big shaft and left at the bottom. It's on the right side of the lava area.",
            late = true),
        t(CHOZODIA, SUPER, 3, listOf("Bombs", "Missiles"), "From the lava room, go left, down two shafts and through the right door. Bomb through the big brown blocks and drop down; it's behind a Missile block in the left wall.",
            late = true),
        t(CHOZODIA, SUPER, 4, listOf("Screw Attack", "Bombs"), "From the ship's central Save Room, go right past the shutter room into the shaft and Screw Attack and bomb your way to the very top. Nudge the slow maintenance robot along with a Missile.",
            late = true),
        t(CHOZODIA, SUPER, 5, listOf("Bombs", "Space Jump"), "From Super Missile Tank 4's shaft, take the highest left door, climb, and go left to a narrow hall with a wall. It's inside the wall; bombing near it drops you into a pit of Space Pirates, so clear them and Space Jump back up.",
            late = true),
        t(CHOZODIA, SUPER, 6, listOf("Fully powered suit"), "Go right from the Map Room at the bottom of the ship, clear the two Space Pirates, and look in the ceiling above the damaged block at the far right.",
            late = true),
        t(CHOZODIA, SUPER, 7, listOf("Power Bombs"), "Power Bomb the glass tube, then the wall at its bottom left. Go down and keep left to a small room with a low ceiling. A tunnel in the middle of the ceiling leads into a maze; clear the Zoomers and climb to the top.",
            late = true),
        t(CHOZODIA, SUPER, 8, listOf("Power Bombs", "Bombs"), "Through the left door from Super Missile Tank 7, in the huge room with packed platforms along the roof. A Power Bomb shows the Bomb blocks; work up through the maze to the top middle.",
            late = true),

        t(CHOZODIA, POWER_BOMB, 1, listOf("Fully powered suit"), "Your first Power Bombs, the ones a Space Pirate walked off with. From the ship's bridge (top of the ship, windows on the left), drop down the narrow opening in the left corner and bomb down to the open area. Space Jump over the two alarm lines.",
            late = true),
        t(CHOZODIA, POWER_BOMB, 2, listOf("Power Bombs"), "In the room right of Power Bomb Tank 1. Lay a Power Bomb to open the ceiling; two Space Pirates guard it.",
            late = true),
        t(CHOZODIA, POWER_BOMB, 3, listOf("Power Bombs"), "Near the crash site: where the ship's purple hull first shows, climb to its far right edge and lay a Power Bomb to reveal a narrow alcove.",
            late = true),
        t(CHOZODIA, POWER_BOMB, 4, listOf("Power Bombs", "Speed Booster"), "The tall blue shaft with no visible entrance on the map. Power Bomb the glass tube, go to the bottom right of the cliff, Power Bomb the wall, and follow the corridor to the shaft. Speed Boost up past the Space Pirates.",
            late = true),
        t(CHOZODIA, POWER_BOMB, 5, listOf("Power Bombs", "Wave Beam"), "Enter Chozodia through the door in Crateria's top-right corner. In the first room after the long corridor, shoot the left wall with the Wave Beam to find it, then Missile the block in the way.",
            late = true),
        t(CHOZODIA, POWER_BOMB, 6, listOf("Power Bombs", "Missiles"), "The dark hallway near the Chozo Warrior's room that sealed when you got your suit back. In the tall shaft, Power Bomb the small opening on the left wall, crawl to the end of the highest tunnel, and Missile the ceiling.",
            late = true),
    )

    val byId: Map<String, Item> = all.associateBy { it.id }
    val majors: List<Item> = all.filter { it.kind == MAJOR }
}
