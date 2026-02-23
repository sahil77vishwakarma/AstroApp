package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.commonComposable.MaxWidthGreyCard
import com.capital.motion.clotho.ui.theme.ClothoTheme

@Composable
fun SetBirthdate(){

        val days = (1..31).map { it.toString() }
        val months = listOf(
            "January","February","March","April","May","June",
            "July","August","September","October","November","December"
        )
        val years = (1980..2000).map { it.toString() }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            /// Background Image
            Image(
                painter = painterResource(id = R.drawable.moon_bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            /// Dark overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.65f))
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(90.dp))

                Text(
                    text = "WHAT’S YOUR BIRTH DATE ?",
                    color = Color.White,
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )


                Spacer(modifier = Modifier.height(40.dp))


                /// Picker
                Row(
                    modifier = Modifier.height(200.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    WheelPicker(days)

                    WheelPicker(months, isCenter = true)

                    WheelPicker(years)
                }


                Spacer(modifier = Modifier.weight(1f))



                Text(
                    text = "We use your date of birth to calculate your astrological chart",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )


                Spacer(modifier = Modifier.height(20.dp))


                /// Continue Button
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RectangleShape
                ) {

                    Text(
                        "CONTINUE",
                        color = Color.Black,
                        letterSpacing = 2.sp
                    )

                }

                Spacer(modifier = Modifier.height(40.dp))
            }


            /// Center Highlight Line
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White.copy(alpha = 0.5f))
            )
        }
    }

@Composable
fun WheelPicker(
    items: List<String>,
    isCenter: Boolean = false
) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(if (isCenter) 140.dp else 80.dp)
    ) {

        items(items.size) {

            Text(
                text = items[it],
                color = Color.White,
                fontSize = if (isCenter) 22.sp else 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

    }
}
@Preview(showBackground = false)
@Composable
fun WheelPreview() {
    ClothoTheme {

        SetBirthdate()
    }
}