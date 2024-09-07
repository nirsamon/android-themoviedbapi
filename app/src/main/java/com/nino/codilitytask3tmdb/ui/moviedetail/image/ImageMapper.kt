package com.nino.codilitytask3tmdb.ui.moviedetail.image

import com.nino.codilitytask3tmdb.data.remote.ImagesResponse
import com.nino.codilitytask3tmdb.util.Mapper
import com.nino.codilitytask3tmdb.util.toOriginalUrl
import javax.inject.Inject

class ImageMapper @Inject constructor() : Mapper<ImagesResponse, List<Image>> {
    override fun map(input: ImagesResponse): List<Image> {
        return buildList {
            addAll(input.backdrops.map { Image(it.filePath.toOriginalUrl(), it.voteCount) })
            addAll(input.posters.map { Image(it.filePath.toOriginalUrl(), it.voteCount) })
            sortByDescending { it.voteCount }
        }
    }
}
