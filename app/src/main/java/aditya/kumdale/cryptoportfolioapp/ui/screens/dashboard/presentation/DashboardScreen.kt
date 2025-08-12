package aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.presentation


import aditya.kumdale.cryptoportfolioapp.navigation.Dashboard_ROUTE
import aditya.kumdale.cryptoportfolioapp.navigation.Exchange_ROUTE
import aditya.kumdale.cryptoportfolioapp.navigation.Transactions_ROUTE
import aditya.kumdale.cryptoportfolioapp.navigation.Wallet_ROUTE
import aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components.AnalyticsScreen
import aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components.AppBottomBar
import aditya.kumdale.cryptoportfolioapp.ui.screens.exchange.presentation.ExchangeScreenRoute
import aditya.kumdale.cryptoportfolioapp.ui.screens.transactions.presentation.TransactionsScreenRoute
import aditya.kumdale.cryptoportfolioapp.ui.screens.wallet.WalletScreenRoute
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild


@Composable
fun HomeScaffold() {

    val homeNavController = rememberNavController()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hazeState = remember { HazeState() }
    Scaffold(
        bottomBar = {




                Row(
                    modifier = Modifier
                        //.blur(30.dp)
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                        .navigationBarsPadding(), verticalAlignment = Alignment.CenterVertically
                ) {

                    AppBottomBar(
                        navController = homeNavController,
                        modifier = Modifier.weight(1f).hazeChild(state = hazeState)
                    )

                    if (currentDestination?.route == Dashboard_ROUTE) {
                        Spacer(modifier = Modifier.width(8.dp))
                        FloatingActionButton(
                            onClick = { /* TODO: Handle FAB click */ },
                            shape = CircleShape,
                            containerColor = Color.White,
                            contentColor = Color.Blue,
                            modifier = Modifier.hazeChild(state = hazeState)
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Add Transaction",
                                modifier = Modifier.size(27.dp)
                            )
                        }
                    }
                }
        }

    ) { paddingValues ->

        NavHost(
            navController = homeNavController,
            startDestination = Dashboard_ROUTE,
            modifier = Modifier.consumeWindowInsets(paddingValues)
        ) {
            composable(route = Dashboard_ROUTE) {
                AnalyticsScreen()

            }
            composable(route = Exchange_ROUTE) {
                ExchangeScreenRoute()
            }
            composable(route = Transactions_ROUTE) {
                TransactionsScreenRoute()
            }
            composable(route = Wallet_ROUTE) {
                WalletScreenRoute()
            }

        }
    }
}


@Preview(showBackground = true, heightDp = 1000)
@Composable
fun HomeScaffoldPreview() {
    HomeScaffold()
}




