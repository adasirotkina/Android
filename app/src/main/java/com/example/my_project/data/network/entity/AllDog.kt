package com.example.my_project.data.network.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllDog(
    @SerialName("age")
    val age: Int?,
    @SerialName("gender")
    val gender: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?
)