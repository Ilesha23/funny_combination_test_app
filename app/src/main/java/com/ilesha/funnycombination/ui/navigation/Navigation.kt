package com.ilesha.funnycombination.ui.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilesha.funnycombination.ui.screen.game.Game
import com.ilesha.funnycombination.ui.screen.highscore.HighScore
import com.ilesha.funnycombination.ui.screen.main.Main
import com.ilesha.funnycombination.ui.screen.privacy.Privacy
import com.ilesha.funnycombination.ui.screen.result.Result
import com.ilesha.funnycombination.ui.screen.splash.Splash
import kotlinx.serialization.Serializable

@Serializable
data object Splash

@Serializable
data object Main

@Serializable
data object Game

@Serializable
data class Result(val result: Int, val isNewRecord: Boolean)

@Serializable
data object HighScore

@Serializable
data object Privacy

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val activity = LocalActivity.current
    NavHost(navController, Splash) {
        composable<Splash> {
            Splash(
                onAnimationFinished = {
                    navController.navigate(Main)
                }
            )
        }
        composable<Main> {
            Main(
                onPlay = dropUnlessResumed {
                    navController.navigate(Game)
                },
                onHighScore = dropUnlessResumed {
                    navController.navigate(HighScore)
                },
                onPrivacy = dropUnlessResumed {
                    navController.navigate(Privacy)
                },
                onExit = dropUnlessResumed {
                    activity?.finish()
                }
            )
        }
        composable<Game> {
            Game(
                onResult = { result, isNewRecord ->
                    navController.navigate(Result(result = result, isNewRecord = isNewRecord))
                }
            )
        }
        composable<Result> {
            Result(
                onMenu = dropUnlessResumed {
                    navController.popBackStack(Main, inclusive = false)
                },
                onGame = dropUnlessResumed {
                    navController.popBackStack(Main, inclusive = false)
                    navController.navigate(Game)
                }
            )
        }
        composable<HighScore> {
            HighScore(
                onBack = dropUnlessResumed {
                    navController.navigateUp()
                }
            )
        }
        composable<Privacy> {
            Privacy(
                onBack = dropUnlessResumed {
                    navController.navigateUp()
                }
            )
        }
    }
}