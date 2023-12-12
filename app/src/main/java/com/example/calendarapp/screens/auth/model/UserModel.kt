package com.example.calendarapp.screens.auth.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val userId: String,
    val firstname: String,
    val lastname: String,
    val image: String,
    val createdAt: String,
    val updatedAt: String,
):Parcelable
