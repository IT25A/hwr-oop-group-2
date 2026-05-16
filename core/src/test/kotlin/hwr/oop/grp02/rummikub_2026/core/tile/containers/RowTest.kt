package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach

class RowTest {
lateinit var DiffNumSameColor_Deck: Row
lateinit var SameNumDiffColor_Deck: Row

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
		DiffNumSameColor_Deck = Row(RowType.DiffNumberSameColor, tileSet)
		SameNumDiffColor_Deck = Row(RowType.SameNumberDiffColor, tileSet)
		
	}
	
	
	/*
	@Test
	fun `add and sort by RowType`() {
	  deck.add(blueSix)
		deck.add(blueFive)
		deck.add(blueFour)
		deck.add(blueThree)
		deck.add(blueTwo)
		deck.add(blueOne)
		
		val correctSet = TileSet.byList(listOf(blueOne, blueTwo, blueThree, blueFour, blueFive, blueSix))
		val currentSet = deck.tileSet.tiles()
		//correctSet.add(listOf(blueOne, blueTwo, blueThree, blueFour, blueFive, blueSix))
		assertThat(deck.tiles()).isEqualTo(correctSet.tiles())
	} */
	
	// --- DiffNumberSameColor ---
	
	
	@Test
	fun `DiffNumSameColor validate returns false when fewer than 3 tiles`() {
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.add(blueTwo)
		
		assertThat(DiffNumSameColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when tiles are not a sequence`() {
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.add(blueTwo)
		DiffNumSameColor_Deck.add(blueFour)
		DiffNumSameColor_Deck.add(blueSix)
		
		assertThat(DiffNumSameColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when double tiles in sequence`() {
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.add(blueTwo)
		DiffNumSameColor_Deck.add(blueThree)
		
		assertThat(DiffNumSameColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when triple tiles in sequence`() {
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.add(blueTwo)
		
		assertThat(DiffNumSameColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when tiles have different colors`() {
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.add(blueTwo)
		DiffNumSameColor_Deck.add(redThree)
		
		assertThat(DiffNumSameColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumSameColor validate returns false when removing a tile and rowSize below 3 tiles`() {
		blueRow.forEach { DiffNumSameColor_Deck.add(it) }
		DiffNumSameColor_Deck.remove(blueOne)
		assertThat(DiffNumSameColor_Deck.validate()).isFalse()
	}
	
	
	// --- SameNumberDiffColor ---
	
	@Test
	fun `SameNumDiffColor validate returns false when fewer than 3 tiles`() {
		SameNumDiffColor_Deck.add(redThree)
		SameNumDiffColor_Deck.add(blueThree)
		
		assertThat(SameNumDiffColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `validate returns false when tiles color is double` () {
		SameNumDiffColor_Deck.add(redThree)
		SameNumDiffColor_Deck.add(blueThree)
		SameNumDiffColor_Deck.add(blueThree)
		SameNumDiffColor_Deck.add(orangeThree)
		
		assertThat(SameNumDiffColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `SameNumDiffColor validate returns false when triple tiles in sequence`() {
		SameNumDiffColor_Deck.add(orangeThree)
		SameNumDiffColor_Deck.add(orangeThree)
		SameNumDiffColor_Deck.add(orangeThree)
		SameNumDiffColor_Deck.add(blackThree)
		
		assertThat(SameNumDiffColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `SameNumDiffColor validate returns false when tiles have different numbers`() {
		SameNumDiffColor_Deck.add(orangeThree)
		SameNumDiffColor_Deck.add(blueFour)
		SameNumDiffColor_Deck.add(redThree)
		SameNumDiffColor_Deck.add(blackThree)
		
		assertThat(SameNumDiffColor_Deck.validate()).isFalse()
	}
	
	//--Functionalities
	
	@Test
	fun `Empty DiffNumSameColor_Deck is always false`(){
		assertThat(DiffNumSameColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `Empty SameNumDiffColor is always false`(){
		assertThat(SameNumDiffColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `removing one tile from 4-tile valid DiffNumberSameColor row keeps it valid`() {
		val blueFour = Tile(TileNumber.Four, TileColor.Blue)
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.add(blueTwo)
		DiffNumSameColor_Deck.add(blueThree)
		DiffNumSameColor_Deck.add(blueFour)
		
		DiffNumSameColor_Deck.remove(blueFour)
		
		assertThat(DiffNumSameColor_Deck.validate()).isTrue()
	}
	
	@Test
	fun `DiffNumSameColor returns false when more than four tiles`() {
		threeRow.forEach { SameNumDiffColor_Deck.add(it) }
		SameNumDiffColor_Deck.add(blueThree)
		
		assertThat(SameNumDiffColor_Deck.validate()).isFalse()
	}
	
	@Test
	fun `add and sort by RowType`() {
		DiffNumSameColor_Deck.add(blueSix)
		DiffNumSameColor_Deck.add(blueFive)
		DiffNumSameColor_Deck.add(blueFour)
		DiffNumSameColor_Deck.add(blueThree)
		DiffNumSameColor_Deck.add(blueTwo)
		DiffNumSameColor_Deck.add(blueOne)
		
		assertThat(DiffNumSameColor_Deck.validate()).isTrue()
	}
	
	@Test
	fun `validate returns true for legal DiffNumSameColor`() {
		threeRow.forEach { SameNumDiffColor_Deck.add(it) }
		
		assertThat(SameNumDiffColor_Deck.validate()).isTrue()
	}
	
	@Test
	fun `validate returns true for combination of add and removes`(){
		DiffNumSameColor_Deck.add(blueThree)
		DiffNumSameColor_Deck.add(redThree)
		DiffNumSameColor_Deck.add(blueOne)
		DiffNumSameColor_Deck.remove(blueThree)
		DiffNumSameColor_Deck.add(blueFour)
		DiffNumSameColor_Deck.remove(blueOne)
		DiffNumSameColor_Deck.remove(redThree)
		DiffNumSameColor_Deck.add(blueFive)
		DiffNumSameColor_Deck.add(blueSix)
		
		assertThat(DiffNumSameColor_Deck.validate()).isTrue()
		
	}
}

