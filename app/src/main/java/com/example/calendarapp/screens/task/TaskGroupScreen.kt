package com.example.calendarapp.screens.task

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.calendarapp.navigation.routes.RouteQueryConstant.CATEGORY_COLOR
import com.example.calendarapp.navigation.routes.RouteQueryConstant.CATEGORY_QUERY
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.task.model.categoryList
import com.example.calendarapp.screens.task.viewmodel.SharedTaskViewModel
import com.example.calendarapp.screens.task.widgets.TaskGroupCardWidget
import kotlin.math.log

@Composable
fun TaskGroupScreen(controller: NavHostController) {

    val viewModel:SharedTaskViewModel= viewModel()
    viewModel.setTaskCategoryList(categoryList)

    Scaffold {
        viewModel.getTaskCategoryList()?.let {state->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .then(Modifier.padding(bottom = 80.dp))
            ) {
                items(state.size) { index ->
                    TaskGroupCardWidget(state[index], onTap = {
                            category->
                        viewModel.setTaskList(category.taskList)
                        val route=Routes.TaskRoute.route.replace(CATEGORY_QUERY,category.categoryName).replace(CATEGORY_COLOR,category.color)
                        controller.navigate(route)
                    })
                }
            }
        }

    }

}

