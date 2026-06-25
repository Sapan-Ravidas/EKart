package com.sapan.ekart.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

import kotlinx.serialization.Serializable

/**
 * Base marker for all navigation routes in the app.
 * Used with Kotlin Serialization for type-safe navigation.
 */
interface BaseRoute

@Serializable
object AnalyticsRoute : BaseRoute

data class BottomNavItem(
    val titleRes: Int,
    val route: BaseRoute,
    val icon: ImageVector,
    val order: Int = 0
)

interface FeatureApi {
    val route: BaseRoute
    val isStartDestination: Boolean get() = false
    
    fun getBottomNavItem(): BottomNavItem? = null

    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    )
}
