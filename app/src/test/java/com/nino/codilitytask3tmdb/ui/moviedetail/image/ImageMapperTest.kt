package com.nino.codilitytask3tmdb.ui.moviedetail.image

import com.nino.codilitytask3tmdb.data.remote.ImagesResponse
import com.nino.codilitytask3tmdb.util.parseJson
import com.nino.codilitytask3tmdb.util.toOriginalUrl
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.contains

class ImageMapperTest {
    private val mapper = ImageMapper()

    private val imagesResponse: ImagesResponse = parseJson("images.json")

    @Test
    fun `Map backdrops and posters with original url`() {
        val images = mapper.map(imagesResponse)

        val expectedBackdrops = imagesResponse.backdrops.map { Image(it.filePath.toOriginalUrl(), it.voteCount) }
        val expectedPosters = imagesResponse.posters.map { Image(it.filePath.toOriginalUrl(), it.voteCount) }
        expectThat(images).contains(expectedBackdrops)
        expectThat(images).contains(expectedPosters)
    }
}
