package com.righttofitness.ai.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import com.righttofitness.ai.ui.dashboard.DashboardScreen
import com.righttofitness.ai.ui.onboarding.OnboardingScreen
import com.righttofitness.ai.ui.theme.RtfTheme

@Composable fun RightToFitnessApp(viewModel: MainViewModel = hiltViewModel()) = RtfTheme {
    val state by viewModel.state.collectAsState()
    val nav = rememberNavController()
    Surface(Modifier.fillMaxSize()) {
        NavHost(nav, startDestination = if (state.profile == null) "onboarding" else "dashboard") {
            composable("onboarding") { OnboardingScreen { viewModel.completeOnboarding(it); nav.navigate("dashboard") { popUpTo("onboarding") { inclusive = true } } } }
            composable("dashboard") { DashboardScreen(state, viewModel::ask, viewModel.coachReply.collectAsState().value) }
        }
    }
}
