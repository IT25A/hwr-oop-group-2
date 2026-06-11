package hwr.oop.grp02.rummikub_2026.core

class PlayerNotFoundException(val playerName: String) : RuntimeException("The provided player $playerName doesn’t exist")