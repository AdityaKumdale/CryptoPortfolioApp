package aditya.kumdale.cryptoportfolioapp.ui.screens.exchange.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SwapButton(onClick: () -> Unit) {

    val outerBorderColor = Color(0xFF4A4A4C)
    val innerBorderColor = Color(0xFF5A5A5C) 
    val iconColor = Color.White

    val buttonShape = RoundedCornerShape(16.dp)
    val innerShape = RoundedCornerShape(12.dp)


    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF3A3A3C),
            Color(0xFF1C1C1E)
        )
    )

    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(54.dp)
            .clip(buttonShape)
            .background(backgroundBrush)
    ) {

        Box(
            modifier = Modifier
                .size(38.dp)
                .border(
                    width = 1.dp,
                    color = innerBorderColor,
                    shape = innerShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.SwapVert,
                contentDescription = "Swap Vertically",
                tint = iconColor,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF1C1C1E)
@Composable
fun SwapButtonPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        SwapButton(onClick = {
            println("Swap Button Clicked!")
        })
    }
}
