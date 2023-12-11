package com.example.calendarapp.screens.auth

import android.os.Looper
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.auth.model.SocialAccountType
import com.example.calendarapp.screens.auth.model.socialAuthAccounts
import com.example.calendarapp.screens.auth.widgets.AuthBottomAccountStatusWidget
import com.example.calendarapp.screens.auth.widgets.AuthContinueLabelWidget
import com.example.calendarapp.screens.auth.widgets.AuthSocialAccountWidget
import com.example.calendarapp.screens.auth.widgets.AuthTopTitleWidget
import com.example.calendarapp.screens.auth.widgets.CustomAuthButton
import com.example.calendarapp.screens.common.widgets.ProgressIndicator
import com.example.calendarapp.screens.common.widgets.UserInput
import com.example.calendarapp.screens.common.widgets.UserInputPassword
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import com.example.calendarapp.util.UiConstant.widthModifier
import com.example.calendarapp.util.Util

@Composable
fun LoginScreen(controller: NavHostController, onLoginClick: () -> Unit) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var isPasswordShow by remember {
        mutableStateOf(false)
    }

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    val defaultMargin = 20.dp

    val defaultButtonElevation = ButtonDefaults.elevatedButtonElevation(
        pressedElevation = 10.dp
    )

    val keyboardController = LocalSoftwareKeyboardController.current

    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
            )
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val (loginTitleKey, emailFiledKey, passwordFiledKey, forgotKey, signInButtonKey, continueLabelKey, signInOptionRowKey, registerNowLabelKey, circularIndicatorKey) = createRefs()

            AuthTopTitleWidget(authTitle = "Hello Again!",
                authSubTitle = "welcome back to task tracker app",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(loginTitleKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = 100.dp)
                    })

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
                        top.linkTo(loginTitleKey.bottom, margin = 50.dp)
                        bottom.linkTo(passwordFiledKey.top)
                    },
                imeActionCallBack = {

                }, keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next

            )

            UserInputPassword(value = password,
                onValueChange = { passwordValue ->
                    password = passwordValue
                },
                hint = "Enter your password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(passwordFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(emailFiledKey.bottom, margin = 30.dp)
                    },
                isPasswordShow = isPasswordShow,
                passwordIconChange = {
                    isPasswordShow = !isPasswordShow
                }, imeActionCallBack = {
                    keyboardController?.hide()
                    Util.validateEmailAndPassword(
                        email = email,
                        password = password,
                        scope = scope,
                        snackState = snackBarHostState
                    ) {
                        isLoading = true
                        android.os.Handler(Looper.getMainLooper()).postDelayed({
                            isLoading = false
                            controller.navigate(Routes.HomeRootRoute.route)
                        }, 2000)
                    }
                }, imeAction = ImeAction.Done)

            TextButton(onClick = {
                controller.navigate(Routes.ForgotRoute.route)
            }, modifier = Modifier.constrainAs(forgotKey) {
                end.linkTo(parent.end, defaultMargin)
                top.linkTo(passwordFiledKey.bottom, margin = 10.dp)
                bottom.linkTo(signInButtonKey.top)
            }) {
                Text(
                    text = "Forgot password?",
                    style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFontFamily),
                    textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.primaryContainer
                )
            }

            CustomAuthButton(title = "Sign in", onTap = {

                Util.validateEmailAndPassword(
                    email = email,
                    password = password,
                    scope = scope,
                    snackState = snackBarHostState
                ) {
                    keyboardController?.hide()
                    isLoading = true
                    android.os.Handler(Looper.getMainLooper()).postDelayed({
                        isLoading = false
                        controller.navigate(Routes.HomeRootRoute.route)
                    }, 2000)
                }
            }, modifier = widthModifier
                .height(50.dp)
                .padding(horizontal = 20.dp)
                .constrainAs(signInButtonKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(forgotKey.bottom, margin = 30.dp)
                })

            AuthContinueLabelWidget(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .constrainAs(continueLabelKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(signInButtonKey.bottom, margin = 30.dp)
                })

            AuthSocialAccountWidget(
                socialAccounts = socialAuthAccounts,
                modifier = widthModifier.constrainAs(signInOptionRowKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(continueLabelKey.bottom, margin = 30.dp)
                },
                onTap = { account ->
                    when (account.socialAccountType) {
                        SocialAccountType.GOOGLE -> {

                        }

                        SocialAccountType.FACEBOOK -> {

                        }

                        SocialAccountType.APPLE -> {

                        }

                        else -> {

                        }
                    }
                })

            AuthBottomAccountStatusWidget(authMessage = "Not any account?",
                authLabel = "Register now",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .constrainAs(registerNowLabelKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(signInOptionRowKey.bottom, margin = 30.dp)
                    },
                onTap = {
                    controller.navigate(Routes.RegisterRoute.route) {
                        popUpTo(controller.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                })

            if (isLoading) {
                ProgressIndicator(modifier = Modifier.constrainAs(circularIndicatorKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })
            }

        }
    }
}
