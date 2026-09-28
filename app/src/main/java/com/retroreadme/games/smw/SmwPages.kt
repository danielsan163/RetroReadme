package com.retroreadme.games.smw

import com.retroreadme.core.Section
import com.retroreadme.core.Tone

object SmwPages {

    // ---------------------------------------------------------------- Switch Palaces

    val palaces: List<Palace> = listOf(
        Palace(
            "p_yellow", "Yellow Switch Palace", SmwColors.PalaceYellow, "ysp",
            openedBy = "Clearing Yoshi's Island 1.",
            sections = listOf(
                Section("What the yellow blocks help with", listOf(
                    "Donut Plains 1: a pipe you can go up partway through, into a 1-Up room.",
                    "Star World 5: part of the ! block trail to the secret exit (all four colors are needed to walk it).",
                )),
            ),
        ),
        Palace(
            "p_green", "Green Switch Palace", SmwColors.PalaceGreen, "gsp",
            openedBy = "The secret exit of Donut Plains 2.",
            sections = listOf(
                Section("What the green blocks help with", listOf(
                    "Donut Plains 1 secret exit: a column of green blocks near the goal climbs up to the key without a Cape.",
                    "Star World 4 secret exit: the green blocks under the pipe section start the bridge to the key (with red).",
                    "Star World 5 secret exit: part of the ! block trail.",
                )),
            ),
        ),
        Palace(
            "p_red", "Red Switch Palace", SmwColors.PalaceRed, "rsp",
            openedBy = "The secret exit of Vanilla Dome 2.",
            sections = listOf(
                Section("What the red blocks help with", listOf(
                    "Vanilla Dome 1 secret exit: the red block staircase to the vine. Treat this palace as required for it.",
                    "Star World 4 secret exit: the red blocks finish the bridge to the key.",
                    "Star World 5 secret exit: part of the ! block trail.",
                    "Chocolate Island 2: with Red or Blue missing, the last gap in the four-Dragon-Coin area needs the P-Switch.",
                )),
            ),
        ),
        Palace(
            "p_blue", "Blue Switch Palace", SmwColors.PalaceBlue, "bsp",
            openedBy = "The secret exit of Forest of Illusion 2.",
            sections = listOf(
                Section("What the blue blocks help with", listOf(
                    "Vanilla Secret 1 secret exit: a springboard set on the blue blocks reaches the secret pipe without a Cape.",
                    "Star World 5 secret exit: the last stretch of the ! block trail, and the hardest to do without.",
                    "Chocolate Island 2: the final Dragon Coin is out of reach without blue blocks or a Cape.",
                )),
            ),
        ),
    )

    // ---------------------------------------------------------------- Yoshi & Capes

    val yoshiAndCapes: List<Page> = listOf(
        Page(
            "yoshi", "Getting Yoshi", "Green Yoshi hatches from eggs in ? blocks",
            listOf(
                Section(null, listOf(
                    "Yoshi eggs sit in ? blocks all over the game. If you already have Yoshi, the block gives a 1-Up instead.",
                    "Reliable eggs: Donut Plains 1 (the ledge after the coin-flight room), Chocolate Island 2, the vine block at the start of Valley of Bowser 4, the first ? block on the ground in Groovy, and the Top Secret Area.",
                    "Yoshi waits outside Ghost Houses, fortresses and castles. The Sunken Ghost Ship is the one exception.",
                )),
                Section("Shells in Yoshi's mouth", listOf(
                    "Blue shell: Yoshi flies. Red shell: spits three fireballs. Yellow shell: stomps make dust clouds that defeat enemies. Green shell: nothing special.",
                    "A Blue Yoshi flies with any shell in his mouth.",
                )),
            ),
        ),
        Page(
            "colors", "Colored Yoshis", "Baby Yoshis in Star World",
            listOf(
                Section(null, listOf(
                    "Blue: an egg in Star World 2.",
                    "Red: an egg at the start of Star World 4 (there's also one low down in Star World 1).",
                    "Yellow: in Star World 3 and Star World 5.",
                    "Feed a baby five enemies or objects, or a single power-up, and it grows up.",
                    "You can pick a baby up and carry it. A common plan: grow Blue Yoshi in Star World 2, leave through its normal exit so he stays with you, then take him to Star World 4 and 5.",
                )),
                Section("Blue Yoshi without Star World", listOf(
                    "In the SNES version, touching Yoshi's wings (for example the wings block in Cheese Bridge Area) turns Yoshi blue until you lose him.",
                )),
            ),
        ),
        Page(
            "capes", "Cape Feathers", "Where to grab or refill one",
            listOf(
                Section(null, listOf(
                    "Super Koopas with a flashing cape drop a Feather when stomped. The first is at the very start of Donut Plains 1.",
                    "Donut Plains 1 also has a Feather between two gray blocks in the underground coin room.",
                    "Vanilla Ghost House: the ? block in front of the Big Boo.",
                    "Forest Ghost House: one of the ? blocks in the large hall.",
                    "Chocolate Island 2: the 21+ coin area starts with a Feather.",
                    "Star World 4: the ? block partway through, if you're big.",
                    "Mondo: a ? block early on, if you're powered up.",
                    "Top Secret Area: its blocks restock power-ups every visit.",
                )),
            ),
        ),
        Page(
            "needs", "Exits that need them", "Plan which power-up to bring",
            listOf(
                Section("Cape", listOf(
                    "Donut Ghost House, Cheese Bridge Area, Chocolate Island 3, Vanilla Secret 1 (or blue blocks).",
                    "Cape is one option for Donut Plains 2, Donut Secret House, Star World 3, 4 and 5.",
                )),
                Section("Yoshi", listOf(
                    "Valley of Bowser 4: required. Get the egg at the start and keep him to the end.",
                    "Donut Plains 1: a Yoshi jump from the pipe is the easy way up to the key (a Cape or green blocks also work).",
                    "Blue Yoshi is the no-palace route for Star World 4 and 5.",
                )),
                Section("Big Mario", listOf(
                    "Forest of Illusion 3 and Star World 1: you have to break turn blocks with a spin jump.",
                )),
                Section("Small Mario", listOf(
                    "Valley Ghost House secret: easier small, to fit through the gap by the key.",
                )),
            ),
        ),
    )

    // ---------------------------------------------------------------- Hints

    val hints: List<Page> = listOf(
        Page(
            "count", "The 96 count", "72 normal exits and 24 secret exits",
            listOf(
                Section(null, listOf(
                    "Red dots on the map mark levels with a secret exit. Ghost Houses have no dot, but four of them hide secrets: Donut, Donut Secret, Forest and Valley.",
                    "No castle, fortress, Yoshi's Island level or Special Zone level has a secret exit.",
                    "Star World normal exits count even though they only lead back to the warp.",
                    "Forest Ghost House's secret exit loops back to Forest of Illusion 1, and Forest of Illusion 4's normal exit leads to a path you've already opened. Both still count.",
                    "Soda Lake is easy to miss: the path it opens is invisible.",
                    "Bowser's Castle doesn't count. Clearing all 96 puts a star on your save file.",
                )),
            ),
        ),
        Page(
            "starroad", "Star Road warps", "What opens each Star World level",
            listOf(
                Section(null, listOf(
                    "Star World 1: Donut Secret House secret exit (beat the Big Boo).",
                    "Star World 2: Vanilla Secret 1 secret exit.",
                    "Star World 3: Soda Lake (from Cheese Bridge Area's secret exit).",
                    "Star World 4: Forest Fortress (via Forest of Illusion 4's secret exit and Forest Secret Area).",
                    "Star World 5: Valley of Bowser 4 secret exit, which also opens a warp to Bowser's Front Door.",
                    "Each Star World secret exit also opens the next Star World level. Star World 5's secret exit opens the Special Zone.",
                ), numbered = false),
            ),
        ),
        Page(
            "special", "Special Zone", "Eight levels, no secret exits",
            listOf(
                Section(null, listOf(
                    "Gnarly, Tubular, Way Cool, Awesome, Groovy, Mondo, Outrageous, Funky, in that order.",
                    "Each has a single exit, and all eight count toward the 96.",
                    "Tips for the worst of them are on each level's page in Worlds.",
                )),
                Section("After Funky", listOf(
                    "Dinosaur Land switches to autumn colors, Koopas wear Mario masks, Piranha Plants become pumpkins, and Bullet Bills become Pidgit Bills.",
                    "It's cosmetic, but if you'd rather keep summer, leave Funky for last.",
                )),
            ),
        ),
        Page(
            "topsecret", "Top Secret Area", "Not a level, no checkbox",
            listOf(
                Section(null, listOf(
                    "Opened by Donut Ghost House's secret exit (Cape needed).",
                    "No enemies and no timer. Its blocks give power-ups and a Yoshi, and they restock every time you go in.",
                    "Handy for grabbing a Cape before Cape-only exits.",
                )),
            ),
        ),
        Page(
            "ci2", "Chocolate Island 2", "Coins and time pick the route",
            listOf(
                Section("First area: coins", listOf(
                    "8 or fewer coins, 9 to 20 coins, or 21 or more coins each send you to a different second area.",
                    "21+ needs Fire Mario or Yoshi to turn Dino-Torches into coins, plus fast hits on the multi-coin block.",
                )),
                Section("Second area: timer", listOf(
                    "250 or more on the timer at the pipe: the secret exit area.",
                    "235 to 249, or 234 and below: two different areas leading to the normal goal.",
                ), tone = Tone.SECRET),
                Section("Last area: Dragon Coins", listOf(
                    "3 or fewer Dragon Coins or all 4 pick the final area. Both end at a Giant Gate.",
                )),
            ),
        ),
        Page(
            "saving", "Saving", "When the game offers to save",
            listOf(
                Section(null, listOf(
                    "The game offers to save after you clear a Ghost House, fortress, castle or Switch Palace.",
                    "Exits you clear after your last save are lost if you quit, so keep an eye on when the last save prompt was.",
                )),
            ),
        ),
    )
}
