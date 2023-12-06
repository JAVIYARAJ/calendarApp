package com.example.calendarapp.screens.welcome

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(controller: NavHostController) {
    Scaffold {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {

            val pageState = rememberPagerState(initialPage = 0) {
                Pages.size
            }

            val scope = rememberCoroutineScope()
            val buttonState = remember {
                derivedStateOf {
                    when (pageState.currentPage) {
                        0 -> listOf("", "Next")
                        1 -> listOf("Back", "Next")
                        2 -> listOf("Back", "Get Started")
                        else -> listOf("", "")
                    }
                }
            }

            HorizontalPager(state = pageState) {
                OnBoardingPage(page = Pages[it])
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PageIndicator(pageSize = Pages.size, selectedPageIndex = pageState.currentPage, onPageTap = {
                    scope.launch {
                        pageState.animateScrollToPage(page = it)
                    }
                })

                Row(verticalAlignment = Alignment.CenterVertically) {

                    if (buttonState.value[0].isNotEmpty()) {
                        NewsTextButton(title = buttonState.value[0]) {
                            scope.launch {
                                pageState.animateScrollToPage(pageState.currentPage - 1)
                            }
                        }
                    }
                    NewsButton(title = buttonState.value[1]) {
                        if (pageState.currentPage == 2) {
                            controller.navigate(Routes.AuthRootRoutes.route){
                                popUpTo(controller.graph.findStartDestination().id){
                                    inclusive=true
                                }
                            }
                        } else {
                            scope.launch {
                                pageState.animateScrollToPage(pageState.currentPage + 1)
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun OnBoardingPage(
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
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, fontFamily = robotoFontFamily),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

    }
}

data class Page(
    val title: String,@DrawableRes val image: Int
)

val Pages = listOf(
    Page(
        title = "Easy task and work management",
        image = R.drawable.ic_welcome_01_icon
    ),
    Page(
        title = "Track your productivity and gain insights",
        image = R.drawable.ic_welcome_02_icon
    ),
    Page(
        title = "Boost your productivity now and be successful",
        image = R.drawable.ic_welcome_03_icon
    )
)

@Composable
fun PageIndicator(
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
fun NewsButton(title: String, onTap: () -> Unit) {
    Button(
        onClick = onTap, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
            contentColor =MaterialTheme.colorScheme.primaryContainer, containerColor = if(isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold, fontFamily = robotoFontFamily, color = MaterialTheme.colorScheme.primaryContainer)
        )
    }
}

@Composable
fun NewsTextButton(title: String, onTap: () -> Unit) {
    TextButton(onClick = onTap) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold, fontFamily = robotoFontFamily),
            color =MaterialTheme.colorScheme.primaryContainer
        )
    }
}