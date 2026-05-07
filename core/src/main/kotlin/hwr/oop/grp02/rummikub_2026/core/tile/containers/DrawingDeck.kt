package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

class DrawingDeck : TilesContainer() {
	
	/**
	 * Randomizes the drawing deck by shuffling all tiles
	 */
	fun randomize() {
		tiles.shuffle();
	}
	
	/**
	 * Draws one tile and returns it
	 * @return the drawn tile
	 * @exception NoSuchElementException if DrawingDeck is empty
	 */
	fun draw(): Tile {
		return tiles.removeFirst();
	}
	
}
