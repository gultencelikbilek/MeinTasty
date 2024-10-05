package com.example.meintasty.feature.signup_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.feature.component.BackIcon
import com.example.meintasty.feature.component.EmailComponent
import com.example.meintasty.feature.component.NameSurnameComponent
import com.example.meintasty.feature.component.PasswordSignUpComponent
import com.example.meintasty.feature.component.PhoneComponent
import com.example.meintasty.feature.component.ScreenImage
import com.example.meintasty.feature.component.SignUpButtonComponent
import com.example.meintasty.feature.component.SignUpImage
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController
) {

    var nameSurname by remember {
        mutableStateOf("")
    }

    var mail by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        BackIcon(
                            onClick = {
                                navController.navigate(Screen.LoginScreen.route)
                            }
                        )
                    }
                }
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
                Spacer(modifier = Modifier.height(30.dp))
                SignUpImage()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        NameSurnameComponent(
                            name_surname = nameSurname,
                            onNameSurnameChange = {
                                nameSurname = it
                            }
                        )
                        EmailComponent(
                            email = mail,
                            onMailChange = {
                                mail = it
                            }
                        )
                        PhoneComponent(
                            phone = phone,
                            onPhoneChange = {
                                phone = it
                            }
                        )
                        PasswordSignUpComponent(
                            password = password,
                            onPaswordChange = {
                                password = it
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        SignUpButtonComponent(
                            onClick = {},
                            ""
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun SignUpPrew(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    SignUpScreen(navController)
}