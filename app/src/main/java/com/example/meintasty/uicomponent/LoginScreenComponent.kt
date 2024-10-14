package com.example.meintasty.uicomponent


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.navigation.Screen

@Composable
fun ScreenImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(colorResource(id = R.color.mein_tasty_color))
    ) {
        Image(
            painter = painterResource(id = R.drawable.meintast_logo),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun EmailLoginComponent(emailText: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        value = emailText,
        onValueChange = { newEmail ->
            onEmailChange(newEmail)
        },
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),

        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = colorResource(id = R.color.mein_tasty_color),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}

@Composable
fun PasswordLoginComponent(passwordText: String, onPasswordChange: (String) -> Unit) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = passwordText,
        onValueChange = { password ->
            onPasswordChange(password)
        },
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        ),

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
            //focusedLabelColor =  colorResource(id = R.color.mein_tasty_color)
        )
    )
}

@Composable
fun LoginButtonComponent(onLogin: () -> Unit) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.mein_tasty_color)
        ),
        onClick = {
            onLogin()
        }
    ) {
        Text(text = stringResource(id = R.string.login))
    }
}


@Composable
fun SignUpComponent(navController: NavController) {
    TextButton(
        onClick = {
            navController.navigate(Screen.SignUpScreen.route)
        },
    ) {

        Text(
            text = stringResource(id = R.string.sign_up),
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                color = colorResource(id = R.color.mein_tasty_color)
            )
        )
    }

}

@Composable
fun ForgotPasswordComponent() {
    Text(
        text = stringResource(id = R.string.forgot_password),
        style = TextStyle(
            textDecoration = TextDecoration.Underline
        ),
        textAlign = TextAlign.End
    )

}