package com.retroreadme.core

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/** App-wide settings, persisted in SharedPreferences ("settings"). */
class SettingsStore(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    /** Id of the launcher theme (a LauncherTheme name). Unknown ids fall back to the default. */
    var launcherTheme: String by mutableStateOf(prefs.getString(KEY_THEME, null) ?: "SNES")
        private set

    fun saveLauncherTheme(id: String) {
        launcherTheme = id
        prefs.edit().putString(KEY_THEME, id).apply()
    }

    /** Console headings (Platform names) the user has collapsed on the launcher. */
    var collapsedPlatforms: Set<String> by mutableStateOf(prefs.getStringSet(KEY_COLLAPSED, null)?.toSet() ?: emptySet())
        private set

    fun setPlatformCollapsed(platform: String, collapsed: Boolean) {
        collapsedPlatforms = if (collapsed) collapsedPlatforms + platform else collapsedPlatforms - platform
        prefs.edit().putStringSet(KEY_COLLAPSED, collapsedPlatforms).apply()
    }

    private companion object {
        const val KEY_THEME = "launcher_theme"
        const val KEY_COLLAPSED = "collapsed_platforms"
    }
}
