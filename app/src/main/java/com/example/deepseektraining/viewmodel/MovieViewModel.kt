package com.example.deepseektraining.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deepseektraining.constants.AppConstants.KINOPOISK_API_KEY
import com.example.deepseektraining.data.api.KinopoiskApiService
import com.example.deepseektraining.data.db.MovieDao
import com.example.deepseektraining.data.db.MovieEntity
import com.example.deepseektraining.data.model.Movie
//import com.example.deepseektraining.data.di.WorkManagerHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val kinopoiskApiService: KinopoiskApiService,
    private val movieDao: MovieDao,
    //workManagerHelper: WorkManagerHelper
) : ViewModel() {

    /*init {
        loadInitialData()
        workManagerHelper.scheduleMovieUpdate() // Запускаем периодическое обновление
    }*/

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            launch { subscribeToMovies() }
            launch { loadTopPopularMovies() }
        }
    }

    private fun subscribeToMovies() = viewModelScope.launch {
        movieDao.getAllMovies()
            .collect { entities ->
                _movies.value = entities.map { it.toDomain() }
                debugLog("Основной список обновлен (${_movies.value.size} фильмов)")
            }
    }

    fun loadTopPopularMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = kinopoiskApiService.getTopPopularMovies(KINOPOISK_API_KEY)
                val networkMovies = response.items ?: emptyList()

                val currentFavorites = movieDao.getFavoriteIds()

                movieDao.updateMovies(
                    networkMovies.map { movie ->
                        movie.toEntity().apply {
                            isFavorite = kinopoiskId in currentFavorites
                        }
                    }
                )
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка загрузки: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun debugLog(message: String) {
        println("[MovieViewModel] $message")
    }
}

// Конвертеры моделей
fun Movie.toEntity(): MovieEntity = MovieEntity(
    kinopoiskId = this.kinopoiskId ?: 0,
    isFavorite = this.isFavorite,
    nameRu = this.nameRu,
    nameEn = this.nameEn,
    nameOriginal = this.nameOriginal,
    posterUrl = this.posterUrl,
    posterUrlPreview = this.posterUrlPreview,
    year = this.year,
    ratingKinopoisk = this.ratingKinopoisk,
    ratingImdb = this.ratingImdb,
    filmLength = this.filmLength
)

fun MovieEntity.toDomain(): Movie = Movie(
    kinopoiskId = this.kinopoiskId,
    isFavorite = this.isFavorite,
    nameRu = this.nameRu,
    nameEn = this.nameEn,
    nameOriginal = this.nameOriginal,
    posterUrl = this.posterUrl,
    posterUrlPreview = this.posterUrlPreview,
    year = this.year,
    ratingKinopoisk = this.ratingKinopoisk,
    ratingImdb = this.ratingImdb,
    filmLength = this.filmLength
)