package aditya.kumdale.cryptoportfolioapp.navigation



import aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.presentation.HomeScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = NavGraph.HOME_GRAPH
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(
            route = NavGraph.HOME_GRAPH,
            startDestination = "home_scaffold_route",
        ) {
            composable("home_scaffold_route") {
                HomeScaffold()
            }
        }
    }
}
