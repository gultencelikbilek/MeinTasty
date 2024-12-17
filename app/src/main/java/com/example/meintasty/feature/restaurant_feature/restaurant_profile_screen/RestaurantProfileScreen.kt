package com.example.meintasty.feature.restaurant_feature.restaurant_profile_screen

import android.content.Context
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
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.BasicText
import com.example.meintasty.uicomponent.DividierProfile
import com.example.meintasty.uicomponent.EditIconComponent
import com.example.meintasty.uicomponent.HeaderComponent
import com.example.meintasty.uicomponent.LabelUserText
import com.example.meintasty.uicomponent.ProfileStartIcon
import com.example.meintasty.uicomponent.ProfileUserIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    restaurantProfileViewModel: RestaurantProfileViewModel = hiltViewModel()
) {

    val detailRestaurantProfileState = restaurantProfileViewModel.detailRestaurantProfileState.collectAsState()
    val restaurantIdState = restaurantProfileViewModel.restaurantDatabaseState.collectAsState()
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)

    val restaurantId = restaurantIdState.value.data?.restaurantId

    LaunchedEffect(restaurantId) {
        restaurantProfileViewModel.getRestaurantDatabase()
        restaurantId?.let {
            restaurantProfileViewModel.getDetailRestaurant(DetailRestaurantRequest(it))

            val editorRestaurantId = sharedPreferences.edit()
            editorRestaurantId.putInt(Constants.SHARED_RESTAURANT_ID, it)
            editorRestaurantId.apply()

            Log.d("restaurantId","$restaurantId")
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
                        HeaderComponent(text = stringResource(id = R.string.restaurant_profile))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            detailRestaurantProfileState.value.data?.let { restaurant ->
                Log.d("restauran","$restaurant")
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
                                LabelUserText(restaurant.restaurantName.toString())
                            }

                            DividierProfile()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                ProfileStartIcon(
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.gmail)
                                )
                                BasicText(modifier, restaurant.email.toString())
                                Spacer(modifier = Modifier.weight(1f))
                                EditIconComponent(
                                    onClick = {
                                        if (restaurant?.email == null) {
                                            Toast.makeText(context, "Null", Toast.LENGTH_SHORT)
                                                .show()

                                        } else {
                                            Toast.makeText(context, "go", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    },
                                    painterResource(id = R.drawable.pen)
                                )
                            }

                            DividierProfile()
                            val regex = """(\d)(\d{3})(\d{3})(\d{2})(\d{2})""".toRegex()
                            val number = restaurant?.phoneNumber.toString()
                            val output = regex.replace(number, "$2 $3-$4-$5")
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                ProfileStartIcon(
                                    modifier = modifier,
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.phone)
                                )
                                BasicText(modifier, output)
                                Spacer(modifier = Modifier.weight(1f))
                                EditIconComponent(
                                    onClick = {
                                        if (restaurant?.phoneNumber.isNullOrEmpty()) {
                                            Toast.makeText(
                                                context,
                                                R.string.number_null,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                           //navcontroller
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
                                    .padding(vertical = 10.dp)
                            ) {
                                ProfileStartIcon(
                                    modifier = modifier,
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.calendar)
                                )
                                BasicText(modifier, "${restaurant.workDayFrom.toString()} - ${restaurant.workDayTo.toString()}")
                                Spacer(modifier = Modifier.weight(1f))
                                EditIconComponent(
                                    onClick = {

                                    },
                                    painterResource(id = R.drawable.pen),
                                    modifier = modifier
                                )
                            }

                            DividierProfile()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                ProfileStartIcon(
                                    modifier = modifier,
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.clock)
                                )
                                BasicText(modifier, "${restaurant.workHourFrom.toString()}- ${restaurant.workHourTo.toString()}")
                                Spacer(modifier = Modifier.weight(1f))
                                EditIconComponent(
                                    onClick = {

                                    },
                                    painterResource(id = R.drawable.pen),
                                    modifier = modifier
                                )
                            }

                            DividierProfile()
                            Column {
                                restaurant.addressList?.forEach {adress->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 10.dp)
                                    ) {
                                        ProfileStartIcon(
                                            onClick = {},
                                            painter = painterResource(id = R.drawable.navigation)
                                        )
                                        BasicText(modifier, adress?.addressText.toString())
                                        Spacer(modifier = Modifier.weight(1f))
                                        EditIconComponent(
                                            onClick = {
                                            },
                                            painterResource(id = R.drawable.pen)
                                        )
                                    }
                                }
                            }

                            DividierProfile()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                                    .clickable {
                                        navController.navigate(Screen.RestaurantMenuDetailScreen.route)
                                    }
                            ) {
                                ProfileStartIcon(
                                    onClick = {},
                                    painter = painterResource(id = R.drawable.restaurant_menu)
                                )
                                BasicText(modifier, "Restaurant Menu")
                                Spacer(modifier = Modifier.weight(1f))
                                EditIconComponent(
                                    onClick = {
                                              navController.navigate(Screen.RestaurantMenuDetailScreen.route)
                                    },
                                    painterResource(id = R.drawable.right_arrow)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}