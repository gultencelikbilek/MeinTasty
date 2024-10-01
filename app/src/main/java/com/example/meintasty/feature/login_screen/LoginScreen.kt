package com.example.meintasty.feature.login_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.feature.component.EmailLoginComponent
import com.example.meintasty.feature.component.LoginButtonComponent
import com.example.meintasty.feature.component.PasswordLoginComponent
import com.example.meintasty.feature.component.ScreenImage
import com.example.meintasty.feature.component.SignUpComponent
@Composable
fun LoginScreen() {
    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                ScreenImage()
            }
            Spacer(modifier = Modifier.height(200.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(top = 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                       // elevation = CardDefaults.cardElevation(2.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = White
                        )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            EmailLoginComponent(
                                emailText = emailText,
                                onEmailChange = { newEmail ->
                                    emailText = newEmail
                                }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            PasswordLoginComponent(
                                passwordText = passwordText,
                                onPasswordChange = { password ->
                                    passwordText = password
                                }
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            LoginButtonComponent(onLogin = {})
                            SignUpComponent()
                        }
                    }
                }

        }
    )
}


@Preview
@Composable
fun LoginScreenPrew(modifier: Modifier = Modifier) {
    LoginScreen()
}