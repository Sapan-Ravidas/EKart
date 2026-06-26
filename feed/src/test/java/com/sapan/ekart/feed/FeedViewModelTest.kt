package com.sapan.ekart.feed

import androidx.paging.PagingData
import app.cash.turbine.test
import com.sapan.ekart.core.analytics.domain.model.Interaction
import com.sapan.ekart.core.analytics.domain.repository.AnalyticsRepository
import com.sapan.ekart.core.analytics.domain.usecase.LogInteractionUseCase
import com.sapan.ekart.core.cart.domain.model.CartItem
import com.sapan.ekart.core.cart.domain.repository.CartRepository
import com.sapan.ekart.core.network.Resource
import com.sapan.ekart.feed.domain.model.Category
import com.sapan.ekart.feed.domain.model.Product
import com.sapan.ekart.feed.domain.repository.ProductRepository
import com.sapan.ekart.feed.domain.usecase.GetPagedProductsUseCase
import com.sapan.ekart.feed.domain.usecase.RefreshProductsUseCase
import com.sapan.ekart.feed.presentation.FeedIntent
import com.sapan.ekart.feed.presentation.FeedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FeedViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: ProductRepository
    private lateinit var viewModel: FeedViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = object : ProductRepository {
            override fun getProducts() = flow {
                emit(fakeProducts())
            }

            override fun getPagedProducts(categoryName: String?) = flow {
                emit(PagingData.from(fakeProducts()))
            }

            override suspend fun refreshProducts(): Resource<Unit> = Resource.Success(Unit)
            
            override suspend fun getCategories(): Resource<List<String>> = Resource.Success(listOf("beauty", "electronics"))
        }

        val getPagedProductsUseCase = GetPagedProductsUseCase(repository)
        val refreshProductsUseCase = RefreshProductsUseCase(repository)
        val logInteractionUseCase = LogInteractionUseCase(object : AnalyticsRepository {
            override suspend fun logInteraction(interaction: Interaction) {}
            override suspend fun getLoggedInteractions(): List<Interaction> = emptyList()
            override suspend fun drainInteractions(): List<Interaction> = emptyList()
            override suspend fun clearInteractions() {}
        })
        val cartRepository = object : CartRepository {
            override fun getCartItems() = flow { emit(emptyList<CartItem>()) }
            override suspend fun addToCart(item: CartItem) {}
            override suspend fun removeFromCart(productId: Int) {}
            override suspend fun clearCart() {}
            override suspend fun updateQuantity(productId: Int, quantity: Int) {}
        }

        viewModel = FeedViewModel(
            repository = repository,
            getPagedProductsUseCase = getPagedProductsUseCase,
            refreshProductsUseCase = refreshProductsUseCase,
            logInteractionUseCase = logInteractionUseCase,
            cartRepository = cartRepository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initial_state_should_load_products_and_categories() = runTest {
        viewModel.uiState.test {
            awaitItem() // Initial state
            runCurrent()
            val updatedState = awaitItem()
            assertEquals(false, updatedState.isLoading)
            assertEquals(2, updatedState.categories.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun selectCategory_updates_selectedCategory() = runTest {
        viewModel.uiState.test {
            awaitItem() // Initial
            runCurrent()
            awaitItem() // After categories loaded
            
            viewModel.handleIntent(FeedIntent.SelectCategory("beauty"))
            
            val state = awaitItem()
            assertEquals("beauty", state.selectedCategory)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun fakeProducts() = listOf(
        Product(
            id = 1,
            title = "Test Product A",
            price = 34.5,
            description = "Description A",
            images = listOf("https://example.com/a.png"),
            inventoryCount = 5,
            category = Category(1, "beauty", "")
        ),
        Product(
            id = 2,
            title = "Test Product B",
            price = 42.0,
            description = "Description B",
            images = listOf("https://example.com/b.png"),
            inventoryCount = 10,
            category = Category(2, "electronics", "")
        )
    )
}
