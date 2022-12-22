package com.example.my_project.data.network

import com.example.my_project.data.network.entity.AllDogResponse
import retrofit2.http.GET

interface DogApi {

    @GET("dogsfromshelters")
    suspend fun getAllDogs(
//        @Query("type") type: String,
//        @Query("limit") limit: Int,
    ): AllDogResponse
}