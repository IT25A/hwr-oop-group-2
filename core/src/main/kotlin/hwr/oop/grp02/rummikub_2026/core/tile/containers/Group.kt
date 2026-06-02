package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.containers.GroupType.*

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
	
	private fun validSameColor(): Boolean {
		for (tile in tiles) {
			if (tile.color() != tiles.first().color()) {
				return false
			}
		}
		
		return !tiles.zipWithNext { a, b ->
			b.number().value() == a.number().value() + 1
		}.contains(false)
	}
	
	private fun validSameNumber(): Boolean {
		if (tiles.size > 4) {
			return false
		}
		
		for (tile in tiles) {
			if (tile.number() != tiles.first().number()) {
				return false
			}
		}
		
		val colors = tiles.map { it.color() }
		return colors.distinct().size == tiles.size
	}
}
