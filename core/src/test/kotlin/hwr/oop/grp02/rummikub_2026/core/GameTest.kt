package hwr.oop.grp02.rummikub_2026.core

import hwr.oop.grp02.rummikub_2026.core.tile.JokerTile
import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import hwr.oop.grp02.rummikub_2026.core.tile.containers.Group
import hwr.oop.grp02.rummikub_2026.core.tile.containers.GroupType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class GameTest {

    private val twoPlayerNames = setOf("Alice", "Bob")

    val blueOne = Tile(TileNumber.One, TileColor.Blue)
    val blueTwo = Tile(TileNumber.Two, TileColor.Blue)
    val blueThree = Tile(TileNumber.Three, TileColor.Blue)
    val blueFour = Tile(TileNumber.Four, TileColor.Blue)
    val blueFive = Tile(TileNumber.Five, TileColor.Blue)
    val blueSix = Tile(TileNumber.Six, TileColor.Blue)
    val blueSeven = Tile(TileNumber.Seven, TileColor.Blue)
    val blueEight = Tile(TileNumber.Eight, TileColor.Blue)
    val redOne = Tile(TileNumber.One, TileColor.Red)
    val redTwo = Tile(TileNumber.Two, TileColor.Red)
    val redThree = Tile(TileNumber.Three, TileColor.Red)
    val redFour = Tile(TileNumber.Four, TileColor.Red)
    val redFive = Tile(TileNumber.Five, TileColor.Red)
    val redSix = Tile(TileNumber.Six, TileColor.Red)
    val redSeven = Tile(TileNumber.Seven, TileColor.Red)
    val redEight = Tile(TileNumber.Eight, TileColor.Red)
    val redNine = Tile(TileNumber.Nine, TileColor.Red)
    val redTen = Tile(TileNumber.Ten, TileColor.Red)
    val redEleven = Tile(TileNumber.Eleven, TileColor.Red)
    val redTwelve = Tile(TileNumber.Twelve, TileColor.Red)
    val redThirteen = Tile(TileNumber.Thirteen, TileColor.Red)

    private val groupedTiles = listOf(blueOne, blueTwo, blueThree)

    @Test
    fun `creates game with correct number of players`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames)

        assertThat(game.players()).hasSize(2)
    }

    @Test
    fun `each player has 14 tiles after creation`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames)

        for (player in game.players()) {
            assertThat(player.rack()).hasSize(14)
        }
    }

    @Test
    fun `drawPile has remaining tiles after dealing`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames)
        val totalTiles = 106
        val dealtTiles = 2 * 14

        assertThat(game.drawPile.tiles()).hasSize(totalTiles - dealtTiles)
    }

    @Test
    fun `board is empty at start`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames)

        assertThat(game.board().groups()).isEmpty()
    }

    @Test
    fun `player names are preserved`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames)

        val names = game.players().map { it.name() }.toSet()
        assertThat(names).isEqualTo(twoPlayerNames)
    }

    @Test
    fun `nextPlayer advances to the second player`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames)

        val next = game.nextPlayer()

        assertThat(next).isEqualTo(game.players()[1])
    }

    @Test
    fun `nextPlayer wraps around to the first player`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames)

        game.nextPlayer()
        val wrapped = game.nextPlayer()

        assertThat(wrapped).isEqualTo(game.players().first())
    }

    @Test
    fun `creating game with 1 player throws exception`() {
        assertThatThrownBy {
            Game.withShuffledDrawPile(setOf("Alone"))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `creating game with 5 players throws exception`() {
        assertThatThrownBy {
            Game.withShuffledDrawPile(setOf("A", "B", "C", "D", "E"))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `creating game with shuffled drawPile is actually shuffled`() {
        val shuffledGame = Game.withShuffledDrawPile(setOf("A", "B", "C"))
        val unShuffledGame = Game.withUnShuffledDrawPile(setOf("A", "B", "C"))

        assertThat(unShuffledGame.drawPile.tiles()).hasSameSizeAs(shuffledGame.drawPile.tiles())
        assertThat(unShuffledGame.drawPile.tiles()).isNotEqualTo(shuffledGame.drawPile.tiles())
    }

    @Test
    fun `Exception is thrown if laidTest is Empty`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames)
        val laid = emptyList<Tile>()
        assertThatThrownBy {
            game.makeMove(game.players()[0], laid, Board())
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Player didn’t lay out any tiles")
    }

    @Test
    fun `Exception is thrown if newBoard is not validated`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames, true)
        val testBoard = Board(listOf(Group(GroupType.DiffNumberSameColor, listOf(blueOne, blueTwo))))
        assertThatThrownBy {
            game.makeMove(game.players()[0], groupedTiles, testBoard)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Provided board is invalid")
    }

    @Test
    fun `Exception is thrown if newBoard contains unexpected tiles`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames, true)
        val unexpectedTile = Tile(TileNumber.Four, TileColor.Blue)
        val testBoard = Board(
            listOf(
                Group(GroupType.DiffNumberSameColor, groupedTiles + unexpectedTile)
            )
        )

        assertThatThrownBy {
            game.makeMove(game.players()[0], groupedTiles, testBoard)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Provided board does contain tiles which aren’t present in the old board")
    }

    @Test
    fun `Player can move only with correct playerIndex`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames)

        val currentPlayer = game.currentPlayer()
        assertThat(currentPlayer).isEqualTo(game.players()[0])

        game.nextPlayer()
        val nextPlayer = game.currentPlayer()
        assertThat(nextPlayer).isEqualTo(game.players()[1])
        assertThat(nextPlayer).isNotEqualTo(currentPlayer)
    }

    @Test
    fun `draw removes a card from the draw pile and adds that card to player`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika"))
        val oldDrawPileSize = game.drawPile.tiles().size
        val oldPlayer = game.currentPlayer()
        val drawResponse = game.drawTile(oldPlayer)
        assertThat(oldPlayer.rack().size).isEqualTo(drawResponse.newPlayer.rack().size - 1)
        assertThat(drawResponse.newPlayer.rack()).contains(drawResponse.tile)
        assertThat(game.drawPile.tiles().size).isEqualTo(oldDrawPileSize - 1)
    }

    @Test
    fun `player index returns new player after tile was drawn`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika"))
        val oldPlayer = game.currentPlayer()
        val drawResponse = game.drawTile(game.currentPlayer())
        assertThat(drawResponse.newPlayer.name()).isEqualTo(oldPlayer.name())
        assertThat(drawResponse.nextPlayer.name()).isEqualTo(game.currentPlayer().name())
        assertThat(game.currentPlayer().name()).isNotEqualTo(oldPlayer.name())
    }

    @Test
    fun `drawing with wrong player throws exception`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika"))
        val wrongPlayer = game.players()[1]
        assertThatThrownBy {
            game.drawTile(wrongPlayer)
        }.isInstanceOf(PlayerNotAllowedException::class.java)
    }

    @Test
    fun `Player replaces player at index 0`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika"))
        val oldPlayers = game.players()
        val newPlayer = Player("Tillmann_Modified", mutableListOf())

        val updatedPlayers = game.replacePlayer(0, newPlayer)

        assertThat(updatedPlayers).hasSize(2)
        assertThat(updatedPlayers[0]).isEqualTo(newPlayer)
        assertThat(updatedPlayers[1]).isEqualTo(oldPlayers[1].copy(points = -93))
    }

    @Test
    fun `replacePlayer preserves all other players`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika", "Anes"))
        val oldPlayers = game.players()
        val newPlayer = Player("Mika_Modified", mutableListOf())

        val updatedPlayers = game.replacePlayer(1, newPlayer)

        assertThat(updatedPlayers).hasSize(3)
        assertThat(updatedPlayers[0]).isEqualTo(oldPlayers[0].copy(points = -92))
        assertThat(updatedPlayers[1]).isEqualTo(newPlayer)
        assertThat(updatedPlayers[2]).isEqualTo(oldPlayers[2].copy(points = -94))
    }

    @Test
    fun `replacePlayer returns new list instance`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika"))
        val oldPlayers = game.players()
        val newPlayer = Player("Tillmann", mutableListOf())

        val updatedPlayers = game.replacePlayer(0, newPlayer)

        assertThat(updatedPlayers).isNotSameAs(oldPlayers)
    }

    @Test
    fun `emptyRack leads to winning game and checks if rack is truly emptied`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika", "Stefan"))

        // we need player 3 to be our current player to make a move
        game.drawTile(game.currentPlayer())
        game.drawTile(game.currentPlayer())

        val player3 = game.currentPlayer()

        val run1 = Group(
            GroupType.DiffNumberSameColor, listOf(
                Tile(TileNumber.Three, TileColor.Blue),
                Tile(TileNumber.Four, TileColor.Blue),
                Tile(TileNumber.Five, TileColor.Blue),
                Tile(TileNumber.Six, TileColor.Blue),
                Tile(TileNumber.Seven, TileColor.Blue),
                Tile(TileNumber.Eight, TileColor.Blue),
                Tile(TileNumber.Nine, TileColor.Blue),
                Tile(TileNumber.Ten, TileColor.Blue),
                Tile(TileNumber.Eleven, TileColor.Blue),
                Tile(TileNumber.Twelve, TileColor.Blue),
                Tile(TileNumber.Thirteen, TileColor.Blue),
            )
        )

        val run2 = Group(
            GroupType.DiffNumberSameColor, listOf(
                Tile(TileNumber.One, TileColor.Orange),
                Tile(TileNumber.Two, TileColor.Orange),
                Tile(TileNumber.Three, TileColor.Orange),
            )
        )

        val newBoard = Board(listOf(run1, run2))

        val testResponse = game.makeMove(player3, player3.rack(), newBoard)

        assertThat(testResponse.hasWon).isTrue
        assertThat(testResponse.newPlayer.rack()).isEmpty()
        assertThat(testResponse.nextPlayer).isNull()
        assertThat(game.finished()).isTrue
    }

    @Test
    fun `laying tiles removes them from playerRack`() {
        val game = Game.withUnShuffledDrawPile(twoPlayerNames, true)
        val laidTiles = listOf(redOne, redTwo, redThree)
        val newBoard = Board(listOf(Group(GroupType.DiffNumberSameColor, laidTiles)))
        val moveResponse = game.makeMove(game.currentPlayer(), laidTiles, newBoard)
        assertThat(moveResponse.newPlayer.rack()).doesNotContain(redOne, redTwo, redThree)
        assertThat(moveResponse.hasWon).isFalse
        assertThat(moveResponse.nextPlayer).isNotNull
        assertThat(moveResponse.nextPlayer).isEqualTo(game.currentPlayer())
        assertThat(moveResponse.nextPlayer!!.name()).isNotEqualTo(moveResponse.newPlayer.name())
        assertThat(game.finished()).isFalse
    }

    @Test
    fun `illegal player cannot make move`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika"))
        val illegalPlayer = Player("Gandalf")
        val laidTiles = listOf(redOne, redTwo, redThree)
        val newBoard = Board(listOf(Group(GroupType.DiffNumberSameColor, laidTiles)))
        assertThatThrownBy {
            game.makeMove(illegalPlayer, laidTiles, newBoard)
        }.isInstanceOf(PlayerNotAllowedException::class.java)
            .hasMessage("The provided player ${illegalPlayer.name()} is not allowed to play")
    }

    @Test
    fun `player does not win if rack has tiles left`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika"), true)

        val player0 = game.players()[0]
        val playSet = player0.rack().take(3).sortedBy { it.pointValue() }

        val newBoard = Board(listOf(Group(GroupType.DiffNumberSameColor, playSet)))

        val testResponse: Game.MoveResponse = game.makeMove(player0, playSet, newBoard)

        assertThat(testResponse.hasWon).isFalse
        assertThat(testResponse.newPlayer.rack()).hasSize(11)
    }

    @Test
    fun `initial meld with manipulated board fails`() {
        val game = Game.withUnShuffledDrawPile(setOf("Tillmann", "Mika"))
        val board = Board(listOf(Group(GroupType.DiffNumberSameColor, listOf(redEleven, redTwelve, redThirteen))))
        val laidTiles = listOf(redSeven, redEight, redNine, redTen)
        assertThatThrownBy {
            game.makeMove(
                game.currentPlayer(),
                laidTiles,
                board
            )
        }.isInstanceOf(IllegalFirstMoveException::class.java)
    }

    @Test
    fun `makeMove should fail when initial meld is below 30 points`() {
        val player1 = Player(
            "Tillmann", mutableListOf(
                redOne, redTwo, redThree, redFour, redFive, blueTwo, blueThree, blueFour, blueFive
            )
        )
        val player2 = Player("Mika", mutableListOf(blueOne))
        val game = Game.withDefinedPlayers(listOf(player1, player2))

        val laidTiles = player1.rack()
        val newBoard = Board(
            listOf(
                Group(GroupType.DiffNumberSameColor, listOf(redOne, redTwo, redThree, redFour, redFive)),
                Group(GroupType.DiffNumberSameColor, listOf(blueTwo, blueThree, blueFour, blueFive)),
            )
        )

        assertThatThrownBy { game.makeMove(player1, laidTiles, newBoard) }
            .isInstanceOf(IllegalFirstMoveException::class.java)
            .hasMessage(
                "Player ${
                    game.currentPlayer().name()
                }’s first move is not valid (30 points, board not modified)"
            )
    }

    @Test
    fun `makeMove should succeed when initial meld is exactly 30 points`() {
        val player1 = Player(
            "Tillmann", mutableListOf(
                redFour, redFive, redSix, redSeven, redEight
            )
        )
        val player2 = Player("Mika", mutableListOf(blueOne))
        val game = Game.withDefinedPlayers(listOf(player1, player2))

        val laidTiles = player1.rack()
        val newBoard = Board(
            listOf(
                Group(GroupType.DiffNumberSameColor, listOf(redFour, redFive, redSix, redSeven, redEight)),
            )
        )

        val gameResponse = game.makeMove(player1, laidTiles, newBoard)

        assertThat(gameResponse.hasWon).isTrue
        assertThat(gameResponse.newPlayer.rack()).isEmpty()
    }

    @Test
    fun `makeMove should succeed when initial meld is above 30 points`() {
        val player1 = Player(
            "Tillmann", mutableListOf(
                redTwo, redThree, redFour, blueFour, blueFive, blueSix, blueSeven
            )
        )
        val player2 = Player("Mika", mutableListOf(blueOne))
        val game = Game.withDefinedPlayers(listOf(player1, player2))

        val laidTiles = player1.rack()
        val newBoard = Board(
            listOf(
                Group(GroupType.DiffNumberSameColor, listOf(redTwo, redThree, redFour)),
                Group(GroupType.DiffNumberSameColor, listOf(blueFour, blueFive, blueSix, blueSeven))
            )
        )
        val gameResponse = game.makeMove(player1, laidTiles, newBoard)

        assertThat(gameResponse.hasWon).isTrue
    }

    @Test
    fun `makeMove should succeed when initial meld is above 30 points with joker`() {
        val player1 = Player(
            "Tillmann", mutableListOf(
                redTwo, JokerTile, redFour, JokerTile, blueFive, blueSix, blueSeven
            )
        )
        val player2 = Player("Mika", mutableListOf(blueOne))
        val game = Game.withDefinedPlayers(listOf(player1, player2))

        val laidTiles = player1.rack()
        val newBoard = Board(
            listOf(
                Group(GroupType.DiffNumberSameColor, listOf(redTwo, JokerTile, redFour)),
                Group(GroupType.DiffNumberSameColor, listOf(JokerTile, blueFive, blueSix, blueSeven))
            )
        )
        val gameResponse = game.makeMove(player1, laidTiles, newBoard)

        assertThat(gameResponse.hasWon).isTrue
    }


    @Test
    fun `check sum at end of game, joker included`() {
        val player1 = Player(
            "Tillmann", mutableListOf(
                redTwo, redThree, redFour, blueFour, blueFive, blueSix, blueSeven
            )
        )
        val player2 = Player("Mika", mutableListOf(blueOne, blueTwo, blueThree, JokerTile))
        val game = Game.withDefinedPlayers(listOf(player1, player2))

        val laidTiles = player1.rack()
        val newBoard = Board(
            listOf(
                Group(GroupType.DiffNumberSameColor, listOf(redTwo, redThree, redFour)),
                Group(GroupType.DiffNumberSameColor, listOf(blueFour, blueFive, blueSix, blueSeven))
            )
        )
        val gameResponse = game.makeMove(player1, laidTiles, newBoard)

        assertThat(gameResponse.hasWon).isTrue
        assertThat(game.finished()).isTrue
        assertThat(game.winningPlayer()).isEqualTo(gameResponse.newPlayer.copy(points = 56))
        assertThat(game.players()[1].points()).isEqualTo(-56)
    }

    @Test
    fun `makeMove should fail when game is over`() {
        val player1 = Player(
            "Tillmann", mutableListOf(
                redTwo, JokerTile, redFour, JokerTile, blueFive, blueSix, blueSeven
            )
        )
        val player2 = Player("Mika", mutableListOf(blueEight))
        val game = Game.withDefinedPlayers(listOf(player1, player2))

        val laidTiles = player1.rack()
        val newBoard = Board(
            listOf(
                Group(GroupType.DiffNumberSameColor, listOf(redTwo, JokerTile, redFour)),
                Group(GroupType.DiffNumberSameColor, listOf(JokerTile, blueFive, blueSix, blueSeven))
            )
        )

        game.makeMove(player1, laidTiles, newBoard)

        val laidTiles2 = player2.rack()
        val newBoard2 = Board(
            listOf(
                Group(GroupType.DiffNumberSameColor, listOf(redTwo, JokerTile, redFour)),
                Group(GroupType.DiffNumberSameColor, listOf(JokerTile, blueFive, blueSix, blueSeven, blueEight))
            )
        )

        assertThatThrownBy { game.makeMove(player2, laidTiles2, newBoard2) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Game has finished (one player has won)")
    }
}

