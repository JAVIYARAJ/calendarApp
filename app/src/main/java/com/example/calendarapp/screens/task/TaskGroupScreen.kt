package com.example.calendarapp.screens.task

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.calendarapp.data.ColorModel
import com.example.calendarapp.data.categoryList
import com.example.calendarapp.navigation.routes.RouteQueryConstant.CATEGORY_COLOR
import com.example.calendarapp.navigation.routes.RouteQueryConstant.CATEGORY_QUERY
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.task.viewmodel.SharedTaskViewModel
import com.example.calendarapp.screens.task.widgets.TaskGroupCardWidget

@Composable
fun TaskGroupScreen(controller: NavHostController, sharedTaskViewModel: SharedTaskViewModel) {

    Scaffold { padding ->
        sharedTaskViewModel.getTaskCategoryValue()?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .then(Modifier.padding(bottom = 80.dp))
            ) {
                items(it.size) { index ->
                    TaskGroupCardWidget(it[index], onTap = { category ->

                        val route =
                            Routes.TaskRoute.route.replace(CATEGORY_QUERY, category.categoryName)
                        sharedTaskViewModel.setCategoryColor(category.color)
                        sharedTaskViewModel.setTaskListValue(category.taskList)
                        controller.navigate(route)

                    }, onColorChange = {
                        sharedTaskViewModel.changeTaskCategoryColor(index,it)
                    })
                }
            }
        }
    }

}

