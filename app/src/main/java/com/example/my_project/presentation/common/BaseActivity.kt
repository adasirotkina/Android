package com.example.my_project.presentation.common

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    val tag: String
    get() = this::class.qualifiedName ?: ""

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(tag, "onCreate $savedInstanceState")
        super.onCreate(savedInstanceState)
    }

    override fun onRestart() {
        Log.d(tag, "onRestart")
        super.onRestart()
    }

    override fun onStart() {
        Log.d(tag, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(tag, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(tag, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(tag, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(tag, "onDestroy")
        super.onDestroy()
    }

}