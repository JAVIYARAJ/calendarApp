package com.example.calendarapp.screens.task.viewmodel

import android.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calendarapp.data.ColorModel
import com.example.calendarapp.data.categoryList
import com.example.calendarapp.screens.task.model.TaskCategoryModel
import com.example.calendarapp.screens.task.model.TaskModel
import com.example.calendarapp.ui.theme.primaryDarkColor

class SharedTaskViewModel : ViewModel() {

    private var taskCategory by mutableStateOf<List<TaskCategoryModel>?>(null)
    private var taskList by mutableStateOf<List<TaskModel>?>(null)
    private var selectedCategoryColor by mutableStateOf<androidx.compose.ui.graphics.Color>(
        primaryDarkColor)

    init {
        taskCategory = categoryList
    }

    fun setTaskCategoryValue(list: List<TaskCategoryModel>) {
        taskCategory = list
    }

    fun getTaskCategoryValue(): List<TaskCategoryModel>? {
        return taskCategory
    }

    fun setTaskListValue(list: List<TaskModel>) {
        taskList = list
    }

    fun getTaskListValue(): List<TaskModel>? {
        return taskList
    }

    fun changeTaskCategoryColor(index:Int,color:androidx.compose.ui.graphics.Color){
        taskCategory?.let {
            it[index].color=color
        }
    }

    fun setCategoryColor(color:androidx.compose.ui.graphics.Color){
        selectedCategoryColor=color
    }

    fun getCategoryColor():androidx.compose.ui.graphics.Color{
        return selectedCategoryColor
    }
}