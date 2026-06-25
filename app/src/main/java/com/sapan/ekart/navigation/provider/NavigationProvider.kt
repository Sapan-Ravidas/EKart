package com.sapan.ekart.navigation.provider

import com.sapan.ekart.core.navigation.BottomNavItem
import com.sapan.ekart.core.navigation.FeatureApi
import javax.inject.Inject

interface NavigationProvider {
    fun getBottomNavItems(): List<BottomNavItem>
}

class DefaultNavigationProvider @Inject constructor(
    private val features: Set<@JvmSuppressWildcards FeatureApi>
) : NavigationProvider {
    override fun getBottomNavItems(): List<BottomNavItem> {
        return features
            .mapNotNull { it.getBottomNavItem() }
            .sortedBy { it.order }
    }
}
