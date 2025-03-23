package com.example.deepseektraining.data.di

import com.example.deepseektraining.data.api.KinopoiskApiService
import com.example.deepseektraining.data.api.KinopoiskRetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKinopoiskApiService(): KinopoiskApiService {
        return KinopoiskRetrofitInstance.api
    }
}