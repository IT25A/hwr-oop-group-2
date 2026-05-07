package hwr.oop.grp02.rummikub_2026.core.tile

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TilesTest {
	
	@Test
	fun `color has all four possible values`() {
		val entries = TileColor.entries
		assertThat(entries).containsExactlyInAnyOrder(
			TileColor.Red,
			TileColor.Black,
			TileColor.Blue,
			TileColor.Orange
		)
	}
	
	@Test
	fun `number has all 13 possible values`() {
		val entries = TileNumber.entries
		
		assertThat(entries).containsExactlyInAnyOrder(
			TileNumber.One,
			TileNumber.Two,
			TileNumber.Three,
			TileNumber.Four,
			TileNumber.Five,
			TileNumber.Six,
			TileNumber.Seven,
			TileNumber.Eight,
			TileNumber.Nine,
			TileNumber.Ten,
			TileNumber.Eleven,
			TileNumber.Twelve,
			TileNumber.Thirteen,
		)
	}
	
	@Test
	fun `correct int mapping of TileNumber`() {
		val entries = TileNumber.entries
		val numList = (1..13).toList()
		val values = entries.map { it.value() }
		assertThat(values).containsExactlyElementsOf(numList)
	}
	
	companion object {
		@JvmStatic
		fun allCombinations(): List<Array<Any>> =
			TileColor.entries.flatMap { color ->
				TileNumber.entries.map { value -> arrayOf(value, color) }
			}
	}
	
	@ParameterizedTest
	@MethodSource("allCombinations")
	fun `all possible valid tile combinations are tiles`(value: TileNumber, color: TileColor) {
		val tile = Tile(value, color)
		assertThat(tile.number()).isEqualTo(value)
		assertThat(tile.color()).isEqualTo(color)
	}
	
	@Test
	fun `same tiles have same hashCode and are equal`() {
		val tile1 = Tile(TileNumber.One, TileColor.Blue)
		val tile2 = Tile(TileNumber.One, TileColor.Blue)
		val tile3 = Tile(TileNumber.Two, TileColor.Blue)
		
		assertThat(tile1).isEqualTo(tile2)
		
		assertThat(tile1.hashCode()).isEqualTo(tile2.hashCode())
		
		assertThat(tile1).isNotEqualTo(tile3)
	}
	
}