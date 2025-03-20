package com.example.deepseektraining.data.model

data class Photo(
    val id: String,
    val urls: PhotoUrls
)

data class PhotoUrls(
    val regular: String // URL изображения
)