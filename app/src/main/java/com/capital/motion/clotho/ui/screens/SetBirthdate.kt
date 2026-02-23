package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.commonComposable.MaxWidthGreyCard
import com.capital.motion.clotho.ui.theme.ClothoTheme
import kotlin.math.absoluteValue

@Composable
fun SetBirthdate(navController: NavController) {

    val days = (1..31).map { it.toString() }
    val months = listOf(
        "January","February","March","April","May","June",
        "July","August","September","October","November","December"
    )
    val years = (1980..2000).map { it.toString() }
    val itemHeight = 54.dp
    val visibleItems = 5

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.moon_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.65f))
        )

        Image(
            painter = painterResource(R.drawable.back_arrow),
            contentDescription = null,
            modifier = Modifier.padding(vertical = 30.dp, horizontal = 20.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            Spacer(modifier = Modifier.height(90.dp))

            Text(
                "WHAT’S YOUR BIRTH DATE ?",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                letterSpacing = 2.sp
            )


            Spacer(modifier = Modifier.height(40.dp))



            Row(
                modifier = Modifier.height(itemHeight * visibleItems),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IOSWheelTimePicker(days,   Modifier.weight(1f),onItemSelected = { selectedDay ->
                    println(selectedDay)
                })
                IOSWheelTimePicker(months, Modifier.weight(1f),onItemSelected = { selectedDay ->
                    println(selectedDay)
                })
                IOSWheelTimePicker(years,  Modifier.weight(1f),onItemSelected = { selectedDay ->
                    println(selectedDay)
                })
            }

            Spacer(modifier = Modifier.weight(1f))


            Text(
                "We use your date of birth to calculate your astrological chart",
                color = Color.White.copy(.7f),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 30.dp)
            )


            Spacer(modifier = Modifier.height(20.dp))


            Button(
                onClick = {
                    navController.navigate("setBirthTime")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {

                Text("CONTINUE",
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = Color.Black)
            }


            Spacer(modifier = Modifier.height(40.dp))

        }

    }
}

@Composable
fun IOSWheelTimePicker(
    items: List<String>,
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit
) {

    val itemHeight = 54.dp
    val visibleItems = 5
    val halfVisible = visibleItems / 2

    val listState = rememberLazyListState()

    val flingBehavior = rememberSnapFlingBehavior(
        snapLayoutInfoProvider =
            androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider(
                lazyListState = listState,
                snapPosition =
                    androidx.compose.foundation.gestures.snapping.SnapPosition.Center
            )
    )

    val centerIndex by remember {
        derivedStateOf {

            val layoutInfo = listState.layoutInfo

            val viewportCenter =
                layoutInfo.viewportStartOffset +
                        (layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset) / 2

            layoutInfo.visibleItemsInfo.minByOrNull {
                kotlin.math.abs(
                    (it.offset + it.size / 2) - viewportCenter
                )
            }?.index ?: 0
        }
    }

    /* ---- Notify selected item ---- */

    LaunchedEffect(centerIndex) {
        if (centerIndex in items.indices) {
            onItemSelected(items[centerIndex])
        }
    }

    Box(
        modifier = modifier.height(itemHeight * visibleItems)
    ) {

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                vertical = itemHeight * halfVisible
            ),
            flingBehavior = flingBehavior
        ) {

            itemsIndexed(items) { index, item ->

                val distance = (index - centerIndex).absoluteValue

                val alpha =
                    when (distance) {
                        0 -> 1f
                        1 -> 0.5f
                        else -> 0.2f
                    }

                val scale =
                    if (distance == 0) 1.2f else 1f

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = item,
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.inter_semibold)),
                        color = Color.White.copy(alpha),
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


        Column(
            modifier = Modifier.matchParentSize()
        ) {

            Spacer(Modifier.weight(1f))

            Divider(
                color = Color.White.copy(.6f),
                thickness = 1.dp
            )

            Spacer(Modifier.height(itemHeight))

            Divider(
                color = Color.White.copy(.6f),
                thickness = 1.dp
            )

            Spacer(Modifier.weight(1f))
        }
    }
}
@Preview(showBackground = false)
@Composable
fun WheelPreview() {
    ClothoTheme {

     //   SetBirthdate()
    }
}