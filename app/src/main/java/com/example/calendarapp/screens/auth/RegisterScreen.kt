package com.example.calendarapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.screens.auth.widgets.AuthBottomAccountStatusWidget
import com.example.calendarapp.screens.auth.widgets.AuthContinueLabelWidget
import com.example.calendarapp.screens.auth.widgets.AuthSocialAccountWidget
import com.example.calendarapp.screens.auth.widgets.AuthTopTitleWidget
import com.example.calendarapp.screens.auth.widgets.CustomAuthButton
import com.example.calendarapp.screens.auth.widgets.CustomTextButton
import com.example.calendarapp.screens.common.widgets.ProgressIndicator
import com.example.calendarapp.screens.common.widgets.UserInput
import com.example.calendarapp.screens.common.widgets.UserInputPassword
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant
import com.example.calendarapp.util.UiConstant.rubikBubblesFontFamily
import com.example.calendarapp.util.UiConstant.widthModifier
import com.example.calendarapp.util.Util
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(controller: NavHostController) {
    var email by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var taskGroupName by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmpassword by remember {
        mutableStateOf("")
    }

    val isLoading by remember {
        mutableStateOf(false)
    }

    var isPasswordShow by remember {
        mutableStateOf(false)
    }

    var isConfirmPasswordShow by remember {
        mutableStateOf(false)
    }


    val defaultButtonElevation = ButtonDefaults.elevatedButtonElevation(
        pressedElevation = 10.dp
    )
    val scope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val (registerTitleKey, emailFiledKey, nameFiledKey, taskGroupFiledKey, passwordFiledKey, confirmPasswordFiledKey, signInButtonKey, continueLabelKey, signInOptionRowKey, registerNowLabelKey, circularIndicatorKey) = createRefs()

            AuthTopTitleWidget(authTitle = "Welcome",
                authSubTitle = "Sign up with your task tracker app",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(registerTitleKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = 20.dp)
                    })

            UserInput(
                value = name,
                onValueChange = { nameValue ->
                    name = nameValue
                },
                hint = "Enter your name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(nameFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(registerTitleKey.bottom, margin = 50.dp)
                        bottom.linkTo(emailFiledKey.top)
                    },
                imeActionCallBack = {
                    Util.validateRegisterData(
                        name = name,
                        email = email,
                        password = password,
                        confirmPassword = confirmpassword,
                        taskGroup = taskGroupName,
                        scope = scope,
                        snackState = snackBarHostState, onSuccess = {
                            scope.launch {
                                snackBarHostState.showSnackbar(message = "Register Success")
                            }
                        })
                }, imeAction = ImeAction.Next
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
                        top.linkTo(nameFiledKey.bottom, margin = 20.dp)
                        bottom.linkTo(taskGroupFiledKey.top)
                    },
                imeActionCallBack = {

                }, keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
            )
            UserInput(
                value = taskGroupName,
                onValueChange = { taskValue ->
                    taskGroupName = taskValue
                },
                hint = "Enter your initial task group name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(taskGroupFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(emailFiledKey.bottom, margin = 20.dp)
                        bottom.linkTo(passwordFiledKey.top)
                    },
                imeActionCallBack = {

                }, imeAction = ImeAction.Next
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
                        top.linkTo(taskGroupFiledKey.bottom, margin = 20.dp)
                    },
                isPasswordShow = isPasswordShow,
                passwordIconChange = {
                    isPasswordShow = !isPasswordShow
                }, imeActionCallBack = {

                }, imeAction = ImeAction.Next)
            UserInputPassword(value = confirmpassword,
                onValueChange = { confirmPasswordValue ->
                    confirmpassword = confirmPasswordValue
                },
                hint = "Enter your confirm password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(confirmPasswordFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(passwordFiledKey.bottom, margin = 20.dp)
                    },
                isPasswordShow = isConfirmPasswordShow,
                passwordIconChange = {
                    isConfirmPasswordShow = !isConfirmPasswordShow
                }, imeActionCallBack = {

                }, imeAction = ImeAction.Done)

            CustomAuthButton(title = "Sign up", onTap = {
                Util.validateRegisterData(
                    name = name,
                    email = email,
                    password = password,
                    confirmPassword = confirmpassword,
                    taskGroup = taskGroupName,
                    scope = scope,
                    snackState = snackBarHostState, onSuccess = {
                        scope.launch {
                            snackBarHostState.showSnackbar(message = "Register Success")
                        }
                    })
            }, modifier = widthModifier
                .height(50.dp)
                .padding(horizontal = 20.dp)
                .constrainAs(signInButtonKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(confirmPasswordFiledKey.bottom, margin = 30.dp)
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
                modifier = UiConstant.widthModifier.constrainAs(signInOptionRowKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(continueLabelKey.bottom, margin = 20.dp)
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

            AuthBottomAccountStatusWidget(authMessage = "Already have an account?",
                authLabel = "Login",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .constrainAs(registerNowLabelKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(signInOptionRowKey.bottom, margin = 10.dp)
                        bottom.linkTo(parent.bottom)
                    },
                onTap = {
                    controller.navigate(Routes.LoginRoute.route) {
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