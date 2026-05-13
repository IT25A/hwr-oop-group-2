package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class TileSetTest {
	val tile1 = Tile(TileNumber.One, TileColor.Blue)
	val tile2 = Tile(TileNumber.Two, TileColor.Red)
	val tile3 = Tile(TileNumber.Three, TileColor.Orange)
	
	@Test
	fun `tiles list is empty on initialisation`() {
		val container = TileSet()
		
		assertThat(container.tiles()).isEmpty()
	}
	
	@Test
	fun `container#add adds a tile to the list`() {
		val container = TileSet()
		
		container.add(tile1)
		
		assertThat(container.tiles()).containsExactly(tile1)
	}
	
	@Test
	fun `container#add adds multiple tile to the list in correct order`() {
		val container = TileSet()
		
		container.add(listOf(tile1, tile2, tile3))
		
		assertThat(container.tiles()).containsExactlyElementsOf(listOf(tile1, tile2, tile3))
	}
	
	@Test
	fun `container#remove removes a tile from the list`() {
		val container = TileSet.byList(listOf(tile1))
		
		container.remove(tile1)
		
		assertThat(container.tiles()).isEmpty()
	}
	
	@Test
	fun `order of tiles is preserved`() {
		val container = TileSet.byList(listOf(tile1, tile2, tile3))
		
		val contentOfContainer = container.tiles()
		
		assertThat(contentOfContainer).isEqualTo(listOf(tile1, tile2, tile3))
	}
	
	@Test
	fun `container#size has correct size`() {
		val container = TileSet.byList(listOf(tile1, tile2, tile3))
		
		assertThat(container.size()).isEqualTo(3)
	}
	
	@Test
	fun `by-number - correct sorting`() {
		val tileSet = TileSet.byList(listOf(tile2, tile1, tile3))
		
		tileSet.sortByNumber()
		
		assertThat(tileSet.tiles()).isEqualTo(listOf(tile1, tile2, tile3))
	}
	
	@Test
	fun `by-color - correct sorting`() {
		val tileSet = TileSet.byList(listOf(tile2, tile1, tile3))
		
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
		val tileSet = TileSet.byList(listOf(tile1, tile2, tile3))
		
		tileSet.sortByNumber()
		
		assertThat(tileSet.tiles()).isEqualTo(listOf(tile1, tile2, tile3))
	}
	
	@Test
	fun `by-color - sorted list remains sorted`() {
		val tileSet = TileSet.byList(listOf(tile1, tile3, tile2))
		
		tileSet.sortByColor()
		
		assertThat(tileSet.tiles()).isEqualTo(listOf(tile1, tile3, tile2))
	}
}
