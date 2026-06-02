package hwr.oop.grp02.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class GameTest {
	
	private val twoPlayerNames = setOf("Alice", "Bob")
	
	@Test
	fun `creates game with correct number of players`() {
		val game = Game.withUnShuffledDrawPile(twoPlayerNames)
		
		assertThat(game.players).hasSize(2)
	}
	
	@Test
	fun `each player has 14 tiles after creation`() {
		val game = Game.withUnShuffledDrawPile(twoPlayerNames)
		
		for (player in game.players) {
			assertThat(player.rack()).hasSize(14)
		}
	}
	
	@Test
	fun `drawPile has remaining tiles after dealing`() {
		val game = Game.withUnShuffledDrawPile(twoPlayerNames)
		val totalTiles = 104
		val dealtTiles = 2 * 14
		
		assertThat(game.drawPile.tiles()).hasSize(totalTiles - dealtTiles)
	}
	
	@Test
	fun `board is empty at start`() {
		val game = Game.withUnShuffledDrawPile(twoPlayerNames)
		
		assertThat(game.board().rows()).isEmpty()
	}
	
	@Test
	fun `player names are preserved`() {
		val game = Game.withUnShuffledDrawPile(twoPlayerNames)
		
		val names = game.players.map { it.name() }.toSet()
		assertThat(names).isEqualTo(twoPlayerNames)
	}
	
	@Test
	fun `nextPlayer advances to the second player`() {
		val game = Game.withUnShuffledDrawPile(twoPlayerNames)
		
		val next = game.nextPlayer()
		
		assertThat(next).isEqualTo(game.players[1])
	}
	
	@Test
	fun `nextPlayer wraps around to the first player`() {
		val game = Game.withUnShuffledDrawPile(twoPlayerNames)
		
		game.nextPlayer()
		val wrapped = game.nextPlayer()
		
		assertThat(wrapped).isEqualTo(game.players.first())
	}
	
	@Test
	fun `creating game with 1 player throws exception`() {
		assertThatThrownBy {
			Game.withShuffledDrawPile(setOf("Alone"))
		}.isInstanceOf(IllegalArgumentException::class.java)
	}
	
	@Test
	fun `creating game with 5 players throws exception`() {
		assertThatThrownBy {
			Game.withShuffledDrawPile(setOf("A", "B", "C", "D", "E"))
		}.isInstanceOf(IllegalArgumentException::class.java)
	}
	
	@Test
	fun `creating game with shuffled drawPile is actually shuffled`() {
		val shuffledGame = Game.withShuffledDrawPile(setOf("A", "B", "C"))
		val unShuffledGame = Game.withUnShuffledDrawPile(setOf("A", "B", "C"))
		
		assertThat(unShuffledGame.drawPile.tiles()).hasSameSizeAs(shuffledGame.drawPile.tiles())
		assertThat(unShuffledGame.drawPile.tiles()).isNotEqualTo(shuffledGame.drawPile.tiles())
	}
}
