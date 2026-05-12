package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

class DrawingDeck(private val container: TilesContainer = TilesContainer()) {
	
	fun init() {
		TODO("Create all tiles")
	}
	
	fun randomize() = container.randomize()
	
	/**
	 * Draws one tile and returns it
	 * @return the drawn tile
	 * @exception NoSuchElementException if DrawingDeck is empty
	 */
	fun draw() = container.removeFirst()
	
	fun tiles() = container.tiles()
	
}
