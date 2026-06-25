package com.sapan.ekart.flashdeal.data.di

import com.sapan.ekart.flashdeal.data.repository.FlashDealRepositoryImpl
import com.sapan.ekart.flashdeal.domain.repository.FlashDealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FlashDealModule {

    @Binds
    @Singleton
    abstract fun bindFlashDealRepository(
        impl: FlashDealRepositoryImpl
    ): FlashDealRepository
}
