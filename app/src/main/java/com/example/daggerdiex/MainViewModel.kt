package com.example.daggerdiex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerdiex.data.MainRepository
import com.example.daggerdiex.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()
    val progressBarStatus = MutableLiveData<Boolean>()

    fun fetchAllMovies() {
        progressBarStatus.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllMovies()
            if (response.isSuccessful) {
                movieList.postValue(response.body())
            }
        }
        progressBarStatus.value = false
    }


}