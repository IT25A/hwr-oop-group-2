package hwr.oop.examples.template

import GameNotFoundException
import hwr.oop.grp02.rummikub_2026.core.Game
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

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
		val game = Game.withShuffledDrawPile("0", setOf("John", "Doe"))
		persistenceLayer.saveGame(game)
		val loaded = persistenceLayer.loadGame(game.gameId)
		assertThat(loaded).isEqualTo(game)
	}
	
	@Test
	fun `should throw GameNotFoundException when game does not exist`() {
		val gameId = "non-existent"
		assertThatThrownBy { persistenceLayer.loadGame(gameId) }.isInstanceOf(GameNotFoundException::class.java)
	}
}