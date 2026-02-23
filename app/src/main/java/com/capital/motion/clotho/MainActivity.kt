package com.capital.motion.clotho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.capital.motion.clotho.ui.navigation.AppNavHost
import com.capital.motion.clotho.ui.theme.ClothoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClothoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { _ ->
                    AppNavHost()   // ← AppNavHost manages navController internally
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClothoTheme {
        AppNavHost()
    }
}