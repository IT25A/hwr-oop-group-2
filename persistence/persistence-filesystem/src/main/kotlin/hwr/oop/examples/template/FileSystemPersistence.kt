package hwr.oop.examples.template

import kotlinx.serialization.json.Json
import okio.FileSystem
import okio.Path

private val json = Json {
	prettyPrint = true
	ignoreUnknownKeys = true
	allowStructuredMapKeys = true
}

class RummikubFileSystemPersistence(
	private val directory: Path,
	private val fileSystem: FileSystem = FileSystem.SYSTEM,
) : GamePersistence {

	override fun saveGame(gameId: String, game: Game) {
		val path = directory.resolve("$gameId.json")
		if (!fileSystem.exists(directory)) {
			fileSystem.createDirectories(directory)
		}
		fileSystem.write(path) {
			writeUtf8(json.encodeToString(game))
		}
	}

	override fun loadGame(gameId: String): Game {
		val path = directory.resolve("$gameId.json")
		val jsonString = try {
			fileSystem.read(path) { readUtf8() }
		} catch (e: okio.FileNotFoundException) {
			throw GameNotFoundException(gameId)
		}
		return json.decodeFromString<Game>(jsonString)
	}
}