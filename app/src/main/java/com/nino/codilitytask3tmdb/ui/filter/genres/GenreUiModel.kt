package com.nino.codilitytask3tmdb.ui.filter.genres

import androidx.compose.ui.graphics.Color
import com.nino.codilitytask3tmdb.data.remote.Genre
import com.nino.codilitytask3tmdb.util.randomColor
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class GenreUiModel(val genre: Genre = Genre(-1, "")) {
    @Transient
    val primaryColor: Color = Color.randomColor()

    @Transient
    val secondaryColor: Color = Color.randomColor()
}
