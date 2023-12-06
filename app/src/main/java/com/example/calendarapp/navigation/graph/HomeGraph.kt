package com.example.calendarapp.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.main.BookmarkScreen
import com.example.calendarapp.screens.main.CalendarMonthScreen
import com.example.calendarapp.screens.main.CalendarYearScreen
import com.example.calendarapp.screens.main.HistoryScreen
import com.example.calendarapp.screens.main.HomeScreen
import com.example.calendarapp.screens.main.ProfileScreen
import com.example.calendarapp.screens.main.TaskGroupScreen
import com.example.calendarapp.screens.main.TaskScreen
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
            //CalendarYearScreen(controller = homeNavController)
        }

        composable(Routes.BookmarkRoute.route) {
            BookmarkScreen()
        }

        composable(Routes.TaskRoute.route) {
            TaskScreen()
        }

        composable(Routes.HistoryRoute.route) {
            HistoryScreen()
        }

        composable(Routes.HomeTaskGroupRoutes.route){
            TaskGroupScreen(controller = homeNavController)
        }
    }
}