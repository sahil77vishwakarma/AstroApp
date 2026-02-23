package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.commonComposable.*

@Composable
fun DashboardScreen() {

    Box(modifier = Modifier.fillMaxSize()) {

        // 🔹 Background Image
        Image(
            painter = painterResource(id = R.drawable.moon_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 🔹 Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val cardPadding = Modifier.padding(horizontal = 7.dp, vertical = 10.dp)

            // 🔹 Personal Info Card
            PersonalInfoCard(
                sunSign = "Taurus",
                moonSign = "Scorpio",
                risingSign = "Leo",
                date = "January 15, 1990",
                time = "12:00 AM",
                location = "London, UK",
                modifier = Modifier.fillMaxWidth().then(cardPadding)
            )

            // 🔹 Clotho Info Card
            ClothoInfoCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp, vertical = 10.dp),
                onInfoClick = {
                    // TODO: show info dialog / bottom sheet
                }
            )


            // 🔹 Your Pattern Card
            MaxWidthCard(
                title = "Your Pattern",
                subTitle = "Natal Chart Analysis",
                credits = 5,
                info = "Your cosmic blueprint at the moment of your birth, revealing personality and destiny.",
                new = true
            )

            // 🔹 Monthly Cycle Row
            val monthlyCycles = listOf("December (12/12)", "January (01/01)")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                monthlyCycles.forEach { cycle ->
                    MinWidthCard(
                        title = "Monthly Cycle",
                        subTitle = "Lunar Return Forecast",
                        credits = 5,
                        info = "Your emotional landscape forecast.",
                        date = true,
                        data = cycle,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // 🔹 Grey Cards Section (2 cards)
            repeat(2) {
                MaxWidthGreyCard()
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DashboardScreen()
}