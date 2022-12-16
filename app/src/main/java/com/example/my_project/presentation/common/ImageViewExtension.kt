package com.example.my_project.presentation.common

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImafeUrl(url: String?) {

    Glide.with(this)
        .load(url)
        .into(this)

}