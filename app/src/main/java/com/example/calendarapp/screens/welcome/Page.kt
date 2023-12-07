package com.example.calendarapp.screens.welcome

import androidx.annotation.DrawableRes
import com.example.calendarapp.R

data class Page(
    val title: String,@DrawableRes val image: Int
)

val Pages = listOf(
    Page(
        title = "Easy task and work management",
        image = R.drawable.ic_welcome_01_icon
    ),
    Page(
        title = "Track your productivity and gain insights",
        image = R.drawable.ic_welcome_02_icon
    ),
    Page(
        title = "Boost your productivity now and be successful",
        image = R.drawable.ic_welcome_03_icon
    )
)
