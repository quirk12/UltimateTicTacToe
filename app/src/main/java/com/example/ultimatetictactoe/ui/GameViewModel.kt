package com.example.ultimatetictactoe.ui

import androidx.lifecycle.ViewModel
import com.example.ultimatetictactoe.data.SmallGameBoard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GameUIState())
    val uiState: StateFlow<GameUIState> = _uiState.asStateFlow()
  //  private var activeBoardIndex: Int = 0
    private var bigGameBoards = Array(9) { SmallGameBoard() }
 //   private var playerTurn = 1
    private var winningBoard: SmallGameBoard = SmallGameBoard()
 // private var areBoardsUnlocked: Boolean = true
 // private var isGameOver: Boolean = false
    init {resetGame()}

    fun updateWinningBoard(activeBoard: Int) {
        if (bigGameBoards[activeBoard].isBoardTaken()) {
            winningBoard.playSmallBoard(bigGameBoards[activeBoard].getWinner(), index = activeBoard)
        }
    }

    fun playGame(index: Int) {
        val activeBoard = uiState.value.activeBoardIndex
        val playerTurn = uiState.value.playerTurn
        bigGameBoards[activeBoard].playSmallBoard(playerTurn, index)
        updateWinningBoard(activeBoard)
        updateUIStateBigBoard()
        _uiState.update { currentState ->
            if (playerTurn == 1) {
                currentState.copy(playerTurn = 2)
            }
            else {
                currentState.copy(playerTurn = 1)
            }
        }
        updateUIStateBigBoard()
        checkWinner()
        lockUnlockAndSetActiveBoards(index = index)
    }

    fun lockUnlockAndSetActiveBoards(index: Int){
        if (!bigGameBoards[index].isBoardTaken()){
            updateUIStateActiveBoardIndex(index = index)
            _uiState.update { currentState ->
                currentState.copy(areBoardsUnlocked = false )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(areBoardsUnlocked = true )
            }
        }
    }

    fun checkWinner(){
        if (winningBoard.isBoardTaken()){
            _uiState.update { currentState ->
                currentState.copy(
                    isGameOver = true,
                    winner = winningBoard.getWinner()
                    )
            }
        }
    }

    fun updateUIStateBigBoard(){
        _uiState.update { currentState ->
            currentState.copy(bigGameBoards = bigGameBoards )
        }
    }
    fun updateUIStateActiveBoardIndex(index: Int){
        _uiState.update { currentState ->
            currentState.copy(activeBoardIndex = index )
        }
    }

    fun resetGame(){
        val player1Name = _uiState.value.player1Name
        val player2Name = _uiState.value.player2Name
        _uiState.value = GameUIState()
        winningBoard.resetSmallBoard()
        for (board in bigGameBoards){
            board.resetSmallBoard()
        }
        updatePlayersNames(
            player = 1,
            playerName = player1Name
        )
        updatePlayersNames(
            player = 2,
            playerName = player2Name
        )
    }

    fun showInstructionsScreen(shownState: Boolean){
        _uiState.update { currentState ->
            currentState.copy(isInstructionsDialogShow = shownState)
        }
    }

    fun showPlayerNamesScreen(shownState: Boolean){
        _uiState.update { currentState ->
            currentState.copy(isPlayerNamesScreenShown = shownState)
        }
    }

    fun updatePlayersNames(player: Int, playerName: String) {

            if(player == 1) {
                _uiState.update {currentState ->
                    currentState.copy(
                        player1Name = playerName
                    )
                }
            }
            else if (player == 2) {
                _uiState.update {currentState ->
                    currentState.copy(
                        player2Name = playerName
                    )
                }
            }
        }
  /*  fun dummyChangeName(){
        if(_uiState.value.player1Name.lowercase() == "mariam"
            || _uiState.value.player1Name.lowercase() == "maryam"
            || _uiState.value.player1Name.lowercase() == "maryeam"
            || _uiState.value.player1Name.lowercase() == "mariyam"
            || _uiState.value.player1Name.lowercase() == "meriem"
            || _uiState.value.player1Name.lowercase() == "miryam"
            || _uiState.value.player1Name.lowercase() == "meryem"
            || _uiState.value.player1Name.lowercase() == "mryam"
            || _uiState.value.player1Name.lowercase() == "miriam"



        ){
            val player = _uiState.value.player1Name
            _uiState.update {currentState ->
                currentState.copy(
                    player1Name = "Dumb $player"
                )
            }
        }
        if(_uiState.value.player2Name.lowercase() == "mariam"
            || _uiState.value.player1Name.lowercase() == "maryam"
            || _uiState.value.player1Name.lowercase() == "maryeam"
            || _uiState.value.player1Name.lowercase() == "mariyam"
            || _uiState.value.player1Name.lowercase() == "meriem"
            || _uiState.value.player1Name.lowercase() == "miryam"
            || _uiState.value.player1Name.lowercase() == "meryem"
            || _uiState.value.player1Name.lowercase() == "mryam"
            || _uiState.value.player1Name.lowercase() == "miriam"

        ){
            val player = _uiState.value.player2Name
            _uiState.update {currentState ->
                currentState.copy(
                    player2Name = "Dumb $player"
                )
            }
        }

    } */

    }



