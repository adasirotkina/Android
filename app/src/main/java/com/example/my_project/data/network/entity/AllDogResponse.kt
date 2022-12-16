package com.example.my_project.data.network.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllDogResponse(
    @SerialName("all_dogs")
    val allDogs: List<AllDog?>?
)