package com.ilesha.funnycombination.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: GameResultEntity)

    @Query("SELECT * FROM game_results ORDER BY score DESC, date DESC")
    suspend fun getResults(): List<GameResultEntity>

    @Query("SELECT * FROM game_results ORDER BY score DESC LIMIT 1")
    fun getTopResult(): Flow<GameResultEntity?>

}