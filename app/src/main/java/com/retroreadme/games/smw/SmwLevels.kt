package com.retroreadme.games.smw

import com.retroreadme.games.smw.LevelType.CASTLE
import com.retroreadme.games.smw.LevelType.FORTRESS
import com.retroreadme.games.smw.LevelType.GHOST_HOUSE
import com.retroreadme.games.smw.LevelType.LEVEL
import com.retroreadme.games.smw.LevelType.SWITCH_PALACE
import com.retroreadme.games.smw.World.CHOCOLATE
import com.retroreadme.games.smw.World.DONUT_PLAINS
import com.retroreadme.games.smw.World.FOREST
import com.retroreadme.games.smw.World.SPECIAL
import com.retroreadme.games.smw.World.STAR_WORLD
import com.retroreadme.games.smw.World.TWIN_BRIDGES
import com.retroreadme.games.smw.World.VALLEY
import com.retroreadme.games.smw.World.VANILLA_DOME
import com.retroreadme.games.smw.World.YOSHIS_ISLAND

/**
 * Every level on the map, in map order, with all 96 exits (72 normal, 24 secret).
 * Exit routes and destinations were checked against at least two independent guides,
 * then confirmed in a full playthrough.
 */
object SmwLevels {

    private const val GOAL = "Reach the Giant Gate at the end of the level."

    private val reznor = listOf(
        "Reznor: four of them ride a spinning wheel. Jump up and hit the block under each one to knock it off.",
        "The bridge floor crumbles from the middle as the fight goes on, so don't take too long.",
    )

    private fun palace(color: String) = ExitInfo(
        leadsTo = "No new path. Fills in the $color ! blocks.",
        steps = listOf("Jump on the big switch. It fills in every $color outline block in the game, and the game offers to save."),
    )

    val all: List<Level> = listOf(

        // ---------------------------------------------------------------- Yoshi's Island (6)

        level("yi1", "Yoshi's Island 1", YOSHIS_ISLAND, LEVEL, ExitInfo("Yellow Switch Palace", listOf(GOAL))),
        level("ysp", "Yellow Switch Palace", YOSHIS_ISLAND, SWITCH_PALACE, palace("yellow")),
        level("yi2", "Yoshi's Island 2", YOSHIS_ISLAND, LEVEL, ExitInfo("Yoshi's Island 3", listOf(GOAL))),
        level("yi3", "Yoshi's Island 3", YOSHIS_ISLAND, LEVEL, ExitInfo("Yoshi's Island 4", listOf(GOAL))),
        level("yi4", "Yoshi's Island 4", YOSHIS_ISLAND, LEVEL, ExitInfo("#1 Iggy's Castle", listOf(GOAL))),
        level(
            "iggy", "#1 Iggy's Castle", YOSHIS_ISLAND, CASTLE,
            ExitInfo("Donut Plains 1", listOf("Defeat Iggy.")),
            boss = listOf("Iggy: the platform tilts over lava. Stomp him (or hit him with fireballs) to push him toward the edge until he falls in."),
        ),

        // ---------------------------------------------------------------- Donut Plains (15)

        level(
            "dp1", "Donut Plains 1", DONUT_PLAINS, LEVEL,
            normal = ExitInfo("Donut Plains 2", listOf(GOAL)),
            secret = ExitInfo(
                "Donut Secret 1",
                needs = listOf("Yoshi", "or Green blocks"),
                steps = listOf(
                    "Near the end, the key and keyhole are up on the thin yellow pipes overhead.",
                    "Riding Yoshi, from the top of the pipe, do a Yoshi jump: jump with Yoshi, then jump off his back at the top of the arc for extra height, and land up by the key.",
                    "With the Green Switch Palace done, a column of green blocks near the goal lets you climb up instead.",
                    "A Cape flight from the hills can also get up there, but the Yoshi jump is much easier.",
                ),
            ),
            notes = listOf("With the Yellow Switch Palace done, you can go up a pipe partway through into a 1-Up room."),
        ),
        level(
            "dp2", "Donut Plains 2", DONUT_PLAINS, LEVEL,
            normal = ExitInfo("Donut Ghost House", listOf(GOAL)),
            secret = ExitInfo(
                "Green Switch Palace",
                needs = listOf("Cape", "or big Mario", "or Yoshi"),
                steps = listOf(
                    "Just past the green ! block, take the pipe above you into a side passage.",
                    "The key is through a small hole in the ceiling of that room. Any of these gets you up there:",
                    "Cape: spin-fly up through the hole, or hit the highest of the four blocks to grow a vine.",
                    "Big Mario: spin jump to break the blocks above the blue shell at the end, then kick the shell up into that highest block for the vine.",
                    "Yoshi: eat the blue shell and fly up through the hole.",
                ),
            ),
        ),
        level("gsp", "Green Switch Palace", DONUT_PLAINS, SWITCH_PALACE, palace("green")),
        level(
            "dgh", "Donut Ghost House", DONUT_PLAINS, GHOST_HOUSE,
            normal = ExitInfo(
                "Donut Plains 3",
                listOf(
                    "Head right and go through the door at the far right.",
                    "In the next room, hit the block: a P-Switch comes out. Ignore it and go back through the door on the right.",
                    "You land in what looks like the same room. Hit the block again and this time a vine grows.",
                    "Climb the vine and take the door at the top to the goal.",
                ),
            ),
            secret = ExitInfo(
                "Top Secret Area",
                needs = listOf("Cape"),
                steps = listOf(
                    "Right at the start, run a little to the right for a runway, then run back left and take off.",
                    "Fly up through the opening at the top left, above the Boos, and land on the upper floor.",
                    "Run right to a room with four blocks (each hides a 1-Up) and a door. The door leads to the secret goal.",
                ),
            ),
        ),
        level("dp3", "Donut Plains 3", DONUT_PLAINS, LEVEL, ExitInfo("Donut Plains 4", listOf(GOAL))),
        level("dp4", "Donut Plains 4", DONUT_PLAINS, LEVEL, ExitInfo("#2 Morton's Castle", listOf(GOAL))),
        level(
            "ds1", "Donut Secret 1", DONUT_PLAINS, LEVEL,
            normal = ExitInfo("Donut Ghost House", listOf(GOAL)),
            secret = ExitInfo(
                "Donut Secret House",
                needs = listOf("P-Switch (in the level)"),
                steps = listOf(
                    "The key is in a ? block boxed in by other blocks. A P-Switch not far away turns those blocks into coins.",
                    "The fish don't come back once defeated, so clear the path first, then carry the P-Switch close to the keyhole.",
                    "Step on it, grab the key from the ? block, and take it to the keyhole.",
                ),
            ),
        ),
        level(
            "dsh", "Donut Secret House", DONUT_PLAINS, GHOST_HOUSE,
            normal = ExitInfo(
                "Donut Secret 2",
                listOf(
                    "In the second room, collect the five coins shaped like a door on the small platform first.",
                    "Fetch the P-Switch from the far left, bring it back to that platform and step on it. A blue door appears: it leads to the goal.",
                    "If you skip the coins, they turn into blocks around the door and you can't get in.",
                ),
            ),
            secret = ExitInfo(
                "Star Road (Star World 1)",
                needs = listOf("P-Switch (in the level)"),
                steps = listOf(
                    "Carry the P-Switch to the floating blocks near the Message Block, under the door in mid-air, and step on it.",
                    "? blocks appear. Stand on the middle one and hit the block above it to grow a vine. Don't use the mid-air door: it's a trap.",
                    "Climb the vine and run right to a hidden door. Get there before the P-Switch runs out.",
                    "With a Cape you can instead carry the P-Switch and fly straight up to that corridor.",
                    "The door leads to a Big Boo fight (see Boss).",
                ),
            ),
            boss = listOf(
                "Big Boo, with a couple of small Boos around him. Pick up blocks from the floor and throw them at him while he's visible.",
                "Three hits wins. Don't dig a hole under yourself: the floor is your ammunition.",
            ),
        ),
        level("ds2", "Donut Secret 2", DONUT_PLAINS, LEVEL, ExitInfo("Donut Plains 3", listOf(GOAL))),
        level(
            "morton", "#2 Morton's Castle", DONUT_PLAINS, CASTLE,
            ExitInfo("Vanilla Dome 1", listOf("Defeat Morton.")),
            boss = listOf("Morton: stomp his head three times. He climbs the walls and ceiling; his landing stuns you if you're standing on the floor, so be in the air when he drops."),
        ),

        // ---------------------------------------------------------------- Vanilla Dome (14)

        level(
            "vd1", "Vanilla Dome 1", VANILLA_DOME, LEVEL,
            normal = ExitInfo("Vanilla Dome 2", listOf(GOAL)),
            secret = ExitInfo(
                "Vanilla Secret 1",
                needs = listOf("Red blocks"),
                steps = listOf(
                    "Needs the Red Switch Palace (secret exit of Vanilla Dome 2), so come back later.",
                    "Find the ? block with a staircase of red ! blocks beside it.",
                    "Climb the red blocks, hit the block above them to grow a vine, and climb it to the key and keyhole.",
                ),
            ),
        ),
        level(
            "vd2", "Vanilla Dome 2", VANILLA_DOME, LEVEL,
            normal = ExitInfo("Vanilla Ghost House", listOf(GOAL)),
            secret = ExitInfo(
                "Red Switch Palace",
                needs = listOf("P-Switch (in the level)"),
                steps = listOf(
                    "Find the item block by a very steep ramp. Run up the ramp, turn around, and jump across to the upper path.",
                    "Go up and left to a P-Switch. Carry it back to the wall of used blocks in your way and step on it.",
                    "Run through the coins. Jump the first pit, but drop down the second one.",
                    "The key is on the ground below. Take it down through the water to the keyhole (clearing the fish first helps).",
                ),
            ),
        ),
        level("rsp", "Red Switch Palace", VANILLA_DOME, SWITCH_PALACE, palace("red")),
        level(
            "vgh", "Vanilla Ghost House", VANILLA_DOME, GHOST_HOUSE,
            ExitInfo(
                "Vanilla Dome 3",
                listOf(
                    "Get through the first room (one turn block near the Big Boo hides a vine that skips most of it) and take the door at the end.",
                    "In the second area, the door at the very end is a trap back to the start.",
                    "The middle turn block just before it hides a P-Switch. Carry it to the coin formation at the end.",
                    "Collect the coins first, then step on the P-Switch. A blue door appears: it leads to the goal.",
                ),
            ),
            notes = listOf("The ? block in front of the Big Boo has a Cape Feather."),
        ),
        level("vd3", "Vanilla Dome 3", VANILLA_DOME, LEVEL, ExitInfo("Vanilla Dome 4", listOf(GOAL))),
        level("vd4", "Vanilla Dome 4", VANILLA_DOME, LEVEL, ExitInfo("#3 Lemmy's Castle", listOf(GOAL))),
        level(
            "vs1", "Vanilla Secret 1", VANILLA_DOME, LEVEL,
            normal = ExitInfo("Vanilla Secret 2", listOf(GOAL)),
            secret = ExitInfo(
                "Star Road (Star World 2)",
                needs = listOf("Cape", "or Blue blocks"),
                steps = listOf(
                    "Climb to the section with the springboard and Koopa Paratroopas. The secret is in a pipe up to the left.",
                    "Cape: get a run-up along the floor, take off, and hold jump and left to fly up into the pipe.",
                    "Blue Switch Palace done: set the springboard on the blue blocks and bounce up instead.",
                    "Go through the pipe and on to the secret goal.",
                ),
            ),
        ),
        level("vs2", "Vanilla Secret 2", VANILLA_DOME, LEVEL, ExitInfo("Vanilla Secret 3", listOf(GOAL))),
        level("vs3", "Vanilla Secret 3", VANILLA_DOME, LEVEL, ExitInfo("Vanilla Fortress", listOf(GOAL))),
        level("vanfort", "Vanilla Fortress", VANILLA_DOME, FORTRESS, ExitInfo("Butter Bridge 1", listOf("Defeat the Reznors.")), boss = reznor),
        level(
            "lemmy", "#3 Lemmy's Castle", VANILLA_DOME, CASTLE,
            ExitInfo("Cheese Bridge Area", listOf("Defeat Lemmy.")),
            boss = listOf("Lemmy pops out of the pipes along with two decoys. Stomp the real one three times, and keep an eye on the bouncing Podoboo."),
        ),

        // ---------------------------------------------------------------- Twin Bridges (7)

        level(
            "cba", "Cheese Bridge Area", TWIN_BRIDGES, LEVEL,
            normal = ExitInfo("Cookie Mountain", listOf(GOAL)),
            secret = ExitInfo(
                "Soda Lake",
                needs = listOf("Cape"),
                steps = listOf(
                    "The secret goal is past the normal one, below it, where you can't just walk.",
                    "With Yoshi and a Cape: float off the ledge to the right, pass under the normal goal, then jump off Yoshi onto the ground behind it.",
                    "Cape only: keep a flight going to the last two saws, dive under the normal goal, then fly back up to the secret one.",
                ),
            ),
            notes = listOf("A block partway through has Yoshi's wings; touching them with Yoshi turns him blue in the SNES version."),
        ),
        level(
            "soda", "Soda Lake", TWIN_BRIDGES, LEVEL,
            ExitInfo("Star Road (Star World 3)", listOf(GOAL)),
            notes = listOf("The path this opens on the map is invisible, which is why it's easy to miss."),
        ),
        level("cookie", "Cookie Mountain", TWIN_BRIDGES, LEVEL, ExitInfo("#4 Ludwig's Castle", listOf(GOAL))),
        level("bb1", "Butter Bridge 1", TWIN_BRIDGES, LEVEL, ExitInfo("Butter Bridge 2", listOf(GOAL))),
        level("bb2", "Butter Bridge 2", TWIN_BRIDGES, LEVEL, ExitInfo("#4 Ludwig's Castle", listOf(GOAL))),
        level(
            "ludwig", "#4 Ludwig's Castle", TWIN_BRIDGES, CASTLE,
            ExitInfo("Forest of Illusion 1", listOf("Defeat Ludwig.")),
            boss = listOf("Ludwig: stomp him three times. After each hit he tucks into his shell and chases you. Jump over it, then stomp again when he comes out."),
        ),

        // ---------------------------------------------------------------- Forest of Illusion (14)

        level(
            "foi1", "Forest of Illusion 1", FOREST, LEVEL,
            normal = ExitInfo("Forest of Illusion 2", listOf(GOAL)),
            secret = ExitInfo(
                "Forest Ghost House",
                needs = listOf("P-Balloon (in the level)"),
                steps = listOf(
                    "Get the P-Balloon from its block. Instead of floating right, float left, underneath the wooden logs.",
                    "Keep going to a block and a keyhole. The block holds the key.",
                    "Wait for the balloon to wear off, then take the key to the keyhole.",
                ),
            ),
        ),
        level(
            "foi2", "Forest of Illusion 2", FOREST, LEVEL,
            normal = ExitInfo("Forest of Illusion 3", listOf(GOAL)),
            secret = ExitInfo(
                "Blue Switch Palace",
                steps = listOf(
                    "Near the end there's a yellow block and what looks like a wall blocking a passage.",
                    "The wall isn't solid. Swim through it to the key and keyhole.",
                ),
            ),
        ),
        level("bsp", "Blue Switch Palace", FOREST, SWITCH_PALACE, palace("blue")),
        level(
            "foi3", "Forest of Illusion 3", FOREST, LEVEL,
            normal = ExitInfo("Forest Ghost House", listOf(GOAL)),
            secret = ExitInfo(
                "#5 Roy's Castle",
                needs = listOf("Big Mario"),
                steps = listOf(
                    "Near the Splittin' Chuck there's a tall green pipe. Go down it.",
                    "Spin jump through the turn blocks inside (you have to be big to break them) to reach the key and keyhole.",
                ),
            ),
        ),
        level(
            "fgh", "Forest Ghost House", FOREST, GHOST_HOUSE,
            normal = ExitInfo(
                "Forest of Illusion 4",
                listOf(
                    "In the second area, ignore the yellow door past the P-Switch: it leads back to the start.",
                    "Carry the P-Switch into the next area with the line of coins, go to its right side and step on it. A hidden door appears.",
                    "It puts you on top of the corridor you came through. Go left and take the first door you reach.",
                ),
            ),
            secret = ExitInfo(
                "Forest of Illusion 1 (loops back)",
                steps = listOf(
                    "Follow the normal route to the top of the corridor, but go past the first door and the Big Boo.",
                    "Take the next door. You'll know it's the right one: there's a 3-Up Moon right before the goal.",
                    "The path it opens goes back to Forest of Illusion 1, but it still counts toward the 96.",
                ),
            ),
            notes = listOf("One of the ? blocks in the large hall has a Cape Feather."),
        ),
        level(
            "foi4", "Forest of Illusion 4", FOREST, LEVEL,
            normal = ExitInfo("Forest of Illusion 2 (already open)", listOf(GOAL)),
            secret = ExitInfo(
                "Forest Secret Area",
                steps = listOf(
                    "Past the midway gate there's a raised blue pipe with a Lakitu inside it.",
                    "Get rid of the Lakitu and go down the pipe. The key and keyhole are in the room below.",
                ),
            ),
        ),
        level("fsa", "Forest Secret Area", FOREST, LEVEL, ExitInfo("Forest Fortress", listOf(GOAL))),
        level(
            "ffort", "Forest Fortress", FOREST, FORTRESS,
            ExitInfo("Star Road (Star World 4)", listOf("Defeat the Reznors.")),
            boss = reznor,
            notes = listOf(
                "Secret route (Cape needed): take off and fly up over the path above the red door, and keep flying all the way along.",
                "Up there are nine 1-Up Mushrooms and a second boss door. It leads to the same Reznor fight, so it's not an extra exit.",
            ),
        ),
        level(
            "roy", "#5 Roy's Castle", FOREST, CASTLE,
            ExitInfo("Chocolate Island 1", listOf("Defeat Roy.")),
            boss = listOf("Roy: the same fight as Morton (three stomps), but he's faster and the walls slowly close in."),
        ),

        // ---------------------------------------------------------------- Chocolate Island (11)

        level("ci1", "Chocolate Island 1", CHOCOLATE, LEVEL, ExitInfo("Choco-Ghost House", listOf(GOAL))),
        level("cgh", "Choco-Ghost House", CHOCOLATE, GHOST_HOUSE, ExitInfo("Chocolate Island 2", listOf("Reach the goal. This Ghost House has no secret exit."))),
        level(
            "ci2", "Chocolate Island 2", CHOCOLATE, LEVEL,
            normal = ExitInfo(
                "Chocolate Island 3",
                listOf(
                    "This level changes depending on your coins and timer (see Hints: Chocolate Island 2).",
                    "Any route that doesn't hit the timer check ends at a Giant Gate.",
                ),
            ),
            secret = ExitInfo(
                "Chocolate Secret",
                needs = listOf("250+ on the timer"),
                steps = listOf(
                    "Hurry: the pipe at the end of the second area sends you to the secret area only if the timer shows 250 or more.",
                    "Coins in the first area only change which second area you get, not whether you can make it.",
                    "In the secret area, get past the Chucks (grab blocks help), pick up the key after the gap, and carry it right to the keyhole.",
                ),
            ),
        ),
        level(
            "ci3", "Chocolate Island 3", CHOCOLATE, LEVEL,
            normal = ExitInfo("Chocolate Island 3 again (the path loops back)", listOf(GOAL)),
            secret = ExitInfo(
                "Chocolate Fortress",
                needs = listOf("Cape"),
                steps = listOf(
                    "Play to the end of the level, near the normal goal.",
                    "Get a run-up, take off, and fly right, underneath the normal goal, until you reach the secret one.",
                    "You need this one to move on: the normal exit just loops back to the same level.",
                ),
            ),
        ),
        level("cfort", "Chocolate Fortress", CHOCOLATE, FORTRESS, ExitInfo("Chocolate Island 4", listOf("Defeat the Reznors.")), boss = reznor),
        level("ci4", "Chocolate Island 4", CHOCOLATE, LEVEL, ExitInfo("Chocolate Island 5", listOf(GOAL))),
        level("ci5", "Chocolate Island 5", CHOCOLATE, LEVEL, ExitInfo("#6 Wendy's Castle", listOf(GOAL))),
        level("csec", "Chocolate Secret", CHOCOLATE, LEVEL, ExitInfo("#6 Wendy's Castle", listOf(GOAL))),
        level(
            "wendy", "#6 Wendy's Castle", CHOCOLATE, CASTLE,
            ExitInfo("Sunken Ghost Ship", listOf("Defeat Wendy.")),
            boss = listOf("Wendy: the same fight as Lemmy (stomp the real one three times among the decoys), but with two Podoboos."),
        ),

        // ---------------------------------------------------------------- Valley of Bowser (11)

        level(
            "sgs", "Sunken Ghost Ship", VALLEY, GHOST_HOUSE,
            ExitInfo("Valley of Bowser 1", listOf("Reach the end of the ship.")),
            notes = listOf("The only Ghost House Yoshi can go into."),
        ),
        level("vob1", "Valley of Bowser 1", VALLEY, LEVEL, ExitInfo("Valley of Bowser 2", listOf(GOAL))),
        level(
            "vob2", "Valley of Bowser 2", VALLEY, LEVEL,
            normal = ExitInfo("Valley Ghost House", listOf(GOAL)),
            secret = ExitInfo(
                "Valley Fortress",
                steps = listOf(
                    "Go through the second pipe and past the first section where the ground rises and falls.",
                    "Get on top of the raised ground and jump up onto the top of the ceiling.",
                    "Run left along the top to the key and keyhole.",
                ),
            ),
        ),
        level(
            "valgh", "Valley Ghost House", VALLEY, GHOST_HOUSE,
            normal = ExitInfo("Valley of Bowser 3", listOf("Reach the goal.")),
            secret = ExitInfo(
                "#7 Larry's Castle",
                needs = listOf("P-Switches (in the level)", "or Cape"),
                steps = listOf(
                    "In the big second room, hit the block above you for a P-Switch, step on it, and run right as fast as you can to the last door before it runs out.",
                    "Pick up the P-Switch and go right to the ? block and hit it. A Control Coin comes out and leaves a trail of coins; it keeps going the last way you pressed.",
                    "Build steps: keep alternating right, up, right, up on the D-pad so the trail climbs in a stair-step pattern toward the top-right corner. It runs for about 10 seconds on the timer.",
                    "If you're big, let a Boo hit you so you're small; the gap by the key is only one block tall.",
                    "Step on the P-Switch: the coins turn into blocks. Climb the steps, then run and jump through the narrow gap at the top.",
                    "Carry the key right to the keyhole. If you miss, go back down through the door and redo the P-Switch run from the big room.",
                    "Cape: skip the coin trail entirely. Run, take off, and fly or spin-jump up to the top (the Top Secret Area is a handy place to grab a Cape first).",
                ),
            )
        ),
        level("vob3", "Valley of Bowser 3", VALLEY, LEVEL, ExitInfo("Valley of Bowser 4", listOf(GOAL))),
        level(
            "vob4", "Valley of Bowser 4", VALLEY, LEVEL,
            normal = ExitInfo("#7 Larry's Castle", listOf(GOAL)),
            secret = ExitInfo(
                "Star Road (Star World 5) and Bowser's Castle Front Door",
                needs = listOf("Yoshi"),
                steps = listOf(
                    "Just past the first pipe, hit the block for a vine, climb it, and hit the ? block at the top for a Yoshi egg.",
                    "Keep Yoshi all the way to the end. Lose him and this exit is impossible for that attempt.",
                    "Right before the goal, the key is sealed inside the wall. Have Yoshi grab it through the wall with his tongue.",
                    "Walk Yoshi in front of the keyhole above it with the key in his mouth.",
                ),
            ),
        ),
        level("valfort", "Valley Fortress", VALLEY, FORTRESS, ExitInfo("Bowser's Castle Back Door", listOf("Defeat the Reznors.")), boss = reznor),
        level(
            "larry", "#7 Larry's Castle", VALLEY, CASTLE,
            ExitInfo("Bowser's Castle Front Door", listOf("Defeat Larry.")),
            boss = listOf("Larry: the same fight as Iggy (knock him into the lava), with fireballs leaping out of the lava at the left, middle and right."),
        ),
        level(
            "bowser", "Bowser's Castle", VALLEY, CASTLE,
            normal = null,
            boss = listOf(
                "Three phases. In each one, hit Bowser twice with Mechakoopas.",
                "Stomp a Mechakoopa, pick it up, and throw it up so it comes down on his head. Throwing it straight into the Clown Car's propeller or underside doesn't count.",
                "In phase two he drops big steel balls; spin jump off them or dodge.",
            ),
            notes = listOf(
                "Not part of the 96: beating Bowser isn't an exit.",
                "Front Door: from Larry's Castle, Valley of Bowser 4's secret, or Star World 4's secret. Back Door: from Valley Fortress.",
            ),
        ),

        // ---------------------------------------------------------------- Star World (10)

        level(
            "sw1", "Star World 1", STAR_WORLD, LEVEL,
            normal = ExitInfo("Back to the Star Road warp", listOf(GOAL)),
            secret = ExitInfo(
                "Star World 2",
                needs = listOf("Big Mario"),
                steps = listOf(
                    "At the second stretch of yellow turn blocks, go to the far right.",
                    "Spin jump down through the blocks, hugging the right wall, to land next to the key and keyhole.",
                ),
            ),
        ),
        level(
            "sw2", "Star World 2", STAR_WORLD, LEVEL,
            normal = ExitInfo("Back to the Star Road warp", listOf("Take the pipe at the end to the goal.")),
            secret = ExitInfo(
                "Star World 3",
                steps = listOf(
                    "Don't enter the pipe at the end. Look under it: there's a narrow underwater passage.",
                    "Swim along it to the right to the key and keyhole.",
                ),
            ),
            notes = listOf("A blue Yoshi egg is in this level."),
        ),
        level(
            "sw3", "Star World 3", STAR_WORLD, LEVEL,
            normal = ExitInfo("Back to the Star Road warp", listOf(GOAL)),
            secret = ExitInfo(
                "Star World 4",
                needs = listOf("Cape", "or Lakitu's cloud"),
                steps = listOf(
                    "The key is in an alcove above the ceiling.",
                    "Cape: fly up beside the highest of the four blue blocks. Or: throw a grab block up at the Lakitu; the cloud stays behind, so ride it up.",
                    "Hit the ? block on the left for the key, then carry it to the keyhole on the right.",
                ),
            ),
        ),
        level(
            "sw4", "Star World 4", STAR_WORLD, LEVEL,
            normal = ExitInfo("Back to the Star Road warp", listOf(GOAL)),
            secret = ExitInfo(
                "Star World 5 and Bowser's Castle Front Door",
                needs = listOf("Green + Red blocks", "Shell or Cape"),
                steps = listOf(
                    "With the Green and Red Switch Palaces done: late in the level, drop from the pipe section onto the green ! blocks below and follow the red ! blocks right.",
                    "The key is in a ? block beside the keyhole. Bring a shell to kick into it, or spin into it with the Cape.",
                    "Without both palaces: Blue Yoshi with a shell can fly down and right to it, or a Cape flight kept low under the platforms can reach it.",
                ),
            ),
            notes = listOf("A red Yoshi egg is at the start. There's a Cape Feather in a ? block partway through if you're big."),
        ),
        level(
            "sw5", "Star World 5", STAR_WORLD, LEVEL,
            normal = ExitInfo("Star World 1 (only Star World normal exit that opens a path)", listOf(GOAL)),
            secret = ExitInfo(
                "Special Zone (Gnarly)",
                needs = listOf("All four palaces", "or Cape", "or Blue Yoshi"),
                steps = listOf(
                    "At the Switch Block, hit the ? block for a line of coins and immediately hold right so it heads right.",
                    "Near the end of its run, hit the Switch Block: the coin trail turns into blocks. Run along them.",
                    "All four palaces done: hit the third of the four blocks at the end for a vine, then follow the yellow, green, red and blue ! blocks to the key (slide under the gray blocks if you're big).",
                    "Cape: take off from those blocks and land up on the ! block trail instead.",
                    "Blue Yoshi: eat the red Koopa, go left past the green pipe, and fly up into the key room.",
                ),
            ),
        ),

        // ---------------------------------------------------------------- Special Zone (8)

        level("gnarly", "Gnarly", SPECIAL, LEVEL, ExitInfo("Tubular", listOf(GOAL)), notes = listOf("The block at the far right of the first row of blocks lets you climb faster.")),
        level("tubular", "Tubular", SPECIAL, LEVEL, ExitInfo("Way Cool", listOf(GOAL)), notes = listOf("Notoriously hard. Build up lives first.")),
        level(
            "waycool", "Way Cool", SPECIAL, LEVEL, ExitInfo("Awesome", listOf(GOAL)),
            notes = listOf("Go through the yellow pipe near the end of the first moving-platform part for a Yoshi, then touch the wings from the ? block to fly."),
        ),
        level(
            "awesome", "Awesome", SPECIAL, LEVEL, ExitInfo("Groovy", listOf(GOAL)),
            notes = listOf("Carry the P-Switch to where the shell-less Koopa kicks the yellow shell, step on it, and get the Star from the ? block there."),
        ),
        level(
            "groovy", "Groovy", SPECIAL, LEVEL, ExitInfo("Mondo", listOf(GOAL)),
            notes = listOf("Yoshi is in the first ? block on the ground. A Cape helps; take the Star from the roulette block and run."),
        ),
        level(
            "mondo", "Mondo", SPECIAL, LEVEL, ExitInfo("Outrageous", listOf(GOAL)),
            notes = listOf("Bring a Cape and a spare in reserve. There's a ? block with a Feather early on if you're powered up."),
        ),
        level("outrageous", "Outrageous", SPECIAL, LEVEL, ExitInfo("Funky", listOf(GOAL))),
        level(
            "funky", "Funky", SPECIAL, LEVEL,
            ExitInfo("A Star Road warp back to Yoshi's Island", listOf(GOAL)),
            notes = listOf("Beating Funky switches Dinosaur Land to its autumn look. Save it for last if you'd rather keep summer."),
        ),
    )

    val byId: Map<String, Level> = all.associateBy { it.id }
    val allExits: List<Exit> = all.flatMap { it.exits }
    val secretExits: List<Pair<Level, Exit>> = all.flatMap { l -> l.exits.filter { it.kind == ExitKind.SECRET }.map { l to it } }
}
