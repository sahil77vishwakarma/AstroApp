package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.capital.motion.clotho.ui.commonComposable.MaxWidthCard
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import com.capital.motion.clotho.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capital.motion.clotho.ui.commonComposable.MaxWidthGreyCard
import com.capital.motion.clotho.ui.commonComposable.MinWidthCard

@Composable
fun DashboardScreen(){

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.moon_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            MaxWidthCard(
                "Your Pattern",
                "Natal Chart Analysis",
                5,
                "Your cosmic blueprint at the moment of your birth, revealing personality and destiny.",
                true
            )


            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)){
                MinWidthCard(
                    "Monthly Cycle",
                    "Lunar Return Forecast",
                    5,
                    "Your emotional landscape forecast.",
                    true,
                    "December (12/12)",
                    modifier = Modifier.weight(1f)
                )
                MinWidthCard(
                    "Monthly Cycle",
                    "Lunar Return Forecast",
                    5,
                    "Your emotional landscape forecast.",
                    true,
                    "December (12/12)",
                    modifier = Modifier.weight(1f)
                )

            }

            MaxWidthGreyCard()

            Spacer(modifier = Modifier.height(10.dp))

            MaxWidthGreyCard()

        }
    }

}



@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DashboardScreen()
}