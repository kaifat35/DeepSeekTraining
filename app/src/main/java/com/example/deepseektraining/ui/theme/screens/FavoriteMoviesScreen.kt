package com.example.deepseektraining.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deepseektraining.viewmodel.FavoriteViewModel


@Composable
fun FavoriteMoviesScreen(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val favoriteMovies by favoriteViewModel.favoriteMovies.collectAsState()

    // Логирование для отладки
    LaunchedEffect(favoriteMovies) {
        println("[FAV UI] Текущие избранные: ${favoriteMovies.map { it.kinopoiskId }}")
    }

    if (favoriteMovies.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Нет избранных фильмов")
        }
    } else {
        LazyColumn {
            items(favoriteMovies, key = { it.kinopoiskId ?: 0 }) { movie ->
                MovieItem(
                    movie = movie,
                    onFavoriteClick = {
                        movie.kinopoiskId?.let { id ->
                            favoriteViewModel.toggleFavorite(id)
                        }
                    }
                )
            }
        }
    }
}