package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.junit.jupiter.api.BeforeEach

class TileRowTest {
	lateinit var deck: TileRow
	val blueOne = Tile(TileNumber.One, TileColor.Blue)
	val blueTwo = Tile(TileNumber.Two, TileColor.Blue)
	
	val blueThree = Tile(TileNumber.Three, TileColor.Blue)
	
	val redThree = Tile(TileNumber.Three, TileColor.Red)
	val blackThree = Tile(TileNumber.Three, TileColor.Black)
	val orangeThree = Tile(TileNumber.Three, TileColor.Orange)
	
	val blueRow = listOf(blueThree, blueOne, blueTwo)
	val threeRow = listOf(blueThree, blackThree, redThree, orangeThree)
	
	@BeforeEach
	fun setup() {
		TODO("Will be implemented soon")
	}
	
	// TODO - QUESTION TO STEFAN
	// will be discussed on Wednesday (abstraction / no abstraction / sort on add)
	
	/*
	@Test
	fun `validating same color`() {
		assertThat(blueRow.validate()).isTrue
		
	}
	
	 */
}

