package com.sapan.ekart.analytics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sapan.ekart.analytics.data.local.entity.InteractionEntity

@Dao
interface InteractionDao {
    @Insert
    suspend fun insertInteraction(interaction: InteractionEntity)

    @Query("SELECT * FROM interactions")
    suspend fun getAllInteractions(): List<InteractionEntity>

    @Query("DELETE FROM interactions")
    suspend fun clearInteractions()
}
