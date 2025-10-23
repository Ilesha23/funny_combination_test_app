package com.ilesha.funnycombination.data.model

data class GameState(
    val level: Int = 0,
    val sequence: List<GameEmoji> = emptyList(),
    val playerInput: List<GameEmoji> = emptyList(),
    val isDemonstrating: Boolean = false,
    val isGameOver: Boolean = false,
    val currentDemonstratingItem: GameEmoji? = null
)
