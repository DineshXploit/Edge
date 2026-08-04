package com.righttofitness.ai.domain.usecase

import com.righttofitness.ai.domain.model.*
import javax.inject.Inject
import kotlin.math.roundToInt

class AiFitnessEngine @Inject constructor() {
    private val library = listOf(
        Exercise("Barbell Back Squat",3,listOf("Quadriceps","Glutes"),listOf("Hamstrings","Core"),listOf("Barbell","Rack"),listOf("Brace hard","Descend under control","Drive through mid-foot"),"squat_3d",listOf("Knees collapsing","Losing brace"),"Inhale down, exhale through sticking point","3-1-1",listOf("Goblet Squat","Leg Press")),
        Exercise("Pull Up",3,listOf("Lats"),listOf("Biceps","Rear Delts"),listOf("Pull-up Bar"),listOf("Start from dead hang","Pull chest toward bar","Lower with control"),"pullup_3d",listOf("Half reps","Shrugging"),"Exhale up, inhale down","2-1-2",listOf("Band Pull Up","Lat Pulldown")),
        Exercise("Planche Lean",4,listOf("Shoulders","Core"),listOf("Chest","Wrists"),listOf("Floor"),listOf("Protract scapula","Lean forward safely","Hold hollow body"),"planche_3d",listOf("Bent arms","Sagging hips"),"Slow nasal breathing","Isometric",listOf("Pseudo Planche Push Up","Plank")),
        Exercise("Dumbbell Press",2,listOf("Chest"),listOf("Triceps","Shoulders"),listOf("Dumbbells","Bench"),listOf("Pack shoulders","Press smoothly","Control the bottom"),"press_3d",listOf("Flaring elbows","Bouncing"),"Exhale press, inhale lower","2-0-2",listOf("Push Up","Machine Press")),
        Exercise("Kettlebell Swing",2,listOf("Glutes","Hamstrings"),listOf("Core","Back"),listOf("Kettlebell"),listOf("Hinge","Snap hips","Float bell"),"swing_3d",listOf("Squatting","Overusing arms"),"Sharp exhale at lockout","1-0-1",listOf("Hip Thrust","RDL"))
    )

    fun generate(profile: UserProfile, fatigue: Int, skippedWorkout: Boolean): CoachInsight {
        val bmr = 10 * profile.weightKg + 6.25 * profile.heightCm - 5 * profile.age + if (profile.gender.equals("male", true)) 5 else -161
        val activity = 1.2 + (profile.dailySteps.coerceIn(0, 14000) / 14000.0) * .5
        val goalDelta = if (profile.goal == Goal.FAT_LOSS) -450 else if (profile.goal == Goal.MUSCLE_GAIN) 250 else 0
        val calories = (bmr * activity + goalDelta).roundToInt().coerceAtLeast(1400)
        val protein = (profile.weightKg * if (profile.goal == Goal.FAT_LOSS) 2.2 else 1.8).roundToInt()
        val fat = (calories * .25 / 9).roundToInt(); val carbs = ((calories - protein * 4 - fat * 9) / 4).coerceAtLeast(80)
        val intensity = when { profile.sleepHours < 6 || fatigue > 7 -> "deload technique focus"; skippedWorkout -> "compressed full body"; else -> "progressive overload" }
        val chosen = library.filter { ex -> profile.location == TrainingLocation.GYM || ex.equipment.all { it == "Floor" || profile.equipment.contains(it) } }.ifEmpty { library.filter { it.equipment.contains("Floor") } }
        val sets = chosen.take(profile.availableDays.coerceIn(3, 6)).mapIndexed { i, ex -> WorkoutSet(ex, if (intensity.startsWith("deload")) 2 else 3 + i % 2, if (profile.goal == Goal.STRENGTH) "3-6" else "8-15", if (profile.goal == Goal.FAT_LOSS) 45 else 90, intensity) }
        val meals = buildMeals(profile.dietStyle, calories, protein, carbs, fat)
        val warnings = (profile.medicalConditions + profile.injuries).map { "Respect limitation: $it. Use pain-free range and consult a clinician when symptoms change." }
        return CoachInsight(WorkoutPlan("${profile.goal.name.lowercase().replace('_',' ')} adaptive ${profile.availableDays}-day plan", profile.workoutMinutes, sets, "Target ${profile.sleepHours.coerceAtLeast(7.0)}h sleep, mobility after sessions, hydration ${profile.waterLiters.coerceAtLeast(2.5)}L.", "Add 1 rep or 2.5% load after two clean sessions; deload automatically on poor sleep/fatigue."), DietPlan(calories, protein, carbs, fat, meals, meals.flatMap { it.ingredients }.distinct(), profile.waterLiters.coerceAtLeast(profile.weightKg * .035)), warnings, 120)
    }

    private fun buildMeals(style: DietStyle, c: Int, p: Int, carbs: Int, f: Int): List<Meal> {
        val veg = style in listOf(DietStyle.VEGETARIAN, DietStyle.VEGAN, DietStyle.JAIN)
        val protein = if (veg) "tofu paneer lentils" else "eggs chicken fish greek yogurt"
        return listOf(
            Meal("AI power breakfast bowl", (c*.28).roundToInt(), (p*.28).roundToInt(), (carbs*.32).roundToInt(), (f*.22).roundToInt(), listOf("oats", protein, "berries", "chia")),
            Meal("Budget performance lunch", (c*.38).roundToInt(), (p*.38).roundToInt(), (carbs*.38).roundToInt(), (f*.38).roundToInt(), listOf("rice or roti", protein, "seasonal vegetables", "curd")),
            Meal("Recovery dinner", (c*.34).roundToInt(), (p*.34).roundToInt(), (carbs*.30).roundToInt(), (f*.40).roundToInt(), listOf("sweet potato", protein, "salad", "olive oil"))
        )
    }
}
