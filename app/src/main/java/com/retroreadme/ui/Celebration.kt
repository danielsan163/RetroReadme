package com.retroreadme.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * Shown once, when a game's last checklist item gets checked off.
 * Rotating light rays behind a life-bar that fills itself in, one cell per checklist item.
 */
@Composable
fun CelebrationOverlay(
    cells: List<Color>,
    title: String,
    message: String,
    onDismiss: () -> Unit,
) {
    // Narrower cells for long checklists so the bar always fits the 4:3 screen.
    val cellWidth = when {
        cells.size <= 32 -> 11.dp
        cells.size <= 60 -> 7.dp
        cells.size <= 100 -> 5.dp
        else -> 3.dp
    }
    val cellGap = if (cells.size <= 32) 3.dp else 1.dp
    val focusRequester = remember { FocusRequester() }
    val spin = rememberInfiniteTransition(label = "rays")
    val angle by spin.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(durationMillis = 18000, easing = LinearEasing)),
        label = "angle",
    )
    val pop = remember { Animatable(0.55f) }
    val filled = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        runCatching { focusRequester.requestFocus() }
        pop.animateTo(
            targetValue = 1f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        )
    }
    LaunchedEffect(Unit) {
        delay(250)
        filled.animateTo(cells.size.toFloat(), tween(durationMillis = 1600, easing = LinearEasing))
    }
    BackHandler { onDismiss() }

    // Read theme colors here: the Canvas draw block below isn't a composable scope.
    val rayColor = Palette.accent.copy(alpha = 0.055f)

    Box(
        Modifier
            .fillMaxSize()
            .background(Palette.background.copy(alpha = 0.93f))
            .focusRequester(focusRequester)
            .focusable()
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val rayLength = size.maxDimension
            repeat(14) { i ->
                rotate(degrees = angle + i * (360f / 14f)) {
                    drawRect(
                        color = rayColor,
                        topLeft = Offset(center.x, center.y - 16f),
                        size = Size(rayLength, 32f),
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.padding(24.dp),
        ) {
            Text(
                text = "100%",
                color = Palette.accent,
                fontFamily = Condensed,
                fontSize = 68.sp,
                modifier = Modifier.graphicsLayer {
                    scaleX = pop.value
                    scaleY = pop.value
                },
            )
            Text(
                text = title,
                color = Palette.text,
                fontFamily = Condensed,
                fontSize = 26.sp,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(cellGap)) {
                cells.forEachIndexed { i, color ->
                    val lit = i < filled.value
                    Box(
                        Modifier
                            .size(width = cellWidth, height = 30.dp)
                            .clip(PanelShape)
                            .background(if (lit) color else color.copy(alpha = 0.15f)),
                    )
                }
            }
            Text(
                text = message,
                color = Palette.muted,
                fontSize = 15.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Press A to continue",
                color = Palette.accent.copy(alpha = 0.7f),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 6.dp),
            )
        }
    }
}
