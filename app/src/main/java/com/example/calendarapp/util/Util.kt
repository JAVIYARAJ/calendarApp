package com.example.calendarapp.util

import android.os.Build
import android.util.Patterns
import androidx.annotation.RequiresApi
import com.example.calendarapp.util.Constant
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

class Util {

    companion object {

        fun String.isEmailValid(): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(this).find()
        }

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

            val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)//total 7 days - get current day of weak
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
            val weeksInAdvance =6

            val allDays= ArrayList<ArrayList<String>>()

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
    }
}