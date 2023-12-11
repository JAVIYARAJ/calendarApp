package com.example.calendarapp.screens.task.widgets

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskGroupCardWidget(categoryModel: TaskCategoryModel, onTap: (TaskCategoryModel) -> Unit) {
    val listOfTaskStatus = listOf("In Progress", "In Review", "On Hold", "Canceled")

    val optionMenuList = listOf("Edit", "Delete")

    val taskStatus = categoryModel.taskCategoryStatus

    val listOfTaskProgressData = listOf(
        taskStatus.inProgressTask,
        taskStatus.inReviewTask,
        taskStatus.onHoldTask,
        taskStatus.canceledTask
    )

    var selectedTaskGroupColor by remember {
        mutableStateOf(primaryLightColor)
    }

    val cardColor = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor

    var isColorPickerDialogShow by remember {
        mutableStateOf(false)
    }

    var isOptionMenuVisible by remember {
        mutableStateOf(false)
    }

    if (isColorPickerDialogShow) {
        ColorPickerDialogWidget(onDismiss = {
            isColorPickerDialogShow = false
        }, onConfirm = {
            isColorPickerDialogShow = false
        }, defaultColor = selectedTaskGroupColor)
    }

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
                    )
                    .combinedClickable(
                        onClick = {
                            onTap.invoke(categoryModel)
                        }, onLongClick = {
                            isColorPickerDialogShow = true
                            selectedTaskGroupColor = categoryModel.color.convertIntoColor()
                        }
                    )

            ) {

                val (topCardDesignKey, titleKey, completedLabelKey, completedValueKey, moreIconKey, optionDropDownMenuKey) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .constrainAs(topCardDesignKey) {
                        },
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    color = categoryModel.color.convertIntoColor()
                ) {

                }
                Text(
                    text = categoryModel.categoryName,
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.background),
                    modifier = Modifier.constrainAs(titleKey) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(topCardDesignKey.bottom, 20.dp)
                    })

                IconButton(
                    onClick = { isOptionMenuVisible = true },
                    modifier = Modifier.constrainAs(moreIconKey) {
                        end.linkTo(parent.end, 10.dp)
                        top.linkTo(parent.top)
                    }) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.background
                    )
                }
                /*
                DropdownMenu(expanded = isOptionMenuVisible, onDismissRequest = { isOptionMenuVisible=false }, modifier = Modifier.constrainAs(optionDropDownMenuKey){
                   start.linkTo(titleKey.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }) {
                    optionMenuList.forEach {
                        DropdownMenuItem(text = { Text(text = it)}, onClick = { isOptionMenuVisible=false })
                    }
                }

                 */

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


@Composable
fun ColorPickerDialogWidget(
    onDismiss: () -> Unit, onConfirm: (Color) -> Unit, defaultColor: Color
) {
    val colorPickerController = rememberColorPickerController()

    Dialog(onDismissRequest = {
        onDismiss.invoke()
    }) {
        Card(
            shape = RoundedCornerShape(10.dp), colors = CardDefaults.cardColors(
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Color Picker Dialog",
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = robotoFontFamily)
                )
                Spacer(modifier = Modifier.height(10.dp))
                HsvColorPicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    controller = colorPickerController, onColorChanged = {

                    }, initialColor = defaultColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                AlphaTile(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    controller = colorPickerController
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = {
                        onDismiss.invoke()
                    }) {
                        Text(
                            text = "Dismiss",
                            style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFontFamily)
                        )
                    }
                    TextButton(onClick = {
                        onConfirm.invoke(colorPickerController.selectedColor.value)
                    }) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFontFamily)
                        )
                    }
                }

            }
        }
    }

}
