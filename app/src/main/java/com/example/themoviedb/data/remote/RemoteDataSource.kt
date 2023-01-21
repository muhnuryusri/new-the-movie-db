package com.example.themoviedb.data.remote

import android.util.Log
import com.example.themoviedb.BuildConfig.API_KEY
import com.example.themoviedb.BuildConfig.BASE_URL
import com.example.themoviedb.data.api.ApiConfig
import com.example.themoviedb.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun getGenres(callback: LoadGenresCallback) {
        val client = ApiConfig.getApiService().getAllGenres(API_KEY)
        client.enqueue(object : Callback<GenresResponse> {
            override fun onResponse(call: Call<GenresResponse>, response: Response<GenresResponse>) {
                callback.onGenresLoaded(response.body()?.genres as List<GenresItem>?)
            }

            override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getGenres onFailure : ${t.message}")
            }
        })
    }

    fun getMovies(callback: LoadMoviesCallback, genreId: Int) {
        val client = ApiConfig.getApiService().getMoviesByGenre(API_KEY, genreId)
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                callback.onMoviesLoaded(response.body()?.results as List<MoviesItem>?)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
            }
        })
    }

    fun getDetailMovie(callback: LoadDetailCallback, movieId: Int) {
        val client = ApiConfig.getApiService().getMovieDetail(movieId, API_KEY)
        client.enqueue(object : Callback<MoviesItem> {
            override fun onResponse(call: Call<MoviesItem>, response: Response<MoviesItem>) {
                callback.onDetailLoaded(response.body())
            }

            override fun onFailure(call: Call<MoviesItem>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
            }
        })
    }

    fun getTrailer(callback: LoadTrailerCallback, movieId: Int) {
        val client = ApiConfig.getApiService().getTrailer(movieId, API_KEY)
        client.enqueue(object : Callback<TrailerResponse> {
            override fun onResponse(call: Call<TrailerResponse>, response: Response<TrailerResponse>) {
                val filteredList = ArrayList<TrailerResponse>()
                callback.onTrailerLoaded(response.body()?.results?.last())
            }

            override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTrailer onFailure : ${t.message}")
            }
        })
    }

    fun getReviews(callback: LoadReviewsCallback, reviewId: Int) {
        val client = ApiConfig.getApiService().getReviews(reviewId, API_KEY)
        client.enqueue(object : Callback<ReviewResponse> {
            override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                callback.onReviewsLoaded(response.body()?.results as List<ReviewItem>?)
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getReview onFailure : ${t.message}")
            }
        })
    }

    interface LoadGenresCallback {
        fun onGenresLoaded(genres : List<GenresItem>?)
    }

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies : List<MoviesItem>?)
    }

    interface LoadDetailCallback {
        fun onDetailLoaded(movieDetail : MoviesItem?)
    }

    interface LoadTrailerCallback {
        fun onTrailerLoaded(movieTrailer : VideosItem?)
    }

    interface LoadReviewsCallback {
        fun onReviewsLoaded(reviews : List<ReviewItem>?)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}