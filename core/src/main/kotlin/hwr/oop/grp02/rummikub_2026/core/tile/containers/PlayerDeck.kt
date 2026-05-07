package hwr.oop.grp02.rummikub_2026.core.tile.containers

class PlayerDeck : TilesContainer() {

    fun sortByNumber() {
        tiles.sortBy { it.number().value() }
    }

    fun sortByColor() {
        tiles.sortBy { it.color().name }
    }

}
