package com.example.deepseektraining.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.deepseektraining.data.model.Movie
import com.example.deepseektraining.viewmodel.FavoriteViewModel
import com.example.deepseektraining.viewmodel.MovieViewModel


@Composable
fun MovieScreen(viewModel: MovieViewModel = hiltViewModel(),
                favoriteViewModel: FavoriteViewModel = hiltViewModel()) {
    val movies = viewModel.movies.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTopPopularMovies()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (errorMessage.value != null) {
            Text(text = "Ошибка: ${errorMessage.value}", color = Color.Red)
        } else {
            LazyColumn {
                items(movies.value) { movie ->
                    MovieItem(
                        movie = movie,
                        onFavoriteClick = { favoriteViewModel.toggleFavorite(movie.kinopoiskId ?: 0) }
                    )
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie,
              onFavoriteClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Загрузка постера фильма с помощью Coil
        if (movie.posterUrl != null) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(movie.posterUrl)
                        .apply { crossfade(true) }
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        } else {
            // Заглушка, если posterUrl равен null
            Text(text = "Нет постера", modifier = Modifier.size(100.dp))
        }
        Column {
            Text(text = movie.nameRu ?: movie.nameEn ?: movie.nameOriginal ?: "No Title")
            Text(text = "Год: ${movie.year ?: "Нет данных"}")
            Text(text = "Рейтинг Кинопоиска: ${movie.ratingKinopoisk ?: "N/A"}")
        }
        // Кнопка избранного (если передана)
        onFavoriteClick?.let { onClick ->
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (movie.isFavorite) Icons.Filled.Favorite
                    else Icons.Outlined.Favorite,
                    tint = if (movie.isFavorite) Color.Red else Color.Gray,
                    contentDescription = "Избранное"
                )
            }
        }
    }
}