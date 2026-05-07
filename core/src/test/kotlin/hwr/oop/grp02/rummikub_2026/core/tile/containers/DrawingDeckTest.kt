package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class DrawingDeckTest {
	
	lateinit var deck: DrawingDeck
	val tile1 = Tile(TileNumber.One, TileColor.Blue)
	val tile2 = Tile(TileNumber.Four, TileColor.Red)
	val tile3 = Tile(TileNumber.Five, TileColor.Orange)
	val tile4 = Tile(TileNumber.Six, TileColor.Red)
	
	@BeforeEach
	fun setup() {
		deck = DrawingDeck()
	}
	
	@Test
	fun `sorted list gets randomized`() {
		val input = listOf(tile1, tile2, tile3, tile4)
		deck.add(input)
		
		var randomList: List<Tile>
		var counter = 0
		
		// we need to expect that randomize could statistically return the same list,
		// therefore we try to randomize five times unless the input and the random list are not equal
		do {
			deck.randomize()
			randomList = deck.tiles()
			counter++
		} while (counter < 5 && randomList == input)
		
		assertThat(randomList).isNotEqualTo(input)
	}
	
	@Test
	fun `drawing a tile from DrawingDeck`() {
		val input = listOf(tile1, tile2, tile3, tile4)
		deck.add(input)
		val drawnTile = deck.draw()
		assertThat(deck.tiles()).isEqualTo(listOf(tile2, tile3, tile4))
		assertThat(drawnTile).isEqualTo(tile1)
		assertThat(deck.tiles()).doesNotContain(drawnTile)
	}
	
	@Test
	fun `drawing a tile from an empty deck throws exception`() {
		assertThatThrownBy { deck.draw() }.isInstanceOf(NoSuchElementException::class.java)
	}
}

