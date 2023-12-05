package com.example.calendarapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.calendarapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInput(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    hintStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background),
    modifier: Modifier,
    inSingleLine: Boolean = true,
    isPassword: Boolean = false,
    imeAction: (KeyboardActionScope) -> Unit
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
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Email,
            imeAction = if (isPassword) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = if (isPassword) KeyboardActions(onDone = imeAction) else KeyboardActions(
            onNext = imeAction
        )
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
    isConfirmPassword:Boolean=false,
    imeAction:()->Unit
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
        visualTransformation =if(isPasswordShow) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = if(isConfirmPassword) ImeAction.Done else ImeAction.Next),
        keyboardActions = KeyboardActions(onDone = {
            imeAction.invoke()
        }, onNext = {
            imeAction.invoke()
        })

    )
}