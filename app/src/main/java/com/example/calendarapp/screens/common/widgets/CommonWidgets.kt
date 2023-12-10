package com.example.calendarapp.screens.common.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.ExtensionFunction.Companion.postPercentage
import com.example.calendarapp.util.UiConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInput(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    hintStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background),
    modifier: Modifier,
    inSingleLine: Boolean = true,
    keyboardType: KeyboardType=KeyboardType.Text,
    imeAction:ImeAction,
    imeActionCallBack: () -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint, style = hintStyle)
        },
        shape = RoundedCornerShape(10.dp),
        singleLine = inSingleLine,
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = MaterialTheme.colorScheme.background
        ),
        textStyle = hintStyle,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = {
            imeActionCallBack.invoke()
        }, onNext = {
            imeActionCallBack.invoke()
        })
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputPassword(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier,
    hintStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background),
    inSingleLine: Boolean = true,
    isPasswordShow: Boolean = false,
    passwordIconChange: () -> Unit,
    imeAction: ImeAction,
    imeActionCallBack: () -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint, style = hintStyle)
        },
        shape = RoundedCornerShape(10.dp),
        singleLine = inSingleLine,
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = MaterialTheme.colorScheme.background
        ),
        textStyle = hintStyle,
        modifier = modifier,
        trailingIcon = {
            IconButton(onClick = passwordIconChange) {
                Icon(
                    imageVector = if (isPasswordShow) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "eye_icon",
                    tint = MaterialTheme.colorScheme.background
                )
            }
        },
        visualTransformation = if (isPasswordShow) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = {
            imeActionCallBack.invoke()
        }, onNext = {
            imeActionCallBack.invoke()
        })

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputOtp(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    hintStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background),
    modifier: Modifier,
    imeAction: (KeyboardActionScope) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint, style = hintStyle)
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = MaterialTheme.colorScheme.background
        ),
        textStyle = hintStyle.copy(textAlign = TextAlign.Center),
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            imeAction.invoke(this)
        })
    )
}


@Composable
fun CustomProgressBar(
    modifier: Modifier = Modifier,
    percentage: Float,
    number: Int,
    color: Color = Color(0xFF6495ED),
    animationDuration: Int = 1800,
    animationDelay: Int = 0,
    strokeWidth: Dp = 10.dp,
    radius: Dp = 40.dp,
    textSize: TextUnit = 10.sp
) {
    var animationStatus by remember {
        mutableStateOf(false)
    }



    val animation = animateFloatAsState(
        targetValue = if (animationStatus) percentage else 0f,
        animationSpec = tween(durationMillis = animationDuration, delayMillis = animationDelay),
        label = ""
    )

    LaunchedEffect(key1 = true) {
        animationStatus = true
    }

    Box(modifier = modifier.size(radius * 2f), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = color,
                startAngle = -90f, //top minus axis 90 degree angle
                sweepAngle = 360 * animation.value,//whole angle*value
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (animation.value * number).toInt().toString().postPercentage(),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = textSize),
            color = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
    )
}

@Composable
fun CustomScreenTopNavBar(title: String, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back_icon",
                tint = MaterialTheme.colorScheme.primaryContainer
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primaryContainer,
                fontFamily = UiConstant.robotoFontFamily
            )
        )
    }
}


