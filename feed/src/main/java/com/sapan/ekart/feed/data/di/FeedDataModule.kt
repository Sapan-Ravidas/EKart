package com.sapan.ekart.feed.data.di

import android.content.Context
import androidx.room.Room
import com.sapan.ekart.feed.BuildConfig
import com.sapan.ekart.feed.data.local.ProductDao
import com.sapan.ekart.feed.data.local.ProductDatabase
import com.sapan.ekart.feed.data.remote.ProductApiService
import com.sapan.ekart.feed.data.repository.ProductRepositoryImpl
import com.sapan.ekart.feed.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeedDataModule {

    @Provides
    @Singleton
    fun provideFeedRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApiService(retrofit: Retrofit): ProductApiService {
        return retrofit.create(ProductApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "product_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(db: ProductDatabase): ProductDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        api: ProductApiService,
        dao: ProductDao,
        db: ProductDatabase
    ): ProductRepository {
        return ProductRepositoryImpl(api, dao, db)
    }
}
