package com.example.meintasty.feature.restaurant_profile_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.meintasty.domain.model.get_user_model.user_request.UserRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.BackIcon
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

    val detailProfileState = restaurantProfileViewModel.detailRestProfState.collectAsState()
    val restaurantDetailState = restaurantProfileViewModel.restaurantDatabaseState.collectAsState()


    val resturantId = restaurantDetailState.value.data?.restaurantId
    val context = LocalContext.current

    LaunchedEffect(resturantId) {
        restaurantProfileViewModel.getRestaurantDatabase()
        resturantId?.let {
            restaurantProfileViewModel.getDetailRestaurant(DetailRestaurantRequest(it))
            Log.d("restaurantId","$resturantId")
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
                        /* BackIcon {
                             navController.navigate(Screen.RestaurantScreen.route)
                         }*/
                        HeaderComponent(text = stringResource(id = R.string.restaurant_profile))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            detailProfileState.value.data?.let { restaurant ->
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
                                            /* navController.navigate(
                                                 Screen.UpdateScreen.route + "?userId=$userId&email=${user?.email}&phone=${user?.phoneNumber}&updateType=email"
                                             )*/
                                        }
                                    })
                            }
                        }
                    }
                }
            }
        }
    )
}