package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.commonComposable.buttons.RectangleButton
import com.capital.motion.clotho.ui.commonComposable.editTexts.CommonEditText
import com.capital.motion.clotho.ui.theme.Black
import com.capital.motion.clotho.ui.theme.greyTextBg
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.capital.motion.clotho.ui.theme.rectangleBtnBorderColor

@Composable
fun SetBirthPlace(navController: NavController) {

    val cities = listOf(
        "Tokyo, Japan",
        "London, UK",
        "Paris, France",
        "Berlin, Germany",
        "New York, USA",
        "Sydney, Australia",
        "Tel Aviv, Israel"
    )

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
                        text = "WHERE WERE YOU BORN?",
                        color = Color.White,
                        fontSize = 18.sp,
                        letterSpacing = 2.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 16.dp)
                    )
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))


                CommonEditText(
                    value = "",
                    onValueChange = {},
                    hint = "Search for your city...",
                    showEye = false
                )


                Spacer(modifier = Modifier.height(20.dp))


                Text(
                    text = "POPULAR CITIES",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                )


                Spacer(modifier = Modifier.height(12.dp))


                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(cities) {

                        CityItem(it)

                    }

                }


                Spacer(modifier = Modifier.height(10.dp))


                RectangleButton(
                    text = "FINISH",
                    onClick = {
                        navController.navigate("signInScreen")
                    },
                    borderColor = rectangleBtnBorderColor
                )


                Spacer(modifier = Modifier.height(60.dp))

            }

        }

    }

}

@Composable
fun CityItem(city: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFF111111),
                RoundedCornerShape(12.dp)
            )
            .clickable { }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_location),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = city,
            color = Color.White,
            fontSize = 14.sp
        )

    }

}


@Preview(showBackground = true)
@Composable
fun SetBirthPreview() {
    SetBirthPlace(rememberNavController())
  //  CityItem("tokyo")
    //SetBirthPlace()
}