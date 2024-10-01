package com.example.meintasty.feature.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.meintasty.R


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
                text = "Enter your email",

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
    var isPasswordVisible by remember { mutableStateOf(false) } // Parola görünürlüğü durumu

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
                text = "Enter your password",

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
                    contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password",
                    tint = Color.LightGray,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
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
        Text(text = "Login")

    }
}


@Composable
fun SignUpComponent() {
    Text(
        text = "Sign Up",
        style = TextStyle(
            textDecoration = TextDecoration.Underline
        )
    )
}