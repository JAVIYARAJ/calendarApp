package com.example.calendarapp.screens.auth

import android.os.Handler
import android.os.Looper
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.auth.widgets.CustomAuthButton
import com.example.calendarapp.screens.common.widgets.CustomScreenTopNavBar
import com.example.calendarapp.screens.common.widgets.ProgressIndicator
import com.example.calendarapp.screens.common.widgets.UserInput
import com.example.calendarapp.screens.common.widgets.UserInputPassword
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasswordScreen(controller: NavHostController) {

    var isLoading by remember {
        mutableStateOf(false)
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var isPasswordShow by remember {
        mutableStateOf(false)
    }

    var isConfirmPasswordShow by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            CustomScreenTopNavBar(
                title = "Create New Password", onBackClick = {
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

            val (lockImageKey, screenTitleKey, passwordFiledKey, confirmPasswordFiledKey, sendButtonKey, loadingKey) = createRefs()



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
                    painter = painterResource(id = R.drawable.ic_verified_icon),
                    contentDescription = "lock_icon",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = "Your new password must be different from previously used password",
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primaryContainer),
                fontFamily = UiConstant.robotoFontFamily,
                modifier = Modifier.constrainAs(screenTitleKey) {
                    top.linkTo(lockImageKey.bottom, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                textAlign = TextAlign.Center
            )

            UserInputPassword(
                value = password,
                onValueChange = { pwd ->
                    password = pwd
                },
                hint = "Enter your New Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(passwordFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(screenTitleKey.bottom, margin = 50.dp)
                    },
                imeActionCallBack = {

                }, passwordIconChange = {
                    isPasswordShow = !isPasswordShow
                }, isPasswordShow = isPasswordShow, imeAction = ImeAction.Next
            )

            UserInputPassword(
                value = confirmPassword,
                hint = "Re enter new password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(confirmPasswordFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(passwordFiledKey.bottom, margin = 20.dp)
                    },
                onValueChange = { pwd ->
                    confirmPassword = pwd
                }, imeActionCallBack = {

                }, passwordIconChange = {
                    isConfirmPasswordShow = !isConfirmPasswordShow
                }, isPasswordShow = isConfirmPasswordShow, imeAction = ImeAction.Done
            )

            CustomAuthButton(title = "Save", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .constrainAs(sendButtonKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(confirmPasswordFiledKey.bottom, margin = 50.dp)
                }, onTap = {
                isLoading = true
                Handler(Looper.getMainLooper()).postDelayed({
                    isLoading = false
                    controller.navigate(Routes.LoginRoute.route) {
                        popUpTo(controller.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
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
