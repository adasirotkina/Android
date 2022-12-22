package com.example.my_project.presentation.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_project.*
import com.example.my_project.databinding.ActivitySearchBinding
import com.example.my_project.presentation.common.BaseActivity
import com.example.my_project.presentation.detail.DogDetailActivity
import com.example.my_project.presentation.favorites.FavoritesActivity
import com.example.my_project.presentation.random_dog.RandomDogAdapter

class SearchActivity : BaseActivity() {

    private val viewModel by viewModels<SearchViewModel>()
    private val viewBinding by viewBinding(ActivitySearchBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        viewBinding.searchSubmit.setOnClickListener {
            viewModel.onSubmit(
                searchTyp = when (viewBinding.searchDogTypeGroup.checkedRadioButtonId) {
                    R.id.search_dog_type_group_male -> SearchType.MALE
                    R.id.search_dog_type_group_female -> SearchType.FEMALE
                    else -> SearchType.ALL
                },
                ageFrom = (viewBinding.searchAgeFromSpinner.selectedItem as String).toInt(),
                ageTo = (viewBinding.searchAgeToSpinner.selectedItem as String).toInt(),

                )
            val searchTyp = when (viewBinding.searchDogTypeGroup.checkedRadioButtonId) {
                R.id.search_dog_type_group_male -> SearchType.MALE
                R.id.search_dog_type_group_female -> SearchType.FEMALE
                else -> SearchType.ALL }
            val ageFrom = (viewBinding.searchAgeFromSpinner.selectedItem as String).toInt()
            val ageTo = (viewBinding.searchAgeToSpinner.selectedItem as String).toInt()

            if (ageTo != 0) {
                openResult(ageFrom, ageTo, searchTyp.toString())
            }
//            else {
//                Toast.makeText(this, SearchErrorType.AGE_TO_BELLOW_ONE.toText(), Toast.LENGTH_LONG).show()
//            }

        }

        viewBinding.searchAgeFromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val ageFromPosition = viewBinding.searchAgeFromSpinner.selectedItemPosition
                val ageToPosition = viewBinding.searchAgeToSpinner.selectedItemPosition

                if (ageFromPosition > ageToPosition) {
                    viewBinding.searchAgeToSpinner.setSelection(ageFromPosition)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        viewBinding.searchAgeToSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val ageFromPosition = viewBinding.searchAgeFromSpinner.selectedItemPosition
                val ageToPosition = viewBinding.searchAgeToSpinner.selectedItemPosition

                if (ageFromPosition > ageToPosition) {
                    viewBinding.searchAgeFromSpinner.setSelection(ageToPosition)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

//        viewModel.loading.observe(this) {
//            viewBinding.searchProgress.isVisible = it
//            viewBinding.searchClickStub.isVisible = it
//
//        }

        viewBinding.searchBack.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(DogDetailActivity.DOG_DETAIL_RESULT_KEY, 10))
            finish()
        }

        viewModel.errorMessage.observe(this) {
            showError(it.toText())
        }
    }

    private fun openResult(ageFrom: Int, ageTo: Int, gender: String) {
        startActivity(Intent(this, SearchResultActivity::class.java).apply {
//            putExtra("name", name)
            putExtra("ageTo", ageTo)
            putExtra("ageFrom", ageFrom)
            putExtra("gender", gender)
        })
    }

    private fun showError(text: String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
    private fun SearchErrorType.toText(): String {
        return when (this){
            SearchErrorType.SIZE_FROM_MORE_THAN_TO -> getString(R.string.search_year_from_more_than_to)
            SearchErrorType.AGE_TO_BELLOW_ONE -> "Возраст ДО не может быть меньше 1"
        }
    }
}

