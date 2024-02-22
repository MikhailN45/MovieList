package com.application.movielist.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.movielist.data.FootageList
import com.application.movielist.data.MovieInfo
import com.application.movielist.repository.Repository
import kotlinx.coroutines.launch

class ViewModelMovieDetails(private val repository: Repository) : ViewModel() {
    private var _movieLiveData: MutableLiveData<MovieInfo> = MutableLiveData<MovieInfo>()
    val movieLiveData: LiveData<MovieInfo>
        get() = _movieLiveData

    private var _footageLiveData: MutableLiveData<FootageList> = MutableLiveData<FootageList>()
    val footageLiveData: LiveData<FootageList>
        get() = _footageLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _loadingLiveData.value = true
            try {
                _movieLiveData.value = repository.getMovieDetails(movieId)
                _footageLiveData.value = repository.getMovieFootage(movieId)
            } catch (exception: Exception) {
                Log.d("TAG", exception.toString())
            }

        }
        _loadingLiveData.value = false
    }
}
