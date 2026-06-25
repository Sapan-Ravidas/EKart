package com.sapan.ekart.flashdeal.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sapan.ekart.core.R
import com.sapan.ekart.core.designsystem.components.JoinPoolButton
import com.sapan.ekart.flashdeal.domain.model.FlashDeal
import java.util.Locale

@Composable
fun FlashDealItem(
    deal: FlashDeal,
    timerFlow: androidx.compose.runtime.State<Long>,
    quantity: Int,
    onJoin: () -> Unit,
    onLeave: () -> Unit,
    onShare: () -> Unit
) {
    val remainingMillis = timerFlow.value
    val remainingSeconds = (remainingMillis / 1000).coerceAtLeast(0)
    val minutes = remainingSeconds / 60
    val seconds = remainingSeconds % 60
    val countdown = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (remainingMillis < 60000) Color(0xFFFFEBEE) else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column {
            Box {
                AsyncImage(
                    model = deal.imageUrl,
                    contentDescription = deal.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
                Surface(
                    color = Color.Black.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(bottomEnd = 12.dp),
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = stringResource(R.string.deal_limited_deal),
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(deal.title, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.weight(1f))
                    Text(
                        text = countdown,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Red
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "$${deal.dealPrice}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.deal_was_price, "$${deal.originalPrice}"),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    JoinPoolButton(
                        isJoined = quantity > 0,
                        onJoin = onJoin,
                        onLeave = onLeave
                    )
                    TextButton(onClick = onShare) {
                        Text(stringResource(R.string.deal_share))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlashDealItemPreview() {
    val mockDeal = FlashDeal(
        id = "1",
        productId = 101,
        title = "Premium Headphones",
        imageUrl = "https://example.com/image.jpg",
        originalPrice = 199.99,
        dealPrice = 149.99,
        endTimeMillis = System.currentTimeMillis() + 3600000
    )
    FlashDealItem(
        deal = mockDeal,
        timerFlow = remember { mutableStateOf(3600000L) },
        quantity = 0,
        onJoin = {},
        onLeave = {},
        onShare = {}
    )
}
