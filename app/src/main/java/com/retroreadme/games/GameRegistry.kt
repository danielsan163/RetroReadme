package com.retroreadme.games

import com.retroreadme.core.Game
import com.retroreadme.games.mmx2.Mmx2Game
import com.retroreadme.games.smw.SmwGame
import com.retroreadme.games.wl4.Wl4Game

/**
 * Every guide in the app. Add new games here. The launcher groups them by platform
 * (in Platform's order); within a platform they keep the order listed here.
 * Guides with the same groupTitle should be listed next to each other.
 */
object GameRegistry {
    val games: List<Game> = listOf(
        Mmx2Game.game,
        SmwGame.game,
        Wl4Game.normal,
        Wl4Game.hard,
    ).sortedBy { it.platform.ordinal }
}
