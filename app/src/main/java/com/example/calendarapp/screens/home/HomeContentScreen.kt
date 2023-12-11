package com.example.calendarapp.screens.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.calendarapp.screens.common.widgets.CustomBottomAppBar
import com.example.calendarapp.navigation.graph.HomeGraph

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContentScreen() {
    val homeNavHostController = rememberNavController()

    val drawerState= rememberDrawerState(initialValue = DrawerValue.Open)

    val scope= rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            CustomBottomAppBar(homeNavHostController)
        }
    ) {
        HomeGraph(homeNavController = homeNavHostController, onChange = {

        })
    }

}
