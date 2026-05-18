package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class RowTest {

	val blueOne = Tile(TileNumber.One, TileColor.Blue)
	val blueTwo = Tile(TileNumber.Two, TileColor.Blue)
	
	val blueThree = Tile(TileNumber.Three, TileColor.Blue)
	val blueFour = Tile(TileNumber.Four, TileColor.Blue)
	val blueFive = Tile(TileNumber.Five, TileColor.Blue)
	
	val blueSix = Tile(TileNumber.Six, TileColor.Blue)
	
	val redThree = Tile(TileNumber.Three, TileColor.Red)
	val blackThree = Tile(TileNumber.Three, TileColor.Black)
	val orangeThree = Tile(TileNumber.Three, TileColor.Orange)
	
	
	@Test
	fun `DiffNumSameColor validate returns false when fewer than 3 tiles`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo))
		val diffNumSameColorRow = Row(RowType.DiffNumberSameColor, tileSet)
		
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when tiles are not a sequence`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, blueFour, blueSix))
		val diffNumSameColorRow = Row(RowType.DiffNumberSameColor, tileSet)
		
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when double tiles in sequence`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, blueOne, blueThree))
		val diffNumSameColorRow = Row(RowType.DiffNumberSameColor, tileSet)
		
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when tiles have different colors`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, redThree))
		val diffNumSameColorRow = Row(RowType.DiffNumberSameColor, tileSet)
		
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when removing a tile and rowSize below 3 tiles`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, blueThree))
		val diffNumSameColorRow = Row(RowType.DiffNumberSameColor, tileSet)
		diffNumSameColorRow.remove(blueOne)
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `SameNumDiffColor validate returns false when fewer than 3 tiles`() {
		val tileSet = TileSet.byList(listOf(redThree, blueThree))
		val sameNumDiffColorRow  = Row(RowType.SameNumberDiffColor, tileSet)
		
		assertThat(sameNumDiffColorRow.validate()).isFalse()
	}
	
	@Test
	fun `validate returns false when tiles color is double` () {
		val tileSet = TileSet.byList(listOf(redThree, blueThree, blueThree, orangeThree))
		val sameNumDiffColorRow = Row(RowType.SameNumberDiffColor, tileSet)
		
		assertThat(sameNumDiffColorRow.validate()).isFalse()
	}
	
	@Test
	fun `SameNumDiffColor validate returns false when tiles have different numbers`() {
		val tileSet = TileSet.byList(listOf(redThree, blueFour, blackThree, orangeThree))
		val sameNumDiffColorRow = Row(RowType.SameNumberDiffColor, tileSet)
		
		assertThat(sameNumDiffColorRow.validate()).isFalse()
	}
	
	@Test
	fun `Empty DiffNumSameColor_Deck is always false`(){
		val tileSet = TileSet()
		val diffNumSameColorRow = Row(RowType.DiffNumberSameColor, tileSet)
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `Empty SameNumDiffColor is always false`(){
		val tileSet = TileSet()
		val sameNumDiffColorRow = Row(RowType.SameNumberDiffColor, tileSet)
		assertThat(sameNumDiffColorRow.validate()).isFalse()
	}
	
	@Test
	fun `removing one tile from 4-tile valid DiffNumberSameColor row keeps it valid`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, blueThree, blueFour))
		val diffNumSameColorRow = Row(RowType.DiffNumberSameColor, tileSet)
		diffNumSameColorRow.remove(blueFour)
		
		assertThat(diffNumSameColorRow.validate()).isTrue()
	}
	
	@Test
	fun `DiffNumSameColor returns false when more than four tiles`() {
		val tileSet = TileSet.byList(listOf(redThree, blueThree, blackThree, orangeThree))
		val sameNumDiffColorRow = Row(RowType.SameNumberDiffColor, tileSet)
		sameNumDiffColorRow.add(blueThree)
		
		assertThat(sameNumDiffColorRow.validate()).isFalse()
	}
	
	@Test
	fun `add and sort by RowType`() {
		val tileSet = TileSet()
		val diffNumSameColorRow = Row(RowType.DiffNumberSameColor, tileSet)
		diffNumSameColorRow.add(blueFour)
		diffNumSameColorRow.add(blueThree)
		diffNumSameColorRow.add(blueTwo)
		diffNumSameColorRow.add(blueOne)
		
		assertThat(diffNumSameColorRow.validate()).isTrue()
	}
	
	@Test
	fun `validate returns true for legal DiffNumSameColor`() {
		val tileSet = TileSet.byList(listOf(redThree, blueThree, blackThree, orangeThree))
		val sameNumDiffColorRow = Row(RowType.SameNumberDiffColor, tileSet)
		
		assertThat(sameNumDiffColorRow.validate()).isTrue()
	}
	
	@Test
	fun `validate returns true for combination of add and removes`(){
		val tileSet = TileSet()
		val diffNumSameColorRow = Row(RowType.DiffNumberSameColor, tileSet)
		diffNumSameColorRow.add(blueThree)
		diffNumSameColorRow.add(redThree)
		diffNumSameColorRow.add(blueOne)
		diffNumSameColorRow.remove(blueThree)
		diffNumSameColorRow.add(blueFour)
		diffNumSameColorRow.remove(blueOne)
		diffNumSameColorRow.remove(redThree)
		diffNumSameColorRow.add(blueFive)
		diffNumSameColorRow.add(blueSix)
		
		assertThat(diffNumSameColorRow.validate()).isTrue()
	}
}

