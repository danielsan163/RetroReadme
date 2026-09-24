package com.retroreadme

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.retroreadme.core.ProgressStore
import com.retroreadme.games.GameRegistry
import com.retroreadme.ui.RetroReadmeApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Full-screen: the 4:3 panel is small, so give every pixel to the guide.
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        // One store per game, each in its own prefs file, shared by the launcher and the guide.
        val stores = GameRegistry.games.associate { it.id to ProgressStore(applicationContext, it.id) }
        setContent { RetroReadmeApp(stores) }
    }

    /**
     * Gamepad face buttons: A confirms, B goes back.
     * If your handheld's layout is flipped, swap BUTTON_A and BUTTON_B below.
     */
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val remapped = when (event.keyCode) {
            KeyEvent.KEYCODE_BUTTON_A -> KeyEvent.KEYCODE_DPAD_CENTER
            KeyEvent.KEYCODE_BUTTON_B -> KeyEvent.KEYCODE_BACK
            else -> return super.dispatchKeyEvent(event)
        }
        return super.dispatchKeyEvent(
            KeyEvent(
                event.downTime, event.eventTime, event.action, remapped, event.repeatCount,
                event.metaState, event.deviceId, event.scanCode, event.flags, event.source,
            )
        )
    }
}
