package com.example.deepseektraining.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.deepseektraining.viewmodel.PhotoViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import com.example.deepseektraining.data.model.Photo

@Composable
fun PhotoScreen() {
    val viewModel: PhotoViewModel = viewModel()
    val photos: List<Photo> by viewModel.photos.collectAsState()
    val isLoading: Boolean by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPhotos()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    LazyColumn {
                        items(photos) { photo ->
                            AsyncImage(
                                model = photo.urls.regular,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}