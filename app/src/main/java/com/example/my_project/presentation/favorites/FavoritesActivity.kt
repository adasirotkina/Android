package com.example.my_project.presentation.favorites

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_project.R
import com.example.my_project.databinding.ActivityFavoritesBinding
import com.example.my_project.di.FavoritesDaoProvider
import com.example.my_project.entity.Dog
import com.example.my_project.presentation.common.BaseActivity
import com.example.my_project.presentation.detail.DogDetailActivity
import com.example.my_project.presentation.random_dog.RandomDogAdapter

class FavoritesActivity : BaseActivity() {



    private val viewMode by viewModels<FavoritesViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T: ViewModel> create(modelClass: Class<T>):T {
                return FavoritesViewModel(
                    FavoritesDaoProvider.getDao(this@FavoritesActivity)
                ) as T
            }
        }
    }

    private val viewBinding by viewBinding(ActivityFavoritesBinding::bind)
    private val favoritesAdapter = RandomDogAdapter {viewMode.onDogClick(it)}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        with(viewBinding.favoritesList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = favoritesAdapter
        }
        viewBinding.favoritesBack.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(DogDetailActivity.DOG_DETAIL_RESULT_KEY, 10))
            finish()
        }
        viewMode.openDetail.observe(this) {
            openDetail(it)
        }
        viewMode.state.observe(this) {
            favoritesAdapter.setData(it)
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