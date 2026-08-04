package com.righttofitness.ai.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.righttofitness.ai.domain.model.*
import com.righttofitness.ai.domain.repository.FitnessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainUiState(val profile: UserProfile? = null, val insight: CoachInsight? = null, val coachReply: String = "", val loading: Boolean = false)

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FitnessRepository) : ViewModel() {
    val state: StateFlow<MainUiState> = combine(repository.observeProfile(), repository.observeLatestInsight()) { p, i -> MainUiState(p, i) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), MainUiState())
    fun completeOnboarding(profile: UserProfile) = viewModelScope.launch { repository.saveProfile(profile); repository.generateAdaptivePlan(profile) }
    fun ask(question: String) = viewModelScope.launch { state.value.profile.let { profile -> _reply.value = repository.askCoach(question, profile) } }
    private val _reply = MutableStateFlow("")
    val coachReply: StateFlow<String> = _reply.asStateFlow()
}
