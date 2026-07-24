package com.continuum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.continuum.ui.theme.ContinuumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Database()

        setContent {
            // 1. Move the state tracking variable inside setContent using remember
            var result by remember { mutableStateOf("Loading...") }

            // 2. Launch the coroutine scope linked to the composition lifecycle
            LaunchedEffect(Unit) {
                result = try {
                    // 3. Updating this state variable will now automatically trigger a UI refresh!
                    db.testDB()
                } catch (e: Exception) {
                    "error"
                }
            }
            ContinuumTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = result,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContinuumTheme {
        Greeting("Gary")
    }
}