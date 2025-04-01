package com.example.deepseektraining.data.model

data class Movie(
    val kinopoiskId: Int?,
    val isFavorite: Boolean,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val year: Int?,
    val ratingKinopoisk: Double?,
    val ratingImdb: Double?,
    val filmLength: Int?
)