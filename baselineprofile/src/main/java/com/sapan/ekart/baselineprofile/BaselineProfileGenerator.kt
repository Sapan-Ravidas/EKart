package com.sapan.ekart.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {
    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun generate() = baselineProfileRule.collect(
        packageName = "com.sapan.ekart"
    ) {
        pressHome()
        startActivityAndWait()

        // Interaction for Feed
        device.waitForIdle()
        
        // Scroll the product list
        device.executeShellCommand("input swipe 500 1500 500 500")
        device.waitForIdle()

        // Navigate to Flash Deals
        val flashDealsTab = device.findObject(androidx.test.uiautomator.By.text("Deals"))
        flashDealsTab?.click()
        device.waitForIdle()

        // Navigate to Cart
        val cartTab = device.findObject(androidx.test.uiautomator.By.text("Cart"))
        cartTab?.click()
        device.waitForIdle()
    }
}
