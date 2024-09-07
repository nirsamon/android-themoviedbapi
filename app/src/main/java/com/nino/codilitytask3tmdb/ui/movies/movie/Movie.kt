package com.nino.codilitytask3tmdb.ui.movies.movie

data class Movie(
    val id: Int,
    val name: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int,
)
