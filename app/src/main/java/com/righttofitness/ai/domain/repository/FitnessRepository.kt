package com.righttofitness.ai.domain.repository

import com.righttofitness.ai.domain.model.*
import kotlinx.coroutines.flow.Flow

interface FitnessRepository {
    fun observeProfile(): Flow<UserProfile?>
    fun observeLatestInsight(): Flow<CoachInsight?>
    suspend fun saveProfile(profile: UserProfile)
    suspend fun generateAdaptivePlan(profile: UserProfile, fatigue: Int = 3, skippedWorkout: Boolean = false): CoachInsight
    suspend fun askCoach(question: String, profile: UserProfile?): String
}
