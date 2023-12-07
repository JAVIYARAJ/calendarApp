package com.example.calendarapp.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calendarapp.R
import com.example.calendarapp.ui.theme.primaryDarkColor

@Preview
@Composable
fun CreateTaskScreen() {

}

@Preview
@Composable
fun CategoryCardDesign() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(color = primaryDarkColor)){
        Text(text = "Android Development", style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primaryContainer), modifier = Modifier.padding(10.dp)
        )
        Image(painter = painterResource(id = R.drawable.ic_checked_icon), contentDescription = "", modifier = Modifier.size(30.dp).align(
            Alignment.CenterEnd))
    }
}