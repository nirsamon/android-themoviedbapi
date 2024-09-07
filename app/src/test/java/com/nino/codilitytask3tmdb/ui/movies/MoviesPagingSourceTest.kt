package com.nino.codilitytask3tmdb.ui.movies

import androidx.paging.PagingSource
import com.nino.codilitytask3tmdb.ui.filter.FilterState
import com.nino.codilitytask3tmdb.ui.filter.MovieRequestOptionsMapper
import com.nino.codilitytask3tmdb.ui.movies.movie.MovieMapper
import com.nino.codilitytask3tmdb.util.CoroutineTestRule
import com.nino.codilitytask3tmdb.util.client.FakeMovieClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class MoviesPagingSourceTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val movieService = FakeMovieClient()

    private val movieMapper = MovieMapper()
    private val movieRequestOptionsMapper = MovieRequestOptionsMapper()
    private val filterState = FilterState()
    private val loadParams = PagingSource.LoadParams.Refresh(1, 1, true)

    lateinit var moviesPagingSource: MoviesPagingSource

    @Test
    fun `should call movies endpoint when query is empty`() = runTest {
        initPagingSource()

        val loadResult = moviesPagingSource.load(loadParams)

        expectThat(loadResult).isEqualTo(
            PagingSource.LoadResult.Page(
                data = movieService.moviesResponse.movies.map(movieMapper::map),
                prevKey = null,
                nextKey = null,
            ),
        )
    }

    @Test
    fun `should call search endpoint when query is not empty`() = runTest {
        val query = "query"
        initPagingSource(query)

        val loadResult = moviesPagingSource.load(loadParams)

        expectThat(loadResult).isEqualTo(
            PagingSource.LoadResult.Page(
                data = movieService.searchResponse.movies.map(movieMapper::map),
                prevKey = null,
                nextKey = null,
            ),
        )
    }

    private fun initPagingSource(query: String = "") {
        moviesPagingSource =
            MoviesPagingSource(movieService, movieMapper, movieRequestOptionsMapper, filterState, query)
    }
}
