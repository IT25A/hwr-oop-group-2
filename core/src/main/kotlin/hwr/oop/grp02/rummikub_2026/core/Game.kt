package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.containers.DrawPile
import kotlin.collections.plus

class Game private constructor(
	internal val drawPile: DrawPile,
	private var players: List<Player>,
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
			
			return Game(drawPile, players)
		}
	}
	
	data class MoveResponse(val newPlayer: Player, val nextPlayer: Player?, val hasWon: Boolean)
	
	fun makeMove(player: Player, laidTiles: List<Tile>, newBoard: Board): MoveResponse {
		requireValidPlayer(player)
		
		require(laidTiles.isNotEmpty()) { "Player didn’t lay out any tiles" }
		require(newBoard.validate()) { "Provided board is invalid" }
		require(
			newBoard.subtractTiles(board.tiles() + laidTiles).isEmpty()
		) { "Provided board does contain tiles which aren’t present in the old board" }
		
		val newPlayer = player.removeAll(tile = laidTiles.toTypedArray())
		
		players = replacePlayer(currentPlayerIndex, newPlayer)
		board = newBoard
		
		if (newPlayer.rack().isEmpty()) return MoveResponse(newPlayer, hasWon = true, nextPlayer = null)
		return MoveResponse(newPlayer, nextPlayer(), hasWon = false)
	}
	
	data class DrawResponse(val newPlayer: Player, val nextPlayer: Player, val tile: Tile)
	
	fun drawTile(player: Player): DrawResponse {
		requireValidPlayer(player)
		val tile = drawPile.draw()
		val newPlayer = player.add(tile)
		players = replacePlayer(currentPlayerIndex, newPlayer)
		return DrawResponse(newPlayer, nextPlayer(), tile)
	}
	
	internal fun replacePlayer(index: Int, player: Player): List<Player> {
		return players.slice(0 until index) + player + players.slice(index + 1 until players.size)
	}
	
	private fun requireValidPlayer(player: Player) {
		if (players[currentPlayerIndex] != player) throw PlayerNotAllowedException(player.name())
	}
	
	fun board(): Board = board
	
	fun currentPlayer(): Player = players[currentPlayerIndex]
	
	internal fun nextPlayer(): Player {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size
		return currentPlayer()
	}
	
	fun players(): List<Player> = players.toList()
	
}
