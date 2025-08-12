package aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnalyticsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        PortfolioCard4()
        PortfolioChartCard()
        AssetsHorizontalList()
        RecentTransactionsList(
            "Recent Transactions", ""
        )
        Spacer(modifier = Modifier.height(200.dp))
    }
}


@Preview()
@Composable
fun AnalyticsScreenPreview() {
    AnalyticsScreen()
}