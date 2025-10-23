package com.ilesha.funnycombination.ui.screen.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilesha.funnycombination.R
import com.ilesha.funnycombination.ui.components.MenuButton

@Composable
fun Main(
    onPlay: () -> Unit,
    onHighScore: () -> Unit,
    onPrivacy: () -> Unit,
    onExit: () -> Unit
) {

    BackHandler { onExit() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Menu(
            onPlay = onPlay,
            onHighScore = onHighScore,
            onPrivacy = onPrivacy,
            onExit = onExit
        )
    }
}

@Composable
fun Menu(
    onPlay: () -> Unit,
    onHighScore: () -> Unit,
    onPrivacy: () -> Unit,
    onExit: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(2f)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .weight(3f)
        ) {
            MenuButton(
                action = onPlay,
                text = stringResource(R.string.main_play)
            )
            MenuButton(
                action = onHighScore,
                text = stringResource(R.string.main_score)
            )
            MenuButton(
                action = onPrivacy,
                text = stringResource(R.string.main_privacy)
            )
            MenuButton(
                action = onExit,
                text = stringResource(R.string.main_exit)
            )
        }
    }
}