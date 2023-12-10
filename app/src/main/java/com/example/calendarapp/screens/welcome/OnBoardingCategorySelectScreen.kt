package com.example.calendarapp.screens.welcome

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calendarapp.R
import com.example.calendarapp.ui.theme.primaryLightColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OnBoardingCategorySelectScreen() {
    Scaffold {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                CategoryCardWidget()
            }
        }
    }
}

@Composable
fun CategoryCardWidget() {
    Box(
        modifier = Modifier
            .padding(10.dp).aspectRatio(1f) // Ensure square aspect ratio
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val (imageKey, selectedKey, categoryTitleKey) = createRefs()

                Image(
                    painter = painterResource(id = R.drawable.ic_user_icon),
                    contentDescription = "category_icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .constrainAs(imageKey) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_checked_icon),
                    contentDescription = "category_icon",
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .constrainAs(selectedKey) {
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Android Developer",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.Black), modifier = Modifier.constrainAs(categoryTitleKey){
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(selectedKey.bottom)
                    },
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Android Developer",
                style = MaterialTheme.typography.titleMedium.copy(color = Color.Black)
            )
        }
    }
}
