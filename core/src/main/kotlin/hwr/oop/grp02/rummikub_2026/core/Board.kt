package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.NoSuchTileException
import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.containers.Group

data class Board(private val groups: List<Group> = listOf()) {
	fun groups() = groups
	
	fun tiles() = groups.flatMap { it.tiles() }
	
	fun subtractTiles(tiles: List<Tile>): List<Tile> {
		val mutableTiles = tiles().toMutableList()
		if (!tiles().containsAll(tiles)) throw NoSuchTileException()
		tiles.forEach { mutableTiles.remove(it) }
		return mutableTiles.toList()
	}
	
	fun validate(): Boolean {
		return groups.all { it.validate() }
	}
}