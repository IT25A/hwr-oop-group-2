package hwr.oop.grp02.rummikub_2026.core.tile.containers

class DrawingDeck(private val container: TilesContainer = TilesContainer()) {
	
	companion object {
		fun fullDeck() {
			TODO("Implement init with all possible tiles")
		}
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
