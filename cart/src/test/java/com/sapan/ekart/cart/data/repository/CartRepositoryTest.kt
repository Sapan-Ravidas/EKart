package com.sapan.ekart.cart.data.repository

import com.sapan.ekart.cart.data.local.CartDao
import com.sapan.ekart.cart.data.local.entity.CartItemEntity
import com.sapan.ekart.core.cart.domain.model.CartItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class CartRepositoryTest {

    private val dao: CartDao = mock()
    private lateinit var repository: CartRepositoryImpl

    @Before
    fun setup() {
        repository = CartRepositoryImpl(dao)
    }

    @Test
    fun `getCartItems maps entities to domain models`() = runTest {
        val entities = listOf(
            CartItemEntity(1, "Title", 10.0, 1, "url")
        )
        whenever(dao.getCartItems()).thenReturn(flowOf(entities))

        val result = repository.getCartItems().first()

        assertEquals(1, result.size)
        assertEquals(1, result[0].productId)
        assertEquals("Title", result[0].title)
    }

    @Test
    fun `addToCart calls dao insert`() = runTest {
        val item = CartItem(1, "Title", 10.0, 1, "url")
        repository.addToCart(item)

        verify(dao).insertCartItem(any())
    }

    @Test
    fun `removeFromCart calls dao delete`() = runTest {
        repository.removeFromCart(1)
        verify(dao).deleteCartItem(1)
    }

    @Test
    fun `updateQuantity deletes if quantity is zero`() = runTest {
        repository.updateQuantity(1, 0)
        verify(dao).deleteCartItem(1)
        verify(dao, never()).updateQuantity(any(), any())
    }

    @Test
    fun `updateQuantity updates if quantity is positive`() = runTest {
        repository.updateQuantity(1, 5)
        verify(dao).updateQuantity(1, 5)
    }
}
