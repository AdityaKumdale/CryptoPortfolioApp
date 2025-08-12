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

///**
// * A custom IconButton styled to match the provided image.
// * It features a dark background, a light border, and rounded corners.
// *
// * @param onClick The lambda to be executed when the button is clicked.
// */
//@Composable
//fun SwapButton(onClick: () -> Unit) {
//    // Define custom colors for better visual appeal
//    val buttonBackgroundColor = Color(0xFF2C2C2E) // A dark charcoal color
//    val buttonBorderColor = Color(0xFF4A4A4C)   // A subtle gray for the border
//    val iconColor = Color.White
//
//    // Define the shape with a corner radius
//    val buttonShape = RoundedCornerShape(16.dp)
//
//    IconButton(
//        onClick = onClick,
//        modifier = Modifier
//            .size(64.dp) // Sets a fixed size for the button
//            // First, apply the border. The border will be drawn outside the background.
//            .border(
//                width = 1.dp,
//                color = buttonBorderColor,
//                shape = buttonShape
//            )
//            // Then, apply padding to create space between the border and the background.
//            // This makes the border appear more distinct.
//            .padding(1.dp)
//            // Clip the content to the button's shape
//            .clip(buttonShape)
//            // Finally, set the background color
//            .background(buttonBackgroundColor)
//    ) {
//        Icon(
//            imageVector = Icons.Default.SwapVert,
//            contentDescription = "Swap Vertically", // Important for accessibility
//            tint = iconColor,
//            modifier = Modifier.size(32.dp) // Adjust icon size as needed
//        )
//    }
//}
//
///**
// * A preview function to display the SwapButton in Android Studio's preview pane.
// * It's wrapped in a Box to provide a contrasting background.
// */
//@Preview(showBackground = true, )
//@Composable
//fun SwapButtonPreview() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(20.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            SwapButton(onClick = {
//                // This is a placeholder for the click action.
//                // In a real app, you would put your logic here.
//                println("Swap Button Clicked!")
//            })
//        }
//    }
//}

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

/**
 * A preview function to display the SwapButton in Android Studio's preview pane.
 * It's wrapped in a Box to provide a contrasting background.
 */
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
            // This is a placeholder for the click action.
            // In a real app, you would put your logic here.
            println("Swap Button Clicked!")
        })
    }
}
