package com.sapan.ekart.feed.presentation

import android.content.Intent
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sapan.ekart.core.R
import com.sapan.ekart.core.designsystem.components.ErrorLayout
import com.sapan.ekart.core.util.AnalyticsConstants
import com.sapan.ekart.core.util.NetworkConstants
import com.sapan.ekart.feed.domain.model.Product
import com.sapan.ekart.feed.presentation.components.CategoryFilterBar
import com.sapan.ekart.feed.presentation.components.ProductCard

@Composable
fun FeedScreen(
    viewModel: FeedViewModel
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagedProducts = viewModel.pagedProducts.collectAsLazyPagingItems()

    FeedContent(
        uiState = uiState,
        pagedProducts = pagedProducts,
        onIntent = { intent ->
            when (intent) {
                is FeedIntent.LogInteraction -> {
                    viewModel.handleIntent(intent)
                    if (intent.type == AnalyticsConstants.TYPE_SHARED_LINK) {
                        val productTitle = intent.details.substringAfter(AnalyticsConstants.DETAILS_SHARED_PREFIX)
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, context.getString(R.string.feed_share_text, productTitle))
                            type = NetworkConstants.MIME_TEXT_PLAIN
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }
                }
                else -> viewModel.handleIntent(intent)
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedContent(
    uiState: FeedUiState,
    pagedProducts: LazyPagingItems<Product>,
    onIntent: (FeedIntent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CategoryFilterBar(
            categories = uiState.categories,
            selectedCategory = uiState.selectedCategory,
            onCategorySelected = { onIntent(FeedIntent.SelectCategory(it)) }
        )

        AnimatedContent(
            targetState = uiState.selectedCategory,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "CategoryTransition",
            modifier = Modifier.fillMaxSize()
        ) { _ ->
            Box(modifier = Modifier.fillMaxSize()) {
                if (pagedProducts.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (pagedProducts.loadState.refresh is LoadState.Error) {
                    val error = (pagedProducts.loadState.refresh as LoadState.Error).error
                    ErrorLayout(
                        message = error.localizedMessage ?: stringResource(R.string.feed_failed_to_load),
                        onRetry = { onIntent(FeedIntent.Refresh) },
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    ProductList(
                        pagedProducts = pagedProducts,
                        cartItems = uiState.cartItems,
                        onIntent = onIntent
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductList(
    pagedProducts: LazyPagingItems<Product>,
    cartItems: List<com.sapan.ekart.core.cart.domain.model.CartItem>,
    onIntent: (FeedIntent) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        items(
            count = pagedProducts.itemCount,
            key = pagedProducts.itemKey { it.id }
        ) { index ->
            pagedProducts[index]?.let { product ->
                val cartItem = cartItems.find { it.productId == product.id }
                val quantity = cartItem?.quantity ?: 0
                ProductCard(
                    product = product,
                    quantity = quantity,
                    onAddToCart = { onIntent(FeedIntent.AddToCart(product)) },
                    onRemoveFromCart = { onIntent(FeedIntent.RemoveFromCart(product.id)) },
                    onShare = {
                        onIntent(FeedIntent.LogInteraction(AnalyticsConstants.TYPE_SHARED_LINK, "${AnalyticsConstants.DETAILS_SHARED_PREFIX}${product.title}"))
                    }
                )
            }
        }

        if (pagedProducts.loadState.append is LoadState.Loading) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        } else if (pagedProducts.loadState.append is LoadState.Error) {
            item(span = StaggeredGridItemSpan.FullLine) {
                val error = (pagedProducts.loadState.append as LoadState.Error).error
                ErrorLayout(
                    message = error.localizedMessage ?: stringResource(R.string.feed_failed_to_load),
                    onRetry = { pagedProducts.retry() }
                )
            }
        }
    }
}
