package com.sapan.ekart.cart

import com.sapan.ekart.core.cart.domain.repository.CartRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CartHiltTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: CartRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testRepositoryInjection() {
        assertNotNull(repository)
    }
}
