package com.example.calendarapp.screens.task

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calendarapp.screens.common.widgets.CustomScreenTopNavBar
import com.example.calendarapp.screens.common.widgets.UserInput
import com.example.calendarapp.ui.theme.completedTaskColor
import com.example.calendarapp.ui.theme.inProgressTaskColor
import com.example.calendarapp.ui.theme.inReviewTaskColor
import com.example.calendarapp.ui.theme.onCancelTaskColor
import com.example.calendarapp.ui.theme.onHoldTaskColor
import com.example.calendarapp.util.UiConstant.robotoFontFamily

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CreateTaskScreen() {

    val categories = mutableStateListOf(
        CategoryItemModel(
            id = "1",
            item = "Android Development",
            color = completedTaskColor,
            isSelected = true
        ),
        CategoryItemModel(
            id = "2",
            item = "Web Development with boostrap",
            color = inProgressTaskColor
        ),
        CategoryItemModel(id = "3", item = "Blockchain", color = inReviewTaskColor),
        CategoryItemModel(
            id = "4",
            item = "DSA",
            color = onHoldTaskColor,
        ),
        CategoryItemModel(
            id = "5",
            item = "Flutter",
            color = onCancelTaskColor,
        ),
        CategoryItemModel(id = "6", item = "Google Auth", color = completedTaskColor),
        CategoryItemModel(id = "7", item = "KMM", color = inReviewTaskColor),
        CategoryItemModel(id = "8", item = "Jetpack compose", color = onCancelTaskColor)
    )

    val categories1 by remember {
        mutableStateOf(
            listOf(
                CategoryItemModel(
                    id = "1",
                    item = "Android Development",
                    color = completedTaskColor
                ),
                CategoryItemModel(
                    id = "2",
                    item = "Web Development with boostrap",
                    color = inProgressTaskColor
                ),
                CategoryItemModel(id = "3", item = "Blockchain", color = inReviewTaskColor),
                CategoryItemModel(
                    id = "4",
                    item = "DSA",
                    color = onHoldTaskColor,
                ),
                CategoryItemModel(
                    id = "5",
                    item = "Flutter",
                    color = onCancelTaskColor,
                ),
                CategoryItemModel(id = "6", item = "Google Auth", color = completedTaskColor),
                CategoryItemModel(id = "7", item = "KMM", color = inReviewTaskColor),
                CategoryItemModel(id = "8", item = "Jetpack compose", color = onCancelTaskColor)
            )
        )
    }

    Scaffold(topBar = {
        CustomScreenTopNavBar(title = "Create Task", onBackClick = {})
    }) {

        var taskTitle by remember {
            mutableStateOf("")
        }

        var taskDescription by remember {
            mutableStateOf("")
        }


        var startDate by remember {
            mutableStateOf("")
        }

        var endDate by remember {
            mutableStateOf("")
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            UserInput(
                value = taskTitle,
                onValueChange = { title ->
                    taskTitle = title
                },
                hint = "Task Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp).then(Modifier.padding(top = 20.dp)),
                imeActionCallBack = {

                }, imeAction = ImeAction.Done
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Select task category",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = robotoFontFamily
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(100.dp),
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                items(categories) { category ->
                    CategoryCardDesign(category, onTap = {

                    })
                }

            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserInput(
                    value = startDate,
                    onValueChange = { startDateValue ->
                        startDate = startDateValue
                    },
                    hint = "Start date",
                    modifier = Modifier
                        .weight(5f)
                        .padding(horizontal = 5.dp),
                    keyboardType = KeyboardType.Text,
                    imeActionCallBack = {

                    }, imeAction = ImeAction.Done
                )

                UserInput(
                    value = endDate,
                    onValueChange = { endDateValue ->
                        endDate = endDateValue
                    },
                    hint = "End Date",
                    modifier = Modifier
                        .weight(5f)
                        .padding(horizontal = 5.dp),
                    imeActionCallBack = {

                    }, imeAction = ImeAction.Done
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Select Participants",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = robotoFontFamily
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TaskParticipateSelectionCard(modifier = Modifier.padding(start = 20.dp))
            Spacer(modifier = Modifier.height(15.dp))
        }
    }

}

data class CategoryItemModel(
    val id: String,
    val item: String,
    val color: Color,
    var isSelected: Boolean = false
)

@Composable
fun CategoryCardDesign(category: CategoryItemModel, onTap: () -> Unit) {

    Box(
        modifier = Modifier
            .padding(horizontal = 3.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = category.color.copy(alpha = 0.7f))
            .clickable { onTap.invoke() }
            .border(
                2.dp,
                color = if (category.isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = category.item,
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primaryContainer),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 3.dp, vertical = 5.dp)
        )
    }
}


@Composable
fun TaskParticipateSelectionCard(modifier: Modifier) {
    Box(
        modifier = modifier
            .size(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer) ,contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = {

        }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add_people", tint = MaterialTheme.colorScheme.background
            )
        }
    }
}