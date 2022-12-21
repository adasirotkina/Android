package com.example.my_project.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_project.domain.AllDogTypes
import com.example.my_project.domain.DogReprository
import com.example.my_project.entity.Dog
import com.example.my_project.presentation.common.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SearchResultViewModel(
    private val dogReprository: DogReprository
): ViewModel() {
    private val _state = MutableLiveData<SearchResultState>(SearchResultState.Loading)
    val state: LiveData<SearchResultState> = _state

    private val _openDetail = SingleLiveEvent<Dog>()
    val openDetail: LiveData<Dog> = _openDetail

    init {
        loadData()
    }

    fun onDogClick(dog: Dog) {
        _openDetail.value = dog
    }

    fun onRetry() {
        _state.value = SearchResultState.Loading
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _state.value = SearchResultState.Error(throwable)
        }) {
            val dogs = dogReprository.getAllDog(
                AllDogTypes.ALL
            )
            _state.value = SearchResultState.Succes(dogs)
        }

    }
}

sealed interface SearchResultState{
    object Loading:SearchResultState
    class Error(val throwable: Throwable): SearchResultState
    class Succes(val dogs:List<Dog>):SearchResultState
}