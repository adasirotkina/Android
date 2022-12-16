package com.example.my_project.data

import com.example.my_project.data.network.DogApi
import com.example.my_project.domain.DogReprository
import com.example.my_project.domain.RandomDogTypes
import com.example.my_project.entity.Dog

class DogRepositoryImp(
    private val dogApi: DogApi
): DogReprository {

    override suspend fun getAllDog() : List<Dog> {
        val response = dogApi.getAllDogs()
        val dogsNw = response.allDogs
        return dogsNw?.mapNotNull {
            Dog(
                name = it?.name ?: return@mapNotNull null,
                age = it.age ?: return@mapNotNull null,
                posterUrl = null,
            )
        } ?: emptyList()
    }

}