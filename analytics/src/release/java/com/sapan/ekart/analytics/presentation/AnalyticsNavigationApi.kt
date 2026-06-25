package com.sapan.ekart.analytics.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.sapan.ekart.core.navigation.AnalyticsRoute
import com.sapan.ekart.core.navigation.BaseRoute
import com.sapan.ekart.core.navigation.FeatureApi
import javax.inject.Inject

class AnalyticsNavigationApi @Inject constructor() : FeatureApi {
    override val route: BaseRoute = AnalyticsRoute

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        // No-op in release
    }
}
