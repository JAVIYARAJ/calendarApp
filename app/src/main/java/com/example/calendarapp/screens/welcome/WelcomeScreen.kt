package com.example.calendarapp.screens.welcome

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.welcome.widgets.OnBoardingPageTopWidget
import com.example.calendarapp.screens.welcome.widgets.PageIndicatorBottomWidget
import com.example.calendarapp.screens.welcome.widgets.WelcomeDefaultButton
import com.example.calendarapp.screens.welcome.widgets.WelcomeTextButton
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

            HorizontalPager(state = pageState) {pageIndex->
                OnBoardingPageTopWidget(page = Pages[pageIndex])
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
                PageIndicatorBottomWidget(pageSize = Pages.size, selectedPageIndex = pageState.currentPage, onPageTap = {
                    scope.launch {
                        pageState.animateScrollToPage(page = it)
                    }
                })

                Row(verticalAlignment = Alignment.CenterVertically) {

                    if (buttonState.value[0].isNotEmpty()) {
                        WelcomeTextButton(title = buttonState.value[0]) {
                            scope.launch {
                                pageState.animateScrollToPage(pageState.currentPage - 1)
                            }
                        }
                    }
                    WelcomeDefaultButton(title = buttonState.value[1]) {
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




