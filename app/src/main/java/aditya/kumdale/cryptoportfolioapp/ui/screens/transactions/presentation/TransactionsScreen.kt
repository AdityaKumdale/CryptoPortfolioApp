package aditya.kumdale.cryptoportfolioapp.ui.screens.transactions.presentation

import aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components.RecentTransactionsList
import aditya.kumdale.cryptoportfolioapp.ui.screens.transactions.components.ActionButtonGroup
import aditya.kumdale.cryptoportfolioapp.ui.screens.transactions.components.PortfolioCard
import aditya.kumdale.cryptoportfolioapp.ui.screens.transactions.components.mockPortfolioSummary
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TransactionsScreenRoute(

) {
    TransactionsScreen(

    )

}

@Composable
fun TransactionsScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
                Icon(Icons.Outlined.Notifications, contentDescription = null, tint = Color.White)
            }


            PortfolioCard(
                summary = mockPortfolioSummary
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ActionButtonGroup()
            }


            RecentTransactionsList(
                "Transactions",
                "Last 4 days"
            )


        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun TransactionsScreenPreview() {
    TransactionsScreen()

}