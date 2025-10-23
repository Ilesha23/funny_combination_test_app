package com.ilesha.funnycombination.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilesha.funnycombination.domain.model.GameResult
import java.time.LocalDate

@Entity(
    tableName = "game_results"
)
data class GameResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val score: Int,
    val date: String
)

fun GameResultEntity.toDomain(): GameResult {
    return GameResult(
        id = this.id,
        score = this.score,
        date = LocalDate.parse(this.date)
    )
}

fun GameResult.toEntity(): GameResultEntity {
    return GameResultEntity(
        score = this.score,
        date = this.date.toString()
    )
}