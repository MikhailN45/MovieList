package com.application.movielist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.movielist.data.MovieData
import com.application.movielist.data.loadMovies
import kotlinx.coroutines.launch

class ViewModelMovieDetails(private val app: Application) : AndroidViewModel(app) {
    private var _movieLiveData: MutableLiveData<MovieData> = MutableLiveData<MovieData>()
    val movieLiveData: LiveData<MovieData>
        get() = _movieLiveData

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            val movies = loadMovies(app.applicationContext)
            val movie = movies.single { it.id == movieId }
            _movieLiveData.postValue(movie)
        }
    }
}