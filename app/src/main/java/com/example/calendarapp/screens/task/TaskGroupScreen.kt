package com.example.calendarapp.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.calendarapp.navigation.routes.RouteQueryConstant.CATEGORY_QUERY
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.common.widgets.CustomScreenTopNavBar
import com.example.calendarapp.screens.task.widgets.TaskGroupCardWidget

@Composable
fun TaskGroupScreen(controller: NavHostController) {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .then(Modifier.padding(bottom = 80.dp))
        ) {
            items(categoryList.size) { index ->
                TaskGroupCardWidget(categoryList[index], onTap = {
                    category->
                    val route=Routes.TaskRoute.route.replace(CATEGORY_QUERY,category.categoryName)
                    controller.navigate(route)
                })
            }
        }
    }

}

