package com.capital.motion.clotho.ui.commonComposable.texts

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.R

@Composable
fun CommonText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray,
    fontSize: TextUnit = 14.sp,
    fontFamily: FontFamily = FontFamily(Font(R.font.inter_medium)),
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: (() -> Unit)? = null
) {


    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        modifier = modifier.clickable(
            enabled = onClick != null
        ) {
            onClick?.invoke()
        }
    )
}