package com.example.calendarapp.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.calendarapp.screens.auth.model.UserModel
import com.example.calendarapp.screens.common.widgets.CustomScreenTopNavBar
import com.example.calendarapp.screens.task.model.TaskModel
import com.example.calendarapp.screens.task.viewmodel.SharedTaskViewModel
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.ExtensionFunction.Companion.convertIntoColor
import com.example.calendarapp.util.UiConstant.robotoFontFamily

@Composable
fun TaskScreen(
    controller: NavHostController,
    category: String?,
    sharedTaskViewModel: SharedTaskViewModel
) {


    Scaffold(topBar = {
        CustomScreenTopNavBar(title = "$category group", onBackClick = {
            controller.popBackStack()
        })
    }) { padding ->
        sharedTaskViewModel.getTaskListValue()?.let { tasks ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .then(Modifier.padding(padding))
            ) {
                items(tasks.size) { index ->
                    TaskItem(taskModel = tasks[index],sharedTaskViewModel.getCategoryColor())
                }
            }

        }
    }
}

@Composable
fun TaskItem(taskModel: TaskModel,color: Color) {
    Surface(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(10.dp)) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color =color
                )
        ) {
            val (taskTitle, taskDescriptionKey, peopleKey, createdByKey, locationKey,dateAndLocationKey) = createRefs()

            Box(modifier = Modifier
                .constrainAs(createdByKey) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .clip(RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp))
                .background(color = if (isSystemInDarkTheme()) primaryLightColor else primaryDarkColor)) {
                Text(
                    text = "${taskModel.createdBy.firstname} ${taskModel.createdBy.lastname}",
                    style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }

            Text(text = taskModel.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = robotoFontFamily
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(taskTitle) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(createdByKey.bottom)
                    }
                    .padding(horizontal = 10.dp),
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Text(text = taskModel.description,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = robotoFontFamily
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(taskDescriptionKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(taskTitle.bottom, 10.dp)
                    }
                    .padding(horizontal = 10.dp),
                textAlign = TextAlign.Start,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis)


            Row(modifier = Modifier.fillMaxWidth().constrainAs(dateAndLocationKey){
                start.linkTo(parent.start)
                top.linkTo(taskDescriptionKey.bottom, 10.dp)
            }.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                taskModel.location?.let {
                    Row(modifier = Modifier.weight(0.5f),verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "location_icon",
                            tint = MaterialTheme.colorScheme.primaryContainer
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text =it,
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                fontFamily = robotoFontFamily
                            ),
                            modifier = Modifier, textAlign = TextAlign.Start,
                        )
                    }
                }
                Row(modifier = Modifier.weight(0.6f),verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccessTimeFilled,
                        contentDescription = "location_icon",
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = taskModel.startDate,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            fontFamily = robotoFontFamily
                        ),
                        modifier = Modifier, textAlign = TextAlign.Start,
                    )
                }
            }


            TaskAssignPeopleListWidget(modifier = Modifier
                .constrainAs(peopleKey) {
                    top.linkTo(dateAndLocationKey.bottom, 10.dp)
                    start.linkTo(parent.start, 5.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                }
                .padding(horizontal = 5.dp), peopleList = taskModel.assignTo)


        }
    }
}

@Composable
fun TaskAssignPeopleListWidget(modifier: Modifier, peopleList: List<UserModel>) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.End) {

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
                fontFamily = robotoFontFamily, color = MaterialTheme.colorScheme.primaryContainer
            ), textAlign = TextAlign.Center
        )
    }
}