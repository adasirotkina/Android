package com.example.my_project.data

import com.example.my_project.data.network.DogApi
import com.example.my_project.domain.DogReprository
import com.example.my_project.entity.Dog


class DogRepositoryImp(
    private val dogApi: DogApi
): DogReprository {
//
//    private val retrofit = NetworkProvider.getDogRepository()
//    private val dogApi = retrofit.create(DogApi::class.java)

    override suspend fun getAllDog(
//        type: AllDogTypes, limit: Int
    ) : List<Dog> {
        val response = dogApi.getAllDogs(
//            type.name.uppercase(), limit
        )
        println("dogs ${response}")
        val dogsNw = response.dogs

        return dogsNw?.mapNotNull {
            Dog(
                name = it?.name ?: return@mapNotNull null,
                age = it?.age ?: return@mapNotNull null,
                posterUrl = it?.image ?: return@mapNotNull null,
                description = it?.description ?: return@mapNotNull null,
                contact = it?.contact ?: return@mapNotNull null,
            )
        } ?: emptyList()
    }

}