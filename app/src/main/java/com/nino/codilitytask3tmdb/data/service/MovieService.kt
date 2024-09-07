package com.nino.codilitytask3tmdb.data.service

import com.nino.codilitytask3tmdb.data.remote.CreditsResponse
import com.nino.codilitytask3tmdb.data.remote.GenresResponse
import com.nino.codilitytask3tmdb.data.remote.ImagesResponse
import com.nino.codilitytask3tmdb.data.remote.MovieDetailResponse
import com.nino.codilitytask3tmdb.data.remote.MoviesResponse

interface MovieService {
    suspend fun fetchMovies(pageNumber: Int, options: Map<String, String>): MoviesResponse

    suspend fun search(pageNumber: Int, searchQuery: String, includeAdult: Boolean = true): MoviesResponse

    suspend fun fetchGenres(): GenresResponse

    suspend fun fetchMovieDetail(movieId: Int): MovieDetailResponse

    suspend fun fetchMovieCredits(movieId: Int): CreditsResponse

    suspend fun fetchMovieImages(movieId: Int): ImagesResponse
}
