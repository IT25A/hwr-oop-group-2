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
		val diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when tiles are not a sequence`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, blueFour, blueSix))
		val diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when double tiles in sequence`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, blueOne, blueThree))
		val diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when tiles have different colors`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, redThree))
		val diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when removing a tile and rowSize below 3 tiles`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, blueThree))
		val diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		diffNumSameColorDeck.remove(blueOne)
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `SameNumDiffColor validate returns false when fewer than 3 tiles`() {
		val tileSet = TileSet.byList(listOf(redThree, blueThree))
		val sameNumDiffColorDeck  = Row(RowType.SameNumberDiffColor, tileSet)
		
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `validate returns false when tiles color is double` () {
		val tileSet = TileSet.byList(listOf(redThree, blueThree, blueThree, orangeThree))
		val sameNumDiffColorDeck = Row(RowType.SameNumberDiffColor, tileSet)
		
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `SameNumDiffColor validate returns false when tiles have different numbers`() {
		val tileSet = TileSet.byList(listOf(redThree, blueFour, blackThree, orangeThree))
		val sameNumDiffColorDeck = Row(RowType.SameNumberDiffColor, tileSet)
		
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `Empty DiffNumSameColor_Deck is always false`(){
		val tileSet = TileSet()
		val diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `Empty SameNumDiffColor is always false`(){
		val tileSet = TileSet()
		val sameNumDiffColorDeck = Row(RowType.SameNumberDiffColor, tileSet)
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `removing one tile from 4-tile valid DiffNumberSameColor row keeps it valid`() {
		val tileSet = TileSet.byList(listOf(blueOne, blueTwo, blueThree, blueFour))
		val diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		diffNumSameColorDeck.remove(blueFour)
		
		assertThat(diffNumSameColorDeck.validate()).isTrue()
	}
	
	@Test
	fun `DiffNumSameColor returns false when more than four tiles`() {
		val tileSet = TileSet.byList(listOf(redThree, blueThree, blackThree, orangeThree))
		val sameNumDiffColorDeck = Row(RowType.SameNumberDiffColor, tileSet)
		sameNumDiffColorDeck.add(blueThree)
		
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `add and sort by RowType`() {
		val tileSet = TileSet()
		val diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		diffNumSameColorDeck.add(blueFour)
		diffNumSameColorDeck.add(blueThree)
		diffNumSameColorDeck.add(blueTwo)
		diffNumSameColorDeck.add(blueOne)
		
		assertThat(diffNumSameColorDeck.validate()).isTrue()
	}
	
	@Test
	fun `validate returns true for legal DiffNumSameColor`() {
		val tileSet = TileSet.byList(listOf(redThree, blueThree, blackThree, orangeThree))
		val sameNumDiffColorDeck = Row(RowType.SameNumberDiffColor, tileSet)
		
		assertThat(sameNumDiffColorDeck.validate()).isTrue()
	}
	
	@Test
	fun `validate returns true for combination of add and removes`(){
		val tileSet = TileSet()
		val diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		diffNumSameColorDeck.add(blueThree)
		diffNumSameColorDeck.add(redThree)
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.remove(blueThree)
		diffNumSameColorDeck.add(blueFour)
		diffNumSameColorDeck.remove(blueOne)
		diffNumSameColorDeck.remove(redThree)
		diffNumSameColorDeck.add(blueFive)
		diffNumSameColorDeck.add(blueSix)
		
		assertThat(diffNumSameColorDeck.validate()).isTrue()
	}
}

