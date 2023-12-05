package com.example.calendarapp.util

import android.util.Patterns
import com.example.calendarapp.util.Constant.Companion.PASSWORD_STRONG_COUNT

class ExtensionFunction {

    companion object {


        fun String.postPercentage(): String {
            return "$this%"
        }

        fun String.isEmailValid(): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(this).find()
        }

        fun String.isPasswordStrong(): Boolean {
            return this.length >= PASSWORD_STRONG_COUNT
        }
    }

}