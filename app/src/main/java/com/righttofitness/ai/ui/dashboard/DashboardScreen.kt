package com.righttofitness.ai.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.righttofitness.ai.ui.MainUiState
import com.righttofitness.ai.ui.components.*

@Composable fun DashboardScreen(state: MainUiState, askCoach: (String) -> Unit, coachReply: String) {
    val insight = state.insight
    var question by remember { mutableStateOf("How should I train if I slept badly?") }
    Box { HeroBackdrop(); LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { Text("Premium AI Command Center", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary) }
        item { Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) { ProgressRing("Calories", .72f, "${insight?.diet?.calories ?: 0}"); ProgressRing("Protein", .80f, "${insight?.diet?.protein ?: 0}g"); ProgressRing("XP", .55f, "+${insight?.xpReward ?: 0}") } }
        item { GlassCard(Modifier.fillMaxWidth()) { Text(insight?.workout?.title ?: "Generating adaptive plan…", style = MaterialTheme.typography.titleLarge); Text(insight?.workout?.progressiveOverload.orEmpty()); Text(insight?.workout?.recoveryAdvice.orEmpty()) } }
        items(insight?.workout?.blocks.orEmpty()) { set -> GlassCard(Modifier.fillMaxWidth()) { Text(set.exercise.name, style = MaterialTheme.typography.titleMedium); Text("${set.sets} sets • ${set.reps} reps • ${set.restSeconds}s rest • ${set.intensity}"); Text("Muscles: ${set.exercise.primaryMuscles.joinToString()} | Tempo ${set.exercise.tempo}"); Text("Mistakes: ${set.exercise.mistakes.joinToString()}") } }
        item { GlassCard(Modifier.fillMaxWidth()) { Text("AI Diet System", style = MaterialTheme.typography.titleLarge); Text("Macros: ${insight?.diet?.protein}P / ${insight?.diet?.carbs}C / ${insight?.diet?.fat}F"); Text("Groceries: ${insight?.diet?.groceryList?.joinToString().orEmpty()}") } }
        items(insight?.diet?.meals.orEmpty()) { meal -> GlassCard(Modifier.fillMaxWidth()) { Text(meal.name, style = MaterialTheme.typography.titleMedium); Text("${meal.calories} kcal • ${meal.protein}g protein • Ingredients: ${meal.ingredients.joinToString()}") } }
        item { GlassCard(Modifier.fillMaxWidth()) { Text("24x7 AI Coach"); OutlinedTextField(question, { question = it }, modifier = Modifier.fillMaxWidth()); Button(onClick = { askCoach(question) }) { Text("Ask Coach") }; if (coachReply.isNotBlank()) Text(coachReply) } }
        item { GlassCard(Modifier.fillMaxWidth()) { Text("Future-ready camera intelligence"); Text("Body scan, posture detection, form correction, food recognition, and progress comparison are represented in the architecture for model-backed rollout.") } }
    } }
}
