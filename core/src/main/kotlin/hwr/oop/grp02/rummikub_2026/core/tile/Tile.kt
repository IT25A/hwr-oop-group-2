package hwr.oop.grp02.rummikub_2026.core.tile

data class Tile(
	private val value: TileNumber,
	private val color: TileColor,
) {
	fun number(): TileNumber = value
	fun color(): TileColor = color
}