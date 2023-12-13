package com.example.calendarapp.screens.task.widgets

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.calendarapp.screens.auth.model.UserModel
import com.example.calendarapp.screens.task.model.TaskCategoryModel
import com.example.calendarapp.screens.task.model.TaskModel
import com.example.calendarapp.screens.task.model.TaskStatus
import com.example.calendarapp.ui.theme.completedTaskColor
import com.example.calendarapp.ui.theme.inProgressTaskColor
import com.example.calendarapp.ui.theme.inReviewTaskColor
import com.example.calendarapp.ui.theme.onHoldTaskColor
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.ExtensionFunction.Companion.convertIntoColor
import com.example.calendarapp.util.UiConstant
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import com.example.calendarapp.util.Util
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

//task group screen widgets

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskGroupCardWidget(
    categoryModel: TaskCategoryModel,
    onTap: (TaskCategoryModel) -> Unit,
    onColorChange: (Color) -> Unit
) {
    val listOfTaskStatus = listOf("In Progress", "In Review", "On Hold", "Canceled")

    val taskList = categoryModel.taskList

    val listOfTaskProgressData = listOf(
        taskList.filter { it.taskStatus == TaskStatus.IN_PROGRESS }.size.toString(),
        taskList.filter { it.taskStatus == TaskStatus.IN_REVIEW }.size.toString(),
        taskList.filter { it.taskStatus == TaskStatus.ON_HOLD }.size.toString(),
        taskList.filter { it.taskStatus == TaskStatus.ON_CANCELED }.size.toString()
    )

    val selectedTaskGroupColor by remember {
        mutableStateOf(primaryLightColor)
    }

    var isColorPickerDialogShow by remember {
        mutableStateOf(false)
    }

    var isDropDownVisible by remember {
        mutableStateOf(false)
    }

    if (isColorPickerDialogShow) {
        ColorPickerDialogWidget(onDismiss = {
            isColorPickerDialogShow = false
        }, onConfirm = {
            onColorChange.invoke(it)
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
                    )
                    .combinedClickable(
                        onClick = {
                            onTap.invoke(categoryModel)
                        }, onLongClick = {
                            isColorPickerDialogShow = true
                        }
                    )

            ) {

                val (topCardDesignKey, titleKey, completedLabelKey, completedValueKey, moreIconKey, dropDownKey) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .constrainAs(topCardDesignKey) {
                        },
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    color = categoryModel.color
                ) {

                }
                Text(
                    text = categoryModel.categoryName,
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.constrainAs(titleKey) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(topCardDesignKey.bottom, 20.dp)
                    })

                IconButton(
                    onClick = { isDropDownVisible = !isDropDownVisible },
                    modifier = Modifier.constrainAs(moreIconKey) {
                        end.linkTo(parent.end, 10.dp)
                        top.linkTo(parent.top)
                    }) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                }

                Text(
                    text = "Completed",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontFamily = robotoFontFamily
                    ),
                    modifier = Modifier.constrainAs(completedLabelKey) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(titleKey.bottom, 10.dp)
                    })
                Text(
                    text = taskList.filter { it.taskStatus == TaskStatus.COMPLETED }.size.toString(),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.primaryContainer,
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
                                color = MaterialTheme.colorScheme.primaryContainer,
                                fontFamily = robotoFontFamily
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
                            text = listOfTaskProgressData[it],
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = MaterialTheme.colorScheme.primaryContainer,
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
                    controller = colorPickerController, initialColor = defaultColor
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

//task screen widgets

@Composable
fun TaskAssignPeopleListWidget(modifier: Modifier, peopleList: List<UserModel>) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Start) {

        val listSize = peopleList.size

        val defaultPeopleSize = 3

        val peopleListSize =
            if (peopleList.size > defaultPeopleSize) defaultPeopleSize + 1 else peopleList.size

        repeat(peopleListSize) { index ->
            val overlapPercentage = 0.2f
            val overlapOffset = (40.dp * overlapPercentage * index)

            if (peopleList.size > defaultPeopleSize && peopleListSize == index + 1) {
                TaskAssignToPeopleMoreWidget(modifier = Modifier.offset(-overlapOffset),
                    value = listSize - defaultPeopleSize,
                    onTap = {

                    })
            } else {
                AsyncImage(
                    model = peopleList[index].image,
                    contentDescription = "user",
                    modifier = Modifier
                        .size(40.dp)
                        .offset(x = -overlapOffset)
                        .clip(CircleShape), // Ensure proper layering/ordering of images
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun TaskAssignToPeopleMoreWidget(modifier: Modifier, value: Int, onTap: () -> Unit) {

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(
                color = if (isSystemInDarkTheme()) primaryDarkColor.copy(alpha = 0.6f) else primaryLightColor.copy(
                    alpha = 0.8f
                )
            )
            .clickable {
                onTap.invoke()
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = "+${value}", style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = robotoFontFamily, color = Color.White
            ), textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TaskCardWidget(modifier: Modifier, taskModel: TaskModel, onMorePeopleTap: () -> Unit) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = primaryDarkColor), contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = taskModel.title,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                    fontFamily = robotoFontFamily,
                    modifier = Modifier.weight(8f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = {

                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "more",
                        tint = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(2.5f), verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "Status",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color.White,
                            fontFamily = UiConstant.novaFontFamily,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(id = Util.getTaskStatusIcon(taskModel.taskStatus)),
                            contentDescription = "",
                            Modifier
                                .size(25.dp)
                                .clip(
                                    CircleShape
                                ),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = Util.convertTaskStatus(taskModel.taskStatus),
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = Color.White,
                                fontFamily = robotoFontFamily
                            )
                        )
                    }
                }
                Column(modifier = Modifier.weight(2.5f), verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "Priority",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color.White,
                            fontFamily = UiConstant.novaFontFamily,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(color = Color.White)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Icon(
                            imageVector = Icons.Default.AutoGraph,
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Red
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = Util.convertTaskPriority(taskModel.taskPriorityTAG),
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = Color.Red,
                                fontFamily = robotoFontFamily
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Column(modifier = Modifier.weight(3f), verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "Timeline",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color.White,
                            fontFamily = UiConstant.novaFontFamily,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTimeFilled,
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "06:10-08:10 AM",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = Color.White,
                                fontFamily = robotoFontFamily
                            )
                        )
                    }
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                TaskAssignPeopleListWidget(
                    modifier = Modifier.weight(5f),
                    peopleList = taskModel.assignTo
                )
            }
        }


    }
}

@Composable
fun SearchBarTextFiled(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onSearchTap: () -> Unit,
    onFilterTap: () -> Unit
) {
    TextField(
        modifier = modifier, value = value, onValueChange = onValueChange, leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search_icon")
        }, trailingIcon = {
            IconButton(onClick = { onFilterTap.invoke() }) {
                Icon(imageVector = Icons.Default.FilterAlt, contentDescription = "filter_icon")
            }
        }, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
        ), keyboardActions = KeyboardActions(onSearch = {
            onSearchTap.invoke()
        }), colors = TextFieldDefaults.colors(
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ), shape = RoundedCornerShape(10.dp)
    )
}