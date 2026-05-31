package hwr.oop.grp02.rummikub_2026.core.player

import hwr.oop.grp02.rummikub_2026.core.IllegalMoveException
import hwr.oop.grp02.rummikub_2026.core.MoveTarget
import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.containers.TileSet

class Player(private val name: String, private val container: TileSet = TileSet()) : MoveTarget() {
	
	fun rack() = container.tiles()
	
	fun name() = name
	
	override fun add(tile: Tile) {
		container.add(tile)
	}
	
	override fun remove(tile: Tile): Boolean {
		return container.remove(tile)
	}
	
	fun sortByNumber() {
		container.sortByNumber()
	}
	
	fun sortByColor() {
		container.sortByColor()
	}
}