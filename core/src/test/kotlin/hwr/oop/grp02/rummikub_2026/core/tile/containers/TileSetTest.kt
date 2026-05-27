package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class TileSetTest {
	val tile1 = Tile(TileNumber.One, TileColor.Blue)
	val tile2 = Tile(TileNumber.Two, TileColor.Red)
	val tile3 = Tile(TileNumber.Three, TileColor.Orange)
	
	@Test
	fun `tiles list is empty on initialisation`() {
		val tileSet = TileSet()
		
		assertThat(tileSet.tiles()).isEmpty()
	}
	
	@Test
	fun `adds a tile to the list`() {
		val tileSet = TileSet()
		
		tileSet.add(tile1)
		
		assertThat(tileSet.tiles()).containsExactly(tile1)
	}
	
	@Test
	fun `adds multiple tile to the list in correct order`() {
		val container = TileSet()
		
		container.add(listOf(tile1, tile2, tile3))
		
		assertThat(container.tiles()).containsExactlyElementsOf(listOf(tile1, tile2, tile3))
	}
	
	@Test
	fun `removes a tile from the list`() {
		val tileSet = TileSet(mutableListOf(tile1))
		
		val result = tileSet.remove(tile1)
		
		assertThat(tileSet.tiles()).isEmpty()
		assertThat(result).isTrue()
	}
	
	@Test
	fun `remove first does remove first tile and returns it`() {
		val tileSet = TileSet(mutableListOf(tile1, tile2))
		
		val result = tileSet.removeFirst()
		
		assertThat(tileSet.tiles()).containsExactly(tile2)
		assertThat(result).isEqualTo(tile1)
	}
	
	@Test
	fun `remove first on empty list throws exception`() {
		val tileSet = TileSet()
		assertThatThrownBy { tileSet.removeFirst() }.isInstanceOf(NoSuchElementException::class.java)
	}
	
	@Test
	fun `order of tiles is preserved`() {
		val tileSet = TileSet(mutableListOf(tile1, tile2, tile3))
		
		val contentOfTiles = tileSet.tiles()
		
		assertThat(contentOfTiles).isEqualTo(listOf(tile1, tile2, tile3))
	}
	
	@Test
	fun `has correct size`() {
		val tileSet = TileSet(mutableListOf(tile1, tile2, tile3))
		
		assertThat(tileSet.size()).isEqualTo(3)
	}
	
	@Test
	fun `by-number - correct sorting`() {
		val tileSet = TileSet(mutableListOf(tile2, tile1, tile3))
		
		tileSet.sortByNumber()
		
		assertThat(tileSet.tiles()).isEqualTo(listOf(tile1, tile2, tile3))
	}
	
	@Test
	fun `by-color - correct sorting`() {
		val tileSet = TileSet(mutableListOf(tile2, tile1, tile3))
		
		tileSet.sortByColor()
		
		assertThat(tileSet.tiles()).isEqualTo(listOf(tile1, tile3, tile2))
	}
	
	@Test
	fun `by-number - empty list remains empty on sort`() {
		val tileSet = TileSet()
		
		tileSet.sortByNumber()
		
		assertThat(tileSet.tiles()).isEmpty()
	}
	
	@Test
	fun `by-color - empty list remains empty on sort`() {
		val tileSet = TileSet()
		
		tileSet.sortByColor()
		
		assertThat(tileSet.tiles()).isEmpty()
	}
	
	@Test
	fun `by-number - sorted list remains sorted`() {
		val tileSet = TileSet(mutableListOf(tile1, tile2, tile3))
		
		tileSet.sortByNumber()
		
		assertThat(tileSet.tiles()).isEqualTo(listOf(tile1, tile2, tile3))
	}
	
	@Test
	fun `by-color - sorted list remains sorted`() {
		val tileSet = TileSet(mutableListOf(tile1, tile3, tile2))
		
		tileSet.sortByColor()
		
		assertThat(tileSet.tiles()).isEqualTo(listOf(tile1, tile3, tile2))
	}
	
	@Test
	fun `remove tile from empty list`() {
		val container = TileSet()
		container.remove(tile1)
		
		assertThat(container.tiles()).isEmpty()
	}
}
