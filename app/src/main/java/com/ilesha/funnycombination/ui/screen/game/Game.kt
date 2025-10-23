package com.ilesha.funnycombination.ui.screen.game

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ilesha.funnycombination.R
import com.ilesha.funnycombination.data.model.GameEmoji
import com.ilesha.funnycombination.data.model.GameState

@Composable
fun Game(
    viewModel: GameViewModel = hiltViewModel(),
    onResult: (Int, Boolean) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val navigateToResults by viewModel.navigateToResults.collectAsStateWithLifecycle()
    val isNewRecord by viewModel.isNewRecord.collectAsStateWithLifecycle()

    LaunchedEffect(navigateToResults) {
        if (navigateToResults) onResult(state.level - 1, isNewRecord)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (!state.isGameOver) {
            GameField(
                state = state,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        } else {
            GameOverPanel(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
        ButtonsPanel(
            clickedEmoji = {
                viewModel.handleInput(it)
            }
        )
    }


}

@Composable
fun GameField(
    state: GameState,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        state.currentDemonstratingItem?.let {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(it.drawableResId),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )
                if (state.playerInput.isNotEmpty() and (state.playerInput.lastIndex <= state.sequence.lastIndex)) {
                    if (state.sequence[state.playerInput.lastIndex] == state.playerInput.last()) {
                        Image(
                            painter = painterResource(R.drawable.heavy_check_mark),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Green),
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.x),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Red),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonsPanel(
    clickedEmoji: (GameEmoji) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
            )
            .padding(vertical = 20.dp)
    ) {
        for (emoji in GameEmoji.entries) {
            Image(
                painter = painterResource(emoji.drawableResId),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        onClick = {
                            clickedEmoji(emoji)
                        }
                    )
            )
        }
    }
}

@Composable
fun GameOverPanel(
    modifier: Modifier = Modifier
) {
    val alpha = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        repeat(3) {
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 300
                )
            )
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }
        alpha.snapTo(1f)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.main_game_over),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary.copy(alpha = alpha.value)
        )
    }
}