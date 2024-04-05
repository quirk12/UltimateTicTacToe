package com.example.ultimatetictactoe.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Instructions(
    @StringRes val text: Int,
    @DrawableRes val image: Int
)
