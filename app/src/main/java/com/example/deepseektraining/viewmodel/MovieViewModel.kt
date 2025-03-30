package com.example.deepseektraining.viewmodel


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

    init {
        loadCachedMovies()
    }

    fun loadTopPopularMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Загрузка из сети
                val response = kinopoiskApiService.getTopPopularMovies(KINOPOISK_API_KEY)
                val networkMovies = response.items ?: emptyList()

                // Конвертация и сохранение в Room
                val entities = networkMovies.map { it.toEntity() }
                movieDao.insertAll(entities)

                // Обновление UI
                _movies.value = networkMovies
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Неизвестная ошибка"
                loadCachedMovies() // Пытаемся показать кэшированные данные
            } finally {
                _isLoading.value = false
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
    filmLength = this.filmLength
)