package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import hwr.oop.grp02.rummikub_2026.core.tile.containers.DrawPile
import hwr.oop.grp02.rummikub_2026.core.tile.containers.TileSet

val allTiles = TileColor.entries.flatMap { color ->
	TileNumber.entries.map { value -> Tile(value, color) }
}

class Game(internal val drawPile: DrawPile, internal val players: List<Player>, internal var board: Board = Board()) {
	private var currentPlayerIndex: Int = 0
	
	private var stagingBoard: Board = board
	
	companion object {
		fun withShuffledDrawPile(playerNames: Set<String>): Game {
			val tiles: MutableList<Tile> = listOf(allTiles, allTiles).flatten().shuffled().toMutableList()
			val drawPile = DrawPile(TileSet(tiles))
			return withPlayers(playerNames, drawPile)
		}
		
		fun withUnShuffledDrawPile(playerNames: Set<String>): Game {
			val tiles: MutableList<Tile> = listOf(allTiles, allTiles).flatten().toMutableList()
			val drawPile = DrawPile(TileSet(tiles))
			return withPlayers(playerNames, drawPile)
		}
		
		private fun withPlayers(playerNames: Set<String>, drawPile: DrawPile): Game {
			require(playerNames.size in 2..4) { "Rummikub requires 2 to 4 players" }
			
			val players = playerNames.map {
				Player(it, TileSet(List(14) { drawPile.draw() }.toMutableList()))
			}
			
			return Game(drawPile, players, Board())
		}
	}
	
	fun board(): Board = stagingBoard
	
	fun currentPlayer(): Player = players[currentPlayerIndex]
	
	fun nextPlayer(): Player {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size
		return currentPlayer()
	}
}