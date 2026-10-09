package com.retroreadme.games.tmc

import com.retroreadme.games.tmc.UpgradeKind.BOMBS
import com.retroreadme.games.tmc.UpgradeKind.BOTTLE
import com.retroreadme.games.tmc.UpgradeKind.ITEM
import com.retroreadme.games.tmc.UpgradeKind.QUIVER
import com.retroreadme.games.tmc.UpgradeKind.WALLET

/**
 * The walkthrough, all 44 Heart Pieces and the bottles and upgrades. Checked against Zelda
 * Universe's guides and Zelda Wiki; details only one of them gives are marked.
 */
object TmcData {

    private var stepCount = 0
    private fun s(chapter: String, text: String) = Step("w%02d".format(++stepCount), chapter, text)

    /** Short "where next" pointers, in story order. Dungeons get one line: what's inside. */
    val steps: List<Step> = listOf(
        s("Picori Festival", "Follow Zelda around the festival, then take the Smith's Sword up to the castle."),
        s("Picori Festival", "After the ceremony the King sends you to find the Minish. Head south out of town and east over the bridge into Minish Woods; Deepwood Shrine is marked on your map."),

        s("Minish Woods", "Near the white shrine, save the talking cap from the Octoroks. That's Ezlo; shrink on the old stump with him."),
        s("Minish Woods", "In the Minish Village only Festari understands you. Get the Jabber Nut from the barrel house to the south, then talk to Elder Gentari (yellow roof)."),
        s("Minish Woods", "Deepwood Shrine is the white building north of the village. The Gust Jar is inside, and the boss guards the Earth Element."),

        s("Mount Crenel", "Back to Gentari: Melari on Mount Crenel can fix the blade. Leave by the Elder's back door and see Belari next to it for Bombs."),
        s("Mount Crenel", "In Hyrule Town, Swiftblade (log cabin, southwest) teaches the Spin Attack. Show it to the guard blocking the way to Trilby Highlands."),
        s("Mount Crenel", "To climb Mount Crenel you need a bottle: a Business Scrub in a cave down a ladder in southern Trilby Highlands sells one for 20 rupees."),
        s("Mount Crenel", "Fill it at the green hot spring by Mount Crenel's Base and water the bean planted there to grow a vine up."),
        s("Mount Crenel", "Partway up, bomb into the cave with another Business Scrub: buy the Grip Ring to climb the rough walls."),
        s("Mount Crenel", "Near the top, shrink and find Melari at the bottom of Melari's Mines. The sword will take a while."),
        s("Mount Crenel", "Meanwhile the Cave of Flames is up the stairs outside the mine. The Cane of Pacci is inside, and Gleerok guards the Fire Element."),

        s("The Pegasus Boots", "Back to Melari for the White Sword, then take it to the Elemental Sanctuary hidden in Hyrule Castle."),
        s("The Pegasus Boots", "Charging the White Sword on the glowing pads splits you in two: that opens the cave in southern Trilby Highlands and the way to the Western Wood."),
        s("The Pegasus Boots", "Castor Wilds' swamp needs the Pegasus Boots, and Rem the shoemaker in Hyrule Town is asleep. Syrup sells a Wake-Up Mushroom at her hut in the northern Minish Woods (through Lon Lon Ranch)."),
        s("The Pegasus Boots", "Wake Rem with it and he finishes the Pegasus Boots."),

        s("Castor Wilds", "Dash over Castor Wilds' muck. Shrink and head into the northwest for the Bow."),
        s("Castor Wilds", "Three Mysterious Statues in the south want gold Kinstones. The three pieces are around the swamp; arrows in the eye wake the one-eyed statues."),
        s("Castor Wilds", "With all three fused the boulder breaks open the way to the Wind Ruins and, at their end, the Fortress of Winds."),
        s("Castor Wilds", "Fortress of Winds: the Mole Mitts are inside and Mazaal is the boss. Afterwards you get the Ocarina of Wind."),

        s("Lake Hylia", "The Ocarina flies you to any Wind Crest you've uncovered. At Lake Hylia, you learn the Temple of Droplets' way in is known to Librari, in Hyrule Town's library."),
        s("Lake Hylia", "Librari's three books are checked out. The librarians tell you who has each one."),
        s("Lake Hylia", "A Hyrulean Bestiary is in a blue-roofed house: get in Minish-sized from the girl's house next door and push it off the shelf."),
        s("Lake Hylia", "Legend of the Picori is at the scholar Dr. Left's. To move his bookshelves you need the Power Bracelets, found along a Minish path through the town fountain."),
        s("Lake Hylia", "A History of Masks: Mayor Hagen left it at his cabin by Lake Hylia. Get in along the Minish path."),
        s("Lake Hylia", "Return all three, shrink on the library's upper floor and walk into the books to Librari. His trial ends with the Flippers."),
        s("Lake Hylia", "Swim to the Temple of Droplets in Lake Hylia. The Flame Lantern is inside, and the Big Octorok has the Water Element."),

        s("Royal Valley", "King Gustaf's ghost marks a spot on your map. First take the Water Element to the Sanctuary."),
        s("Royal Valley", "The Royal Valley is north of Hyrule Castle; have bombs and the Lantern ready."),
        s("Royal Valley", "Dampé the gravedigger gives you the Graveyard Key, and a Takkuri steals it: knock it out of the tree."),
        s("Royal Valley", "In the Royal Crypt, Gustaf gives you a gold Kinstone."),

        s("Cloud Tops", "Before going up: save Gregal, or you lose the Light Arrows for good (see Hints)."),
        s("Cloud Tops", "Veil Falls is through Lon Lon Ranch. Fuse Gustaf's gold piece with the Source of the Flow to open the caves up to Veil Springs."),
        s("Cloud Tops", "A whirlwind lifts you to the Cloud Tops. Fuse with all five Mysterious Clouds; the gold pieces come from chests up there."),
        s("Cloud Tops", "The tornado takes you to the Wind Tribe's tower, with the Palace of Winds at the top. Roc's Cape is inside, and the Gyorg Pair guards the Wind Element."),

        s("Dark Hyrule Castle", "Take the Wind Element to the Sanctuary for the Four Sword."),
        s("Dark Hyrule Castle", "Vaati darkens Hyrule Castle. Climb it; at the end you race three bell tolls to reach Zelda, then face Vaati."),
    )

    val chapters: List<String> = steps.map { it.chapter }.distinct()

    private fun h(n: Int, area: String, needs: List<String>, how: String) = Heart("h%02d".format(n), area, needs, how)

    private const val BOMBS_ = "Bombs"
    private const val CANE = "Cane of Pacci"
    private const val BOOTS = "Pegasus Boots"
    private const val FLIPPERS = "Flippers"
    private const val CAPE = "Roc's Cape"
    private const val MITTS = "Mole Mitts"
    private const val SPLIT = "Split (charged sword)"
    private const val FUSION = "Kinstone fusion"

    /** All 44 Heart Pieces, grouped by area. Ids follow Nintendo's guide numbering (Zelda Wiki's list). */
    val hearts: List<Heart> = listOf(
        h(1, "Minish Woods", emptyList(), "Just south of Deepwood Shrine. You can see it on the way in; it's only reachable at full size."),
        h(11, "Minish Woods", listOf(CANE), "The northwest woods, reached through Lon Lon Ranch (where Syrup's hut is). Follow the path to its southwest end, by a pool."),
        h(25, "Minish Woods", listOf(FLIPPERS), "Shrink at the Minish Portal west of the Minish Village and go northwest to three tiny caves. The leftmost one."),
        h(2, "Minish Village", emptyList(), "At the end of a pier in the village."),
        h(3, "Deepwood Shrine", listOf("Gust Jar"), "In the room where you get the Gust Jar, suck the web off the wall at the bottom right."),
        h(4, "Deepwood Shrine", listOf("Gust Jar"), "Clear the dust north of the barrel room to uncover the blue portal. Come back through the red portal once you've found it."),
        h(5, "Mount Crenel", listOf(BOMBS_), "From the northwest corner of Mount Crenel's Base, head north and bomb the cracked wall."),
        h(6, "Mount Crenel", listOf(BOMBS_), "East of the bottom of the Crenel Wall: bomb between two rocks against the wall."),
        h(12, "Mount Crenel", listOf(SPLIT), "Inside Grayblade's dojo, in the east of the mountain."),
        h(18, "Mount Crenel", listOf(MITTS), "Climb the Crenel Wall, go down the ladder to the left, and dig into the cave at the bottom (west of the Crenel Hermit)."),
        h(40, "Mount Crenel", listOf(FUSION), "Fuse with Melari: a beanstalk grows on the summit."),
        h(7, "Cave of Flames", listOf(BOMBS_), "Past the upturned mine cart, follow its tracks and bomb the cracked south wall."),
        h(9, "North Hyrule Field", listOf(BOMBS_), "Bomb the cracked block in the northwest corner, near the castle, and go down."),
        h(10, "Hyrule Town", listOf(CANE), "Flip the pot in the house of the boy with the dog to find a Minish Portal. Shrink, climb the vine by the inn and go round the back."),
        h(19, "Hyrule Town", emptyList(), "Win at Simon's Simulations."),
        h(20, "Hyrule Town", listOf(FLIPPERS, CAPE), "Minish-sized, go round behind the fountain and across, then Roc's Cape over to the west side."),
        h(29, "Hyrule Town", listOf(FLIPPERS), "Clear every round of Anju's Cucco game; the last one needs the Flippers."),
        h(32, "Hyrule Town", listOf(CAPE), "Jump up into the town bell with Roc's Cape."),
        h(35, "Hyrule Town", listOf("Figurines"), "Once you have 130 Figurines from Carlov (one guide says all of them), the man left of the café opens his Music House."),
        h(36, "Hyrule Town", listOf(SPLIT), "Shrink at the portal inside Funday School, follow the Minish path in the schoolyard to its end and split to push the rock."),
        h(13, "Hyrule Castle Garden", listOf("Lantern"), "Inside Grimblade's dojo: cut the bushes in the southeast corner of the garden."),
        h(44, "Hyrule Castle Garden", listOf(FUSION), "A random Kinstone fusion (often with the Hurdy-Gurdy Man) drains the northeast pool."),
        h(8, "Veil Falls", listOf(CANE), "Use the Cane on the hole at the north edge of Lon Lon Ranch, jump in, then north, east, downstairs and east."),
        h(21, "Veil Falls", listOf(FLIPPERS), "Swim east from the northeast corner of North Hyrule Field."),
        h(42, "Veil Falls", listOf(FUSION), "Fuse with Gale at the Cloud Tops: a waterfall opens near the top of the falls."),
        h(43, "Veil Falls", listOf(FUSION, FLIPPERS), "Fuse with the Forest Picori in the far east house of the Minish Village: a shoal rises to a cave near the bottom of the falls."),
        h(14, "Lon Lon Ranch", listOf(BOOTS), "Dash into a tree in the northwest of the ranch to reveal a Minish Portal, then follow the Minish path."),
        h(34, "Lon Lon Ranch", listOf(CAPE, MITTS), "Roc's Cape to Lake Hylia's north shore, dig into the cave and follow it left; it comes out at the ranch."),
        h(15, "Castor Wilds", listOf(BOOTS), "Inside Swiftblade the First's dojo: dash out to the westernmost land and push a tombstone in the southwest."),
        h(26, "Castor Wilds", listOf(FLIPPERS), "Swim to the cave in the northeast corner."),
        h(27, "Castor Wilds", listOf(FUSION, FLIPPERS), "After the Lily fusion, shrink in the northeast, ride the lily south through the swamp and swim to the small cave in the southeast."),
        h(16, "Wind Ruins", emptyList(), "Shrink at the stump right of the Fortress of Winds, go west and climb down two vines, then the middle of the next three, to a cave."),
        h(17, "Fortress of Winds", emptyList(), "Shrink in the eastern section, fall through the holes back down to the first floor, and go through the small passage to the east."),
        h(22, "Lake Hylia", listOf(FLIPPERS), "Dive in the little pond beside Stockwell's house, north of the lake."),
        h(23, "Lake Hylia", listOf(FLIPPERS), "On a ledge at the lake's southern edge."),
        h(24, "Lake Hylia", listOf(FLIPPERS), "Inside Waveblade's dojo, a tree on the southwest shore."),
        h(33, "Lake Hylia", listOf(CAPE), "Roc's Cape across the small islands in the north of the lake, above the Temple of Droplets."),
        h(41, "Lake Hylia", listOf(FUSION, CAPE, MITTS), "After the Forest Picori fusion that grows a beanstalk here, cape to the north shore, dig into the cave and go right to the beanstalk."),
        h(28, "South Hyrule Field", listOf(BOOTS, FLIPPERS), "Dash into a tree in the southwest for a Minish Portal; shrink, swim north in the river to a small cave."),
        h(37, "South Hyrule Field", listOf(FUSION), "Fuse with the Hurdy-Gurdy Man in Hyrule Town: a tree opens in the southeast."),
        h(38, "Eastern Hills", listOf(FUSION), "Fuse with the Forest Picori in the mushroom house in the southwest of the hills: a beanstalk grows."),
        h(39, "Western Wood", listOf(FUSION), "Fuse with the Forest Picori near Lake Hylia's Wind Crest: a tree opens in the middle of the wood."),
        h(30, "Royal Valley", listOf(SPLIT), "Push the northwest tombstone in the graveyard; split on the glowing pads to shove the big block."),
        h(31, "Palace of Winds", listOf(CAPE), "On the fourth floor, push the row of blocks into the gap and Roc's Cape across to the path north."),
    )

    val heartAreas: List<String> = hearts.map { it.area }.distinct()

    /** Bottles, capacity upgrades and item upgrades. */
    val upgrades: List<Upgrade> = listOf(
        Upgrade("u_bottle1", BOTTLE, "Empty Bottle 1", "Trilby Highlands", "Sold for 20 rupees by the Business Scrub in the cave down a ladder in the south. You need it for the story."),
        Upgrade("u_bottle2", BOTTLE, "Empty Bottle 2", "Eastern Hills", "Fuse with Smith (red): a chest appears south of Eenie and Meenie's garden."),
        Upgrade("u_bottle3", BOTTLE, "Empty Bottle 3", "Lake Hylia", "Shrink into the back of Stockwell's shop: he hands you a bottle of Dog Food for Fifi, his dog at his house by Lake Hylia. Feed Fifi and keep the bottle.", confirm = true),
        Upgrade("u_bottle4", BOTTLE, "Empty Bottle 4", "Lon Lon Ranch", "At the end of the Goron Cave. Fuse with Eenie to get the Gorons digging, then with all five Mysterious Walls for more diggers."),
        Upgrade("u_wallet1", WALLET, "Big Wallet (shop)", "Hyrule Town", "Stockwell's shop, 80 rupees.", confirm = true),
        Upgrade("u_wallet2", WALLET, "Big Wallet (Great Fairy)", "Minish Woods", "The Great Fairy inside a tree in the north of the woods asks for all your rupees. Say yes."),
        Upgrade("u_wallet3", WALLET, "Big Wallet (Mayor Hagen)", "Lon Lon Ranch", "Fuse with Mayor Hagen (red): a pool at the ranch drains down to a chest."),
        Upgrade("u_bombs1", BOMBS, "Big Bomb Bag (shop)", "Hyrule Town", "Stockwell's shop, 600 rupees. Not sold in the European version.", confirm = true),
        Upgrade("u_bombs2", BOMBS, "Big Bomb Bag (Great Fairy)", "Mount Crenel", "Bomb the unfenced wall by the 'No bomb throwing' spring, then throw a bomb into the Great Fairy's spring and answer truthfully."),
        Upgrade("u_bombs3", BOMBS, "Big Bomb Bag (Belari)", "Wind Ruins", "Fuse with Belari (red): a chest appears in the Wind Ruins."),
        Upgrade("u_quiver1", QUIVER, "Large Quiver (shop)", "Hyrule Town", "Stockwell's shop, 600 rupees.", confirm = true),
        Upgrade("u_quiver2", QUIVER, "Large Quiver (beanstalk)", "Wind Ruins", "Fuse with the Forest Picori in the Wind Ruins (red): climb the beanstalk to the chest."),
        Upgrade("u_quiver3", QUIVER, "Large Quiver (Great Fairy)", "Royal Valley", "Answer all the Great Fairy's questions about your journey truthfully. Get one wrong and she empties your quiver."),
        Upgrade("u_boomerang", ITEM, "Magical Boomerang", "North Hyrule Field", "Buy the Boomerang at Stockwell's (300 rupees). Fusing with each of the four Tingle brothers (Tingle, Ankle, Knuckle and David Jr.) opens a tree in North Hyrule Field with a switch inside; with all four on, a ladder leads down to the chest."),
        Upgrade("u_remote", ITEM, "Remote Bombs", "Minish Woods", "Fuse with Elder Gentari (red), then see Belari. He'll swap back if you ask."),
        Upgrade("u_lightbow", ITEM, "Bow of Light", "Cloud Tops", "Fuse with Strato in Hyrule Town to open a portal in South Hyrule Field. Up there, suck the ghost off Gregal with the Gust Jar. Visit him again once you reach the Cloud Tops by Veil Falls.", missable = "If you reach the Cloud Tops through Veil Falls before saving Gregal, he dies and the Bow of Light is gone for good."),
        Upgrade("u_mirror", ITEM, "Mirror Shield", "Veil Springs", "After beating Vaati, let Biggoron eat your shield at Veil Springs (fusion with the Goron in the Goron Cave wakes him). Come back later for the Mirror Shield."),
    )

    val heartById: Map<String, Heart> = hearts.associateBy { it.id }
    val upgradeById: Map<String, Upgrade> = upgrades.associateBy { it.id }
    val stepById: Map<String, Step> = steps.associateBy { it.id }

    init {
        require(hearts.size == 44 && heartById.size == 44) { "The Minish Cap has 44 Heart Pieces, not ${hearts.size}" }
        require(upgradeById.size == upgrades.size) { "Duplicate upgrade id" }
    }
}
