package hwr.oop.grp02.rummikub_2026.core

class PlayerNotAllowedException(playerName: String) :
	RuntimeException("The provided player $playerName is not allowed to play")