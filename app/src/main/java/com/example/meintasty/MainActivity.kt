package com.example.meintasty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.navigation.NavGraph
import com.example.meintasty.ui.theme.MeinTastyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var showSplashScreen by remember {
                mutableStateOf(true)
            }
            val lifecycleOwner = LocalLifecycleOwner.current
            val context = LocalContext.current
            LaunchedEffect(key1 = lifecycleOwner) {
                lifecycleOwner.lifecycleScope.launch {
                    delay(3000)
                    showSplashScreen = false
                }
            }
            val navContoller = rememberNavController()
                MeinTastyTheme {
                    Log.v("Logg:loginNav","")
                    NavGraph()
                }
           // }

        }
    }
}

