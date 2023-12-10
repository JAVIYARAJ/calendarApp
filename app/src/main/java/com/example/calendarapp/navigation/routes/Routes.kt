package com.example.calendarapp.navigation.routes

import com.example.calendarapp.navigation.routes.RouteQueryConstant.EMAIL_QUERY

sealed class Routes(val route: String) {

    object RootRoute: Routes(route = "root_route")

    object OnBoardingRoutes: Routes(route = "onBoarding_root_route")

    object AuthRootRoutes: Routes(route = "auth_root_route")

            object LoginRoute: Routes(route = "login_route")
            object RegisterRoute: Routes(route = "register_route")
            object ForgotRoute: Routes(route = "forgot_route")
                    object ForgotOtpSendRoute: Routes(route = "forgot_otp_send_route")
                    object ForgotOtpVerifyRoute: Routes(route = "forgot_otp_verify_route/${EMAIL_QUERY}")
                    object ConfirmPasswordRoute: Routes(route = "confirm_password_route")



        object HomeRootRoute: Routes(route = "home_root_route")

            object HomeRoute: Routes(route = "home_route")

                object HomeTaskGroupRoutes:Routes(route = "home_task_group_route")

            object CalendarRoute: Routes(route = "calendar_route")
            object DocumentRoute: Routes(route = "document_route")
            object TaskRoute: Routes(route = "task_route")
            object HistoryRoute: Routes(route = "history_route")

}


object RouteQueryConstant{
    const val EMAIL_QUERY="{email}"
}