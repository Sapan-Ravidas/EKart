package com.sapan.ekart.feed.data.repository

import com.sapan.ekart.feed.data.local.ProductDao
import com.sapan.ekart.feed.data.local.ProductDatabase
import com.sapan.ekart.feed.data.mapper.toDomain
import com.sapan.ekart.feed.data.mapper.toEntity
import com.sapan.ekart.feed.data.remote.ProductApiService
import com.sapan.ekart.feed.data.remote.ProductRemoteMediator
import com.sapan.ekart.feed.domain.model.Product
import com.sapan.ekart.feed.domain.repository.ProductRepository
import com.sapan.ekart.core.network.Resource
import com.sapan.ekart.core.network.NetworkResponse
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApiService,
    private val dao: ProductDao,
    private val database: ProductDatabase
) : ProductRepository {

    override fun getProducts(): Flow<List<Product>> {
        return dao.getAllProducts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedProducts(categoryName: String?): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20, enablePlaceholders = false),
            remoteMediator = ProductRemoteMediator(database, api, categoryName),
            pagingSourceFactory = {
                if (categoryName != null) {
                    dao.getProductsByCategoryPagingSource(categoryName)
                } else {
                    dao.getProductsPagingSource()
                }
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun refreshProducts(): Resource<Unit> = withContext(Dispatchers.IO) {
        when (val response = api.getProducts()) {
            is NetworkResponse.Success -> {
                dao.clearProducts()
                dao.insertProducts(response.body.products.map { it.toEntity() })
                Resource.Success(Unit)
            }
            is NetworkResponse.ApiError -> Resource.Error("API Error: ${response.code}")
            is NetworkResponse.NetworkError -> Resource.Error("Network Error", response.error)
            is NetworkResponse.UnknownError -> Resource.Error("Unknown Error", response.error)
        }
    }

    override suspend fun getCategories(): Resource<List<String>> = withContext(Dispatchers.IO) {
        when (val response = api.getCategoryList()) {
            is NetworkResponse.Success -> Resource.Success(response.body)
            is NetworkResponse.ApiError -> Resource.Error("API Error: ${response.code}")
            is NetworkResponse.NetworkError -> Resource.Error("Network Error", response.error)
            is NetworkResponse.UnknownError -> Resource.Error("Unknown Error", response.error)
        }
    }
}
