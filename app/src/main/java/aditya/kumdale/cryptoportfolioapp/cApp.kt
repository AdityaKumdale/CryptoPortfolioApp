package aditya.kumdale.cryptoportfolioapp

import aditya.kumdale.cryptoportfolioapp.navigation.MainNavGraph
import aditya.kumdale.cryptoportfolioapp.navigation.NavGraph.HOME_GRAPH
import aditya.kumdale.cryptoportfolioapp.ui.theme.CryptoPortfolioAppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun cApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navDestination = HOME_GRAPH

    CryptoPortfolioAppTheme {
        MainNavGraph(
            modifier = modifier,
            navHostController = navController,
            startDestination = navDestination
        )
    }
}