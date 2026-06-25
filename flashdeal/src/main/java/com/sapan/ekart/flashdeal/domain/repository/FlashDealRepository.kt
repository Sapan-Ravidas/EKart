package com.sapan.ekart.flashdeal.domain.repository

import com.sapan.ekart.flashdeal.domain.model.FlashDeal
import kotlinx.coroutines.flow.Flow

interface FlashDealRepository {
    fun getActiveFlashDeals(): Flow<List<FlashDeal>>
}
