package com.retroreadme.games

import com.retroreadme.core.Game
import com.retroreadme.games.aos.AosGame
import com.retroreadme.games.dread.DreadGame
import com.retroreadme.games.kdl3.Kdl3Game
import com.retroreadme.games.mf.MfGame
import com.retroreadme.games.mmx2.Mmx2Game
import com.retroreadme.games.mmz.MmzGame
import com.retroreadme.games.mzm.MzmGame
import com.retroreadme.games.sm.SmGame
import com.retroreadme.games.smw.SmwGame
import com.retroreadme.games.tmc.TmcGame
import com.retroreadme.games.wl4.Wl4Game

/**
 * Every guide in the app. Add new games here, in any order: the launcher groups them by
 * platform (in Platform's order) and sorts each platform's games alphabetically.
 */
object GameRegistry {
    val games: List<Game> = listOf(
        Mmx2Game.game,
        SmwGame.game,
        Kdl3Game.game,
        Wl4Game.normal,
        Wl4Game.hard,
        MzmGame.game,
        MfGame.game,
        SmGame.game,
        MmzGame.game,
        TmcGame.game,
        DreadGame.game,
        AosGame.game,
    ).sortedWith(
        // Grouped by platform, then alphabetical by title. Guides in a group sort by the
        // group's title and keep the order listed here (e.g. Normal before Hard).
        compareBy<Game>({ it.platform.ordinal }, { (it.groupTitle ?: it.title).lowercase() }),
    )
}
