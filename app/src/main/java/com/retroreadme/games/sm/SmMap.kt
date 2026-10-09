package com.retroreadme.games.sm

import androidx.compose.runtime.Composable
import com.retroreadme.core.ProgressStore
import com.retroreadme.ui.ItemShape
import com.retroreadme.ui.MapItem
import com.retroreadme.ui.MapRoom
import com.retroreadme.ui.RoomMap
import com.retroreadme.ui.RoomMapView

/** Super Metroid's area maps (data in SmMapData.kt, drawn by the shared RoomMapView). */
object SmMaps {
    private val byArea = SmMapData.all.associateBy { it.key }
    fun of(area: Area): RoomMap = byArea.getValue(area.name)
    fun roomOf(item: Item): MapRoom? = of(item.area).roomOf(item.id)

    init {
        // Every item must sit inside a room on its own area's map.
        SmItems.all.forEach { item ->
            requireNotNull(roomOf(item)) { "No map room for ${item.id}" }
        }
    }
}

/** The map of [item]'s area with its room highlighted. */
@Composable
fun SmMapView(item: Item, progress: ProgressStore, zoomed: Boolean) {
    RoomMapView(SmMaps.of(item.area), SmMaps.roomOf(item), item.id, zoomed) { id ->
        SmItems.byId[id]?.let {
            val shape = when (it.kind) {
                Kind.MAJOR -> ItemShape.DIAMOND
                Kind.ENERGY, Kind.RESERVE -> ItemShape.SQUARE
                else -> ItemShape.CIRCLE
            }
            MapItem(it.kind.color, shape, progress.isDone(id))
        }
    }
}
