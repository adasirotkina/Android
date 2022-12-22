package com.example.my_project.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.my_project.presentation.common.BaseActivity
import com.example.my_project.Dog
import com.example.my_project.R

class DogDetailActivity : BaseActivity() {

    companion object {
        const val DOG_DETAIL_ARGUMENT_KEY = "DOG_DETAIL_ARGUMENT_KEY"
        const val DOG_DETAIL_RESULT_KEY = "DOG_DETAIL_RESULT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_detail)
        val dog = intent.getParcelableExtra<Dog>(DOG_DETAIL_ARGUMENT_KEY)

        findViewById<TextView>(R.id.dog_name)?.text = dog?.name
        findViewById<Button>(R.id.dog_detail_back)?.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(DOG_DETAIL_RESULT_KEY, 10))
            finish()
        }
    }
}