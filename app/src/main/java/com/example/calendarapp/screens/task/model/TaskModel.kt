package com.example.calendarapp.screens.task.model

import android.os.Parcelable
import com.example.calendarapp.screens.auth.model.UserModel
import com.example.calendarapp.screens.calendar.TaskAttachmentType
import kotlinx.parcelize.Parcelize


data class TaskModel(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val attachmentType: TaskAttachmentType,
    val taskStatus: TaskStatus,
    val createdBy: UserModel,
    val assignTo: List<UserModel>,
    val startDate: String,
    val endDate: String?,
    val cratedAt: String,
    val updatedAt: String,
    val location: String?
)

enum class TaskStatus {
    COMPLETED,
    IN_PROGRESS,
    IN_REVIEW,
    ON_HOLD,
    ON_CANCELED

}
