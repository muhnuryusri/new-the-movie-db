package com.example.themoviedb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themoviedb.data.remote.MoviesDataSource
import com.example.themoviedb.data.remote.RemoteDataSource
import com.example.themoviedb.model.GenresItem
import com.example.themoviedb.model.MoviesItem
import com.example.themoviedb.model.ReviewItem
import com.example.themoviedb.model.VideosItem

class MoviesRepository private constructor(private val remoteDataSource: RemoteDataSource) : MoviesDataSource{
    companion object {
        @Volatile
        private var instance: MoviesRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MoviesRepository =
            instance ?: synchronized(this) {
                instance ?: MoviesRepository(remoteData)
            }
    }

    override fun getGenres(): LiveData<List<GenresItem>> {
        val genreItems = MutableLiveData<List<GenresItem>>()
        remoteDataSource.getGenres(object : RemoteDataSource.LoadGenresCallback {
            override fun onGenresLoaded(genres: List<GenresItem>?) {
                val genreList = ArrayList<GenresItem>()
                if (genres != null) {
                    for (response in genres) {
                        with(response) {
                            val genre = GenresItem(id = id, name = name)
                            genreList.add(genre)
                        }
                    }
                    genreItems.postValue(genreList)
                }
            }
        })
        return genreItems
    }

    override fun getMovies(genreId: Int): LiveData<List<MoviesItem>> {
        val movieItems = MutableLiveData<List<MoviesItem>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<MoviesItem>?) {
                val movieList = ArrayList<MoviesItem>()
                if (movies != null) {
                    for (response in movies) {
                        with(response) {
                            val movie = MoviesItem(id = id, posterPath = posterPath)
                            movieList.add(movie)
                        }
                    }
                    movieItems.postValue(movieList)
                }
            }
        }, genreId)
        return movieItems
    }

    override fun getDetailMovie(movieId: Int): LiveData<MoviesItem> {
        val movieDetailItems = MutableLiveData<MoviesItem>()
        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailCallback {
            override fun onDetailLoaded(movieDetail: MoviesItem?) {
                if (movieDetail != null) {
                    with(movieDetail) {
                        val detailMovie = MoviesItem(
                            id = id,
                            backdropPath = backdropPath,
                            title = title,
                            runtime = runtime,
                            genres = genres,
                            overview = overview
                        )
                        movieDetailItems.postValue(detailMovie)
                    }
                }
            }
        }, movieId)
        return movieDetailItems
    }

    override fun getTrailer(movieId: Int): LiveData<VideosItem> {
        val trailerItem = MutableLiveData<VideosItem>()
        remoteDataSource.getTrailer(object : RemoteDataSource.LoadTrailerCallback {
            override fun onTrailerLoaded(movieTrailer: VideosItem?) {
                if (movieTrailer != null) {
                    with(movieTrailer) {
                        val detailTrailer = VideosItem(
                            id = id,
                            key = key
                        )
                        trailerItem.postValue(detailTrailer)
                    }
                }
            }
        }, movieId)
        return trailerItem
    }

    override fun getReview(reviewId: Int): LiveData<List<ReviewItem>> {
        val reviewItems = MutableLiveData<List<ReviewItem>>()
        remoteDataSource.getReviews(object : RemoteDataSource.LoadReviewsCallback {
            override fun onReviewsLoaded(reviews: List<ReviewItem>?) {
                val movieList = ArrayList<ReviewItem>()
                if (reviews != null) {
                    for (response in reviews) {
                        with(response) {
                            val movie = ReviewItem(id = id, author = author, content = content)
                            movieList.add(movie)
                        }
                    }
                    reviewItems.postValue(movieList)
                }
            }
        }, reviewId)
        return reviewItems
    }
}