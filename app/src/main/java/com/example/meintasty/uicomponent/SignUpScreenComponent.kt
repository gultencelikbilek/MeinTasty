package com.example.meintasty.uicomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import java.security.Key

@Composable
fun SignUpImage() {

    Image(painter = painterResource(id = R.drawable.account), contentDescription = "")
}

@Composable
fun NameSurnameComponent(
    name_surname: String,
    onNameSurnameChange: (String) -> Unit
) {
    OutlinedTextField(
        value = name_surname,
        onValueChange = {
            onNameSurnameChange(it)
        },
        shape = RoundedCornerShape(25.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "",
                tint = Color.LightGray,
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = stringResource(id = R.string.name_surname),
                style = TextStyle(
                    color = Color.LightGray
                )
            )
        },
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = Color.Black
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = colorResource(id = R.color.mein_tasty_color),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = colorResource(id = R.color.mein_tasty_color)
        )

    )
}

@Composable
fun EmailComponent(
    email: String,
    onMailChange: (String) -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = { newEmail ->
            onMailChange(newEmail)
        },
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = Color.Black
        ),
        label = {
            Text(
                text = stringResource(id = R.string.email),
                style = TextStyle(
                    color = Color.LightGray
                )
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.gmail),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = Color.LightGray
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = colorResource(id = R.color.mein_tasty_color),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = colorResource(id = R.color.mein_tasty_color)
        )
    )
}

@Composable
fun PhoneComponent(phone: String, onPhoneChange: (String) -> Unit) {

    OutlinedTextField(
        value = phone,
        onValueChange = {
            onPhoneChange(it)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "",
                tint = Color.LightGray,
                modifier = Modifier.size(24.dp)
            )
        },
        shape = RoundedCornerShape(25.dp),
        label = {
            Text(
                text = stringResource(id = R.string.phone),
                style = TextStyle(
                    color = Color.LightGray
                )
            )
        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = Color.Black
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = colorResource(id = R.color.mein_tasty_color),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = colorResource(id = R.color.mein_tasty_color)
        )
    )
}

@Composable
fun PasswordSignUpComponent(
    password: String,
    onPaswordChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = password,
        onValueChange = { password ->
            onPaswordChange(password)
        },
        modifier = Modifier.fillMaxWidth(),

        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        ),
        shape = RoundedCornerShape(25.dp),
        label = {
            Text(
                text = stringResource(id = R.string.password),
                style = TextStyle(
                    color = Color.LightGray
                )
            )
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.lock),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = Color.LightGray
            )
        },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painter = painterResource(id = if (isPasswordVisible) R.drawable.show else R.drawable.hide),
                    contentDescription = if (isPasswordVisible) stringResource(id = R.string.hide) else stringResource(
                        id = R.string.show
                    ),
                    tint = Color.LightGray,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = colorResource(id = R.color.mein_tasty_color),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = colorResource(id = R.color.mein_tasty_color),
        )
    )
}
