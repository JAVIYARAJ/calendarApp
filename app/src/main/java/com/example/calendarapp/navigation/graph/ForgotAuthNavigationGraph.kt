package com.example.calendarapp.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.auth.ConfirmPasswordScreen
import com.example.calendarapp.screens.auth.ForgotOtpVerifyScreen
import com.example.calendarapp.screens.auth.ForgotOtpSendScreen

fun NavGraphBuilder.forgotNavigationGraph(controller: NavHostController) {
    navigation(
        route = Routes.ForgotRoute.route,
        startDestination = Routes.ForgotOtpSendRoute.route
    ) {
        composable(Routes.ForgotOtpSendRoute.route) {
            ForgotOtpSendScreen(controller)
        }
        composable(Routes.ForgotOtpVerifyRoute.route) {
            ForgotOtpVerifyScreen(controller)
        }
        composable(Routes.ConfirmPasswordRoute.route) {
            ConfirmPasswordScreen(controller)
        }
    }
}