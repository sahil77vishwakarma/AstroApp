package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.commonComposable.GalaxyStarsLayer
import com.capital.motion.clotho.ui.commonComposable.buttons.RectangleButton
import com.capital.motion.clotho.ui.commonComposable.texts.CommonText
import com.capital.motion.clotho.ui.theme.editTextColor
import com.capital.motion.clotho.ui.theme.rectangleBtnBorderColor

@Composable
fun WelcomeScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF06060F)),
    ) {

        GalaxyStarsLayer()


        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.img_full_moon),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                contentScale = ContentScale.Crop
            )

            CommonText(
                text = "C L O T H O",
                fontSize = 34.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.inter_light)),
            )

            Spacer(modifier = Modifier.height(10.dp))

            CommonText(
                text = "Personalized Astrology",
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
            )
            Spacer(modifier = Modifier.height(20.dp))


            CommonText(
                text = "Real astrologers wrote it, AI delivers it.",
                fontSize = 15.sp,
                color = editTextColor,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
            )

            CommonText(
                text = "I ALREADY HAVE AN ACCOUNT",
                fontSize = 15.sp,
                color = editTextColor,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                onClick = { }
            )

            Spacer(modifier = Modifier.height(20.dp))

            RectangleButton(text = "GET STARTED", onClick = {  navController.navigate("setBirthdate") }, borderColor = rectangleBtnBorderColor)

        }
    }
}
@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    WelcomeScreen(rememberNavController())
}