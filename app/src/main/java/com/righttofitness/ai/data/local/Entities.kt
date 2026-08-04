package com.righttofitness.ai.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(@PrimaryKey val id: String, val payload: String, val updatedAt: Long)

@Entity(tableName = "coach_insights")
data class CoachInsightEntity(@PrimaryKey val id: String, val payload: String, val createdAt: Long)

@Entity(tableName = "progress_logs")
data class ProgressLogEntity(@PrimaryKey(autoGenerate = true) val id: Long = 0, val weightKg: Double, val sleepHours: Double, val fatigue: Int, val skippedWorkout: Boolean, val createdAt: Long)
