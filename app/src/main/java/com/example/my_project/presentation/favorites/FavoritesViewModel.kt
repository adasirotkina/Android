package com.example.my_project.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.my_project.domain.FavoritesDao
import com.example.my_project.entity.Dog
import com.example.my_project.presentation.common.SingleLiveEvent

class FavoritesViewModel(
    private val favoritesDao: FavoritesDao,
): ViewModel() {

    val state : LiveData<List<Dog>> = favoritesDao.getAll()

    private val _openDetail = SingleLiveEvent<Dog>()
    val openDetail: LiveData<Dog> = _openDetail

    fun onDogClick(dog: Dog) {
        _openDetail.value = dog
    }
}