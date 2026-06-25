package com.sapan.ekart.analytics.data.di

import android.content.Context
import androidx.room.Room
import com.sapan.ekart.analytics.data.local.AnalyticsDatabase
import com.sapan.ekart.analytics.data.local.InteractionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsDataModule {

    @Provides
    @Singleton
    fun provideAnalyticsDatabase(@ApplicationContext context: Context): AnalyticsDatabase {
        return Room.databaseBuilder(
            context,
            AnalyticsDatabase::class.java,
            "analytics_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideInteractionDao(db: AnalyticsDatabase): InteractionDao {
        return db.dao
    }
}
