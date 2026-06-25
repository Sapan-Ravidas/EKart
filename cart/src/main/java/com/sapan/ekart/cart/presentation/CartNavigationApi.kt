package com.sapan.ekart.cart.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
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
object CartRoute : BaseRoute

class CartNavigationApi @Inject constructor() : FeatureApi {
    override val route: BaseRoute = CartRoute

    override fun getBottomNavItem(): BottomNavItem {
        return BottomNavItem(
            titleRes = R.string.nav_cart,
            route = CartRoute,
            icon = Icons.Default.ShoppingCart,
            order = 3
        )
    }

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<CartRoute> {
            val viewModel: CartViewModel = hiltViewModel()
            CartScreen(viewModel = viewModel)
        }
    }
}
