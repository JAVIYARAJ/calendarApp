package com.example.calendarapp.screens.auth

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.navigation.routes.RouteQueryConstant.EMAIL_QUERY
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.auth.widgets.CustomAuthButton
import com.example.calendarapp.screens.common.widgets.CustomScreenTopNavBar
import com.example.calendarapp.screens.common.widgets.ProgressIndicator
import com.example.calendarapp.screens.common.widgets.UserInput
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.ExtensionFunction.Companion.isEmailValid
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import kotlinx.coroutines.launch

@Composable
fun ForgotOtpSendScreen(controller: NavHostController) {

    var isLoading by remember {
        mutableStateOf(false)
    }

    val snackBarHost = remember {
        SnackbarHostState()
    }

    var email by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    Scaffold(

        topBar = {
            CustomScreenTopNavBar(
                title = "Forgot Password", onBackClick = {
                    controller.popBackStack()
                }, modifier = Modifier
                    .fillMaxWidth()
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHost)
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
                    painter = painterResource(id = R.drawable.ic_lock_icon),
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
                text = "Please enter your email address To Receive a verification Code",
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primaryContainer),
                fontFamily = robotoFontFamily, modifier = Modifier.constrainAs(screenTitleKey) {
                    top.linkTo(lockImageKey.bottom, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, textAlign = TextAlign.Center
            )

            UserInput(
                value = email,
                onValueChange = { emailValue ->
                    email = emailValue
                },
                hint = "Enter your email",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(emailFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(screenTitleKey.bottom, margin = 50.dp)
                    },
                imeActionCallBack = {

                }, imeAction = ImeAction.Done
            )

            CustomAuthButton(title = "Send Otp", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .constrainAs(sendButtonKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(emailFiledKey.bottom, margin = 50.dp)
                }, onTap = {
                if (email.isEmpty()) {
                    scope.launch {
                        snackBarHost.showSnackbar("enter your email")
                    }
                } else {
                    if (email.isEmailValid()) {
                        isLoading = true
                        Handler(Looper.getMainLooper()).postDelayed({
                            isLoading = false

                            val routes=Routes.ForgotOtpVerifyRoute.route.replace(EMAIL_QUERY,email)

                            controller.navigate(routes)
                        }, 1000)
                    } else {
                        scope.launch {
                            snackBarHost.showSnackbar("enter valid email")
                        }
                    }
                }

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