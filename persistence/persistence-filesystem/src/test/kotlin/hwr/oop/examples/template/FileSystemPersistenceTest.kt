package hwr.oop.examples.template

import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import hwr.oop.grp02.rummikub_2026.core.Game

class FileSystemPersistenceTest {

	private val fakeFileSystem = FakeFileSystem()
	private val tempDir = "/tmp/rummikub-test".toPath()
	private val persistenceLayer: FileSystemPersistence

	init {
		fakeFileSystem.createDirectories(tempDir)
		persistenceLayer = FileSystemPersistence(
			FileSystemPersistenceConfiguration(tempDir),
			fakeFileSystem
		)
	}

	@AfterEach
	fun tearDown() {
		fakeFileSystem.checkNoOpenFiles()
	}

	@Test
	fun `should save and load game`() {
		val game = Game.withShuffledDrawPile(twoPlayerNames)
		val gameId = "test-game-1"
		persistenceLayer.saveGame(gameId, game)
		val loaded = persistenceLayer.loadGame(gameId)
		assertThat(loaded).isEqualTo(game)
	}

	@Test
	fun `should throw GameNotFoundException when game does not exist`() {
		val gameId = "non-existent"
		assertThatThrownBy{ persistenceLayer.loadGame(gameId) }.isInstanceOf(GameNotFoundException::class.java)
	}
}