package com.sapan.ekart.feed.domain.usecase

import androidx.paging.PagingData
import com.sapan.ekart.feed.domain.model.Product
import com.sapan.ekart.feed.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(categoryName: String?): Flow<PagingData<Product>> {
        return repository.getPagedProducts(categoryName)
    }
}
