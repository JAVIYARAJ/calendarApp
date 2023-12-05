package com.example.calendarapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import com.example.calendarapp.util.ExtensionFunction.Companion.isEmailValid
import com.example.calendarapp.util.ExtensionFunction.Companion.isPasswordStrong
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

class Util {

    companion object {

        fun defaultDate(): String {
            val dateFormat = SimpleDateFormat(Constant.STANDARD_FORMAT, Locale.getDefault())
            val calendar = Calendar.getInstance()
            return dateFormat.format(calendar.timeInMillis)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentMonthDays(year: Int, month: Int): Int {
            val currentYear = YearMonth.of(year, month)
            return currentYear.lengthOfMonth()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getFirstDayOfMonth(year: Int, month: Int): String {
            val firstDayOfMonth = LocalDate.of(year, month, 1)
            val dayOfWeek = firstDayOfMonth.dayOfWeek
            return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getMonth(): Int {
            val currentDate = LocalDate.now()
            return currentDate.monthValue
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getYear(): Int {
            val currentDate = LocalDate.now()
            return currentDate.year
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDay(): Int {
            val currentDate = LocalDate.now()
            return currentDate.dayOfMonth
        }

        fun getCurrentWeekDates(): List<String> {
            val calendar = Calendar.getInstance()

            val currentDayOfWeek =
                calendar.get(Calendar.DAY_OF_WEEK)//total 7 days - get current day of weak
            val daysInWeek = 7
            val days = mutableListOf<String>()

            for (i in 1..daysInWeek) {
                val difference = i - currentDayOfWeek
                calendar.add(Calendar.DAY_OF_MONTH, difference)

                val date = SimpleDateFormat("dd", Locale.getDefault()).format(calendar.time)

                days.add(date)

                // Reset the calendar for the next iteration
                calendar.add(Calendar.DAY_OF_MONTH, -difference)
            }

            return days
        }

        fun get7WeakDaysDates(): ArrayList<ArrayList<String>> {
            val calendar = Calendar.getInstance()

            val daysInWeek = 7
            val weeksInAdvance = 6

            val allDays = ArrayList<ArrayList<String>>()

            for (week in 1..weeksInAdvance) {
                val dates = ArrayList<String>()
                for (day in 1..daysInWeek) {
                    val difference = day - calendar.get(Calendar.DAY_OF_WEEK)
                    calendar.add(Calendar.DAY_OF_MONTH, difference)

                    val date = SimpleDateFormat("dd", Locale.getDefault()).format(calendar.time)
                    dates.add(date)

                    // Reset the calendar for the next iteration
                    calendar.add(Calendar.DAY_OF_MONTH, -difference)
                }
                allDays.add(dates)
                // Move to the next week
                calendar.add(Calendar.WEEK_OF_YEAR, 1)
            }

            return allDays
        }

        fun validateEmailAndPassword(
            email: String,
            password: String,
            scope: CoroutineScope,
            snackState: SnackbarHostState,
            onSuccess: () -> Unit
        ) {

            when {
                email.isEmpty() -> showSnackBar("enter your email", snackState, scope)
                !email.isEmailValid() -> showSnackBar("enter your valid email", snackState, scope)
                password.isEmpty() -> showSnackBar("enter your password", snackState, scope)
                !password.isPasswordStrong() -> showSnackBar(
                    "enter your strong password\nat least 5 character required",
                    snackState,
                    scope
                )

                else -> {
                    onSuccess.invoke()
                }
            }

        }

        fun validateRegisterData(
            name: String,
            email: String,
            password: String,
            confirmPassword: String,
            taskGroup: String,
            scope: CoroutineScope,
            snackState: SnackbarHostState,
            onSuccess: () -> Unit
        ) {
            when {
                name.isEmpty() -> showSnackBar("enter your name", snackState, scope)
                email.isEmpty() -> showSnackBar("enter your email", snackState, scope)
                !email.isEmailValid() -> showSnackBar("enter your valid email", snackState, scope)
                taskGroup.isEmpty() -> showSnackBar("enter your task group name", snackState, scope)
                password.isEmpty() -> showSnackBar("enter your password", snackState, scope)
                confirmPassword.isEmpty() -> showSnackBar(
                    "enter your confirm password",
                    snackState,
                    scope
                )

                !password.isPasswordStrong() -> showSnackBar(
                    "enter your strong password\nat least 5 character required",
                    snackState,
                    scope
                )

                password != confirmPassword -> showSnackBar(
                    "password not match",
                    snackState,
                    scope
                )

                else -> {
                    onSuccess.invoke()
                }
            }
        }

        private fun showSnackBar(
            message: String,
            snackState: SnackbarHostState,
            scope: CoroutineScope
        ) {
            scope.launch {
                snackState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short,
                    withDismissAction = true
                )
            }
        }

    }
}