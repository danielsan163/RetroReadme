package com.retroreadme.games.mf

import androidx.compose.runtime.Composable
import com.retroreadme.core.ProgressStore
import com.retroreadme.ui.ItemShape
import com.retroreadme.ui.MapItem
import com.retroreadme.ui.MapRoom
import com.retroreadme.ui.RoomMap
import com.retroreadme.ui.RoomMapView

/** Fusion's sector maps (data in MfMapData.kt, drawn by the shared RoomMapView). */
object MfMaps {
    private val bySector = MfMapData.all.associateBy { it.key }
    fun of(sector: Sector): RoomMap = bySector.getValue(sector.name)
    fun roomOf(item: Item): MapRoom? = of(item.sector).roomOf(item.id)

    init {
        // Every item must sit inside a room on its own sector's map.
        MfItems.all.forEach { item ->
            requireNotNull(roomOf(item)) { "No map room for ${item.id}" }
        }
    }
}

/** The map of [item]'s sector with its room highlighted. */
@Composable
fun MfMapView(item: Item, progress: ProgressStore, zoomed: Boolean) {
    RoomMapView(MfMaps.of(item.sector), MfMaps.roomOf(item), item.id, zoomed) { id ->
        MfItems.byId[id]?.let {
            val shape = if (it.kind == Kind.ENERGY) ItemShape.SQUARE else ItemShape.CIRCLE
            MapItem(it.kind.color, shape, progress.isDone(id))
        }
    }
}
