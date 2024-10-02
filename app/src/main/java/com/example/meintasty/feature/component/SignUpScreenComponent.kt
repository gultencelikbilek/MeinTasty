package com.example.meintasty.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
            Text(text = stringResource(id = R.string.name_surname))
        },
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = Color.Black
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFFA841E7),
            unfocusedIndicatorColor = Color.LightGray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = Color(0xFFA841E7)
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
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = Color.Black
        ),
        label = {
            Text(
                text = stringResource(id = R.string.email),

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
            focusedIndicatorColor = Color(0xFFA841E7),
            unfocusedIndicatorColor = Color.LightGray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = Color(0xFFA841E7)
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
        label = {
            Text(text = stringResource(id = R.string.phone))
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
            focusedIndicatorColor = Color(0xFFA841E7),
            unfocusedIndicatorColor = Color.LightGray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = Color(0xFFA841E7)
        )
    )
}

@Composable
fun PasswordSignUpComponent(
    password : String,
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

        label = {
            Text(
                text = stringResource(id = R.string.password),

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
            focusedIndicatorColor = Color(0xFFA841E7),
            unfocusedIndicatorColor = Color.LightGray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = Color(0xFFA841E7),
        )
    )
}

@Composable
fun SignUpButtonComponent(onClick: () -> Unit) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFA841E7)
        ),
        onClick = { /*TODO*/ }
    ) {
        Text(text = stringResource(id = R.string.sign_up))
    }
}