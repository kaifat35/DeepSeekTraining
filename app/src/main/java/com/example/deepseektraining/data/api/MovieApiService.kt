package com.example.deepseektraining.data.api

import com.example.deepseektraining.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KinopoiskApiService {
    @GET("v2.2/films/collections")
    suspend fun getTopPopularMovies(
        @Header("X-API-KEY") apiKey: String,
        @Query("type") type: String = "TOP_POPULAR_ALL",
        @Query("page") page: Int = 1
    ): MovieResponse
}