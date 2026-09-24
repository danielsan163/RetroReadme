package com.retroreadme.core

import android.content.Context
import androidx.compose.runtime.mutableStateMapOf

/**
 * Checklist state for one game, persisted in its own SharedPreferences file ("progress_<gameId>"),
 * so ids can never collide between games and clearing one game's checklist leaves the others alone.
 */
class ProgressStore(context: Context, gameId: String) {
    private val prefs = context.getSharedPreferences("progress_$gameId", Context.MODE_PRIVATE)
    private val done = mutableStateMapOf<String, Boolean>().apply {
        prefs.all.forEach { (key, value) -> if (value == true) put(key, true) }
    }

    fun isDone(id: String): Boolean = done[id] == true

    fun toggle(id: String) {
        val nowDone = !isDone(id)
        if (nowDone) done[id] = true else done.remove(id)
        prefs.edit().putBoolean(id, nowDone).apply()
    }

    fun countDone(ids: List<String>): Int = ids.count { isDone(it) }

    fun reset() {
        done.clear()
        prefs.edit().clear().apply()
    }
}
