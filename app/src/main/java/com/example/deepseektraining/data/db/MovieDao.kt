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
        val currentFavorites = getFavoriteIds().toSet()
        clearAll()
        movies.forEach { movie ->
            movie.isFavorite = movie.kinopoiskId in currentFavorites
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

    @Query("SELECT kinopoiskId, isFavorite FROM movies")
    suspend fun debugFavorites(): List<FavoriteDebugInfo>

    data class FavoriteDebugInfo(
        val kinopoiskId: Int,
        val isFavorite: Boolean
    )
}