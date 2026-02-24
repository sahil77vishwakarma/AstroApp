package com.capital.motion.clotho.ui.commonComposable.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.R
import androidx.compose.ui.graphics.Color

@Composable
fun CommonSocialButton(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    borderColor: Color = Color.Transparent
) {

    Button(
        onClick = onClick,

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

        shape = RoundedCornerShape(30.dp),

        border = BorderStroke(1.dp, borderColor),

        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified, // keeps original icon color
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = text,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.inter_medium))
            )
        }
    }
}