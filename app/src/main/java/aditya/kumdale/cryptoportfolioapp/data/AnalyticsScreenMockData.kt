package aditya.kumdale.cryptoportfolioapp.data

import aditya.kumdale.cryptoportfolioapp.R
import aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components.PortfolioDataPoint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.random.Random


// Define your data class as before
data class Asset(val name: String, val code: String, val value: String, val change: String, val icon: Painter, val color: Color)

@Composable
fun getMockAssets(): List<Asset> {
    return listOf(
        Asset("Bitcoin", "BTC", "₹ 75,62,502.14", "+3.2%", painterResource(id = R.drawable.bitcoin), Color(0xFFF7931A)),
        Asset("Ether", "ETH", "₹ 1,79,102.50", "+5.8%", painterResource(id = R.drawable.eth), Color.Gray),
        Asset("Ripple", "XRP", "₹ 52.45", "-1.1%", painterResource(id = R.drawable.eth), Color(0xFF00A2E8))
    )
}

/**
 * Enum to represent the type of transaction.
 */
enum class TransactionType(val displayText: String) {
    RECEIVE("Receive"),
    SEND("Sent")
}

/**
 * Data class to hold information about a single crypto transaction.
 */
data class Transaction(
    val type: TransactionType,
    val date: String,
    val token: String,
    val amount: Double
)

/**
 * A mock list of transactions for preview and development.
 */
val mockTransactions = listOf(
    Transaction(TransactionType.RECEIVE, "20 March", "BTC", 0.002126),
    Transaction(TransactionType.SEND, "19 March", "ETH", 0.003126),
    Transaction(TransactionType.SEND, "18 March", "LTC", 0.02126),
    Transaction(TransactionType.RECEIVE, "15 March", "DOGE", 150.50),
    Transaction(TransactionType.SEND, "14 March", "XRP", 55.12),
)


/**
 * Generates random portfolio data for different timeframes.
 */
fun generateMockPortfolioData(timeframe: String): List<PortfolioDataPoint> {
    val random = Random(System.currentTimeMillis())
    val now = System.currentTimeMillis()
    val (points, hours) = when (timeframe) {
        "1h" -> 60 to 1L
        "8h" -> 60 to 8L
        "1d" -> 48 to 24L
        "1w" -> 7 * 24 to 7 * 24L
        "1m" -> 30 * 12 to 30 * 24L
        "6m" -> 180 * 4 to 180 * 24L
        "1y" -> 365 to 365 * 24L
        else -> 30 to 24L
    }

    val timeStep = (hours * 60 * 60 * 1000) / points
    var currentValue = 140000f + random.nextInt(-10000, 10000)

    return (0 until points).map { i ->
        val timestamp = now - (points - 1 - i) * timeStep
        currentValue += random.nextFloat() * 4000f - 2000f
        PortfolioDataPoint(timestamp, currentValue.coerceAtLeast(50000f))
    }
}

/**
 * Formats a timestamp into a date string (e.g., "24 March").
 */
 fun formatDate(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("d MMMM", Locale.getDefault())
    return format.format(date)
}

/**
 * Formats a float value into a currency string (e.g., "₹1,42,340").
 */
 fun formatCurrency(value: Float): String {
    val format = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    format.maximumFractionDigits = 0
    return format.format(value.roundToInt())
}

