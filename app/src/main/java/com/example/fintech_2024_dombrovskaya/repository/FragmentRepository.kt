package com.example.fintech_2024_dombrovskaya.repository

import com.example.fintech_2024_dombrovskaya.API.ApiService
import com.example.fintech_2024_dombrovskaya.Converter
import com.example.fintech_2024_dombrovskaya.database.FilmEntity
import com.example.fintech_2024_dombrovskaya.database.FilmsDao

class FragmentRepository(private val filmsDao: FilmsDao) {

    private val converter: Converter = Converter()

    private val apiService by lazy {
        ApiService.create()
    }

    suspend fun getListOfPopularFilms(): List<FilmEntity> {
        val listOfFilms = apiService.getPopularFilms()
        var description = ""

        for(film in listOfFilms) {
            description = apiService.getDescriptionOfFilm(film.filmId).description.toString()
            val filmEntity = converter.convertToFilmEntity(film, description)
            filmsDao.insertFilm(filmEntity)
        }

        return filmsDao.getAllFilms()
    }

    suspend fun updateFilm(film: FilmEntity) {
        filmsDao.updateFilm(film)
    }

    fun getFavouriteDataList(): MutableList<FilmEntity> {
        val favouriteFilms = mutableListOf<FilmEntity>()
        for(film in filmsDao.getAllFilms()){
            if(film.isFavourite == true){
                favouriteFilms.add(film)
            }
        }
        return favouriteFilms
    }
}