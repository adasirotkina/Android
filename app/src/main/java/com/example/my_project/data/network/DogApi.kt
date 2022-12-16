package com.example.my_project.data.network

import com.example.my_project.data.network.entity.AllDogResponse
import retrofit2.http.GET

interface DogApi {

    @GET("all_dogs")
    suspend fun getAllDogs(): AllDogResponse
}