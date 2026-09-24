package com.retroreadme.games.wl4

import com.retroreadme.games.wl4.Passage.EMERALD
import com.retroreadme.games.wl4.Passage.ENTRY
import com.retroreadme.games.wl4.Passage.GOLDEN
import com.retroreadme.games.wl4.Passage.RUBY
import com.retroreadme.games.wl4.Passage.SAPPHIRE
import com.retroreadme.games.wl4.Passage.TOPAZ

/**
 * All 18 levels. Normal locations were checked against two walkthroughs that agree;
 * Hard locations come from one detailed walkthrough, so the Hard guide flags them for checking.
 */
object Wl4Levels {

    val all: List<Wl4Level> = listOf(

        // ---------------------------------------------------------------- Entry Passage

        Wl4Level(
            "hoh", "Hall of Hieroglyphs", ENTRY,
            normal = ModeData(
                jewels = listOf(
                    "Up the platforms and down the pipe in the top corridor, out through the upside-down pipe, then right.",
                    "Break the next block with a charge and the one after with a jump attack. It's in the room beyond.",
                    "Throw the stone upward to smash the block above the ladder, then crawl through. It's in the next corridor.",
                    "Just past the Keyzer, next to a diamond.",
                ),
                cd = null,
                keyzer = "At the end of the rolling tutorial, before the last jewel piece.",
                escapeTime = "1:00",
                escapeTip = "Set the stone by the steps before you press the switch, then use it to break the blocks and crawl back.",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Up the platforms and down the pipe in the top corridor, out through the upside-down pipe, then right.",
                    "Break the next block with a charge and the one after with a jump attack. It's in the room beyond.",
                    "Throw the stone upward to smash the block above the ladder, then crawl through. It's in the next corridor.",
                    "Just past the Keyzer, next to a diamond.",
                ),
                cd = null,
                keyzer = "At the end of the rolling tutorial, before the last jewel piece.",
                escapeTime = "0:15",
                escapeTip = "Only 15 seconds: set the stone by the steps before pressing the switch.",
            ),
            notes = listOf("The tutorial level. No CD here."),
        ),

        // ---------------------------------------------------------------- Emerald Passage

        Wl4Level(
            "ptp", "Palm Tree Paradise", EMERALD,
            normal = ModeData(
                jewels = listOf(
                    "In the section with the dotted red ! blocks.",
                    "In the section right after that.",
                    "Smash the group of blocks hiding a door. In the cave, climb the ladder.",
                    "Past that cave, follow the rising line of crystals and walk through the fake wall into the next cave. The box is right there.",
                ),
                cd = "From the last jewel piece, go down and walk through the right wall to the metal box.",
                keyzer = "Outside the caves, hit the red ! switch and climb the blocks it makes.",
                escapeTime = "1:30",
            ),
            hard = ModeData(
                jewels = listOf(
                    "In the section with the dotted red ! blocks.",
                    "At the passage blocked by small blocks: stand on the first one and jump into the fake wall above.",
                    "Smash the group of blocks hiding a door. In the cave, climb the ladder.",
                    "Past that cave, follow the rising line of crystals and walk through the fake wall into the next cave. The box is right there.",
                ),
                cd = "From the last jewel piece, go down and walk through the right wall to the metal box.",
                keyzer = "Outside the caves, hit the red ! switch and climb the blocks it makes.",
                escapeTime = "0:45",
            ),
        ),
        Wl4Level(
            "wff", "Wildflower Fields", EMERALD,
            normal = ModeData(
                jewels = listOf(
                    "Get into the cave under the blue block (float up as Puffy Wario and super smash it). Through the door on the right, swim up the currents; at the crossroad go right to the end.",
                    "From the very top of the level, drop to the platform below with the heart medal.",
                    "Back on the main path after floating up past the two red crystals, heading right.",
                    "After the switch, on the way left to the Keyzer.",
                ),
                cd = "At the start, get stung by the Beezley at the second flower and float as Puffy Wario to the very top.",
                keyzer = "After the switch, climb the new green platforms and head left.",
                escapeTime = "2:30",
            ),
            hard = ModeData(
                jewels = listOf(
                    "In the first cave (under the blue block), carry the stone up the platforms and throw it at the block above, then go back to the cave entrance and right.",
                    "Up the currents behind the door; at the crossroad go left and keep going up to the top.",
                    "Float up past the two red crystals as Puffy Wario. It's at the top.",
                    "At the right end of the leaf platform you float up to (heart at its far left).",
                ),
                cd = "At the start, get stung by the Beezley at the second flower and float as Puffy Wario to the very top.",
                keyzer = "After the switch, climb the new green platforms and head left.",
                escapeTime = "1:20",
            ),
        ),
        Wl4Level(
            "ml", "Mystic Lake", EMERALD,
            normal = ModeData(
                jewels = listOf(
                    "Swimming right through the lake, surface into the small room along the way.",
                    "Out of the water, in the corridor with the two Hammer guys.",
                    "After the switch, heading back left, next to the bridge.",
                    "Further left, on the third of three platforms.",
                ),
                cd = "After the switch, dash attack the blue block left of the first door, near the entrance.",
                keyzer = "After the switch, dive to the bottom, go right past the spinning maces; it's just out of the water at the top.",
                escapeTime = "3:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Just past the column of blocks in the lake, smash through the odd-looking bit of ceiling (two crystals below it). It's in the secret room, on the right.",
                    "Out of the water, go right, turn and dash back left across the water, and break the left wall.",
                    "In the Hammer guys' corridor, become Bouncy Wario where the floor dips and spring into the odd-looking ceiling.",
                    "In the bubble section, ride a bubble from the last bubble-maker past the downward current at the top.",
                ),
                cd = "After the switch, dash attack the blue block left of the first door, near the entrance.",
                keyzer = "After the switch, dive to the bottom, go right past the spinning maces; it's just out of the water at the top.",
                escapeTime = "2:00",
                escapeTip = "Don't eat the monkeys' apples on the way back: Fat Wario is slow.",
            ),
        ),
        Wl4Level(
            "mj", "Monsoon Jungle", EMERALD,
            normal = ModeData(
                jewels = listOf(
                    "Eat a monkey's apple, smash the blue block into the crystal cave as Fat Wario. It's back up from there.",
                    "Past the platform crossings to the left, dropping down toward the switch.",
                    "After the switch, go right; break the second group of blocks for a door, climb to the top inside and drop down.",
                    "Keep going right into the next area.",
                ),
                cd = "In the Fat Wario cave, pound the floor by the left wall until the Hammer guy bounces up. Get hit, spring as Bouncy Wario into the room above, and roll down its slope.",
                keyzer = "Roll down the first slope at the far left, jump at the end into a secret crystal passage, take its pipe back to the start, and ride the blue platform to its end.",
                escapeTime = "4:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "In the Fat Wario crystal cave, stay against the right wall after the last row of crystals to fall through a second weak block.",
                    "Before pressing the switch: dash-break the blue block behind it, climb the ladder, get stung by the arrow and float as Puffy Wario up past the entrance into a new area. Cross to the far left.",
                    "After the switch, in the door behind the second group of blocks: take the stone from behind the left wall up the platforms and power-throw it at the red block.",
                    "At the top of the ladder at the far right.",
                ),
                cd = "In the Fat Wario cave, pound the floor by the left wall until the Hammer guy bounces up. Get hit, spring as Bouncy Wario into the room above, and roll down its slope.",
                keyzer = "Roll down the first slope at the far left, jump at the end into a secret crystal passage, take its pipe back to the start, and ride the blue platform to its end.",
                escapeTime = "2:45",
            ),
        ),

        // ---------------------------------------------------------------- Ruby Passage

        Wl4Level(
            "cf", "The Curious Factory", RUBY,
            normal = ModeData(
                jewels = listOf(
                    "Use the first wheel to get over the wall, then drop below the second wheel.",
                    "In the corridor past the flame block (get lit by the Torchman), before the block at the end.",
                    "After the switch, in the room past the newly opened corridor on the far right.",
                    "At the far left, across the spinning and floating platforms.",
                ),
                cd = "After the switch, get flattened by a piston, drop through the newly opened passage at the bottom right of the wheel room, float right and slip through the tight hole.",
                keyzer = "On the conveyor belts: jump belt to belt. It's between the third and fourth.",
                escapeTime = "3:30",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Get flattened by a piston, float back left past the wheel to the platforms on the left, and slip through the tight hole. Touch the detector to turn back.",
                    "In the conveyor-belt room, go up to the top, head left and break the wall for a small room.",
                    "Down the hole at the end of the flame-block corridor, with metal birds around.",
                    "At the far left, across the spinning and floating platforms.",
                ),
                cd = "After the switch, get flattened by a piston, drop through the newly opened passage at the bottom right of the wheel room, float right and slip through the tight hole.",
                keyzer = "On the conveyor belts: jump belt to belt. It's between the third and fourth.",
                escapeTime = "2:50",
            ),
        ),
        Wl4Level(
            "tl", "The Toxic Landfill", RUBY,
            normal = ModeData(
                jewels = listOf(
                    "As Fat Wario (the left monkey's apple), break down through the blocks under the blue block. It's beside where you land.",
                    "Past the sewer, just before you turn Bouncy at the door.",
                    "After springing up the right side of the room as Bouncy Wario, in the upper passage going right.",
                    "After the switch, back in the first room.",
                ),
                cd = "Dash-break the blue block and clear the garbage heading right. After three chunks, smash down and go left.",
                keyzer = "After the switch, as Bouncy Wario, spring from right under the fifth block from the left.",
                escapeTime = "5:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Same Fat Wario drop, then break the wall left of the ladder until you reach it.",
                    "Spring up through the ceiling as Bouncy Wario, take the door, super smash the block at the bottom, and ride a bubble up on the left.",
                    "Through the secret door behind the tires: throw the monkey at the small blocks above, then spring up as Bouncy Wario.",
                    "After the switch, float as Puffy Wario to the very top of the first room.",
                ),
                cd = "Dash-break the blue block and clear the garbage heading right. After three chunks, smash down and go left.",
                keyzer = "After the switch, as Bouncy Wario, spring from right under the fifth block from the left.",
                escapeTime = "3:30",
            ),
        ),
        Wl4Level(
            "fridge", "40 Below Fridge", RUBY,
            normal = ModeData(
                jewels = listOf(
                    "Through the door after the first Snowman Wario roll.",
                    "After the next Snowman roll clears the way, at the top of the ladder.",
                    "Just inside the room you reach by rolling down the slope left of that ladder.",
                    "At the bottom of that same room.",
                ),
                cd = "After the switch, take the long roll down, climb past the Yetis, crawl through the small passage and break the block from below. Then from the vortex, go right.",
                keyzer = "Near the first jewel: skip the lower passage and take the next one down to its end.",
                escapeTime = "4:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "In the passages near the start: take the lower one and crouch-jump into the small opening below it.",
                    "After the ladder, roll down the right slope as Snowman Wario into a secret niche.",
                    "In the snowball room, roll down the second slope from the floor and jump at the small platform to break into a room. It's at the top.",
                    "As Snowman Wario, reach the slope above the bottom-left tunnel and slide.",
                ),
                cd = "After the switch, take the long roll down, climb past the Yetis, crawl through the small passage and break the block from below. Then from the vortex, go right.",
                keyzer = "Near the start: skip the lower passage and take the next one down to its end.",
                escapeTime = "3:30",
            ),
        ),
        Wl4Level(
            "pz", "Pinball Zone", RUBY,
            normal = ModeData(
                jewels = listOf(
                    "After the first ball room's door, roll down, then go back and break the small blocks.",
                    "In the second ball room, before you place the balls.",
                    "In the third ball room: eat a monkey's apple and break the blue block as Fat Wario.",
                    "At the start of the fourth ball room.",
                ),
                cd = "After the third ball room, roll down the slope, then work up and to the left.",
                keyzer = "After the switch, in the room past the one with the sparking antennas.",
                escapeTime = "6:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "After the first ball room's door, roll down, then go back and break the small blocks.",
                    "In the second ball room: from the top-left platform, crouch-jump down the left wall and press left at the hole.",
                    "In the third ball room, at the bottom: smash the wall on the right where the first mouth was.",
                    "In the fourth ball room, go left from the exit door and break the wall.",
                ),
                cd = "After the third ball room, roll down the slope, then work up and to the left.",
                keyzer = "After the switch, in the room past the one with the sparking antennas.",
                escapeTime = "5:15",
            ),
            notes = listOf("Each ball room opens once all four black balls are in the mouths. Slam the platforms under out-of-reach balls to knock them down."),
        ),

        // ---------------------------------------------------------------- Topaz Passage

        Wl4Level(
            "tbt", "Toy Block Tower", TOPAZ,
            normal = ModeData(
                jewels = listOf(
                    "Behind the second pyramid door.",
                    "Through the door above the blue block (knock the block down first).",
                    "Near the switch, on the platform above it.",
                    "After the switch, take the Keyzer and drop down the pipe: you land on it.",
                ),
                cd = "After the last jewel, take the pyramid to the door on the right (stand on the ball to reach the slot). The CD is inside.",
                keyzer = "After the switch, break the cat blocks under the blue block and jump to the upper floor.",
                escapeTime = "4:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Climb the columns, take the pyramid from the top, and open the door below.",
                    "At the bottom after the cat-block room: dash over the blue blocks and right, then dash again from the platform at the end of the corridor.",
                    "Behind the flame block on the far left (burn it as Flaming Wario, facing left), through the door below.",
                    "After the switch, from the pipe landing: push the red ball into the floor hole, crawl through, and use the blue block to cross.",
                ),
                cd = "After the last jewel, take the pyramid to the door on the right (stand on the ball to reach the slot). The CD is inside.",
                keyzer = "After the switch, break the cat blocks under the blue block and jump to the upper floor.",
                escapeTime = "2:30",
            ),
            notes = listOf("Winking cat blocks can be broken; still cat faces mean leave it; blue blocks never break."),
        ),
        Wl4Level(
            "bb", "The Big Board", TOPAZ,
            normal = ModeData(
                jewels = listOf(
                    "In the second area, follow the lower path.",
                    "Down the hole after the Flaming Wario section.",
                    "Down the steps after the first bonus pipe.",
                    "Further right, on the way to the Fat Wario slot machine.",
                ),
                cd = "Turn into Flat Wario at a slot machine and float into the hole high in the right wall.",
                keyzer = "After the switch, get the board's cursor to Goal. The floor vanishes; catch it as you fall.",
                escapeTime = "3:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Get Flaming Wario from a slot machine (face right), run left up the platforms and burn the flame block.",
                    "Get the Black Man from a slot and use one of its enemies to break the small blocks in the right wall.",
                    "As Fat Wario (from a slot), break the blue blocks. It's in the tunnel below.",
                    "As Flat Wario (from a slot), float into the hole high in the right wall.",
                ),
                cd = "After the switch and the Keyzer drop, go right along the small corridor above the blue blocks and break the blocks.",
                keyzer = "After the switch, get the board's cursor to Goal. The floor vanishes; catch it as you fall.",
                escapeTime = "2:30",
            ),
            notes = listOf("Slot results: arrows swap solid and dotted platforms, Wario's face transforms you, the bolt costs 400 coins, the Black Man spawns enemies."),
        ),
        Wl4Level(
            "dw", "Doodle Woods", TOPAZ,
            normal = ModeData(
                jewels = listOf(
                    "Inside the first blue wall zone at the start (jump in from the red platform).",
                    "Break the blocks in the second blue wall for a door.",
                    "After the switch, in the pencil-climbing section, up and to the right.",
                    "Further up and left in the pencil section, on the way out.",
                ),
                cd = "After rolling through the tight passage, take the door on the left.",
                keyzer = "Right after the switch, just below it.",
                escapeTime = "6:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Through the door behind the blocks in the second blue wall.",
                    "Bounce on a Hoggus's flying pig over the wall of small blocks and go right into the door.",
                    "In the section after the pencils: eat a monkey's apple, smash the block right of the pencil and follow the path.",
                    "Roll through the tight passage and take the door on the left.",
                ),
                cd = "After the switch, in the pencil-climbing section, up and to the right.",
                keyzer = "Right after the switch, just below it.",
                escapeTime = "5:15",
            ),
            notes = listOf("You can walk through the blue sections of wall."),
        ),
        Wl4Level(
            "dr", "Domino Row", TOPAZ,
            normal = ModeData(
                jewels = listOf(
                    "Beat the dominos to the button in the second room. The box sits above the next door.",
                    "Underwater, in the run-and-swim domino room.",
                    "After the switch, keep rolling down the slope and jump at each gap.",
                    "In the room below the Keyzer hole: break all the blocks.",
                ),
                cd = "Beat the dominos in the room after the underwater one to open a door.",
                keyzer = "After the switch, back in the entry room, drop down the hole.",
                escapeTime = "4:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Beat the dominos to the button in the second room. The box sits above the next door.",
                    "Underwater, in the run-and-swim domino room.",
                    "After the switch, keep rolling down the slope and jump at each gap.",
                    "In the room below the Keyzer hole: break all the blocks.",
                ),
                cd = "Beat the dominos in the room after the underwater one to open a door.",
                keyzer = "After the switch, back in the entry room, drop down the hole.",
                escapeTime = "3:00",
            ),
            notes = listOf("Hit the red button at the end of a row before the dominos do; it breaks walls you need."),
        ),

        // ---------------------------------------------------------------- Sapphire Passage

        Wl4Level(
            "cmv", "Crescent Moon Village", SAPPHIRE,
            normal = ModeData(
                jewels = listOf(
                    "Let a bat bite you and fly to the far left as Bat Wario, avoiding candlelight.",
                    "On the last platform before the door, roll down the slope to the left into a new room.",
                    "Hit the red ! switch and roll down the second slope it makes.",
                    "In the water below the hole, near the leftward current.",
                ),
                cd = "After the second jewel, roll again from the same spot to uncover a secret room.",
                keyzer = "After the switch, fly up to it as Bat Wario.",
                escapeTime = "3:30",
                escapeTip = "The big ghosts steal the Keyzer and lock the doors until you get it back. Keep away from them.",
            ),
            hard = ModeData(
                jewels = listOf(
                    "At the far left of the bat flight, smash the floor under the candle for a secret door. Use the stone inside and crawl through the passages.",
                    "Roll again from the new room left of the last platform to uncover the secret room.",
                    "Hit the red ! switch and roll down the first slope into a small niche.",
                    "Swim left into the leftward current in the water below the hole.",
                ),
                cd = "On the last platform before the door, roll down the slope to the left into a new room.",
                keyzer = "After the switch, fly up to it as Bat Wario.",
                escapeTime = "2:45",
                escapeTip = "The big ghosts steal the Keyzer and lock the doors until you get it back. Keep away from them.",
            ),
        ),
        Wl4Level(
            "an", "Arabian Night", SAPPHIRE,
            normal = ModeData(
                jewels = listOf(
                    "In the room full of boxes, after the first carpets.",
                    "Ride the next carpet up and go right.",
                    "Near the switch: drop as Zombie Wario between the last two sets of spikes.",
                    "After the switch, in the water to the left.",
                ),
                cd = "After the last jewel, fly the final carpet left (it dissolves in water), then go down the ladder.",
                keyzer = "In the room with the Beezley and bat cylinders: become Bat Wario, fly up to the ladder and go down.",
                escapeTime = "4:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "In the box room, turn into Zombie Wario at the ladder and drop through the platforms on the left.",
                    "Dash attack as the carpet lands to break the blue block; it's at the bottom of the next ladder.",
                    "After the Keyzer room, dash-jump into the indentation in the right wall.",
                    "After the switch, super slam at the crystal arrow to break the box below, then swim to it.",
                ),
                cd = "After the last jewel, fly the final carpet left (it dissolves in water), then go down the ladder.",
                keyzer = "In the room with the Beezley and bat cylinders: become Bat Wario, fly up to the ladder and go down.",
                escapeTime = "3:00",
            ),
            notes = listOf("Carpets follow the way you face. Move gently or they'll leave without you."),
        ),
        Wl4Level(
            "fc", "Fiery Cavern", SAPPHIRE,
            normal = ModeData(
                jewels = listOf(
                    "Past the platforms over the first lava pillar, before the second bonus pipe.",
                    "Through the door after the ladder, go left.",
                    "After the switch (the cave freezes), down the ladder in the room after the second door.",
                    "In the last room, go up past the Yeti and break the wall at the top left.",
                ),
                cd = "After the switch, let a Yeti freeze you from its right side so you slide all the way left.",
                keyzer = "After the switch, up the platforms past the ladder.",
                escapeTime = "5:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Through the door after the ladder, dash-jump left across the pit.",
                    "After the switch, down the ladder in the room after the door.",
                    "In the next room, as Snowman Wario roll left through the wall.",
                    "In the last room, go up past the Yeti and break the wall at the top left.",
                ),
                cd = "After the switch, let a Yeti freeze you from its right side so you slide all the way left.",
                keyzer = "After the switch, up the platforms past the ladder.",
                escapeTime = "4:00",
            ),
            notes = listOf("Hitting the switch freezes the whole cave and turns the boulder throwers into Yetis."),
        ),
        Wl4Level(
            "hh", "Hotel Horror", SAPPHIRE,
            normal = ModeData(
                jewels = listOf(
                    "At the end of the first corridor, right of Room 104.",
                    "In the corridor, left of Room 302.",
                    "Right after Door 201, before Room 202.",
                    "Right after Door 402.",
                ),
                cd = "After the switch, Room 203: break the blocks, go past the wall, and drop as Zombie Wario.",
                keyzer = "Room 401: burn the flame block as Flaming Wario.",
                escapeTime = "4:00",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Room 102: turn into Bat Wario.",
                    "After Room 401, as Zombie Wario drop to the very bottom of the stairs.",
                    "Room 202: become Bat Wario, fly right and up, then left.",
                    "Room 403: as Zombie Wario fall through the floor just right of the door.",
                ),
                cd = "After the switch, Room 203: break the blocks, go past the wall, and drop as Zombie Wario.",
                keyzer = "Room 401: burn the flame block as Flaming Wario.",
                escapeTime = "3:15",
            ),
        ),

        // ---------------------------------------------------------------- Golden Pyramid

        Wl4Level(
            "gp", "Golden Passage", GOLDEN,
            normal = ModeData(
                jewels = listOf(
                    "Dive and ride the first bubble, steering slightly right.",
                    "Go right and drop down.",
                    "Past the snowballs, become Bat Wario and fly up and left.",
                    "Hit the ! switch, climb, and roll down the slope. It's where you stop.",
                ),
                cd = null,
                keyzer = "Break the big block with the Professor, roll down again, and jump the two pits.",
                escapeTime = "9:30",
                escapeTip = "You land on the switch as you enter, so the clock runs the whole level. Keep moving: chandeliers fall and platforms crumble.",
            ),
            hard = ModeData(
                jewels = listOf(
                    "Dive and ride the first bubble, steering slightly right.",
                    "Go right and drop down.",
                    "Past the snowballs, become Bat Wario and fly up and left.",
                    "Hit the ! switch, climb, and roll down the slope. It's where you stop.",
                ),
                cd = null,
                keyzer = "Break the big block with the Professor, roll down again, and jump the two pits.",
                escapeTime = "6:00",
                escapeTip = "You land on the switch as you enter, so the clock runs the whole level. Keep moving: chandeliers fall and platforms crumble.",
            ),
            notes = listOf("Opens once all four main bosses are beaten. No CD here."),
        ),
    )

    val byId: Map<String, Wl4Level> = all.associateBy { it.id }

    fun allItems(mode: Mode): List<Wl4Item> = all.flatMap { it.items(mode) }
}
