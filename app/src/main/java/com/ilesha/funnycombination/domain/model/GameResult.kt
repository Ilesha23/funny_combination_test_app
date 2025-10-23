package com.ilesha.funnycombination.domain.model

import java.time.LocalDate

data class GameResult(
    val id: Int = 0,
    val score: Int,
    val date: LocalDate
)
