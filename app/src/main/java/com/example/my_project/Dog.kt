package com.example.my_project

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Dog (
    val name: String,
    val age: Int,
) : Parcelable