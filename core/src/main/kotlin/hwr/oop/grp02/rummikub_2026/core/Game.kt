package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.containers.DrawPile

class Game private constructor(
	internal val drawPile: DrawPile,
	internal val players: List<Player>,
	private var board: Board = Board(),
) {
	private var currentPlayerIndex: Int = 0
	
	companion object {
		fun withShuffledDrawPile(playerNames: Set<String>): Game {
			val drawPile = DrawPile.withAllTiles()
			drawPile.shuffle()
			return withPlayers(playerNames, drawPile)
		}
		
		fun withUnShuffledDrawPile(playerNames: Set<String>): Game {
			return withPlayers(playerNames, DrawPile.withAllTiles())
		}
		
		private fun withPlayers(playerNames: Set<String>, drawPile: DrawPile): Game {
			require(playerNames.size in 2..4) { "Rummikub requires 2 to 4 players" }
			
			val players = playerNames.map {
				Player(it, List(14) { drawPile.draw() }.toMutableList())
			}
			
			return Game(drawPile, players, Board())
		}
	}
	
	fun board(): Board = board
	
	fun currentPlayer(): Player = players[currentPlayerIndex]
	
	fun nextPlayer(): Player {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size
		return currentPlayer()
	}
}