package com.example.calendarapp.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.components.CustomScreenTopNavBar
import com.example.calendarapp.ui.theme.completedTaskColor
import com.example.calendarapp.ui.theme.inProgressTaskColor
import com.example.calendarapp.ui.theme.inReviewTaskColor
import com.example.calendarapp.ui.theme.onHoldTaskColor
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import com.example.calendarapp.util.UiConstant.rubikBubblesFontFamily
import com.example.calendarapp.util.UiConstant.widthModifier

@Composable
fun TaskGroupScreen(controller: NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            CustomScreenTopNavBar(title = "Task Groups", onBackClick = {
                controller.popBackStack()
            })
        }
        items(10) {
            TaskGroupV1()
        }
    }
}

@Preview
@Composable
fun TaskGroupCard() {
    Surface(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(3.5.dp)) {
        ConstraintLayout(
            modifier = Modifier
                .padding(15.dp)
        ) {

            val (taskGroupIconKey, taskGroupTitleKey, taskTitle, taskTimeStampKey, progressBarKey, progressBarRow, taskStatusGridKey) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_user_icon),
                contentDescription = "task_group_icon",
                Modifier
                    .size(40.dp)
                    .clip(
                        CircleShape
                    )
                    .constrainAs(taskGroupIconKey) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    },
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Data Structure",
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.constrainAs(taskGroupTitleKey) {
                    start.linkTo(taskGroupIconKey.end, margin = 10.dp)
                    top.linkTo(taskGroupIconKey.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(taskGroupIconKey.bottom)
                },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(taskStatusGridKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(taskGroupTitleKey.bottom, margin = 10.dp)
                }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(color = completedTaskColor), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Completed 10",
                        style = MaterialTheme.typography.bodySmall.copy(color = androidx.compose.ui.graphics.Color.White),
                        modifier = Modifier.padding(3.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(color = inProgressTaskColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "In Progress 50",
                        style = MaterialTheme.typography.bodySmall.copy(color = androidx.compose.ui.graphics.Color.White),
                        modifier = Modifier.padding(3.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(color = onHoldTaskColor), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "On Hold 100",
                        style = MaterialTheme.typography.bodySmall.copy(color = androidx.compose.ui.graphics.Color.White),
                        modifier = Modifier.padding(3.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(color = inReviewTaskColor), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "In Review 50",
                        style = MaterialTheme.typography.bodySmall.copy(color = androidx.compose.ui.graphics.Color.White),
                        modifier = Modifier.padding(3.dp)
                    )
                }
            }

            Row(
                modifier = widthModifier.constrainAs(progressBarRow) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(taskStatusGridKey.bottom, margin = 10.dp)
                },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Progress",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Text(
                    text = "48%",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }
            LinearProgressIndicator(
                progress = 10f,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(progressBarKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(progressBarRow.bottom, 10.dp)
                    }
                    .clip(RoundedCornerShape(10.dp)),
            )
        }
    }
}


@Composable
fun TaskGroupStatusCard(modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
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
fun TaskGroupItem() {

    val listOfTaskStatus = listOf("Completed", "In Progress", "In Review", "In Hold", "Canceled")

    Surface(
        modifier = widthModifier
            .then(Modifier.padding(horizontal = 10.dp, vertical = 5.dp)),
        shape = RoundedCornerShape(10.dp),

        ) {
        Column(modifier = widthModifier.padding(horizontal = 10.dp)) {
            Row(
                modifier = widthModifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "#1", style = MaterialTheme.typography.headlineMedium.copy(fontFamily = robotoFontFamily))
                Spacer(modifier = Modifier.width(20.dp))
                Column(modifier = Modifier.weight(5f)) {
                    Text(
                        text = "Data Structure",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = UiConstant.novaFontFamily
                        ),
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                    Text(
                        text = "12 Tasks",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = UiConstant.robotoFontFamily,
                        ),
                        color = MaterialTheme.colorScheme.primaryContainer,
                    )
                }

            }

            Row(
                modifier = widthModifier.padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(5) {
                    StatusCard()
                }
            }
            Row(
                modifier = widthModifier.padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(listOfTaskStatus.size) {
                    Text(text = listOfTaskStatus[it], style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Preview
@Composable
fun StatusCard() {

    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = completedTaskColor), contentAlignment = Alignment.Center
    ) {
        Text(
            text = "12",
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.background,
                fontFamily = rubikBubblesFontFamily
            )
        )
    }
}

@Preview
@Composable
fun TaskGroupV1() {
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
                        fontFamily = rubikBubblesFontFamily
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
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.background, fontFamily = robotoFontFamily),
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
                                fontFamily = rubikBubblesFontFamily
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
                        style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFontFamily),
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

@Preview
@Composable
fun TaskItemDesign() {
    Surface(shape = RoundedCornerShape(10.dp)) {

    }
}