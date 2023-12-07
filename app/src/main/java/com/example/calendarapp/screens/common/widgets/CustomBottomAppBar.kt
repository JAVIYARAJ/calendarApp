package com.example.calendarapp.screens.common.widgets

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.TaskAlt
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
        BottomScreens.History,
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
                val isSelected=currentDestination?.hierarchy?.any { it.route == screen.routes } == true
                NavigationBarItem(
                    label = { Text(text = screen.title) },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(screen.routes) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(imageVector = if(isSelected) screen.selectedIcon else screen.unSelectedIcon, contentDescription = screen.title) },
                    modifier = Modifier.clip(CircleShape),
                )
            }
        }
    }
}

sealed class BottomScreens(
    val routes: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
) {
    object Home : BottomScreens(
        routes = Routes.HomeRoute.route,
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
    )

    object Calendar : BottomScreens(
        routes = Routes.CalendarRoute.route,
        title = "Calendar",
        selectedIcon = Icons.Default.DateRange,
        unSelectedIcon = Icons.Outlined.DateRange,
    )

    object BookMark : BottomScreens(
        routes = Routes.BookmarkRoute.route,
        title = "Bookmark",
        selectedIcon = Icons.Default.Star,
        unSelectedIcon = Icons.Outlined.Star,
    )

    object Task : BottomScreens(
        routes = Routes.TaskRoute.route,
        title = "Task",
        selectedIcon = Icons.Filled.TaskAlt,
        unSelectedIcon = Icons.Outlined.TaskAlt,
    )

    object History : BottomScreens(
        routes = Routes.HistoryRoute.route,
        title = "History",
        selectedIcon = Icons.Filled.AccessTime,
        unSelectedIcon = Icons.Outlined.AccessTime,
    )


}