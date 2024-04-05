package com.example.ultimatetictactoe.ui

import androidx.annotation.DimenRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ultimatetictactoe.R
import com.example.ultimatetictactoe.data.SmallGameBoard

@Composable
fun Grid(
    @DimenRes cardSize: Int,
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
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small))
    ) {
        GridRow(
            onClick01 = onClick0,
            onClick02 = onClick1,
            onClick03 = onClick2,
            position1 = board.getSmallBoardPosition(0),
            position2 = board.getSmallBoardPosition(1),
            position3 = board.getSmallBoardPosition(2),
            cardSize = cardSize

            )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        )
        GridRow(
            onClick01 = onClick3,
            onClick02 = onClick4,
            onClick03 = onClick5,
            position1 = board.getSmallBoardPosition(3),
            position2 = board.getSmallBoardPosition(4),
            position3 = board.getSmallBoardPosition(5),
            cardSize = cardSize
            )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        )
        GridRow(
            onClick01 = onClick6,
            onClick02 = onClick7,
            onClick03 = onClick8,
            position1 = board.getSmallBoardPosition(6),
            position2 = board.getSmallBoardPosition(7),
            position3 = board.getSmallBoardPosition(8),
            cardSize = cardSize

            )
    }
}

@Composable
fun GridRow(
    @DimenRes cardSize: Int,
    position1: Int,
    position2: Int,
    position3: Int,
    onClick01: () -> Unit,
    onClick02: () -> Unit,
    onClick03: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_extra_small))
    ) {
        XOCard(
            onClick = onClick01,
            position = position1,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_extra_small)),
            cardSize = cardSize
        )
        Divider( modifier = Modifier
            .height(dimensionResource(id = cardSize))
            .width(2.dp)
        )
        XOCard(
            onClick = onClick02,
            position = position2,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_extra_small)),
            cardSize = cardSize
        )
        Divider( modifier = Modifier
            .height(dimensionResource(id = cardSize))
            .width(2.dp)
        )
        XOCard(
            onClick = onClick03,
            position = position3,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_extra_small)),
            cardSize = cardSize
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XOCard(
    @DimenRes cardSize: Int,
    onClick: () -> Unit,
    position: Int,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    Card (
        elevation = when (position) {
            1-> CardDefaults.cardElevation(4.dp)
            2-> CardDefaults.cardElevation(4.dp)
            else -> CardDefaults.cardElevation(0.dp)
        },
        colors = when (position) {
            1-> CardDefaults.cardColors(colorResource(id = R.color.x_color))
            2-> CardDefaults.cardColors(colorResource(id = R.color.o_color))
            else -> CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
                                 },
        shape = MaterialTheme.shapes.extraSmall,
        onClick = onClick
        ,
        modifier = Modifier
            .size(dimensionResource(id = cardSize))
            .then(modifier)
    ) {
        when (position) {
            1->   Image(painter = painterResource(id = R.drawable.x_shape), contentDescription = null )
            2->   Image(painter = painterResource(id = R.drawable.o_shape), contentDescription = null )


        }
    }
}

private fun doNothing(){

}

@Preview
@Composable
fun XOCardPreview(){
    //XOCard()
}

@Preview
@Composable
fun GridRowPreview(){
  //  GridRow()
}


@Preview
@Composable
fun GridPreview(){
  //  Grid()
}