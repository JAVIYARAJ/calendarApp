package com.example.calendarapp.screens.auth

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.auth.widgets.CustomAuthButton
import com.example.calendarapp.screens.common.widgets.CustomScreenTopNavBar
import com.example.calendarapp.screens.common.widgets.ProgressIndicator
import com.example.calendarapp.screens.common.widgets.UserInput
import com.example.calendarapp.screens.common.widgets.UserInputOtp
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotOtpVerifyScreen(controller: NavHostController,email:String?) {

    var code1 by remember {
        mutableStateOf("")
    }

    var code2 by remember {
        mutableStateOf("")
    }

    var code3 by remember {
        mutableStateOf("")
    }

    var code4 by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            CustomScreenTopNavBar(
                title = "Verify Your Email", onBackClick = {
                    controller.popBackStack()
                }, modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            val (lockImageKey, screenTitleKey, emailFiledKey, sendButtonKey, loadingKey) = createRefs()
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .constrainAs(lockImageKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, 50.dp)
                    }
                    .clip(CircleShape)
                    .background(
                        color = if (isSystemInDarkTheme()) primaryDarkColor.copy(alpha = 0.6f) else primaryLightColor.copy(
                            alpha = 0.6f
                        )
                    ), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_email_icon),
                    contentDescription = "lock_icon",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = "Please enter the 4 digit code sent to $email",
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primaryContainer),
                fontFamily = UiConstant.robotoFontFamily,
                modifier = Modifier.constrainAs(screenTitleKey) {
                    top.linkTo(lockImageKey.bottom, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(emailFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(screenTitleKey.bottom, margin = 50.dp)
                    }, horizontalArrangement = Arrangement.SpaceBetween
            ) {
                UserInputOtp(
                    value = code1,
                    onValueChange = { code1Value ->
                        code1 = code1Value
                    },

                    hint = "",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp),
                    imeAction = {
                    },
                )
                UserInputOtp(
                    value = code2,
                    onValueChange = { code2Value ->
                        code2 = code2Value
                    },
                    hint = "",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp),
                    imeAction = {
                    }
                )
                UserInputOtp(
                    value = code3,
                    onValueChange = { code3Value ->
                        code3 = code3Value
                    },
                    hint = "",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp),
                    imeAction = {
                    }
                )
                UserInputOtp(
                    value = code4,
                    onValueChange = { code4Value ->
                        code4 = code4Value
                    },
                    hint = "",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp),
                    imeAction = {
                    }
                )

            }

            CustomAuthButton(title = "Verify", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .constrainAs(sendButtonKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(emailFiledKey.bottom, margin = 50.dp)
                }, onTap = {
                isLoading = true
                Handler(Looper.getMainLooper()).postDelayed({
                    isLoading = false
                    controller.navigate(Routes.ConfirmPasswordRoute.route)
                }, 1000)
            })

            if (isLoading) {
                ProgressIndicator(modifier = Modifier.constrainAs(loadingKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })
            }

        }
    }
}