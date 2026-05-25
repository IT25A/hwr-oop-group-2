package hwr.oop.grp02.rummikub_2026.core.move

import hwr.oop.grp02.rummikub_2026.core.tile.Tile

data class Move(
    val tile: Tile,
    val fromRowIndex: Int?,
    val toRowIndex: Int?,
)