package com.example.calendarapp.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calendarapp.R
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant.robotoFontFamily

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp)
    ) {
        items(10) {
            TaskItem()
        }
    }
}

@Preview
@Composable
fun TaskItem() {
    Surface(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(10.dp)) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            val (leftCardDesignKey, rightSideLayoutKey) = createRefs()

            Surface(
                modifier = Modifier
                    .width(10.dp)
                    .fillMaxHeight()
                    .constrainAs(leftCardDesignKey) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(rightSideLayoutKey.start)
                        bottom.linkTo(parent.bottom)
                    },
                shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
            ) {}

            ConstraintLayout(modifier = Modifier
                .fillMaxSize()
                .constrainAs(rightSideLayoutKey) {
                    start.linkTo(leftCardDesignKey.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .background(
                    color = if (isSystemInDarkTheme()) primaryDarkColor.copy(alpha = 0.6f) else primaryLightColor.copy(
                        alpha = 0.6f
                    )
                )
                .padding(10.dp))
            {
                val (taskTitle, taskTimeRowKey, locationRowKey, optionMenuKey, taskDescriptionKey, dividerKey, peopleKey) = createRefs()

                Text(
                    text = "Learn new android concept Learn new android concept",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontFamily = robotoFontFamily
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(taskTitle) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }, textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Learn new android concept",
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
                        }, textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(locationRowKey) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(taskDescriptionKey.bottom, 15.dp)
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location_icon",
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "City Center 2",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            fontFamily = robotoFontFamily
                        ),
                        modifier = Modifier, textAlign = TextAlign.Start,
                    )
                }

                Divider(
                    modifier = Modifier.constrainAs(dividerKey) {
                        start.linkTo(parent.start, 20.dp)
                        end.linkTo(parent.end, 20.dp)
                        top.linkTo(locationRowKey.bottom, 10.dp)
                    },
                    color = MaterialTheme.colorScheme.primaryContainer,
                    thickness = 2.dp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(taskTimeRowKey) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(locationRowKey.bottom, 25.dp)
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTimeFilled,
                        contentDescription = "location_icon",
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "7:10 AM-9:10 AM",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            fontFamily = robotoFontFamily
                        ),
                        modifier = Modifier, textAlign = TextAlign.Start,
                    )
                }

                val listOfPeople = listOf(
                    R.drawable.ic_google_icon,
                    R.drawable.ic_facebook_icon,
                    R.drawable.ic_user_icon,
                    R.drawable.ic_user_icon,
                    R.drawable.ic_user_icon
                )

                TasKPeopleUi(
                    modifier = Modifier
                        .constrainAs(peopleKey) {
                            end.linkTo(parent.end)
                            top.linkTo(taskTimeRowKey.bottom, 10.dp)
                        }, peopleList = listOfPeople
                )

            }
        }
    }
}


@Composable
fun TasKPeopleUi(modifier: Modifier, peopleList: List<Int>) {
    Row(modifier = modifier) {

        val listSize = peopleList.size

        val defaultPeopleSize = 3
        if (listSize > 0) {
            if (listSize > defaultPeopleSize) {

                repeat(defaultPeopleSize + 1) { index ->
                    val overlapPercentage = 0.2f
                    val overlapOffset = (40.dp * overlapPercentage * index)

                    if (index == defaultPeopleSize) {
                        MorePeopleUi(
                            modifier = Modifier.offset(-overlapOffset),
                            value = listSize - defaultPeopleSize,
                            onTap = {

                            })
                    } else {
                        Image(
                            painter = painterResource(id = peopleList[index]),
                            contentDescription = "user",
                            modifier = Modifier
                                .size(40.dp)
                                .offset(x = -overlapOffset)
                                .clip(CircleShape), // Ensure proper layering/ordering of images
                            contentScale = ContentScale.Crop
                        )
                    }
                }

            } else {
                repeat(listSize) { index ->
                    val overlapPercentage = 0.2f
                    val overlapOffset = (40.dp * overlapPercentage * index)

                    Image(
                        painter = painterResource(id = peopleList[index]),
                        contentDescription = "user",
                        modifier = Modifier
                            .size(40.dp)
                            .offset(x = -overlapOffset)
                            .clip(CircleShape)
                            .zIndex(index.toFloat()), // Ensure proper layering/ordering of images
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun MorePeopleUi(modifier: Modifier, value: Int, onTap: () -> Unit) {

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
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "+${value}",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = robotoFontFamily,
                color = MaterialTheme.colorScheme.primaryContainer
            ),
            textAlign = TextAlign.Center
        )
    }
}