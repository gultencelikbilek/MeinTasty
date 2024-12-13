package com.example.meintasty.feature.restaurant_login_screen

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.login_model.login_request.LoginUserRequest
import com.example.meintasty.domain.model.restaurant_login_model.restaurant_login_request.RestaurantLoginRequest
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.EmailLoginComponent
import com.example.meintasty.uicomponent.LoginButtonComponent
import com.example.meintasty.uicomponent.PasswordLoginComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantLoginScreen(
    navController: NavController,
    restaurantLoginViewModel: RestaurantLoginViewModel = hiltViewModel()
    ) {

    var restaurantEmail by remember {
        mutableStateOf("")
    }

    var restaurantPassword by remember {
        mutableStateOf("")
    }
    val restaurantLoginState = restaurantLoginViewModel.restaurantLoginState.collectAsState().value
    val context = LocalContext.current
    val sharedPrefrences =
        context.getSharedPreferences(Constants.SHARED_TOKEN, Context.MODE_PRIVATE)


    LaunchedEffect(restaurantLoginState.data) {
        Log.d("LaunchedEffect", "Data: ${restaurantLoginState.data}")
        restaurantLoginState.data?.let {
            if (it.token != null) {
                Log.d("loginRestaurant", "$it")
                val editor = sharedPrefrences.edit()
                editor.putString(Constants.SHARED_TOKEN, it.token)
                editor.apply()

                navController.navigate(Screen.CantonScreen.route)
            } else {
                Toast.makeText(context, "Restaurant not found", Toast.LENGTH_SHORT).show()
            }
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
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        EmailLoginComponent(
                            emailText = restaurantEmail,
                            onEmailChange = { newEmail ->
                                restaurantEmail = newEmail
                            }
                        )
                        PasswordLoginComponent(
                            passwordText = restaurantPassword,
                            onPasswordChange = { password ->
                                restaurantPassword = password
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        LoginButtonComponent(
                            onLogin = {
                                if (restaurantPassword.isNotEmpty() && restaurantEmail.isNotEmpty()) {
                                    val request = RestaurantLoginRequest(restaurantEmail, restaurantPassword)
                                    restaurantLoginViewModel.restaurantLogin(request)
                                    Log.d("userinsertrequest","$request")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Missing information",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )

                    }
                }
            }
        }
    )
}