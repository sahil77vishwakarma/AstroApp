package com.capital.motion.clotho.ui.commonComposable.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.R

@Composable
fun RectangleButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    borderColor: Color = Color.Transparent
){

    Button(
        onClick = onClick,

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

        shape = RectangleShape,

        border = BorderStroke(1.dp, borderColor),

        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {

        Text(
            text = text,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.inter_medium))
        )
    }

}