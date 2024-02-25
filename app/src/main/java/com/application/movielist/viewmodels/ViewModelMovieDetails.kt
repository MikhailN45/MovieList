package com.application.movielist.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.movielist.data.FootageList
import com.application.movielist.data.MovieInfo
import com.application.movielist.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelMovieDetails(private val repository: Repository) : ViewModel() {
    private var _movieLiveData: MutableLiveData<MovieInfo> = MutableLiveData<MovieInfo>()
    val movieLiveData: LiveData<MovieInfo> = _movieLiveData

    private var _footageLiveData: MutableLiveData<FootageList> = MutableLiveData<FootageList>()
    val footageLiveData: LiveData<FootageList> = _footageLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _loadingLiveData.value = true
            try {
                withContext(Dispatchers.IO) {
                    _movieLiveData.postValue(repository.getMovieDetails(movieId))
                    _footageLiveData.postValue(repository.getMovieFootage(movieId))
                }
            } catch (exception: Exception) {
                Log.d("TAG", exception.toString())
            }
            _loadingLiveData.value = false
        }
    }
}
