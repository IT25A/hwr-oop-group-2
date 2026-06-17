package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.NoSuchTileException
import hwr.oop.grp02.rummikub_2026.core.tile.Tile

data class Player(
	private val name: String,
	private val tiles: List<Tile> = listOf(),
	private val initialMeld: Boolean = false,
	private val points: Int = 0,
) {
	fun rack() = tiles
	fun initMeld() = initialMeld
	fun name() = name
	fun points() = points
	
	fun add(tile: Tile): Player {
		val original = tiles.toMutableList()
		original.add(tile)
		return copy(tiles = original.toList())
	}
	
	fun remove(tile: Tile): Player {
		val original = tiles.toMutableList()
		if (!original.remove(tile)) throw NoSuchTileException()
		return copy(tiles = original.toList())
	}
	
	fun removeAll(vararg tile: Tile): Player {
		val original = tiles.toMutableList()
		if (!original.containsAll(tile.toList())) throw NoSuchTileException()
		tile.toList().forEach { original.remove(it) }
		return copy(tiles = original.toList())
	}
	
	fun sum(): Int {
		return -tiles.sumOf { it.number().value() }
	}
}