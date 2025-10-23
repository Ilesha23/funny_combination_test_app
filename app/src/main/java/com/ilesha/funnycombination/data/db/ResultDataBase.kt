package com.ilesha.funnycombination.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GameResultEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ResultDataBase: RoomDatabase() {
    abstract fun gameResultDao(): GameResultDao
}