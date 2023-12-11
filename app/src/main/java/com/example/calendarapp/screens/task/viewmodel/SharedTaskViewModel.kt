package com.example.calendarapp.screens.task.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.calendarapp.screens.task.model.TaskCategoryModel
import com.example.calendarapp.screens.task.model.TaskModel

class SharedTaskViewModel:ViewModel() {

    private val taskCategoryList= mutableStateListOf<TaskCategoryModel>()
    private val taskList= mutableStateListOf<TaskModel>()

    fun setTaskCategoryList(list:List<TaskCategoryModel>){
        taskCategoryList.clear()
        taskCategoryList.addAll(list)
    }

    fun getTaskCategoryList():List<TaskCategoryModel>{
        return taskCategoryList
    }

    fun setTaskList(list:List<TaskModel>){
        taskList.addAll(list)
    }

    fun getTaskList():List<TaskModel>{
        return taskList
    }

}