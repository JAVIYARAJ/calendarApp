package com.example.calendarapp.navigation.routes

sealed class Routes(val route: String) {

    object RootRoute: Routes(route = "root_route")

        object AuthRootRoutes: Routes(route = "auth_root_route")

            object LoginRoute: Routes(route = "login_route")
            object RegisterRoute: Routes(route = "register_route")
            object ForgotRoute: Routes(route = "forgot_route")


        object HomeRootRoute: Routes(route = "home_root_route")

            object HomeRoute: Routes(route = "home_route")

                object HomeTaskGroupRoutes:Routes(route = "home_task_group_route")

            object CalendarRoute: Routes(route = "calendar_route")
            object BookmarkRoute: Routes(route = "bookmark_route")
            object TaskRoute: Routes(route = "task_route")
            object HistoryRoute: Routes(route = "history_route")

}
