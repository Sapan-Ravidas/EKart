package com.sapan.ekart.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapan.ekart.feed.domain.model.Product
import com.sapan.ekart.feed.domain.repository.ProductRepository
import com.sapan.ekart.feed.domain.usecase.GetPagedProductsUseCase
import com.sapan.ekart.feed.domain.usecase.RefreshProductsUseCase
import com.sapan.ekart.core.analytics.domain.usecase.LogInteractionUseCase
import com.sapan.ekart.core.cart.domain.model.CartItem
import com.sapan.ekart.core.cart.domain.repository.CartRepository
import com.sapan.ekart.core.network.Resource
import com.sapan.ekart.core.util.AnalyticsConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

data class FeedUiState(
    val selectedCategory: String? = null,
    val categories: List<String> = emptyList(),
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class FeedIntent {
    data class SelectCategory(val category: String?) : FeedIntent()
    data class AddToCart(val product: Product) : FeedIntent()
    data class RemoveFromCart(val productId: Int) : FeedIntent()
    data class LogInteraction(val type: String, val details: String) : FeedIntent()
    object Refresh : FeedIntent()
}

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val getPagedProductsUseCase: GetPagedProductsUseCase,
    private val refreshProductsUseCase: RefreshProductsUseCase,
    private val logInteractionUseCase: LogInteractionUseCase,
    private val cartRepository: CartRepository,
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow<String?>(null)
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagedProducts: Flow<PagingData<Product>> = _selectedCategory
        .flatMapLatest { getPagedProductsUseCase(it) }
        .cachedIn(viewModelScope)

    val uiState: StateFlow<FeedUiState> = combine(
        _selectedCategory,
        _categories,
        cartRepository.getCartItems(),
        _isLoading,
        _error
    ) { selectedCategory, categories, cartItems, isLoading, error ->
        FeedUiState(
            selectedCategory = selectedCategory,
            categories = categories,
            cartItems = cartItems,
            isLoading = isLoading,
            error = error
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FeedUiState())

    init {
        handleIntent(FeedIntent.Refresh)
        fetchCategories()
    }

    fun handleIntent(intent: FeedIntent) {
        when (intent) {
            is FeedIntent.SelectCategory -> selectCategory(intent.category)
            is FeedIntent.AddToCart -> addToCart(intent.product)
            is FeedIntent.RemoveFromCart -> removeFromCart(intent.productId)
            is FeedIntent.LogInteraction -> logInteraction(intent.type, intent.details)
            FeedIntent.Refresh -> refresh()
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            when (val result = repository.getCategories()) {
                is Resource.Success -> _categories.value = result.data
                is Resource.Error -> _error.value = result.message
                Resource.Loading -> {}
            }
        }
    }

    private fun selectCategory(category: String?) {
        _selectedCategory.value = category
    }

    private fun refresh() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            when (val result = refreshProductsUseCase()) {
                is Resource.Success -> _isLoading.value = false
                is Resource.Error -> {
                    _error.value = result.message
                    _isLoading.value = false
                }
                Resource.Loading -> _isLoading.value = true
            }
        }
    }

    private fun addToCart(product: Product) {
        viewModelScope.launch {
            val existingItem = uiState.value.cartItems.find { it.productId == product.id }
            if (existingItem != null) {
                cartRepository.updateQuantity(product.id, existingItem.quantity + 1)
            } else {
                cartRepository.addToCart(
                    CartItem(
                        productId = product.id,
                        title = product.title,
                        price = product.price,
                        quantity = 1,
                        imageUrl = product.images.firstOrNull() ?: ""
                    )
                )
            }
            logInteraction(AnalyticsConstants.TYPE_ADD_TO_CART, "${AnalyticsConstants.DETAILS_ADDED_PREFIX}${product.title}${AnalyticsConstants.DETAILS_ADDED_SUFFIX}")
        }
    }

    private fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            val existingItem = uiState.value.cartItems.find { it.productId == productId }
            if (existingItem != null) {
                if (existingItem.quantity > 1) {
                    cartRepository.updateQuantity(productId, existingItem.quantity - 1)
                } else {
                    cartRepository.removeFromCart(productId)
                }
            }
        }
    }

    private fun logInteraction(type: String, details: String) {
        viewModelScope.launch {
            logInteractionUseCase(type, details)
        }
    }
}
