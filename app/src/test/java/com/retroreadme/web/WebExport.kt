package com.retroreadme.web

import com.retroreadme.core.Game
import com.retroreadme.core.Platform
import com.retroreadme.games.GameRegistry
import com.retroreadme.games.wl4.Mode
import com.retroreadme.ui.LauncherPalette
import org.junit.Test
import java.io.File

/**
 * Writes the web app's data (docs/data/) from the guide content, so the web version always
 * matches the Android app. Run it after changing any guide:
 *
 *     gradle :app:testDebugUnitTest --tests com.retroreadme.web.WebExport
 *
 * A guide without an exporter fails the run, so new guides can't be left out by accident.
 */
class WebExport {

    private val tabs: Map<String, () -> List<Tab>> = mapOf(
        "mmx2" to ::mmx2Tabs,
        "smw" to ::smwTabs,
        "kdl3" to ::kdl3Tabs,
        "wl4" to { wl4Tabs(Mode.NORMAL) },
        "wl4_hard" to { wl4Tabs(Mode.HARD) },
        "aos" to ::aosTabs,
        "mzm" to ::mzmTabs,
        "mf" to ::mfTabs,
        "sm" to ::smTabs,
        "mmz" to ::mmzTabs,
        "tmc" to ::tmcTabs,
        "dread" to ::dreadTabs,
    )

    @Test
    fun export() {
        // Gradle runs unit tests from the module directory (app/).
        val out = File("../docs/data").apply { mkdirs() }
        val games = GameRegistry.games
        val missing = games.map { it.id } - tabs.keys
        check(missing.isEmpty()) { "No web exporter for: $missing (add one in GuideExports.kt)" }

        games.forEach { g ->
            val guide = meta(g) + mapOf(
                "tabs" to tabs.getValue(g.id)().map { it.json() },
                "maps" to when (g.id) {
                    "mzm" -> mzmMaps()
                    "mf" -> mfMaps()
                    "sm" -> smMaps()
                    "dread" -> dreadMaps()
                    else -> null
                },
            )
            File(out, "${g.id}.json").writeText(toJson(guide).toString())
        }
        val index = mapOf(
            "platforms" to Platform.entries.map { listOf(it.name, it.label) },
            "palette" to palette(LauncherPalette),
            "games" to games.map { meta(it) - "palette" + mapOf("accent" to hex(it.palette.accent)) },
        )
        File(out, "index.json").writeText(toJson(index).toString())
        println("Wrote ${games.size} guides to ${out.canonicalPath}")
    }

    private fun meta(g: Game): Map<String, Any?> = mapOf(
        "id" to g.id,
        "title" to g.title,
        "platform" to g.platform.name,
        "badge" to g.badge,
        "group" to g.groupTitle,
        "variant" to g.variant,
        "about" to g.about,
        "palette" to palette(g.palette),
        "checklist" to g.checklist.map { listOf(it.id, hex(it.color)) },
        "celebrate" to listOf(g.celebrationTitle, g.celebrationMessage),
    )
}
