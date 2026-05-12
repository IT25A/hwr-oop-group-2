package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

enum class PlayerDeckSortingMode {
	ByColor,
	ByNumber
}

class PlayerDeck(
	private val container: TilesContainer = TilesContainer(),
	private var mode: PlayerDeckSortingMode = PlayerDeckSortingMode.ByNumber,
) {
	fun tiles() = container.tiles()
	
	fun give(tile: Tile) {
		container.add(tile);
		sort()
	}
	
	fun give(tiles: List<Tile>) {
		container.add(tiles);
	}
	
	fun play(tile: Tile) {
		container.remove(tile)
	}
	
	fun sort() {
		if (mode === PlayerDeckSortingMode.ByNumber) {
			container.sortByNumber()
		} else {
			container.sortByColor()
		}
	}
	
	fun setMode(mode: PlayerDeckSortingMode) {
		this.mode = mode;
	}
}
