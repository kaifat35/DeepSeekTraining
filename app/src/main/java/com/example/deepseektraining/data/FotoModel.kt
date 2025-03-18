package com.example.deepseektraining.data

data class Photo(
    val id: String,
    val urls: PhotoUrls
)

data class PhotoUrls(
    val regular: String // URL изображения
)