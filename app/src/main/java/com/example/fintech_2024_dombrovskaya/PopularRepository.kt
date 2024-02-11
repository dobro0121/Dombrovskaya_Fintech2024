package com.example.fintech_2024_dombrovskaya

import com.example.fintech_2024_dombrovskaya.API.ApiService
import com.example.fintech_2024_dombrovskaya.database.FilmEntity
import com.example.fintech_2024_dombrovskaya.database.FilmsDao

class PopularRepository(private val filmsDao: FilmsDao) {

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
}