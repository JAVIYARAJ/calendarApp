package com.example.calendarapp.screens.welcome.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.calendarapp.screens.welcome.model.Page
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant

@Composable
fun PageIndicatorBottomWidget(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPageIndex: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = Color.LightGray,
    onPageTap:(Int)->Unit
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(color = if (selectedPageIndex == page) selectedColor else unSelectedColor).clickable { onPageTap.invoke(page) }
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@Composable
fun OnBoardingPageTopWidget(
    page: Page, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.7f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, fontFamily = UiConstant.robotoFontFamily),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

    }
}

@Composable
fun WelcomeDefaultButton(title: String, onTap: () -> Unit) {
    Button(
        onClick = onTap, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primaryContainer, containerColor = if(isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold, fontFamily = UiConstant.robotoFontFamily, color = MaterialTheme.colorScheme.primaryContainer)
        )
    }
}

@Composable
fun WelcomeTextButton(title: String, onTap: () -> Unit) {
    TextButton(onClick = onTap) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold, fontFamily = UiConstant.robotoFontFamily),
            color = MaterialTheme.colorScheme.primaryContainer
        )
    }
}