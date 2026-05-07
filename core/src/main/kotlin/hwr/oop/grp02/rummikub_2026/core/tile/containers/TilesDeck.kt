package hwr.oop.grp02.rummikub_2026.core.tile.containers

class TilesDeck() : TilesContainer() {

    fun sortByNumber() {
        tiles.sortBy { it.value() }
    }

    fun sortByColor() {
        tiles.sortBy { it.color() }
    }

}
