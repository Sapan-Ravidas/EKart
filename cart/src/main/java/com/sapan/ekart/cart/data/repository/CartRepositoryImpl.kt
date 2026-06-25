package com.sapan.ekart.cart.data.repository

import com.sapan.ekart.cart.data.local.CartDao
import com.sapan.ekart.cart.data.local.entity.CartItemEntity
import com.sapan.ekart.core.cart.domain.model.CartItem
import com.sapan.ekart.core.cart.domain.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val dao: CartDao
) : CartRepository {

    override fun getCartItems(): Flow<List<CartItem>> {
        return dao.getCartItems().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToCart(item: CartItem) = withContext(Dispatchers.IO) {
        dao.insertCartItem(item.toEntity())
    }

    override suspend fun removeFromCart(productId: Int) = withContext(Dispatchers.IO) {
        dao.deleteCartItem(productId)
    }

    override suspend fun clearCart() = withContext(Dispatchers.IO) {
        dao.clearCart()
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int) = withContext(Dispatchers.IO) {
        if (quantity <= 0) {
            dao.deleteCartItem(productId)
        } else {
            dao.updateQuantity(productId, quantity)
        }
    }

    private fun CartItemEntity.toDomain() = CartItem(
        productId = productId,
        title = title,
        price = price,
        quantity = quantity,
        imageUrl = imageUrl
    )

    private fun CartItem.toEntity() = CartItemEntity(
        productId = productId,
        title = title,
        price = price,
        quantity = quantity,
        imageUrl = imageUrl
    )
}
