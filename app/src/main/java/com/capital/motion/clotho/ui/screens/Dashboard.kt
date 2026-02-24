package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.commonComposable.*

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: AstrologyViewModel = viewModel()
) {


    val mediumFont   = FontFamily(Font(R.font.medium))
    val semiBoldFont = FontFamily(Font(R.font.semi_bold))
    val mediumChatFont = FontFamily(Font(R.font.inter_regular))


    // User info — in a real app this comes from your user ViewModel/repository
    val userInfo = UserAstrologyInfo(
        sunSign = "Taurus",
        moonSign = "Scorpio",
        risingSign = "Leo",
        birthDate = "January 15, 1990",
        birthTime = "12:00 AM",
        birthLocation = "London, UK"
    )

    Box(modifier = Modifier.fillMaxSize()
        .padding(vertical = 20.dp)) {

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
                .padding(vertical = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val cardPadding = Modifier.padding(horizontal = 7.dp, vertical = 10.dp)

            // ── Top bar ───────────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left: hamburger + title close together

                Text(
                    text = "DASHBOARD",
                    fontSize = 18.sp,
                    fontFamily = mediumChatFont,
                    color = TextWhite,
                    letterSpacing = 4.sp
                )

                // Right: new chat icon
                Image(
                    painter = painterResource(id = R.drawable.close_icon),
                    contentDescription = "Open AI Chat",
                    modifier = Modifier
                        .size(10.dp)
                        .clickable {
                            navController.navigate("ai_chat")
                        }
                )


            }

            // 🔹 Personal Info Card
            PersonalInfoCard(
                sunSign = userInfo.sunSign,
                moonSign = userInfo.moonSign,
                risingSign = userInfo.risingSign,
                date = userInfo.birthDate,
                time = userInfo.birthTime,
                location = userInfo.birthLocation,
                modifier = Modifier
                    .fillMaxWidth()
                    .then(cardPadding)
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

            // 🔹 Your Pattern Card — opens detail screen on click
            MaxWidthCard(
                title = "Your Pattern",
                subTitle = "Natal Chart Analysis",
                credits = 5,
                info = "Your cosmic blueprint at the moment of your birth, revealing personality and destiny.",
                new = true,
                onClick = {
                    viewModel.selectCard(buildYourPatternData(userInfo))
                    navController.navigate("astrology_detail")
                }
            )

            // 🔹 Monthly Cycle Row — each card opens detail screen on click
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
                        modifier = Modifier.weight(1f),
                        onClick = {
                            viewModel.selectCard(buildMonthlyCycleData(userInfo, cycle))
                            navController.navigate("astrology_detail")
                        }
                    )
                }
            }

            // 🔹 Grey Cards Section
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
    DashboardScreen(navController = rememberNavController())
}