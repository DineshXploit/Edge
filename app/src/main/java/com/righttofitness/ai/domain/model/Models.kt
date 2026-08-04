package com.righttofitness.ai.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class Goal { FAT_LOSS, MUSCLE_GAIN, STRENGTH, CALISTHENICS, MOBILITY, RECOMPOSITION }
@Serializable
enum class TrainingLocation { GYM, HOME, OUTDOOR, HYBRID }
@Serializable
enum class DietStyle { VEGETARIAN, NON_VEGETARIAN, VEGAN, JAIN, KETO, HIGH_PROTEIN, LOW_CARB }

@Serializable
data class UserProfile(
    val id: String = "guest", val age: Int, val gender: String, val heightCm: Int, val weightKg: Double,
    val bodyFatPercent: Double?, val experience: String, val goal: Goal, val location: TrainingLocation,
    val equipment: List<String>, val availableDays: Int, val workoutMinutes: Int, val dietStyle: DietStyle,
    val foodPreference: String, val budgetPerWeek: Int, val medicalConditions: List<String>, val injuries: List<String>,
    val sleepHours: Double, val waterLiters: Double, val lifestyle: String, val occupation: String, val dailySteps: Int,
    val transformationGoal: String
)

@Serializable
data class Exercise(val name: String, val difficulty: Int, val primaryMuscles: List<String>, val secondaryMuscles: List<String>, val equipment: List<String>, val instructions: List<String>, val animationKey: String, val mistakes: List<String>, val breathing: String, val tempo: String, val alternatives: List<String>)
@Serializable
data class WorkoutSet(val exercise: Exercise, val sets: Int, val reps: String, val restSeconds: Int, val intensity: String)
@Serializable
data class WorkoutPlan(val title: String, val durationMinutes: Int, val blocks: List<WorkoutSet>, val recoveryAdvice: String, val progressiveOverload: String)
@Serializable
data class Meal(val name: String, val calories: Int, val protein: Int, val carbs: Int, val fat: Int, val ingredients: List<String>)
@Serializable
data class DietPlan(val calories: Int, val protein: Int, val carbs: Int, val fat: Int, val meals: List<Meal>, val groceryList: List<String>, val hydrationLiters: Double)
@Serializable
data class CoachInsight(val workout: WorkoutPlan, val diet: DietPlan, val warnings: List<String>, val xpReward: Int)
