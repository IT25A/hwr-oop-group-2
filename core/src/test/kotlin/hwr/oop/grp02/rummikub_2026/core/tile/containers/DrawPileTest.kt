package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.NoSuchTileException
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
		val container = mutableListOf(tile1, tile2, tile3, tile4)
		val deck = DrawPile(container)
		
		val drawnTile = deck.draw()
		assertThat(deck.tiles()).isEqualTo(listOf(tile2, tile3, tile4))
		assertThat(drawnTile).isEqualTo(tile1)
		assertThat(deck.tiles()).doesNotContain(drawnTile)
	}
	
	@Test
	fun `drawing a tile from an empty deck throws exception`() {
		val deck = DrawPile()
		assertThatThrownBy { deck.draw() }.isInstanceOf(NoSuchTileException::class.java)
	}
	
	@Test
	fun `withAllTiles has 104 tiles`() {
		val drawPile = DrawPile.withAllTiles()
		assertThat(drawPile.tiles()).hasSize(104)
	}
	
	@Test
	fun `withAllTiles with distinct has 52 tiles`() {
		val drawPile = DrawPile.withAllTiles()
		assertThat(drawPile.tiles().distinct()).hasSize(52)
	}
	
	@Test
	fun `withAllTiles has every tile twice`() {
		val drawPile = DrawPile.withAllTiles()
		val map = drawPile.tiles().groupBy { it }
		assertThat(map.all { it.value.size == 2 }).isTrue
	}
}

