package hwr.oop.grp02.rummikub_2026.core.tile.containers

enum class TileRowType {
	DiffNumberSameColor,
	SameNumberDiffColor
}

// TODO: Sort list on add tile(s)
class TileRow(private val type: TileRowType) : TilesContainer() {
	fun validate(): Boolean {
		val content = tiles()
		if (content.size < 3) {
			return false
		}
		
		return if (type === TileRowType.DiffNumberSameColor) {
			validSameColor()
		} else {
			validSameNumber()
		}
	}
	
	private fun validSameColor(): Boolean {
		val currTiles = tiles()
		
		for (tile in currTiles) {
			if (tile.color() != currTiles.first().color()) {
				return false
			}
		}
		
		return !currTiles.zipWithNext { a, b ->
			b.number().value() == a.number().value() + 1
		}.contains(false)
	}
	
	private fun validSameNumber(): Boolean {
		val currTiles = tiles()
		if (currTiles.size > 4) {
			return false
		}
		
		for (tile in currTiles) {
			if (tile.number() != currTiles.first().number()) {
				return false
			}
		}
		
		val colors = currTiles.map { it.color() }
		return colors.distinct().size == currTiles.size
	}
}
