package com.example.calendarapp.screens.common.model

import androidx.compose.ui.graphics.Color
import com.example.calendarapp.R

data class TaskGroupItem(
    val id:String,
    val title: String,
    val taskSize: Int,
    val completedTaskSize: Int,
    val taskGroupIcon: Int = R.drawable.ic_default_task_group_icon,
    val taskGridColor: Color = Color(0XF923E073)
)