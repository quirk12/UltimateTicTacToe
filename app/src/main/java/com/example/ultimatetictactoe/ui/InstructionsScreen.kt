package com.example.ultimatetictactoe.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ultimatetictactoe.R
import com.example.ultimatetictactoe.WindowSize
import com.example.ultimatetictactoe.data.Instructions
import com.example.ultimatetictactoe.data.InstructionsSource


@Composable
fun InstructionsDialog(
    onInstructionsDoneButtonClicked: () -> Unit,
    onDismissRequest: () -> Unit,
    windowSize: WindowSize,
    modifier: Modifier = Modifier
){
        Dialog(
            onDismissRequest = onDismissRequest,
            ) {
            InstructionPage(
                onInstructionsDoneButtonClicked = onInstructionsDoneButtonClicked,
                windowSize = windowSize
            )
        }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InstructionPage(
    windowSize: WindowSize,
    onInstructionsDoneButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        InstructionsSource.instructions.size
    }

    Card (
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.size(700.dp)
    ){

            Box (
                contentAlignment = Center,
                modifier = modifier
                    .fillMaxSize()
            ) {

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                ) {page ->

                    InstructionsSheet(
                        isLastPage = page == (InstructionsSource.instructions.size)-1,
                        instructions = InstructionsSource.instructions[page],
                         modifier = Modifier.align(TopCenter),
                        onInstructionsDoneButtonClicked = onInstructionsDoneButtonClicked,
                        windowSize = windowSize
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
                        .align(BottomCenter),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                        repeat(InstructionsSource.instructions.size){iteration ->
                            val color = if (pagerState.currentPage == iteration) Color.LightGray else Color.LightGray.copy(alpha = 0.5f)
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(20.dp)
                            ){

                            }
                        }

                }

            }

        }
    }


@Composable
fun InstructionsSheet(
    windowSize: WindowSize,
    isLastPage: Boolean,
    instructions: Instructions,
    modifier: Modifier = Modifier,
    onInstructionsDoneButtonClicked: () -> Unit,
    ){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        if (windowSize != WindowSize.PhoneLandScape) {
            if(!isLastPage) {

                Card(
                    elevation = CardDefaults.cardElevation(2.dp),
                    shape = MaterialTheme.shapes.large,
                    modifier = modifier
                        .size(height = 400.dp, width = 300.dp)
                    //  .padding(dimensionResource(id = R.dimen.padding_medium)),
                ) {
                    Image(
                        painter = painterResource(id = instructions.image) ,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            //   .padding(dimensionResource(id = R.dimen.padding_small))
                            .clip(MaterialTheme.shapes.large),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }



        Text(
            text = stringResource(id = instructions.text),
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
                .width(300.dp),
            textAlign = if(isLastPage) TextAlign.Center else TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge
        )

        if(isLastPage) {
            Button(
                onClick = onInstructionsDoneButtonClicked,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_large)),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondaryContainer),
                elevation = ButtonDefaults.elevatedButtonElevation(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.done),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }


    }
}
/*
@Preview
@Composable
fun InstructionsPreview(){
    InstructionsSheet(
        isLastPage = false,
        instructions = Instructions(
        text = R.string.instruction1,
        image = R.drawable.instructions1,
    ),
        onInstructionsDoneButtonClicked = {}
    )
}

@Preview
@Composable
fun InstructionsPagesPreview(){
    InstructionPage(
        onInstructionsDoneButtonClicked = {}
    )
}
@Preview
@Composable
fun InstructionsDialogPreview(){
  //  InstructionsDialog()
}
 */