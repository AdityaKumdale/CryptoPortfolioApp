package aditya.kumdale.cryptoportfolioapp.ui.screens.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: Dp = 64.dp,
    iconSize: Dp = 28.dp,
    contentDescription: String? = null
) {

    val buttonGradient = Brush.linearGradient(
        colors = listOf(Color(0xFF353535), Color(0xFF242424)),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Box(
        modifier = modifier
            .size(buttonSize)
            .clip(CircleShape)
            .background(buttonGradient)
            .border(
                width = 1.dp,
                color = Color(0xFF3A3A3A),
                shape = CircleShape
            )
            .clickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
fun ActionButtonGroup() {
    Row(
        horizontalArrangement = Arrangement .spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp),
    ) {
        CircularButton(
            icon = Icons.Default.ArrowUpward,
            contentDescription = "Up",
            onClick = {
                // TODO: Handle 'Up' action
            }
        )
        CircularButton(
            icon = Icons.Default.Add,
            contentDescription = "Add",
            onClick = {
                // TODO: Handle 'Add' action
            }
        )
        CircularButton(
            icon = Icons.Default.ArrowDownward,
            contentDescription = "Down",
            onClick = {
                // TODO: Handle 'Down' action
            }
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ActionButtonGroupPreview() {
    Surface(color = Color.Black) {
        ActionButtonGroup()
    }
}

