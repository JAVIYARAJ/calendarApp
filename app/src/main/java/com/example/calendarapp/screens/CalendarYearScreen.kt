package com.example.calendarapp.screens

import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.calendarapp.Routes
import com.example.calendarapp.components.CustomTopBar
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.jetpackdesign.util.Constant
import com.example.jetpackdesign.util.ModifierConstant
import com.example.jetpackdesign.util.Util

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarYearScreen(controller: NavHostController) {
    YearView(navHostController = controller)
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearView(navHostController: NavHostController) {

    Scaffold(
        topBar = {
            CustomTopBar(title = "2023", onTap = {}) {

            }
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 3),
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            val listOfDaysTitle = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

            items(Constant.MONTHS.size) {
                Surface(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxSize()
                        .clickable {

                            val route = Routes.MonthViewScreen.route
                                .replace("{year}", "2023")
                                .replace("{month}", "${it + 1}")

                            Log.e("TAG", "YearView: ${route}")

                            navHostController.navigate(route)
                        }, color = Color.Transparent, shape = RoundedCornerShape(15.dp)
                ) {
                    Column(modifier = ModifierConstant.widthModifier) {

                        val month = it + 1
                        val disableCount =
                            listOfDaysTitle.indexOf(Util.getFirstDayOfMonth(Util.getYear(), month))
                        val dayCount = Util.getCurrentMonthDays(Util.getYear(), month)
                        Text(
                            text = Constant.MONTHS[it],
                            style = MaterialTheme.typography.titleLarge,
                            color = if (Util.getMonth() == month) if (isSystemInDarkTheme()) primaryDarkColor
                            else
                                primaryLightColor else MaterialTheme.colorScheme.primaryContainer
                        )
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(count = 7),
                            content = {
                                items(disableCount) {
                                    Surface {

                                    }
                                }
                                items(dayCount) {
                                    val dayValue = it + 1
                                    val isCurrentDay =
                                        Util.getDay() == dayValue && Util.getMonth() == month
                                    if (isCurrentDay) {
                                        Surface(
                                            modifier = Modifier
                                                .width(30.dp)
                                                .height(20.dp),
                                            shape = CircleShape,
                                            color = if (isSystemInDarkTheme()) primaryDarkColor
                                            else
                                                primaryLightColor
                                        ) {
                                            Text(
                                                text = dayValue.toString(),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.primaryContainer
                                            )
                                        }
                                    } else {
                                        Text(
                                            text = dayValue.toString(),
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.primaryContainer
                                        )
                                    }

                                }
                            },
                            contentPadding = PaddingValues(3.dp),
                            modifier = Modifier.height(120.dp)
                        )
                    }
                }
            }
        }
    }


}
