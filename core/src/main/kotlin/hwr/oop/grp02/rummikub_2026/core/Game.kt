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
	private var movePossible = false
	
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
	
	fun makeMove(player: Player, laidTiles: List<Tile>, newBoard: Board): Player {
		val playerIndex = playerIndex(player)
		
		if (!player.rack().containsAll(laidTiles)) throw TODO("TileNotFoundException?")
		
		if (!newBoard.validate()) throw TODO("Board invalid")
		
		val newPlayer = player.removeAll(tile = laidTiles.toTypedArray())
		
		players = replacePlayer(playerIndex, newPlayer)
		board = newBoard
		return newPlayer
	}
	
	fun drawCard(player: Player): Player {
		val playerIndex = playerIndex(player)
		val card = drawPile.draw()
		val newPlayer = player.add(card)
		players = replacePlayer(playerIndex, newPlayer)
		return newPlayer
	}
	
	private fun playerIndex(player: Player): Int {
		if (!movePossible) throw TODO("Move not possible -> nextPlayer()")
		
		val playerIndex = players.indexOf(player)
		if (playerIndex == -1) throw PlayerNotFoundException(player.name())
		if (playerIndex != currentPlayerIndex) throw TODO("Player not allowed to make move")
		return playerIndex
	}
	
	internal fun replacePlayer(index: Int, player: Player): List<Player> {
		return players.slice(0 until index) + players + players.slice(index + 1 until players.size)
	}
	
	fun board(): Board = board
	
	fun currentPlayer(): Player = players[currentPlayerIndex]
	
	fun nextPlayer(): Player {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size
		return currentPlayer()
	}
	
	fun players(): List<Player> = players.toList()
}