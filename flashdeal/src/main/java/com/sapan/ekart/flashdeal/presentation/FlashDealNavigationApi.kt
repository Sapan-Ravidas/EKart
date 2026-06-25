package com.sapan.ekart.flashdeal.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
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
object FlashDealsRoute : BaseRoute

class FlashDealNavigationApi @Inject constructor() : FeatureApi {
    override val route: BaseRoute = FlashDealsRoute

    override fun getBottomNavItem(): BottomNavItem {
        return BottomNavItem(
            titleRes = R.string.nav_deals,
            route = FlashDealsRoute,
            icon = Icons.Default.Notifications,
            order = 2
        )
    }

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<FlashDealsRoute> {
            val viewModel: FlashDealViewModel = hiltViewModel()
            FlashDealScreen(viewModel = viewModel)
        }
    }
}
