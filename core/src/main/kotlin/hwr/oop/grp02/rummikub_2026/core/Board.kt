package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.containers.Row
import hwr.oop.grp02.rummikub_2026.core.tile.containers.RowType

data class Board(private val rows: List<Row> = listOf()) {
	fun rows() = rows
	
	fun validate(): Boolean {
		return rows.all { it.validate() }
	}
	
	fun newRow(type: RowType): Board {
		val original = mutableListOf(*rows.toTypedArray())
		original.add(Row(type))
		return copy(rows = original.toList())
	}
	
	fun removeEmptyRows(): Board {
		val original = mutableListOf(*rows.toTypedArray())
		original.removeIf { it.size() == 0 }
		return copy(rows = original.toList())
	}
}