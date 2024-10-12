package com.example.meintasty.feature.splash_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.navigation.Screen

@Composable
fun MeinTastySplashScreen(
    navController: NavController,
    meinTastyViewModel: MeinTastyViewModel = hiltViewModel()
) {

    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_blackitalic, weight = FontWeight.Normal)
    )
    val locationState = meinTastyViewModel.locaState.collectAsState()

    LaunchedEffect(Unit) {

        val splashShowState = meinTastyViewModel.splashShow.value
        Log.v("splashShowState:", splashShowState.toString())
        if (splashShowState?.data?.token != null) {
            Log.d("tokenNotnull:","${splashShowState.data.token}")


                if (splashShowState.isNavigateLoginScreen == true) {
                    Log.v("splashShowState:", splashShowState.toString())
                    navController.navigate(Screen.RestaurantScreen.route)
                } else {
                    Log.v("splashShowState:", "else")
                    navController.navigate(Screen.CantonScreen.route)
                }
        } else {
            navController.navigate(Screen.ChooseLoginRegisterScreen.route)
        }
    }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(colorResource(id = R.color.mein_tasty_color)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.mein_tasty),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = Color.White,
                        fontFamily = customFontFamily
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.meintast_logo),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun SplashPrew() {
    val navController = rememberNavController()
    MeinTastySplashScreen(navController = navController)

}