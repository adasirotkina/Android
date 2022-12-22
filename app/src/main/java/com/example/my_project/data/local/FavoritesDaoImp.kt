package com.example.my_project.data.local

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.my_project.domain.FavoritesDao
import com.example.my_project.entity.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FavoritesDaoImp(
    private val preferences: SharedPreferences
): FavoritesDao {

    private val dogState = MutableLiveData(dogs)

    private var dogs: List<Dog>
        get() = preferences.getString(DOGS_KEY, null)
            ?.let { Json.decodeFromString(it)}
            ?: emptyList()

        set(value) {

            preferences.edit().putString(DOGS_KEY, Json.encodeToString(value)).apply()
        }
    override suspend fun add(dog: Dog) {
        withContext(Dispatchers.IO) {
            dogs = dogs + dog
        }
        withContext(Dispatchers.Main) {
            dogState.value = dogs
        }
    }

    override suspend fun delete(dog: Dog) {
        withContext(Dispatchers.IO) {
            dogs = dogs - dog
        }
        withContext(Dispatchers.Main) {
            dogState.value = dogs
        }
    }

    override suspend fun isInFavorite(dog: Dog): Boolean {
        return withContext(Dispatchers.IO) {
            dogs.contains(dog)
        }
    }

    override fun getAll(): LiveData<List<Dog>> {
        return dogState
    }
    companion object {
        private const val DOGS_KEY = "DOGS_KEY"
    }
}