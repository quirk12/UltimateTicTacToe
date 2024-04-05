package com.example.ultimatetictactoe.ui

import com.example.ultimatetictactoe.data.SmallGameBoard

data class GameUIState(
    val activeBoardIndex: Int = 1,
    val bigGameBoards: Array<SmallGameBoard> = Array(9) { SmallGameBoard() },
    val playerTurn: Int = 1,
    val areBoardsUnlocked: Boolean = true,
    val isGameOver: Boolean = false,
    val winner: Int = -1,
    val isInstructionsDialogShow: Boolean = false,
    val isPlayerNamesScreenShown: Boolean = false,
    val player1Name: String = "",
    val player2Name: String = "",
    )
