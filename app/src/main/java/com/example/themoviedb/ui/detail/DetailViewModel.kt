package com.example.themoviedb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.data.MoviesRepository
import com.example.themoviedb.model.MoviesItem
import com.example.themoviedb.model.VideosItem

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getDetailMovie(movieId: Int): LiveData<MoviesItem> = moviesRepository.getDetailMovie(movieId)
    fun getReviews(reviewId: Int) = moviesRepository.getReview(reviewId)
    fun getTrailer(movieId: Int): LiveData<VideosItem> = moviesRepository.getTrailer(movieId)
}