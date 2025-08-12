package aditya.kumdale.cryptoportfolioapp.ui.screens.exchange.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

// Data class to represent a currency in the exchange
data class Currency(
    val code: String,
    val name: String,
    val balance: Double,
    // In a real app, this would be a URI for Coil/Glide
    val icon: @Composable () -> Unit
)

// Data class to hold the entire state of the exchange screen
data class ExchangeUiState(
    val fromCurrency: Currency,
    val toCurrency: Currency,
    val sendAmount: String = "2.640", // Initial amount from your screenshot
    val receiveAmount: Double = 0.0,
    val rate: Double = 176138.80, // 1 ETH to INR rate from screenshot
    val spread: Double = 0.002, // 0.2%
    val gasFee: Double = 422.73, // In the "from" currency's value, e.g., INR
    val finalReceivableAmount: Double = 0.0
) {
    // Helper to format currency values for display
    fun Double.toFormattedString(): String {
        return NumberFormat.getNumberInstance(Locale("en", "IN")).format(this)
    }
}

class ExchangeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ExchangeUiState(fromCurrency = eth, toCurrency = inr))
    val uiState: StateFlow<ExchangeUiState> = _uiState.asStateFlow()

    init {
        // Perform initial calculation on launch
        onAmountChange(_uiState.value.sendAmount)
    }

    fun onAmountChange(amount: String) {
        val sendAmountDouble = amount.toDoubleOrNull() ?: 0.0
        _uiState.update { currentState ->
            val baseReceiveAmount = sendAmountDouble * currentState.rate
            val spreadAmount = baseReceiveAmount * currentState.spread
            val finalReceivableAmount = baseReceiveAmount - spreadAmount - currentState.gasFee

            currentState.copy(
                sendAmount = amount,
                receiveAmount = baseReceiveAmount,
                finalReceivableAmount = finalReceivableAmount
            )
        }
    }

    fun swapCurrencies() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                // When swapping, we need to find the inverse rate.
                // Note: In a real app, you'd fetch the new rate from an API.
                // For this example, we'll calculate the inverse and clear the input.
                val newRate = 1 / currentState.rate
                currentState.copy(
                    fromCurrency = currentState.toCurrency,
                    toCurrency = currentState.fromCurrency,
                    rate = newRate,
                    sendAmount = "", // Clear amount on swap
                    receiveAmount = 0.0,
                    finalReceivableAmount = 0.0
                )
            }
        }
    }

    companion object {
        // Mock currencies based on the screenshot
        val eth = Currency(
            code = "ETH",
            name = "Ethereum",
            balance = 10.254
        ) { androidx.compose.material3.Icon(Icons.Default.CurrencyExchange, "ETH", tint = Color.White) } // Placeholder Icon

        val inr = Currency(
            code = "INR",
            name = "Indian Rupee",
            balance = 435804.0
        ) { androidx.compose.material3.Icon(Icons.Default.CurrencyRupee, "INR", tint = Color.White) } // Placeholder Icon
    }
}