package com.example.calendarapp.screens.task.model

import com.example.calendarapp.screens.auth.model.UserModel
import com.example.calendarapp.screens.calendar.TaskAttachmentType

data class TaskModel(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val attachmentType: TaskAttachmentType,
    val taskStatus: TaskStatus,
    val createdBy: UserModel,
    val assignTo: List<UserModel>,
    val taskPriorityTAG: TaskPriorityTAG = TaskPriorityTAG.LOW,
    val startDate: String,
    val endDate: String?,
    val cratedAt: String,
    val updatedAt: String,
    val location: String?
)

data class TaskStatusModel(
    val title:String,
    val isSelected:Boolean=false
)

enum class TaskStatus {
    COMPLETED,
    IN_PROGRESS,
    IN_REVIEW,
    ON_HOLD,
    ON_CANCELED
}

enum class TaskPriorityTAG {
    HIGH,
    MEDIUM,
    LOW,
    URGENT
}

