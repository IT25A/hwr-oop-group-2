package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TilesDeckTest {

    lateinit var deck: TilesDeck
    val tile1 = Tile(TileValue.One, TileColor.Blue)
    val tile2 = Tile(TileValue.Five, TileColor.Red)
    val tile3 = Tile(TileValue.Four, TileColor.Orange)
    val tile4 = Tile(TileValue.Six, TileColor.Red)

    @BeforeEach
    fun setup() {
        deck = TilesDeck()
    }

    @Test
    fun `TilesDeck inherits TilesContainer`() {
        assertThat { deck is TilesContainer }
    }

    @Test
    fun `TilesDeck sets color`() {
        deck.add(listOf(tile1, tile2, tile3, tile4))
        deck.sortByColor()
        assertThat(deck.tiles()).isEqualTo(listOf(tile1, tile3, tile2, tile4))
    }

    @Test
    fun `TilesDeck sorts by number`() {
        deck.add(listOf(tile4, tile3, tile2, tile1))
        deck.sortByNumber() // returned Unit -> deck.tiles()   :)
        assertThat(deck.tiles()).isEqualTo(listOf(tile1, tile2, tile3, tile4))
    }
}