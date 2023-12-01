package com.example.calendarapp

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calendarapp.screens.CalendarMonthScreen
import com.example.calendarapp.screens.CalendarYearScreen
import com.example.calendarapp.screens.HomeScreen
import com.example.calendarapp.ui.theme.CalendarAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(controller: NavHostController) {

    NavHost(navController = controller, startDestination = Routes.HomeScreen.route) {
        composable(Routes.HomeScreen.route) {
            HomeScreen()
        }
        composable(Routes.YearViewScreen.route) {
            CalendarAppTheme {
                CalendarYearScreen(controller = controller)
            }
        }
        composable(Routes.MonthViewScreen.route) {
            val year = it.arguments?.getString("year")
            val month = it.arguments?.getString("month")
            CalendarAppTheme {
                CalendarMonthScreen(yearValue = year?.toInt(), monthValue = month?.toInt())
            }
        }

    }
}