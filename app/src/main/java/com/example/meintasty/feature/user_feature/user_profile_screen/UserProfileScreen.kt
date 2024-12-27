package com.example.meintasty.feature.user_feature.user_profile_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material.Scaffold
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.uicomponent.EditIconComponent
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.DividierProfile
import com.example.meintasty.uicomponent.HeaderComponent
import com.example.meintasty.uicomponent.ProfileStartIconComponent
import com.example.meintasty.uicomponent.BasicText
import com.example.meintasty.uicomponent.ProfileUserIcon
import com.example.meintasty.uicomponent.LabelUserText
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meintasty.domain.model.user_model_.get_user_model.user_request.UserRequest
import com.example.meintasty.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    profileViewModel: UserProfileViewModel = hiltViewModel()
) {
    val userState = profileViewModel.userState.collectAsState().value
    val userDatabaseState = profileViewModel.userDatabaseState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        profileViewModel.getUserDatabaseModel()
    }

    val userId = userDatabaseState.value.data?.userId
    LaunchedEffect(userId) {
        if (userDatabaseState.value.data != null) {
            userId?.let {
                if (it > 0) {
                    val userRequest = UserRequest(it)
                    Log.d("userrequest:userId3:", "$it")
                    profileViewModel.getUser(userRequest = userRequest)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BackIcon {
                            navController.navigate(Screen.RestaurantScreen.route)
                        }
                        HeaderComponent(text = stringResource(id = R.string.profile))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            userState.data?.value.let { user ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ProfileUserIcon(
                                    onClick = {},
                                    painterResource(id = R.drawable.user)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                LabelUserText(user?.fullName.toString())
                            }

                            DividierProfile()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                ProfileStartIconComponent(
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.gmail)
                                )
                                BasicText(modifier, user?.email.toString())
                                Spacer(modifier = Modifier.weight(1f))

                                EditIconComponent(
                                    onClick = {
                                        if (user?.email == null) {
                                            Toast.makeText(context, "Null", Toast.LENGTH_SHORT)
                                                .show()

                                        } else {
                                            navController.navigate(
                                                Screen.UpdateScreen.route + "?userId=$userId&email=${user?.email}&phone=${user?.phoneNumber}&updateType=email"
                                            )
                                        }
                                    },
                                    painterResource(id = R.drawable.pen),
                                )
                            }
                            DividierProfile()
                            val regex = """(\d)(\d{3})(\d{3})(\d{2})(\d{2})""".toRegex()
                            val number = user?.phoneNumber.toString()
                            val output = regex.replace(number, "$2 $3-$4-$5")
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                ProfileStartIconComponent(
                                    modifier = modifier,
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.phone)
                                )
                                BasicText(modifier, output)
                                Spacer(modifier = Modifier.weight(1f))
                                EditIconComponent(
                                    onClick = {
                                        if (user?.phoneNumber.isNullOrEmpty()) {
                                            Toast.makeText(
                                                context,
                                               R.string.number_null,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            navController.navigate(
                                                route = "update_screen?userId=$userId&email=${user?.email}&phone=${user?.phoneNumber}&updateType=phone"
                                            )
                                        }
                                    },
                                    painterResource(id = R.drawable.pen),
                                    modifier = modifier
                                )
                            }

                            DividierProfile()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ProfileStartIconComponent(
                                    modifier,
                                    onClick = {},
                                    painterResource(id = R.drawable.lock)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                BasicText(modifier, stringResource(id = R.string.password_change))
                                Spacer(modifier = Modifier.weight(1f))
                                EditIconComponent(
                                    onClick = {
                                        if (userId.toString() == null) {
                                            Toast.makeText(context, "Null", Toast.LENGTH_SHORT)
                                                .show()
                                        } else {
                                            navController.navigate(Screen.PasswordScreen.route + "&userId=$userId")
                                        }
                                    },
                                    painterResource(id = R.drawable.pen)
                                )
                            }
                            DividierProfile()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                ProfileStartIconComponent(
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.navigation)
                                )
                                BasicText(modifier, user?.userAdddress?.addressText.toString())
                                Spacer(modifier = Modifier.weight(1f))
                                EditIconComponent(
                                    onClick = {
                                    },
                                    painterResource(id = R.drawable.pen),
                                )
                            }
                            DividierProfile()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                                    .clickable {
                                    }
                            ) {
                                ProfileStartIconComponent(
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.love)
                                )
                                BasicText(modifier, "Favorite Restaurants")
                            }
                            DividierProfile()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                                    .clickable {
                                        navController.navigate(Screen.OrderScreen.route)
                                    }
                            ) {
                                ProfileStartIconComponent(
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.order)
                                )
                                BasicText(modifier, "Orders")
                            }
                            DividierProfile()
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun ProfileScreenPrew(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}