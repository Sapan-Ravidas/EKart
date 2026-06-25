package com.sapan.ekart.cart.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sapan.ekart.cart.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CartDaoTest {

    private lateinit var database: CartDatabase
    private lateinit var dao: CartDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CartDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.dao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetCartItems() = runBlocking {
        val item = CartItemEntity(1, "Product 1", 10.0, 1, "url1")
        dao.insertCartItem(item)

        val items = dao.getCartItems().first()
        assertEquals(1, items.size)
        assertEquals(item, items[0])
    }

    @Test
    fun deleteCartItem() = runBlocking {
        val item = CartItemEntity(1, "Product 1", 10.0, 1, "url1")
        dao.insertCartItem(item)
        dao.deleteCartItem(1)

        val items = dao.getCartItems().first()
        assertTrue(items.isEmpty())
    }

    @Test
    fun updateQuantity() = runBlocking {
        val item = CartItemEntity(1, "Product 1", 10.0, 1, "url1")
        dao.insertCartItem(item)
        dao.updateQuantity(1, 5)

        val items = dao.getCartItems().first()
        assertEquals(5, items[0].quantity)
    }

    @Test
    fun clearCart() = runBlocking {
        dao.insertCartItem(CartItemEntity(1, "Product 1", 10.0, 1, "url1"))
        dao.insertCartItem(CartItemEntity(2, "Product 2", 20.0, 2, "url2"))
        
        dao.clearCart()

        val items = dao.getCartItems().first()
        assertTrue(items.isEmpty())
    }
}
