package com.application.movielist.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.movielist.data.MovieData
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class ViewModelMovieDetails() : ViewModel() {
    private var _movieLiveData: MutableLiveData<MovieData> = MutableLiveData<MovieData>()
    val movieLiveData: LiveData<MovieData>
        get() = _movieLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    @ExperimentalSerializationApi
    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _loadingLiveData.value = true
            val movies = getMovieDetails()
            movies.singleOrNull { it.id = movieId }.let {
                _movieLiveData.value = it
            }
            _loadingLiveData.value = false
        }
    }
}