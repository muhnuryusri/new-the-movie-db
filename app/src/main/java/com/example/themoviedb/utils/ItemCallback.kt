package com.example.themoviedb.utils

import com.example.themoviedb.model.GenresItem
import com.example.themoviedb.model.MoviesItem

interface ItemCallback {
    fun onGenreClicked(data: GenresItem)
    fun onMovieClicked(data: MoviesItem)
}