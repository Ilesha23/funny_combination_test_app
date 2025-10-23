package com.ilesha.funnycombination.domain.repository

import com.ilesha.funnycombination.domain.model.GameResult
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun saveResult(result: GameResult)

    suspend fun getAllResults(): List<GameResult>

    fun getTopResult(): Flow<GameResult?>

}