package com.example.buildgrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Topic(
    @StringRes val name: Int,
    val availableCourse: Int,
    @DrawableRes val imageRes: Int
)