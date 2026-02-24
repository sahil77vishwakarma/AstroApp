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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.capital.motion.clotho.ui.commonComposable.buttons.CommonButton
import com.capital.motion.clotho.ui.commonComposable.editTexts.CommonEditText
import com.capital.motion.clotho.ui.commonComposable.GalaxyStarsLayer
import com.capital.motion.clotho.ui.commonComposable.buttons.CommonSocialButton
import com.capital.motion.clotho.ui.commonComposable.texts.CommonText
import com.capital.motion.clotho.ui.theme.Black
import com.capital.motion.clotho.ui.theme.editTextColor

@Composable
fun SignInScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF06060F)),
       // contentAlignment = Alignment.Center
    ){

        GalaxyStarsLayer()

        Image(
            painter = painterResource(R.drawable.back_arrow),
            contentDescription = null,
            modifier = Modifier
                .clickable{
                    navController.navigateUp()
                }
                .padding(vertical = 50.dp, horizontal = 20.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CommonText(
                text = "Sign In",
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                onClick = { }
            )

            Spacer(modifier = Modifier.height(10.dp))


            CommonText(
                text = "Welcome back! Please sign in to your account.",
                fontSize = 15.sp,
                color = editTextColor,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                onClick = { }
            )

            Spacer(modifier = Modifier.height(40.dp))


            CommonEditText(
                value = email,
                onValueChange = { email = it },
                hint = "Email address",
                showEye = false,
                isPassword = false
            )

            Spacer(modifier = Modifier.height(10.dp))


            CommonEditText(
                value = password,
                onValueChange = { password = it },
                hint = "Password",
                showEye = true,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(20.dp))


            CommonButton("Sign In",{
                navController.navigate("ai_chat")

            },true)

            Spacer(modifier = Modifier.height(30.dp))

            CommonText(
                text = "Forgot password?",
                fontSize = 15.sp,
                color = editTextColor,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                onClick = { }
            )

            Spacer(modifier = Modifier.height(30.dp))

            CommonText(
                text = "Don't have an account? Create an account",
                fontSize = 15.sp,
                color = editTextColor,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                onClick = null
            )

            Spacer(modifier = Modifier.height(20.dp))


            CommonSocialButton("Continue with Apple",R.drawable.ic_apple,{ navController.navigate("ai_chat") })

            Spacer(modifier = Modifier.height(10.dp))

            CommonSocialButton("Continue with Google",R.drawable.ic_google,{ navController.navigate("ai_chat")})

            Spacer(modifier = Modifier.height(10.dp))

            CommonSocialButton("Continue with Facebook",R.drawable.ic_facebook,{navController.navigate("ai_chat") })


        }

    }


}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    SignInScreen(rememberNavController())
}