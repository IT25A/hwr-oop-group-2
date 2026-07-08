package hwr.oop.examples.template

import hwr.oop.grp02.rummikub_2026.core.Game
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.json.jsonb

private val format = Json {
	prettyPrint = false
	isLenient = true
	ignoreUnknownKeys = true
	allowStructuredMapKeys = true
}

object RummikubGamesTable : Table("rummikub_games") {
	val id = varchar("id", 255)
	val game = jsonb<Game>("game", format)
	
	override val primaryKey = PrimaryKey(id)
}