package com.nino.codilitytask3tmdb.ui.movies

import com.nino.codilitytask3tmdb.ui.filter.FilterDataStore
import com.nino.codilitytask3tmdb.ui.filter.MovieRequestOptionsMapper
import com.nino.codilitytask3tmdb.ui.movies.movie.MovieMapper
import com.nino.codilitytask3tmdb.util.CoroutineTestRule
import com.nino.codilitytask3tmdb.util.FakeStringDataStore
import com.nino.codilitytask3tmdb.util.client.FakeMovieClient
import com.nino.codilitytask3tmdb.util.json
import com.nino.codilitytask3tmdb.util.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val movieService = FakeMovieClient()
    private val filterDataStore = FilterDataStore(json, FakeStringDataStore())
    private val movieMapper = MovieMapper()
    private val movieRequestOptionsMapper = MovieRequestOptionsMapper()

    @Test
    fun `Should not set search query when query is empty`() = runTest {
        val moviesViewModel = createViewModel()
        val queryChanges = moviesViewModel.searchQuery.test()

        moviesViewModel.onSearch("")

        expectThat(queryChanges.last()).isEmpty()
    }

    @Test
    fun `Should not set search query when query is less than threshold`() = runTest {
        val moviesViewModel = createViewModel()
        val queryChanges = moviesViewModel.searchQuery.test()

        moviesViewModel.onSearch("qu")

        expectThat(queryChanges.last()).isEmpty()
    }

    @Test
    fun `Should set search query when query is valid`() = runTest {
        val moviesViewModel = createViewModel()
        val queryChanges = moviesViewModel.searchQuery.test()

        val query = "query"
        moviesViewModel.onSearch(query)

        expectThat(queryChanges.last()).isEqualTo(query)
    }

    private fun createViewModel() =
        MoviesViewModel(movieService, movieMapper, movieRequestOptionsMapper, filterDataStore)
}
