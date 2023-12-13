package com.example.calendarapp.screens.task

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calendarapp.screens.common.widgets.CustomScreenTopNavBar
import com.example.calendarapp.screens.common.widgets.UserInput
import com.example.calendarapp.ui.theme.completedTaskColor
import com.example.calendarapp.ui.theme.inProgressTaskColor
import com.example.calendarapp.ui.theme.inReviewTaskColor
import com.example.calendarapp.ui.theme.onCancelTaskColor
import com.example.calendarapp.ui.theme.onHoldTaskColor
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import java.util.Calendar

@SuppressLint("UnrememberedMutableState")
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun CreateTaskScreen() {

    var isDateDialogShow by remember {
        mutableStateOf(false)
    }

    if (isDateDialogShow) {
        CustomDatePicker(onDismiss = { isDateDialogShow = false }, onConfirm = {
            isDateDialogShow = false
        })
    }

    val categories = mutableStateListOf(
        CategoryItemModel(
            id = "1",
            item = "Android Development",
            color = completedTaskColor,
            isSelected = true
        ),
        CategoryItemModel(
            id = "2",
            item = "Web Development with boostrap",
            color = inProgressTaskColor
        ),
        CategoryItemModel(id = "3", item = "Blockchain", color = inReviewTaskColor),
        CategoryItemModel(
            id = "4",
            item = "DSA",
            color = onHoldTaskColor,
        ),
        CategoryItemModel(
            id = "5",
            item = "Flutter",
            color = onCancelTaskColor,
        ),
        CategoryItemModel(id = "6", item = "Google Auth", color = completedTaskColor),
        CategoryItemModel(id = "7", item = "KMM", color = inReviewTaskColor),
        CategoryItemModel(id = "8", item = "Jetpack compose", color = onCancelTaskColor)
    )

    val categories1 by remember {
        mutableStateOf(
            listOf(
                CategoryItemModel(
                    id = "1",
                    item = "Android Development",
                    color = completedTaskColor
                ),
                CategoryItemModel(
                    id = "2",
                    item = "Web Development with boostrap",
                    color = inProgressTaskColor
                ),
                CategoryItemModel(id = "3", item = "Blockchain", color = inReviewTaskColor),
                CategoryItemModel(
                    id = "4",
                    item = "DSA",
                    color = onHoldTaskColor,
                ),
                CategoryItemModel(
                    id = "5",
                    item = "Flutter",
                    color = onCancelTaskColor,
                ),
                CategoryItemModel(id = "6", item = "Google Auth", color = completedTaskColor),
                CategoryItemModel(id = "7", item = "KMM", color = inReviewTaskColor),
                CategoryItemModel(id = "8", item = "Jetpack compose", color = onCancelTaskColor)
            )
        )
    }

    Scaffold(topBar = {
        CustomScreenTopNavBar(title = "Create Task", onBackClick = {

        })
    }) {

        var taskTitle by remember {
            mutableStateOf("")
        }

        var taskDescription by remember {
            mutableStateOf("")
        }


        var startDate by remember {
            mutableStateOf("")
        }

        var endDate by remember {
            mutableStateOf("")
        }

        val items = listOf("1", "2")

        val context = LocalContext.current
        var expanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .then(Modifier.padding(10.dp))
        ) {

            Text(
                text = "Task Title",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = robotoFontFamily
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = taskTitle,
                onValueChange = { title ->
                    taskTitle = title
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    cursorColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                textStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {

                })
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Task Description",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = robotoFontFamily
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = taskDescription,
                onValueChange = { description ->
                    taskDescription = description
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    cursorColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                textStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {

                }), maxLines = 6
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateShowCardWidget(modifier = Modifier.weight(5f), title = "Start Date") {
                    isDateDialogShow = true
                }
                Spacer(modifier = Modifier.width(10.dp))
                DateShowCardWidget(modifier = Modifier.weight(5f), title = "End Date") {
                    isDateDialogShow = true
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

        }
    }

}

data class CategoryItemModel(
    val id: String,
    val item: String,
    val color: Color,
    var isSelected: Boolean = false
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    val datePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = Calendar.getInstance().timeInMillis,
        initialSelectedEndDateMillis = Calendar.getInstance().timeInMillis
    )

    DatePickerDialog(onDismissRequest = onDismiss, confirmButton = {
        TextButton(onClick = { onConfirm.invoke() }) {
            Text(text = "Confirm", style = MaterialTheme.typography.titleMedium)
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismiss.invoke()
        }) {
            Text(text = "Dismiss", style = MaterialTheme.typography.titleMedium)
        }

    }) {
        DateRangePicker(
            state = datePickerState,
            modifier = Modifier.height(height = 500.dp) // if I don't set this, dialog's buttons are not appearing
        )
    }

}


@Composable
fun DateShowCardWidget(modifier: Modifier, title: String, onTap: () -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onTap.invoke()
            }
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = "",
                modifier = Modifier.weight(1f), tint = MaterialTheme.colorScheme.background
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = robotoFontFamily,
                    color = MaterialTheme.colorScheme.background
                ), modifier = Modifier.weight(5f)
            )
        }
    }
}