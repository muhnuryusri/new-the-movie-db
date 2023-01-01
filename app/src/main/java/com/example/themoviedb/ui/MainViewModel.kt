package com.example.themoviedb.ui

import androidx.lifecycle.ViewModel
import com.example.themoviedb.data.MoviesRepository

class MainViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getGenres() = moviesRepository.getGenres()
}