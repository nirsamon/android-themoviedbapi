package com.nino.codilitytask3tmdb.util.client

import com.nino.codilitytask3tmdb.data.remote.CreditsResponse
import com.nino.codilitytask3tmdb.data.remote.Genre
import com.nino.codilitytask3tmdb.data.remote.GenresResponse
import com.nino.codilitytask3tmdb.data.remote.ImagesResponse
import com.nino.codilitytask3tmdb.data.remote.MovieDetailResponse
import com.nino.codilitytask3tmdb.data.remote.MovieResponse
import com.nino.codilitytask3tmdb.data.remote.MoviesResponse
import com.nino.codilitytask3tmdb.data.service.MovieService
import com.nino.codilitytask3tmdb.util.parseJson

class FakeMovieClient : MovieService {
    val genre = Genre(1, "Name")
    var fetchGenresException: Exception? = null
    var movieDetailException: Exception? = null
    val movieDetailResponse: MovieDetailResponse = parseJson("movie_detail.json")
    val creditsResponse: CreditsResponse = parseJson("credits.json")
    val imagesResponse: ImagesResponse = parseJson("images.json")
    val moviesResponse = MoviesResponse(1, listOf(MovieResponse(1, "movie", "", "", "", "", "", 1.1, 1)), 1, 1)
    val searchResponse = MoviesResponse(1, listOf(MovieResponse(1, "search", "", "", "", "", "", 1.1, 1)), 1, 1)

    override suspend fun fetchMovies(pageNumber: Int, options: Map<String, String>): MoviesResponse {
        return moviesResponse
    }

    override suspend fun search(pageNumber: Int, searchQuery: String, includeAdult: Boolean): MoviesResponse {
        return searchResponse
    }

    override suspend fun fetchGenres(): GenresResponse {
        return if (fetchGenresException == null) {
            GenresResponse(listOf(genre))
        } else {
            throw fetchGenresException!!.also { fetchGenresException = null }
        }
    }

    override suspend fun fetchMovieDetail(movieId: Int): MovieDetailResponse {
        return if (movieDetailException == null) {
            movieDetailResponse
        } else {
            throw movieDetailException!!.also { movieDetailException = null }
        }
    }

    override suspend fun fetchMovieCredits(movieId: Int): CreditsResponse {
        return creditsResponse
    }

    override suspend fun fetchMovieImages(movieId: Int): ImagesResponse {
        return imagesResponse
    }
}
