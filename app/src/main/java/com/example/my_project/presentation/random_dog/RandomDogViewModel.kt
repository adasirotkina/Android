package com.example.my_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_project.presentation.common.SingleLiveEvent

class RandomDogViewModel(): ViewModel() {
    private val _dogs = MutableLiveData(listOf<Dog>(
        Dog("April", 2),
        Dog("Rango", 6),
        Dog("Goofy", 2),
        Dog("Kliko", 3),
        Dog("Layfa", 3),
        Dog("Vikki", 4),
        Dog("Salvador", 2),
        Dog("Ocean", 1),
        Dog("Night", 1),
        Dog("Fiksik", 10),
    ))

    val dogs: LiveData<List<Dog>> = _dogs

    private val _openDetail = SingleLiveEvent<Dog>()
    val openDetail: LiveData<Dog> = _openDetail

    fun onDogClick(dog: Dog) {
        _openDetail.value = dog
    }
}