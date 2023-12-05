package com.example.calendarapp.screens.auth

import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.components.CustomAuthButton
import com.example.calendarapp.components.CustomTextButton
import com.example.calendarapp.components.UserInput
import com.example.calendarapp.components.UserInputPassword
import com.example.calendarapp.navigation.routes.Routes
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant.robotoFontFamily
import com.example.calendarapp.util.UiConstant.rubikBubblesFontFamily
import com.example.calendarapp.util.UiConstant.widthModifier
import com.example.calendarapp.util.Util

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
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
                /*
                snackbar = {
                    Surface(modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(50.dp).padding(horizontal = 20.dp).then(Modifier.padding(bottom = 10.dp)), shape = RoundedCornerShape(5.dp), color = MaterialTheme.colorScheme.primary) {
                        Text(text =it.visuals.message, style = MaterialTheme.typography.titleMedium, modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
                    }
                }

                 */
            )
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val (loginTitle1Key, loginTitle2Key, emailFiledKey, passwordFiledKey, forgotKey, signInButtonKey, continueLabelKey, signInOptionRowKey, registerNowLabelKey, circularIndicatorKey) = createRefs()

            Text(
                text = "Hello Again!",
                style = MaterialTheme.typography.headlineMedium.copy(fontFamily = rubikBubblesFontFamily),
                textAlign = TextAlign.Center, modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(loginTitle1Key) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = 100.dp)
                    },
                color = MaterialTheme.colorScheme.primaryContainer
            )
            Text(
                text = "welcome back to task tracker app",
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = robotoFontFamily),
                textAlign = TextAlign.Center, modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(loginTitle2Key) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(loginTitle1Key.bottom, margin = 10.dp)
                        bottom.linkTo(emailFiledKey.top)
                    }, color = MaterialTheme.colorScheme.primaryContainer
            )
            UserInput(
                value = email,
                onValueChange = {
                    email = it
                },
                hint = "Enter your email",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(emailFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(loginTitle2Key.bottom, margin = 50.dp)
                        bottom.linkTo(passwordFiledKey.top)
                    },
                imeAction = {

                }
            )

            UserInputPassword(value = password,
                onValueChange = { password = it },
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
                }, isConfirmPassword = true, imeAction = {
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
                })
            Text(
                text = "Forgot password",
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFontFamily),
                textAlign = TextAlign.Center, modifier = Modifier.constrainAs(forgotKey) {
                    end.linkTo(parent.end, defaultMargin)
                    top.linkTo(passwordFiledKey.bottom, margin = 10.dp)
                    bottom.linkTo(signInButtonKey.top)
                }, color = MaterialTheme.colorScheme.primaryContainer

            )

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .constrainAs(continueLabelKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(signInButtonKey.bottom, margin = 30.dp)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Divider(
                    modifier = Modifier.weight(2.5f),
                    thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer
                )
                Text(
                    text = "or continue with",
                    style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFontFamily),
                    modifier = Modifier.weight(4f),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Divider(
                    modifier = Modifier.weight(2.5f),
                    thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer
                )
            }

            Row(
                modifier = widthModifier.constrainAs(signInOptionRowKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(continueLabelKey.bottom, margin = 30.dp)
                },
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                ElevatedButton(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.White,
                    ),
                    modifier = Modifier.size(70.dp),
                    elevation = defaultButtonElevation

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google_icon),
                        contentDescription = "google_icon"
                    )
                }
                ElevatedButton(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.White,
                    ),
                    modifier = Modifier.size(70.dp),
                    elevation = defaultButtonElevation

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_apple_icon),
                        contentDescription = "google_icon"
                    )
                }
                ElevatedButton(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.White,
                    ),
                    modifier = Modifier.size(70.dp),
                    elevation = defaultButtonElevation
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook_icon),
                        contentDescription = "google_icon", modifier = Modifier.fillMaxSize(),
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .constrainAs(registerNowLabelKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(signInOptionRowKey.bottom, margin = 30.dp)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Not any account?",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.width(5.dp))
                CustomTextButton(
                    title = "Register now",
                    onClick = {
                        controller.navigate(Routes.RegisterRoute.route) {
                            popUpTo(controller.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }, textStyle = MaterialTheme.typography.titleMedium.copy(
                        color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor,
                        fontSize = 20.sp
                    )
                )
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.constrainAs(circularIndicatorKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }, color = if (isSystemInDarkTheme()) primaryDarkColor else primaryLightColor
                )
            }

        }
    }
}
