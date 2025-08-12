package aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components

import aditya.kumdale.cryptoportfolioapp.data.Asset
import aditya.kumdale.cryptoportfolioapp.data.getMockAssets
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AssetsHorizontalList() {
    val assets = getMockAssets()
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(assets) { asset ->
            AssetCard(asset)
        }
    }
}

@Composable
fun AssetCard(asset: Asset) {
    Card(
        modifier = Modifier.width(202.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A25)
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFF464654))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Box(
                    modifier = Modifier
                        .background(asset.color, CircleShape)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = asset.icon,
                        contentDescription = asset.name,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(28.dp))
                Text(asset.name, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(19.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(asset.value, color = Color.White, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    asset.change,
                    color = if (asset.change.startsWith("+")) Color.Green else Color.Red
                )
            }

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF11111B)
@Composable
fun AssetPreview() {
    AssetsHorizontalList()
}
