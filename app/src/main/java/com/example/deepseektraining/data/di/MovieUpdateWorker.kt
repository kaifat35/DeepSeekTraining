package com.example.deepseektraining.data.di

/*import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.example.deepseektraining.constants.AppConstants
import com.example.deepseektraining.data.api.KinopoiskApiService
import com.example.deepseektraining.data.db.MovieDao
import com.example.deepseektraining.viewmodel.toEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieUpdateWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val kinopoiskApiService: KinopoiskApiService,
    private val movieDao: MovieDao
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val response = kinopoiskApiService.getTopPopularMovies(AppConstants.KINOPOISK_API_KEY)
            val networkMovies = response.items ?: emptyList()
            val currentFavorites = movieDao.getFavoriteIds()

            movieDao.updateMovies(
                networkMovies.map { movie ->
                    movie.toEntity().apply {
                        isFavorite = kinopoiskId in currentFavorites
                    }
                }
            )
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
    companion object {
        const val WORK_NAME = "movieUpdateWork"
    }

    class Factory @Inject constructor(
        private val kinopoiskApiService: KinopoiskApiService,
        private val movieDao: MovieDao
    ) : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return MovieUpdateWorker(appContext, params, kinopoiskApiService, movieDao)
        }
    }
}*/