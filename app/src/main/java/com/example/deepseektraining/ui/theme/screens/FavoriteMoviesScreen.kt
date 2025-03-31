package com.example.deepseektraining.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.deepseektraining.viewmodel.MovieViewModel

@Composable
fun FavoriteMoviesScreen(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val favoriteMovies by viewModel.favoriteMovies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            favoriteMovies.isEmpty() -> Text(
                "Нет избранных фильмов",
                Modifier.align(Alignment.Center)
            )

            else -> LazyColumn {
                items(favoriteMovies, key = { it.kinopoiskId ?: 0 }) { movie ->
                    MovieItem(
                        movie = movie.copy(isFavorite = true),
                        onFavoriteClick = {
                            movie.kinopoiskId?.let(viewModel::toggleFavorite)
                        }
                    )
                }
            }
        }
    }
}