package com.sapan.ekart.analytics.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sapan.ekart.core.analytics.domain.repository.AnalyticsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@HiltWorker
class AnalyticsUploadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val analyticsRepository: AnalyticsRepository
) : CoroutineWorker(context, params) {

    companion object {
        private const val MAX_RETRIES = 3
    }

    override suspend fun doWork(): Result {
        if (runAttemptCount >= MAX_RETRIES) {
            // when max retry reached for the period
            // Return success to stop retrying now, but we HAVEN'T called clearInteractions()
            // so the data remains in the database for the next periodic cycle.
            return Result.success()
        }

        return try {
            val payload = analyticsRepository.drainInteractions()
            if (payload.isEmpty()) {
                return Result.success()
            }

            simulateUpload(payload)
            analyticsRepository.clearInteractions()
            Result.success()
        } catch (exception: IOException) {
            Result.retry()
        } catch (exception: HttpException) {
            Result.retry()
        } catch (exception: Exception) {
            // Unexpected error - don't clear data, let it try again in next period
            Result.failure()
        }
    }

    private suspend fun simulateUpload(payload: List<com.sapan.ekart.core.analytics.domain.model.Interaction>) {
        withContext(kotlinx.coroutines.Dispatchers.IO) {
            // In a real app, this would be a network call
            delay(500)
        }
    }
}
