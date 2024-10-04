package com.example.meintasty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.feature.SplashScreenViewModel
import com.example.meintasty.feature.component.ScreenImage
import com.example.meintasty.feature.login_screen.LoginScreen
import com.example.meintasty.navigation.NavGraph
import com.example.meintasty.navigation.Screen
import com.example.meintasty.ui.theme.MeinTastyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var showSplashScreen by remember {
                mutableStateOf(true)
            }
            val lifecycleOwner = LocalLifecycleOwner.current
            LaunchedEffect(key1 = lifecycleOwner) {
                lifecycleOwner.lifecycleScope.launch {
                    delay(3000)
                    showSplashScreen = false
                }
            }
            val navContoller = rememberNavController()
            if(showSplashScreen) {
                SplashScreenContent(navContoller)
            }else {
                MeinTastyTheme {
                    Log.v("Logg:loginNav","")
                    NavGraph()
                }
            }

        }
    }
}

@Composable
fun SplashScreenContent(
    navController: NavController,
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel()

    ) {
    val authToken = splashScreenViewModel.tokenExists.observeAsState(initial = false)
    LaunchedEffect(authToken) {
        if (authToken.value){
            navController.navigate(Screen.LoginScreen.route) {

            }
        }else{
            navController.navigate(Screen.NewScreen.route)
        }
    }



    Box(modifier = Modifier.fillMaxSize()){
     //   Text(text = "Splah Screen")
    }
}
