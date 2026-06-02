package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GroupTest {
	
	val blueOne = Tile(TileNumber.One, TileColor.Blue)
	val blueTwo = Tile(TileNumber.Two, TileColor.Blue)
	
	val blueThree = Tile(TileNumber.Three, TileColor.Blue)
	val blueFour = Tile(TileNumber.Four, TileColor.Blue)
	
	val blueSix = Tile(TileNumber.Six, TileColor.Blue)
	
	val redThree = Tile(TileNumber.Three, TileColor.Red)
	val blackThree = Tile(TileNumber.Three, TileColor.Black)
	val orangeThree = Tile(TileNumber.Three, TileColor.Orange)
	
	@Test
	fun `group size is correct`() {
		val group = Group(GroupType.SameNumberDiffColor, listOf(blueOne, blueThree, orangeThree))
		assertThat(group.size()).isEqualTo(3)
	}
	
	@Test
	fun `DiffNumberSameColor validate returns false when fewer than 3 tiles`() {
		val list = listOf(blueOne, blueTwo)
		val diffNumSameColorRow = Group(GroupType.DiffNumberSameColor, list)
		
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumberSameColor validate returns false when tiles are not a sequence`() {
		val list = listOf(blueOne, blueTwo, blueFour, blueSix)
		val diffNumSameColorRow = Group(GroupType.DiffNumberSameColor, list)
		
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumberSameColor validate returns false when double tiles in sequence`() {
		val list = listOf(blueOne, blueTwo, blueOne, blueThree)
		val diffNumSameColorRow = Group(GroupType.DiffNumberSameColor, list)
		
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumberSameColor validate returns false when tiles have different colors`() {
		val list = listOf(blueOne, blueTwo, redThree)
		val diffNumSameColorRow = Group(GroupType.DiffNumberSameColor, list)
		
		assertThat(diffNumSameColorRow.validate()).isFalse()
	}
	
	@Test
	fun `SameNumberDiffColor validate returns false when fewer than 3 tiles`() {
		val list = listOf(redThree, blueThree)
		val sameNumDiffColorRow = Group(GroupType.SameNumberDiffColor, list)
		
		assertThat(sameNumDiffColorRow.validate()).isFalse()
	}
	
	@Test
	fun `SameNumberDiffColor validate returns false when there are more than four tiles`() {
		val list = listOf(redThree, blueThree, blueThree, orangeThree, redThree)
		val sameNumDiffColorRow = Group(GroupType.SameNumberDiffColor, list)
		
		assertThat(sameNumDiffColorRow.validate()).isFalse()
	}
	
	@Test
	fun `SameNumberDiffColor validate returns false when tiles color is double`() {
		val list = listOf(redThree, blueThree, blueThree, orangeThree)
		val sameNumDiffColorRow = Group(GroupType.SameNumberDiffColor, list)
		
		assertThat(sameNumDiffColorRow.validate()).isFalse()
	}
	
	@Test
	fun `SameNumberDiffColor validate returns false when tiles have different numbers`() {
		val list = listOf(redThree, blueFour, blackThree, orangeThree)
		val sameNumDiffColorRow = Group(GroupType.SameNumberDiffColor, list)
		
		assertThat(sameNumDiffColorRow.validate()).isFalse()
	}
	
	@Test
	fun `SameNumberDiffColor validate returns true for legal DiffNumSameColor`() {
		val list = listOf(redThree, blueThree, blackThree, orangeThree)
		val sameNumDiffColorRow = Group(GroupType.SameNumberDiffColor, list)
		
		assertThat(sameNumDiffColorRow.validate()).isTrue()
	}
	
}

