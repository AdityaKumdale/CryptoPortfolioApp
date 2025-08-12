package aditya.kumdale.cryptoportfolioapp.navigation


import androidx.navigation.NavController


const val Dashboard_ROUTE = "dashboard_route"


fun NavController.navigateToDashboard() {
    this.navigate(Dashboard_ROUTE)
}


const val Exchange_ROUTE = "exchange_route"

fun NavController.navigateToExchange() {
    this.navigate(Exchange_ROUTE)
}



const val Transactions_ROUTE = "transactions_route"

fun NavController.navigateToTransactions() {
    this.navigate(Transactions_ROUTE)
}


const val Wallet_ROUTE = "wallet_route"

fun NavController.navigateToWallet() {
    this.navigate(Wallet_ROUTE)
}