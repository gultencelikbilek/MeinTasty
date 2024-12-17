package com.example.meintasty.uicomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.meintasty.R


@Composable
fun CustomSignUpTextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    leadingIconRes: Int,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isPasswordField: Boolean = false
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val visualTransformation =
        if (isPasswordField && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(25.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        label = {
            Text(
                text = labelText,
                style = TextStyle(color = Color.LightGray)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIconRes),
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(24.dp)
            )
        },
        trailingIcon = {
            if (isPasswordField) {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = painterResource(id = if (isPasswordVisible) R.drawable.show else R.drawable.hide),
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = Color.Black
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = colorResource(id = R.color.mein_tasty_color),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = colorResource(id = R.color.mein_tasty_color)
        )
    )
}