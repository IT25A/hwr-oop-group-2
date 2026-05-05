package hwr.oop.grp02.rummikub_2026.core.tile

data class Tile(
	private val value: TileValue,
	private val color: TileColor,
) {
	fun value(): TileValue = value
	fun color(): TileColor = color
}