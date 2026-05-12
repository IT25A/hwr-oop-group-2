package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

enum class TileRowType {
	DiffNumberSameColor,
	SameNumberDiffColor
}

class TileRow(private val type: TileRowType, private val container: TilesContainer = TilesContainer()) {
	fun add(tile: Tile) {
		container.add(tile)
		if (type === TileRowType.DiffNumberSameColor) {
			container.sortByNumber()
		}
	}
	
	fun remove(tile: Tile) {
		container.remove(tile)
	}
	
	fun validate(): Boolean {
		val content = container.tiles()
		if (content.size < 3) {
			return false
		}
		
		return if (type === TileRowType.DiffNumberSameColor) {
			validSameColor()
		} else {
			validSameNumber()
		}
	}
	
	private fun validSameColor(): Boolean {
		val currTiles = container.tiles()
		
		for (tile in currTiles) {
			if (tile.color() != currTiles.first().color()) {
				return false
			}
		}
		
		return !currTiles.zipWithNext { a, b ->
			b.number().value() == a.number().value() + 1
		}.contains(false)
	}
	
	private fun validSameNumber(): Boolean {
		val currTiles = container.tiles()
		if (currTiles.size > 4) {
			return false
		}
		
		for (tile in currTiles) {
			if (tile.number() != currTiles.first().number()) {
				return false
			}
		}
		
		val colors = currTiles.map { it.color() }
		return colors.distinct().size == currTiles.size
	}
}
