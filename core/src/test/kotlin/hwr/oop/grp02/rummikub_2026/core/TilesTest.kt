package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

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
	
	@ParameterizedTest
	@EnumSource(TileValue::class)
	fun `blue tiles accept all values`(value: TileValue) {
		val tile = Tile(value, TileColor.Blue)
		assertThat(tile.value()).isEqualTo(value)
	}
	
	@ParameterizedTest
	@EnumSource(TileValue::class)
	fun `red tiles accept all values`(value: TileValue) {
		val tile = Tile(value, TileColor.Red)
		assertThat(tile.value()).isEqualTo(value)
	}
	
	@ParameterizedTest
	@EnumSource(TileValue::class)
	fun `black tiles accept all values`(value: TileValue) {
		val tile = Tile(value, TileColor.Black)
		assertThat(tile.value()).isEqualTo(value)
	}

	// Dies testet alle
	@Test
	fun `all possible valid tile combinations are unique`() {
		val colors = TileColor.entries
		val values = TileValue.entries
		val allCombinations = colors.flatMap { color ->
			values.map { value -> Tile(value, color) }
		}
		val expectedCombinations = colors.flatMap { color ->
			values.map { value -> Pair(value, color) }
		}
		assertThat(allCombinations).doesNotHaveDuplicates()

		assertThat(allCombinations).hasSize(expectedCombinations.size)

		assertThat(allCombinations.map { Pair(it.value(), it.color()) })
			.containsExactlyInAnyOrderElementsOf(expectedCombinations)
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

	@ParameterizedTest
	@EnumSource(TileValue::class)
	fun `orange tiles accept all values`(value: TileValue) {
		val tile = Tile(value, TileColor.Orange);
		assertThat(tile.value()).isEqualTo(value);
	}
	
}


