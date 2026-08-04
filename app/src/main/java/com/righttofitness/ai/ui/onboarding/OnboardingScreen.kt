package com.righttofitness.ai.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.righttofitness.ai.domain.model.*
import com.righttofitness.ai.ui.components.GlassCard
import com.righttofitness.ai.ui.components.HeroBackdrop

@Composable fun OnboardingScreen(onDone: (UserProfile) -> Unit) {
    var nameGoal by remember { mutableStateOf("Lose 12 kg while building strength") }
    Box { HeroBackdrop(); Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("RIGHT TO FITNESS AI", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
        Text("Your adaptive coach builds training, diet, recovery, and transformation plans from your biology, schedule, food preferences, and performance signals.")
        GlassCard { Text("Transformation Goal"); OutlinedTextField(nameGoal, { nameGoal = it }, modifier = Modifier.fillMaxWidth()); Spacer(Modifier.height(12.dp)); Button(onClick = { onDone(sampleProfile(nameGoal)) }, modifier = Modifier.fillMaxWidth()) { Text("Generate My AI Plan") } }
        Text("Collects age, gender, height, weight, body fat, experience, gym/home, equipment, days, duration, diet style, budget, medical conditions, injuries, sleep, water, lifestyle, occupation, steps and goals.")
    } }
}

private fun sampleProfile(goalText: String) = UserProfile(age=29, gender="male", heightCm=176, weightKg=84.0, bodyFatPercent=24.0, experience="intermediate", goal=Goal.FAT_LOSS, location=TrainingLocation.GYM, equipment=listOf("Barbell","Rack","Dumbbells","Bench","Pull-up Bar","Kettlebell"), availableDays=5, workoutMinutes=55, dietStyle=DietStyle.HIGH_PROTEIN, foodPreference="Indian and international", budgetPerWeek=70, medicalConditions=emptyList(), injuries=listOf("occasional lower back tightness"), sleepHours=6.8, waterLiters=2.7, lifestyle="moderately active", occupation="desk professional", dailySteps=7200, transformationGoal=goalText)
