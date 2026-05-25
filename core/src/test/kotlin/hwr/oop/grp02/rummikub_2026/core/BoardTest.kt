package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import hwr.oop.grp02.rummikub_2026.core.tile.containers.Row
import hwr.oop.grp02.rummikub_2026.core.tile.containers.RowType
import hwr.oop.grp02.rummikub_2026.core.tile.containers.TileSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
	val blueOne = Tile(TileNumber.One, TileColor.Blue)
	val blueTwo = Tile(TileNumber.Two, TileColor.Blue)
	val blueThree = Tile(TileNumber.Three, TileColor.Blue)
	
	val redOne = Tile(TileNumber.One, TileColor.Red)
	val orangeOne = Tile(TileNumber.One, TileColor.Orange)
	
	@Test
	fun `newRow adds a new row to the board`() {
		val board = Board(listOf(Row(RowType.SameNumberDiffColor)))
		val newBoard = board.newRow(RowType.DiffNumberSameColor)
		assertThat(newBoard.rows().size).isEqualTo(board.rows().size + 1)
		assertThat(newBoard.rows()).containsAll(board.rows())
		assertThat(newBoard.rows()).contains(Row(RowType.DiffNumberSameColor))
	}
	
	@Test
	fun `removing empty rows changes the board`() {
		val notEmptyRow = Row(RowType.DiffNumberSameColor, TileSet(mutableListOf(blueOne)))
		val board = Board(listOf(Row(RowType.DiffNumberSameColor), notEmptyRow))
		val emptyBoard = board.removeEmptyRows()
		assertThat(emptyBoard.rows()).containsExactly(notEmptyRow)
	}
	
	@Test
	fun `validation checks every row (positive case)`() {
		val tileSet1 = TileSet(mutableListOf(blueOne, blueTwo, blueThree))
		val tileSet2 = TileSet(mutableListOf(blueOne, redOne, orangeOne))
		
		val board = Board(
			listOf(
				Row(RowType.DiffNumberSameColor, tileSet1),
				Row(RowType.SameNumberDiffColor, tileSet2)
			)
		)
		
		assertThat(board.validate()).isTrue()
	}
	
	@Test
	fun `validation checks every row (negative case)`() {
		val tileSet1 = TileSet(mutableListOf(blueOne, blueTwo, blueThree))
		val board = Board(
			listOf(
				Row(RowType.DiffNumberSameColor, tileSet1),
				Row(RowType.SameNumberDiffColor, tileSet1)
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