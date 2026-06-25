package com.sapan.ekart.feed.domain.usecase

import com.sapan.ekart.feed.domain.repository.ProductRepository
import com.sapan.ekart.core.network.Resource
import javax.inject.Inject

class RefreshProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Resource<Unit> {
        return repository.refreshProducts()
    }
}
