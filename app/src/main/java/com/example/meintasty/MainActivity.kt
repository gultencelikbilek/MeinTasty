package com.example.meintasty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.meintasty.feature.login_screen.LoginScreen
import com.example.meintasty.navigation.NavGraph
import com.example.meintasty.ui.theme.MeinTastyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeinTastyTheme {
                NavGraph()
            }
        }
    }
}
