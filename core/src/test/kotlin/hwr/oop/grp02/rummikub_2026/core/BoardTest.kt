package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.NoSuchTileException
import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import hwr.oop.grp02.rummikub_2026.core.tile.containers.Group
import hwr.oop.grp02.rummikub_2026.core.tile.containers.GroupType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BoardTest {
	val blueOne = Tile(TileNumber.One, TileColor.Blue)
	val blueTwo = Tile(TileNumber.Two, TileColor.Blue)
	val blueThree = Tile(TileNumber.Three, TileColor.Blue)
	
	val redOne = Tile(TileNumber.One, TileColor.Red)
	val orangeOne = Tile(TileNumber.One, TileColor.Orange)
	
	@Test
	fun `groups does return list`() {
		val tileSet1 = listOf(blueOne, blueTwo, blueThree)
		val tileSet2 = listOf(blueOne, redOne, orangeOne)
		
		val rows = listOf(
			Group(GroupType.DiffNumberSameColor, tileSet1),
			Group(GroupType.SameNumberDiffColor, tileSet2)
		)
		
		val board = Board(rows)
		
		assertThat(board.groups()).isEqualTo(rows)
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
	fun `validation should pass if there are no groups`() {
		val board = Board()
		assertThat(board.validate()).isTrue()
	}
	
	@Test
	fun `board tiles returns tiles`() {
		val group1 = Group(GroupType.DiffNumberSameColor,listOf(blueOne, blueTwo, blueThree))
		val group2 = Group(GroupType.SameNumberDiffColor,listOf(blueOne, orangeOne, redOne))
		val board = Board(listOf(group1, group2))
		val alltiles = board.tiles()
		assertThat(alltiles).containsExactly(blueOne, blueTwo, blueThree, blueOne, orangeOne, redOne)
	}
	
	@Test
	fun `subtractTiles subtracts tile list`() {
		val group1 = Group(GroupType.DiffNumberSameColor,listOf(blueOne, blueTwo, blueThree))
		val board = Board(listOf(group1))
		val listoftiles = listOf(blueOne, blueTwo, blueThree)
		val alltiles = board.subtractTiles(listoftiles)
		assertThat(alltiles).isEmpty()
	}
	
	@Test
	fun `subtractTiles does not change during removing nothing`() {
		val group1 = Group(GroupType.DiffNumberSameColor,listOf(blueOne, blueTwo, blueThree))
		val board = Board(listOf(group1))
		val listoftiles = emptyList<Tile>()
		val alltiles = board.subtractTiles(listoftiles)
		assertThat(alltiles).containsExactly(blueOne, blueTwo, blueThree)
	}
	
	@Test
	fun `Throws an exception when removing non existing tile`() {
		val group1 = Group(GroupType.DiffNumberSameColor,listOf(blueOne, blueTwo, blueThree))
		val board = Board(listOf(group1))
		val listoftiles = listOf<Tile>(orangeOne)
		assertThatThrownBy{ board.subtractTiles(listoftiles) }.isInstanceOf(NoSuchTileException::class.java)
	}
	
	@Test
	fun `subtractTiles recognises duplicates`() {
		val group1 = Group(GroupType.DiffNumberSameColor,listOf(blueOne, blueOne, blueTwo, blueThree))
		val board = Board(listOf(group1))
		val listoftiles = listOf(blueOne, blueTwo, blueThree)
		val alltiles = board.subtractTiles(listoftiles)
		assertThat(alltiles).containsExactly(blueOne)
	}
}