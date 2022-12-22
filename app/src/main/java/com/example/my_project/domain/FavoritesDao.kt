package com.example.my_project.domain

import androidx.lifecycle.LiveData
import com.example.my_project.entity.Dog
import kotlinx.coroutines.flow.Flow

interface FavoritesDao {

    suspend fun add(dog: Dog)

    suspend fun delete(dog: Dog)

    suspend fun isInFavorite(dog: Dog): Boolean

    fun getAll(): LiveData<List<Dog>>



}