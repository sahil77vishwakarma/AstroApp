package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.theme.Black
import com.capital.motion.clotho.ui.theme.ClothoTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.util.*
import kotlin.math.absoluteValue
@Composable
fun SetBirthTime(navController: NavController) {

    var selectedHour by remember { mutableStateOf(12) }
    var selectedMinute by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {


        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            ) {

                Image(
                    painter = painterResource(R.drawable.ic_half_moon),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )


                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 50.dp, start = 20.dp)
                        .clickable {
                            navController.navigateUp()
                        }
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.6f))
                ) {
                    Text(
                        text = "WHAT TIME WERE YOU BORN?",
                        color = Color.White,
                        fontSize = 18.sp,
                        letterSpacing = 2.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 14.dp)
                    )

                }

            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Spacer(modifier = Modifier.height(40.dp))


                // Time picker centered
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {

                    IOSTimePicker24h(
                        onTimeSelected = { hour, minute ->
                            selectedHour = hour
                            selectedMinute = minute
                        }
                    )

                }


                Text(
                    text = "We use your time of birth to calculate your astrological chart",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )


                Spacer(modifier = Modifier.height(20.dp))


                // Continue button
                Button(
                    onClick = {

                        navController.navigate("setBirthPlace")

                        println(String.format("%02d:%02d", selectedHour, selectedMinute))

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {

                    Text(
                        "CONTINUE",
                        color = Color.Black
                    )

                }


                Spacer(modifier = Modifier.height(10.dp))


                // Skip button
                Button(
                    onClick = {
                       // navController.navigate("dashboard")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {

                    Text(
                        "DON’T KNOW? SKIP",
                        color = Color.White
                    )

                }


                Spacer(modifier = Modifier.height(40.dp))

            }

        }

    }
}

@Composable
fun IOSInfiniteWheel(
    items: List<String>,
    initialIndex: Int = 0,
    modifier: Modifier = Modifier,
    itemHeight: Dp = 40.dp,
    visibleItems: Int = 5,
    onItemSelected: (String) -> Unit = {}
) {
    val halfVisible = visibleItems / 2
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2 % items.size) + initialIndex
    )

    val snapBehavior = rememberSnapFlingBehavior(listState, snapPosition = SnapPosition.Center)

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .map { layoutInfo ->
                if (layoutInfo.visibleItemsInfo.isEmpty()) return@map 0
                val viewportCenter = layoutInfo.viewportStartOffset + layoutInfo.viewportSize.height / 2
                val closest = layoutInfo.visibleItemsInfo.minByOrNull { item ->
                    kotlin.math.abs((item.offset + item.size / 2) - viewportCenter)
                }
                closest?.index ?: 0
            }
            .distinctUntilChanged()
            .collectLatest { centerIndex ->
                val modIndex = ((centerIndex % items.size) + items.size) % items.size
                onItemSelected(items[modIndex])
            }
    }

    Box(modifier = modifier.height(itemHeight * visibleItems)) {

        LazyColumn(
            state = listState,
            flingBehavior = snapBehavior,
            contentPadding = PaddingValues(vertical = itemHeight * halfVisible),
            modifier = Modifier.fillMaxSize()
        ) {
            items(Int.MAX_VALUE) { index ->
                val layoutInfo = listState.layoutInfo
                val viewportCenter = layoutInfo.viewportStartOffset + layoutInfo.viewportSize.height / 2

                val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
                val distance = itemInfo?.let {
                    ((it.offset + it.size / 2) - viewportCenter).toFloat()
                } ?: Float.MAX_VALUE

                val normalizedDistance = (distance / with(LocalDensity.current) { itemHeight.toPx() }).absoluteValue

                val alpha = when {
                    normalizedDistance < 0.5f -> 1f
                    normalizedDistance < 1.5f -> 0.7f
                    normalizedDistance < 2.5f -> 0.4f
                    else -> 0.15f
                }

                val scale = 1f + 0.2f * (1f - (normalizedDistance.coerceAtMost(halfVisible.toFloat()) / halfVisible))

                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = items[index % items.size],
                        fontSize = 20.sp,
                        color = Color.White.copy(alpha = alpha),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.graphicsLayer {
                            this.alpha = alpha
                            scaleX = scale
                            scaleY = scale
                        }
                    )
                }
            }
        }

        // Dividers in center
        Column(Modifier.matchParentSize(), verticalArrangement = Arrangement.Center) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White.copy(alpha = 0.45f),
                thickness = 1.dp
            )
            Spacer(Modifier.height(itemHeight - 1.dp))
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White.copy(alpha = 0.45f),
                thickness = 1.dp
            )
        }
    }
}

@Composable
fun IOSTimePicker24h(
    modifier: Modifier = Modifier,
    onTimeSelected: (hour: Int, minute: Int) -> Unit
) {
    val hours = (0..23).map { "%02d".format(it) }
    val minutes = (0..59).map { "%02d".format(it) }

    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }

    Row(
        modifier = modifier.height(220.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IOSInfiniteWheel(
            items = hours,
            modifier = Modifier.weight(1f),
            onItemSelected = {
                selectedHour = it.toInt()
                onTimeSelected(selectedHour, selectedMinute)
            }
        )

        Text(
            ":",
            fontSize = 28.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        IOSInfiniteWheel(
            items = minutes,
            modifier = Modifier.weight(1f),
            onItemSelected = {
                selectedMinute = it.toInt()
                onTimeSelected(selectedHour, selectedMinute)
            }
        )
    }
}


@Preview(showBackground = false)
@Composable
fun BirthPreview() {
    ClothoTheme {
       // SetBirthTime()
    }
}