package com.example.meintasty.feature.profile_screen

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material.Scaffold
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.meintasty.uicomponent.ProfileStartIcon
import com.example.meintasty.uicomponent.BasicText
import com.example.meintasty.uicomponent.ProfileUserIcon
import com.example.meintasty.uicomponent.LabelUserText
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meintasty.domain.model.get_user_model.user_request.UserRequest
import com.example.meintasty.navigation.Screen
import com.google.gson.annotations.Until
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    userViewModel: ProfileViewModel = hiltViewModel()
) {
    val userState = userViewModel.userState.collectAsState().value
    val userDatabaseState = userViewModel.userDatabaseState.collectAsState()

    LaunchedEffect(Unit) {
        userViewModel.getUserDatabaseModel()
    }

    val userId = userDatabaseState.value.data?.userId

    LaunchedEffect(userId) {
        userId?.let {
            if (it > 0) {
                val userRequest = UserRequest(it)
                Log.d("userrequest:userId3:", "$it")
                userViewModel.getUser(userRequest = userRequest)
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
                            navController.navigateUp()
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
            if (userState.isLoading == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.mein_tasty_color)
                    )
                }
            } else if(userState.isSucces == true){ //!!!!!!
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
                                    ProfileUserIcon(onClick = {}, painterResource(id = R.drawable.user))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    LabelUserText(user?.fullName.toString())
                                    Spacer(modifier = Modifier.weight(1f))
                                    EditIconComponent(
                                        onClick = {},

                                        )
                                }

                                DividierProfile()
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp)
                                ) {
                                    ProfileStartIcon(onClick = {}, painter = painterResource(id = R.drawable.gmail))
                                    BasicText(modifier, user?.email.toString())
                                    Spacer(modifier = Modifier.weight(1f))
                                    EditIconComponent(onClick = {
                                        navController.navigate(Screen.UpdateScreen.route+"?userId=$userId?email=${user?.email}?phone=${user?.phoneNumber}?updateType=email"
                                        )
                                    })
                                }
                                DividierProfile()
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp)
                                ) {
                                    ProfileStartIcon(
                                        modifier,
                                        onClick = {},
                                        painter = painterResource(id = R.drawable.phone)
                                    )
                                    BasicText(modifier, user?.phoneNumber.toString())
                                    Spacer(modifier = Modifier.weight(1f))
                                    EditIconComponent(
                                        onClick = {
                                            navController.navigate(Screen.UpdateScreen.route+ "?userId=$userId?email=${user?.email}?phone=${user?.phoneNumber}?updateType=phone"
                                            )
                                        },
                                        modifier
                                    )
                                }
                                DividierProfile()

                            }
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Column() {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentSize(),
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
                                            .padding(vertical = 10.dp)
                                    ) {
                                        ProfileStartIcon(
                                            onClick = {},
                                            painter = painterResource(id = R.drawable.navigation)
                                        )
                                        BasicText(modifier, user?.userAdddress.toString())
                                        Spacer(modifier = Modifier.weight(1f))
                                        EditIconComponent(
                                            onClick = {}
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 10.dp)
                                    ) {
                                        ProfileStartIcon(
                                            onClick = {},
                                            painter = painterResource(id = R.drawable.love)
                                        )
                                        BasicText(modifier, "Favorite Restaurants")
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 10.dp)
                                    ) {
                                        ProfileStartIcon(
                                            onClick = {},
                                            painter = painterResource(id = R.drawable.order)
                                        )
                                        BasicText(modifier, "Orders")
                                    }
                                }
                            }
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