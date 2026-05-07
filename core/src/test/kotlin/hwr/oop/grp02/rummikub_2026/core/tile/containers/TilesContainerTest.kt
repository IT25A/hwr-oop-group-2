package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach

class TestContainer : TilesContainer()

class TilesContainerTest {
	lateinit var container: TestContainer
	val tile1 = Tile(TileNumber.One, TileColor.Blue)
	val tile2 = Tile(TileNumber.Two, TileColor.Red)
	val tile3 = Tile(TileNumber.Three, TileColor.Orange)
	
	@BeforeEach
	fun setup() {
		container = TestContainer()
	}
	
	@Test
	fun `tiles list is empty on initialisation`() {
		assertThat(container.tiles()).isEmpty()
	}
	
	@Test
	fun `container#add adds a tile to the list`() {
		container.add(tile1)
		assertThat(container.tiles()).containsExactly(tile1)
	}
	
	@Test
	fun `container#add adds multiple tile to the list in correct order`() {
		container.add(listOf(tile1, tile2, tile3))
		assertThat(container.tiles()).containsExactlyElementsOf(listOf(tile1, tile2, tile3))
	}
	
	@Test
	fun `container#remove removes a tile from the list`() {
		container.add(tile1)
		container.remove(tile1)
		assertThat(container.tiles()).isEmpty()
	}
	
	@Test
	fun `order of tiles is preserved`() {
		val listGiven = listOf(tile1, tile2, tile3)
		
		container.add(tile1)
		container.add(tile2)
		container.add(tile3)
		
		val contentOfContainer = container.tiles()
		
		assertThat(contentOfContainer).isEqualTo(listGiven)
	}
	
	@Test
	fun `container#size has correct size`() {
		val collectionSize: Int = 3;
		
		container.add(tile1)
		container.add(tile2)
		container.add(tile3)
		
		assertThat(container.size()).isEqualTo(collectionSize)
	}
	
	// TODO: QUESTION TO STEFAN - Do we need to check for the mutability of a list?
	
}
