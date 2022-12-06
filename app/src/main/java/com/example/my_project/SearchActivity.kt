package com.example.my_project

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_project.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {

    private val viewModel by viewModels<SearchViewModel>()
    private val viewBinding by viewBinding(ActivitySearchBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        viewBinding.searchSubmit.setOnClickListener {
            viewModel.onSubmit(
                searchTyp = when (viewBinding.searchDogTypeGroup.checkedRadioButtonId) {
                    R.id.search_dog_type_group_all -> SearchType.ALL
                    R.id.search_dog_type_group_female -> SearchType.FEMALE
                    else -> SearchType.MALE
                },
                ageFrom = (viewBinding.searchAgeFromSpinner.selectedItem as String).toInt(),
                ageTo = (viewBinding.searchAgeToSpinner.selectedItem as String).toInt(),
                sizeFrom = viewBinding.searchSizeFromEdit.text.toString(),
                sizeTo = viewBinding.searchSizeToEdit.text.toString(),
            )
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

        viewModel.loading.observe(this) {
            viewBinding.searchProgress.isVisible = it
            viewBinding.searchClickStub.isVisible = it

        }
        viewModel.errorMessage.observe(this) {
            showError(it.toText())
        }
    }
    private fun showError(text: String){
        println("Error4 $text")
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
    private fun SearchErrorType.toText(): String {
        return when (this){
            SearchErrorType.SIZE_FROM_MORE_THAN_TO -> getString(R.string.search_year_from_more_than_to)
        }
    }
}

