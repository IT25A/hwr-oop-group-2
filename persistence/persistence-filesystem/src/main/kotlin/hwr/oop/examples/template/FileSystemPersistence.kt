package hwr.oop.examples.template

import GameNotFoundException
import GamePersistence
import hwr.oop.grp02.rummikub_2026.core.Game
import kotlinx.serialization.json.Json
import okio.FileSystem

private val json = Json {
	prettyPrint = true
	ignoreUnknownKeys = true
	allowStructuredMapKeys = true
}

class FileSystemPersistence(
	configuration: FileSystemPersistenceConfiguration,
	private val fileSystem: FileSystem = FileSystem.SYSTEM,
) : GamePersistence {
	private val directory = configuration.directory
	
	override fun saveGame(game: Game) {
		val path = directory.resolve("${game.gameId}.json")
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