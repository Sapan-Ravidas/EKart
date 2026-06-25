package com.sapan.ekart.feed.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val images: List<String>,
    val inventoryCount: Int,
    val category: Category
)

data class Category(
    val id: Int,
    val name: String,
    val image: String
)
