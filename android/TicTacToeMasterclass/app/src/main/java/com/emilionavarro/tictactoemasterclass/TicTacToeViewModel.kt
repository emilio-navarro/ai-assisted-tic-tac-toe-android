package com.emilionavarro.tictactoemasterclass

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class GameMode {
    TWO_PLAYERS,
    VS_COMPUTER
}

data class TicTacToeUiState(
    val game: TicTacToeGame = TicTacToeGame(),
    val mode: GameMode = GameMode.TWO_PLAYERS
)

class TicTacToeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TicTacToeUiState())
    val uiState = _uiState.asStateFlow()

    fun placeMark(position: Int) {
        val currentState = _uiState.value
        if (currentState.mode == GameMode.VS_COMPUTER && currentState.game.currentPlayer != Player.X) {
            return
        }

        var updatedGame = currentState.game.placeMark(position)
        if (currentState.mode == GameMode.VS_COMPUTER && updatedGame.status == GameStatus.IN_PROGRESS) {
            val computerMove = TicTacToeAi.chooseMove(updatedGame)
            if (computerMove != null) {
                updatedGame = updatedGame.placeMark(computerMove)
            }
        }

        _uiState.value = currentState.copy(game = updatedGame)
    }

    fun setMode(mode: GameMode) {
        val currentState = _uiState.value
        if (mode != currentState.mode) {
            _uiState.value = currentState.copy(game = TicTacToeGame(), mode = mode)
        }
    }

    fun resetGame() {
        _uiState.value = _uiState.value.copy(game = _uiState.value.game.reset())
    }
}