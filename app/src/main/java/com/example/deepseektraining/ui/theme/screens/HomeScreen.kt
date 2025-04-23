package com.example.deepseektraining.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.deepseektraining.R

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.home_screen),
                    style = MaterialTheme.typography.titleLarge)
                Button(onClick = { navController.navigate("settings") }) {
                    Text(stringResource(R.string.settings_screen),
                        style = MaterialTheme.typography.bodyMedium)
                }
                Button(onClick = { navController.navigate("profile") }) {
                    Text(stringResource(R.string.profile_screen),
                        style = MaterialTheme.typography.bodyMedium)
                }
                Button(onClick = { navController.navigate("about") }) {
                    Text(stringResource(R.string.About_the_app),
                        style = MaterialTheme.typography.bodyMedium)
                }
                Button(onClick = { navController.navigate("photos") }) {
                    Text(stringResource(R.string.View_photos),
                        style = MaterialTheme.typography.bodyMedium)
                }
                Button(onClick = { navController.navigate("movies") }) {
                    Text(stringResource(R.string.View_Movies),
                        style = MaterialTheme.typography.bodyMedium)
                }
                Button(onClick = { navController.navigate("favorites") }) {
                    Text(stringResource(R.string.View_isFavorite_Movies),
                        style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    )
}