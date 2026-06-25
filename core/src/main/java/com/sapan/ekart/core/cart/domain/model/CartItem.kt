package com.sapan.ekart.core.cart.domain.model

data class CartItem(
    val productId: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String
)
