package com.example.my_project.presentation.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_project.presentation.common.BaseActivity
import com.example.my_project.entity.Dog
import com.example.my_project.R
import com.example.my_project.databinding.ActivityDogDetailBinding
import com.example.my_project.di.FavoritesDaoProvider
import com.example.my_project.presentation.common.setImageUrl


class DogDetailActivity : BaseActivity() {

    companion object {
        const val DOG_DETAIL_ARGUMENT_KEY = "DOG_DETAIL_ARGUMENT_KEY"
        const val DOG_DETAIL_RESULT_KEY = "DOG_DETAIL_RESULT_KEY"
    }


    private val viewMode by viewModels<DogDetailViewMode> {
        object : ViewModelProvider.Factory {
            override fun <T: ViewModel> create(modelClass: Class<T>):T {
                return DogDetailViewMode(
                    intent.getParcelableExtra(DOG_DETAIL_ARGUMENT_KEY)!!,
                    FavoritesDaoProvider.getDao(this@DogDetailActivity)
                ) as T
            }
        }
    }
    private val viewBinding by viewBinding(ActivityDogDetailBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_detail)
        val dog = intent.getParcelableExtra<Dog>(DOG_DETAIL_ARGUMENT_KEY)

        fun ageToText(age: String): String {
            if (age.toInt() == 0) return "меньше года"
            else if (age.toInt() == 1) return "$age год"
            else if (age.toInt() in 2..4) return "$age года"
            else return "${age} лет"
        }

        fun genderToText(gender: String?): String {
            if (gender == "male") return "мальчик"
            else return "девочка"
        }

        viewBinding.dogName.text = dog?.name
        viewBinding.dogAge.text = ageToText(dog?.age!!.filter { it.isDigit() } )
        viewBinding.dogGender.text = genderToText(dog?.gender)
        viewBinding.dogDetailPosters.setImageUrl(dog?.posterUrl)
        viewBinding.dogDescription.text = dog?.description
        viewBinding.dogContact.text = "По всем вопросам: ${dog?.contact}"

        viewBinding.dogDetailBack.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(DOG_DETAIL_RESULT_KEY, 10))
            finish()
        }

        viewBinding.dogDetailFavorites.setOnClickListener {
            viewMode.onFavoritesclick()
        }

        viewMode.isInFavorites.observe(this) {
            viewBinding.dogDetailFavorites.setImageResource(
                if (it) {
                    R.drawable.ic_favorite
                } else {
                    R.drawable.ic_non_favorite
                }
            )
        }
    }
}