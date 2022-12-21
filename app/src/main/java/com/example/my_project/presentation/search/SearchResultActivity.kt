package com.example.my_project.presentation.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_project.R
import com.example.my_project.SearchType
import com.example.my_project.databinding.ActivitySearchResultBinding
import com.example.my_project.di.NetworkProvider
import com.example.my_project.entity.Dog
import com.example.my_project.presentation.common.BaseActivity
import com.example.my_project.presentation.detail.DogDetailActivity
import com.example.my_project.presentation.random_dog.RandomDogAdapter

class SearchResultActivity : BaseActivity() {


    private val viewModel by viewModels<SearchResultViewModel>{
        object : ViewModelProvider.Factory {
            override fun <T: ViewModel> create(modelClass: Class<T>):T {
                return SearchResultViewModel(
                    NetworkProvider.getDogRepository()
                ) as T
            }
        }
    }
    private val viewBinding by viewBinding(ActivitySearchResultBinding::bind)
    private val randomDogAdapter = RandomDogAdapter {viewModel.onDogClick(it)}

//    private val ageFrom:String = intent.getStringExtra("ageFrom").toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val ageFrom = intent.getIntExtra("ageFrom", 0)
        val ageTo = intent.getIntExtra("ageTo", 30)
        val gender = intent.getStringExtra("gender")
//        val searchTyp = intent.getStringExtra("searchTyp")

//        val typeDict = mapOf()



        with(viewBinding.searchResultList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = randomDogAdapter
        }

        viewModel.state.observe(this) {state ->
            when(state){
                is SearchResultState.Error -> {
                    viewBinding.searchResultList.isVisible = false
                    viewBinding.searchResultTitle.isVisible = true
//                    viewBinding.randomDogError.setOnClickListener {viewModel.onRetry()}
                }
                SearchResultState.Loading -> {
                    viewBinding.searchResultList.isVisible = true
                    viewBinding.searchResultTitle.isVisible = true
                }
                is SearchResultState.Succes -> {
                    viewBinding.searchResultList.isVisible = true
                    viewBinding.searchResultTitle.isVisible = true

                    val newDogs = state.dogs

                    println(gender)

                    val newDogsFiltered = newDogs.filter { if (gender == "ALL") {
                        (it.age?.toInt()!! >= ageFrom) && (it.age?.toInt()!! <= ageTo)
                    }
                    else {
                        (it.age?.toInt()!! >= ageFrom) && (it.age?.toInt()!! <= ageTo) &&(it.gender?.uppercase() == gender)
                    }
                    }


                    randomDogAdapter.setData(newDogsFiltered)
                }
            }
        }
        viewModel.openDetail.observe(this) {
            openDetail(it)
        }

        viewBinding.searchResultBack.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(DogDetailActivity.DOG_DETAIL_RESULT_KEY, 10))
            finish()
        }
    }

    private fun openDetail(dog: Dog) {
        startActivity(
            Intent(this, DogDetailActivity::class.java)
                .apply {
                    putExtra(DogDetailActivity.DOG_DETAIL_ARGUMENT_KEY, dog )
                },
        )
    }

}