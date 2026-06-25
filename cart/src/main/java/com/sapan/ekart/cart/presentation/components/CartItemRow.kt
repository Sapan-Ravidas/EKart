package com.sapan.ekart.cart.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sapan.ekart.core.R
import com.sapan.ekart.core.cart.domain.model.CartItem
import com.sapan.ekart.core.designsystem.components.QuantitySelector

@Composable
fun CartItemRow(
    item: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.title, fontWeight = FontWeight.Bold)
                Text(text = "$${item.price}", color = MaterialTheme.colorScheme.primary)
                
                QuantitySelector(
                    quantity = item.quantity,
                    onIncrement = { onQuantityChange(item.quantity + 1) },
                    onDecrement = { 
                        if (item.quantity > 1) onQuantityChange(item.quantity - 1)
                        else onRemove()
                    },
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.cart_remove))
            }
        }
    }
}
