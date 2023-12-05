package com.example.calendarapp.screens.auth

import android.os.Handler
import android.os.Looper
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.calendarapp.R
import com.example.calendarapp.components.UserInput
import com.example.calendarapp.navigation.Routes
import com.example.calendarapp.ui.theme.primaryDarkColor
import com.example.calendarapp.ui.theme.primaryLightColor
import com.example.calendarapp.util.UiConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(controller: NavHostController) {
    var email by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val defaultMargin = 20.dp

    val defaultButtonElevation= ButtonDefaults.elevatedButtonElevation(
        pressedElevation = 10.dp
    )

    Scaffold {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val (loginTitle1Key, loginTitle2Key, emailFiledKey,nameFiledKey, passwordFiledKey, forgotKey, signInButtonKey, continueLabelKey, signInOptionRowKey, registerNowLabelKey, circularIndicatorKey) = createRefs()

            Text(
                text = "Hello Again!",
                style = MaterialTheme.typography.headlineMedium.copy(fontFamily = UiConstant.robotoFontFamily),
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
                text = "sign up with your task tracker app",
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = UiConstant.robotoFontFamily),
                textAlign = TextAlign.Center, modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(loginTitle2Key) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(loginTitle1Key.bottom, margin = 10.dp)
                        bottom.linkTo(nameFiledKey.top)
                    }, color = MaterialTheme.colorScheme.primaryContainer
            )
            UserInput(
                value = name,
                onValueChange = {
                    name = it
                },
                hint = "Enter your name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs(nameFiledKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(loginTitle2Key.bottom, margin = 50.dp)
                        bottom.linkTo(emailFiledKey.top)
                    },
                imeAction = {

                }
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
                        bottom.linkTo(nameFiledKey.top)
                    },
                imeAction = {

                }
            )
            UserInput(
                value = password,
                onValueChange = {
                    password = it
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
                isPassword = true,
                imeAction = {

                }
            )
            Text(
                text = "Forgot password",
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = UiConstant.robotoFontFamily),
                textAlign = TextAlign.Center, modifier = Modifier.constrainAs(forgotKey) {
                    end.linkTo(parent.end, defaultMargin)
                    top.linkTo(passwordFiledKey.bottom, margin = 10.dp)
                    bottom.linkTo(signInButtonKey.top)
                }, color = MaterialTheme.colorScheme.primaryContainer

            )

            ElevatedButton(
                onClick = {
                    isLoading = true
                    Handler(Looper.getMainLooper()).postDelayed({
                        isLoading=false
                        controller.navigate(Routes.HomeRootRoute.route)
                    },2000)
                },
                modifier = UiConstant.widthModifier
                    .height(50.dp)
                    .padding(horizontal = 20.dp)
                    .constrainAs(signInButtonKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(forgotKey.bottom, margin = 30.dp)
                    },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = primaryDarkColor,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Sign up",
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = UiConstant.robotoFontFamily),
                    color = Color.White
                )
            }
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
                    style = MaterialTheme.typography.titleMedium.copy(fontFamily = UiConstant.robotoFontFamily),
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
                modifier = UiConstant.widthModifier.constrainAs(signInOptionRowKey) {
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
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Not any account?",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "Register now",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Blue,
                        fontSize = 18.sp
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