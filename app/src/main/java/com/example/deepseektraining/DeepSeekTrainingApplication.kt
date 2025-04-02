package com.example.deepseektraining

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeepSeekTrainingApplication : Application()

/*import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.deepseektraining.data.di.AppWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DeepSeekTrainingApplication : Application() {

    @Inject
    lateinit var workerFactory: AppWorkerFactory

    override fun onCreate() {
        super.onCreate()
        // Инициализация WorkManager с нашей конфигурацией
        val configuration = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

        WorkManager.initialize(this, configuration)
    }
}*/