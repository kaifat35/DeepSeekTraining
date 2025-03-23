package com.example.deepseektraining.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KinopoiskRetrofitInstance {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"

    val api: KinopoiskApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KinopoiskApiService::class.java)
    }
}