package com.example.my_project.data.network.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    @SerialName("age")
    val age: String?,
    @SerialName("contact")
    val contact: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("gender")
    val gender: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("name")
    val name: String?
)