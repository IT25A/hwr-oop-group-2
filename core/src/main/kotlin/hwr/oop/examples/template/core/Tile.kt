package hwr.oop.examples.template.core

import java.awt.Color

data class Tile (
    private val color: TileColor,
    private val number: TileNumber,
) {
    fun color(): TileColor = color
    fun number(): TileNumber = number
}