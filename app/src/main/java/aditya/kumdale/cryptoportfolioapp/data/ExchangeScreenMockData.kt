package aditya.kumdale.cryptoportfolioapp.data

import androidx.compose.ui.graphics.painter.Painter

/**
 * Represents a currency that can be used in the exchange.
 *
 * @param name The full name of the currency (e.g., "Ethereum").
 * @param code The currency ticker symbol (e.g., "ETH").
 * @param icon The icon representing the currency.
 * @param balance The available balance for this currency.
 */
data class ExchangeCurrency(
    val name: String,
    val code: String,
    val icon: Painter,
    val balance: Double
)