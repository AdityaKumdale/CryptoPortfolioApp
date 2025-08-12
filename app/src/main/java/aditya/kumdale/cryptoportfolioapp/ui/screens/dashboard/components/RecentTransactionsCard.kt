package aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components

import aditya.kumdale.cryptoportfolioapp.data.Transaction
import aditya.kumdale.cryptoportfolioapp.data.TransactionType
import aditya.kumdale.cryptoportfolioapp.data.mockTransactions
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.ui.graphics.vector.ImageVector
import org.w3c.dom.Text

@Composable
fun RecentTransactionsList(
    headingstring: String,
    endString: String
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp, vertical = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = headingstring,
                color = Color.White,
                fontSize = 14.sp,

                modifier = Modifier.padding(vertical = 1.dp)
            )
            Text(
                text = endString,
                color = Color.White,
                fontSize = 14.sp,

                modifier = Modifier.padding(vertical = 9.dp)
            )
        }

        mockTransactions.forEach { transaction ->
            TransactionItem(transaction)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Composable
fun TransactionHistory(transactions: List<Transaction>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(transactions) { transaction ->
            TransactionItem(transaction = transaction)
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    val icon: ImageVector
    val iconColor: Color

    when (transaction.type) {
        TransactionType.RECEIVE -> {
            icon = Icons.Default.ArrowDownward
            iconColor = Color.White
        }

        TransactionType.SEND -> {
            icon = Icons.Default.ArrowUpward
            iconColor = Color.White
        }
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1F1F1F)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon section
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF333333),
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = transaction.type.displayText,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Transaction Type and Date section
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.type.displayText,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = transaction.date,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            // Token and Amount section
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = transaction.token,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "+${transaction.amount}",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF11111B)
@Composable
fun RecentTransactionsListPreview() {
    RecentTransactionsList("Transactions", "Last 4 days")
}


@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun TransactionHistoryPreview() {

    TransactionHistory(transactions = mockTransactions)

}





