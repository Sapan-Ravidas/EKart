package com.sapan.ekart.analytics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapan.ekart.core.analytics.domain.model.Interaction
import com.sapan.ekart.core.analytics.domain.repository.AnalyticsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val repository: AnalyticsRepository
) : ViewModel() {

    private val _interactions = MutableStateFlow<List<Interaction>>(emptyList())
    val interactions: StateFlow<List<Interaction>> = _interactions

    init {
        loadInteractions()
    }

    fun loadInteractions() {
        viewModelScope.launch {
            _interactions.value = repository.getLoggedInteractions()
        }
    }

    fun clearInteractions() {
        viewModelScope.launch {
            repository.clearInteractions()
            _interactions.value = emptyList()
        }
    }
}
