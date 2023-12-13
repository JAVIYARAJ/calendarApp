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
    Text(text = "CreateTaskScreen")
}
