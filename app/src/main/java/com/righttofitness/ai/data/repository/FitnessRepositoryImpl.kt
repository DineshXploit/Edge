package com.righttofitness.ai.data.repository

import com.righttofitness.ai.data.local.*
import com.righttofitness.ai.domain.model.*
import com.righttofitness.ai.domain.repository.FitnessRepository
import com.righttofitness.ai.domain.usecase.AiFitnessEngine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FitnessRepositoryImpl @Inject constructor(private val dao: FitnessDao, private val engine: AiFitnessEngine) : FitnessRepository {
    private val json = Json { ignoreUnknownKeys = true }
    override fun observeProfile(): Flow<UserProfile?> = dao.observeProfile("primary").map { it?.payload?.let { payload -> json.decodeFromString<UserProfile>(payload) } }
    override fun observeLatestInsight(): Flow<CoachInsight?> = dao.observeLatestInsight().map { it?.payload?.let { payload -> json.decodeFromString<CoachInsight>(payload) } }
    override suspend fun saveProfile(profile: UserProfile) = dao.upsertProfile(ProfileEntity("primary", json.encodeToString(profile), System.currentTimeMillis()))
    override suspend fun generateAdaptivePlan(profile: UserProfile, fatigue: Int, skippedWorkout: Boolean): CoachInsight = engine.generate(profile, fatigue, skippedWorkout).also { dao.saveInsight(CoachInsightEntity("latest", json.encodeToString(it), System.currentTimeMillis())) }
    override suspend fun askCoach(question: String, profile: UserProfile?): String = "Based on your ${profile?.goal ?: "fitness"} goal: ${question.trim()} should be handled with progressive overload, adequate protein, sleep, and pain-free form. I will adapt today's prescription using your recovery signals."
}
