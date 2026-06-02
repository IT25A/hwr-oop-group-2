package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.containers.Group

data class Board(private val groups: List<Group> = listOf()) {
	fun rows() = groups
	
	fun validate(): Boolean {
		return groups.all { it.validate() }
	}
}