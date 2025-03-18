package com.example.deepseektraining.repository

import com.example.deepseektraining.api.RetrofitInstance
import com.example.deepseektraining.data.Photo

class PhotoRepository {
    suspend fun getPhotos(): List<Photo> {
        return RetrofitInstance.api.getPhotos()
    }
}