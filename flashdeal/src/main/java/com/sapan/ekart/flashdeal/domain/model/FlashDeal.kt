package com.sapan.ekart.flashdeal.domain.model

data class FlashDeal(
    val id: String,
    val productId: Int,
    val title: String,
    val imageUrl: String,
    val originalPrice: Double,
    val dealPrice: Double,
    val endTimeMillis: Long
)
