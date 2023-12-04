package com.example.calendarapp.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.calendarapp.navigation.Routes
import com.example.calendarapp.screens.auth.ForgotScreen
import com.example.calendarapp.screens.auth.LoginScreen
import com.example.calendarapp.screens.auth.RegisterScreen

fun NavGraphBuilder.authNavigationGraph(authNavController: NavHostController) {
    navigation(
        route = Routes.AuthRootRoutes.route,
        startDestination = Routes.LoginRoute.route,
    ) {
        composable(Routes.LoginRoute.route) {
            LoginScreen(authNavController, onLoginClick = {
                authNavController.popBackStack()
                authNavController.navigate(Routes.HomeRootRoute.route)
            })
        }
        composable(Routes.RegisterRoute.route) {
            RegisterScreen()
        }
        composable(Routes.ForgotRoute.route) {
            ForgotScreen()
        }
    }
}