package com.example.calendarapp.screens.task.model

import com.example.calendarapp.screens.calendar.TaskAttachmentType

data class TaskCategoryModel(
    val id: String,
    val categoryName: String,
    val taskList:List<TaskModel>,
    val color:String,
    val createdAt: String,
    val updatedAt: String,
)

val categoryList = listOf(
    TaskCategoryModel(
        id = "", categoryName = "Android Development", taskList = listOf(
            TaskModel(
                id = "2",
                title = "UI/UX Design",
                description = "Improve UI/UX design skills",
                category = "Android Development",
                attachmentType = TaskAttachmentType.IMAGE,
                taskStatus = TaskStatus.IN_PROGRESS,
                cratedAt = "2023-01-02",
                updatedAt = "2023-01-06"
            ),
            TaskModel(
                id = "3",
                title = "Testing",
                description = "Write unit and UI tests for Android apps",
                category = "Android Development",
                attachmentType = TaskAttachmentType.DOC,
                taskStatus = TaskStatus.COMPLETED,
                cratedAt = "2023-01-03",
                updatedAt = "2023-01-07"
            ),
            TaskModel(
                id = "4",
                title = "Database Management",
                description = "Implement SQLite or Room database in Android apps",
                category = "Android Development",
                attachmentType = TaskAttachmentType.DOC,
                taskStatus = TaskStatus.COMPLETED,
                cratedAt = "2023-01-04",
                updatedAt = "2023-01-08"
            ),TaskModel(
                id = "5",
                title = "Kotlin Coroutines",
                description = "Explore and implement Kotlin coroutines for asynchronous programming",
                category = "Android Development",
                attachmentType = TaskAttachmentType.DOC,
                taskStatus = TaskStatus.ON_HOLD,

                cratedAt = "2023-01-05",
                updatedAt = "2023-01-09"
            ),
            TaskModel(
                id = "6",
                title = "Firebase Integration",
                description = "Integrate Firebase services into Android apps",
                category = "Android Development",
                attachmentType = TaskAttachmentType.IMAGE,
                taskStatus = TaskStatus.IN_REVIEW,
                cratedAt = "2023-01-06",
                updatedAt = "2023-01-10"
            ),
        ), color = "#CCCCFF", createdAt = "", updatedAt = ""
    ),
    TaskCategoryModel(
        id = "", categoryName = "Flutter Development", taskList = listOf(
            TaskModel(
                id = "1",
                title = "Learn Flutter Basics",
                description = "Explore the fundamentals of Flutter development.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.PDF,
                taskStatus = TaskStatus.IN_PROGRESS,
                cratedAt = "2023-01-01",
                updatedAt = "2023-01-02"
            ),
            TaskModel(
                id = "2",
                title = "Build a To-Do App",
                description = "Create a simple To-Do app using Flutter.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.VIDEO,
                taskStatus = TaskStatus.COMPLETED,
                cratedAt = "2023-02-01",
                updatedAt = "2023-02-03"
            ),
            TaskModel(
                id = "3",
                title = "Flutter State Management",
                description = "Explore different state management techniques in Flutter.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.LINK,
                taskStatus = TaskStatus.IN_REVIEW,
                cratedAt = "2023-03-01",
                updatedAt = "2023-03-05"
            ),TaskModel(
                id = "4",
                title = "Responsive UI with Flutter",
                description = "Learn how to create responsive user interfaces with Flutter.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.LINK,
                taskStatus = TaskStatus.ON_CANCELED,
                cratedAt = "2023-04-01",
                updatedAt = "2023-04-05"
            ),
            TaskModel(
                id = "5",
                title = "Flutter Testing Techniques",
                description = "Explore testing strategies for Flutter applications.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.VIDEO,
                taskStatus = TaskStatus.IN_REVIEW,
                cratedAt = "2023-05-01",
                updatedAt = "2023-05-07"
            )
        ), color = "#40E0D0", createdAt = "", updatedAt = ""
    ),
    TaskCategoryModel(
        id = "", categoryName = "Data Structure", taskList = listOf(
            TaskModel(
                id = "1",
                title = "Introduction to Data Structures",
                description = "Overview of basic data structures.",
                category = "Data Structure",
                attachmentType = TaskAttachmentType.PDF,
                taskStatus = TaskStatus.COMPLETED,
                cratedAt = "2023-01-01",
                updatedAt = "2023-01-02"
            ),
            TaskModel(
                id = "2",
                title = "Arrays and Linked Lists",
                description = "Understanding arrays and linked lists.",
                category = "Data Structure",
                attachmentType = TaskAttachmentType.VIDEO,
                taskStatus = TaskStatus.COMPLETED,
                cratedAt = "2023-02-01",
                updatedAt = "2023-02-05"
            )
        ), color = "#FF7F50", createdAt = "", updatedAt = ""
    )
)
