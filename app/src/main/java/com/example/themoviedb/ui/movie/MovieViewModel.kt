package com.example.themoviedb.ui.movie

import androidx.lifecycle.ViewModel
import com.example.themoviedb.data.MoviesRepository

class MovieViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getMovies(genreId: Int) = moviesRepository.getMovies(genreId)
}