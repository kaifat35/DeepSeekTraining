package com.example.deepseektraining.api


import com.example.deepseektraining.data.Photo
import retrofit2.http.GET

interface PhotoApiService {
    @GET("photos") // Замени на реальный эндпоинт API
    suspend fun getPhotos(): List<Photo>
}