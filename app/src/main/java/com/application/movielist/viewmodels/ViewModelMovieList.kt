package com.application.movielist.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.movielist.data.MovieData
import com.application.movielist.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelMovieList(private val repository: Repository) : ViewModel() {
    private var _movieListLiveData: MutableLiveData<List<MovieData>> = MutableLiveData()
    val movieListLiveData: LiveData<List<MovieData>> = _movieListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun getMovies() {
        viewModelScope.launch {
            _loadingLiveData.value = true
            try {
                withContext(Dispatchers.IO) {
                    _movieListLiveData.postValue(repository.getActualMovies().result)
                }

            } catch (exception: Exception) {
                Log.d("TAG", exception.toString())
            }
            _loadingLiveData.value = false
        }
    }
}