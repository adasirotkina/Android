package com.example.my_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.ActionMode
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.my_project.DogDetailActivity.Companion.DOG_DETAIL_RESULT_KEY

class MainActivity : BaseActivity() {

    companion object {
        private const val COUNTER_KEY = "COUNTER_KEY"
        private const val DOG_DETAIL_REQUEST_CODE = 10
    }

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button? = findViewById<Button>(R.id.mainCounterAdd)
        button?.setOnClickListener {
            add()
        }

        findViewById<Button>(R.id.mainDetail)?.setOnClickListener {
            openDetail()
        }

        findViewById<Button>(R.id.main_search)?.setOnClickListener {
            openSearch()
        }
    }

    private fun openDetail() {
        val dog = Dog("Baks",
             10)
        startActivityForResult(
            Intent(this, DogDetailActivity::class.java)
                .apply {
                    putExtra(DogDetailActivity.DOG_DETAIL_ARGUMENT_KEY, dog )
                },
            DOG_DETAIL_REQUEST_CODE
        )
    }

    private fun openSearch() {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == DOG_DETAIL_REQUEST_CODE) {
            val dataValue = data?.getIntExtra(DogDetailActivity.DOG_DETAIL_RESULT_KEY, 0)
            if (dataValue != null) {
                showResult(dataValue)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun showResult(result:Int){
        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
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