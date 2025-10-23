package com.ilesha.funnycombination.data.repository

import com.ilesha.funnycombination.data.db.GameResultDao
import com.ilesha.funnycombination.data.db.toDomain
import com.ilesha.funnycombination.data.db.toEntity
import com.ilesha.funnycombination.domain.model.GameResult
import com.ilesha.funnycombination.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: GameResultDao
) : Repository {

    override suspend fun saveResult(result: GameResult) {
        dao.insertResult(result.toEntity())
    }

    override suspend fun getAllResults(): List<GameResult> {
        return dao.getResults().map { it.toDomain() }
    }

    override fun getTopResult(): Flow<GameResult?> {
        return dao.getTopResult().map { it?.toDomain() }
    }

}