package com.example.my_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_project.presentation.common.SingleLiveEvent

class SearchViewModel: ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = SingleLiveEvent<SearchErrorType>()
    val errorMessage: LiveData<SearchErrorType> = _errorMessage

    override fun onCleared() {
        super.onCleared()
    }

    fun onSubmit(searchTyp: SearchType,
                 ageFrom:Int,
                 ageTo:Int,) {

//        val sizeFromInt = sizeFrom.toIntOrNull()
//        val sizeToInt = sizeTo.toIntOrNull()
//
//        if (sizeFromInt != null && sizeToInt != null && sizeFromInt > sizeToInt) {
//            _errorMessage.value =  SearchErrorType.SIZE_FROM_MORE_THAN_TO
//            return
//        }


        if (ageTo <= 0) {
            _errorMessage.value =  SearchErrorType.AGE_TO_BELLOW_ONE
            return
        }

        _loading.value = true
    }
}

enum class SearchType{
    ALL, FEMALE, MALE
}

enum class SearchErrorType{
    SIZE_FROM_MORE_THAN_TO, AGE_TO_BELLOW_ONE
}