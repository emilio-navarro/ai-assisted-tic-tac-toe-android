package com.emilionavarro.tictactoemasterclass

import org.junit.Assert.assertEquals
import org.junit.Test

class TicTacToeViewModelTest {
    @Test
    fun computerMakesAReplyAfterHumanMove() {
        val viewModel = TicTacToeViewModel()
        viewModel.setMode(GameMode.VS_COMPUTER)

        viewModel.placeMark(0)

        val game = viewModel.uiState.value.game
        assertEquals(Player.X, game.board[0])
        assertEquals(1, game.board.count { it == Player.O })
        assertEquals(Player.X, game.currentPlayer)
    }

    @Test
    fun changingModeStartsANewGame() {
        val viewModel = TicTacToeViewModel()
        viewModel.placeMark(0)

        viewModel.setMode(GameMode.VS_COMPUTER)

        assertEquals(GameMode.VS_COMPUTER, viewModel.uiState.value.mode)
        assertEquals(TicTacToeGame(), viewModel.uiState.value.game)
    }
}