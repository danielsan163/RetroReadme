package com.retroreadme.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.floor

private val StarFill = Color(0xFFF8D830)
private val StarShade = Color(0xFFC89010)

/**
 * 9×9 pixel star: # = fill, s = shade, . = empty.
 * Drawn with whole-pixel squares so it stays crisp at any size.
 */
private val STAR = listOf(
    "....#....",
    "....#....",
    "...###...",
    "####s####",
    ".###s###.",
    "..##s##..",
    "..#sss#..",
    ".##s.s##.",
    ".#.....#.",
)

/** A small yellow pixel-art star, used to mark a fully checked-off guide. */
@Composable
fun PixelStar(size: Dp = 16.dp, modifier: Modifier = Modifier) {
    Canvas(modifier.size(size)) {
        val px = floor(minOf(this.size.width, this.size.height) / STAR.size).coerceAtLeast(1f)
        // Center the grid in the canvas.
        val originX = (this.size.width - px * STAR[0].length) / 2f
        val originY = (this.size.height - px * STAR.size) / 2f
        STAR.forEachIndexed { y, row ->
            row.forEachIndexed { x, ch ->
                val color = when (ch) {
                    '#' -> StarFill
                    's' -> StarShade
                    else -> null
                }
                if (color != null) {
                    drawRect(color, topLeft = Offset(originX + x * px, originY + y * px), size = Size(px, px))
                }
            }
        }
    }
}
