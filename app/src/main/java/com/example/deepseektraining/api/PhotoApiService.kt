package com.example.deepseektraining.api


import com.example.deepseektraining.data.Photo
import retrofit2.http.GET
import retrofit2.http.Header

interface PhotoApiService {
    @GET("photos/random?count=10") // Пример для Unsplash API: загружаем 10 случайных фото
    suspend fun getPhotos(
        @Header("Authorization") apiKey: String = "Client-ID Z4j3tOUJe9GQgfRsXlcEwr5UCrOfFABFpPkYOE47P4M"
    ): List<Photo>
}
