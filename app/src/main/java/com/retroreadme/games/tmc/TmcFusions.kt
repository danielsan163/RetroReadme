package com.retroreadme.games.tmc

import com.retroreadme.games.tmc.KinColor.BLUE
import com.retroreadme.games.tmc.KinColor.GOLD
import com.retroreadme.games.tmc.KinColor.GREEN
import com.retroreadme.games.tmc.KinColor.RED

/**
 * All 100 Kinstone fusions, numbered and staged as in Zelda Wiki's list and checked against
 * Zelda Universe's fusion guide. The 18 "Random" ones turn up on whichever of the random fusers
 * the game picks; Hints explains them.
 */
object TmcFusions {

    private fun f(n: Int, color: KinColor, stage: Int, fuser: String, location: String, result: String) =
        Fusion("f%03d".format(n), n, color, stage, fuser, location, result)

    val all: List<Fusion> = listOf(
        f(1, BLUE, 1, "Hurdy-Gurdy Man", "Hyrule Town", "A tree opens in South Hyrule Field: Heart Piece"),
        f(2, RED, 1, "Mayor Hagen", "Mayor Hagen's House", "A pool drains at Lon Lon Ranch, down to a chest with a Big Wallet"),
        f(3, GREEN, 1, "Mountain Minish", "Melari's Mine, southeast dining hall", "A Golden Tektite appears on Mount Crenel"),
        f(4, RED, 1, "Strato", "Stranger's House", "A portal opens in South Hyrule Field up to the Home of the Wind Tribe (save Gregal there: see Hints)"),
        f(5, BLUE, 1, "Random", "Anyone on the random list", "A tree opens in North Hyrule Field: Fairy Fountain"),
        f(6, GREEN, 1, "Random", "Anyone on the random list", "A pool drains in Hyrule Castle Garden: Fairy Fountain"),
        f(7, GREEN, 1, "Random", "Anyone on the random list", "A pool drains in South Hyrule Field: cave with 75 Rupees"),
        f(8, GREEN, 1, "Random", "Anyone on the random list", "A Golden Octorok appears in Minish Woods"),
        f(9, GREEN, 1, "Random", "Anyone on the random list", "A Golden Rope appears in the Eastern Hills"),
        f(10, GREEN, 1, "Random", "Anyone on the random list", "A Golden Rope appears in Hyrule Castle Garden"),
        f(11, GREEN, 1, "Random", "Anyone on the random list", "A crack opens in Minish Woods near the Minish Village: chest with a blue Kinstone"),
        f(12, GREEN, 1, "Random", "Anyone on the random list", "Chest on the Minish path at Funday School: red Kinstone"),
        f(13, GREEN, 1, "Random", "Anyone on the random list", "Chest on the Minish path at Lon Lon Ranch: red Kinstone"),
        f(14, GREEN, 1, "Random", "Anyone on the random list", "Chest on the Minish path to the Minish Village: 200 Rupees"),
        f(15, GREEN, 1, "Random", "Anyone on the random list", "Chest in Minish Woods: red Kinstone"),
        f(16, GREEN, 1, "Random", "Anyone on the random list", "Chest in Minish Woods: red Kinstone"),
        f(17, GREEN, 1, "Random", "Anyone on the random list", "Chest in Minish Woods: 200 Rupees"),
        f(18, GREEN, 1, "Random", "Anyone on the random list", "Chest in Minish Woods: blue Kinstone"),
        f(19, GREEN, 1, "Random", "Anyone on the random list", "Chest in Lon Lon Ranch: 200 Rupees"),
        f(20, GREEN, 1, "Random", "Anyone on the random list", "Chest in North Hyrule Field: 200 Mysterious Shells"),
        f(21, GREEN, 1, "Random", "Anyone on the random list", "Chest in South Hyrule Field: 200 Mysterious Shells"),
        f(22, RED, 1, "Random", "Anyone on the random list", "A fountain drains in Hyrule Castle Garden: Heart Piece"),
        f(23, GREEN, 2, "Ankle", "Lon Lon Ranch", "A tree opens in North Hyrule Field: red Kinstone, and one of the four switches for the Magical Boomerang"),
        f(24, BLUE, 2, "Business Scrub", "Castor Wilds", "A tree opens in Minish Woods with a Business Scrub who sells Kinstones"),
        f(25, BLUE, 2, "Candy", "Happy Hearth Inn", "A shoal rises in Trilby Highlands to a secret cave (needs the Flippers)"),
        f(26, GREEN, 2, "David Jr.", "Lake Hylia", "A tree opens in North Hyrule Field: 200 Mysterious Shells, and one of the four switches for the Magical Boomerang"),
        f(27, BLUE, 2, "Eenie", "Eastern Hills", "The Goron at Lon Lon Ranch opens the Goron Cave, or another Goron joins the dig"),
        f(28, GREEN, 2, "Fifi", "Stockwell's House", "Chest on the Minish path to Mayor Hagen's cabin: blue Kinstone"),
        f(29, GREEN, 2, "Forest Minish", "Mount Crenel's Base", "Chest in Mount Crenel's Base: 200 Rupees"),
        f(30, BLUE, 2, "Goron Digger", "Lon Lon Ranch", "The Goron Merchant appears in Hyrule Town"),
        f(31, GREEN, 2, "Knuckle", "Trilby Highlands, above the cave you dig into", "A tree opens in North Hyrule Field: red Kinstone, and one of the four switches for the Magical Boomerang"),
        f(32, GREEN, 2, "Meenie", "Eastern Hills", "Chest on the Crenel Wall: blue Kinstone"),
        f(33, RED, 2, "Melari", "Melari's Mine", "A beanstalk grows: a Heart Piece and 160 Rupees"),
        f(34, GREEN, 2, "Mountain Minish", "Melari's Mine", "Chest on the rainy Minish path on Mount Crenel: blue Kinstone"),
        f(35, GREEN, 2, "Mountain Minish", "Melari's Mine", "Chest in Minish Woods: blue Kinstone"),
        f(36, GREEN, 2, "Mountain Minish", "Melari's Mine", "Chest on the Minish path by Mount Crenel's hot spring: blue Kinstone"),
        f(37, GOLD, 2, "Mysterious Statue (left)", "Castor Wilds", "Cracks the boulder; all three open the way to the Wind Ruins"),
        f(38, GOLD, 2, "Mysterious Statue (center)", "Castor Wilds", "Cracks the boulder; all three open the way to the Wind Ruins"),
        f(39, GOLD, 2, "Mysterious Statue (right)", "Castor Wilds", "Cracks the boulder; all three open the way to the Wind Ruins"),
        f(40, BLUE, 2, "Mysterious Wall", "Eastern Hills", "The Goron at Lon Lon Ranch opens the Goron Cave, or another Goron joins the dig"),
        f(41, BLUE, 2, "Mysterious Wall", "Minish Woods", "The Goron at Lon Lon Ranch opens the Goron Cave, or another Goron joins the dig"),
        f(42, BLUE, 2, "Mysterious Wall", "Mount Crenel", "The Goron at Lon Lon Ranch opens the Goron Cave, or another Goron joins the dig"),
        f(43, BLUE, 2, "Mysterious Wall", "Trilby Highlands", "The Goron at Lon Lon Ranch opens the Goron Cave, or another Goron joins the dig"),
        f(44, RED, 2, "Percy", "Trilby Highlands", "A fallen tree rises in Western Wood: the way to Percy's house"),
        f(45, BLUE, 2, "Postman", "Hyrule Town", "Marcy starts selling the Swordsman Newsletter at the Post Office"),
        f(46, RED, 2, "Smith", "South Hyrule Field", "Chest in Eastern Hills: Empty Bottle"),
        f(47, GREEN, 2, "Smith", "South Hyrule Field", "Chest in Trilby Highlands: red Kinstone"),
        f(48, GREEN, 2, "Tingle", "South Hyrule Field", "A tree opens in North Hyrule Field: red Kinstone, and one of the four switches for the Magical Boomerang"),
        f(49, RED, 3, "Bremor", "Hyrule Town", "Mutoh builds a second house for Gorman to rent"),
        f(50, GREEN, 3, "Business Scrub", "Minish Woods", "A crack opens in Castor Wilds: chest with a red Kinstone"),
        f(51, GREEN, 3, "Business Scrub", "Minish Woods", "A Golden Octorok appears in Western Wood"),
        f(52, GREEN, 3, "David Jr.", "Lake Hylia", "Chest on the Minish path to Melari's Mine: 200 Mysterious Shells"),
        f(53, RED, 3, "Farore", "Hyrule Town", "Gorman arrives in Hyrule Town looking for tenants"),
        f(54, BLUE, 3, "Forest Minish", "Eastern Hills", "A beanstalk grows: Heart Piece, 200 Rupees and 200 Mysterious Shells"),
        f(55, GREEN, 3, "Forest Minish", "Hyrule Castle Garden", "A fallen tree rises in Western Wood: dig up 400 Rupees with the Mole Mitts"),
        f(56, BLUE, 3, "Forest Minish", "Lake Hylia", "A beanstalk grows: Heart Piece, 200 Rupees and 200 Mysterious Shells"),
        f(57, RED, 3, "Forest Minish", "Lake Hylia", "A tree opens in Western Wood: Heart Piece"),
        f(58, GREEN, 3, "Forest Minish (or Zill)", "Minish Village (or Hyrule Town)", "A lily pad appears in Castor Wilds"),
        f(59, GREEN, 3, "Forest Minish (or Zill)", "Minish Village (or Hyrule Town)", "A lily appears in Castor Wilds"),
        f(60, GREEN, 3, "Forest Minish (or Zill)", "Minish Village (or Hyrule Town)", "A lily appears in Castor Wilds"),
        f(61, GREEN, 3, "Forest Minish", "North Hyrule Field", "Chest in Wind Ruins: 200 Mysterious Shells"),
        f(62, RED, 3, "Forest Minish", "South Hyrule Field", "Syrup starts selling the Red Potion"),
        f(63, GREEN, 3, "Forest Minish", "Trilby Highlands", "A pool drains in Trilby Highlands: cave with 75 Rupees"),
        f(64, BLUE, 3, "Forest Minish", "Western Wood", "A beanstalk grows: 320 Rupees and a red Kinstone"),
        f(65, RED, 3, "Forest Minish", "Wind Ruins", "A beanstalk grows: Large Quiver at the top"),
        f(66, RED, 3, "Grayblade", "Mount Crenel", "A waterfall opens in Castor Wilds: Scarblade's dojo"),
        f(67, GREEN, 3, "Librari", "Hyrule Town", "A Golden Octorok appears at the Wind Ruins"),
        f(68, GREEN, 3, "Mama", "Hyrule Town", "A shoal rises in Lake Hylia to a secret cave"),
        f(69, RED, 3, "Tingle", "South Hyrule Field", "A Golden Tektite appears on Mount Crenel"),
        f(70, RED, 4, "Belari", "Minish Woods", "Chest in Wind Ruins: Big Bomb Bag"),
        f(71, RED, 4, "Gentari", "Minish Village", "Belari invents the Remote Bombs"),
        f(72, BLUE, 4, "Spekter", "Royal Valley", "A harder level in the Chest Mini-Game Shop"),
        f(73, BLUE, 4, "Spekter", "Royal Valley", "Spookter leaves town, opening Anju's henhouse"),
        f(74, GREEN, 5, "Flurris", "Home of the Wind Tribe", "A fallen tree rises in Western Wood: dig up 300 Rupees with the Mole Mitts"),
        f(75, GREEN, 5, "Flurris", "Home of the Wind Tribe", "A Golden Rope appears in Castor Wilds"),
        f(76, GREEN, 5, "Caprice", "Home of the Wind Tribe", "Chest in Veil Falls: blue Kinstone"),
        f(77, BLUE, 5, "Dampé", "Royal Valley", "A tombstone opens in the graveyard: Gina, and 100 Mysterious Shells"),
        f(78, GREEN, 5, "Dampé", "Royal Valley", "A crack opens in the Wind Ruins: chest with a red Kinstone"),
        f(79, RED, 5, "Din", "Hyrule Town", "A Joy Butterfly in the Wind Ruins: faster arrows"),
        f(80, RED, 5, "Farore", "Hyrule Town", "A Joy Butterfly in Castor Wilds: faster digging"),
        f(81, RED, 5, "Gale", "Cloud Tops", "A waterfall opens at Veil Falls: Heart Piece"),
        f(82, GREEN, 5, "Gina", "Royal Valley", "A fallen tree rises in Western Wood: 100 Mysterious Shells"),
        f(83, GREEN, 5, "Gina", "Royal Valley", "A waterfall opens in Hyrule Town: 200 Mysterious Shells"),
        f(84, RED, 5, "Grimblade", "Hyrule Castle Garden", "A waterfall opens at Veil Falls: Splitblade's dojo"),
        f(85, GREEN, 5, "Hailey", "Cloud Tops", "A Golden Tektite appears at Veil Falls"),
        f(86, GOLD, 5, "Mysterious Cloud (northwest)", "Cloud Tops", "Starts a pinwheel; all five call a tornado up to the Wind Tribe"),
        f(87, GOLD, 5, "Mysterious Cloud (center)", "Cloud Tops", "Starts a pinwheel; all five call a tornado up to the Wind Tribe"),
        f(88, GOLD, 5, "Mysterious Cloud (southwest)", "Cloud Tops", "Starts a pinwheel; all five call a tornado up to the Wind Tribe"),
        f(89, GOLD, 5, "Mysterious Cloud (southeast)", "Cloud Tops", "Starts a pinwheel; all five call a tornado up to the Wind Tribe"),
        f(90, GOLD, 5, "Mysterious Cloud (northeast)", "Cloud Tops", "Starts a pinwheel; all five call a tornado up to the Wind Tribe"),
        f(91, BLUE, 5, "Mysterious Wall", "Lake Hylia", "The Goron at Lon Lon Ranch opens the Goron Cave, or another Goron joins the dig"),
        f(92, RED, 5, "Nayru", "Hyrule Town", "A Joy Butterfly in the Royal Valley: faster swimming"),
        f(93, GREEN, 5, "Siroc", "Home of the Wind Tribe", "Chest in Royal Valley: red Kinstone"),
        f(94, GREEN, 5, "Siroc", "Home of the Wind Tribe", "Chest in Royal Valley: red Kinstone"),
        f(95, GOLD, 5, "Source of the Flow", "Veil Falls", "Opens the stone slab at the end of the bridge, into the caves up to Veil Springs"),
        f(96, GREEN, 5, "Tina", "Hyrule Town", "Chest in Trilby Highlands: red Kinstone"),
        f(97, RED, 5, "Waveblade", "Lake Hylia", "A waterfall opens in North Hyrule Field: Greatblade's dojo"),
        f(98, RED, 6, "Forest Minish", "Minish Village", "A shoal rises at Veil Falls to a cave with a Heart Piece"),
        f(99, RED, 6, "Forest Minish", "Minish Village", "A crack opens by Lake Hylia's Wind Crest down to Librari, who gives you a Heart Container"),
        f(100, RED, 6, "Goron Digger", "Goron Cave", "Biggoron awakens at Veil Springs"),
    )

    val byId: Map<String, Fusion> = all.associateBy { it.id }

    /** What each story stage covers, for headings. */
    val stages: List<String> = listOf(
        "Stage 1: from the start",
        "Stage 2: after the King is taken over (Western Wood)",
        "Stage 3: after the Fortress of Winds",
        "Stage 4: after the Temple of Droplets",
        "Stage 5: after the Royal Crypt",
        "Stage 6: after the Palace of Winds",
    )

    init {
        require(all.size == 100 && byId.size == 100) { "The Minish Cap has 100 fusions, not ${all.size}" }
    }
}
