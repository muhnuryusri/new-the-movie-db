package com.example.themoviedb.data.remote

import androidx.lifecycle.LiveData
import com.example.themoviedb.model.GenresItem
import com.example.themoviedb.model.MoviesItem
import com.example.themoviedb.model.ReviewItem

interface MoviesDataSource {
    fun getGenres(): LiveData<List<GenresItem>>
    fun getMovies(genreId: Int): LiveData<List<MoviesItem>>
    fun getDetailMovie(movieId: Int): LiveData<MoviesItem>
    fun getReview(reviewId: Int): LiveData<List<ReviewItem>>
}