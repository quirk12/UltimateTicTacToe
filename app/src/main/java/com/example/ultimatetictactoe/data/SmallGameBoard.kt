package com.example.ultimatetictactoe.data

class SmallGameBoard {
    private var smallBoard =  IntArray(9) {-1}
    private var winner: Int = -1
    private var moveCounter = 9
    private var isBoardTaken:Boolean = false

    fun playSmallBoard(playerTurn: Int, index: Int) {
        smallBoard[index] = playerTurn
        moveCounter--
        checkWinner(playerTurn)
    }

    fun isBoardTaken(): Boolean {
        return isBoardTaken
    }

    fun getWinner():Int {
        return winner
    }

    fun checkWinner(playerTurn: Int) {
        if (moveCounter == 0) {
            winner = 3
            isBoardTaken = true
        }

            if (smallBoard[0] == playerTurn && smallBoard[1] == playerTurn && smallBoard[2] == playerTurn) {
                winner = playerTurn
                isBoardTaken = true

            } else if (smallBoard[3] == playerTurn && smallBoard[4] == playerTurn && smallBoard[5] == playerTurn) {
                winner = playerTurn
                isBoardTaken = true

            } else if (smallBoard[6] == playerTurn && smallBoard[7] == playerTurn && smallBoard[8] == playerTurn) {
                winner = playerTurn
                isBoardTaken = true

            } else if (smallBoard[0] == playerTurn && smallBoard[3] == playerTurn && smallBoard[6] == playerTurn) {
                winner = playerTurn
                isBoardTaken = true

            } else if (smallBoard[1] == playerTurn && smallBoard[4] == playerTurn && smallBoard[7] == playerTurn) {
                winner = playerTurn
                isBoardTaken = true

            } else if (smallBoard[2] == playerTurn && smallBoard[5] == playerTurn && smallBoard[8] == playerTurn) {
                winner = playerTurn
                isBoardTaken = true

            } else if (smallBoard[0] == playerTurn && smallBoard[4] == playerTurn && smallBoard[8] == playerTurn) {
                winner = playerTurn
                isBoardTaken = true

            } else if (smallBoard[2] == playerTurn && smallBoard[4] == playerTurn && smallBoard[6] == playerTurn) {
                winner = playerTurn
                isBoardTaken = true

            }
    }

    fun resetSmallBoard(){
        for (i in 0..8) {
            smallBoard[i] = -1
        }
        moveCounter = 9
        winner = -1
        isBoardTaken = false
    }

    fun getSmallBoard(): IntArray {
        return smallBoard
    }

    fun getSmallBoardPosition(position: Int): Int {
        return smallBoard[position]
    }

}
