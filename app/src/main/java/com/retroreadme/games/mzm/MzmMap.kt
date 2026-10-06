package com.retroreadme.games.mzm

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.retroreadme.core.ProgressStore
import com.retroreadme.ui.Palette

enum class RoomStyle { NORMAL, HIDDEN, HEATED }
enum class DoorKind { NORMAL, MISSILE, SUPER, POWER_BOMB }
enum class ExitDir { UP, DOWN, LEFT, RIGHT }

/** An elevator or tunnel to another area, drawn as an arrow off the room's edge. */
data class Exit(val to: String, val dir: ExitDir)

/** Map cells c0..c1, r0..r1 (inclusive). Columns grow right, rows grow down. */
data class CellRect(val c0: Int, val r0: Int, val c1: Int, val r1: Int)

data class MapRoom(
    /** e.g. "BR-07". Numbered in the order the 100% route reaches each room. */
    val code: String,
    val style: RoomStyle,
    val rects: List<CellRect>,
    val save: Boolean = false,
    val map: Boolean = false,
    val exit: Exit? = null,
) {
    val cells: List<Long> = rects.flatMap { r -> (r.r0..r.r1).flatMap { row -> (r.c0..r.c1).map { cell(it, row) } } }
    /** Top-left cell, where the room number goes. */
    val anchor: Long = cells.minWith(compareBy({ rowOf(it) }, { colOf(it) }))
}

/** A door on the wall between (col, row) and (col + 1, row). */
data class MapDoor(val row: Int, val col: Int, val kind: DoorKind)

class AreaMap(
    val area: Area,
    val prefix: String,
    val rooms: List<MapRoom>,
    val doors: List<MapDoor>,
    /** Where each item's icon sits. */
    val items: Map<String, Long>,
) {
    val roomAt: Map<Long, MapRoom> = rooms.flatMap { room -> room.cells.map { it to room } }.toMap()
    val minCol = roomAt.keys.minOf { colOf(it) }
    val maxCol = roomAt.keys.maxOf { colOf(it) }
    val minRow = roomAt.keys.minOf { rowOf(it) }
    val maxRow = roomAt.keys.maxOf { rowOf(it) }

    fun roomOf(itemId: String): MapRoom? = items[itemId]?.let { roomAt[it] }
}

fun cell(col: Int, row: Int): Long = (col.toLong() shl 32) or (row.toLong() and 0xFFFFFFFFL)
fun colOf(cell: Long): Int = (cell shr 32).toInt()
fun rowOf(cell: Long): Int = cell.toInt()

fun r(c0: Int, r0: Int, c1: Int, r1: Int) = CellRect(c0, r0, c1, r1)
fun d(row: Int, col: Int, kind: DoorKind = DoorKind.NORMAL) = MapDoor(row, col, kind)
fun room(code: String, rects: List<CellRect>, save: Boolean = false, map: Boolean = false, exit: Exit? = null) =
    MapRoom(code, RoomStyle.NORMAL, rects, save, map, exit)
fun room(code: String, style: RoomStyle, rects: List<CellRect>, save: Boolean = false, map: Boolean = false, exit: Exit? = null) =
    MapRoom(code, style, rects, save, map, exit)

object MzmMaps {
    private val byArea = MzmMapData.all.associateBy { it.area }
    fun of(area: Area): AreaMap = byArea.getValue(area)
    fun roomOf(item: Item): MapRoom? = of(item.area).roomOf(item.id)

    init {
        // Every item must sit inside a room on its own area's map.
        MzmItems.all.forEach { item ->
            requireNotNull(roomOf(item)) { "No map room for ${item.id}" }
        }
    }
}

private object MapColors {
    val Normal = Color(0xFF1E3550)
    val Hidden = Color(0xFF1B3A24)
    val Heated = Color(0xFF4A3A12)
    val Wall = Color(0xFFE8ECF2)
    val HiddenWall = Color(0xFF7CFC6A)
    val Door = Color(0xFF4FC3F7)
    val MissileDoor = Color(0xFFD8342C)
    val SuperDoor = Color(0xFF3DDC6A)
    val PowerBombDoor = Color(0xFFF2C94C)
    val Label = Color(0xFFF2C94C)
    val Chip = Color(0xD90B0E14)
}

/**
 * An area's map: room outlines, doors, saves, elevators and item icons. The [highlight] room
 * is outlined in the accent color and [selectedItem] gets a ring. Collected items fade out.
 * [zoomed] shows a close-up around the highlighted room with readable room numbers; otherwise
 * the whole area is scaled to fit the width it's given.
 */
@Composable
fun AreaMapView(map: AreaMap, highlight: MapRoom?, selectedItem: String?, progress: ProgressStore, zoomed: Boolean) {
    val measurer = rememberTextMeasurer()
    val accent = Palette.accent
    val muted = Palette.muted
    // One spare cell around the edges for exit arrows.
    val cols = map.maxCol - map.minCol + 3
    val rows = map.maxRow - map.minRow + 3
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val fitCell = min(maxWidth / cols, 30.dp)
        // Close-up: big enough cells for readable room numbers, centered on the highlighted room.
        val cellDp = if (zoomed) max(fitCell, CLOSE_UP_CELL) else fitCell
        val visibleRows = if (zoomed) minOf(rows, CLOSE_UP_ROWS) else rows
        Canvas(Modifier.fillMaxWidth().height(cellDp * visibleRows).clipToBounds()) {
            val s = cellDp.toPx()
            // Center of the highlighted room, in map pixels.
            val focus = highlight?.let { room ->
                Offset(
                    (room.cells.map { colOf(it) }.average().toFloat() - map.minCol + 1.5f) * s,
                    (room.cells.map { rowOf(it) }.average().toFloat() - map.minRow + 1.5f) * s,
                )
            } ?: Offset(cols * s / 2, rows * s / 2)
            fun offset(total: Float, view: Float, at: Float) =
                if (total <= view) (view - total) / 2 else (view / 2 - at).coerceIn(view - total, 0f)
            val left = offset(cols * s, size.width, focus.x)
            val top = offset(rows * s, size.height, focus.y)
            fun x(col: Int) = left + (col - map.minCol + 1) * s
            fun y(row: Int) = top + (row - map.minRow + 1) * s
            val wall = 1.5.dp.toPx()

            map.roomAt.forEach { (c, room) ->
                val fill = when (room.style) {
                    RoomStyle.NORMAL -> MapColors.Normal
                    RoomStyle.HIDDEN -> MapColors.Hidden
                    RoomStyle.HEATED -> MapColors.Heated
                }
                drawRect(fill, Offset(x(colOf(c)), y(rowOf(c))), Size(s, s))
                if (room === highlight) drawRect(accent.copy(alpha = 0.28f), Offset(x(colOf(c)), y(rowOf(c))), Size(s, s))
            }

            // Walls wherever a cell's neighbor belongs to another room (or none).
            val dash = PathEffect.dashPathEffect(floatArrayOf(3.dp.toPx(), 3.dp.toPx()))
            map.roomAt.forEach { (c, room) ->
                val col = colOf(c)
                val row = rowOf(c)
                val color = if (room.style == RoomStyle.HIDDEN) MapColors.HiddenWall.copy(alpha = 0.6f) else MapColors.Wall.copy(alpha = 0.85f)
                val effect = if (room.style == RoomStyle.HIDDEN) dash else null
                fun edge(other: Long, a: Offset, b: Offset) {
                    if (map.roomAt[other] !== room) drawLine(color, a, b, wall, pathEffect = effect)
                }
                edge(cell(col + 1, row), Offset(x(col + 1), y(row)), Offset(x(col + 1), y(row + 1)))
                edge(cell(col - 1, row), Offset(x(col), y(row)), Offset(x(col), y(row + 1)))
                edge(cell(col, row + 1), Offset(x(col), y(row + 1)), Offset(x(col + 1), y(row + 1)))
                edge(cell(col, row - 1), Offset(x(col), y(row)), Offset(x(col + 1), y(row)))
            }

            // Outline the highlighted room on top of its neighbors' walls.
            if (highlight != null) {
                highlight.cells.forEach { c ->
                    val col = colOf(c)
                    val row = rowOf(c)
                    fun edge(other: Long, a: Offset, b: Offset) {
                        if (map.roomAt[other] !== highlight) drawLine(accent, a, b, 2.5.dp.toPx())
                    }
                    edge(cell(col + 1, row), Offset(x(col + 1), y(row)), Offset(x(col + 1), y(row + 1)))
                    edge(cell(col - 1, row), Offset(x(col), y(row)), Offset(x(col), y(row + 1)))
                    edge(cell(col, row + 1), Offset(x(col), y(row + 1)), Offset(x(col + 1), y(row + 1)))
                    edge(cell(col, row - 1), Offset(x(col), y(row)), Offset(x(col + 1), y(row)))
                }
            }

            map.doors.forEach { door ->
                val color = when (door.kind) {
                    DoorKind.NORMAL -> MapColors.Door
                    DoorKind.MISSILE -> MapColors.MissileDoor
                    DoorKind.SUPER -> MapColors.SuperDoor
                    DoorKind.POWER_BOMB -> MapColors.PowerBombDoor
                }
                val w = maxOf(3.dp.toPx(), s * 0.16f)
                drawRoundRect(
                    color, Offset(x(door.col + 1) - w / 2, y(door.row) + s * 0.25f), Size(w, s * 0.5f),
                    CornerRadius(w / 2, w / 2),
                )
            }

            val letterStyle = TextStyle(color = MapColors.Label, fontSize = (s * 0.5f).toSp(), fontWeight = FontWeight.Bold)
            map.rooms.forEach { room ->
                val ax = x(colOf(room.anchor))
                val ay = y(rowOf(room.anchor))
                val letter = if (room.save) "S" else if (room.map) "M" else null
                if (letter != null) {
                    val laid = measurer.measure(letter, letterStyle)
                    drawText(laid, topLeft = Offset(ax + (s - laid.size.width) / 2, ay + s - laid.size.height))
                }
                room.exit?.let { exit -> drawExit(room, exit, s, ::x, ::y, accent, measurer, muted) }
            }

            map.items.forEach { (id, c) ->
                val item = MzmItems.byId[id] ?: return@forEach
                val done = progress.isDone(id)
                val color = item.kind.color.copy(alpha = if (done) 0.3f else 1f)
                val center = Offset(x(colOf(c)) + s * 0.6f, y(rowOf(c)) + s * 0.64f)
                val r = s * 0.19f
                when (item.kind) {
                    Kind.MAJOR -> drawPath(
                        Path().apply {
                            moveTo(center.x, center.y - r * 1.25f); lineTo(center.x + r * 1.25f, center.y)
                            lineTo(center.x, center.y + r * 1.25f); lineTo(center.x - r * 1.25f, center.y); close()
                        },
                        color,
                    )
                    Kind.ENERGY -> drawRoundRect(color, center - Offset(r, r), Size(r * 2, r * 2), CornerRadius(r / 3, r / 3))
                    else -> drawCircle(color, r, center)
                }
                if (id == selectedItem) drawCircle(accent, r * 2f, center, style = Stroke(2.dp.toPx()))
            }

            // Room numbers last, on a dark chip so they read over any fill. Too small to read in
            // the whole-area view, so there only the highlighted room gets its full code.
            val itemCells = map.items.values.toSet()
            fun labelCell(room: MapRoom): Long {
                val letterCell = if (room.save || room.map) room.anchor else null
                return room.cells.sortedWith(compareBy({ rowOf(it) }, { colOf(it) }))
                    .firstOrNull { it !in itemCells && it != letterCell } ?: room.anchor
            }
            fun chip(text: String, c: Long, color: Color, size: TextUnit) {
                val laid = measurer.measure(text, TextStyle(color = color, fontSize = size, fontWeight = FontWeight.Bold))
                val pad = 2.dp.toPx()
                val at = Offset(x(colOf(c)) + 1.5.dp.toPx(), y(rowOf(c)) + 1.5.dp.toPx())
                drawRoundRect(
                    MapColors.Chip, at, Size(laid.size.width + pad * 2, laid.size.height.toFloat()),
                    CornerRadius(pad, pad),
                )
                drawText(laid, topLeft = at + Offset(pad, 0f))
            }
            if (s >= MIN_NUMBERED_CELL.toPx()) {
                map.rooms.forEach { room ->
                    chip(room.code.substringAfter('-'), labelCell(room), if (room === highlight) accent else MapColors.Wall, 10.sp)
                }
            } else if (highlight != null) {
                chip(highlight.code, labelCell(highlight), accent, 11.sp)
            }
        }
    }
}

/** Close-up cell size: room numbers stay readable at this size. */
private val CLOSE_UP_CELL = 26.dp
/** How many rows of the map the close-up shows. */
private const val CLOSE_UP_ROWS = 9
/** Below this cell size the whole-area view drops the room numbers. */
private val MIN_NUMBERED_CELL = 20.dp

/** A small arrow off the room's outer edge pointing to the area it leads to, with its name. */
private fun DrawScope.drawExit(
    room: MapRoom, exit: Exit, s: Float, x: (Int) -> Float, y: (Int) -> Float, color: Color,
    measurer: androidx.compose.ui.text.TextMeasurer, labelColor: Color,
) {
    val cells = room.cells
    val c = when (exit.dir) {
        ExitDir.UP -> cells.minWith(compareBy({ rowOf(it) }, { colOf(it) }))
        ExitDir.DOWN -> cells.maxWith(compareBy({ rowOf(it) }, { -colOf(it) }))
        ExitDir.LEFT -> cells.minWith(compareBy({ colOf(it) }, { rowOf(it) }))
        ExitDir.RIGHT -> cells.maxWith(compareBy({ colOf(it) }, { -rowOf(it) }))
    }
    val cx = x(colOf(c)) + s / 2
    val cy = y(rowOf(c)) + s / 2
    val a = s * 0.22f
    val tip = when (exit.dir) {
        ExitDir.UP -> Offset(cx, cy - s * 0.95f)
        ExitDir.DOWN -> Offset(cx, cy + s * 0.95f)
        ExitDir.LEFT -> Offset(cx - s * 0.95f, cy)
        ExitDir.RIGHT -> Offset(cx + s * 0.95f, cy)
    }
    val back = when (exit.dir) {
        ExitDir.UP -> Offset(0f, a * 1.6f)
        ExitDir.DOWN -> Offset(0f, -a * 1.6f)
        ExitDir.LEFT -> Offset(a * 1.6f, 0f)
        ExitDir.RIGHT -> Offset(-a * 1.6f, 0f)
    }
    val side = Offset(back.y, back.x) * (a / (a * 1.6f))
    drawPath(Path().apply { moveTo(tip.x, tip.y); lineTo(tip.x + back.x + side.x, tip.y + back.y + side.y); lineTo(tip.x + back.x - side.x, tip.y + back.y - side.y); close() }, color)
    val laid = measurer.measure(exit.to, TextStyle(color = labelColor, fontSize = (s * 0.32f).toSp()))
    val at = when (exit.dir) {
        ExitDir.UP -> Offset(tip.x + a * 1.4f, tip.y)
        ExitDir.DOWN -> Offset(tip.x + a * 1.4f, tip.y - laid.size.height)
        ExitDir.LEFT -> Offset(tip.x - laid.size.width / 2, tip.y + a * 1.2f)
        ExitDir.RIGHT -> Offset(tip.x - laid.size.width / 2, tip.y + a * 1.2f)
    }
    drawText(laid, topLeft = at)
}
