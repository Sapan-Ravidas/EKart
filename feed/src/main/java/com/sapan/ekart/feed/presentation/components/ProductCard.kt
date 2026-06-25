package com.sapan.ekart.feed.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sapan.ekart.core.R
import com.sapan.ekart.core.designsystem.components.QuantitySelector
import com.sapan.ekart.feed.domain.model.Category
import com.sapan.ekart.feed.domain.model.Product

@Composable
fun ProductCard(
    product: Product,
    quantity: Int,
    onAddToCart: () -> Unit,
    onRemoveFromCart: () -> Unit,
    onShare: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = product.images.firstOrNull(),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = product.category.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = stringResource(R.string.feed_stock_count, product.inventoryCount),
                        style = MaterialTheme.typography.labelMedium,
                        color = if (product.inventoryCount < 10) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (quantity > 0) {
                        QuantitySelector(
                            quantity = quantity,
                            onIncrement = onAddToCart,
                            onDecrement = onRemoveFromCart
                        )
                    } else {
                        Button(onClick = onAddToCart) {
                            Text(stringResource(R.string.feed_add_to_cart))
                        }
                    }
                    TextButton(onClick = onShare) {
                        Text(stringResource(R.string.feed_share))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        product = Product(
            id = 1,
            title = "Sample Product",
            description = "This is a sample product description.",
            price = 99.99,
            images = listOf("https://example.com/image.jpg"),
            category = Category(id = 1, name = "Electronics", image = ""),
            inventoryCount = 10
        ),
        quantity = 0,
        onAddToCart = {},
        onRemoveFromCart = {},
        onShare = {}
    )
}
