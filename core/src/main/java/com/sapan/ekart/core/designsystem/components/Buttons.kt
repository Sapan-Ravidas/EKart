package com.sapan.ekart.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sapan.ekart.core.R

@Composable
fun JoinPoolButton(
    isJoined: Boolean,
    onJoin: () -> Unit,
    onLeave: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isJoined) {
        OutlinedButton(
            onClick = onLeave,
            modifier = modifier,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF4CAF50)
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF4CAF50))
            ),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(stringResource(R.string.deal_joined))
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(1.dp)
                    .background(Color(0xFF4CAF50).copy(alpha = 0.3f))
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.cd_close),
                modifier = Modifier.size(16.dp),
                tint = Color.Red.copy(alpha = 0.7f)
            )
        }
    } else {
        Button(
            onClick = onJoin,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(stringResource(R.string.deal_join_pool))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JoinPoolButtonPreview() {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        JoinPoolButton(isJoined = false, onJoin = {}, onLeave = {})
        JoinPoolButton(isJoined = true, onJoin = {}, onLeave = {})
    }
}

@Composable
fun QuantitySelector(
    quantity: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 32.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedIconButton(onClick = onDecrement, modifier = Modifier.size(size)) {
            Text(stringResource(R.string.cd_minus), style = textStyle)
        }
        Text(
            text = quantity.toString(),
            style = textStyle,
            fontWeight = FontWeight.Bold
        )
        OutlinedIconButton(onClick = onIncrement, modifier = Modifier.size(size)) {
            Text(stringResource(R.string.cd_plus), style = textStyle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuantitySelectorPreview() {
    QuantitySelector(
        quantity = 5,
        onIncrement = {},
        onDecrement = {}
    )
}
