package com.emilionavarro.tictactoemasterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[TicTacToeViewModel::class.java]
        setContent {
            MaterialTheme {
                TicTacToeScreen(viewModel)
            }
        }
    }
}

@Composable
private fun TicTacToeScreen(viewModel: TicTacToeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val game = uiState.game
    val statusText = when (uiState.mode) {
        GameMode.TWO_PLAYERS -> when (game.status) {
            GameStatus.IN_PROGRESS -> stringResource(R.string.turn_label, game.currentPlayer.name)
            GameStatus.X_WON -> stringResource(R.string.winner_label, Player.X.name)
            GameStatus.O_WON -> stringResource(R.string.winner_label, Player.O.name)
            GameStatus.DRAW -> stringResource(R.string.draw_label)
        }
        GameMode.VS_COMPUTER -> when (game.status) {
            GameStatus.IN_PROGRESS -> stringResource(R.string.your_turn)
            GameStatus.X_WON -> stringResource(R.string.you_won)
            GameStatus.O_WON -> stringResource(R.string.computer_won)
            GameStatus.DRAW -> stringResource(R.string.draw_label)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(R.string.game_title),
                style = MaterialTheme.typography.headlineMedium
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = uiState.mode == GameMode.TWO_PLAYERS,
                    onClick = { viewModel.setMode(GameMode.TWO_PLAYERS) },
                    label = { Text(text = stringResource(R.string.two_players_mode)) }
                )
                FilterChip(
                    selected = uiState.mode == GameMode.VS_COMPUTER,
                    onClick = { viewModel.setMode(GameMode.VS_COMPUTER) },
                    label = { Text(text = stringResource(R.string.computer_mode)) }
                )
            }
            Text(
                text = statusText,
                style = MaterialTheme.typography.titleMedium
            )
            for (row in 0 until 3) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    for (column in 0 until 3) {
                        val position = row * 3 + column
                        val mark = game.board[position]
                        val cellDescription = stringResource(
                            R.string.cell_accessibility,
                            position + 1,
                            mark?.name ?: stringResource(R.string.empty_cell)
                        )
                        OutlinedButton(
                            onClick = { viewModel.placeMark(position) },
                            enabled = game.status == GameStatus.IN_PROGRESS && mark == null &&
                                (uiState.mode == GameMode.TWO_PLAYERS || game.currentPlayer == Player.X),
                            modifier = Modifier
                                .size(88.dp)
                                .semantics { contentDescription = cellDescription },
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = mark?.name.orEmpty(),
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                    }
                }
            }
            Button(onClick = viewModel::resetGame) {
                Text(text = stringResource(R.string.restart_game))
            }
        }
    }
}