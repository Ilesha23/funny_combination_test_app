package com.ilesha.funnycombination.ui.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import com.ilesha.funnycombination.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Splash(
    onAnimationFinished: () -> Unit
) {
    val animationDelayMillis = 500L
    val animationDurationMillis = 1500
    val delayAfterAnimation = 1000L
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        delay(animationDelayMillis)
        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = animationDurationMillis,
                    easing = LinearEasing
                )
            )
        }
        launch {
            scale.animateTo(
                targetValue = 1.3f,
                animationSpec = tween(
                    durationMillis = animationDurationMillis,
                    easing = LinearEasing
                )
            )
        }
        delay(animationDurationMillis + delayAfterAnimation)
        onAnimationFinished()
    }
    SplashContent(
        logoAlpha = alpha,
        logoScale = scale
    )
}

@Composable
fun SplashContent(
    logoAlpha: Animatable<Float, AnimationVector1D>,
    logoScale: Animatable<Float, AnimationVector1D>
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.primary.copy(alpha = logoAlpha.value),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .scale(logoScale.value)
        )
    }
}