package aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components

import aditya.kumdale.cryptoportfolioapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PortfolioCard4(

    modifier: Modifier = Modifier.padding(11.dp)
) {
    var isBalanceVisible by remember { mutableStateOf(true) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF010103),
                        Color(0xFF2D35B6),
                    ),
                ),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )
            .padding(16.dp)

    ) {
        Column(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
                Icon(painterResource(id = R.drawable.notif), contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Portfolio Value",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 18.sp
                    )
                    Icon(
                        Icons.Default.ArrowForwardIos,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier
                            .size(22.dp)
                            .padding(start = 4.dp)
                    )
                }
                // Toggle Button
                Spacer(modifier = Modifier.width(2.dp))
                Row(

                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { isBalanceVisible = !isBalanceVisible }
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.2f), RoundedCornerShape(60))
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(40.dp)
                            .clip(RoundedCornerShape(50))

                            .background(
                                if (isBalanceVisible) Color.Black.copy(alpha = 0.3f) else Color.Transparent,
                                CircleShape
                            )
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.money) ,
                            contentDescription = "Show Balance",
                            tint = Color.White,
                            modifier = Modifier.size(46.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(40.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (!isBalanceVisible) Color.Black.copy(alpha = 0.3f) else Color.Transparent,
                                CircleShape
                            )
                            .padding(6.dp)
                    ) {
                        Icon(
                            Icons.Default.CurrencyBitcoin,
                            contentDescription = "Hide Balance",
                            tint = Color.White,
                            modifier = Modifier.size(46.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = if (isBalanceVisible) "₹ 1,57,342.05" else "₿ 0.015",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF11111B)
@Composable
fun PortPrev4() {

    PortfolioCard4(

    )
}

