package com.example.meintasty.feature.welcome_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.feature.component.BeatMeCardComponent
import com.example.meintasty.feature.component.SettingComponent
import com.example.meintasty.feature.component.SignUpButtonComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffdc3545)
                ),
                title = {},
                actions = {
                    SignUpButtonComponent(onClick = {}, stringResource(id = R.string.sign_up))
                  //  Spacer(modifier = Modifier.width(4.dp))
                    SignUpButtonComponent(onClick = {}, stringResource(id = R.string.sign_in))
                    SettingComponent(onClick = {}, painterResource(id = R.drawable.settings))
                },
                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(60.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .width(80.dp)
                                .height(50.dp)
                                .background(Color(0xffdc3545))
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp), // İkonun kenarlardan biraz boşluk bırakması için padding ekledik
                                painter = painterResource(id = R.drawable.meintast_logo),
                                contentDescription = ""
                            )
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                BeatMeCardComponent()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NewsScreenPrew(modifier: Modifier = Modifier) {
    NewScreen()
}
