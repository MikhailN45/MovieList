package com.application.movielist.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.movielist.data.MovieData
import com.application.movielist.repository.Repository
import kotlinx.coroutines.launch

class ViewModelMovieList(private val repository: Repository) : ViewModel() {
    private var _movieListLiveData: MutableLiveData<List<MovieData>> = MutableLiveData()
    val movieListLiveData: LiveData<List<MovieData>>
        get() = _movieListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    fun getMovies() {
        viewModelScope.launch {
            _loadingLiveData.postValue(true)
            try {
                _movieListLiveData.postValue(repository.getActualMovies().result)
            }
            catch (exception: Exception) {
                Log.d("TAG", exception.toString())
            }
            _loadingLiveData.postValue(false)
        }
    }
}