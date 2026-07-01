package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.RegularTile
import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.containers.GroupType.DiffNumberSameColor

data class Group(private val type: GroupType, private val tiles: List<Tile> = listOf()) {
	fun size() = tiles.size
	
	fun validate(): Boolean {
		val content = tiles
		if (content.size < 3) {
			return false
		}
		
		return if (type == DiffNumberSameColor) {
			validSameColor()
		} else {
			validSameNumber()
		}
	}
	
	fun tiles() = tiles.toList()
	
	fun totalPointValue(): Int {
		return when (type) {
			DiffNumberSameColor -> {
				val regularTiles = tiles.filterIsInstance<RegularTile>()
				if (regularTiles.isEmpty()) return 0
				val firstRegularIndex = tiles.indexOfFirst { it is RegularTile }
				val startValue = regularTiles.first().number().value() - firstRegularIndex
				(0 until tiles.size).sumOf { startValue + it }
			}
			
			GroupType.SameNumberDiffColor -> {
				val regularTiles = tiles.filterIsInstance<RegularTile>()
				if (regularTiles.isEmpty()) return 0
				regularTiles.first().number().value() * tiles.size
			}
		}
	}
	
	private fun validSameColor(): Boolean {
		
		val regularTiles = tiles.filterIsInstance<RegularTile>()
		
		if (regularTiles.any { it.color() != regularTiles.first().color() }) return false
		
		val firstRegularIndex = tiles.indexOfFirst { it is RegularTile }
		val startValue = regularTiles.first().number().value() - firstRegularIndex
		
		if (startValue < 1 || startValue + tiles.size - 1 > 13) return false
		
		for ((index, tile) in tiles.withIndex()) {
			if (tile is RegularTile) {
				if (tile.number().value() != startValue + index) {
					return false
				}
			}
		}
		
		return true;
	}
	
	private fun validSameNumber(): Boolean {
		if (tiles.size > 4) {
			return false
		}
		
		val regularTiles = tiles.filterIsInstance<RegularTile>()
		
		if (regularTiles.any { it.number() != regularTiles.first().number() }) return false
		
		val colors = regularTiles.map { it.color() }
		return colors.distinct().size == colors.size
	}
}
