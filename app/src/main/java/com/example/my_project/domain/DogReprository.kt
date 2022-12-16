package com.example.my_project.domain

import androidx.lifecycle.LiveData
import com.example.my_project.entity.Dog

interface DogReprository {

    suspend fun getAllDog(): List<Dog>
}

