package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.containers.DrawPile
import kotlinx.serialization.Serializable
import kotlin.math.abs

@Serializable
data class Game private constructor(
	internal val drawPile: DrawPile,
	private var players: List<Player>,
	private var board: Board = Board(),
	val gameId: String,
) {
	private var currentPlayerIndex: Int = 0
	private var winningPlayer: Player? = null
	
	companion object {
		fun withShuffledDrawPile(gameId: String, playerNames: Set<String>, withInitialMeld: Boolean = false): Game {
			val drawPile = DrawPile.withAllTiles()
			drawPile.shuffle()
			return withPlayers(playerNames, drawPile, withInitialMeld, gameId)
		}
		
		fun withUnShuffledDrawPile(gameId: String, playerNames: Set<String>, withInitialMeld: Boolean = false): Game {
			return withPlayers(playerNames, DrawPile.withAllTiles(), withInitialMeld, gameId)
		}
		
		internal fun withDefinedPlayers(players: List<Player>, gameId: String = "0"): Game {
			return Game(DrawPile.withAllTiles(), players, gameId = gameId)
		}
		
		private fun withPlayers(
			playerNames: Set<String>,
			drawPile: DrawPile,
			withInitialMeld: Boolean = false,
			gameId: String,
		): Game {
			require(playerNames.size in 2..4) { "Rummikub requires 2 to 4 players" }
			
			val players = playerNames.map {
				Player(it, List(14) { drawPile.draw() }.toMutableList(), withInitialMeld)
			}
			
			return Game(drawPile, players, gameId = gameId)
		}
	}
	
	data class MoveResponse(val newPlayer: Player, val nextPlayer: Player?, val hasWon: Boolean)
	
	fun makeMove(player: Player, laidTiles: List<Tile>, newBoard: Board): MoveResponse {
		require(winningPlayer == null) { "Game has finished (one player has won)" }
		
		requireValidPlayer(player)
		
		require(laidTiles.isNotEmpty()) { "Player didn’t lay out any tiles" }
		
		requireInitialMeld(player, laidTiles, newBoard)
		
		require(newBoard.validate()) { "Provided board is invalid" }
		
		require(
			newBoard.subtractTiles(board.tiles() + laidTiles).isEmpty()
		) { "Provided board does contain tiles which aren’t present in the old board" }
		
		val newPlayer = player.removeAll(tile = laidTiles.toTypedArray())
		
		players = replacePlayer(currentPlayerIndex, newPlayer.copy(initialMeld = true))
		board = newBoard
		
		if (newPlayer.rack().isEmpty()) {
			winningPlayer = newPlayer.copy(points = players.sumOf { abs(it.points()) })
			return MoveResponse(newPlayer, hasWon = true, nextPlayer = null)
		}
		
		return MoveResponse(newPlayer, nextPlayer(), hasWon = false)
	}
	
	data class DrawResponse(val newPlayer: Player, val nextPlayer: Player, val tile: Tile)
	
	fun drawTile(player: Player): DrawResponse {
		require(winningPlayer == null) { "Game has finished (one player has won)" }
		requireValidPlayer(player)
		val tile = drawPile.draw()
		val newPlayer = player.add(tile)
		players = replacePlayer(currentPlayerIndex, newPlayer)
		return DrawResponse(newPlayer, nextPlayer(), tile)
	}
	
	internal fun replacePlayer(index: Int, player: Player): List<Player> {
		val list = players.slice(0 until index) + player + players.slice(index + 1 until players.size)
		return list.map { it.copy(points = it.sum()) }
	}
	
	private fun requireValidPlayer(player: Player) {
		if (players[currentPlayerIndex] != player) throw PlayerNotAllowedException(player.name())
	}
	
	private fun requireInitialMeld(player: Player, laidTiles: List<Tile>, newBoard: Board) {
		if (player.initMeld()) return
		
		val newGroups = newBoard.groups().toMutableList()
		board.groups().forEach { newGroups.remove(it) }
		val boardNotModified = newGroups.flatMap { it.tiles() } == laidTiles
		
		val enoughPoints = newGroups.sumOf { it.totalPointValue() } >= 30
		
		if (boardNotModified && enoughPoints) return
		
		throw IllegalFirstMoveException(player.name())
	}
	
	fun board(): Board = board
	
	fun currentPlayer(): Player = players[currentPlayerIndex]
	
	fun finished(): Boolean = winningPlayer != null
	
	fun winningPlayer(): Player? = winningPlayer
	
	internal fun nextPlayer(): Player {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size
		return currentPlayer()
	}
	
	fun players(): List<Player> = players.toList()
}
