package com.emilionavarro.tictactoemasterclass

object TicTacToeAi {
    fun chooseMove(game: TicTacToeGame): Int? {
        if (game.status != GameStatus.IN_PROGRESS || game.currentPlayer != Player.O) {
            return null
        }

        var bestMove: Int? = null
        var bestScore = Int.MIN_VALUE
        var alpha = Int.MIN_VALUE

        for (position in game.board.indices) {
            if (game.board[position] != null) continue

            val score = minimax(game.placeMark(position), depth = 1, alpha = alpha, beta = Int.MAX_VALUE)
            if (score > bestScore) {
                bestScore = score
                bestMove = position
            }
            alpha = maxOf(alpha, bestScore)
        }

        return bestMove
    }

    private fun minimax(game: TicTacToeGame, depth: Int, alpha: Int, beta: Int): Int {
        when (game.status) {
            GameStatus.O_WON -> return 10 - depth
            GameStatus.X_WON -> return depth - 10
            GameStatus.DRAW -> return 0
            GameStatus.IN_PROGRESS -> Unit
        }

        return if (game.currentPlayer == Player.O) {
            maximize(game, depth, alpha, beta)
        } else {
            minimize(game, depth, alpha, beta)
        }
    }

    private fun maximize(game: TicTacToeGame, depth: Int, alpha: Int, beta: Int): Int {
        var bestScore = Int.MIN_VALUE
        var currentAlpha = alpha

        for (position in game.board.indices) {
            if (game.board[position] != null) continue

            bestScore = maxOf(bestScore, minimax(game.placeMark(position), depth + 1, currentAlpha, beta))
            currentAlpha = maxOf(currentAlpha, bestScore)
            if (currentAlpha >= beta) break
        }

        return bestScore
    }

    private fun minimize(game: TicTacToeGame, depth: Int, alpha: Int, beta: Int): Int {
        var bestScore = Int.MAX_VALUE
        var currentBeta = beta

        for (position in game.board.indices) {
            if (game.board[position] != null) continue

            bestScore = minOf(bestScore, minimax(game.placeMark(position), depth + 1, alpha, currentBeta))
            currentBeta = minOf(currentBeta, bestScore)
            if (currentBeta <= alpha) break
        }

        return bestScore
    }
}