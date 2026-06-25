package com.sapan.ekart.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sapan.ekart.navigation.EKartNavGraph
import com.sapan.ekart.navigation.provider.NavigationProvider
import com.sapan.ekart.core.navigation.FeatureApi

@Composable
fun MainScreen(
    navigationProvider: NavigationProvider,
    features: Set<FeatureApi>
) {
    val navController = rememberNavController()
    val navItems = navigationProvider.getBottomNavItems()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navItems.forEach { item ->
                    val title = stringResource(item.titleRes)
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = title) },
                        label = { Text(title) },
                        selected = currentDestination?.hierarchy?.any { 
                            it.hasRoute(item.route::class)
                        } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            EKartNavGraph(
                navController = navController,
                features = features
            )
        }
    }
}
