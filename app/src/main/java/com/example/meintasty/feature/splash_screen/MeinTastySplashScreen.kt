package com.example.meintasty.feature.splash_screen

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.meintasty.data.Constants
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
    val context = LocalContext.current
    val sharedPrefrences =
        context.getSharedPreferences(Constants.SHARED_TOKEN, Context.MODE_PRIVATE)
    val splashShowState = meinTastyViewModel.splashShow.collectAsState().value
    val locInfo = meinTastyViewModel.locaState.collectAsState().value

    LaunchedEffect(splashShowState) {
        if (splashShowState.data != null) {
            Log.d("splashshowstate:data:", "${splashShowState.data}")
            splashShowState?.data?.let { token ->
                Log.v("splashShowState:", splashShowState.toString())
                Log.d("tokenNotnull:", "${token}")
                val editor = sharedPrefrences.edit()
                editor.putString(Constants.SHARED_TOKEN, token.token.toString())
                editor.apply()
            }
        } else {
            Log.d("splash:else", "")
            navController.navigate(Screen.ChooseLoginRegisterScreen.route)
        }
    }

    LaunchedEffect(locInfo) {
        if (locInfo.data != null && locInfo.isNavigateLoginScreen ==true) {
            navController.navigate(Screen.RestaurantScreen.route)
        } else if (locInfo.data == null && locInfo.isNavigateLoginScreen != true) {
            Log.v("splashShowStateLoc:", "Waiting for valid data")
        } else if (locInfo.isNavigateLoginScreen == true) {
            // Bu durumda hem data boş, hem de isNavigateLoginScreen yanlış bir şekilde true ise
            Log.v("splashShowStateLoc:", "else condition triggered")
            navController.navigate(Screen.CantonScreen.route)
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