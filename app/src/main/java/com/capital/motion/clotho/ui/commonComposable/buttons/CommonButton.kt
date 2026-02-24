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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.theme.editTextBorder
import com.capital.motion.clotho.ui.theme.editTextContainer

@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {

    Button(
        onClick = onClick,

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

        enabled = enabled,

        shape = RoundedCornerShape(30.dp),

        border = BorderStroke(
            width = 1.dp,
            color = editTextBorder
        ),

        colors = ButtonDefaults.buttonColors(
            containerColor = editTextContainer,
            contentColor = Color.White,
            disabledContainerColor = editTextContainer,
            disabledContentColor = Color.Gray
        )
    ) {

        Text(
            text = text,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.inter_medium)),
            textAlign = TextAlign.Center,
            color = Color.White
        )

    }
}

