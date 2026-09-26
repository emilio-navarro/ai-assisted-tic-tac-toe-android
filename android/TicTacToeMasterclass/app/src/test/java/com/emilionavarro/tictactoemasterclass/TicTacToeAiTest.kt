package com.emilionavarro.tictactoemasterclass

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TicTacToeAiTest {
    @Test
    fun takesAnAvailableImmediateWinningMove() {
        val game = gameAfter(0, 3, 1, 4, 8)

        assertEquals(Player.O, game.currentPlayer)
        assertEquals(5, TicTacToeAi.chooseMove(game))
    }

    @Test
    fun blocksTheOpponentsOnlyImmediateThreat() {
        val game = gameAfter(0, 4, 1, 8, 6)

        assertEquals(Player.O, game.currentPlayer)
        assertEquals(2, TicTacToeAi.chooseMove(game))
    }

    @Test
    fun neverLosesAgainstAnySequenceOfHumanMoves() {
        assertTrue(computerAvoidsLossAgainstEveryHumanReply(TicTacToeGame()))
    }

    private fun gameAfter(vararg positions: Int): TicTacToeGame = positions.fold(TicTacToeGame()) { game, position ->
        game.placeMark(position)
    }

    private fun computerAvoidsLossAgainstEveryHumanReply(game: TicTacToeGame): Boolean {
        if (game.status != GameStatus.IN_PROGRESS) {
            return game.status != GameStatus.X_WON
        }

        if (game.currentPlayer == Player.O) {
            val computerMove = TicTacToeAi.chooseMove(game) ?: return false
            return computerAvoidsLossAgainstEveryHumanReply(game.placeMark(computerMove))
        }

        return game.board.indices
            .filter { game.board[it] == null }
            .all { position -> computerAvoidsLossAgainstEveryHumanReply(game.placeMark(position)) }
    }
}