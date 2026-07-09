package hwr.oop.grp02.rummikub_2026.core.tile

import kotlinx.serialization.Serializable

sealed interface Tile {
	fun isJoker(): Boolean
	fun pointValue(): Int
	
	companion object {
		operator fun invoke(value: TileNumber, color: TileColor): RegularTile = RegularTile(value, color)
	}
}

@Serializable
data class RegularTile(
	private val value: TileNumber,
	private val color: TileColor,
) : Tile {
	fun number(): TileNumber = value
	fun color(): TileColor = color
	override fun isJoker(): Boolean = false
	override fun pointValue(): Int = value.value()
}

class JokerTile : Tile {
	override fun isJoker(): Boolean = true
	override fun pointValue(): Int = 50
}