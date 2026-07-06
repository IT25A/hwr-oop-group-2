import hwr.oop.grp02.rummikub_2026.core.Game

class GameNotFoundException(val gameId: String) : Exception("Game $gameId not found")

interface GamePersistence {
    fun saveGame(gameId: String, game: Game) : Unit
    fun loadGame(gameId: String): Game
}