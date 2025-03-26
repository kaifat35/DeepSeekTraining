package com.example.deepseektraining.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.home_screen))
                Button(onClick = { navController.navigate("settings") }) {
                    Text(stringResource(R.string.settings_screen))
                }
                Button(onClick = { navController.navigate("profile") }) {
                    Text(stringResource(R.string.profile_screen))
                }
                Button(onClick = { navController.navigate("about") }) {
                    Text(stringResource(R.string.About_the_app))
                }
                Button(onClick = { navController.navigate("photos") }) {
                    Text(stringResource(R.string.View_photos))
                }
                Button(onClick = { navController.navigate("movies") }) {
                    Text(stringResource(R.string.View_Movies))
                }
            }
        }
    )
}