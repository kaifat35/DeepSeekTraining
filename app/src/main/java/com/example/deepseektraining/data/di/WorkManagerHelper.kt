package com.example.deepseektraining.data.di

/*import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkManagerHelper @Inject constructor(private val context: Context) {

    fun scheduleMovieUpdate() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<MovieUpdateWorker>(
            12, // повтор каждые 12 часов
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        WorkManager.Companion.getInstance(context).enqueueUniquePeriodicWork(
            MovieUpdateWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    fun cancelMovieUpdate() {
        WorkManager.Companion.getInstance(context)
            .cancelUniqueWork(MovieUpdateWorker.WORK_NAME)
    }
}*/