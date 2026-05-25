package hwr.oop.grp02.rummikub_2026.core.player

import hwr.oop.grp02.rummikub_2026.core.IllegalMoveException
import hwr.oop.grp02.rummikub_2026.core.move.Move
import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.containers.TileSet

class Player(private val container: TileSet = TileSet(), private val name : String ){
    private val moves: MutableList<Move> = mutableListOf()
    fun moves(): List<Move> = moves.toList()

    fun rack() = container.tiles()

    fun name() =  this.name

    internal fun add(vararg tileList: Tile) {
        container.add(tileList.toList());
    }

    internal fun remove(tile: Tile) {
        if (!container.remove(tile)) {
            throw IllegalMoveException()
        }
    }

     internal fun sortByNumber() {
        container.sortByNumber()
    }

    internal fun sortByColor() {
        container.sortByColor()
    }
}