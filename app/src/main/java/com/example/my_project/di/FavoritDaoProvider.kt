package com.example.my_project.di

import android.content.Context
import com.example.my_project.data.local.FavoritesDaoImp
import com.example.my_project.domain.FavoritesDao

object FavoritesDaoProvider {

    private var dao: FavoritesDao?=null

    fun getDao(context: Context): FavoritesDao {
        return dao ?: FavoritesDaoImp(
            context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        ).also { dao = it }
    }
}