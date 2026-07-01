package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.*

internal class DrawPile(private val list: MutableList<Tile> = mutableListOf()) {
	
	companion object {
		fun withAllTiles(): DrawPile {
			val allTiles = TileColor.entries.flatMap { color ->
				TileNumber.entries.map { value -> Tile(value, color) }
			}
			return DrawPile((allTiles + allTiles + listOf(JokerTile, JokerTile)).toMutableList())
		}
	}
	
	fun draw(): Tile {
		try {
			return list.removeFirst()
		} catch (e: NoSuchElementException) {
			throw NoSuchTileException()
		}
	}
	
	fun tiles() = list.toList()
	
	fun shuffle() = list.shuffle()
}
