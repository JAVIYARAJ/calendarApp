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
import com.example.calendarapp.ui.theme.completedTaskColor
import com.example.calendarapp.ui.theme.inProgressTaskColor
import com.example.calendarapp.ui.theme.inReviewTaskColor
import com.example.calendarapp.ui.theme.onHoldTaskColor
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
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

@Preview
@Composable
fun TaskGroupCardWidget() {
    val listOfTaskStatus = listOf("In Progress", "In Review", "On Hold", "Canceled")

    val cardColor=if(isSystemInDarkTheme()) primaryDarkColor else primaryLightColor

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(color = cardColor.copy(alpha = 0.7f))
            ) {

                val (topCardDesignKey, titleKey, completedLabelKey, completedValueKey) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .constrainAs(topCardDesignKey) {
                        },
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    color =cardColor.copy(alpha = 0.9f)
                ) {

                }
                Text(
                    text = "Data Structure",
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.background),
                    modifier = Modifier.constrainAs(titleKey) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(topCardDesignKey.bottom, 20.dp)
                    })

                Text(
                    text = "Completed",
                    style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.background),
                    modifier = Modifier.constrainAs(completedLabelKey) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(titleKey.bottom, 10.dp)
                    })
                Text(
                    text = "105",
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
                    .fillMaxHeight(1f)
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {

                val (taskGroupStatusRowKey, taskGroupStatusIconKey, dividerKey, bottomCardRowKey) = createRefs()

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
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.background, fontFamily = UiConstant.robotoFontFamily),
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
                    repeat(listOfTaskStatus.size) {
                        Text(
                            text = it.toString(),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = MaterialTheme.colorScheme.background,
                                fontFamily = UiConstant.rubikBubblesFontFamily
                            ), modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Divider(modifier = Modifier
                    .constrainAs(dividerKey) {
                        start.linkTo(parent.start, 20.dp)
                        end.linkTo(parent.end, 20.dp)
                        top.linkTo(taskGroupStatusIconKey.bottom, 10.dp)
                    }
                    .padding(horizontal = 10.dp), color = MaterialTheme.colorScheme.background)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .constrainAs(bottomCardRowKey) {
                            start.linkTo(parent.start, 20.dp)
                            end.linkTo(parent.end, 20.dp)
                            top.linkTo(dividerKey.bottom, 10.dp)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "View Task",
                        style = MaterialTheme.typography.titleMedium.copy(fontFamily = UiConstant.robotoFontFamily),
                        color = MaterialTheme.colorScheme.background
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }

            }
        }
    }

}
