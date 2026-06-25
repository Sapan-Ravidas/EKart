package com.sapan.ekart.analytics.data.repository

import android.util.Log
import com.sapan.ekart.analytics.BuildConfig
import com.sapan.ekart.analytics.data.local.InteractionDao
import com.sapan.ekart.analytics.data.local.entity.InteractionEntity
import com.sapan.ekart.core.analytics.domain.model.Interaction
import com.sapan.ekart.core.analytics.domain.repository.AnalyticsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnalyticsRepositoryImpl @Inject constructor(
    private val dao: InteractionDao
) : AnalyticsRepository {

    override suspend fun logInteraction(interaction: Interaction) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Logging interaction: ${interaction.type}")
        }
        withContext(Dispatchers.IO) {
            dao.insertInteraction(
                InteractionEntity(
                    type = interaction.type,
                    timestamp = interaction.timestamp,
                    details = interaction.details
                )
            )
        }
    }

    override suspend fun getLoggedInteractions(): List<Interaction> {
        return withContext(Dispatchers.IO) {
            dao.getAllInteractions().map {
                Interaction(
                    type = it.type,
                    timestamp = it.timestamp,
                    details = it.details
                )
            }
        }
    }

    override suspend fun drainInteractions(): List<Interaction> {
        return getLoggedInteractions()
    }

    override suspend fun clearInteractions() {
        withContext(Dispatchers.IO) {
            dao.clearInteractions()
        }
    }

    companion object {
        private const val TAG = "ANALYTICS"
    }
}
