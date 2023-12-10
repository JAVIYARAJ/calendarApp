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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.common.TaskGroupItem
import com.example.calendarapp.screens.home.widgets.HomeProgressCardWidget
import com.example.calendarapp.screens.home.widgets.HomeTaskStatusCardWidget
import com.example.calendarapp.screens.home.widgets.HomeTopBarWidget
import com.example.calendarapp.screens.home.widgets.TaskGroupCardWidget
import com.example.calendarapp.ui.theme.completedTaskColor
import com.example.calendarapp.ui.theme.inProgressTaskColor
import com.example.calendarapp.ui.theme.inReviewTaskColor
import com.example.calendarapp.ui.theme.onCancelTaskColor
import com.example.calendarapp.ui.theme.onHoldTaskColor
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.util.UiConstant.widthModifier

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(controller: NavHostController) {

    /*
    val allDaysList = Util.getCurrentWeekDates()

    val pagerState = rememberPagerState(initialPage = 0) {
        allDaysList.size
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            DAYS_TITLE.forEach { title ->
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            repeat(allDaysList.size) { index ->
                CalendarDayCardWidget(
                    day = allDaysList[index],
                    modifier = Modifier.weight(1f),
                    isCurrentDay = (if (Util.getDay()
                            .toString().length == 1
                    ) "0" + Util.getDay().toString() else "") == allDaysList[index]
                )
            }
        }

    }

     */

    val taskGroupList = listOf(
        TaskGroupItem(
            id = "1",
            title = "Office Project",
            taskSize = 50,
            completedTaskSize = 12,
            taskGroupIcon = R.drawable.ic_office_task_group_icon,
            taskGridColor = Color(0XFF9B59B6)
        ),
        TaskGroupItem(
            id = "2",
            title = "Personal Project",
            taskSize = 20,
            completedTaskSize = 2,
            taskGroupIcon = R.drawable.ic_project_task_group_icon,
            taskGridColor = Color(0XFFD68910)
        ),
        TaskGroupItem(
            id = "3",
            title = "Home",
            taskSize = 35,
            completedTaskSize = 9,
            taskGroupIcon = R.drawable.ic_home_task_group_icon,
            taskGridColor = Color(0XFF3498DB)
        ),
        TaskGroupItem(
            id = "4",
            title = "Daily Task",
            taskSize = 50,
            completedTaskSize = 12,
            taskGroupIcon = R.drawable.ic_daily_task_group_icon
        ),
        TaskGroupItem(
            id = "5",
            title = "Data structure",
            taskSize = 12,
            completedTaskSize = 2,
            taskGridColor = Color(0XFF48C9B0)
        )
    )

    Scaffold(topBar = {
        HomeTopBarWidget()
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it).then(Modifier.padding(bottom = 80.dp))
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
                        .padding(horizontal = 10.dp),
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
                        .padding(horizontal = 10.dp),
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
                        "Urgent", "2", modifier = Modifier
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

            item {
                repeat(taskGroupList.size) { taskGroup ->
                    TaskGroupCardWidget(taskGroupList[taskGroup])
                }
            }

        }
    }


}

