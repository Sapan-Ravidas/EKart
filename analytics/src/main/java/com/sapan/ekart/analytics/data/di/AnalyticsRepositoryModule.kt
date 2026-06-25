package com.sapan.ekart.analytics.data.di

import com.sapan.ekart.analytics.data.repository.AnalyticsRepositoryImpl
import com.sapan.ekart.core.analytics.domain.repository.AnalyticsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsRepositoryModule {

    @Binds
    abstract fun bindAnalyticsRepository(
        impl: AnalyticsRepositoryImpl
    ): AnalyticsRepository
}
