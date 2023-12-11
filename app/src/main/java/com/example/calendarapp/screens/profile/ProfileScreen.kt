package com.example.calendarapp.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calendarapp.util.UiConstant.robotoFontFamily

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreen() {
    Scaffold {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .then(Modifier.padding(5.dp))) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "")
                }
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = robotoFontFamily)
                )
                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                }
            }
        }
    }
}