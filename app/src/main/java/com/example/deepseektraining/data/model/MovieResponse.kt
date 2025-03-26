package com.example.deepseektraining.data.model

data class MovieResponse(
    val total: Int?,
    val totalPages: Int?,
    val items: List<Movie>?
)