package com.capital.motion.clotho.ui.commonComposable.editTexts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.capital.motion.clotho.ui.theme.borderColor
import com.capital.motion.clotho.ui.theme.containerColor
import com.capital.motion.clotho.ui.theme.editTextColor

@Composable
fun CommonEditText(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    showEye: Boolean = false,
    isPassword: Boolean = false
) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                color = editTextColor
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),

        shape = RoundedCornerShape(30.dp),

        singleLine = true,

        visualTransformation =
            if (isPassword && !passwordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,

        trailingIcon = {

            if (showEye) {

                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {

                    Icon(
                        imageVector =
                            if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,

                        contentDescription = null,
                        tint = editTextColor
                    )
                }
            }
        },

        colors = OutlinedTextFieldDefaults.colors(

            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,

            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,

            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        )
    )
}