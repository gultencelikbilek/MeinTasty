package com.example.meintasty.feature.common_screen_feature.choose_login_register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.uicomponent.DividerComponent
import com.example.meintasty.uicomponent.SignInButtonComponent
import com.example.meintasty.uicomponent.SignUpButtonComponent
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.ForgotPasswordComponent

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
                    Text(
                        modifier = Modifier
                            .padding(end = dimensionResource(id = R.dimen.horizontal_padding))
                            .clickable {
                                navController.navigate(Screen.CantonScreen.route)
                            },
                        text = stringResource(id = R.string.skip),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                       // .padding(top = 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.choose_screen_box_size))
                            .background(colorResource(id = R.color.mein_tasty_color))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.meintast_logo),
                            contentDescription = "",
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.image_size))
                                .align(Alignment.Center)
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.choose_screen_spacer_height)))
                    SignUpButtonComponent(
                        onClick = {
                            navController.navigate(Screen.SignUpScreen.route)
                        },
                        text = stringResource(id = R.string.sign_up)
                    )
                    DividerComponent()
                    SignInButtonComponent(onClick = {
                        navController.navigate(Screen.LoginScreen.route)
                    }, text = stringResource(id = R.string.sign_in))
                }
                ForgotPasswordComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.BottomCenter),
                    stringResource(id = R.string.restaurant_login),
                    navController
                )
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