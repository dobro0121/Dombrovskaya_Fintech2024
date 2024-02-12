package com.example.fintech_2024_dombrovskaya.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fintech_2024_dombrovskaya.database.FilmEntity
import com.example.fintech_2024_dombrovskaya.repository.FragmentRepository
import kotlinx.coroutines.launch

class FragmentViewModel(application: Application,
                        private val repository: FragmentRepository
):
    AndroidViewModel(application) {

    private val mutableFilmsList = MutableLiveData<MutableList<FilmEntity>>()
    private val favouriteFilmList = MutableLiveData<MutableList<FilmEntity>>()

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

    fun getFavouriteDataList(): LiveData<MutableList<FilmEntity>> {
        val filmItems = repository.getFavouriteDataList()
        updateFavouriteDataList(filmItems)
        return favouriteFilmList
    }
    fun updateFavouriteDataList(filmList: MutableList<FilmEntity>) = viewModelScope.launch {
        favouriteFilmList.value = filmList
    }

    fun updateFilm(film: FilmEntity) = viewModelScope.launch {
        repository.updateFilm(film)
    }

    class ViewModelFactory(
        private val application: Application,
        private val repository: FragmentRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FragmentViewModel::class.java)) {
                return FragmentViewModel(application, repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}