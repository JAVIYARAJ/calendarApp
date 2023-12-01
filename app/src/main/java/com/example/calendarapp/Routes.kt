package com.example.calendarapp

sealed class Routes(val route: String) {

    object SplashScreen : Routes(route = "splash_route")
    object HomeScreen : Routes(route = "home_route")
    object MonthViewScreen : Routes(route = "month_view_route/{year}/{month}")
    object YearViewScreen : Routes(route = "year_view_route")

}
