package com.example.meintasty.feature.profile_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import com.example.meintasty.uicomponent.ProfileStartIcon
import com.example.meintasty.uicomponent.BasicText
import com.example.meintasty.uicomponent.ProfileUserIcon
import com.example.meintasty.uicomponent.LabelUserText
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.colorResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

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
                            LabelUserText("Gülten Çelikbilek")
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
                            ProfileStartIcon(
                                onClick = {},
                                painter = painterResource(id = R.drawable.gmail)
                            )
                            BasicText(modifier, "gultencelikbilek924@gmail.com")
                            Spacer(modifier = Modifier.weight(1f))
                            EditIconComponent(onClick = {})
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

                            BasicText(modifier, "+90 555 555 5555")
                            Spacer(modifier = Modifier.weight(1f))
                            EditIconComponent(
                                onClick = {},
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
                                BasicText(modifier, "Address")
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
    )
}

@Preview
@Composable
fun ProfileScreenPrew(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}