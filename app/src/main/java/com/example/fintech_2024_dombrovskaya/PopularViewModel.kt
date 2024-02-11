package com.example.fintech_2024_dombrovskaya

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fintech_2024_dombrovskaya.database.FilmEntity
import com.example.fintech_2024_dombrovskaya.models.Film
import kotlinx.coroutines.launch

class PopularViewModel(application: Application,
                       private val repository: PopularRepository):
    AndroidViewModel(application) {

    private val mutableFilmsList = MutableLiveData<MutableList<FilmEntity>>()

    fun getListOfPopularFilms() = viewModelScope.launch {
        val filmItems = repository.getListOfPopularFilms()

        updateNewDataList(filmItems.toMutableList())
    }

    fun updateNewDataList(newDataList: MutableList<FilmEntity>) {
        mutableFilmsList.value = newDataList
    }

    fun getNewDataList(): LiveData<MutableList<FilmEntity>> {
        return mutableFilmsList
    }

    class PopularViewModelFactory(
        private val application: Application,
        private val repository: PopularRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PopularViewModel::class.java)) {
                return PopularViewModel(application, repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}