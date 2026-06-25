package com.sapan.ekart.core.cart.domain.repository

import com.sapan.ekart.core.cart.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addToCart(item: CartItem)
    suspend fun removeFromCart(productId: Int)
    suspend fun clearCart()
    suspend fun updateQuantity(productId: Int, quantity: Int)
}
