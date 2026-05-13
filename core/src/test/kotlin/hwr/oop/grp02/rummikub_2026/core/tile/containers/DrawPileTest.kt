package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class DrawPileTest {
	
	val tile1 = Tile(TileNumber.One, TileColor.Blue)
	val tile2 = Tile(TileNumber.Four, TileColor.Red)
	val tile3 = Tile(TileNumber.Five, TileColor.Orange)
	val tile4 = Tile(TileNumber.Six, TileColor.Red)
	
	@Test
	fun `drawing a tile from DrawingDeck`() {
		val container = TileSet.byList(listOf(tile1, tile2, tile3, tile4))
		val deck = DrawPile(container)
		
		val drawnTile = deck.draw()
		assertThat(deck.tiles()).isEqualTo(listOf(tile2, tile3, tile4))
		assertThat(drawnTile).isEqualTo(tile1)
		assertThat(deck.tiles()).doesNotContain(drawnTile)
	}
	
	@Test
	fun `drawing a tile from an empty deck throws exception`() {
		val deck = DrawPile()
		assertThatThrownBy { deck.draw() }.isInstanceOf(NoSuchElementException::class.java)
	}
}

