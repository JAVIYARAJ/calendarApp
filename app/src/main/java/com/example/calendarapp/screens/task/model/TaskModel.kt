package com.example.calendarapp.screens.task.model

import com.example.calendarapp.screens.calendar.TaskAttachmentType

data class TaskModel(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val attachmentType: TaskAttachmentType,
    val taskStatus: TaskStatus,
    val cratedAt: String,
    val updatedAt: String,
)

enum class TaskStatus {
    COMPLETED,
    IN_PROGRESS,
    IN_REVIEW,
    ON_HOLD,
    ON_CANCELED

}

val tasksList = listOf(
    TaskModel(
        id = "1",
        title = "Learn jetpack compose",
        description = "Explore the Jetpack Compose framework for Android app development.",
        category = "Android",
        attachmentType = TaskAttachmentType.LINK,
        taskStatus = TaskStatus.COMPLETED,
        cratedAt = "2023-01-01",
        updatedAt = "2023-01-05"
    ),
    TaskModel(
        id = "2",
        title = "Write a Kotlin tutorial",
        description = "Create a comprehensive tutorial on Kotlin programming language.",
        category = "Kotlin",
        attachmentType = TaskAttachmentType.XLS,
        taskStatus = TaskStatus.COMPLETED,
        cratedAt = "2023-02-01",
        updatedAt = "2023-02-10"
    ),
    TaskModel(
        id = "3",
        title = "Build a weather app",
        description = "Develop a weather application using a weather API.",
        category = "Flutter",
        attachmentType = TaskAttachmentType.DOC,
        taskStatus = TaskStatus.IN_PROGRESS,
        cratedAt = "2023-03-01",
        updatedAt = "2023-03-15"
    ),
    TaskModel(
        id = "4",
        title = "Design a logo",
        description = "Create a unique and visually appealing logo for a new project.",
        category = "Designing",
        attachmentType = TaskAttachmentType.IMAGE,
        taskStatus = TaskStatus.IN_REVIEW,
        cratedAt = "2023-04-01",
        updatedAt = "2023-04-10"
    ),
    TaskModel(
        id = "5",
        title = "Record a tutorial video",
        description = "Produce a video tutorial demonstrating a specific programming concept.",
        category = "Video Recoding",
        attachmentType = TaskAttachmentType.VIDEO,
        taskStatus = TaskStatus.COMPLETED,
        cratedAt = "2023-05-01",
        updatedAt = "2023-05-15"
    ),
    TaskModel(
        id = "6",
        title = "Explore Kotlin coroutines",
        description = "Learn and implement Kotlin coroutines for asynchronous programming.",
        category = "Android Advanced",
        attachmentType = TaskAttachmentType.PDF,
        taskStatus = TaskStatus.ON_CANCELED,
        cratedAt = "2023-06-01",
        updatedAt = "2023-06-10"
    ),
    TaskModel(
        id = "7",
        title = "Write a blog post",
        description = "Compose a blog post on a technology-related topic of interest.",
        category = "Writing",
        attachmentType = TaskAttachmentType.LINK,
        taskStatus = TaskStatus.ON_HOLD,
        cratedAt = "2023-07-01",
        updatedAt = "2023-07-15"
    ),
    TaskModel(
        id = "8",
        title = "Create a mobile app wireframe",
        description = "Design the basic layout and structure of a mobile app using wireframing tools.",
        category = "Ui/UX",
        attachmentType = TaskAttachmentType.IMAGE,
        taskStatus = TaskStatus.COMPLETED,
        cratedAt = "2023-08-01",
        updatedAt = "2023-08-10"
    ),
    TaskModel(
        id = "9",
        title = "Implement user authentication",
        description = "Integrate user authentication into an existing application.",
        category = "Backend Development",
        attachmentType = TaskAttachmentType.NONE,
        taskStatus = TaskStatus.IN_PROGRESS,
        cratedAt = "2023-09-01",
        updatedAt = "2023-09-15"
    ),
    TaskModel(
        id = "10",
        title = "Explore machine learning basics",
        description = "Study the fundamentals of machine learning and its applications.",
        category = "AI/ML",
        attachmentType = TaskAttachmentType.LINK,
        taskStatus = TaskStatus.COMPLETED,
        cratedAt = "2023-10-01",
        updatedAt = "2023-10-10"
    )
)