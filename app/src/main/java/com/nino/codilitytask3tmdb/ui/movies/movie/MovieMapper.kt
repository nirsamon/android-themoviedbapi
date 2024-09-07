package com.nino.codilitytask3tmdb.ui.movies.movie

import com.nino.codilitytask3tmdb.data.remote.MovieResponse
import com.nino.codilitytask3tmdb.util.Mapper
import com.nino.codilitytask3tmdb.util.toPosterUrl
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieResponse, Movie> {
    override fun map(input: MovieResponse) = Movie(
        id = input.id,
        name = input.name,
        releaseDate = input.firstAirDate.orEmpty(),
        posterPath = input.posterPath.orEmpty().toPosterUrl(),
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
    )
}
