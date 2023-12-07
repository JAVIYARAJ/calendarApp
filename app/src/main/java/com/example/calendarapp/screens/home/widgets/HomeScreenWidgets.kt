package com.example.calendarapp.screens.home.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calendarapp.R
import com.example.calendarapp.screens.common.TaskGroupItem
import com.example.calendarapp.screens.common.widgets.CustomProgressBar
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant

@Preview
@Composable
fun HomeProgressCardWidget() {
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


@Preview
@Composable
fun HomeTopBarWidget() {
    Row(
        modifier = UiConstant.widthModifier.padding(horizontal = 10.dp, vertical = 10.dp),
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
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = UiConstant.robotoFontFamily),
                color = MaterialTheme.colorScheme.primaryContainer
            )
            Text(
                text = "javiya raj", style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = UiConstant.rubikBubblesFontFamily, fontSize = 27.sp
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


@Composable
fun TaskGroupCardWidget(taskGroupItem: TaskGroupItem) {
    Surface(
        modifier = UiConstant.widthModifier
            .height(80.dp)
            .then(Modifier.padding(horizontal = 10.dp, vertical = 5.dp)),
        shape = RoundedCornerShape(10.dp),
        color = if(isSystemInDarkTheme()) taskGroupItem.taskGridColor else taskGroupItem.taskGridColor.copy(alpha = 0.5f)
    ) {
        Row(
            modifier = UiConstant.widthModifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "#${taskGroupItem.id}", style = MaterialTheme.typography.headlineMedium.copy(fontFamily = UiConstant.robotoFontFamily))
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(5f)) {
                Text(
                    text = taskGroupItem.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = UiConstant.novaFontFamily
                    ),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Text(
                    text = "${taskGroupItem.taskSize} Tasks",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = UiConstant.robotoFontFamily,
                    ),
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
            }
            CustomProgressBar(percentage = (taskGroupItem.taskSize.toFloat()-taskGroupItem.completedTaskSize.toFloat())/100, number = taskGroupItem.taskSize, radius = 20.dp, strokeWidth = 5.dp)
        }
    }
}
