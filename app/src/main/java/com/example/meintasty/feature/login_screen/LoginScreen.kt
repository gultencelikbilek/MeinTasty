package com.example.meintasty.feature.login_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.login_model.LoginUserRequest
import com.example.meintasty.feature.component.EmailLoginComponent
import com.example.meintasty.feature.component.ForgotPasswordComponent
import com.example.meintasty.feature.component.LoginButtonComponent
import com.example.meintasty.feature.component.PasswordLoginComponent
import com.example.meintasty.feature.component.ScreenImage
import com.example.meintasty.feature.component.SignUpComponent
import com.example.meintasty.feature.component.SkipComponent
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
    val context = LocalContext.current
    val loginState =  loginViewModel.loginState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffdc3545)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
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
                            Spacer(modifier = Modifier.height(16.dp))
                            Spacer(modifier = Modifier.width(200.dp))
                            ForgotPasswordComponent()
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        LoginButtonComponent(
                                onLogin = {
                                    if (passwordText.isNotEmpty() && emailText.isNotEmpty()) {
                                        val request = LoginUserRequest(emailText, passwordText)
                                        loginViewModel.loginFlowUser(request)
                                        navController.navigate(Screen.CantonScreen.route)
                                        Log.d("LoginScreen", "loginFlowUser called")
                                    } else {
                                        Toast.makeText(context, "Missing information", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                )

                        SignUpComponent(
                            navController
                        )
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
    LoginScreen(navController = navController)
}