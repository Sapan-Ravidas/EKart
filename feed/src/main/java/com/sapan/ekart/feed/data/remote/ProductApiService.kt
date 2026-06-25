package com.sapan.ekart.feed.data.remote

import com.sapan.ekart.core.network.NetworkResponse
import com.sapan.ekart.feed.data.remote.dto.ProductsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20
    ): NetworkResponse<ProductsResponseDto, Unit>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20
    ): NetworkResponse<ProductsResponseDto, Unit>

    @GET("products/category-list")
    suspend fun getCategoryList(): NetworkResponse<List<String>, Unit>
}
