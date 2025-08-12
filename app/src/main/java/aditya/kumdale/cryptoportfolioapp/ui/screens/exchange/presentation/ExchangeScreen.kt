package aditya.kumdale.cryptoportfolioapp.ui.screens.exchange.presentation

import aditya.kumdale.cryptoportfolioapp.R
import aditya.kumdale.cryptoportfolioapp.data.ExchangeCurrency
import aditya.kumdale.cryptoportfolioapp.ui.screens.exchange.components.SwapButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale
// Assuming you have an R file with drawables, e.g., R.drawable.ic_eth_logo
// For this example, we'll simulate it with a placeholder.
// You should replace `ImageVector.vectorResource(id = R.drawable.ic_eth_logo)`
// with your actual resource. For now, we'll use a default icon.
import androidx.compose.ui.res.painterResource

@Composable
fun ExchangeScreenRoute(

){
    ExchangeScreen(

    )

}



@Composable
fun CurrencySection(
    sectionLabel: String,
    currency: ExchangeCurrency,
    amount: String,
    isInput: Boolean,
    shape: Shape,
    onAmountChange: (String) -> Unit = {}
) {

    val numberFormat = NumberFormat.getNumberInstance()
    numberFormat.maximumFractionDigits = 3
    val formattedBalance = numberFormat.format(currency.balance)
    val balanceLabel = "Balance"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1C1C1E), shape = shape)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        // Top Row: Currency Selector & Section Label
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.eth),
                    contentDescription = currency.name,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(currency.code, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Select currency", tint = Color.Gray)
            }
            Spacer(Modifier.weight(1f))
            Text(sectionLabel, color = Color.Gray, fontSize = 16.sp)
        }

        Spacer(Modifier.height(24.dp))

        // Middle: Main Amount Field
        if (isInput) {
            BasicTextField(
                value = amount,
                onValueChange = onAmountChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
                singleLine = true,
                cursorBrush = SolidColor(Color.White),
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = amount,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = balanceLabel,
                color = Color.Gray,
                fontSize = 14.sp
            )
            Text(
                text = formattedBalance,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun SummaryDetails(
    rate: Double,
    spread: Double,
    gasFee: Double,
    receivableAmount: Double,
    fromCode: String,
    toCode: String,
    formatter: NumberFormat
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SummaryRow("Rate", "1 $fromCode = ${formatter.format(rate).replace("₹", "₹ ")}")
        SummaryRow("Spread", "${spread * 100}%")
        SummaryRow("Gas fee", formatter.format(gasFee).replace("₹", "₹ "))
        SummaryRow("You will receive", formatter.format(receivableAmount).replace("₹", "₹ "), isBold = true)
    }
}

@Composable
fun SummaryRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 16.sp
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}


// --- Main Exchange Screen Composable (UPDATED) ---

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExchangeScreen() {

    val ethCurrency = ExchangeCurrency("ETH", "ETH", painterResource(id = R.drawable.eth), 10.254)
    val inrCurrency = ExchangeCurrency("INR", "INR", painterResource(id = R.drawable.rupee) , 435804.0)
    var fromCurrency by remember { mutableStateOf(ethCurrency) }
    var toCurrency by remember { mutableStateOf(inrCurrency) }
    var fromAmount by remember { mutableStateOf("2.640") }
    val rate = 176138.80
    val spread = 0.002
    val gasFee = 422.73
    val fromAmountDouble = fromAmount.toDoubleOrNull() ?: 0.0
    val toAmount = fromAmountDouble * rate
    val finalReceivableAmount = (toAmount * (1 - spread)) - gasFee
    val onSwap = {
        val tempCurrency = fromCurrency
        fromCurrency = toCurrency
        toCurrency = tempCurrency
        fromAmount = ""
    }
    val inrFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF121212),
            Color(0xFF121212),
                    Color(0xFF111768).copy(alpha = 0.3f),
        ),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp,vertical = 16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp, vertical = 30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.ArrowBackIos, contentDescription = null, tint = Color.White)
                        Spacer(Modifier.width(40.dp))
                        Text("Exchange", fontSize = 20.sp,color = Color.White)
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(vertical = 24.dp)
                    ) {
                        // Layer 1: The two cards in a column
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp) // Creates a small gap
                        ) {

                            CurrencySection(
                                sectionLabel = "Send",
                                currency = fromCurrency,
                                amount = fromAmount,
                                isInput = true,
                                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                                onAmountChange = { fromAmount = it }
                            )

                            CurrencySection(
                                sectionLabel = "Receive",
                                currency = toCurrency,
                                amount = inrFormat.format(toAmount).replace("₹", "₹ "),
                                isInput = false,
                                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                            )
                        }
                        // Layer 2: The swap button, drawn on top of the column
                        SwapButton(onClick = onSwap)
                    }



                    // "Exchange" Action Button
                    Button(
                        onClick = { /* TODO: Handle exchange logic */ },
                        shape = RoundedCornerShape(26.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(66.dp)
                            .padding(bottom = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3366FF))
                    ) {
                        Text("Exchange", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    }

                    // Summary Details
                    SummaryDetails(
                        rate = rate,
                        spread = spread,
                        gasFee = gasFee,
                        receivableAmount = finalReceivableAmount,
                        fromCode = fromCurrency.code,
                        toCode = toCurrency.code,
                        formatter = inrFormat
                    )
                }


            }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun DefaultExchangeScreenPreview() {

        ExchangeScreen()
}
