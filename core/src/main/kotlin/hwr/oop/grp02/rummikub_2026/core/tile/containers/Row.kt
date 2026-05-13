package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

enum class RowType {
	DiffNumberSameColor,
	SameNumberDiffColor
}

class Row(private val type: RowType, private val tileSet: TileSet = TileSet()) {
	fun add(tile: Tile) {
		tileSet.add(tile)
		if (type == RowType.DiffNumberSameColor) {
			tileSet.sortByNumber()
		}
	}
	
	fun remove(tile: Tile) {
		tileSet.remove(tile)
	}
	
	fun validate(): Boolean {
		val content = tileSet.tiles()
		if (content.size < 3) {
			return false
		}
		
		return if (type == RowType.DiffNumberSameColor) {
			validSameColor()
		} else {
			validSameNumber()
		}
	}
	
	private fun validSameColor(): Boolean {
		val currTiles = tileSet.tiles()
		
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
		val currTiles = tileSet.tiles()
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
