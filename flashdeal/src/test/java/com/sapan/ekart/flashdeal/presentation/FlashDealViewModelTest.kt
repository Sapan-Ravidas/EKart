package com.sapan.ekart.flashdeal.presentation

import app.cash.turbine.test
import com.sapan.ekart.core.analytics.domain.usecase.LogInteractionUseCase
import com.sapan.ekart.core.cart.domain.model.CartItem
import com.sapan.ekart.core.cart.domain.repository.CartRepository
import com.sapan.ekart.flashdeal.domain.model.FlashDeal
import com.sapan.ekart.flashdeal.domain.usecase.GetActiveFlashDealsUseCase
import com.sapan.ekart.flashdeal.domain.usecase.GetFlashDealTimerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class FlashDealViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    
    private val getActiveFlashDealsUseCase: GetActiveFlashDealsUseCase = mock()
    private val timerUseCase: GetFlashDealTimerUseCase = mock()
    private val logInteractionUseCase: LogInteractionUseCase = mock()
    private val cartRepository: CartRepository = mock()

    private lateinit var viewModel: FlashDealViewModel

    private val sampleDeals = listOf(
        FlashDeal(
            id = "1",
            productId = 101,
            title = "Deal 1",
            imageUrl = "",
            originalPrice = 100.0,
            dealPrice = 50.0,
            endTimeMillis = 1000L
        )
    )

    private val cartItemsFlow = MutableStateFlow<List<CartItem>>(emptyList())

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        
        whenever(getActiveFlashDealsUseCase()).thenReturn(flowOf(sampleDeals))
        whenever(cartRepository.getCartItems()).thenReturn(cartItemsFlow)
        
        viewModel = FlashDealViewModel(
            getActiveFlashDealsUseCase,
            timerUseCase,
            logInteractionUseCase,
            cartRepository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState emits deals and cart items correctly`() = runTest {
        viewModel.uiState.test {
            // StateFlow always emits the current value immediately.
            // Depending on timing, this could be the initial empty state or the first combined state.
            var state = awaitItem()
            if (state.deals.isEmpty()) {
                state = awaitItem()
            }

            assertEquals(sampleDeals, state.deals)
            assertEquals(emptyList<CartItem>(), state.cartItems)
            
            val cartItem = CartItem(101, "Deal 1", 50.0, 1, "")
            cartItemsFlow.value = listOf(cartItem)
            
            val updatedState = awaitItem()
            assertEquals(listOf(cartItem), updatedState.cartItems)
        }
    }

    @Test
    fun `handleIntent JoinPool adds item to cart and logs interaction`() = runTest {
        val deal = sampleDeals[0]
        viewModel.handleIntent(FlashDealIntent.JoinPool(deal))
        advanceUntilIdle()

        verify(cartRepository).addToCart(argThat { productId == 101 })
        verify(logInteractionUseCase).invoke(any(), any())
    }

    @Test
    fun `handleIntent LeavePool removes item from cart`() = runTest {
        viewModel.handleIntent(FlashDealIntent.LeavePool(101))
        advanceUntilIdle()

        verify(cartRepository).removeFromCart(101)
    }

    @Test
    fun `countdownFor returns timer flow from usecase`() = runTest {
        val deal = sampleDeals[0]
        whenever(timerUseCase(deal.endTimeMillis)).thenReturn(flowOf(1000L, 500L, 0L))
        
        viewModel.countdownFor(deal).test {
            assertEquals(1000L, awaitItem())
            assertEquals(500L, awaitItem())
            assertEquals(0L, awaitItem())
        }
    }
}
