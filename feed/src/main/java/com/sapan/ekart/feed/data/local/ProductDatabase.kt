package com.sapan.ekart.feed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sapan.ekart.feed.data.local.entity.ProductEntity
import com.sapan.ekart.feed.data.local.entity.RemoteKeys

@Database(entities = [ProductEntity::class, RemoteKeys::class], version = 2)
abstract class ProductDatabase : RoomDatabase() {
    abstract val dao: ProductDao
    abstract val remoteKeysDao: RemoteKeysDao
}
