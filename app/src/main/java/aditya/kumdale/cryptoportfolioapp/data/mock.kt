package aditya.kumdale.cryptoportfolioapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


data class Asset(val name: String, val code: String, val value: String, val change: String, val icon: ImageVector, val color: Color)

val mockAssets = listOf(
    Asset("Bitcoin", "BTC", "₹ 75,62,502.14", "+3.2%", Icons.Default.AccountCircle, Color(0xFFF7931A)),
    Asset("Ether", "ETH", "₹ 1,79,102.50", "+5.8%", Icons.Default.Edit, Color.Gray),
    Asset("Ripple", "XRP", "₹ 52.45", "-1.1%", Icons.Default.CheckCircle, Color(0xFF00A2E8))
)

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

