package com.developers.todoappwithcompose.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.developers.todoappwithcompose.R

val appFontFamily = FontFamily(
    Font(
        resId = R.font.poppins_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.rta_regular,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),

    Font(
        resId = R.font.brandon_medium,
        weight = FontWeight.W900,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.poppins,
        weight = FontWeight.W900,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.israr_syria,
        weight = FontWeight.Thin,
        style = FontStyle.Normal
    )
)