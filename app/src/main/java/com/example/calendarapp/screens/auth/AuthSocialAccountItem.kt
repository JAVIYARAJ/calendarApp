package com.example.calendarapp.screens.auth

import androidx.annotation.DrawableRes
import com.example.calendarapp.R

data class AuthSocialAccountItem(
    val socialAccountType: SocialAccountType,
    @DrawableRes val socialAccountIcon: Int
)

enum class SocialAccountType {
    GOOGLE,
    APPLE,
    FACEBOOK
}

val socialAuthAccounts = listOf(
    AuthSocialAccountItem(
        socialAccountType = SocialAccountType.GOOGLE,
        socialAccountIcon = R.drawable.ic_google_icon
    ),
    AuthSocialAccountItem(
        socialAccountType = SocialAccountType.APPLE,
        socialAccountIcon = R.drawable.ic_apple_icon
    ),
    AuthSocialAccountItem(
        socialAccountType = SocialAccountType.FACEBOOK,
        socialAccountIcon = R.drawable.ic_facebook_icon
    )
)