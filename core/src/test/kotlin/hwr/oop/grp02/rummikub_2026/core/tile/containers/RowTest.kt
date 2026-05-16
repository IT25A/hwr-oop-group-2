package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach

class RowTest {
lateinit var diffNumSameColorDeck: Row
lateinit var sameNumDiffColorDeck: Row

	val blueOne = Tile(TileNumber.One, TileColor.Blue)
	val blueTwo = Tile(TileNumber.Two, TileColor.Blue)
	
	val blueThree = Tile(TileNumber.Three, TileColor.Blue)
	val blueFour = Tile(TileNumber.Four, TileColor.Blue)
	val blueFive = Tile(TileNumber.Five, TileColor.Blue)
	
	val blueSix = Tile(TileNumber.Six, TileColor.Blue)
	
	val redThree = Tile(TileNumber.Three, TileColor.Red)
	val blackThree = Tile(TileNumber.Three, TileColor.Black)
	val orangeThree = Tile(TileNumber.Three, TileColor.Orange)
	
	val blueRow = listOf(blueThree, blueOne, blueTwo)
	val threeRow = listOf(blueThree, blackThree, redThree, orangeThree)
	
	
	@BeforeEach
	fun setUp() {
		val tileSet = TileSet()
		diffNumSameColorDeck = Row(RowType.DiffNumberSameColor, tileSet)
		sameNumDiffColorDeck = Row(RowType.SameNumberDiffColor, tileSet)
		
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when fewer than 3 tiles`() {
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.add(blueTwo)
		
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when tiles are not a sequence`() {
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.add(blueTwo)
		diffNumSameColorDeck.add(blueFour)
		diffNumSameColorDeck.add(blueSix)
		
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when double tiles in sequence`() {
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.add(blueTwo)
		diffNumSameColorDeck.add(blueThree)
		
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when triple tiles in sequence`() {
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.add(blueTwo)
		
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when tiles have different colors`() {
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.add(blueTwo)
		diffNumSameColorDeck.add(redThree)
		
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when removing a tile and rowSize below 3 tiles`() {
		blueRow.forEach { diffNumSameColorDeck.add(it) }
		diffNumSameColorDeck.remove(blueOne)
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `SameNumDiffColor validate returns false when fewer than 3 tiles`() {
		sameNumDiffColorDeck.add(redThree)
		sameNumDiffColorDeck.add(blueThree)
		
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `validate returns false when tiles color is double` () {
		sameNumDiffColorDeck.add(redThree)
		sameNumDiffColorDeck.add(blueThree)
		sameNumDiffColorDeck.add(blueThree)
		sameNumDiffColorDeck.add(orangeThree)
		
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `SameNumDiffColor validate returns false when triple tiles in sequence`() {
		sameNumDiffColorDeck.add(orangeThree)
		sameNumDiffColorDeck.add(orangeThree)
		sameNumDiffColorDeck.add(orangeThree)
		sameNumDiffColorDeck.add(blackThree)
		
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `SameNumDiffColor validate returns false when tiles have different numbers`() {
		sameNumDiffColorDeck.add(orangeThree)
		sameNumDiffColorDeck.add(blueFour)
		sameNumDiffColorDeck.add(redThree)
		sameNumDiffColorDeck.add(blackThree)
		
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `Empty DiffNumSameColor_Deck is always false`(){
		assertThat(diffNumSameColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `Empty SameNumDiffColor is always false`(){
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `removing one tile from 4-tile valid DiffNumberSameColor row keeps it valid`() {
		val blueFour = Tile(TileNumber.Four, TileColor.Blue)
		diffNumSameColorDeck.add(blueOne)
		diffNumSameColorDeck.add(blueTwo)
		diffNumSameColorDeck.add(blueThree)
		diffNumSameColorDeck.add(blueFour)
		
		diffNumSameColorDeck.remove(blueFour)
		
		assertThat(diffNumSameColorDeck.validate()).isTrue()
	}
	
	@Test
	fun `DiffNumSameColor returns false when more than four tiles`() {
		threeRow.forEach { sameNumDiffColorDeck.add(it) }
		sameNumDiffColorDeck.add(blueThree)
		
		assertThat(sameNumDiffColorDeck.validate()).isFalse()
	}
	
	@Test
	fun `add and sort by RowType`() {
		diffNumSameColorDeck.add(blueSix)
		diffNumSameColorDeck.add(blueFive)
		diffNumSameColorDeck.add(blueFour)
		diffNumSameColorDeck.add(blueThree)
		diffNumSameColorDeck.add(blueTwo)
		diffNumSameColorDeck.add(blueOne)
		
		assertThat(diffNumSameColorDeck.validate()).isTrue()
	}
	
	@Test
	fun `validate returns true for legal DiffNumSameColor`() {
		threeRow.forEach { sameNumDiffColorDeck.add(it) }
		
		assertThat(sameNumDiffColorDeck.validate()).isTrue()
	}
	
	@Test
	fun `validate returns true for combination of add and removes`(){
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

