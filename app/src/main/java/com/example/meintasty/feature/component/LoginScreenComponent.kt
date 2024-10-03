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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
    Image(
        painter = painterResource(id = R.drawable.account),
        contentDescription = ""
    )
}

@Composable
fun EmailLoginComponent(emailText: String, onEmailChange: (String) -> Unit) {

    OutlinedTextField(
        value = emailText,
        onValueChange = { newEmail ->
            onEmailChange(newEmail)
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
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Text,
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
fun PasswordLoginComponent(passwordText: String, onPasswordChange: (String) -> Unit) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = passwordText,
        onValueChange = { password ->
            onPasswordChange(password)
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
            onDone ={
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
fun LoginButtonComponent(onLogin: () -> Unit) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFA841E7)
        ),
        onClick = { /*TODO*/ }
    ) {
        Text(text = stringResource(id = R.string.login))
    }
}


@Composable
fun SignUpComponent(navController: NavController) {
    TextButton(
        onClick = {
            navController.navigate(Screen.SignUpScreen.route)
    }) {

        Text(
            text = stringResource(id = R.string.sign_up),
            style = TextStyle(
                textDecoration = TextDecoration.Underline
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