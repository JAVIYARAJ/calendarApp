package com.example.calendarapp.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor

@Composable
fun CustomBottomAppBar(navController: NavHostController) {

    val bottomScreenItems = listOf(
        BottomScreens.Home,
        BottomScreens.Calendar,
        BottomScreens.BookMark,
        BottomScreens.Task,
    )

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = backStackEntry?.destination

    val isBottomAppBarVisible = bottomScreenItems.any { it.routes == currentDestination?.route }

    if (isBottomAppBarVisible) {

        NavigationBar(
            modifier = Modifier.clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentColor = Color.White,
            containerColor = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
        ) {
            bottomScreenItems.forEach { screen ->
                NavigationBarItem(
                    label = { Text(text = screen.title) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.routes } == true,
                    onClick = {
                        navController.navigate(screen.routes) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                    modifier = Modifier.clip(CircleShape),
                )
            }
        }
    }
}

sealed class BottomScreens(
    val routes: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomScreens(
        routes = Routes.HomeRoute.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Calendar : BottomScreens(
        routes = Routes.CalendarRoute.route,
        title = "Calendar",
        icon = Icons.Default.DateRange
    )

    object BookMark : BottomScreens(
        routes = Routes.BookmarkRoute.route,
        title = "Bookmark",
        icon = Icons.Default.Star
    )

    object Task : BottomScreens(
        routes = Routes.TaskRoute.route,
        title = "Task",
        icon = Icons.Default.ShoppingCart
    )

}