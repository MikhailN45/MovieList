package com.application.movielist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.movielist.data.local.MovieDao
import com.application.movielist.repository.Repository

class MovieListViewModelFactory(private val repository: Repository, private val datasource: MovieDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelMovieList(repository) as T
    }
}