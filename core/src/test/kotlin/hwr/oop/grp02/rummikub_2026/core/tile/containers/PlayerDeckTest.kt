package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerDeckTest {

    val tile1 = Tile(TileNumber.One, TileColor.Blue)
    val tile2 = Tile(TileNumber.Five, TileColor.Red)
    val tile3 = Tile(TileNumber.Four, TileColor.Orange)
    val tile4 = Tile(TileNumber.Six, TileColor.Red)
    
    @Test
    fun `PlayerDeck sorts color`() {
        val container = TilesContainer(listOf(tile1, tile2, tile3, tile4))
        val deck = PlayerDeck(container, PlayerDockSortingMode.ByColor)
        
        deck.sort()
        
        assertThat(deck.tiles()).isEqualTo(listOf(tile1, tile3, tile2, tile4))
    }

    @Test
    fun `PlayerDeck sorts by number`() {
        val container = TilesContainer(listOf(tile4, tile3, tile2, tile1))
        val deck = PlayerDeck(container, PlayerDockSortingMode.ByNumber)
        
        deck.sort()
        
        assertThat(deck.tiles()).isEqualTo(listOf(tile1, tile3, tile2, tile4))
    }
    
    @Test
    fun `empty list remains empty on sort`() {
        val deck = PlayerDeck()
        
        deck.sort()
        
        assertThat(deck.tiles()).isEmpty()
    }
    
    @Test
    fun `by-number sorting a sorted list returns the input list`() {
        val input = listOf(tile1, tile3, tile2, tile4)
        val container = TilesContainer(input)
        val deck = PlayerDeck(container, PlayerDockSortingMode.ByNumber)
        
        deck.sort()
        
        assertThat(deck.tiles()).containsExactlyElementsOf(input)
    }
    
    @Test
    fun `by-color sorting a sorted list returns the input list`() {
        val input = listOf(tile1, tile3, tile2, tile4)
        val container = TilesContainer(input)
        val deck = PlayerDeck(container, PlayerDockSortingMode.ByColor)
        
        deck.sort()
        
        assertThat(deck.tiles()).containsExactlyElementsOf(input)
    }


}