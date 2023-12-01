package com.example.jetpackdesign.util

import androidx.compose.ui.unit.dp

class Constant {
    companion object {

        //app text constant values
        const val APP_TITLE = "Jetpack Chat"
        const val JUMP_TO_BOTTOM = "jump to bottom"
        const val MESSAGE_TEXT_HINT = "Write message here"
        val JUMP_BUTTON_THRESHOLD = 56.dp
        val STANDARD_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

        //drawer item
        const val HOME = "Home"
        const val CHAT = "Chat"
        const val PEOPLE = "People"
        const val CALLS = "Calls"
        const val SETTINGS = "settings"
        const val LOGOUT = "Logout"

        val MONTHS= listOf("Jan.", "Feb.", "Mar.", "Apr.", "May.", "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.","Dec.")
        val MONTH_LIST= listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

        val DAYS_TITLE = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")


    }
}