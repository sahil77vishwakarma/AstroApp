package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.theme.Black
import com.capital.motion.clotho.ui.theme.greyTextBg

@Composable
fun SetBirthPlace(navController: NavController) {


    Box(
        Modifier.fillMaxSize()
            .background(color = Black)
            .padding(vertical = 30.dp)
    ) {

        Image(
            painter = painterResource(R.drawable.moon_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(R.drawable.back_arrow),
            contentDescription = null,
            modifier = Modifier
                .clickable{
                    navController.navigateUp()
                }
                .padding(vertical = 30.dp, horizontal = 20.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Spacer(modifier = Modifier.height(90.dp))

            Text(
                "WHERE WERE YOU BORN?",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Black)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                letterSpacing = 2.sp
            )


        }
    }
}


@Preview(showBackground = true)
@Composable
fun SetBirthPreview() {
    //SetBirthPlace()
}