package hwr.oop.grp02.rummikub_2026.core.tile

import hwr.oop.grp02.rummikub_2026.core.IllegalMoveException
import hwr.oop.grp02.rummikub_2026.core.Player
import hwr.oop.grp02.rummikub_2026.core.tile.containers.TileSet
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
        player.add(tile1)
        assertThat(player.rack()).containsExactly(tile1)
    }

    @Test
    fun `player can remove tile`() {
        val player = Player(name = "Alice", TileSet(mutableListOf(tile1, tile2, tile3)))
        player.remove(tile2)
        assertThat(player.rack()).containsExactlyInAnyOrder(tile1, tile3)
    }

    @Test
    fun `player throws exception when removing non-existent tile`() {
        val player = Player(name = "Alice")
        player.add(tile1)
        assertThatThrownBy { player.remove(tile2) }.isInstanceOf(IllegalMoveException::class.java)
        assertThat(player.rack()).containsExactly(tile1)
    }

    @Test
    fun `player can sort by number`() {
        val player = Player(name = "Alice", TileSet(mutableListOf(tile4, tile3, tile2, tile1)))
        player.sortByNumber()
        assertThat(player.rack()).isEqualTo(listOf(tile1, tile3, tile2, tile4))
    }

    @Test
    fun `player can sort by color`() {
        val player = Player(name = "Alice", TileSet(mutableListOf(tile1, tile2, tile3, tile4)))
        player.sortByColor()
        assertThat(player.rack()).isEqualTo(listOf(tile1, tile3, tile2, tile4))
    }

    @Test
    fun `drawing nothing does not change the list`() {
        val player = Player(name = "Alice", TileSet())
        val tiles = player.rack()
        assertThat(player.rack()).isEqualTo(tiles)
    }

}