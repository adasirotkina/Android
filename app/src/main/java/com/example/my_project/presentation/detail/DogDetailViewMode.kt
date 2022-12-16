package com.example.my_project.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_project.domain.FavoritesDao
import com.example.my_project.entity.Dog
import kotlinx.coroutines.launch

class DogDetailViewMode(
    private val dog: Dog,
    private val favoritesDao: FavoritesDao,
): ViewModel() {

    private val _isInFavorites = MutableLiveData<Boolean>()
    val isInFavorites: LiveData<Boolean> = _isInFavorites

    init {
        viewModelScope.launch {
            _isInFavorites.value = favoritesDao.isInFavorite(dog)
        }
    }

    fun onFavoritesclick() {
        viewModelScope.launch {
            val isInFavorites = _isInFavorites.value ?: false
            if (isInFavorites) {
                favoritesDao.delete(dog)
            }
            else {
                favoritesDao.add(dog)
            }
            _isInFavorites.value = !isInFavorites
        }
    }
}