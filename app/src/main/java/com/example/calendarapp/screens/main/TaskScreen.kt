package com.example.calendarapp.screens.main

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant.robotoFontFamily

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(10){
            TaskItem()
        }
    }
}

@Preview
@Composable
fun TaskItem() {
    Surface(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(10.dp)) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            val (leftCardDesignKey, rightSideLayoutKey) = createRefs()

            Surface(
                modifier = Modifier
                    .width(10.dp)
                    .fillMaxHeight()
                    .constrainAs(leftCardDesignKey) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(rightSideLayoutKey.start)
                        bottom.linkTo(parent.bottom)
                    },
                shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                color = primaryDarkColor
            ) {}

            ConstraintLayout(modifier = Modifier
                .fillMaxSize()
                .constrainAs(rightSideLayoutKey) {
                    start.linkTo(leftCardDesignKey.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .background(color = primaryLightColor)) {
                val (taskTitle,taskTimeKey,optionMenuKey) = createRefs()

                Text(
                    text = "Learn new android concept",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.background,
                        fontFamily = robotoFontFamily
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(taskTitle) {
                            start.linkTo(parent.start, 20.dp)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top, 20.dp)
                            bottom.linkTo(taskTimeKey.top)
                        }, textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "7:00 PM-8:30 PM",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.background,
                        fontFamily = robotoFontFamily
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(taskTimeKey) {
                            start.linkTo(parent.start, 20.dp)
                            end.linkTo(parent.end)
                            top.linkTo(taskTitle.bottom, 20.dp)
                            bottom.linkTo(parent.bottom,20.dp)

                        }, textAlign = TextAlign.Start,
                )
            }
        }
    }
}