package com.example.calendarapp.screens.calendar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calendarapp.R
import com.example.calendarapp.components.DayCard
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.Constant
import com.example.calendarapp.util.Constant.Companion.DAYS_TITLE
import com.example.calendarapp.util.UiConstant
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import com.example.calendarapp.util.Util


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMonthScreen(yearValue: Int?, monthValue: Int?, onChange: () -> Unit) {
    MonthView(yearValue, monthValue)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun MonthView(yearValue: Int?, monthValue: Int?) {

    val listOfDaysTitle = DAYS_TITLE

    val currentMonth = monthValue ?: Util.getMonth()
    val currentYear = yearValue ?: Util.getYear()

    var currentDay = Util.getDay()

    var month by remember {
        mutableIntStateOf(currentMonth)
    }

    val totalDaysCount by remember {
        derivedStateOf {
            Util.getCurrentMonthDays(year = currentYear, month = month)
        }
    }

    var year by remember {
        mutableIntStateOf(currentYear)
    }
    val disableDayCount by remember {
        derivedStateOf {
            listOfDaysTitle.indexOf(Util.getFirstDayOfMonth(year, month))
        }
    }

    val calenderTitle by remember {
        derivedStateOf {
            "${Constant.MONTHS[month - 1]} $year"
        }
    }

    val isSameMonth by remember {
        derivedStateOf {
            Util.getMonth() == month
        }
    }

    var animationType by remember {
        mutableStateOf(AnimatedContentTransitionScope.SlideDirection.Up)
    }

    val pagerState = rememberPagerState(initialPage = month - 1) {
        12
    }

    var selectedDay by remember {
        mutableIntStateOf(-1)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        when (pagerState.currentPage) {
            12 -> {
                Log.e("TAG", "MonthView: month-1 and year ${year++}")
            }

            1 -> {
                Log.e("TAG", "MonthView: month-12 and year ${year--}")
            }

            else -> {
                Log.e("TAG", "MonthView: month-${pagerState.currentPage + 1}")
            }
        }
        //month = pagerState.currentPage + 1
    }

    var isDayCardShow by remember {
        mutableStateOf(false)
    }

    val label = "day_animation"

    val transition = updateTransition(
        targetState = if (isDayCardShow) AnimationStatus.VISBLE else AnimationStatus.GONE,
        label = label
    )

    val dayCardOffset = transition.animateDp(label = label) {
        if (it == AnimationStatus.VISBLE) {
            0.dp
        } else {
            (-30).dp
        }
    }.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp, bottom = 75.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
                selectedDay = -1
                animationType = AnimatedContentTransitionScope.SlideDirection.Down
                if (month == 1) {
                    month = 12
                    year -= 1
                } else {
                    month -= 1
                }
            }) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    "down_icon",
                    modifier = Modifier.size(30.dp),
                    tint = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
                )
            }

            AnimatedContent(
                targetState = calenderTitle,
                transitionSpec = {
                    slideIntoContainer(
                        towards = animationType, animationSpec = tween(durationMillis = 200)
                    ) togetherWith ExitTransition.None
                },
                modifier = Modifier.width(200.dp),
                label = "",
            ) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = UiConstant.widthModifier,
                    textAlign = TextAlign.Center,
                    color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
                )
            }
            IconButton(onClick = {
                selectedDay = -1
                animationType = AnimatedContentTransitionScope.SlideDirection.Up

                if (month == 12) {
                    year += 1
                    month = 1
                } else {
                    month += 1
                }

            }) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    "up_icon",
                    modifier = Modifier.size(30.dp),
                    tint = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
                )
            }
            if (Util.getMonth() == month && selectedDay != -1 && selectedDay != currentDay) {
                CurrentMonthCard(currentDay.toString(), onTap = {
                    isDayCardShow = false
                    selectedDay = -1
                    currentDay = Util.getDay()
                }, modifier = Modifier.offset(0.dp, dayCardOffset))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(listOfDaysTitle.size) {
                Text(
                    text = listOfDaysTitle[it],
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        /*
        HorizontalPager(state = pagerState, modifier = widthModifier) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 7),
                content = {
                    items(disableDayCount) {
                        Surface {

                        }
                    }
                    items(totalDaysCount) {
                        val dayValue = it + 1
                        DayCard(
                            day = dayValue.toString(),
                            isCurrentDay = currentMonth == month && dayValue == currentDay,
                            isAnyEvent = it == 15,
                            onTap = {

                            }
                        )

                    }
                },
                contentPadding = PaddingValues(3.dp),
            )
        }

         */

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 7),
            content = {
                items(disableDayCount) {
                    Surface {

                    }
                }
                items(totalDaysCount) { day ->
                    val dayValue = day + 1
                    DayCard(
                        day = dayValue.toString(),
                        isCurrentDay = selectedDay == -1 && isSameMonth && dayValue == currentDay,
                        onTap = {
                            isDayCardShow = true
                            selectedDay = dayValue

                            if (selectedDay == currentDay) {
                                selectedDay = -1
                                currentDay = Util.getDay()
                            }
                        },
                        isOtherDaySelected = selectedDay == dayValue
                    )
                }
            },
            contentPadding = PaddingValues(3.dp)
        )

        val tasks = listOf(
            TaskModel(
                id = "1",
                title = "Learn jetpack compose",
                description = "Explore the Jetpack Compose framework for Android app development.",
                category = "Android",
                attachmentType = TaskAttachmentType.LINK,
                cratedAt = "2023-01-01",
                updatedAt = "2023-01-05"
            ),
            TaskModel(
                id = "2",
                title = "Write a Kotlin tutorial",
                description = "Create a comprehensive tutorial on Kotlin programming language.",
                category = "Kotlin",
                attachmentType = TaskAttachmentType.XLS,
                cratedAt = "2023-02-01",
                updatedAt = "2023-02-10"
            ),
            TaskModel(
                id = "3",
                title = "Build a weather app",
                description = "Develop a weather application using a weather API.",
                category = "Flutter",
                attachmentType = TaskAttachmentType.DOC,
                cratedAt = "2023-03-01",
                updatedAt = "2023-03-15"
            ),
            TaskModel(
                id = "4",
                title = "Design a logo",
                description = "Create a unique and visually appealing logo for a new project.",
                category = "Designing",
                attachmentType = TaskAttachmentType.IMAGE,
                cratedAt = "2023-04-01",
                updatedAt = "2023-04-10"
            ),
            TaskModel(
                id = "5",
                title = "Record a tutorial video",
                description = "Produce a video tutorial demonstrating a specific programming concept.",
                category = "Video Recoding",
                attachmentType = TaskAttachmentType.VIDEO,
                cratedAt = "2023-05-01",
                updatedAt = "2023-05-15"
            ),
            TaskModel(
                id = "6",
                title = "Explore Kotlin coroutines",
                description = "Learn and implement Kotlin coroutines for asynchronous programming.",
                category = "Android Advanced",
                attachmentType = TaskAttachmentType.PDF,
                cratedAt = "2023-06-01",
                updatedAt = "2023-06-10"
            ),
            TaskModel(
                id = "7",
                title = "Write a blog post",
                description = "Compose a blog post on a technology-related topic of interest.",
                category = "Writing",
                attachmentType = TaskAttachmentType.LINK,
                cratedAt = "2023-07-01",
                updatedAt = "2023-07-15"
            ),
            TaskModel(
                id = "8",
                title = "Create a mobile app wireframe",
                description = "Design the basic layout and structure of a mobile app using wireframing tools.",
                category = "Ui/UX",
                attachmentType = TaskAttachmentType.IMAGE,
                cratedAt = "2023-08-01",
                updatedAt = "2023-08-10"
            ),
            TaskModel(
                id = "9",
                title = "Implement user authentication",
                description = "Integrate user authentication into an existing application.",
                category = "Backend Development",
                attachmentType = TaskAttachmentType.NONE,
                cratedAt = "2023-09-01",
                updatedAt = "2023-09-15"
            ),
            TaskModel(
                id = "10",
                title = "Explore machine learning basics",
                description = "Study the fundamentals of machine learning and its applications.",
                category = "AI/ML",
                attachmentType = TaskAttachmentType.LINK,
                cratedAt = "2023-10-01",
                updatedAt = "2023-10-10"
            )
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(tasks.size) {
                TaskGridDesign(tasks[it])
            }
        }
    }
}


@Composable
fun CurrentMonthCard(day: String, onTap: () -> Unit, modifier: Modifier = Modifier) {

    val colors = listOf(Color(0XFFCB4303), Color(0xFFA569BD), Color(0xFFF4D03F))

    Surface(
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(
            1.dp,
            color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
        ),
        modifier = modifier
            .size(30.dp)
            .clickable { onTap() }
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 2.5.dp),
            textAlign = TextAlign.Center
        )
    }

}

enum class AnimationStatus {
    VISBLE,
    GONE
}

@Preview
@Composable
fun TaskCardDesign() {
    Surface(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(10.dp)) {
        ConstraintLayout(
            modifier = Modifier
                .background(
                    color = if (isSystemInDarkTheme()) primaryDarkColor.copy(alpha = 0.6f) else primaryLightColor.copy(
                        alpha = 0.6f
                    )
                )
                .padding(10.dp)
        )
        {
            val (taskTitle, taskTimeRowKey, locationRowKey, optionMenuKey, taskDescriptionKey) = createRefs()

            Text(
                text = "Learn new android concept Learn new android concept",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = UiConstant.robotoFontFamily
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
                    fontFamily = UiConstant.robotoFontFamily
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
                    .constrainAs(taskTimeRowKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(taskDescriptionKey.bottom, 10.dp)
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
                        fontFamily = UiConstant.robotoFontFamily
                    ),
                    modifier = Modifier, textAlign = TextAlign.Start,
                )
            }

        }
    }
}

@Preview
@Composable
fun TaskCircleBox() {
    Box(modifier = Modifier.size(50.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(50.dp), onDraw = {

            drawArc(color = Color.Red, startAngle = 0f, sweepAngle = 360f, useCenter = false)
        })
        Canvas(modifier = Modifier.size(50.dp), onDraw = {
            drawCircle(color = Color.White)
        })
    }
}

@Preview
@Composable
fun TaskCircleDesign() {

}

@Composable
fun TwoCircles(modifier: Modifier) {
    Box(
        modifier = modifier.size(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .size(15.dp)
                .zIndex(1f),
            shape = CircleShape,
            color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
        ) {

        }

        Surface(modifier = Modifier.size(20.dp), shape = CircleShape, color = Color.White) {

        }

    }
}


@Composable
fun TaskGridDesign(task: TaskModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {

        val attachmentImage = when (task.attachmentType) {
            TaskAttachmentType.DOC -> R.drawable.ic_file_doc_icon
            TaskAttachmentType.PDF -> R.drawable.ic_file_pdf_icon
            TaskAttachmentType.XLS -> R.drawable.ic_file_xls_icon
            TaskAttachmentType.VIDEO -> R.drawable.ic_file_video_icon
            TaskAttachmentType.LINK -> R.drawable.ic_link_icon
            TaskAttachmentType.NONE -> R.drawable.ic_user_icon
            TaskAttachmentType.IMAGE -> R.drawable.ic_file_photo_icon
            TaskAttachmentType.OTHER -> R.drawable.ic_user_icon
            else -> R.drawable.ic_file_doc_icon
        }


        ConstraintLayout(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {

            val (startKey, endKey, timeLineKey, timeBubbleKey) = createRefs()


            Text(
                text = "10:10 AM",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = robotoFontFamily,
                    color = MaterialTheme.colorScheme.primaryContainer
                ), modifier = Modifier.constrainAs(startKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
            )

            /*
            TwoCircles(modifier = Modifier.constrainAs(timeBubbleKey){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(startKey.bottom,5.dp)
                bottom.linkTo(timeLineKey.top)
            })

             */
            Divider(
                modifier = Modifier
                    .width(3.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .constrainAs(timeLineKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(startKey.bottom)
                        bottom.linkTo(endKey.top)
                    },
                color = Color.Gray,
            )

            Text(
                text = "11:10 AM",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = robotoFontFamily,
                    color = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier.constrainAs(endKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )


        }

        Surface(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(10.dp)) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
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
                )
                {
                    val (categoryKey, taskTitle, taskTimeRowKey, locationRowKey, optionMenuKey, taskDescriptionKey, dividerKey, peopleKey, taskAttachmentKey) = createRefs()

                    Box(
                        modifier = Modifier
                            .constrainAs(categoryKey) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                            .clip(RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp))
                            .background(color = if (isSystemInDarkTheme()) primaryLightColor else primaryDarkColor)
                    ) {
                        Text(
                            text = task.category,
                            style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.background),
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }

                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            fontFamily = UiConstant.robotoFontFamily
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .constrainAs(taskTitle) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(categoryKey.bottom, 10.dp)
                            }, textAlign = TextAlign.Start,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            fontFamily = UiConstant.robotoFontFamily
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .constrainAs(taskDescriptionKey) {
                                start.linkTo(parent.start, 10.dp)
                                end.linkTo(parent.end, 10.dp)
                                top.linkTo(taskTitle.bottom, 10.dp)
                            }, textAlign = TextAlign.Start,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (task.attachmentType != TaskAttachmentType.NONE) {
                        Image(
                            painter = painterResource(id = attachmentImage),
                            contentDescription = "task_attachment",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(
                                    RoundedCornerShape(bottomEnd = 10.dp, topStart = 10.dp)
                                )
                                .constrainAs(taskAttachmentKey) {
                                    end.linkTo(parent.end)
                                    top.linkTo(taskDescriptionKey.bottom)
                                    bottom.linkTo(parent.bottom)
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

data class TaskModel(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val attachmentType: TaskAttachmentType,
    val cratedAt: String,
    val updatedAt: String,
)

enum class TaskAttachmentType {
    PDF,
    DOC,
    XLS,
    IMAGE,
    VIDEO,
    LINK,
    OTHER,
    NONE
}