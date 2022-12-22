package com.example.my_project.domain

import androidx.lifecycle.LiveData
import com.example.my_project.entity.Dog

interface DogReprository {

    suspend fun getAllDog(
//        type: AllDogTypes, limit: Int = 100
    ): List<Dog>
}

enum class AllDogTypes {
    ANIMALS
}

