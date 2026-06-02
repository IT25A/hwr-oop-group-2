package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import hwr.oop.grp02.rummikub_2026.core.tile.containers.Group
import hwr.oop.grp02.rummikub_2026.core.tile.containers.GroupType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
	val blueOne = Tile(TileNumber.One, TileColor.Blue)
	val blueTwo = Tile(TileNumber.Two, TileColor.Blue)
	val blueThree = Tile(TileNumber.Three, TileColor.Blue)
	
	val redOne = Tile(TileNumber.One, TileColor.Red)
	val orangeOne = Tile(TileNumber.One, TileColor.Orange)
	
	@Test
	fun `rows does return list`() {
		val tileSet1 = listOf(blueOne, blueTwo, blueThree)
		val tileSet2 = listOf(blueOne, redOne, orangeOne)
		
		val rows = listOf(
			Group(GroupType.DiffNumberSameColor, tileSet1),
			Group(GroupType.SameNumberDiffColor, tileSet2)
		)
		
		val board = Board(rows)
		
		assertThat(board.rows()).isEqualTo(rows)
	}
	
	@Test
	fun `validation checks every row (positive case)`() {
		val tileSet1 = listOf(blueOne, blueTwo, blueThree)
		val tileSet2 = listOf(blueOne, redOne, orangeOne)
		
		val board = Board(
			listOf(
				Group(GroupType.DiffNumberSameColor, tileSet1),
				Group(GroupType.SameNumberDiffColor, tileSet2)
			)
		)
		
		assertThat(board.validate()).isTrue()
	}
	
	@Test
	fun `validation checks every row (negative case)`() {
		val tileSet1 = listOf(blueOne, blueTwo, blueThree)
		val board = Board(
			listOf(
				Group(GroupType.DiffNumberSameColor, tileSet1),
				Group(GroupType.SameNumberDiffColor, tileSet1)
			)
		)
		assertThat(board.validate()).isFalse()
	}
	
	@Test
	fun `validation should pass if there are no rows`() {
		val board = Board()
		assertThat(board.validate()).isTrue()
	}
	
}