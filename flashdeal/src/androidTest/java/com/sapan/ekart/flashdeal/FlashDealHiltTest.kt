package com.sapan.ekart.flashdeal

import com.sapan.ekart.flashdeal.domain.repository.FlashDealRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class FlashDealHiltTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: FlashDealRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testRepositoryInjection() {
        assertNotNull(repository)
    }
}
