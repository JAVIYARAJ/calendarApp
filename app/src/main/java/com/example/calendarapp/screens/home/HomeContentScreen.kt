package com.example.calendarapp.screens.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.calendarapp.screens.common.widgets.CustomBottomAppBar
import com.example.calendarapp.navigation.graph.HomeGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContentScreen() {
    val homeNavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            CustomBottomAppBar(homeNavHostController)
        }
    ) {
        HomeGraph(homeNavController = homeNavHostController, onChange = {

        })
    }
}
