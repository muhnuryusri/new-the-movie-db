package com.example.themoviedb.data.api

import com.example.themoviedb.model.GenresResponse
import com.example.themoviedb.model.MoviesItem
import com.example.themoviedb.model.MoviesResponse
import com.example.themoviedb.model.ReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    fun getAllGenres(
        @Query("api_key") apiKey: String,
    ): Call<GenresResponse>

    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") withGenres: Int
    ): Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<MoviesItem>

    @GET("movie/{id}/reviews")
    fun getReviews(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Call<ReviewResponse>
}