package com.sapan.ekart.di

import com.sapan.ekart.navigation.provider.DefaultNavigationProvider
import com.sapan.ekart.navigation.provider.NavigationProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindNavigationProvider(
        defaultNavigationProvider: DefaultNavigationProvider
    ): NavigationProvider
}
