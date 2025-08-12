package aditya.kumdale.cryptoportfolioapp.ui.screens.transactions.components

import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val cardGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF6A5AE0), Color(0xFF4E409A))
)
private val positiveChangeColor = Color(0xFF00C853)
private val negativeChangeColor = Color(0xFFD50000)
private val stackLayer1Color = Color(0xFF4A3A99).copy(alpha = 0.7f)
private val stackLayer2Color = Color(0xFF4A3A99).copy(alpha = 0.5f)

/**
 * Data class representing the portfolio summary.
 *
 * @param totalValue The main balance string.
 * @param currency The currency code (e.g., "INR").
 * @param dailyChangeAbsolute The absolute change in value as a string (e.g., "1,342").
 * @param dailyChangePercentage The percentage change. Positive for gain, negative for loss.
 */
data class PortfolioSummary(
    val totalValue: String,
    val currency: String,
    val dailyChangeAbsolute: String,
    val dailyChangePercentage: Double
)

val mockPortfolioSummary = PortfolioSummary(
    totalValue = "1,57,342.05",
    currency = "INR",
    dailyChangeAbsolute = "1,342",
    dailyChangePercentage = 4.6
)


@Composable
fun PortfolioCard(summary: PortfolioSummary) {
    val changeColor =
        if (summary.dailyChangePercentage >= 0) positiveChangeColor else negativeChangeColor
    val changeSign = if (summary.dailyChangePercentage >= 0) "+" else ""
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .padding(16.dp)
    ) {


        // Layer 2 (Bottom-most layer)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)

                .width(340.dp)
                .height(180.dp)
                .offset(y = 10.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(stackLayer2Color)
        )

        // Layer 1 (Middle layer)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)

                .width(350.dp)
                .height(185.dp)
                .offset(y = 5.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(stackLayer1Color)
        )

        // Main Layer

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(brush = cardGradient)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // INR Tag
            Surface(
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = summary.currency,
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            // Main Balance
            Text(
                text = summary.totalValue,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            // Daily Change Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "₹ ${summary.dailyChangeAbsolute}",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "$changeSign${summary.dailyChangePercentage}%",
                    color = changeColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

    }
}


@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun PortfolioSummaryCardPreview() {
    PortfolioCard(
        summary = mockPortfolioSummary
    )
}