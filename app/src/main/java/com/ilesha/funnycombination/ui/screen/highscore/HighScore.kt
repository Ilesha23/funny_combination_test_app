package com.ilesha.funnycombination.ui.screen.highscore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ilesha.funnycombination.R
import com.ilesha.funnycombination.domain.model.GameResult
import com.ilesha.funnycombination.ui.components.MenuButton

@Composable
fun HighScore(
    viewModel: HighScoreViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val scores by viewModel.scores.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 15.dp, horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.high_score_header),
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            item {
                HeaderRow()
            }
            items(scores) {
                Item(it)
            }
        }
        MenuButton(
            action = {
                onBack()
            },
            text = stringResource(R.string.high_score_button_main_menu)
        )
    }
}

@Composable
fun Item(
    item: GameResult
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = item.score.toString()
        )
        Text(
            text = item.date.toString()
        )
    }
}

@Composable
fun HeaderRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.high_score_score)
        )
        Text(
            text = stringResource(R.string.high_score_date)
        )
    }
}