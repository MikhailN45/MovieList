package com.application.movielist.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.movielist.data.MovieDataResponse
import com.application.movielist.domain.interactors.MovieInteractor
import kotlinx.coroutines.launch
import java.lang.Exception

class ViewModelMovieList(private val interactor: MovieInteractor) : ViewModel() {
    private var _movieListLiveData: MutableLiveData<List<MovieDataResponse>> =
        MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<MovieDataResponse>>
        get() = _movieListLiveData
    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        getMovies()
    }

    fun getMovies() {
        try {
            viewModelScope.launch {
                _loadingLiveData.postValue(true)
                val films = interactor.getActualMovies().movies
                _loadingLiveData.postValue(false)
            }
        //TODO(add states)
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
        }


    }
}