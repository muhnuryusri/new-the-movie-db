package com.example.themoviedb.di

import android.content.Context
import com.example.themoviedb.data.MoviesRepository
import com.example.themoviedb.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MoviesRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MoviesRepository.getInstance(remoteDataSource)
    }
}