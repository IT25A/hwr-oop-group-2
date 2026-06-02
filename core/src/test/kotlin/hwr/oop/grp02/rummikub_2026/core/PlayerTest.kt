package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerTest {
	
	val tile1 = Tile(TileNumber.One, TileColor.Blue)
	val tile2 = Tile(TileNumber.Five, TileColor.Red)
	val tile3 = Tile(TileNumber.Four, TileColor.Orange)
	val tile4 = Tile(TileNumber.Six, TileColor.Red)
	
	@Test
	fun `player starts with empty tiles`() {
		val player = Player(name = "Alice")
		Assertions.assertThat(player.rack()).isEmpty()
	}
	
	@Test
	fun `players's name will be returned`() {
		val player = Player(name = "Alice")
		Assertions.assertThat(player.name()).isEqualTo("Alice")
	}
	
	@Test
	fun `player can add single tile`() {
		val player = Player(name = "Alice")
		val newPlayer = player.add(tile1)
		Assertions.assertThat(newPlayer.rack()).containsExactly(tile1)
	}
	
	@Test
	fun `player can remove tile`() {
		val player = Player(name = "Alice", listOf(tile1, tile2, tile3))
		val newPlayer = player.remove(tile2)
		Assertions.assertThat(newPlayer.rack()).containsExactlyInAnyOrder(tile1, tile3)
	}
	
	@Test
	fun `player throws exception when removing non-existent tile`() {
		val player = Player(name = "Alice", listOf(tile1))
		Assertions.assertThatThrownBy { player.remove(tile2) }.isInstanceOf(NoSuchTileException::class.java)
	}
	
}