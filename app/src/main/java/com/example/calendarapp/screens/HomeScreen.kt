package com.example.calendarapp.screens

import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calendarapp.components.DayCard
import com.example.calendarapp.util.Constant.Companion.DAYS_TITLE
import com.example.jetpackdesign.util.Util

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(
    showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun HomeScreen() {

    val allDaysList=Util.get7WeakDaysDates()

    var pagerState = rememberPagerState(initialPage = 0) {
        allDaysList.size
    }



    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),){
                Text(text = "02 December", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primaryContainer)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                DAYS_TITLE.forEach {title->
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) {parent->
                Row(modifier = Modifier.fillMaxWidth()) {
                    repeat(allDaysList[parent].size) { child ->
                        DayCard(day = allDaysList[parent][child], modifier = Modifier.weight(1f), isCurrentDay = (if(Util.getDay().toString().length==1) "0"+Util.getDay().toString() else "")==allDaysList[parent][child])
                    }
                }
            }
        }
    }
}
