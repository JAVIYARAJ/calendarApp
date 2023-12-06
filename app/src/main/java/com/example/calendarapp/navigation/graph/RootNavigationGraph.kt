package com.example.calendarapp.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.home.HomeContentScreen
import com.example.calendarapp.screens.welcome.OnBoardingScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(rootNavController: NavHostController) {
    NavHost(
        navController = rootNavController,
        route = Routes.RootRoute.route,
        startDestination = Routes.OnBoardingRoutes.route,
    ) {

        composable(Routes.OnBoardingRoutes.route){
            OnBoardingScreen(rootNavController)
        }

        //auth routes
        authNavigationGraph(authNavController = rootNavController)

        //home root route
        composable(Routes.HomeRootRoute.route) {
            HomeContentScreen()
        }

    }
}