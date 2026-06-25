package com.sapan.ekart.analytics.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sapan.ekart.core.navigation.BaseRoute
import com.sapan.ekart.core.navigation.BottomNavItem
import com.sapan.ekart.core.navigation.FeatureApi
import com.sapan.ekart.core.R
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
object AnalyticsRoute : BaseRoute

class AnalyticsNavigationApi @Inject constructor() : FeatureApi {
    override val route: BaseRoute = AnalyticsRoute

    override fun getBottomNavItem(): BottomNavItem {
        return BottomNavItem(
            titleRes = R.string.nav_analytics,
            route = AnalyticsRoute,
            icon = Icons.Default.Info,
            order = 4
        )
    }

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<AnalyticsRoute> {
            val viewModel: AnalyticsViewModel = hiltViewModel()
            AnalyticsScreen(viewModel = viewModel)
        }
    }
}
