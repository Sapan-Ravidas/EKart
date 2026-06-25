package com.sapan.ekart.feed.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sapan.ekart.core.network.NetworkResponse
import com.sapan.ekart.feed.data.local.ProductDatabase
import com.sapan.ekart.feed.data.local.entity.ProductEntity
import com.sapan.ekart.feed.data.local.entity.RemoteKeys
import com.sapan.ekart.feed.data.mapper.toEntity

@OptIn(ExperimentalPagingApi::class)
class ProductRemoteMediator(
    private val database: ProductDatabase,
    private val apiService: ProductApiService,
    private val categoryName: String? = null
) : RemoteMediator<Int, ProductEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {
        val skip = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        val networkResponse = if (categoryName != null) {
            apiService.getProductsByCategory(
                category = categoryName,
                skip = skip,
                limit = state.config.pageSize
            )
        } else {
            apiService.getProducts(
                skip = skip,
                limit = state.config.pageSize
            )
        }

        return when (networkResponse) {
            is NetworkResponse.Success -> {
                val response = networkResponse.body
                val products = response.products
                val endOfPaginationReached = products.isEmpty() || (response.skip + products.size >= response.total)

                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        // When filtering by category, we only want to clear items for that specific query
                        // but since our Room database shared between All and Categories, 
                        // and Paging 3's RemoteMediator expects a clean slate for REFRESH,
                        // we clear both tables.
                        database.remoteKeysDao.clearRemoteKeys()
                        database.dao.clearProducts()
                    }
                    val prevKey = if (skip == 0) null else skip - state.config.pageSize
                    val nextKey = if (endOfPaginationReached) null else skip + products.size
                    val keys = products.map {
                        RemoteKeys(productId = it.id, prevKey = prevKey, nextKey = nextKey)
                    }
                    database.remoteKeysDao.insertAll(keys)
                    database.dao.insertProducts(products.map { it.toEntity() })
                }
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }
            is NetworkResponse.ApiError -> MediatorResult.Error(Exception("API Error: ${networkResponse.code}"))
            is NetworkResponse.NetworkError -> MediatorResult.Error(networkResponse.error)
            is NetworkResponse.UnknownError -> MediatorResult.Error(networkResponse.error ?: Exception("Unknown Error"))
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ProductEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { product ->
                database.remoteKeysDao.getRemoteKeysForProduct(product.id)
            }
    }
}
