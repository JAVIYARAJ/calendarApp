package com.example.calendarapp.screens.auth.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendarapp.screens.auth.model.AuthSocialAccountItem
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant

@Composable
fun AuthTopTitleWidget(authTitle: String, authSubTitle: String, modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        Text(
            text = authTitle,
            style = MaterialTheme.typography.headlineMedium.copy(fontFamily = UiConstant.rubikBubblesFontFamily),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primaryContainer
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = authSubTitle,
            style = MaterialTheme.typography.titleLarge.copy(fontFamily = UiConstant.robotoFontFamily),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primaryContainer
        )
    }

}

@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(
        color = Color.Blue, fontSize = 18.sp
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
    title: String, onTap: () -> Unit, modifier: Modifier, buttonRadius: Dp = 10.dp
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
fun AuthBottomAccountStatusWidget(
    authMessage: String,
    authLabel: String,
    modifier: Modifier = Modifier,
    onTap: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = authMessage, style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primaryContainer, fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        CustomTextButton(
            title = authLabel, onClick = {
                onTap.invoke()
            }, textStyle = MaterialTheme.typography.titleMedium.copy(
                color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor,
                fontSize = 20.sp
            )
        )
    }
}

@Composable
fun AuthSocialAccountWidget(
    socialAccounts: List<AuthSocialAccountItem>,
    modifier: Modifier = Modifier,
    onTap: (AuthSocialAccountItem) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val defaultButtonElevation = ButtonDefaults.elevatedButtonElevation(
            pressedElevation = 10.dp
        )

        repeat(socialAccounts.size) {
            ElevatedButton(
                onClick = { onTap(socialAccounts[it]) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White,
                ),
                modifier = Modifier.size(70.dp),
                elevation = defaultButtonElevation

            ) {
                Image(
                    painter = painterResource(id = socialAccounts[it].socialAccountIcon),
                    contentDescription = socialAccounts[it].socialAccountType.name
                )
            }
        }

    }
}

@Composable
fun AuthContinueLabelWidget(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(2.5f),
            thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer
        )
        Text(
            text = "or continue with",
            style = MaterialTheme.typography.titleMedium.copy(fontFamily = UiConstant.robotoFontFamily),
            modifier = Modifier.weight(4f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primaryContainer
        )
        HorizontalDivider(
            modifier = Modifier.weight(2.5f),
            thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer
        )
    }
}
