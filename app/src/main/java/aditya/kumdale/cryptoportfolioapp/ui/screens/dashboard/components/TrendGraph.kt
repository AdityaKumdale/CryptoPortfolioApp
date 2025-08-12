package aditya.kumdale.cryptoportfolioapp.ui.screens.dashboard.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.random.Random



/**
 * Represents a single data point in the portfolio graph.
 * @param timestamp The time of this data point (in milliseconds).
 * @param value The portfolio value at this time.
 */
data class PortfolioDataPoint(val timestamp: Long, val value: Float)



@Composable
fun GraphTestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        PortfolioChartCard()
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun PortfolioChartCard() {
    val timeframes = listOf("1h", "8h", "1d", "1w", "1m", "6m", "1y")
    var selectedTimeframe by remember { mutableStateOf("6m") }
    val portfolioData by remember(selectedTimeframe) {
        mutableStateOf(generateMockPortfolioData(selectedTimeframe))
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        TimeframeSelector(
            timeframes = timeframes,
            selectedTimeframe = selectedTimeframe,
            onTimeframeSelected = { selectedTimeframe = it }
        )
        CryptoGraph(
            data = portfolioData,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        )
    }
}


@Composable
fun TimeframeSelector(
    timeframes: List<String>,
    selectedTimeframe: String,
    onTimeframeSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(timeframes) { timeframe ->
            Text(
                text = timeframe,
                color = if (selectedTimeframe == timeframe) Color.White else Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable { onTimeframeSelected(timeframe) }
                    .background(
                        if (selectedTimeframe == timeframe) Color(0xFF2E2E2E) else Color.Transparent,
                        RoundedCornerShape(50)
                    )
                    .clip(RoundedCornerShape(50))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun CryptoGraph(
    data: List<PortfolioDataPoint>,
    modifier: Modifier = Modifier
) {

    var touchX by remember { mutableStateOf<Float?>(null) }
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }

    // Animation for the graph line drawing effect
    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(data) {
        animationProgress.snapTo(0f)
        animationProgress.animateTo(1f, animationSpec = tween(durationMillis = 1000))
    }

    // Text measurer for drawing text on canvas
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current

    // data points for rendering
    val (points, minVal, maxVal) = remember(data, canvasSize) {
        if (data.isEmpty() || canvasSize.width == 0 || canvasSize.height == 0) {
            return@remember Triple(emptyList(), 0f, 0f)
        }

        val minVal = data.minOf { it.value }
        val maxVal = data.maxOf { it.value }
        val valueRange = if (maxVal - minVal > 0) maxVal - minVal else 1f

        val points = data.mapIndexed { index, point ->
            val x = index.toFloat() / (data.size - 1).coerceAtLeast(1) * canvasSize.width
            val y = (1 - (point.value - minVal) / valueRange) * canvasSize.height
            Offset(x, y)
        }
        Triple(points, minVal, maxVal)
    }

    Canvas(
        modifier = modifier
            .onSizeChanged { canvasSize = it }
            .pointerInput(points) {
                detectTapGestures(
                    onPress = { offset ->
                        touchX = offset.x.coerceIn(0f, canvasSize.width.toFloat())
                        tryAwaitRelease()
                        touchX = null
                    }
                )
            }
            .pointerInput(points) {
                detectDragGestures(
                    onDragStart = { offset ->
                        touchX = offset.x.coerceIn(0f, canvasSize.width.toFloat())
                    },
                    onDragEnd = { touchX = null },
                    onDragCancel = { touchX = null }
                ) { change, _ ->
                    touchX = change.position.x.coerceIn(0f, canvasSize.width.toFloat())
                }
            }
    ) {
        // Background Bars
        val barWidth = size.width / (points.size * 2)
        points.forEach { point ->
            drawRect(
                color = Color.Gray.copy(alpha = 0.1f),
                topLeft = Offset(point.x - barWidth / 2, point.y),
                size = androidx.compose.ui.geometry.Size(barWidth, size.height - point.y)
            )
        }

        // main graph path
        val path = Path().apply {
            if (points.isNotEmpty()) {
                moveTo(points.first().x, points.first().y)
                for (i in 0 until points.size - 1) {
                    val p1 = points[i]
                    val p2 = points[i + 1]
                    // Use quadratic Bezier for a smoother curve
                    val controlPoint = Offset((p1.x + p2.x) / 2, p1.y)
                    quadraticBezierTo(controlPoint.x, controlPoint.y, p2.x, p2.y)
                }
            }
        }

        //  drawing animation
        val animatedPath = Path()
        val pathMeasure = PathMeasure()
        pathMeasure.setPath(path, false)
        pathMeasure.getSegment(0f, pathMeasure.length * animationProgress.value, animatedPath, true)

        // fading graph line
        drawPath(
            path = animatedPath,
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xFF23E89E), Color.Black),
                startX = 0f,
                endX = size.width
            ),
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
        )

        //  interactive indicator if the user is touching the graph
        touchX?.let { x ->
            // Find the closest data point to the touch position
            val closestPointIndex = points.indices.minByOrNull { abs(points[it].x - x) } ?: -1
            if (closestPointIndex != -1) {
                val selectedPoint = points[closestPointIndex]
                val selectedData = data[closestPointIndex]

                // vertical line
                drawLine(
                    color = Color.White.copy(alpha = 0.5f),
                    start = Offset(selectedPoint.x, 0f),
                    end = Offset(selectedPoint.x, size.height),
                    strokeWidth = 1.dp.toPx()
                )

                //circle on the line
                drawCircle(
                    color = Color.White,
                    radius = 8.dp.toPx(),
                    center = selectedPoint
                )
                drawCircle(
                    color = Color(0xFF23E89E),
                    radius = 5.dp.toPx(),
                    center = selectedPoint
                )


                val dateText = formatDate(selectedData.timestamp)
                val valueText = formatCurrency(selectedData.value)

                val dateTextLayout = textMeasurer.measure(
                    text = dateText,
                    style = TextStyle(color = Color.Gray, fontSize = 12.sp)
                )
                val valueTextLayout = textMeasurer.measure(
                    text = valueText,
                    style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                )

                val textPadding = 8.dp.toPx()
                val totalTextHeight = dateTextLayout.size.height + valueTextLayout.size.height + textPadding
                val textTopOffset = 16.dp.toPx()

                // Position text to avoid going off-screen
                val textX = (selectedPoint.x - (valueTextLayout.size.width / 2))
                    .coerceIn(0f, size.width - valueTextLayout.size.width)

                drawText(
                    textLayoutResult = valueTextLayout,
                    topLeft = Offset(textX, textTopOffset)
                )
                drawText(
                    textLayoutResult = dateTextLayout,
                    topLeft = Offset(textX, textTopOffset + valueTextLayout.size.height + textPadding / 2)
                )
            }
        }
    }
}


@Preview(showBackground = true,)
@Composable
fun GraphTestScreenPreview() {

    GraphTestScreen()

}



/**
 * Generates random portfolio data for different timeframes.
 */
fun generateMockPortfolioData(timeframe: String): List<PortfolioDataPoint> {
    val random = Random(System.currentTimeMillis())
    val now = System.currentTimeMillis()
    val (points, hours) = when (timeframe) {
        "1h" -> 60 to 1L
        "8h" -> 60 to 8L
        "1d" -> 48 to 24L
        "1w" -> 7 * 24 to 7 * 24L
        "1m" -> 30 * 12 to 30 * 24L
        "6m" -> 180 * 4 to 180 * 24L
        "1y" -> 365 to 365 * 24L
        else -> 30 to 24L
    }

    val timeStep = (hours * 60 * 60 * 1000) / points
    var currentValue = 140000f + random.nextInt(-10000, 10000)

    return (0 until points).map { i ->
        val timestamp = now - (points - 1 - i) * timeStep
        currentValue += random.nextFloat() * 4000f - 2000f
        PortfolioDataPoint(timestamp, currentValue.coerceAtLeast(50000f))
    }
}

/**
 * Formats a timestamp into a date string (e.g., "24 March").
 */
private fun formatDate(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("d MMMM", Locale.getDefault())
    return format.format(date)
}

/**
 * Formats a float value into a currency string (e.g., "₹1,42,340").
 */
private fun formatCurrency(value: Float): String {
    val format = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    format.maximumFractionDigits = 0
    return format.format(value.roundToInt())
}