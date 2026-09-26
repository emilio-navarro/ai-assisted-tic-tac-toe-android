package com.emilionavarro.tictactoemasterclass

enum class Player {
    X,
    O;

    fun next(): Player = if (this == X) O else X
}

enum class GameStatus {
    IN_PROGRESS,
    X_WON,
    O_WON,
    DRAW
}

data class TicTacToeGame(
    val board: List<Player?> = List(BOARD_SIZE) { null },
    val currentPlayer: Player = Player.X,
    val status: GameStatus = GameStatus.IN_PROGRESS
) {
    fun placeMark(position: Int): TicTacToeGame {
        if (status != GameStatus.IN_PROGRESS || position !in board.indices || board[position] != null) {
            return this
        }

        val updatedBoard = board.toMutableList().apply { this[position] = currentPlayer }
        val updatedStatus = findWinner(updatedBoard)?.let { winner ->
            if (winner == Player.X) GameStatus.X_WON else GameStatus.O_WON
        } ?: if (updatedBoard.none { it == null }) {
            GameStatus.DRAW
        } else {
            GameStatus.IN_PROGRESS
        }

        return copy(
            board = updatedBoard,
            currentPlayer = if (updatedStatus == GameStatus.IN_PROGRESS) currentPlayer.next() else currentPlayer,
            status = updatedStatus
        )
    }

    fun reset(): TicTacToeGame = TicTacToeGame()

    private fun findWinner(cells: List<Player?>): Player? = WINNING_LINES
        .firstNotNullOfOrNull { line ->
            cells[line[0]]?.takeIf { mark -> line.all { cells[it] == mark } }
        }

    private companion object {
        const val BOARD_SIZE = 9

        val WINNING_LINES = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
    }
}