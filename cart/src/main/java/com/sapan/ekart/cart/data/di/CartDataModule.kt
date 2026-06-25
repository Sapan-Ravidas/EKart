package com.sapan.ekart.cart.data.di

import android.content.Context
import androidx.room.Room
import com.sapan.ekart.cart.data.local.CartDao
import com.sapan.ekart.cart.data.local.CartDatabase
import com.sapan.ekart.cart.data.repository.CartRepositoryImpl
import com.sapan.ekart.core.cart.domain.repository.CartRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CartRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCartRepository(impl: CartRepositoryImpl): CartRepository
}

@Module
@InstallIn(SingletonComponent::class)
object CartDataModule {

    @Provides
    @Singleton
    fun provideCartDatabase(@ApplicationContext context: Context): CartDatabase {
        return Room.databaseBuilder(
            context,
            CartDatabase::class.java,
            "cart_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCartDao(db: CartDatabase): CartDao {
        return db.dao
    }
}
