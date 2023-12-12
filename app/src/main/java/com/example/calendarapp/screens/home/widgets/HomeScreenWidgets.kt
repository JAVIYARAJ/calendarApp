package com.example.calendarapp.screens.home.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.calendarapp.R
import com.example.calendarapp.screens.auth.model.UserModel
import com.example.calendarapp.screens.common.model.TaskGroupItem
import com.example.calendarapp.screens.common.widgets.CustomProgressBar
import com.example.calendarapp.screens.task.model.TaskCategoryModel
import com.example.calendarapp.screens.task.model.TaskStatus
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant
import com.example.calendarapp.util.Util

@Composable
fun HomeProgressCardWidget(progressRate: Float) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
    ) {

        val message = Util.greetMessage(progressRate)

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp)
        ) {
            val (progressTitleKey, viewTaskLabelKey, progressBarKey) = createRefs()
            Text(
                text = message,
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = UiConstant.robotoFontFamily),
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
                    style = MaterialTheme.typography.titleMedium.copy(fontFamily = UiConstant.robotoFontFamily),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }

            CustomProgressBar(
                percentage = progressRate,
                number = 100,
                modifier = Modifier.constrainAs(progressBarKey) {
                    top.linkTo(progressTitleKey.bottom, 20.dp)
                    end.linkTo(parent.end, margin = 15.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                },
                textSize = 15.sp
            )

        }
    }
}


@Composable
fun HomeTopBarWidget(userModel: UserModel, onTap: () -> Unit) {
    Row(
        modifier = UiConstant.widthModifier.padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = userModel.image, contentDescription = "user_image", modifier = Modifier
                .size(50.dp)
                .clip(
                    CircleShape
                )
                .clickable { onTap.invoke() },
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(modifier = Modifier.weight(5f)) {
            Text(
                text = Util.getGreetingMessage(),
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = UiConstant.robotoFontFamily),
                color = MaterialTheme.colorScheme.primaryContainer
            )
            Text(
                text = "${userModel.firstname} ${userModel.lastname}",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = UiConstant.rubikBubblesFontFamily, fontSize = 27.sp
                ),
                color = MaterialTheme.colorScheme.primaryContainer
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


@Composable
fun TaskGroupCardWidget(index:Int,taskCategoryModel: TaskCategoryModel) {
    Surface(
        modifier = UiConstant.widthModifier
            .height(80.dp)
            .then(Modifier.padding(horizontal = 10.dp, vertical = 5.dp)),
        shape = RoundedCornerShape(10.dp),
        color = taskCategoryModel.color
    ) {
        Row(
            modifier = UiConstant.widthModifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "#$index",
                style = MaterialTheme.typography.headlineMedium.copy(fontFamily = UiConstant.robotoFontFamily)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(5f)) {
                Text(
                    text = taskCategoryModel.categoryName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = UiConstant.novaFontFamily
                    ),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Text(
                    text = "${taskCategoryModel.taskList.size} Tasks",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = UiConstant.robotoFontFamily,
                    ),
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
            }
            Log.e("TAG", "TaskGroupCardWidget: ${(taskCategoryModel.taskList.size-taskCategoryModel.taskList.filter { it.taskStatus==TaskStatus.COMPLETED }.size)/taskCategoryModel.taskList.size}", )
            CustomProgressBar(
                percentage = 0.5f,
                number =taskCategoryModel.taskList.size,
                radius = 20.dp,
                strokeWidth = 5.dp
            )
        }
    }
}

@Composable
fun HomeTaskStatusCardWidget(title: String, value: String, modifier: Modifier, color: Color) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = if (isSystemInDarkTheme()) color.copy(alpha = 0.8f) else color.copy(
                    alpha = 0.7f
                )
            )
            .padding(10.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primaryContainer,
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = UiConstant.rubikBubblesFontFamily
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
