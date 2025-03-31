package com.example.deepseektraining.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val kinopoiskId: Int,
    @ColumnInfo(name = "nameRu")val nameRu: String?,
    @ColumnInfo(name = "nameEn")val nameEn: String?,
    @ColumnInfo(name = "nameOriginal")val nameOriginal: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    @ColumnInfo(name = "year")val year: Int?,
    @ColumnInfo(name = "ratingKinopoisk")val ratingKinopoisk: Double?,
    @ColumnInfo(name = "ratingImdb")val ratingImdb: Double?,
    @ColumnInfo(name = "filmLength")val filmLength: Int?,
    val lastUpdated: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "isFavorite", defaultValue = "0")
    var isFavorite: Boolean = false
)

