package com.righttofitness.ai.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessDao {
    @Query("SELECT * FROM profiles WHERE id = :id") fun observeProfile(id: String): Flow<ProfileEntity?>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun upsertProfile(profile: ProfileEntity)
    @Query("SELECT * FROM coach_insights ORDER BY createdAt DESC LIMIT 1") fun observeLatestInsight(): Flow<CoachInsightEntity?>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun saveInsight(insight: CoachInsightEntity)
    @Query("SELECT * FROM progress_logs ORDER BY createdAt DESC LIMIT 30") fun observeProgress(): Flow<List<ProgressLogEntity>>
    @Insert suspend fun addProgress(log: ProgressLogEntity)
}

@Database(entities = [ProfileEntity::class, CoachInsightEntity::class, ProgressLogEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() { abstract fun dao(): FitnessDao }
