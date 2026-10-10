package com.retroreadme.games.dread

import androidx.compose.runtime.Composable
import com.retroreadme.core.ProgressStore
import com.retroreadme.ui.ItemShape
import com.retroreadme.ui.MapItem
import com.retroreadme.ui.MapRoom
import com.retroreadme.ui.RoomMap
import com.retroreadme.ui.RoomMapView

/** Metroid Dread's area maps (data in DreadMapData.kt, drawn by the shared RoomMapView). */
object DreadMaps {
    private val byArea = DreadMapData.all.associateBy { it.key }
    fun of(area: Area): RoomMap = byArea.getValue(area.name)
    fun roomOf(item: Item): MapRoom? = of(item.area).roomOf(item.id)

    init {
        // Every item must sit inside a room on its own area's map.
        DreadItems.all.forEach { item ->
            requireNotNull(roomOf(item)) { "No map room for ${item.id}" }
        }
    }
}

/** The map of [item]'s area with its room highlighted. */
@Composable
fun DreadMapView(item: Item, progress: ProgressStore, zoomed: Boolean) {
    RoomMapView(DreadMaps.of(item.area), DreadMaps.roomOf(item), item.id, zoomed) { id ->
        DreadItems.byId[id]?.let {
            val shape = when (it.kind) {
                Kind.MAJOR -> ItemShape.DIAMOND
                Kind.ENERGY, Kind.PART -> ItemShape.SQUARE
                else -> ItemShape.CIRCLE
            }
            MapItem(it.kind.color, shape, progress.isDone(id))
        }
    }
}
