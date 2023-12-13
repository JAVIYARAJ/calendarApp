package com.example.calendarapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import com.example.calendarapp.R
import com.example.calendarapp.screens.task.model.TaskPriorityTAG
import com.example.calendarapp.screens.task.model.TaskStatus
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

        fun getGreetingMessage(): String {
            val calendar = Calendar.getInstance()

            return when (calendar.get(Calendar.HOUR_OF_DAY)) {
                in 5..11 -> "Hello! Good morning! ☀️"
                in 12..16 -> "Hello! Good afternoon! 🌤️"
                in 17..20 -> "Hello! Good evening!"
                else -> "Good night! 🌙"
            }
        }

        fun greetMessage(progressRate:Float):String{
            return if(progressRate>=0.9){
                "Great job! You've completed most of your tasks today. Keep up the excellent work!"
            }else if(progressRate>=0.7 && progressRate<0.9){
                "You're doing well! Most tasks are completed. A little push, and you'll hit your goal!"
            }else if(progressRate>=0.5 &&progressRate<0.7){
                "You're halfway there! Keep going, and you'll achieve your daily goals."
            }else if(progressRate>=0.3 && progressRate<0.5){
                "Let's pick up the pace! There's still time to complete more tasks today."
            }else{
                "It's not too late to turn things around! Start with the most important tasks and make the day count."
            }
        }

        fun convertTaskStatus(taskStatus: TaskStatus):String{
            return when(taskStatus){
                TaskStatus.COMPLETED->"Completed"
                TaskStatus.IN_PROGRESS->"In Progress"
                TaskStatus.IN_REVIEW->"In Review"
                TaskStatus.ON_CANCELED->"On Canceled"
                TaskStatus.ON_HOLD->"On Hold"
                else->"None"
            }
        }

        fun convertTaskPriority(taskPriorityTAG: TaskPriorityTAG):String{
            return when(taskPriorityTAG){
                TaskPriorityTAG.LOW->"Low"
                TaskPriorityTAG.HIGH->"High"
                TaskPriorityTAG.MEDIUM->"Medium"
                TaskPriorityTAG.URGENT->"Urgent"
                else->"None"
            }
        }

        fun getTaskStatusIcon(taskStatus: TaskStatus):Int{
            return when(taskStatus){
                TaskStatus.COMPLETED-> R.drawable.ic_completed_icon
                TaskStatus.IN_PROGRESS->R.drawable.ic_in_progress_icon
                TaskStatus.IN_REVIEW->R.drawable.ic_in_review_icon
                TaskStatus.ON_CANCELED->R.drawable.ic_reject_icon
                TaskStatus.ON_HOLD->R.drawable.ic_on_hold_icon
                else->R.drawable.ic_reject_icon
            }
        }
    }
}