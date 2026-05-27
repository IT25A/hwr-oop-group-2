package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

data class TileSet(private val tiles: MutableList<Tile> = mutableListOf()) {
	fun add(tile: Tile) {
		tiles.add(tile)
	}
	
	fun add(tileList: List<Tile>) {
		tiles.addAll(tileList)
	}
	
	fun remove(tile: Tile): Boolean {
		return tiles.remove(tile)
	}
	
	fun removeFirst(): Tile {
		return tiles.removeFirst()
	}
	
	fun tiles(): List<Tile> {
		return tiles.toList()
	}
	
	fun size(): Int {
		return tiles.size
	}
	
	fun sortByNumber() {
		tiles.sortBy { it.number().value() }
	}
	
	fun sortByColor() {
		tiles.sortBy { it.color().name }
	}
}
