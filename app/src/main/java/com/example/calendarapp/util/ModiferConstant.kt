package com.example.calendarapp.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.calendarapp.R

object UiConstant {


    //dimension constant
    val widthModifier= Modifier.fillMaxWidth()

    //font family constant
    val robotoFontFamily= FontFamily(
        Font(R.font.roboto_bold))

    val rubikBubblesFontFamily=FontFamily(
        Font(R.font.rubik_bubbles_regular)
    )

    val novaFontFamily=FontFamily(
        Font(R.font.nova_square_regular)
    )

    val hedvig_letters_serif_regular=FontFamily(
        Font(R.font.hedvig_letters_serif_regular)
    )
}