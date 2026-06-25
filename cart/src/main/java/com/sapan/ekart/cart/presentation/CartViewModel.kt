package com.sapan.ekart.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapan.ekart.core.analytics.domain.usecase.LogInteractionUseCase
import com.sapan.ekart.core.cart.domain.model.CartItem
import com.sapan.ekart.core.cart.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CartUiState(
    val cartItems: List<CartItem> = emptyList(),
    val checkoutSuccess: Boolean = false
)

sealed class CartIntent {
    data class UpdateQuantity(val productId: Int, val quantity: Int) : CartIntent()
    data class RemoveFromCart(val productId: Int) : CartIntent()
    object Checkout : CartIntent()
    object DismissSuccessDialog : CartIntent()
}

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
    private val logInteractionUseCase: LogInteractionUseCase
) : ViewModel() {

    private val _checkoutSuccess = MutableStateFlow(false)

    val uiState: StateFlow<CartUiState> = combine(
        repository.getCartItems(),
        _checkoutSuccess
    ) { cartItems, checkoutSuccess ->
        CartUiState(cartItems = cartItems, checkoutSuccess = checkoutSuccess)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CartUiState())

    fun handleIntent(intent: CartIntent) {
        when (intent) {
            is CartIntent.UpdateQuantity -> updateQuantity(intent.productId, intent.quantity)
            is CartIntent.RemoveFromCart -> removeFromCart(intent.productId)
            CartIntent.Checkout -> checkout()
            CartIntent.DismissSuccessDialog -> _checkoutSuccess.value = false
        }
    }

    private fun updateQuantity(productId: Int, quantity: Int) {
        viewModelScope.launch {
            repository.updateQuantity(productId, quantity)
        }
    }

    private fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            repository.removeFromCart(productId)
        }
    }

    private fun checkout() {
        viewModelScope.launch {
            val items = uiState.value.cartItems
            if (items.isNotEmpty()) {
                val details = items.joinToString { "${it.title} (x${it.quantity})" }
                logInteractionUseCase("Completed Checkout", "Checked out: $details")
                repository.clearCart()
                _checkoutSuccess.value = true
            }
        }
    }
}
