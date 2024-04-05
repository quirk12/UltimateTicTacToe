package com.example.ultimatetictactoe

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ultimatetictactoe.ui.GameViewModel
import com.example.ultimatetictactoe.ui.EnterPlayerNamesDialog
import com.example.ultimatetictactoe.ui.InstructionsDialog
import com.example.ultimatetictactoe.ui.MultiplayerScreen

enum class ScreensEnum {
    MainScreen,
    MultiplayerScreen,
}

enum class WindowSize {
   PhoneLandScape,
    PhonePortrait,
    Phone,
    Fold,
    Tablet,
    TabletPortrait,
    TabletLandscape
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicMainScreen(
    windowWidth: WindowWidthSizeClass,
    windowHeight: WindowHeightSizeClass,
    modifier: Modifier = Modifier
) {
    val gameViewModel: GameViewModel = viewModel()
    val ticUIState = gameViewModel.uiState.collectAsState().value
    val navController: NavHostController = rememberNavController()

    val windowSize: WindowSize = when (windowWidth) {
        WindowWidthSizeClass.Compact -> WindowSize.PhonePortrait
        WindowWidthSizeClass.Medium -> {
            if(windowHeight == WindowHeightSizeClass.Medium) WindowSize.Fold
            else WindowSize.TabletPortrait
        }
        WindowWidthSizeClass.Expanded -> {
            if(windowHeight == WindowHeightSizeClass.Compact) WindowSize.PhoneLandScape
            else WindowSize.TabletLandscape
        }
        else -> WindowSize.Phone
    }
    Scaffold(
        topBar = { TicTopAppBar(
            onClick = {gameViewModel.showInstructionsScreen(true)},
            canNavigateBack = 
                navController.previousBackStackEntry != null
             ,
            onArrowBackClicked = {
                gameViewModel.updatePlayersNames(1, "")
                gameViewModel.updatePlayersNames(2, "")
                gameViewModel.resetGame()
                navController.navigateUp()
            }
        ) }
    ) { it ->
                NavHost(
                    navController = navController,
                    startDestination = ScreensEnum.MainScreen.name,
                    modifier = Modifier.padding(it)
                ) {
                    composable(ScreensEnum.MainScreen.name) {
                        TicHomeScreen(
                            onMultiplayerButtonClicked = {gameViewModel.showPlayerNamesScreen(true)},
                            windowSize = windowSize
                        )
                    }
                    composable(ScreensEnum.MultiplayerScreen.name) {
                        MultiplayerScreen(
                            lockUnlockBoards = {gameViewModel.lockUnlockAndSetActiveBoards(it)},
                            playGame = {gameViewModel.playGame(it)},
                            resetGame = {gameViewModel.resetGame()},
                            ticUIState = ticUIState,
                            windowSize = windowSize
                        )
                    }
                }



        
        if(ticUIState.isInstructionsDialogShow) {
            InstructionsDialog(
                modifier = Modifier.padding(it),
                onDismissRequest = {gameViewModel.showInstructionsScreen(false)},
                onInstructionsDoneButtonClicked = {gameViewModel.showInstructionsScreen(false)},
                windowSize = windowSize
                )
        }
        if(ticUIState.isPlayerNamesScreenShown) {
           EnterPlayerNamesDialog(
               ticUIState = ticUIState,
               onPlayer1NameChanged = {gameViewModel.updatePlayersNames(playerName = it, player = 1)} ,
               onPlayer2NameChanged = {gameViewModel.updatePlayersNames(player = 2, playerName = it)},
               onNextButtonClicked = {
                   gameViewModel.showPlayerNamesScreen(false)
                   navController.navigate(ScreensEnum.MultiplayerScreen.name)
                                     },
               onExitButtonClicked = {
                   gameViewModel.showPlayerNamesScreen(false)
                   gameViewModel.updatePlayersNames(1, "")
                   gameViewModel.updatePlayersNames(2, "")
               })
        }


    }
}


@Composable
fun TicHomeScreen(
    modifier: Modifier = Modifier,
    onMultiplayerButtonClicked: () -> Unit,
    windowSize: WindowSize
) {

    if (windowSize == WindowSize.TabletLandscape || windowSize == WindowSize.PhoneLandScape ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxSize()
        ) {
            TicTacToeAppLogo()
            TicHomeScreenButtons (
                onMultiplayerButtonClicked = onMultiplayerButtonClicked
            )
        }
    }
    else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = modifier.fillMaxSize()

        ) {
            Spacer(modifier = Modifier.height(80.dp))
            TicTacToeAppLogo()
            Spacer(modifier = Modifier.height(200.dp))
            TicHomeScreenButtons (
                onMultiplayerButtonClicked = onMultiplayerButtonClicked
            )

        }
    }


}

@Composable
fun TicHomeScreenButtons(
    onMultiplayerButtonClicked: () -> Unit,
    ){
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier.width(dimensionResource(id = R.dimen.button_width)),
            onClick = onMultiplayerButtonClicked
        ) {
            Text(
                text = stringResource(id = R.string.multi_player_button),
                style = MaterialTheme.typography.labelSmall

            )
        }
        OutlinedButton(
            modifier = Modifier.width(dimensionResource(id = R.dimen.button_width)),
            onClick = {
                Toast.makeText(
                    context,
                    context.getString(R.string.feature_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Text(
                text = stringResource(id = R.string.single_player_button),
                style = MaterialTheme.typography.labelSmall
            )
        }
        OutlinedButton(
            modifier = Modifier.width(dimensionResource(id = R.dimen.button_width)),
            onClick = {
                Toast.makeText(
                    context,
                    context.getString(R.string.feature_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Text(
                text = stringResource(id = R.string.online_play_button),
                style = MaterialTheme.typography.labelSmall

            )
        }
    }

}


@Composable
fun TicTacToeAppLogo(modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .scale(1.8f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = null,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTopAppBar(
    onClick: () -> Unit,
    canNavigateBack: Boolean,
    onArrowBackClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
            text = stringResource(id = R.string.app_name_spaced),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
        ) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            IconButton(onClick = onClick) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "info", tint = MaterialTheme.colorScheme.onPrimary )
            }
        },
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = onArrowBackClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    )
}


@Preview
@Composable
fun TicHomeScreenPreview() {
  //  TicHomeScreen()
}

@Preview
@Composable
fun AppLogoPreview() {
    TicTacToeAppLogo()
}