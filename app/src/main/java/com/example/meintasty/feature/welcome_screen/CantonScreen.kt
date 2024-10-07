package com.example.meintasty.feature.welcome_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.CantonRequestModel
import com.example.meintasty.feature.component.BeatMeCardComponent
import com.example.meintasty.feature.component.BreakfastCardComponent
import com.example.meintasty.feature.component.FoodCardComponent
import com.example.meintasty.feature.component.HotTodayComponent
import com.example.meintasty.feature.component.MeinTastyLogoComponent
import com.example.meintasty.feature.component.MenuButtonComponent
import com.example.meintasty.feature.component.SettingComponent
import com.example.meintasty.feature.component.SignInButtonComponent
import com.example.meintasty.feature.component.SignUpButtonComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CantonScreen(
    navController: NavController,
    cantonViewModel: CantonViewModel = hiltViewModel()
) {
    val requestModel = CantonRequestModel()

    LaunchedEffect(Unit) {
        cantonViewModel.getCanton(requestModel)  // Modeli burada ViewModel'e geçiyoruz
    }


    Scaffold(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffdc3545)
                ),
                title = {},
                actions = {
                    Row {
                        SignUpButtonComponent(onClick = {}, stringResource(id = R.string.sign_up))
                        SignInButtonComponent(onClick = {}, stringResource(id = R.string.sign_in))
                        SettingComponent(onClick = {}, painterResource(id = R.drawable.settings))
                    }
                },
                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(60.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        MeinTastyLogoComponent(
                            onClick = {},
                            painterResource(id = R.drawable.meintast_logo)
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.Bottom
            ) {
                BeatMeCardComponent(cantonViewModel)
                Spacer(modifier = Modifier.height(10.dp))
                FoodCardComponent()
                Spacer(modifier = Modifier.height(10.dp))
                MenuButtonComponent(onClick = {})
                Spacer(modifier = Modifier.height(10.dp))
                HotTodayComponent()
                BreakfastCardComponent()
            }
        }
    )
}

@Preview
@Composable
fun CantonScreenPrew() {
    val navController = rememberNavController()
    CantonScreen(navController = navController)

}


