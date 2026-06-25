package com.sapan.ekart.feed

import com.sapan.ekart.core.network.NetworkResponse
import com.sapan.ekart.feed.data.remote.ProductApiService
import com.sapan.ekart.feed.data.remote.dto.ProductDto
import com.sapan.ekart.feed.data.remote.dto.ProductsResponseDto

class MockProductApiService : ProductApiService {
    override suspend fun getProducts(skip: Int, limit: Int): NetworkResponse<ProductsResponseDto, Unit> {
        val products = (skip until skip + limit).map { id ->
            ProductDto(
                id = id,
                title = "Mock Product #$id",
                price = id * 10.0,
                description = "Description for product $id",
                images = listOf("https://example.com/image$id.png"),
                stock = 10,
                category = when {
                    id % 3 == 0 -> "FMCG"
                    id % 3 == 1 -> "Lifestyle"
                    else -> "Tech"
                }
            )
        }
        return NetworkResponse.Success(
            ProductsResponseDto(
                products = products,
                total = 100,
                skip = skip,
                limit = limit
            )
        )
    }

    override suspend fun getProductsByCategory(
        category: String,
        skip: Int,
        limit: Int
    ): NetworkResponse<ProductsResponseDto, Unit> {
        val products = (0 until 5).map { index ->
            val id = category.hashCode() + index
            ProductDto(
                id = id,
                title = "$category Product #$index",
                price = 15.0 + index,
                description = "Details for $category item",
                images = listOf("https://example.com/$category-$index.png"),
                stock = 5,
                category = category
            )
        }
        return NetworkResponse.Success(
            ProductsResponseDto(
                products = products,
                total = 5,
                skip = skip,
                limit = limit
            )
        )
    }

    override suspend fun getCategoryList(): NetworkResponse<List<String>, Unit> {
        return NetworkResponse.Success(listOf("FMCG", "Lifestyle", "Tech"))
    }
}
