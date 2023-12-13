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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.screens.common.widgets.CustomScreenTopNavBar
import com.example.calendarapp.screens.task.model.TaskStatus
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

    val filterModalSheetVisible by remember {
        mutableStateOf(false)
    }

    val filterModalSheetState = rememberModalBottomSheetState()


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

        repeat(taskStatusList.size) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = true,
                    onCheckedChange = {
                    },
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = taskStatusList[it],
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontFamily = robotoFontFamily
                    )
                )
            }
        }
    }
}

