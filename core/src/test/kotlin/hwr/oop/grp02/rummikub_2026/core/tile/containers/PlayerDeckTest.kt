package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerDeckTest {

    lateinit var deck: PlayerDeck
    val tile1 = Tile(TileNumber.One, TileColor.Blue)
    val tile2 = Tile(TileNumber.Five, TileColor.Red)
    val tile3 = Tile(TileNumber.Four, TileColor.Orange)
    val tile4 = Tile(TileNumber.Six, TileColor.Red)

    @BeforeEach
    fun setup() {
        deck = PlayerDeck()
    }

    @Test
    fun `PlayerDeck inherits TilesContainer`() {
        assertThat { deck is TilesContainer }
    }
    
    @Test
    fun `PlayerDeck sorts color`() {
        deck.add(listOf(tile1, tile2, tile3, tile4))
        deck.sortByColor()
        assertThat(deck.tiles()).isEqualTo(listOf(tile1, tile3, tile2, tile4))
    }

    @Test
    fun `PlayerDeck sorts by number`() {
        deck.add(listOf(tile4, tile3, tile2, tile1))
        deck.sortByNumber()
        assertThat(deck.tiles()).isEqualTo(listOf(tile1, tile3, tile2, tile4))
    }
    
    @Test
    fun `empty list remains empty (number)`() {
        deck.sortByNumber()
        assertThat(deck.tiles()).isEmpty()
    }
    
    @Test
    fun `empty list remains empty (color)`() {
        deck.sortByColor()
        assertThat(deck.tiles()).isEmpty();
    }
    
    @Test
    fun `by-number sorting a sorted list returns the input list`() {
        val input = listOf(tile1, tile3, tile2, tile4)
        deck.add(input)
        deck.sortByNumber()
        assertThat(deck.tiles()).containsExactlyElementsOf(input)
    }
    
    @Test
    fun `by-color sorting a sorted list returns the input list`() {
        val input = listOf(tile1, tile3, tile2, tile4)
        deck.add(listOf(tile1, tile3, tile2, tile4))
        deck.sortByColor()
        assertThat(deck.tiles()).containsExactlyElementsOf(input)
    }


}