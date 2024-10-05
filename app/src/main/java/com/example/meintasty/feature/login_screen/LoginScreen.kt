package com.example.meintasty.feature.login_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.feature.component.EmailLoginComponent
import com.example.meintasty.feature.component.ForgotPasswordComponent
import com.example.meintasty.feature.component.PasswordLoginComponent
import com.example.meintasty.feature.component.ScreenImage
import com.example.meintasty.feature.component.SignUpComponent
import com.example.meintasty.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }

    val tokenState by loginViewModel.token.observeAsState()
    Log.v("Logg:Token:", tokenState.toString())

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)

    // Token null olmadığında SharedPreferences içine kaydediyoruz
    tokenState?.let {
        with(sharedPreferences.edit()) {
            putString("auth_token", tokenState)
            apply()
        }
    }

    LaunchedEffect(tokenState) {
        Log.d("Logg:LoginScreen", "token")
        val token = loginViewModel.token.value
        Log.d("Logg:LoginScreen", token.toString())
        when (token) {
            "Test" -> {
                Log.d("Logg:LoginScreen", "trueee")
                navController.navigate(Screen.NewScreen.route)
            }
            else -> {
                Log.d("Logg:LoginScreen", "Error occurred")
            }
        }
    }




    Scaffold(
        topBar = {
                 TopAppBar(title = { /*TODO*/ })
        },
        content = { paddingValues ->
            Log.v("Logg:loginNavLoginScreen","")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                ScreenImage()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
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
                            PasswordLoginComponent(
                                passwordText = passwordText,
                                onPasswordChange = { password ->
                                    passwordText = password
                                }
                            )
                            Row(
                                horizontalArrangement = Arrangement.End
                            ) {
                                Spacer(modifier = Modifier.width(200.dp))
                                ForgotPasswordComponent()
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 80.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFA841E7)
                                ),
                                onClick = {
                                    loginViewModel.loginUser(emailText,passwordText)
                                    // Start collecting when the composable is visible
                                }
                            ) {
                                Text(text = stringResource(id = R.string.login))
                            }

                           SignUpComponent(
                              navController
                           )
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPrew(
) {
    val navController = rememberNavController()
    LoginScreen(navController)
}