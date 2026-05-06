package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

abstract class TilesContainer {
	protected val tiles: MutableList<Tile> = mutableListOf();
	
	fun add(tile: Tile) {
		tiles.add(tile);
	}
	
	fun add(tileList: List<Tile>) {
		tiles.addAll(tileList);
	}
	
	fun remove(tile: Tile) {
		tiles.remove(tile);
	}
	
	fun tiles(): List<Tile> {
		return tiles.toList();
	}
	
	fun size(): Int {
		return tiles.size;
	}
}
