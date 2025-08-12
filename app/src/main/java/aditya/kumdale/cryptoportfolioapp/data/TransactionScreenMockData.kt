package aditya.kumdale.cryptoportfolioapp.data

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