package com.emilionavarro.tictactoemasterclass

import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class TicTacToeGameTest {
    @Test
    fun playersAlternateAfterValidMoves() {
        val game = TicTacToeGame()
            .placeMark(0)
            .placeMark(4)

        assertEquals(Player.X, game.board[0])
        assertEquals(Player.O, game.board[4])
        assertEquals(Player.X, game.currentPlayer)
        assertEquals(GameStatus.IN_PROGRESS, game.status)
    }

    @Test
    fun detectsWin() {
        val game = TicTacToeGame()
            .placeMark(0)
            .placeMark(3)
            .placeMark(1)
            .placeMark(4)
            .placeMark(2)

        assertEquals(GameStatus.X_WON, game.status)
    }

    @Test
    fun detectsDraw() {
        val game = TicTacToeGame()
            .placeMark(0)
            .placeMark(1)
            .placeMark(2)
            .placeMark(4)
            .placeMark(3)
            .placeMark(5)
            .placeMark(7)
            .placeMark(6)
            .placeMark(8)

        assertEquals(GameStatus.DRAW, game.status)
    }

    @Test
    fun ignoresOccupiedAndOutOfRangePositions() {
        val game = TicTacToeGame().placeMark(0)

        assertSame(game, game.placeMark(0))
        assertSame(game, game.placeMark(-1))
        assertSame(game, game.placeMark(9))
    }

    @Test
    fun resetStartsANewGame() {
        val reset = TicTacToeGame().placeMark(0).reset()

        assertEquals(TicTacToeGame(), reset)
    }
}