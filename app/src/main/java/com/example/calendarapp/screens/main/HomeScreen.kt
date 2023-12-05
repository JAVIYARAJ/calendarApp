package com.example.calendarapp.screens.main

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.ExtensionFunction.Companion.postPercentage
import com.example.calendarapp.util.UiConstant.novaFontFamily
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import com.example.calendarapp.util.UiConstant.rubikBubblesFontFamily
import com.example.calendarapp.util.UiConstant.widthModifier

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
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
                DayCard(
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

    val taskGroupList= listOf(
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

    val horizontalMargin = 10.dp
    val verticalMargin = 15.dp

    Column(modifier = Modifier.fillMaxSize()) {
        TopHomeHeadline()
        ProgressCard()
        Row(modifier = widthModifier.padding(horizontal = 10.dp, vertical = 5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Task Groups",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primaryContainer,
            )
            TextButton(onClick = {
                controller.navigate(Routes.HomeTaskGroupRoutes.route)
            }) {
                Text(
                    text = "view more",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
            }
        }
        repeat(taskGroupList.size) {
            TaskGroupCard(taskGroupList[it])
        }
    }
}

@Preview
@Composable
fun TopHomeHeadline() {
    Row(
        modifier = widthModifier.padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_user_icon),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .clip(
                    CircleShape
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(modifier = Modifier.weight(5f)) {
            Text(
                text = "Hello! Good Morning",
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFontFamily),
                color = MaterialTheme.colorScheme.primaryContainer
            )
            Text(
                text = "javiya raj", style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = rubikBubblesFontFamily, fontSize = 27.sp
                ), color = MaterialTheme.colorScheme.primaryContainer
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "notification_icon",
                modifier = Modifier
                    .weight(1f)
                    .size(30.dp)
            )
        }
    }
}

@Preview
@Composable
fun ProgressCard() {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp)
        ) {
            val (progressTitleKey, viewTaskLabelKey, progressBarKey) = createRefs()
            Text(
                text = "Your today's task almost done!",
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFontFamily),
                modifier = Modifier.constrainAs(progressTitleKey) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }, color = MaterialTheme.colorScheme.primaryContainer
            )
            ElevatedButton(
                onClick = {},
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.constrainAs(viewTaskLabelKey) {

                    bottom.linkTo(parent.bottom)
                }
            ) {
                Text(
                    text = "View Task",
                    style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFontFamily),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }

            CustomProgressBar(
                percentage = 0.9f,
                number = 100,
                modifier = Modifier.constrainAs(progressBarKey) {
                    end.linkTo(parent.end, margin = 15.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                textSize = 15.sp
            )

        }
    }
}


@Composable
fun CustomProgressBar(
    modifier: Modifier = Modifier,
    percentage: Float,
    number: Int,
    color: Color = Color(0xFF6495ED),
    animationDuration: Int = 1800,
    animationDelay: Int = 0,
    strokeWidth: Dp = 10.dp,
    radius: Dp = 40.dp,
    textSize: TextUnit = 10.sp
) {
    var animationStatus by remember {
        mutableStateOf(false)
    }

    val animation = animateFloatAsState(
        targetValue = if (animationStatus) percentage else 0f,
        animationSpec = tween(durationMillis = animationDuration, delayMillis = animationDelay),
        label = ""
    )

    LaunchedEffect(key1 = true) {
        animationStatus = true
    }

    Box(modifier = modifier.size(radius * 2f), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = color,
                startAngle = -90f, //top minus axis 90 degree angle
                sweepAngle = 360 * animation.value,//whole angle*value
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (animation.value * number).toInt().toString().postPercentage(),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = textSize),
            color = MaterialTheme.colorScheme.primaryContainer
        )
    }
}


@Composable
fun TaskGroupCard(taskGroupItem:TaskGroupItem) {
    Surface(
        modifier = widthModifier
            .height(80.dp)
            .then(Modifier.padding(horizontal = 10.dp, vertical = 5.dp)),
        shape = RoundedCornerShape(10.dp),
        color = if(isSystemInDarkTheme()) taskGroupItem.taskGridColor else taskGroupItem.taskGridColor.copy(alpha = 0.5f)
    ) {
        Row(
            modifier = widthModifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "#${taskGroupItem.id}", style = MaterialTheme.typography.headlineMedium.copy(fontFamily = robotoFontFamily))
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(5f)) {
                Text(
                    text = taskGroupItem.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = novaFontFamily
                    ),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Text(
                    text = "${taskGroupItem.taskSize} Tasks",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = robotoFontFamily,
                    ),
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
            }
            CustomProgressBar(percentage = (taskGroupItem.taskSize.toFloat()-taskGroupItem.completedTaskSize.toFloat())/100, number = taskGroupItem.taskSize, radius = 20.dp, strokeWidth = 5.dp)
        }
    }
}


data class TaskGroupItem(
    val id:String,
    val title: String,
    val taskSize: Int,
    val completedTaskSize: Int,
    val taskGroupIcon: Int = R.drawable.ic_default_task_group_icon,
    val taskGridColor:Color=Color(0XF923E073)
)