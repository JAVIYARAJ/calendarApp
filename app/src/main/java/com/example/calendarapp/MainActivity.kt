package com.example.calendarapp

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelStore
import androidx.navigation.compose.rememberNavController
import com.example.calendarapp.navigation.graph.RootNavigationGraph
import com.example.calendarapp.ui.theme.CalendarAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarAppTheme {

                val uiController = rememberSystemUiController()
                val inDarkTheme = !isSystemInDarkTheme()

                //run after composable run successfully
                SideEffect {
                    uiController.setStatusBarColor(
                        color =if(inDarkTheme) Color.White else Color.Black,
                        darkIcons =inDarkTheme
                    )
                }

                val controller = rememberNavController()
                RootNavigationGraph(rootNavController = controller)
            }
        }
    }
}
