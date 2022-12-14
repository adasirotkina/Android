package com.example.my_project.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Dog(
    val name: String,
    val age: String,
    val posterUrl: String?,
    val description: String?,
    val contact: String?,
    val gender: String?,
) : Parcelable