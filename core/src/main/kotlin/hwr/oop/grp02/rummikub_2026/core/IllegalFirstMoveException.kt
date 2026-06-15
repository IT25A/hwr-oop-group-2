package hwr.oop.grp02.rummikub_2026.core

class IllegalFirstMoveException(name: String) :
	RuntimeException("Player $name’s first move is not valid (30 points, board not modified)")
