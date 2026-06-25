package com.sapan.ekart.cart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sapan.ekart.cart.data.local.entity.CartItemEntity

@Database(entities = [CartItemEntity::class], version = 1)
abstract class CartDatabase : RoomDatabase() {
    abstract val dao: CartDao
}
