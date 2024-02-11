package com.example.fintech_2024_dombrovskaya.API

object ApiRoutes {
    const val BASE_URL = "https://kinopoiskapiunofficial.tech"
    const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b" // 20 запросов в секунду
    const val POPULAR_FILMS = "/api/v2.2/films/top"
    const val COLLECTION = "TOP_100_POPULAR_FILMS"
    const val DESCRIPTION = "/api/v2.2/films/" // если оставить часть запроса top/, то вылетает ошибка 400
}