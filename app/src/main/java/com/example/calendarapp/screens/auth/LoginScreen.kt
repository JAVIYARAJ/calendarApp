package com.example.calendarapp.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.calendarapp.navigation.Routes

@Composable
fun LoginScreen(controller: NavHostController,onLoginClick:()->Unit) {
    Text(text = "Login", modifier = Modifier.clickable {
        onLoginClick()
    })
}