package com.righttofitness.ai.di

import android.content.Context
import androidx.room.Room
import com.righttofitness.ai.data.local.AppDatabase
import com.righttofitness.ai.data.local.FitnessDao
import com.righttofitness.ai.data.repository.FitnessRepositoryImpl
import com.righttofitness.ai.domain.repository.FitnessRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
abstract class RepositoryModule { @Binds @Singleton abstract fun bindFitnessRepository(impl: FitnessRepositoryImpl): FitnessRepository }

@Module @InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides @Singleton fun database(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "right_to_fitness_ai.db").fallbackToDestructiveMigration(false).build()
    @Provides fun dao(db: AppDatabase): FitnessDao = db.dao()
}
