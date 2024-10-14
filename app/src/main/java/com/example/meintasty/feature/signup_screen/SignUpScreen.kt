package com.example.meintasty.feature.signup_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.meintasty.domain.model.signup_model.SignupRequest
import com.example.meintasty.uicomponent.EmailComponent
import com.example.meintasty.uicomponent.NameSurnameComponent
import com.example.meintasty.uicomponent.PasswordSignUpComponent
import com.example.meintasty.uicomponent.PhoneComponent
import com.example.meintasty.uicomponent.SignUpButtonComponent
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {

    var fullName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val signuState = signUpViewModel.signupState.collectAsState()

    LaunchedEffect(Unit) {
    }

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
                Spacer(modifier = Modifier.height(30.dp))
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
                            name_surname = fullName,
                            onNameSurnameChange = {
                                fullName = it
                            }
                        )
                        EmailComponent(
                            email = email,
                            onMailChange = {
                                email = it
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
                        Spacer(modifier = Modifier.height(12.dp))
                        SignUpButtonComponent(
                            onClick = {
                               if (fullName.isNullOrEmpty().not() && email.isNullOrEmpty().not() && phone.isNullOrEmpty().not() && password.isNullOrEmpty().not()){
                                   val signUpRequest = SignupRequest(email,fullName,phone,password,password)
                                   signUpViewModel.signUp(signUpRequest)
                                   Log.d("signuprequest:","$signUpRequest")
                                   navController.navigate(Screen.CantonScreen.route)
                                   Toast.makeText(context,"Success signup",Toast.LENGTH_SHORT).show()
                               }else{
                                   Toast.makeText(context,"Unssucces signup",Toast.LENGTH_SHORT).show()
                               }

                            },
                            stringResource(id = R.string.sign_up)
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