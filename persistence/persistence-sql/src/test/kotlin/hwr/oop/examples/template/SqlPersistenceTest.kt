package hwr.oop.examples.template

import GameNotFoundException
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import hwr.oop.grp02.rummikub_2026.core.Game
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class SqlPersistenceTest {
	
	companion object {
		@Container
		@JvmStatic
		val postgres = PostgreSQLContainer("postgres:17-alpine")
	}
	
	private lateinit var adapter: SqlPersistence
	private lateinit var dataSource: HikariDataSource
	
	@BeforeEach
	fun setUp() {
		val config = HikariConfig().apply {
			jdbcUrl = postgres.jdbcUrl
			username = postgres.username
			password = postgres.password
		}
		dataSource = HikariDataSource(config)
		adapter = SqlPersistence(dataSource)
	}
	
	@AfterEach
	fun tearDown() {
		if (::dataSource.isInitialized) {
			dataSource.close()
		}
	}
	
	@Test
	fun `should save and load game`() {
		val game = Game.withShuffledDrawPile("0", setOf("John", "Doe"))
		adapter.saveGame(game)
		val loaded = adapter.loadGame(game.gameId)
		assertThat(loaded).isEqualTo(game)
	}
	
	@Test
	fun `should throw GameNotFoundException when game does not exist`() {
		val gameId = "non-existent-game"
		assertThatThrownBy { adapter.loadGame(gameId) }.isInstanceOf(GameNotFoundException::class.java)
	}
	
}