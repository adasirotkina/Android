package com.example.my_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_project.databinding.ActivityRandomDogsBinding
import com.example.my_project.di.FavoritesDaoProvider
import com.example.my_project.di.NetworkProvider
import com.example.my_project.entity.Dog
import com.example.my_project.presentation.common.BaseActivity
import com.example.my_project.presentation.detail.DogDetailActivity
import com.example.my_project.presentation.favorites.FavoritesActivity
import com.example.my_project.presentation.favorites.FavoritesViewModel
import com.example.my_project.presentation.random_dog.RandomDogAdapter
import com.example.my_project.presentation.search.SearchActivity

class RandomDogActivity : BaseActivity() {

    private val viewModel by viewModels<RandomDogViewModel>{
        object : ViewModelProvider.Factory {
            override fun <T: ViewModel> create(modelClass: Class<T>):T {
                return RandomDogViewModel(
                    NetworkProvider.getDogRepository()
                ) as T
            }
        }
    }
    private val viewBinding by viewBinding(ActivityRandomDogsBinding::bind)
    private val randomDogAdapter = RandomDogAdapter {viewModel.onDogClick(it)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_dogs)

        viewBinding.randomDogsSearch.setOnClickListener {
            openSearch()
        }

        with(viewBinding.randomDogsList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = randomDogAdapter
        }

        viewBinding.randomDogsFavorites.setOnClickListener {
            openFavorites()
        }
        viewModel.dogs.observe(this) {
            dogs -> randomDogAdapter.setData(dogs)
        }
        viewModel.openDetail.observe(this) {
            openDetail(it)
        }
    }

    private fun openFavorites() {
        startActivity(Intent(this, FavoritesActivity::class.java))
    }

    private fun openDetail(dog: Dog) {
        startActivity(
            Intent(this, DogDetailActivity::class.java)
                .apply {
                    putExtra(DogDetailActivity.DOG_DETAIL_ARGUMENT_KEY, dog )
                },
        )
    }

    private fun openSearch() {
        startActivity(Intent(this, SearchActivity::class.java))
    }
}

