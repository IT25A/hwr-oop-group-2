package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.NoSuchTileException
import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PlayerTest {
	
	val tile1 = Tile(TileNumber.One, TileColor.Blue)
	val tile2 = Tile(TileNumber.Five, TileColor.Red)
	val tile3 = Tile(TileNumber.Four, TileColor.Orange)
	val tile4 = Tile(TileNumber.Six, TileColor.Red)
	
	@Test
	fun `player starts with empty tiles`() {
		val player = Player(name = "Alice")
		assertThat(player.rack()).isEmpty()
	}
	
	@Test
	fun `players's name will be returned`() {
		val player = Player(name = "Alice")
		assertThat(player.name()).isEqualTo("Alice")
	}
	
	@Test
	fun `player can add single tile`() {
		val player = Player(name = "Alice")
		val newPlayer = player.add(tile1)
		assertThat(newPlayer.rack()).containsExactly(tile1)
	}
	
	@Test
	fun `player can remove tile`() {
		val player = Player(name = "Alice", listOf(tile1, tile2, tile3))
		val newPlayer = player.remove(tile2)
		assertThat(newPlayer.rack()).containsExactlyInAnyOrder(tile1, tile3)
	}
	
	@Test
	fun `player throws exception when removing non-existent tile`() {
		val player = Player(name = "Alice", listOf(tile1))
		assertThatThrownBy { player.remove(tile2) }.isInstanceOf(NoSuchTileException::class.java)
	}
	
	@Test
	fun `removeAll removes just the tiles it is supposed to`() {
		val player = Player(name = "Alice", listOf(tile1, tile2, tile3, tile4))
		val newPlayer = player.removeAll(tile1, tile3)
		assertThat(newPlayer.rack()).containsExactly(tile2, tile4)
	}
	
	@Test
	fun `removeAll does not delete more than it should`() {
		val player = Player(name = "Alice", listOf(tile1, tile2, tile3, tile4))
		val newPlayer = player.removeAll()
		assertThat(newPlayer.rack()).containsExactly(tile1, tile2, tile3, tile4)
	}
	
	@Test
	fun `removeAll throws an exception if tile does not exist`() {
		val player = Player(name = "Alice", listOf(tile1, tile2, tile4))
		assertThatThrownBy { player.removeAll(tile3) }.isInstanceOf(NoSuchTileException::class.java)
	}
	
}