package com.example.calendarapp.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.calendarapp.R
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant.hedvig_letters_serif_regular


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TaskDetailScreen() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {
            Text(
                text = "Finish create task design with test cases",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = hedvig_letters_serif_regular,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user_icon),
                    contentDescription = "task_owner_image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Javiya raj",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = hedvig_letters_serif_regular,
                        fontWeight = FontWeight.SemiBold, fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            val listOfPeople = listOf(
                R.drawable.ic_google_icon,
                R.drawable.ic_facebook_icon,
                R.drawable.ic_user_icon,
                R.drawable.ic_user_icon,
                R.drawable.ic_user_icon
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TasKPeopleUi(modifier = Modifier, listOfPeople)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(5f)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_hastag_icon),
                        contentDescription = "hastag_icon"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontFamily = hedvig_letters_serif_regular,
                            fontWeight = FontWeight.SemiBold, fontSize = 18.sp
                        )
                    )
                }
                Text(
                    text = "2:00 AM",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = hedvig_letters_serif_regular,
                        fontWeight = FontWeight.Normal, fontSize = 18.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "You can adjust the time ranges and messages based on your preferences. The example above uses emoji to add a visual element to the greeting messages, but you can customize the messages to suit your application's style.",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = hedvig_letters_serif_regular,
                    fontWeight = FontWeight.Normal, fontSize = 16.sp

                )
            )
            Spacer(modifier = Modifier.height(15.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_attach_file_icon),
                    contentDescription = "attach_icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Attachments",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = hedvig_letters_serif_regular,
                        fontWeight = FontWeight.SemiBold, fontSize = 18.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(15.dp))

            repeat(3) {
                TaskAttachmentFileCardWidget(modifier = Modifier.padding(5.dp), onTap = {})
            }
            
        }
    }
}


@Composable
fun TaskAttachmentFileCardWidget(modifier: Modifier, onTap: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor)
            .clickable { onTap.invoke() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_file_doc_icon),
                    contentDescription = "file_image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(
                            RoundedCornerShape(10.dp)
                        ),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Android.pdf",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = hedvig_letters_serif_regular,
                        fontWeight = FontWeight.SemiBold, fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.background
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_download_file_icon),
                contentDescription = "download_image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }
}