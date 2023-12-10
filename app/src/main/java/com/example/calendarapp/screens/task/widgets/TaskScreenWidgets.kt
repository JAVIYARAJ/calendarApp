package com.example.calendarapp.screens.task.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calendarapp.screens.task.TaskCategoryModel
import com.example.calendarapp.ui.theme.completedTaskColor
import com.example.calendarapp.ui.theme.inProgressTaskColor
import com.example.calendarapp.ui.theme.inReviewTaskColor
import com.example.calendarapp.ui.theme.onHoldTaskColor
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.ExtensionFunction.Companion.convertIntoColor
import com.example.calendarapp.util.UiConstant

@Composable
fun TaskProgressItemWidget() {
    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = completedTaskColor), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Completed 10",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.padding(3.dp)
                )
            }
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = inProgressTaskColor), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "In Progress 50",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.padding(3.dp)
                )
            }

        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = onHoldTaskColor), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "On Hold 100",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.padding(3.dp)
                )
            }
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = inReviewTaskColor), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "In Review 50",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}

@Composable
fun TaskGroupCardWidget(categoryModel: TaskCategoryModel) {
    val listOfTaskStatus = listOf("In Progress", "In Review", "On Hold", "Canceled")

    val optionMenuList= listOf("Edit","Delete")

    val taskStatus=categoryModel.taskCategoryStatus

    val listOfTaskProgressData= listOf(taskStatus.inProgressTask,taskStatus.inReviewTask,taskStatus.onHoldTask,taskStatus.canceledTask)

    val cardColor = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = categoryModel.color
                            .convertIntoColor()
                            .copy(alpha = 0.9f)
                    )
            ) {

                val (topCardDesignKey, titleKey, completedLabelKey, completedValueKey, moreIconKey,optionMenuKey) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .constrainAs(topCardDesignKey) {
                        },
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    color = categoryModel.color.convertIntoColor().copy(alpha = 0.9f)
                ) {

                }
                Text(
                    text = categoryModel.categoryName,
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.background),
                    modifier = Modifier.constrainAs(titleKey) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(topCardDesignKey.bottom, 20.dp)
                    })

                IconButton(onClick = {}, modifier = Modifier.constrainAs(moreIconKey) {
                    end.linkTo(parent.end,10.dp)
                    top.linkTo(parent.top)
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.background
                    )
                }

                Text(
                    text = "Completed",
                    style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.background),
                    modifier = Modifier.constrainAs(completedLabelKey) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(titleKey.bottom, 10.dp)
                    })
                Text(
                    text = categoryModel.taskCategoryStatus.completedTask.toString(),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.background,
                        fontFamily = UiConstant.rubikBubblesFontFamily
                    ),
                    modifier = Modifier.constrainAs(completedValueKey) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(completedLabelKey.bottom, 5.dp)
                    }, textAlign = TextAlign.Center
                )
                /*
                DropdownMenu(expanded = true, onDismissRequest = {  }, modifier = Modifier.constrainAs(optionMenuKey){
                    end.linkTo(parent.end,20.dp)
                    top.linkTo(moreIconKey.bottom,5.dp)
                }) {
                    optionMenuList.forEach {
                        DropdownMenuItem(text = { Text(text = it)}, onClick = {  })
                    }
                }

                 */
            }
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {

                val (taskGroupStatusRowKey, taskGroupStatusIconKey) = createRefs()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(taskGroupStatusRowKey) {
                            start.linkTo(parent.start, 20.dp)
                            top.linkTo(parent.top, 20.dp)
                            end.linkTo(parent.end, 20.dp)
                        }, horizontalArrangement = Arrangement.SpaceAround
                ) {
                    repeat(listOfTaskStatus.size) {
                        Text(
                            text = listOfTaskStatus[it],
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.background,
                                fontFamily = UiConstant.robotoFontFamily
                            ),
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(taskGroupStatusIconKey) {
                            start.linkTo(parent.start, 20.dp)
                            top.linkTo(taskGroupStatusRowKey.bottom, 10.dp)
                            end.linkTo(parent.end, 20.dp)
                        }, horizontalArrangement = Arrangement.SpaceAround
                ) {
                    repeat(listOfTaskProgressData.size) {
                        Text(
                            text = listOfTaskProgressData[it].toString(),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = MaterialTheme.colorScheme.background,
                                fontFamily = UiConstant.rubikBubblesFontFamily
                            ), modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }
    }

}
