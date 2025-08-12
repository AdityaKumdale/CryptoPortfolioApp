package aditya.kumdale.cryptoportfolioapp.ui.screens.transactions.components

import aditya.kumdale.cryptoportfolioapp.data.PortfolioSummary
import aditya.kumdale.cryptoportfolioapp.data.mockPortfolioSummary
import aditya.kumdale.cryptoportfolioapp.ui.theme.cardGradient
import aditya.kumdale.cryptoportfolioapp.ui.theme.negativeChangeColor
import aditya.kumdale.cryptoportfolioapp.ui.theme.positiveChangeColor
import aditya.kumdale.cryptoportfolioapp.ui.theme.stackLayer1Color
import aditya.kumdale.cryptoportfolioapp.ui.theme.stackLayer2Color
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

                .width(350.dp)
                .height(180.dp)
                .offset(y = 10.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(stackLayer2Color)
        )

        // Layer 1 (Middle layer)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(360.dp)
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