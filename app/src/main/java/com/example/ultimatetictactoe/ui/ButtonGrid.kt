package com.example.ultimatetictactoe.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ultimatetictactoe.R

@Composable
fun ButtonsGrid(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonRow()
        ButtonRow()
        ButtonRow()
    }
}

@Composable
fun ButtonRow(
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        TicButton (
            modifier = Modifier
             //   .weight(1f)
                .padding(dimensionResource(id = R.dimen.padding_extra_small)),
            onClick = {}
        )
        TicButton (
            modifier = Modifier
             //   .weight(1f)
                .padding(dimensionResource(id = R.dimen.padding_extra_small)),
            onClick = {}
        )
        TicButton (
            modifier = Modifier
             //   .weight(1f)
                .padding(dimensionResource(id = R.dimen.padding_extra_small)),
            onClick = {}
        )
    }
}

@Composable
fun TicButton(
    modifier: Modifier =Modifier,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer),
        shape = MaterialTheme.shapes.extraSmall,
        elevation = ButtonDefaults.buttonElevation(4.dp),
        modifier = modifier
            //   .aspectRatio(1f)
            .width(dimensionResource(id = R.dimen.button_size))
            .height(dimensionResource(id = R.dimen.button_size))
            .border(
                color = Color.DarkGray,
                shape = MaterialTheme.shapes.extraSmall,
                width = 1.dp
            )
    ) {

    }
}

@Preview
@Composable
fun ButtonsGridPreview(){
    ButtonsGrid()
}
@Preview
@Composable
fun ButtonRowPreview(){
    ButtonRow()
}