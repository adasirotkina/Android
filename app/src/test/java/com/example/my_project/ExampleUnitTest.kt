package com.example.my_project

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun multiplication_test(){
        assertEquals(4, 2 * 2)
    }


    @Test
    fun increment_test(){
        var d = 2
        assertEquals(3, d++)
    }

    @Test
    fun other_test(){
        assertEquals(3, 1 * 2 + 1)
    }
}