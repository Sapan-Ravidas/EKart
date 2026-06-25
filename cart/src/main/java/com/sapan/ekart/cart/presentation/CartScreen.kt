package com.sapan.ekart.cart.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sapan.ekart.cart.presentation.components.CartItemRow
import com.sapan.ekart.core.R

@Composable
fun CartScreen(viewModel: CartViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CartContent(
        uiState = uiState,
        onIntent = { viewModel.handleIntent(it) }
    )
}

@Composable
fun CartContent(
    uiState: CartUiState,
    onIntent: (CartIntent) -> Unit
) {
    if (uiState.checkoutSuccess) {
        AlertDialog(
            onDismissRequest = { onIntent(CartIntent.DismissSuccessDialog) },
            title = { Text(stringResource(R.string.cart_success_title)) },
            text = { Text(stringResource(R.string.cart_success_message)) },
            confirmButton = {
                TextButton(onClick = { onIntent(CartIntent.DismissSuccessDialog) }) {
                    Text(stringResource(R.string.cart_ok))
                }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = stringResource(R.string.cart_title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (uiState.cartItems.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.cart_empty))
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.cartItems, key = { it.productId }) { item ->
                        CartItemRow(
                            item = item,
                            onQuantityChange = { quantity ->
                                onIntent(CartIntent.UpdateQuantity(item.productId, quantity))
                            },
                            onRemove = { onIntent(CartIntent.RemoveFromCart(item.productId)) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val totalPrice = uiState.cartItems.sumOf { it.price * it.quantity }
                Text(
                    text = stringResource(R.string.cart_total, String.format("%.2f", totalPrice)),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.End)
                )

                Button(
                    onClick = { onIntent(CartIntent.Checkout) },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {
                    Text(stringResource(R.string.cart_checkout))
                }
            }
        }
    }
}
