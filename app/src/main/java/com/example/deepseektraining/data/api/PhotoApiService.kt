package com.example.deepseektraining.data.api

import com.example.deepseektraining.constants.AppConstants.PHOTO_API_KEY
import com.example.deepseektraining.data.model.Photo
import retrofit2.http.GET
import retrofit2.http.Header

interface PhotoApiService {
    @GET("photos/random?count=10") // Пример для Unsplash API: загружаем 10 случайных фото
    suspend fun getPhotos(
        @Header("Authorization") apiKey: String = PHOTO_API_KEY
    ): List<Photo>
}