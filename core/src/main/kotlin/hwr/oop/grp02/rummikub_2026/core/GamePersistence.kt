import kotlinx.serialization.Serializable

class GameNotFoundException(val gameId: String) : Exception("Game $gameId not found")

interface GamePersistence {
    fun saveGame(gameId: String, game: Game) : Unit
    fun loadGame(gameId: String): Game
}