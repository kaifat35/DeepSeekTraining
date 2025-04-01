package com.example.deepseektraining.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deepseektraining.data.db.MovieDao
import com.example.deepseektraining.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieDao: MovieDao
) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies

    init {
        viewModelScope.launch {
            movieDao.getFavoriteMovies()
                .distinctUntilChanged()
                .collect { entities ->
                    _favoriteMovies.value = entities.map { it.toDomain() }
                    println("[FAV VM] Обновлено избранных: ${_favoriteMovies.value.size}")
                }
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieDao.toggleFavorite(movieId)
            println("[FAV VM] Переключен избранный статус для: $movieId")
        }
    }
}