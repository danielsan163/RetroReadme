package com.retroreadme.games.kdl3

import com.retroreadme.games.kdl3.Ability.BURNING
import com.retroreadme.games.kdl3.Ability.CLEAN
import com.retroreadme.games.kdl3.Ability.CUTTER
import com.retroreadme.games.kdl3.Ability.ICE
import com.retroreadme.games.kdl3.Ability.NEEDLE
import com.retroreadme.games.kdl3.Ability.PARASOL
import com.retroreadme.games.kdl3.Ability.SPARK
import com.retroreadme.games.kdl3.Ability.STONE
import com.retroreadme.games.kdl3.Friend.CHUCHU
import com.retroreadme.games.kdl3.Friend.COO
import com.retroreadme.games.kdl3.Friend.KINE
import com.retroreadme.games.kdl3.Friend.NAGO
import com.retroreadme.games.kdl3.Friend.PITCH
import com.retroreadme.games.kdl3.Friend.RICK
import com.retroreadme.games.kdl3.World.CLOUDY_PARK
import com.retroreadme.games.kdl3.World.GRASS_LAND
import com.retroreadme.games.kdl3.World.ICEBERG
import com.retroreadme.games.kdl3.World.RIPPLE_FIELD
import com.retroreadme.games.kdl3.World.SAND_CANYON

/**
 * All 30 Heart Stars. Each was checked against two Heart Star guides (WiKirby and
 * Flying Omelette's FAQ), with StrategyWiki as a third where they differed.
 */
object Kdl3Stages {

    val all: List<Stage> = listOf(

        // ---------------------------------------------------------------- Grass Land

        Stage(
            "gl1", GRASS_LAND, 1, "Tulip", "Don't touch the tulips",
            steps = listOf(
                "In the area with red tulips that have eyes (a tone plays as you arrive), don't touch a single one. Stepping on or hitting one flattens it and fails the task.",
                "Easiest as Kirby alone, flying over them. Nago's triple jump also clears them.",
                "Reach the end with every tulip intact.",
            ),
        ),
        Stage(
            "gl2", GRASS_LAND, 2, "Muchimuchi", "Cheer up the spring creature with ChuChu",
            friends = listOf(CHUCHU),
            steps = listOf(
                "Take ChuChu from the friend room near the start (Pitch is there too).",
                "Along the two-level walkway, break the lower group of Star Blocks on the big rock slab to reveal a door.",
                "Inside is Muchimuchi, a green spring creature. Drop any ability, then use ChuChu's normal attack so her tentacle touches it. It smiles when you've done it.",
            ),
        ),
        Stage(
            "gl3", GRASS_LAND, 3, "Pitcherman", "Sub-game: which Gordo was thrown?",
            steps = listOf(
                "After climbing the cliff in the third room, Pitcherman throws a Gordo between two screens. Pick the face you glimpsed.",
                "Three rounds, each faster. One wrong answer means replaying the stage for another try.",
            ),
        ),
        Stage(
            "gl4", GRASS_LAND, 4, "Chao & Goku", "Rescue Goku past the mid-boss Boboo",
            abilities = listOf(STONE),
            steps = listOf(
                "In the third room, get Stone from the Rocky on the ledge and break the Stone-symbol blocks in the bridge.",
                "Take the lower path to a door and beat the mid-boss Boboo (a big flame).",
                "Grab Goku the monkey in the next room. If you skipped the mid-boss, you'd be stuck on the upper ledge.",
                "Bring him to Chao at the end.",
            ),
        ),
        Stage(
            "gl5", GRASS_LAND, 5, "Mine", "Bring Kine to the end",
            friends = listOf(KINE),
            steps = listOf(
                "At the bridge of spinning logs, take the left of the two doors above it: Kine is inside (with Coo).",
                "Keep Kine all the way to the end, where Mine the sunfish is waiting.",
            ),
        ),
        Stage(
            "gl6", GRASS_LAND, 6, "Pierre", "Collect three shapes for the clown",
            steps = listOf(
                "In the large vertical section near the end (the hollow stump), collect a green triangle, a blue rectangle and an orange circle.",
                "The circle is under blocks, so bring an ability that breaks blocks below you.",
                "A tone plays once you have all three. The clown at the end juggles them.",
            ),
        ),

        // ---------------------------------------------------------------- Ripple Field

        Stage(
            "rf1", RIPPLE_FIELD, 1, "Kamuribana", "Water six sprouts with Pitch + Clean",
            friends = listOf(PITCH), abilities = listOf(CLEAN),
            steps = listOf(
                "Get Clean from a Broom Hatter near the start.",
                "Take Pitch in the friend room (Nago is there too).",
                "Behind three underwater doors are six sprouts (one, then two, then three). Use Pitch + Clean to throw water balls at each until it blooms, and don't damage them afterwards.",
                "A tone plays after the last one.",
            ),
        ),
        Stage(
            "rf2", RIPPLE_FIELD, 2, "Bakasa", "Strike the parasol pose with Kine",
            friends = listOf(KINE), abilities = listOf(PARASOL),
            steps = listOf(
                "Get Parasol from a Sasuke early on.",
                "Take Kine in the friend room (ChuChu is there too).",
                "After the water tunnels, go through the door on the small floating island. Bakasa is inside.",
                "Use Parasol with Kine next to it and hold the pose until it bursts out laughing.",
            ),
            notes = listOf("WiKirby says Rick with Parasol works too."),
        ),
        Stage(
            "rf3", RIPPLE_FIELD, 3, "Elieel", "Sub-game: where's the eel?",
            steps = listOf(
                "Halfway through, Elieel and four Gordos pop in and out of five pots. When they stop, pick the pot Elieel was last in.",
                "Three rounds, each faster. One wrong answer means replaying the stage.",
            ),
        ),
        Stage(
            "rf4", RIPPLE_FIELD, 4, "Kogamugaeru & Gamugaeru", "Rescue the baby frog past Captain Stitch",
            abilities = listOf(NEEDLE),
            steps = listOf(
                "Near the end, the vertical room starts scrolling up quickly. Near the top there's an invisible door on the left (the obvious one is on the right). Get into it before the screen crushes you.",
                "Beat the mid-boss Captain Stitch (a big spiked ball) and take its Needle ability.",
                "Use Needle to break the blocks and pick up the baby frog.",
                "Bring it to its parent at the end.",
            ),
            notes = listOf("Kine with Spark lights up hidden doors like this one."),
        ),
        Stage(
            "rf5", RIPPLE_FIELD, 5, "Pitch Mama", "Find Pitch and bring him to his mother",
            friends = listOf(KINE, PITCH), abilities = listOf(BURNING, STONE),
            steps = listOf(
                "Take Kine. Leaving his room, swim against the current and take the right door (the left one loops back).",
                "Enter the small room with a dragon and inhale it for Burning.",
                "Further up, burn every flame-symbol block in the wall, then leave Kine there: the passage ahead is too small for him.",
                "Go on alone just far enough to eat a Rocky for Stone, come back, break the remaining blocks, and take Kine again.",
                "In the section of spinning currents, swim against the current down the left passage of the second whirl to a door. Pitch is inside.",
                "Swap to Pitch and take him to the end.",
            ),
            notes = listOf("You can't bring Pitch in from another stage: the route needs Kine first."),
        ),
        Stage(
            "rf6", RIPPLE_FIELD, 6, "HB-002", "Carve the Star Blocks into its shape",
            steps = listOf(
                "Halfway through there are three underwater doors. One room has a shape of unbreakable blocks (a diamond, like HB-002). The other two lead to the same 5×5 grid of Star Blocks.",
                "Break only the corners so the grid matches the shape. Leave and re-enter to reset if you break a wrong one.",
                "A tone confirms it.",
            ),
        ),

        // ---------------------------------------------------------------- Sand Canyon

        Stage(
            "sc1", SAND_CANYON, 1, "Geromazudake", "Flatten the tulips, spare the mushrooms",
            steps = listOf(
                "In the first area, flatten every red tulip (step on or hit them) but leave every brown mushroom alone.",
                "A tone plays when all the tulips are down with no mushroom touched.",
            ),
        ),
        Stage(
            "sc2", SAND_CANYON, 2, "Oba-chan", "Sweep the dusty rooms with Clean",
            abilities = listOf(CLEAN),
            steps = listOf(
                "Get Clean from a Keke near the start. A line of Bukisets before the building also has one.",
                "Oba-chan is sweeping inside the sandstone building. Go into the other two rooms and sweep away every bit of the grey dust.",
                "A tone plays when both rooms are clean.",
            ),
        ),
        Stage(
            "sc3", SAND_CANYON, 3, "Caramelo", "Sub-game: how many of the same face?",
            steps = listOf(
                "Caramelo blows a bubble and a crowd of Gordos briefly show faces. Say how many had the face it asks for.",
                "Three rounds; the last is very fast. One wrong answer means replaying the stage.",
            ),
        ),
        Stage(
            "sc4", SAND_CANYON, 4, "Donbe & Hikari", "Find Donbe past the mid-boss Haboki",
            confirm = true,
            steps = listOf(
                "In the underwater auto-scrolling tunnels, take the lower path first.",
                "The guides disagree on the next two forks: one says up, then middle; the other says middle, then middle.",
                "The right route ends at the mid-boss Haboki (a giant broom). Beat it and take the door.",
                "Pick up Donbe (the blue-haired boy) and bring him to his sister Hikari at the end. A wrong route drops you above or below him with no way across.",
            ),
        ),
        Stage(
            "sc5", SAND_CANYON, 5, "Nyupun", "Bring ChuChu to the end",
            friends = listOf(CHUCHU),
            steps = listOf(
                "Partway through, go through the door to the friend room with ChuChu.",
                "Use her ceiling cling to cross the tricky parts, and keep her to the end.",
            ),
        ),
        Stage(
            "sc6", SAND_CANYON, 6, "R.O.B. & Professor Hector", "Collect all five R.O.B. parts",
            friends = listOf(KINE, COO), abilities = listOf(SPARK, PARASOL, STONE),
            steps = listOf(
                "Inside the pyramid, the green Zebon in the hub room spits you in the direction it faces. Have it fire you down to break into a room with one enemy for each ability; the friend rooms are below that (Coo, Kine, Rick on the left; ChuChu, Nago, Pitch on the right).",
                "Base (top-left): with Kine + Spark, a bulb briefly shows an O on one door in each room. Always take the O.",
                "Left arm (bottom-right): with Kine and Parasol, run off the ledge at the last moment and float across, then use Kine against the current.",
                "Head (straight up): climb the auto-scrolling tower and grab it. On the way down, you can only drop through the blue floors. It's easier without a friend.",
                "Torso (top-right): in each room take the left or right door, never the middle one, using that room's enemies' abilities to break through.",
                "Right arm (bottom-left): with Coo + Stone, break the two Stone blocks in the middle wall (cancel Stone quickly, there's a pit below), clear a gap big enough for Coo, then fly against the wind.",
                "A tone plays with the last part. Have the Zebon fire you right to reach the exit.",
            ),
        ),

        // ---------------------------------------------------------------- Cloudy Park

        Stage(
            "cp1", CLOUDY_PARK, 1, "Hibanamodoki", "Dust the dirty flowers",
            friends = listOf(COO, RICK), abilities = listOf(CLEAN),
            steps = listOf(
                "Get Clean from a Keke at the start, and take Coo or Rick (Clean becomes a feather duster with either).",
                "In the last area, dust each dirty flower once. Don't dust twice or hold the button, and don't land on them: that flattens a flower and fails the task.",
                "Coo stops hovering while attacking, so be careful near pits. A tone plays after the last flower.",
            ),
        ),
        Stage(
            "cp2", CLOUDY_PARK, 2, "Piyo & Keko", "Pop the chick's balloon with Needle",
            abilities = listOf(NEEDLE),
            steps = listOf(
                "About three-quarters of the way through, inhale a Togezo for Needle.",
                "Right before the exit, a chick hangs from a red balloon. Pop it with Needle.",
            ),
        ),
        Stage(
            "cp3", CLOUDY_PARK, 3, "Tama-san", "Sub-game: how many of the same color?",
            steps = listOf(
                "Gordos in four colors pop up from behind a screen. Say how many of the color it asks for.",
                "Three rounds, each faster. One wrong answer means replaying the stage.",
            ),
        ),
        Stage(
            "cp4", CLOUDY_PARK, 4, "Mikarin & Kagami Mocchi", "Reach Mikarin against the wind",
            friends = listOf(COO),
            steps = listOf(
                "Take Coo in the friend room.",
                "You have to fight the mid-boss Jumper Shoot (a giant umbrella). It can be easier to drop Coo for the fight; take him back afterwards.",
                "In the next room, fly Coo against the wind to reach Mikarin, the small orange.",
                "Bring it to Kagami Mocchi at the end.",
            ),
        ),
        Stage(
            "cp5", CLOUDY_PARK, 5, "Pick", "Bring Rick to the end",
            friends = listOf(RICK),
            steps = listOf(
                "Take Rick from the friend room you pass along the way.",
                "Mind the jumps across the pillars; Rick can wall-kick if you come up short.",
            ),
        ),
        Stage(
            "cp6", CLOUDY_PARK, 6, "HB-007", "Carve the Star Blocks, the right way up",
            steps = listOf(
                "In the long hallway with several doors, one room has HB-007 (a living puzzle piece) and another has a 5×5 grid of Star Blocks.",
                "Break only the blocks needed to match its shape. It's sitting upside down (look at its face), so carve the shape the right way up.",
                "A tone confirms it.",
            ),
        ),

        // ---------------------------------------------------------------- Iceberg

        Stage(
            "ib1", ICEBERG, 1, "Kogoesou", "Thaw the frozen daisies",
            abilities = listOf(BURNING),
            steps = listOf(
                "Get Burning (there's plenty of it in this stage).",
                "On the snowy hillside partway through, thaw every daisy frozen in ice, and don't trample them afterwards.",
                "A tone plays after the last one.",
            ),
        ),
        Stage(
            "ib2", ICEBERG, 2, "Samus", "Freeze all six Metroids",
            abilities = listOf(ICE),
            steps = listOf(
                "Right at the start, inhale a Chilly for Ice. Be quick, an avalanche is chasing you.",
                "In the lava caves, three side rooms hold six Metroids. Freeze each with Ice, then knock it away.",
                "A tone plays after the last one. Samus takes her helmet off when you've done it.",
            ),
        ),
        Stage(
            "ib3", ICEBERG, 3, "Chef Kawasaki", "Sub-game: what was that sound?",
            steps = listOf(
                "Five Gordos each make a sound, then Kawasaki bangs his pan on a sixth. Pick the Gordo that made the same sound.",
                "Three rounds. One wrong answer means replaying the stage.",
            ),
        ),
        Stage(
            "ib4", ICEBERG, 4, "Name", "Recover the snail's shell",
            friends = listOf(COO, CHUCHU), abilities = listOf(BURNING),
            steps = listOf(
                "In the tall room full of dragons, eat one for Burning and melt the ice at the lower left to reveal a door. Coo is inside.",
                "Later, ignore the door with ice behind it: use Coo + Burning to cut down through the ice to a door with a cattail on each side.",
                "Cut down to the bottom of the next room to find ChuChu. Swap to her and use ChuChu + Burning to cut up through the next column of ice.",
                "Go back for Coo and cut down the next column, then swap to ChuChu again to cut up the last one. Take the door at the top.",
                "Falling down the shaft, look for the shell in a nook on the right wall. Fly back up if you drop past it.",
            ),
        ),
        Stage(
            "ib5", ICEBERG, 5, "Shiro", "Bring Nago in from another stage",
            friends = listOf(NAGO),
            steps = listOf(
                "Nago doesn't appear in this stage: you have to arrive with him.",
                "One recommended setup: go back to Grass Land 1 for Nago, full health and Burning, then return.",
                "You can't lose him, so the whole stage has to be done in one life.",
            ),
        ),
        Stage(
            "ib6", ICEBERG, 6, "Angel", "Collect eight feathers with eight abilities",
            abilities = listOf(SPARK, STONE, PARASOL, ICE, CUTTER, CLEAN, BURNING, NEEDLE),
            steps = listOf(
                "The tower alternates between a room that gives an ability and a room with a feather behind blocks only that ability breaks.",
                "In order: Spark (Sparky room), Stone (mid-boss Blocky), Parasol (Jumper Shoot), Ice (Yuki), Cutter (Sir Kibble room), Clean (Haboki), Burning (Boboo), Needle (Captain Stitch).",
                "Drop your current ability before each mid-boss so you don't grab the wrong star afterwards.",
                "The angel at the end gets her wings back.",
            ),
        ),
    )

    val byId: Map<String, Stage> = all.associateBy { it.id }

    val extras: List<Extra> = listOf(
        Extra(
            "zero", "Hyper Zone: beat Zero", "True final boss",
            listOf(
                "Have all 30 Heart Stars before beating King Dedede in Iceberg. The Hyper Zone then opens.",
                "The Heart Stars form the Love-Love Stick. Beat Dark Matter, then Zero right after (see Bosses).",
            ),
        ),
        Extra(
            "mg5", "MG-5", "All five sub-games in a row",
            listOf(
                "Unlocks on the file select screen once the story is done with every Heart Star.",
                "Plays the five Stage 3 sub-games back to back. You need a perfect score: every guess right.",
            ),
        ),
        Extra(
            "boss_butch", "Boss Butch", "Boss rush",
            listOf(
                "Unlocks after beating Zero.",
                "Fight the bosses back to back on one life, with no healing in between.",
            ),
        ),
        Extra(
            "jumping", "Jumping", "Score 10",
            listOf(
                "Unlocks after a perfect MG-5 (some players say it only showed up once Boss Butch was cleared too).",
                "Land on the safe spaces and avoid the Ticks on the others. You have almost no health, so landing on a Tick ends the run.",
                "Make 10 successful jumps. You can play as Kirby alone or with a friend; each times the jump slightly differently.",
            ),
        ),
    )

    val extrasById: Map<String, Extra> = extras.associateBy { it.id }
}
