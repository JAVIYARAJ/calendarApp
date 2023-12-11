package com.example.calendarapp.navigation.graph

import android.os.Build
import android.util.Log
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
import com.example.calendarapp.screens.profile.ProfileScreen
import com.example.calendarapp.screens.task.CreateTaskScreen
import com.example.calendarapp.screens.task.TaskGroupScreen
import com.example.calendarapp.screens.task.TaskScreen
import com.example.calendarapp.util.Util

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeGraph(homeNavController: NavHostController, onChange: () -> Unit) {

    NavHost(
        navController = homeNavController,
        route = Routes.HomeRootRoute.route,
        startDestination = Routes.HomeRoute.route,
    ) {
        composable(Routes.HomeRoute.route) {
            HomeScreen(controller = homeNavController)
        }

        composable(Routes.CalendarRoute.route) {
            CalendarMonthScreen(
                yearValue = Util.getYear(),
                monthValue = Util.getMonth(),
                onChange = onChange
            )
        }

        composable(Routes.CategoryRoute.route) {
            DocumentScreen()
        }

        composable(Routes.CategoryRoute.route) {
            TaskGroupScreen(controller = homeNavController)
        }

        composable(Routes.TaskRoute.route) {
            val category = it.arguments?.getString("category")
            val color=it.arguments?.getString("color")
            Log.e("TAG", "HomeGraph: ${color}", )
            TaskScreen(controller = homeNavController,category,color)
        }

        composable(Routes.CreateTaskRoute.route){
            CreateTaskScreen()
        }

        composable(Routes.HistoryRoute.route) {
            HistoryScreen()
        }

        composable(Routes.ProfileRoute.route){
            ProfileScreen()
        }

    }
}