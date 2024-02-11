package com.example.fintech_2024_dombrovskaya.models

import kotlinx.serialization.Serializable

@Serializable
data class  ResponseFilms<T>(
    val pagesCount: Int,
    val films: List<T>,
)