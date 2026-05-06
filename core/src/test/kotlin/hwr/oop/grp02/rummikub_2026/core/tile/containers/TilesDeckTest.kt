package hwr.oop.grp02.rummikub_2026.core.tile.containers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TilesDeckTest {
	
	@Test
	fun `TilesDeck inherits TilesContainer`() {
		val deck = TilesDeck()
		assertThat { deck is TilesContainer }
	}
	
}