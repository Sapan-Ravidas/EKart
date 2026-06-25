package com.sapan.ekart.core.analytics.domain.usecase

import com.sapan.ekart.core.analytics.domain.model.Interaction
import com.sapan.ekart.core.analytics.domain.repository.AnalyticsRepository
import javax.inject.Inject

class LogInteractionUseCase @Inject constructor(
    private val repository: AnalyticsRepository
) {
    suspend operator fun invoke(type: String, details: String) {
        repository.logInteraction(
            Interaction(
                type = type,
                timestamp = System.currentTimeMillis(),
                details = details
            )
        )
    }
}
