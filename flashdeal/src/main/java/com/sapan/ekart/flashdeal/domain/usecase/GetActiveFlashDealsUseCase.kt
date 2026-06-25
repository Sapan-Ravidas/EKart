package com.sapan.ekart.flashdeal.domain.usecase

import com.sapan.ekart.flashdeal.domain.model.FlashDeal
import com.sapan.ekart.flashdeal.domain.repository.FlashDealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActiveFlashDealsUseCase @Inject constructor(
    private val repository: FlashDealRepository
) {
    operator fun invoke(): Flow<List<FlashDeal>> {
        return repository.getActiveFlashDeals()
    }
}
