package com.example.themoviedb.utils

import com.example.themoviedb.model.GenresItem

interface ItemCallback {
    fun onGenreClicked(data: GenresItem)
}