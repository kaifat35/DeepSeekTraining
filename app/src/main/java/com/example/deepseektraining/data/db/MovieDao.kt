package com.example.deepseektraining.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    suspend fun updateMovies(movies: List<MovieEntity>) {
        // Сохраняем текущие состояния избранного
        val currentFavorites = getFavoriteIds().toSet()
        // Обновляем только новые/измененные фильмы
        movies.forEach { movie ->
            if (!currentFavorites.contains(movie.kinopoiskId)) {
                movie.isFavorite = false
            }
        }
        insertAll(movies)
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies ORDER BY ratingKinopoisk DESC")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT COUNT(*) FROM movies")
    suspend fun getCount(): Int

    @Query("DELETE FROM movies")
    suspend fun clearAll()

    @Query("SELECT * FROM movies WHERE isFavorite = 1 ORDER BY ratingKinopoisk DESC")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("UPDATE movies SET isFavorite = NOT isFavorite WHERE kinopoiskId = :movieId")
    suspend fun toggleFavorite(movieId: Int)

    @Query("SELECT isFavorite FROM movies WHERE kinopoiskId = :movieId")
    suspend fun isFavorite(movieId: Int): Boolean
    @Query("SELECT kinopoiskId FROM movies WHERE isFavorite = 1")
    suspend fun getFavoriteIds(): List<Int>

}