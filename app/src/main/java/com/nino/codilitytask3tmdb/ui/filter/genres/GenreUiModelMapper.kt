package com.nino.codilitytask3tmdb.ui.filter.genres

import com.nino.codilitytask3tmdb.data.remote.Genre
import com.nino.codilitytask3tmdb.util.Mapper
import javax.inject.Inject

class GenreUiModelMapper @Inject constructor() : Mapper<Genre, GenreUiModel> {
    override fun map(input: Genre) = GenreUiModel(input)
}
