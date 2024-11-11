package com.example.meintasty

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.meintasty.navigation.NavGraph
import com.example.meintasty.ui.theme.MeinTastyTheme
import com.example.meintasty.work_manager.TokenRefreshWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //worker başlatma
       // val tokenWorker = PeriodicWorkRequestBuilder<TokenRefreshWorker>(60,TimeUnit.MINUTES).build()
       // WorkManager.getInstance(this).enqueue(tokenWorker)


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
                MeinTastyTheme {
                    Log.v("Logg:loginNav","")
                    NavGraph()
                }

        }
    }
}

