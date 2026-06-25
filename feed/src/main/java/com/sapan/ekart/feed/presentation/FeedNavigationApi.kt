package com.sapan.ekart.feed.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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
object FeedRoute : BaseRoute

class FeedNavigationApi @Inject constructor() : FeatureApi {
    override val route: BaseRoute = FeedRoute
    override val isStartDestination: Boolean = true

    override fun getBottomNavItem(): BottomNavItem {
        return BottomNavItem(
            titleRes = R.string.nav_feed,
            route = FeedRoute,
            icon = Icons.Default.Home,
            order = 1
        )
    }

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<FeedRoute> {
            val viewModel: FeedViewModel = hiltViewModel()
            FeedScreen(viewModel = viewModel)
        }
    }
}
