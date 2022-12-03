package com.example.my_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : BaseActivity() {

    companion object {
        private val COUNTER_KEY = "COUNTER_KEY"
    }

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button? = findViewById<Button>(R.id.mainCounterAdd)
        button?.setOnClickListener {
            add()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNTER_KEY, counter)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        counter = savedInstanceState.getInt(COUNTER_KEY, 0)
        updateCounter()
        super.onRestoreInstanceState(savedInstanceState)
    }
        private fun add(){
            counter += 1
            updateCounter()
        }

        private fun updateCounter() {
            findViewById<TextView>(R.id.mainCounterText)?.apply {
                text = counter.toString()

        }
    }
}

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