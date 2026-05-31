package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

data class Move(private val from: MoveTarget, private val to: MoveTarget, private val tile: Tile) {
	
	fun from() = from
	
	fun to() = to
	
	fun tile() = tile
	
	fun move() {
		if(!from.remove(tile)) {
			throw IllegalMoveException()
		}
		to.add(tile)
	}
	
	fun validate() {
	
	}
	
}