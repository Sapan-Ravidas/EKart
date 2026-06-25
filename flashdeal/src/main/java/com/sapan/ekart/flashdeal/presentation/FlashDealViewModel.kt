package com.sapan.ekart.flashdeal.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapan.ekart.flashdeal.domain.model.FlashDeal
import com.sapan.ekart.flashdeal.domain.usecase.GetActiveFlashDealsUseCase
import com.sapan.ekart.flashdeal.domain.usecase.GetFlashDealTimerUseCase
import com.sapan.ekart.core.analytics.domain.usecase.LogInteractionUseCase
import com.sapan.ekart.core.cart.domain.model.CartItem
import com.sapan.ekart.core.cart.domain.repository.CartRepository
import com.sapan.ekart.core.util.AnalyticsConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FlashDealUiState(
    val deals: List<FlashDeal> = emptyList(),
    val cartItems: List<CartItem> = emptyList()
)

sealed class FlashDealIntent {
    data class JoinPool(val deal: FlashDeal) : FlashDealIntent()
    data class LeavePool(val productId: Int) : FlashDealIntent()
    data class LogInteraction(val type: String, val details: String) : FlashDealIntent()
}

@HiltViewModel
class FlashDealViewModel @Inject constructor(
    getActiveFlashDealsUseCase: GetActiveFlashDealsUseCase,
    private val timerUseCase: GetFlashDealTimerUseCase,
    private val logInteractionUseCase: LogInteractionUseCase,
    private val cartRepository: CartRepository,
) : ViewModel() {

    val uiState: StateFlow<FlashDealUiState> = combine(
        getActiveFlashDealsUseCase(),
        cartRepository.getCartItems()
    ) { deals, cartItems ->
        // Prune inactive timer flows to prevent memory accumulation
        val activeIds = deals.map { it.id }.toSet()
        synchronized(timerFlows) {
            timerFlows.keys.retainAll { it in activeIds }
        }
        
        FlashDealUiState(deals = deals, cartItems = cartItems)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FlashDealUiState())

    fun handleIntent(intent: FlashDealIntent) {
        when (intent) {
            is FlashDealIntent.JoinPool -> joinPool(intent.deal)
            is FlashDealIntent.LeavePool -> leavePool(intent.productId)
            is FlashDealIntent.LogInteraction -> logInteraction(intent.type, intent.details)
        }
    }

    private val timerFlows = mutableMapOf<String, StateFlow<Long>>()

    fun countdownFor(deal: FlashDeal): StateFlow<Long> {
        return synchronized(timerFlows) {
            timerFlows.getOrPut(deal.id) {
                timerUseCase(deal.endTimeMillis)
                    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0L)
            }
        }
    }

    private fun joinPool(deal: FlashDeal) {
        viewModelScope.launch {
            val existingItem = uiState.value.cartItems.find { it.productId == deal.productId }
            if (existingItem == null) {
                cartRepository.addToCart(
                    CartItem(
                        productId = deal.productId,
                        title = deal.title,
                        price = deal.dealPrice,
                        quantity = 1,
                        imageUrl = deal.imageUrl
                    )
                )
                logInteraction(AnalyticsConstants.TYPE_JOIN_POOL, "${AnalyticsConstants.DETAILS_JOINED_PREFIX}${deal.title}")
            }
        }
    }

    private fun leavePool(productId: Int) {
        viewModelScope.launch {
            cartRepository.removeFromCart(productId)
        }
    }

    private fun logInteraction(type: String, details: String) {
        viewModelScope.launch {
            logInteractionUseCase(type, details)
        }
    }
}
