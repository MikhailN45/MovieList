package com.application.movielist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.movielist.data.MovieData
import com.application.movielist.data.loadMovies
import kotlinx.coroutines.launch

class ViewModelMovieList(private val app: Application) : AndroidViewModel(app) {
    private var _movieListLideData: MutableLiveData<List<MovieData>> = MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<MovieData>>
        get() = _movieListLideData
    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    fun getMovies() {
        viewModelScope.launch {
            _loadingLiveData.postValue(true)
            _movieListLideData.postValue(loadMovies(app.applicationContext))
            _loadingLiveData.postValue(false)
        }
    }
}