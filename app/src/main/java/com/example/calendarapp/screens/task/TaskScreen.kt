package com.example.calendarapp.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.screens.common.widgets.CustomScreenTopNavBar
import com.example.calendarapp.screens.task.model.TaskStatus
import com.example.calendarapp.screens.task.model.TaskStatusModel
import com.example.calendarapp.screens.task.viewmodel.SharedTaskViewModel
import com.example.calendarapp.screens.task.widgets.SearchBarTextFiled
import com.example.calendarapp.screens.task.widgets.TaskCardWidget
import com.example.calendarapp.util.UiConstant.robotoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    controller: NavHostController, category: String?, sharedTaskViewModel: SharedTaskViewModel
) {

    var searchQuery by remember {
        mutableStateOf("")
    }
    val allTask = sharedTaskViewModel.getTaskListValue()

    val resultTask =
        if (searchQuery.isNotEmpty()) sharedTaskViewModel.getTaskListValue(true) else allTask

    val keyboardController = LocalSoftwareKeyboardController.current

    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)

    val focusRequester = remember {
        FocusRequester()
    }

    Scaffold(topBar = {
        CustomScreenTopNavBar(title = "$category tasks", onBackClick = {
            controller.popBackStack()
        })
    }) { padding ->

        resultTask?.let { tasks ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .then(Modifier.padding(padding))
                    .navigationBarsPadding(),
            ) {

                SearchBarTextFiled(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .focusRequester(focusRequester),
                    value = searchQuery,
                    onValueChange = { query ->
                        searchQuery = query

                        allTask?.let { task ->
                            val searchedTask = task.filter { it.title.contains(searchQuery, true) }
                            sharedTaskViewModel.setTaskListValue(
                                list = searchedTask, isSearchTask = true
                            )
                        }

                    },
                    onSearchTap = {
                        keyboardController?.hide()
                    },
                    onFilterTap = {

                    })
                Spacer(modifier = Modifier.height(10.dp))
                if (resultTask.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_no_data_found_icon),
                            contentDescription = "no_data_found",
                            modifier = Modifier
                                .size(300.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(tasks.size) { index ->
                            TaskCardWidget(modifier = Modifier.padding(
                                vertical = 2.5.dp, horizontal = 10.dp
                            ), taskModel = tasks[index], onMorePeopleTap = {

                            })
                        }
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalStdlibApi::class)
@Preview
@Composable
fun FilterTaskWidget() {
    Column(modifier = Modifier.fillMaxSize()) {

        val taskStatusList =
            listOf("Completed", "In Progress", "In Review", "On Hold", "On Canceled")

        val taskStatusListState= listOf(
            TaskStatusModel("Completed",false),
            TaskStatusModel("In Progress",false),
            TaskStatusModel("In Review",false),
            TaskStatusModel("On Hold",false),
            TaskStatusModel("On Canceled",false),
        )

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Row(modifier = Modifier.weight(7f), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Filter",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontFamily = robotoFontFamily
                    )
                )
            }
            TextButton(onClick = {

            }) {
                Text(
                    text = "Done",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontFamily = robotoFontFamily
                    )
                )
            }
        }
        Text(
            text = "Task Status",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.White,
                fontFamily = robotoFontFamily
            ), modifier = Modifier.padding(horizontal = 10.dp)
        )
        repeat(taskStatusList.size) {
            TaskStatusItemWidget(taskStatusModel = taskStatusListState[it])
        }
    }
}

@Composable
fun TaskStatusItemWidget(taskStatusModel: TaskStatusModel) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = taskStatusModel.isSelected,
            onCheckedChange = {

            },
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = taskStatusModel.title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontFamily = robotoFontFamily
            )
        )
    }
}
