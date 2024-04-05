package com.example.ultimatetictactoe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ultimatetictactoe.R
import com.example.ultimatetictactoe.WindowSize

@Composable
fun MultiplayerScreen(
    windowSize: WindowSize,
    modifier: Modifier = Modifier,
    playGame: (Int) -> Unit,
    lockUnlockBoards: (Int) -> Unit,
    resetGame: () -> Unit,
    ticUIState: GameUIState
) {
    if (windowSize == WindowSize.TabletLandscape || windowSize == WindowSize.PhoneLandScape){
        Row (
           modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
                    NewBigGrid(
                        ticUIState = ticUIState,
                        playGame = { playGame(it) },
                        lockUnlockBoards = { lockUnlockBoards(it) },
                        gridCardSize =if (windowSize == WindowSize.TabletLandscape)R.dimen.grid_card_size_medium
                        else R.dimen.grid_card_size_compact,
                        xoCardSize = if (windowSize == WindowSize.TabletLandscape)R.dimen.xo_card_size_medium
                        else R.dimen.xo_card_size_compact,
                        padding = if (windowSize == WindowSize.PhoneLandScape) 10
                        else 40,
                        modifier = Modifier.weight(2f),
                        cardScale = 1.2f
                    )

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                PlayerTurnText(ticUIState = ticUIState)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                )
                ResetButton(
                    resetGame = resetGame
                )
            }
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PlayerTurnText(ticUIState = ticUIState)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
            NewBigGrid(
                ticUIState = ticUIState,
                playGame = { playGame(it) },
                lockUnlockBoards = { lockUnlockBoards(it) },
                gridCardSize = if (windowSize != WindowSize.TabletPortrait ) R.dimen.grid_card_size_compact
                else R.dimen.grid_card_size_medium,
                xoCardSize = if (windowSize != WindowSize.TabletPortrait) R.dimen.xo_card_size_compact
                else R.dimen.xo_card_size_medium,
                padding = if (windowSize != WindowSize.Fold) 0
                else 70,
                cardScale = if(windowSize != WindowSize.TabletPortrait) 1.2f
                else 1f
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )

            ResetButton(
                resetGame = resetGame
            )

        }
    }

}

@Composable
fun PlayerTurnText(
    ticUIState: GameUIState
) {
    Text(
        text = if (ticUIState.isGameOver) {
            if (ticUIState.winner == 1) stringResource(
                id = R.string.game_over,
                ticUIState.player1Name
            )
            else if (ticUIState.winner == 2) stringResource(
                id = R.string.game_over,
                ticUIState.player2Name
            )
            else stringResource(id = R.string.game_over_tie)
        } else {
            if (ticUIState.areBoardsUnlocked) {
                if (ticUIState.playerTurn == 1) stringResource(
                    id = R.string.player_choose_board,
                    ticUIState.player1Name
                )
                else stringResource(id = R.string.player_choose_board, ticUIState.player2Name)
            } else {
                if (ticUIState.playerTurn == 1) stringResource(
                    id = R.string.player_turn,
                    ticUIState.player1Name
                )
                else stringResource(id = R.string.player_turn, ticUIState.player2Name)
            }
        },
        style = MaterialTheme.typography.labelMedium,
        maxLines = 1
    )
}

@Composable
fun ResetButton(
    resetGame: () -> Unit
) {
    Button(
        onClick = resetGame,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier.width(dimensionResource(id = R.dimen.button_width))
    ) {
        Text(
            text = stringResource(id = R.string.reset_game),
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun MultiPlayerScreenPreview() {
    //  MultiplayerScreen()
}