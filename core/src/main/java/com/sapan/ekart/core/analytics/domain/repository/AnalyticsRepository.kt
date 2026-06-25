package com.sapan.ekart.core.analytics.domain.repository

import com.sapan.ekart.core.analytics.domain.model.Interaction

interface AnalyticsRepository {
    suspend fun logInteraction(interaction: Interaction)
    suspend fun getLoggedInteractions(): List<Interaction>
    suspend fun drainInteractions(): List<Interaction>
    suspend fun clearInteractions()
}
