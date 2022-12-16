package com.example.my_project

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            delay(1000)
            println("Word")
        }
        println("Hello")
    }
}

suspend fun printWorld(){
    delay(1000)
    println("Word")
}