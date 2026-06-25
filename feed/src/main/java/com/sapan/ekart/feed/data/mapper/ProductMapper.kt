package com.sapan.ekart.feed.data.mapper

import com.sapan.ekart.feed.data.local.entity.ProductEntity
import com.sapan.ekart.feed.data.remote.dto.ProductDto
import com.sapan.ekart.feed.domain.model.Category
import com.sapan.ekart.feed.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        images = images,
        inventoryCount = stock,
        category = Category(
            id = category.hashCode(),
            name = category,
            image = ""
        )
    )
}

fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        images = images.joinToString(","),
        inventoryCount = stock,
        categoryName = category
    )
}

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        images = images.split(",").filter { it.isNotBlank() },
        inventoryCount = inventoryCount,
        category = Category(
            id = categoryName.hashCode(),
            name = categoryName,
            image = ""
        )
    )
}
