package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

class PlayerRack(
	private val container: TileSet = TileSet()
) {
	fun tiles() = container.tiles()
	
	fun draw(vararg tileList: Tile) {
		container.add(tileList.toList());
	}
	
	fun play(tile: Tile) {
		container.remove(tile)
	}
}
