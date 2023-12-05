package com.example.calendarapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendarapp.ui.theme.eventCardLightColor
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import com.example.calendarapp.util.UiConstant.widthModifier


@Composable
fun DayCard(
    modifier: Modifier = Modifier,
    day: String,
    isCurrentDay: Boolean = false,
    isOtherDaySelected: Boolean = false,
    onTap: () -> Unit = {},
) {

    val colors = listOf(Color(0XFFCB4303), Color(0xFFA569BD), Color(0xFFF4D03F))

    Surface(
        shape = CircleShape,
        color = if (isCurrentDay)
            if (isSystemInDarkTheme()) primaryDarkColor
            else
                primaryLightColor
        else if (isOtherDaySelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.background,
        modifier = modifier
            .padding(2.5.dp)
            .clickable { onTap() },
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = if (isCurrentDay) Color.White else MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 15.dp)
        )
    }
}

@Composable
fun CalenderEventCard() {
    Surface(
        modifier = widthModifier.padding(vertical = 2.5.dp, horizontal = 3.dp),
        shape = RoundedCornerShape(5.dp),
        color = if (isSystemInDarkTheme()) eventCardLightColor else MaterialTheme.colorScheme.primary.copy(
            alpha = 0.7f
        ),
        shadowElevation = 0.8.dp
    ) {
        Row(modifier = widthModifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
            Column(modifier = widthModifier.weight(0.9f)) {
                Text(
                    text = "Project Review",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "21-03-2023",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )
            }
            IconButton(onClick = {}, modifier = Modifier.weight(0.1f)) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more_button",
                    tint = Color.White
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    onTap: () -> Unit,
    actions: @Composable RowScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Row(modifier = widthModifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = title, style = MaterialTheme.typography.headlineLarge)
            IconButton(onClick = { onTap() }) {
                Icon(Icons.Filled.Close, "close_icon", modifier = Modifier.size(35.dp))
            }
        }
    }
}

@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(
        color = Color.Blue,
        fontSize = 18.sp
    ),
    title: String,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(
            text = title,
            style = textStyle,
        )
    }
}

@Composable
fun CustomAuthButton(
    title: String,
    onTap: () -> Unit,
    modifier: Modifier,
    buttonRadius: Dp = 10.dp
) {
    ElevatedButton(
        onClick = onTap,
        modifier = modifier,
        shape = RoundedCornerShape(buttonRadius),
        colors = ButtonDefaults.textButtonColors(
            containerColor = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor,
            contentColor = Color.White
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontFamily = UiConstant.robotoFontFamily),
            color = Color.White
        )
    }
}

@Composable
fun CustomScreenTopNavBar(title: String,onBackClick:()->Unit) {
    Row(modifier = widthModifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onBackClick) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_icon", tint = MaterialTheme.colorScheme.primaryContainer)
        }
        Text(text =title, style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primaryContainer, fontFamily = robotoFontFamily))
    }
}
