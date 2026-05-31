package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

internal interface MoveTarget {
	internal abstract fun add(tile: Tile): Unit
	internal abstract fun remove(tile: Tile): Boolean
}
