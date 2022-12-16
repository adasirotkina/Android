package com.example.my_project.di

import com.example.my_project.data.DogRepositoryImp
import com.example.my_project.data.network.DogApi
import com.example.my_project.domain.DogReprository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

object NetworkProvider {

    private val dogApi: DogApi =
        Retrofit.Builder()
            .baseUrl("http://localhost:3000")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor {chain ->
                        val request = chain.request()
                            .newBuilder()
//                            .addHeader()
                            .build()

                        chain.proceed(request)
                    }
                    .build()
            )
            .addConverterFactory(
                Json(builderAction = {
                isLenient = true
                    ignoreUnknownKeys = true
                }).asConverterFactory(MediaType.parse("application/json")!!)
            )
            .build()
            .create()
    private var dogRepository : DogReprository?= null

    fun getDogRepository(): DogReprository =
        dogRepository ?: DogRepositoryImp(dogApi).also { dogRepository = it  }
}