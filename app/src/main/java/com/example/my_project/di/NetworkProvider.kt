package com.example.my_project.di

import com.example.my_project.data.DogRepositoryImp
import com.example.my_project.data.network.DogApi
import com.example.my_project.domain.DogReprository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create

//object NetworkProvider {
//
//    private const val BASE_URL = "http://10.0.2.2:3000/"
//
//    object RequestInterceptor : Interceptor {
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val request = chain.request()
//            println("Outgoing request to ${request.url()}")
//            return chain.proceed(request)
//        }
//    }
//
//    val okHttpClient = OkHttpClient()
//        .newBuilder()
//        .addInterceptor(RequestInterceptor)
//        .build()
//
//    fun getDog(): Retrofit =
//        Retrofit.Builder()
//            .client(okHttpClient)
//            .baseUrl(BASE_URL)
//            .addConverterFactory(JacksonConverterFactory.create())
//            .build()
////    private val dogApi: DogApi =
////        Retrofit.Builder()
////            .client(okHttpClient)
////            .baseUrl(BASE_URL)
////            .addConverterFactory(JacksonConverterFactory.create())
////            .build()
//
//
//    private var dogRepository : DogReprository?= null
//
//
//    fun getDogRepository(): DogReprository =
//        dogRepository ?: DogRepositoryImp().also { dogRepository = it  }
//}

object NetworkProvider {
    private val dogApi: DogApi =
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor {chain ->
                        val request = chain.request()
                            .newBuilder()
//                            .addHeader("Authorization", "mCZJtVZx")
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