package com.example.ultimatetictactoe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ultimatetictactoe.R

@Composable
fun EnterPlayerNamesDialog(
    ticUIState: GameUIState,
    onPlayer1NameChanged: ( name: String) -> Unit,
    onPlayer2NameChanged: ( name: String) -> Unit,
    onNextButtonClicked: () -> Unit,
    onExitButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
            Dialog(onDismissRequest = onExitButtonClicked) {
                EnterPlayerNames(
                    ticUIState = ticUIState,
                    onPlayer1NameChanged = onPlayer1NameChanged ,
                    onPlayer2NameChanged = onPlayer2NameChanged ,
                    onNextButtonClicked = onNextButtonClicked,
                    onExitButtonClicked = onExitButtonClicked)
            }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterPlayerNames(
    ticUIState: GameUIState,
    onPlayer1NameChanged: ( name: String) -> Unit,
    onPlayer2NameChanged: ( name: String) -> Unit,
    onNextButtonClicked: () -> Unit,
    onExitButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){

    Card (
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .wrapContentSize()
            .padding(dimensionResource(id = R.dimen.padding_small))
    ){
        Column(
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(id = R.string.enter_player1_name),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small)),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.labelSmall

            )
            OutlinedTextField(
                value = ticUIState.player1Name ,
                onValueChange = onPlayer1NameChanged,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_small)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    MaterialTheme.colorScheme.onPrimaryContainer
                ),
                keyboardOptions =  KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                label = { Text(
                    text = stringResource(id = R.string.enter_player_one_name),
                    style = MaterialTheme.typography.labelSmall
                )}

            )
            Text(
                text = stringResource(id = R.string.enter_player2_name),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small)),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.labelSmall
            )
            OutlinedTextField(
                value = ticUIState.player2Name ,
                onValueChange = onPlayer2NameChanged,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_small)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    MaterialTheme.colorScheme.onPrimaryContainer
                ),
                keyboardOptions =  KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                label = { Text(
                    text = stringResource(id = R.string.enter_player_two_name),
                    style = MaterialTheme.typography.labelSmall
                )},
                keyboardActions = KeyboardActions(
                    onDone = {onNextButtonClicked()}
                )

            )

            Row(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(
                    onClick =  onExitButtonClicked,
                ) {
                    Text(
                        text = stringResource(id = R.string.name_screen_exit_button),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                TextButton(onClick =  onNextButtonClicked ) {
                    Text(
                        text = stringResource(id = R.string.name_screen_next_button),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.labelSmall

                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun PlayerNameDialogPreview(){
    EnterPlayerNames(
        ticUIState = GameUIState(),
        onPlayer1NameChanged = {} ,
        onPlayer2NameChanged = {},
        onNextButtonClicked = { /*TODO*/ },
        onExitButtonClicked = {}
        )
}
@Preview
@Composable
fun PlayerNameDialog2Preview(){
    EnterPlayerNamesDialog(
        ticUIState = GameUIState(),
        onPlayer1NameChanged = {} ,
        onPlayer2NameChanged = {},
        onNextButtonClicked = { /*TODO*/ },
        onExitButtonClicked = {}
    )
}