package com.example.fintech_2024_dombrovskaya.models

import kotlinx.serialization.Serializable

@Serializable
data class Film (
    val filmId: Int? = 0,
    val nameRu: String? = null,
    val nameEn: String? = null,
    val year: String? = null,
    val filmLength: String? = null,
    val countries: List<Country>? = null,
    val genres: List<Genre>? = null,
    val rating: String? = null,
    val ratingVoteCount: Int? = 0,
    val posterUrl: String? = null,
    val posterUrlPreview: String? = null,
    val ratingChange: String? = null,
    val isRatingUp: String? = null,
    val isAfisha: Int? = 0,
)