package com.ilesha.funnycombination.data.di

import android.content.Context
import androidx.room.Room
import com.ilesha.funnycombination.data.db.GameResultDao
import com.ilesha.funnycombination.data.db.ResultDataBase
import com.ilesha.funnycombination.data.repository.RepositoryImpl
import com.ilesha.funnycombination.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideResultDataBase(@ApplicationContext context: Context): ResultDataBase {
        return Room.databaseBuilder(
            context,
            ResultDataBase::class.java,
            "result_db"
        ).build()
    }

    @Provides
    fun provideGameResultDao(db: ResultDataBase): GameResultDao {
        return db.gameResultDao()
    }

    @Provides
    @Singleton
    fun provideRepository(impl: RepositoryImpl): Repository = impl

}