package com.example.restoran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.restoran.navigation.RestoranNavGraph
import com.example.restoran.ui.theme.SushiinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        
        val prefs = getSharedPreferences("resto_prefs", MODE_PRIVATE)

        setContent {
            var isDarkMode by remember { 
                mutableStateOf(prefs.getBoolean("is_dark_mode", false)) 
            }

            SushiinTheme(isDarkMode = isDarkMode) {
                val navController = rememberNavController()
                RestoranNavGraph(
                    navController = navController,
                    isDarkMode = isDarkMode,
                    onDarkModeToggle = {
                        isDarkMode = it
                        prefs.edit { putBoolean("is_dark_mode", it) }
                    }
                )
            }
        }
    }
}
