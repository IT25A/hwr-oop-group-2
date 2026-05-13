package hwr.oop.grp02.rummikub_2026.core.tile.containers

class DrawPile(private val tileSet: TileSet = TileSet()) {
	
	/**
	 * Draws one tile and returns it
	 * @return the drawn tile
	 * @exception NoSuchElementException if DrawingDeck is empty
	 */
	fun draw() = tileSet.removeFirst()
	
	fun tiles() = tileSet.tiles()
}
