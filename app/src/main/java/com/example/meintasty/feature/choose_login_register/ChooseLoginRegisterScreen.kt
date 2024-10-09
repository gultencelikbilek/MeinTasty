package com.example.meintasty.feature.choose_login_register

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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.feature.component.DividerComponent
import com.example.meintasty.feature.component.SignInButtonComponent
import com.example.meintasty.feature.component.SignUpButtonComponent
import com.example.meintasty.feature.component.SkipComponent
import com.example.meintasty.feature.signup_screen.SignUpScreen
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseLoginRegisterScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    SkipComponent(onClick = {
                        navController.navigate(Screen.CantonScreen.route)
                    })
                },
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
                        .height(350.dp)
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
                Spacer(modifier = Modifier.height(26.dp))
                SignUpButtonComponent(onClick = {
                    navController.navigate(Screen.SignUpScreen.route)
                }, text = stringResource(id = R.string.sign_up))
                DividerComponent()
                SignInButtonComponent(onClick = {
                    navController.navigate(Screen.LoginScreen.route)
                }, text = stringResource(id = R.string.sign_in))

            }
        }
    )
}

@Preview
@Composable
fun ChooseLoginRegister() {
    val navController = rememberNavController()
    ChooseLoginRegisterScreen(navController)
}