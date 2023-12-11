package com.example.calendarapp.screens.calendar.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calendarapp.R
import com.example.calendarapp.screens.calendar.TaskAttachmentType
import com.example.calendarapp.screens.calendar.TaskModel
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant

@Composable
fun CalendarMonthCardWidget(day: String, onTap: () -> Unit, modifier: Modifier = Modifier) {

    val colors = listOf(Color(0XFFCB4303), Color(0xFFA569BD), Color(0xFFF4D03F))

    Surface(
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(
            1.dp,
            color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
        ),
        modifier = modifier
            .size(30.dp)
            .clickable { onTap() }
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 2.5.dp),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun CalendarTaskCardWidget(task: TaskModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {

        val attachmentImage = when (task.attachmentType) {
            TaskAttachmentType.DOC -> R.drawable.ic_file_doc_icon
            TaskAttachmentType.PDF -> R.drawable.ic_file_pdf_icon
            TaskAttachmentType.XLS -> R.drawable.ic_file_xls_icon
            TaskAttachmentType.VIDEO -> R.drawable.ic_file_video_icon
            TaskAttachmentType.LINK -> R.drawable.ic_link_icon
            TaskAttachmentType.NONE -> R.drawable.ic_user_icon
            TaskAttachmentType.IMAGE -> R.drawable.ic_file_photo_icon
            TaskAttachmentType.OTHER -> R.drawable.ic_user_icon
            else -> R.drawable.ic_file_doc_icon
        }


        ConstraintLayout(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {

            val (startKey, endKey, timeLineKey, timeBubbleKey) = createRefs()

            Text(
                text = "10:10 AM",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = UiConstant.robotoFontFamily,
                    color = MaterialTheme.colorScheme.primaryContainer
                ), modifier = Modifier.constrainAs(startKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(3.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .constrainAs(timeLineKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(startKey.bottom)
                        bottom.linkTo(endKey.top)
                    },
                color = Color.Gray
            )

            Text(
                text = "11:10 AM",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = UiConstant.robotoFontFamily,
                    color = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier.constrainAs(endKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )


        }

        Surface(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(10.dp)) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
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
                    color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
                ) {}

                ConstraintLayout(modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(rightSideLayoutKey) {
                        start.linkTo(leftCardDesignKey.end)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(
                        color = if (isSystemInDarkTheme()) primaryDarkColor.copy(alpha = 0.6f) else primaryLightColor.copy(
                            alpha = 0.6f
                        )
                    )
                )
                {
                    val (categoryKey, taskTitle, taskTimeRowKey, locationRowKey, optionMenuKey, taskDescriptionKey, dividerKey, peopleKey, taskAttachmentKey) = createRefs()

                    Box(
                        modifier = Modifier
                            .constrainAs(categoryKey) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                            .clip(RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp))
                            .background(color = if (isSystemInDarkTheme()) primaryLightColor else primaryDarkColor)
                    ) {
                        Text(
                            text = task.category,
                            style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.background),
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }

                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            fontFamily = UiConstant.robotoFontFamily
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .constrainAs(taskTitle) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(categoryKey.bottom, 10.dp)
                            }, textAlign = TextAlign.Start,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            fontFamily = UiConstant.robotoFontFamily
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .constrainAs(taskDescriptionKey) {
                                start.linkTo(parent.start, 10.dp)
                                end.linkTo(parent.end, 10.dp)
                                top.linkTo(taskTitle.bottom, 10.dp)
                            }, textAlign = TextAlign.Start,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (task.attachmentType != TaskAttachmentType.NONE) {
                        Image(
                            painter = painterResource(id = attachmentImage),
                            contentDescription = "task_attachment",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(
                                    RoundedCornerShape(bottomEnd = 10.dp, topStart = 10.dp)
                                )
                                .constrainAs(taskAttachmentKey) {
                                    end.linkTo(parent.end)
                                    top.linkTo(taskDescriptionKey.bottom)
                                    bottom.linkTo(parent.bottom)
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarDayCardWidget(
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