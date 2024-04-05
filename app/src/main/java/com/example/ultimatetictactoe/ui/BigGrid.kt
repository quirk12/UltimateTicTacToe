package com.example.ultimatetictactoe.ui

import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ultimatetictactoe.R
import com.example.ultimatetictactoe.data.SmallGameBoard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewBigGrid(
    modifier: Modifier = Modifier,
    playGame: (Int) -> Unit,
    lockUnlockBoards: (Int) -> Unit,
    ticUIState: GameUIState,
   @DimenRes xoCardSize: Int,
    @DimenRes gridCardSize: Int,
    padding: Int,
    cardScale: Float
) {
    val weights: List<Float> = listOf(0.7f, 0.7f, 1.2f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f)
    val context = LocalContext.current

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
           .padding(
               horizontal = padding.dp
           )
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            itemsIndexed(weights) { index, weight ->
                val scale by animateFloatAsState(targetValue =
                if (ticUIState.areBoardsUnlocked || ticUIState.isGameOver) 1f
                else {
                    if (index == ticUIState.activeBoardIndex) cardScale
                    else 0.7f
                }
                    , label = "",)

                if (ticUIState.areBoardsUnlocked || ticUIState.bigGameBoards[index].isBoardTaken() || ticUIState.isGameOver) {

                    if (ticUIState.areBoardsUnlocked && !(ticUIState.bigGameBoards[index].isBoardTaken()) && !ticUIState.isGameOver) {
                        BigGridCardActive(
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 20.dp)
                                .scale(scale),
                            board = ticUIState.bigGameBoards[index],
                            onClick0 = { lockUnlockBoards(index) },
                            onClick1 = { lockUnlockBoards(index) },
                            onClick2 = { lockUnlockBoards(index) },
                            onClick3 = { lockUnlockBoards(index) },
                            onClick4 = { lockUnlockBoards(index) },
                            onClick5 = { lockUnlockBoards(index) },
                            onClick6 = { lockUnlockBoards(index) },
                            onClick7 = { lockUnlockBoards(index) },
                            onClick8 = { lockUnlockBoards(index) },
                            bigCardSize = gridCardSize,
                            xoCardSize = xoCardSize
                        )
                    }
                    else {
                        BigGridCardInActive(
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 20.dp)
                                .scale(scale),
                            onClick ={
                                if ((!ticUIState.bigGameBoards[index].isBoardTaken())) {
                                    lockUnlockBoards(index)
                                } else {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.board_is_taken),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            winner = ticUIState.bigGameBoards[index].getWinner(),
                            bigCardSize = gridCardSize
                        )
                    }

                } else {
                    BigGridCardActive(
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 20.dp)
                            .scale(scale),
                        onClick0 = { if (ticUIState.activeBoardIndex == index
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(0) != 1
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(0) != 2 )
                            playGame(0)  },
                        onClick1 = { if (ticUIState.activeBoardIndex == index
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(1) != 1
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(1) != 2 )
                            playGame(1)  },
                        onClick2 = { if (ticUIState.activeBoardIndex == index
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(2) != 1
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(2) != 2 )
                            playGame(2)  },
                        onClick3 = { if (ticUIState.activeBoardIndex == index
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(3) != 1
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(3) != 2 )
                            playGame(3)  },
                        onClick4 = { if (ticUIState.activeBoardIndex == index
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(4) != 1
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(4) != 2 )
                            playGame(4)  },
                        onClick5 = { if (ticUIState.activeBoardIndex == index
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(5) != 1
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(5) != 2 )
                            playGame(5)  },
                        onClick6 = { if (ticUIState.activeBoardIndex == index
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(6) != 1
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(6) != 2 )
                            playGame(6)  },
                        onClick7 = { if (ticUIState.activeBoardIndex == index
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(7) != 1
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(7) != 2 )
                            playGame(7)  },
                        onClick8 = { if (ticUIState.activeBoardIndex == index
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(8) != 1
                            && ticUIState.bigGameBoards[index].getSmallBoardPosition(8) != 2 )
                            playGame(8)  },
                        board = ticUIState.bigGameBoards[index],
                        bigCardSize = gridCardSize,
                        xoCardSize = xoCardSize

                    )
                }

            }

        }
    }

}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BigGridCardActive(
    @DimenRes bigCardSize: Int,
    @DimenRes xoCardSize: Int,
    board: SmallGameBoard,
    onClick0: () -> Unit,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick3: () -> Unit,
    onClick4: () -> Unit,
    onClick5: () -> Unit,
    onClick6: () -> Unit,
    onClick7: () -> Unit,
    onClick8: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .size(dimensionResource(id = bigCardSize))
                ,
        colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.secondary)
    ) {
        Grid(
            board = board,
            onClick0 = onClick0,
            onClick1 = onClick1,
            onClick2 = onClick2,
            onClick3 = onClick3,
            onClick4 = onClick4,
            onClick5 = onClick5,
            onClick6 = onClick6,
            onClick7 = onClick7,
            onClick8 = onClick8,
            cardSize = xoCardSize
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BigGridCardInActive(
    @DimenRes bigCardSize: Int,
    winner: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = MaterialTheme.shapes.large,
        modifier = modifier.size(dimensionResource(id = bigCardSize)),
        colors = when (winner) {
            1 -> CardDefaults.elevatedCardColors(colorResource(id = R.color.x_color))
            2 -> CardDefaults.elevatedCardColors(colorResource(id = R.color.o_color))
            3 -> CardDefaults.elevatedCardColors(colorResource(id = R.color.tie_color))
            else -> CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.secondary)
        },
        onClick = onClick
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            when (winner) {
                1 -> Image(
                    painter = painterResource(id = R.drawable.x_shape),
                    contentDescription = null
                )

                2 -> Image(
                    painter = painterResource(id = R.drawable.o_shape),
                    contentDescription = null
                )

                3 -> Image(
                    painter = painterResource(id = R.drawable.tie_shape),
                    contentDescription = null
                )
            }
        }

    }
}

@Preview
@Composable
fun BigGridPreview() {
    //   BigGrid()
}

@Preview
@Composable
fun BigGridCardPreview() {
    //  BigGridCard()
}

@Preview
@Composable
fun BigGridInactiveCardPreview() {
    // BigGridCardInActive()
}

@Preview
@Composable
fun BigGridRowPreview() {
    //  BigGridRow()
}

