package com.example.deepseektraining.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deepseektraining.constants.AppConstants.KINOPOISK_API_KEY
import com.example.deepseektraining.data.api.KinopoiskApiService
import com.example.deepseektraining.data.db.MovieDao
import com.example.deepseektraining.data.db.MovieEntity
import com.example.deepseektraining.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val kinopoiskApiService: KinopoiskApiService,
    val movieDao: MovieDao
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> get() = _favoriteMovies

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            // Сначала загружаем кешированные данные
            loadCachedMovies()
            // Затем обновляем из сети
            loadTopPopularMovies()
        }
    }

    fun loadTopPopularMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = kinopoiskApiService.getTopPopularMovies(KINOPOISK_API_KEY)
                val networkMovies = response.items ?: emptyList()

                // Сохраняем текущие избранные фильмы перед обновлением
                val currentFavorites = movieDao.getFavoriteIds()

                // Обновляем фильмы в базе, сохраняя избранные
                movieDao.updateMovies(
                    networkMovies.map { movie ->
                        movie.toEntity().apply {
                            isFavorite = kinopoiskId in currentFavorites
                        }
                    }
                )

            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Неизвестная ошибка"
            } finally {
                _isLoading.value = false
                // Обновляем оба списка после завершения
                loadCachedMovies()
                loadFavoriteMovies()
            }
        }
    }

    private fun loadCachedMovies() {
        viewModelScope.launch {
            movieDao.getAllMovies()
                .collect { entities ->
                    _movies.value = entities.map { it.toDomain() }
                }
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            Log.d("FAVORITE", "Toggling favorite for $movieId")
            movieDao.toggleFavorite(movieId)
            // Обновляем только список избранных, основной список обновится сам через Flow
            loadFavoriteMovies()
        }
    }

    internal fun loadFavoriteMovies() {
        viewModelScope.launch {
            movieDao.getFavoriteMovies()
                .collect { entities ->
                    _favoriteMovies.value = entities.map { it.toDomain() }
                        .filter { it.isFavorite == true }
                    Log.d("FavoriteDebug", "Loaded ${_favoriteMovies.value.size} favorites")
                }
        }
    }
}


// Extension functions для конвертации между Entity и Domain моделями
fun Movie.toEntity(): MovieEntity = MovieEntity(
    kinopoiskId = this.kinopoiskId ?: 0,
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
    nameRu = this.nameRu,
    nameEn = this.nameEn,
    nameOriginal = this.nameOriginal,
    posterUrl = this.posterUrl,
    posterUrlPreview = this.posterUrlPreview,
    year = this.year,
    ratingKinopoisk = this.ratingKinopoisk,
    ratingImdb = this.ratingImdb,
    filmLength = this.filmLength,
    isFavorite = this.isFavorite
)