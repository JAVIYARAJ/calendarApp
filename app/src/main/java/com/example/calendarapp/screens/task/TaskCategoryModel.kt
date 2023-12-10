package com.example.calendarapp.screens.task

data class TaskCategoryModel(
    val id: String,
    val categoryName: String,
    val taskCategoryStatus: TaskCategoryStatus,
    val color:String,
    val createdAt: String,
    val updatedAt: String,
)

data class TaskCategoryStatus(
    val completedTask: Int,
    val inProgressTask: Int,
    val onHoldTask: Int,
    val inReviewTask: Int,
    val canceledTask: Int
)

val categoryList = listOf(
    TaskCategoryModel(
        "", "Android Development", TaskCategoryStatus(
            2, 5, 0, 1, 0
        ), "#CCCCFF", "",""
    ),
    TaskCategoryModel(
        "", "Flutter Development", TaskCategoryStatus(
            20, 1, 2, 9, 2
        ), "#F8C471", "",""
    ), TaskCategoryModel(
        "", "Web Development", TaskCategoryStatus(
            200, 50, 10, 11, 10
        ), "#40E0D0", "",""
    ),
    TaskCategoryModel(
        "", "Data Structure", TaskCategoryStatus(
            400, 150, 10, 9, 0
        ), "#FF7F50", "",""
    )
)