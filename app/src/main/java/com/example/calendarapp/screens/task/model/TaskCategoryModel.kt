package com.example.calendarapp.screens.task.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.example.calendarapp.data.ColorModel
import kotlinx.parcelize.Parcelize


data class TaskCategoryModel(
    val id: String,
    val categoryName: String,
    val taskList:List<TaskModel>,
    var color: Color,
    val createdAt: String,
    val updatedAt: String,
)
