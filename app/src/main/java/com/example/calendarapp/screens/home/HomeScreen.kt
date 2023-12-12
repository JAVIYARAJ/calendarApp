@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.calendarapp.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.data.currentUser
import com.example.calendarapp.navigation.routes.RouteQueryConstant
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.common.model.TaskGroupItem
import com.example.calendarapp.screens.home.widgets.HomeProgressCardWidget
import com.example.calendarapp.screens.home.widgets.HomeTaskStatusCardWidget
import com.example.calendarapp.screens.home.widgets.HomeTopBarWidget
import com.example.calendarapp.screens.home.widgets.TaskGroupCardWidget
import com.example.calendarapp.screens.task.viewmodel.SharedTaskViewModel
import com.example.calendarapp.ui.theme.completedTaskColor
import com.example.calendarapp.ui.theme.inProgressTaskColor
import com.example.calendarapp.ui.theme.inReviewTaskColor
import com.example.calendarapp.ui.theme.onCancelTaskColor
import com.example.calendarapp.ui.theme.onHoldTaskColor
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.util.UiConstant.widthModifier

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(controller: NavHostController, sharedTaskViewModel: SharedTaskViewModel) {

    Scaffold(topBar = {
        HomeTopBarWidget(userModel = currentUser, onTap = {
            controller.navigate(Routes.ProfileRoute.route)
        })
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .then(Modifier.padding(bottom = 80.dp))
        ) {
            item {
                HomeProgressCardWidget(progressRate = 0.9f)
            }
            item {
                Text(
                    text = "Task Status",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(10.dp)
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .then(Modifier.padding(vertical = 2.5.dp)),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HomeTaskStatusCardWidget(
                        "Completed", "25", modifier = Modifier
                            .weight(2f)
                            .padding(2.dp), completedTaskColor
                    )
                    HomeTaskStatusCardWidget(
                        "In Progress", "36", modifier = Modifier
                            .weight(2f)
                            .padding(2.dp), inProgressTaskColor
                    )
                    HomeTaskStatusCardWidget(
                        "In Review", "10", modifier = Modifier
                            .weight(2f)
                            .padding(2.dp), inReviewTaskColor
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .then(Modifier.padding(vertical = 2.5.dp)),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HomeTaskStatusCardWidget(
                        "On Hold", "5", modifier = Modifier
                            .weight(2f)
                            .padding(2.dp), onHoldTaskColor
                    )
                    HomeTaskStatusCardWidget(
                        "Canceled", "3", modifier = Modifier
                            .weight(2f)
                            .padding(2.dp), onCancelTaskColor
                    )
                    HomeTaskStatusCardWidget(
                        "Urgent", "20", modifier = Modifier
                            .weight(2f)
                            .padding(2.dp), primaryDarkColor
                    )

                }
            }

            item {
                Row(
                    modifier = widthModifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Task Groups",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primaryContainer,
                    )
                    Text(
                        text = "view more",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primaryContainer,
                    )
                }

            }
            sharedTaskViewModel.getTaskCategoryValue()?.let { category ->
                val list = if (category.size > 3) category.subList(0, 3) else category

                item {
                    repeat(list.size) { index ->
                        TaskGroupCardWidget(
                            index = (index+1),
                            taskCategoryModel = list[index],
                            onTap = {
                                val route =
                                    Routes.TaskRoute.route.replace(RouteQueryConstant.CATEGORY_QUERY,list[index].categoryName)
                                sharedTaskViewModel.setCategoryColor(list[index].color)
                                sharedTaskViewModel.setTaskListValue(list[index].taskList)
                                controller.navigate(route)
                            }
                        )
                    }
                }
            }

        }
    }


}
