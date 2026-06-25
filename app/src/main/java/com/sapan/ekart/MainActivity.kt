package com.sapan.ekart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sapan.ekart.core.designsystem.theme.EKartTheme
import com.sapan.ekart.core.navigation.FeatureApi
import com.sapan.ekart.navigation.provider.NavigationProvider
import com.sapan.ekart.presentation.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationProvider: NavigationProvider

    @Inject
    lateinit var features: Set<@JvmSuppressWildcards FeatureApi>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EKartTheme {
                MainScreen(
                    navigationProvider = navigationProvider,
                    features = features
                )
            }
        }
    }
}
