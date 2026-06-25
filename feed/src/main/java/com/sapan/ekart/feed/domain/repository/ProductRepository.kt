package com.sapan.ekart.feed.domain.repository

import com.sapan.ekart.feed.domain.model.Product
import com.sapan.ekart.core.network.Resource
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    fun getPagedProducts(categoryName: String? = null): Flow<PagingData<Product>>
    suspend fun refreshProducts(): Resource<Unit>
    suspend fun getCategories(): Resource<List<String>>
}
