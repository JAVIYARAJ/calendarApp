package com.example.calendarapp.screens.common.widgets

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant.robotoFontFamily

@Composable
fun CustomBottomAppBar(navController: NavHostController) {

    val bottomScreenItems = listOf(
        BottomScreens.Home,
        BottomScreens.Calendar,
        BottomScreens.CreateTask,
        BottomScreens.Category,
        BottomScreens.History,
    )

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = backStackEntry?.destination

    val isBottomAppBarVisible =
        bottomScreenItems.any { it.routes == currentDestination?.route }
    Log.e("TAG", "CustomBottomAppBar: ${currentDestination?.route}")
    if (isBottomAppBarVisible) {

        NavigationBar(
            modifier = Modifier.clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentColor = Color.White,
            containerColor = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
        ) {
            bottomScreenItems.forEach { screen ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == screen.routes } == true

                NavigationBarItem(
                    label = {
                        Text(
                            text = screen.title,
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                fontFamily = robotoFontFamily
                            )
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(screen.routes) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (isSelected) screen.selectedIcon else screen.unSelectedIcon,
                            contentDescription = screen.title
                        )
                    },
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

    object Category : BottomScreens(
        routes = Routes.CategoryRoute.route,
        title = "Category",
        selectedIcon = Icons.Default.Group,
        unSelectedIcon = Icons.Outlined.Group,
    )

    object CreateTask : BottomScreens(
        routes = Routes.CreateTaskRoute.route,
        title = "Task",
        selectedIcon = Icons.Filled.Add,
        unSelectedIcon = Icons.Outlined.Add,
    )

    object History : BottomScreens(
        routes = Routes.HistoryRoute.route,
        title = "History",
        selectedIcon = Icons.Filled.AccessTime,
        unSelectedIcon = Icons.Outlined.AccessTime,
    )
}


@Composable
fun CustomBottomNavIcon(title: String, icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Icon(imageVector = icon, contentDescription = "", tint = Color.Black)
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.Black,
                    fontFamily = robotoFontFamily
                )
            )
        }
    }
}