package com.sapan.ekart.flashdeal.presentation

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sapan.ekart.core.R
import com.sapan.ekart.core.util.AnalyticsConstants
import com.sapan.ekart.core.util.NetworkConstants
import com.sapan.ekart.flashdeal.domain.model.FlashDeal
import com.sapan.ekart.flashdeal.presentation.components.FlashDealItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun FlashDealScreen(viewModel: FlashDealViewModel) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FlashDealContent(
        uiState = uiState,
        countdownProvider = { viewModel.countdownFor(it) },
        onIntent = { intent ->
            when (intent) {
                is FlashDealIntent.LogInteraction -> {
                    viewModel.handleIntent(intent)
                    if (intent.type == AnalyticsConstants.TYPE_SHARED_LINK) {
                        val shareText = intent.details.substringAfter(AnalyticsConstants.DETAILS_SHARED_PREFIX)
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, context.getString(R.string.deal_share_text, shareText))
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


@Composable
fun FlashDealContent(
    uiState: FlashDealUiState,
    countdownProvider: (FlashDeal) -> StateFlow<Long>,
    onIntent: (FlashDealIntent) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            Text(
                text = stringResource(R.string.deal_flash_pools),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        if (uiState.deals.isEmpty()) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Text(stringResource(R.string.deal_no_active_pools))
            }
        } else {
            items(uiState.deals, key = { it.id }) { deal ->
                val timerState = remember(deal.id) { countdownProvider(deal) }.collectAsStateWithLifecycle(initialValue = 0L)
                val cartItem = uiState.cartItems.find { it.productId == deal.productId }
                val quantity = cartItem?.quantity ?: 0

                FlashDealItem(
                    deal = deal,
                    timerFlow = timerState,
                    quantity = quantity,
                    onJoin = { onIntent(FlashDealIntent.JoinPool(deal)) },
                    onLeave = { onIntent(FlashDealIntent.LeavePool(deal.productId)) },
                    onShare = {
                        onIntent(FlashDealIntent.LogInteraction(AnalyticsConstants.TYPE_SHARED_LINK, "${AnalyticsConstants.DETAILS_SHARED_PREFIX}flash pool ${deal.title} at $${deal.dealPrice}"))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlashDealContentPreview() {
    val mockDeal = FlashDeal(
        id = "1",
        productId = 101,
        title = "Premium Headphones",
        imageUrl = "https://example.com/image.jpg",
        originalPrice = 199.99,
        dealPrice = 149.99,
        endTimeMillis = System.currentTimeMillis() + 3600000
    )
    FlashDealContent(
        uiState = FlashDealUiState(deals = listOf(mockDeal)),
        countdownProvider = { MutableStateFlow(3600000L) },
        onIntent = {}
    )
}
