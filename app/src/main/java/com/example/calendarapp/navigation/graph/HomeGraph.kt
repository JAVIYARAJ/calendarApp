package com.example.calendarapp.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.document.DocumentScreen
import com.example.calendarapp.screens.calendar.CalendarMonthScreen
import com.example.calendarapp.screens.history.HistoryScreen
import com.example.calendarapp.screens.home.HomeScreen
import com.example.calendarapp.screens.task.TaskGroupScreen
import com.example.calendarapp.util.Util

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeGraph(homeNavController: NavHostController,onChange:()->Unit) {

    NavHost(
        navController = homeNavController,
        route = Routes.HomeRootRoute.route,
        startDestination = Routes.HomeRoute.route,
    ) {
        composable(Routes.HomeRoute.route) {
            HomeScreen(controller = homeNavController)
        }

        composable(Routes.CalendarRoute.route) {
            CalendarMonthScreen(yearValue = Util.getYear(),monthValue = Util.getMonth(),onChange=onChange)
        }

        composable(Routes.DocumentRoute.route) {
            DocumentScreen()
        }

        composable(Routes.TaskRoute.route) {
            TaskGroupScreen(controller = homeNavController)
        }

        composable(Routes.HistoryRoute.route) {
            HistoryScreen()
        }

        composable(Routes.HomeTaskGroupRoutes.route){
            TaskGroupScreen(controller = homeNavController)
        }
    }
}