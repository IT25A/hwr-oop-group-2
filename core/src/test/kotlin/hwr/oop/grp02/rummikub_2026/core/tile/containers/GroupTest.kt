package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.JokerTile
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
	
	@Test
	fun `group tiles returns tiles`() {
		val group1 = Group(GroupType.DiffNumberSameColor, listOf(blueOne, blueTwo, blueThree))
		val alltiles = group1.tiles()
		assertThat(alltiles).containsExactly(blueOne, blueTwo, blueThree)
	}
	
	@Test
	fun `DiffNumberSameColor validate returns true for valid sequence`() {
		val list = listOf(blueOne, blueTwo, blueThree)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.validate()).isTrue()
	}
	
	@Test
	fun `DiffNumberSameColor validate returns true with joker at start`() {
		val list = listOf(JokerTile, blueTwo, blueThree)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.validate()).isTrue()
	}
	
	@Test
	fun `DiffNumberSameColor validate returns true with joker at end`() {
		val list = listOf(blueOne, blueTwo, JokerTile)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.validate()).isTrue()
	}
	
	@Test
	fun `DiffNumberSameColor validate returns true with  2 jokers `() {
		val list = listOf(JokerTile, blueTwo, JokerTile)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.validate()).isTrue()
	}
	
	@Test
	fun `DiffNumberSameColor validate returns true with joker in middle`() {
		val list = listOf(blueOne, JokerTile, blueThree)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.validate()).isTrue()
	}
	
	@Test
	fun `DiffNumberSameColor validate returns false when sequence exceeds 13`() {
		val blueTwelve = Tile(TileNumber.Twelve, TileColor.Blue)
		val blueThirteen = Tile(TileNumber.Thirteen, TileColor.Blue)
		val list = listOf(blueTwelve, blueThirteen, JokerTile)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.validate()).isFalse()
	}
	
	@Test
	fun `DiffNumberSameColor validate returns false when joker implies start below 1`() {
		val list = listOf(JokerTile, blueOne, blueTwo)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.validate()).isFalse()
	}
	
	@Test
	fun `SameNumberDiffColor validate returns true with joker`() {
		val list = listOf(redThree, blueThree, JokerTile)
		val group = Group(GroupType.SameNumberDiffColor, list)
		assertThat(group.validate()).isTrue()
	}
	
	@Test
	fun `totalPointValue for DiffNumberSameColor without joker`() {
		val list = listOf(blueOne, blueTwo, blueThree)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.totalPointValue()).isEqualTo(6)
	}
	
	@Test
	fun `totalPointValue for DiffNumberSameColor with joker start`() {
		val list = listOf(JokerTile, blueTwo, blueThree)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.totalPointValue()).isEqualTo(6)
	}
	
	@Test
	fun `totalPointValue for DiffNumberSameColor with joker on middle`() {
		val list = listOf(blueOne, JokerTile, blueThree)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.totalPointValue()).isEqualTo(6)
	}
	
	@Test
	fun `totalPointValue for DiffNumberSameColor with joker end`() {
		val list = listOf(blueOne, blueTwo, JokerTile)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.totalPointValue()).isEqualTo(6)
	}
	
	@Test
	fun `totalPointValue for DiffNumberSameColor only joker returns 0`() {
		val list = listOf(JokerTile, JokerTile, JokerTile)
		val group = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group.totalPointValue()).isEqualTo(0)
	}
	
	@Test
	fun `totalPointValue for SameNumberDiffColor only joker returns 0`() {
		val list = listOf(JokerTile, JokerTile, JokerTile)
		val group = Group(GroupType.SameNumberDiffColor, list)
		assertThat(group.totalPointValue()).isEqualTo(0)
	}
	
	@Test
	fun `totalPointValue for SameNumberDiffColor without joker`() {
		val list = listOf(blueThree, redThree, orangeThree)
		val group = Group(GroupType.SameNumberDiffColor, list)
		assertThat(group.totalPointValue()).isEqualTo(9)
	}
	
	@Test
	fun `totalPointValue for SameNumberDiffColor with joker`() {
		val list = listOf(redThree, JokerTile, blueThree)
		val group = Group(GroupType.SameNumberDiffColor, list)
		assertThat(group.totalPointValue()).isEqualTo(9)
	}
	
	@Test
	fun `groups with same type but different tiles are not equal`() {
		val group1 = Group(GroupType.DiffNumberSameColor, listOf(blueOne, blueTwo, blueThree))
		val group2 = Group(GroupType.DiffNumberSameColor, listOf(blueTwo, blueThree, blueFour))
		assertThat(group1).isNotEqualTo(group2)
	}
	
	@Test
	fun `groups with same type and same tiles are equal`() {
		val list = listOf(blueOne, blueTwo, blueThree, blueFour)
		val group1 = Group(GroupType.DiffNumberSameColor, list)
		val group2 = Group(GroupType.DiffNumberSameColor, list)
		assertThat(group1).isEqualTo(group2)
		assertThat(group1.hashCode()).isEqualTo(group2.hashCode())
	}
	
}

