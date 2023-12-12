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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.calendarapp.data.tasksList
import com.example.calendarapp.screens.calendar.widgets.CalendarDayCardWidget
import com.example.calendarapp.screens.calendar.widgets.CalendarMonthCardWidget
import com.example.calendarapp.screens.calendar.widgets.CalendarTaskCardWidget
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.Constant
import com.example.calendarapp.util.Constant.Companion.DAYS_TITLE
import com.example.calendarapp.util.UiConstant
import com.example.calendarapp.util.Util


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMonthScreen(yearValue: Int?, monthValue: Int?, onChange: () -> Unit) {
    MonthView(yearValue, monthValue)
}

@OptIn(ExperimentalFoundationApi::class)
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
        targetState = if (isDayCardShow) AnimationStatus.VISIBLE else AnimationStatus.GONE,
        label = label
    )

    val dayCardOffset = transition.animateDp(label = label) {
        if (it == AnimationStatus.VISIBLE) {
            0.dp
        } else {
            (-30).dp
        }
    }.value

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 5.dp, bottom = 75.dp).then(Modifier.padding(it))
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
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        "up_icon",
                        modifier = Modifier.size(30.dp),
                        tint = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
                    )
                }

                if (Util.getMonth() == month && selectedDay != -1 && selectedDay != currentDay) {
                    CalendarMonthCardWidget(currentDay.toString(), onTap = {
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
                            CalendarDayCardWidget(
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
                        CalendarDayCardWidget(
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

            //all current task widget
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(tasksList.size) {index->
                    CalendarTaskCardWidget(tasksList[index])
                }
            }
        }
    }
}



