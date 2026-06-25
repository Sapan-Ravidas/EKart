package com.sapan.ekart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sapan.ekart.core.navigation.FeatureApi

@Composable
fun EKartNavGraph(
    navController: NavHostController,
    features: Set<FeatureApi>
) {
    val startFeature = features.find { it.isStartDestination } 
        ?: features.firstOrNull() 
        ?: throw IllegalStateException("No features registered in NavGraph")

    NavHost(
        navController = navController,
        startDestination = startFeature.route
    ) {
        features.forEach { feature ->
            feature.registerGraph(this, navController)
        }
    }
}
