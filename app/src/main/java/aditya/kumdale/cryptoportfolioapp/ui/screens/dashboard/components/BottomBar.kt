package aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components

import aditya.kumdale.cryptoportfolioapp.R
import aditya.kumdale.cryptoportfolioapp.navigation.Dashboard_ROUTE
import aditya.kumdale.cryptoportfolioapp.navigation.Exchange_ROUTE
import aditya.kumdale.cryptoportfolioapp.navigation.Transactions_ROUTE
import aditya.kumdale.cryptoportfolioapp.navigation.Wallet_ROUTE
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navItems = listOf(
        BottomNavItem("Analytics", painterResource(id = R.drawable.chartline), Dashboard_ROUTE),
        BottomNavItem("Exchange", painterResource(id = R.drawable.arrowscounterclockwise), Exchange_ROUTE),
        BottomNavItem("Record", painterResource(id = R.drawable.projectorscreenchart), Transactions_ROUTE),
        BottomNavItem("Wallet", painterResource(id = R.drawable.wallet), Wallet_ROUTE)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selectedIndex = navItems.indexOfFirst {
        currentDestination?.hierarchy?.any { dest -> dest.route == it.route } == true
    }.coerceAtLeast(0)

    Surface(
        modifier = modifier.wrapContentSize(),
        color = Color.Transparent
    ) {
        CompositionLocalProvider(LocalRippleConfiguration provides null) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        color = Color(0xFF2C2C2E),
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFF3A3A3A),
                        shape = CircleShape
                    )
            ) {
                TabRow(
                    selectedTabIndex = selectedIndex,
                    containerColor = Color(0xFF2C2C2E),
                    modifier = Modifier

                        .clip(CircleShape)
                        .height(68.dp),
                    indicator = { tabPositions ->
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[selectedIndex])
                                .fillMaxHeight()
                                .width(tabPositions[selectedIndex].width)
                                .padding(6.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF3742FA))
                        )
                    },
                    divider = {}
                ) {
                    navItems.forEachIndexed { index, item ->
                        Tab(
                            selected = selectedIndex == index,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            modifier = Modifier.zIndex(1f),
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.Gray,
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = "${item.label} Icon",
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            text = {
                                Text(
                                    text = item.label,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        )

                    }
                }
            }
        }
    }
}

data class BottomNavItem(
    val label: String,
    val icon: Painter,
    val route: String,
)


@Preview(showBackground = true, backgroundColor = 0xFF000033)
@Composable
fun BottomBarPreview() {
    AppBottomBar(
        rememberNavController()
    )
}


