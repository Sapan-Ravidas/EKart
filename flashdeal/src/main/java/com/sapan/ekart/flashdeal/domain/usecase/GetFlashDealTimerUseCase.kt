package com.sapan.ekart.flashdeal.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFlashDealTimerUseCase @Inject constructor() {

    /**
     * Emits the remaining time in milliseconds every second.
     * Calculated globally here to maintain precision.
     */
    operator fun invoke(endTimeMillis: Long): Flow<Long> = flow {
        while (true) {
            val remaining = endTimeMillis - System.currentTimeMillis()
            if (remaining <= 0) {
                emit(0L)
                break
            }
            emit(remaining)
            delay(1000L)
        }
    }
}
