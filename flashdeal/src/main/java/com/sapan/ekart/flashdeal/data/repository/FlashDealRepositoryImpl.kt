package com.sapan.ekart.flashdeal.data.repository

import com.sapan.ekart.flashdeal.domain.model.FlashDeal
import com.sapan.ekart.flashdeal.domain.repository.FlashDealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours

class FlashDealRepositoryImpl @Inject constructor() : FlashDealRepository {
    
    // Mocking active deals with unique IDs to prevent collision with Feed products.
    // DummyJSON IDs typically range from 1-200, so we use 1000+ for flash deals.
    override fun getActiveFlashDeals(): Flow<List<FlashDeal>> = flow {
        val deals = listOf(
            FlashDeal(
                id = "deal_1",
                productId = 1001,
                title = "Wireless Headphones Pro",
                imageUrl = "https://cdn.dummyjson.com/products/images/mobile-accessories/Apple%20AirPods%20Max/thumbnail.jpg",
                originalPrice = 549.0,
                dealPrice = 299.0,
                endTimeMillis = System.currentTimeMillis() + 2.hours.inWholeMilliseconds
            ),
            FlashDeal(
                id = "deal_2",
                productId = 1002,
                title = "Mechanical Gaming Keyboard",
                imageUrl = "https://cdn.dummyjson.com/products/images/mobile-accessories/Apple%20Magic%20Keyboard/thumbnail.jpg",
                originalPrice = 159.0,
                dealPrice = 89.0,
                endTimeMillis = System.currentTimeMillis() + 1.hours.inWholeMilliseconds
            )
        )
        emit(deals)
    }
}
