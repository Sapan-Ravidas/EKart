package com.sapan.ekart.flashdeal.presentation

import com.sapan.ekart.core.navigation.FeatureApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FlashDealNavigationModule {
    @Binds
    @Singleton
    @IntoSet
    abstract fun bindFlashDealNavigation(flashDealNavigationApi: FlashDealNavigationApi): FeatureApi
}
