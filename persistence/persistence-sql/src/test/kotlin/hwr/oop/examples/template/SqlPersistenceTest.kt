package hwr.oop.examples.template

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThatThrownBy
import org.assertj.core.api.Assertions.assertThat
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import hwr.oop.grp02.rummikub_2026.core.Game
import hwr.oop.grp02.rummikub_2026.core.GameNotFoundException

@Testcontainers
class SqlPersistenceTest {

	private val twoPlayerNames = setOf("Alice", "Bob")

	companion object {
		@Container
		val postgres = PostgreSQLContainer<Nothing>("postgres:15").apply {
			withDatabaseName("rummikub_test")
			withUsername("test")
			withPassword("test")
		}
	}

	private fun createSqlPersistence(): SqlPersistence {
		return SqlPersistence(
			postgres.jdbcUrl,
			postgres.username,
			postgres.password
		)
	}

	@Test
	fun `should save and load game`() {
		val sqlPersistence = createSqlPersistence()
		val game = Game.withShuffledDrawPile(twoPlayerNames)
		val gameId = "test-game-1"
		sqlPersistence.saveGame(gameId, game)
		val loaded = sqlPersistence.loadGame(gameId)
		assertThat(loaded).isEqualTo(game)
	}

	@Test
	fun `should throw GameNotFoundException when game does not exist`() {
		val sqlPersistence = createSqlPersistence()
		val gameId = "non-existent-game"
		assertThatThrownBy{ sqlPersistence.loadGame(gameId) }.isInstanceOf(GameNotFoundException::class.java)
	}

	@Test
	fun `should update existing game`() {
		val sqlPersistence = createSqlPersistence()
		val gameId = "test-game-2"
		val initialGame = Game.withUnShuffledDrawPile(twoPlayerNames)
		val updatedGame = Game.withShuffledDrawPile(twoPlayerNames)
		sqlPersistence.saveGame(gameId, initialGame)
		sqlPersistence.saveGame(gameId, updatedGame)
		val loaded = sqlPersistence.loadGame(gameId)
		assertThat(loaded).isEqualTo(updatedGame)
	}
