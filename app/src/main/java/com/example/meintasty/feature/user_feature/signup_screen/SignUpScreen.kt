package com.example.meintasty.feature.user_feature.signup_screen

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.user_model_.signup_model.signup_request.SignupRequest
import com.example.meintasty.uicomponent.SignUpButtonComponent
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.CustomSignUpTextFieldComponent

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
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

    val sharedPrefrences =
        context.getSharedPreferences(Constants.SHARED_TOKEN, Context.MODE_PRIVATE)

    LaunchedEffect(signuState.value.data) {
        if (signuState.value != null) {
            signuState.value.data?.let {
                val editor = sharedPrefrences.edit()
                editor.putString(Constants.SHARED_TOKEN, it.token)
                editor.apply()
                navController.navigate(Screen.CantonScreen.route)
                Toast.makeText(
                    context,
                    "Success signup",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                context,
                "Unssucces signup",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },

        content = { paddingValues ->
            if (signuState.value.isLoading == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.mein_tasty_color)
                    )
                }
            } else {
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
                                .size(dimensionResource(id = R.dimen.image_size))
                                .align(Alignment.Center)
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding)),
                        contentAlignment = Alignment.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.signup_screen_card_height)),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            CustomSignUpTextFieldComponent(
                                value = fullName,
                                onValueChange = { fullName = it },
                                labelText = stringResource(id = R.string.name_surname),
                                leadingIconRes = R.drawable.user
                            )

                            CustomSignUpTextFieldComponent(
                                value = email,
                                onValueChange = { email = it },
                                labelText = stringResource(id = R.string.email),
                                leadingIconRes = R.drawable.gmail,
                                keyboardType = KeyboardType.Email
                            )
                            CustomSignUpTextFieldComponent(
                                value = phone,
                                onValueChange = { if (it.length <= 11) phone = it },
                                labelText = stringResource(id = R.string.phone),
                                leadingIconRes = R.drawable.phone,
                                keyboardType = KeyboardType.Phone
                            )
                            CustomSignUpTextFieldComponent(
                                value = password,
                                onValueChange = { password = it },
                                labelText = stringResource(id = R.string.password),
                                leadingIconRes = R.drawable.lock,
                                isPasswordField = true,
                                imeAction = ImeAction.Done
                            )

                            Spacer(modifier = Modifier.height(12.dp))
                            SignUpButtonComponent(
                                onClick = {
                                    if (fullName.isEmpty().not() && email.isEmpty()
                                            .not() && phone.isEmpty()
                                            .not() && password.isEmpty().not()
                                    ) {
                                        val signUpRequest = SignupRequest(
                                            email = email,
                                            fullName = fullName,
                                            password = password,
                                            phoneNumber = phone,
                                            rePassword = password
                                        )
                                        signUpViewModel.signUp(signUpRequest)
                                        Log.d("signuprequest:", "${signUpRequest}")
                                        Log.d("signuprequest:", "${signuState.value}")
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Unssucces signup",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                stringResource(id = R.string.sign_up)
                            )
                        }
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