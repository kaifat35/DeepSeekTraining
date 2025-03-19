package com.example.deepseektraining.repository

import com.example.deepseektraining.api.RetrofitInstance
import com.example.deepseektraining.data.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getPhotos(): List<Photo> {
        return withContext(Dispatchers.IO) {
            try {
                apiService.getPhotos() // Загружаем фотографии
            } catch (e: Exception) {
                println("Ошибка при загрузке фотографий: ${e.message}")
                emptyList() // Возвращаем пустой список в случае ошибки
            }
        }
    }
}