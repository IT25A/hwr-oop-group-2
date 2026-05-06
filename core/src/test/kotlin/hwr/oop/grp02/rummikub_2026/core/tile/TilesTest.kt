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
	fun `value has all 13 possible values`() {
		val entries = TileValue.entries
		
		assertThat(entries).containsExactlyInAnyOrder(
			TileValue.One,
			TileValue.Two,
			TileValue.Three,
			TileValue.Four,
			TileValue.Five,
			TileValue.Six,
			TileValue.Seven,
			TileValue.Eight,
			TileValue.Nine,
			TileValue.Ten,
			TileValue.Eleven,
			TileValue.Twelve,
			TileValue.Thirteen,
		)
	}
	
	@Test
	fun `correct int mapping of TileValue`() {
		val entries = TileValue.entries
		val numList = (1..13).toList()
		val values = entries.map { it.value() }
		assertThat(values).containsExactlyElementsOf(numList)
	}
	
	companion object {
		@JvmStatic
		fun allCombinations(): List<Array<Any>> =
			TileColor.entries.flatMap { color ->
				TileValue.entries.map { value -> arrayOf(value, color) }
			}
	}
	
	@ParameterizedTest
	@MethodSource("allCombinations")
	fun `all possible valid tile combinations are tiles`(value: TileValue, color: TileColor) {
		val tile = Tile(value, color)
		assertThat(tile.value()).isEqualTo(value)
		assertThat(tile.color()).isEqualTo(color)
	}
	
	@Test
	fun `same tiles have same hashCode and are equal`() {
		val tile1 = Tile(TileValue.One, TileColor.Blue)
		val tile2 = Tile(TileValue.One, TileColor.Blue)
		val tile3 = Tile(TileValue.Two, TileColor.Blue)
		
		assertThat(tile1).isEqualTo(tile2)
		
		assertThat(tile1.hashCode()).isEqualTo(tile2.hashCode())
		
		assertThat(tile1).isNotEqualTo(tile3)
	}
	
}