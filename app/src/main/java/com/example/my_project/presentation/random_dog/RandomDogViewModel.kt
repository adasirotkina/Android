package com.example.my_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_project.domain.DogReprository
import com.example.my_project.domain.RandomDogTypes
import com.example.my_project.entity.Dog
import com.example.my_project.presentation.common.SingleLiveEvent
import kotlinx.coroutines.launch

class RandomDogViewModel(
    private val dogReprository: DogReprository
): ViewModel() {
    private val _dogs = MutableLiveData<List<Dog>>()
    val dogs: LiveData<List<Dog>> = _dogs

    private val _openDetail = SingleLiveEvent<Dog>()
    val openDetail: LiveData<Dog> = _openDetail

    init {
        viewModelScope.launch {
            val dogs = dogReprository.getAllDog()
            _dogs.value = dogs
        }
    }

    fun onDogClick(dog: Dog) {
        _openDetail.value = dog
    }
}