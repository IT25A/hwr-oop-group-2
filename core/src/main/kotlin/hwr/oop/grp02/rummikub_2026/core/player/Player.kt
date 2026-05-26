package hwr.oop.grp02.rummikub_2026.core.player

import hwr.oop.grp02.rummikub_2026.core.IllegalMoveException
import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.containers.TileSet

class Player(private val name: String, private val container: TileSet = TileSet()) {

    fun rack() = container.tiles()

    fun name() = name

    internal fun add(tile: Tile) {
        container.add(tile);
    }

    internal fun remove(tile: Tile) {
        if (!container.remove(tile)) {
            throw IllegalMoveException()
        }
    }

    fun sortByNumber() {
        container.sortByNumber()
    }

    fun sortByColor() {
        container.sortByColor()
    }
}