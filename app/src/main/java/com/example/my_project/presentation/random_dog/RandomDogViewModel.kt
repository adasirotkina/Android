package com.example.my_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_project.domain.DogReprository
import com.example.my_project.entity.Dog
import com.example.my_project.presentation.common.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class RandomDogViewModel(
    private val dogReprository: DogReprository
): ViewModel() {
    private val _state = MutableLiveData<RandomDogState>(RandomDogState.Loading)
    val state: LiveData<RandomDogState> = _state

    private val _openDetail = SingleLiveEvent<Dog>()
    val openDetail: LiveData<Dog> = _openDetail

    init {
        loadData()
    }

    fun onDogClick(dog: Dog) {
        _openDetail.value = dog
    }

    fun onRetry() {
        _state.value = RandomDogState.Loading
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _state.value = RandomDogState.Error(throwable)
        }) {
            val dogs = dogReprository.getAllDog(
//                AllDogTypes.ANIMALS, 100
            )
            _state.value = RandomDogState.Succes(dogs)
        }

    }
}

sealed interface RandomDogState{
    object Loading:RandomDogState
    class Error(val throwable: Throwable): RandomDogState
    class Succes(val dogs:List<Dog>):RandomDogState
}