package com.sapan.ekart.analytics.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sapan.ekart.analytics.data.local.entity.InteractionEntity

@Database(entities = [InteractionEntity::class], version = 1)
abstract class AnalyticsDatabase : RoomDatabase() {
    abstract val dao: InteractionDao
}
