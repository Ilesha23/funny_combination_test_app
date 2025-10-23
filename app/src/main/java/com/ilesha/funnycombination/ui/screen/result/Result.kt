package com.ilesha.funnycombination.ui.screen.result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ilesha.funnycombination.R
import com.ilesha.funnycombination.ui.components.MenuButton

@Composable
fun Result(
    viewModel: ResultViewModel = hiltViewModel(),
    onMenu: () -> Unit,
    onGame: () -> Unit
) {

    BackHandler {
        onMenu()
    }

    val score = viewModel.score
    val isNewHighScore = viewModel.isNewRecord

    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = stringResource(R.string.main_game_over),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.result_result, score),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(
                    if (isNewHighScore)
                        R.string.result_new_score
                    else
                        R.string.result_not_new_score
                ),
                style = MaterialTheme.typography.bodyLarge
            )
            MenuButton(
                action = onMenu,
                text = stringResource(R.string.result_button_menu)
            )
            MenuButton(
                action = onGame,
                text = stringResource(R.string.result_button_game)
            )
        }
    }
}