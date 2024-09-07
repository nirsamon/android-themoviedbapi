package com.nino.codilitytask3tmdb.ui.filter

import com.nino.codilitytask3tmdb.ui.filter.option.SortBy
import com.nino.codilitytask3tmdb.util.CoroutineTestRule
import com.nino.codilitytask3tmdb.util.FakeStringDataStore
import com.nino.codilitytask3tmdb.util.json
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class FilterDataStoreTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val fakeStringDataStore = FakeStringDataStore()

    @Test
    fun `Should set filterState as default when preference is not exists`() = runTest {
        fakeStringDataStore.set("", "")

        val filterDataStore = createFilterDataStore()

        expectThat(filterDataStore.filterState.first()).isEqualTo(FilterState())
    }

    @Test
    fun `Should set filterState when preference is exists`() = runTest {
        val filterState = FilterState(sortBy = SortBy.VOTE_AVERAGE)
        fakeStringDataStore.set(filterState)

        val filterDataStore = createFilterDataStore()

        expectThat(filterDataStore.filterState.first()).isEqualTo(filterState)
    }

    private fun createFilterDataStore() = FilterDataStore(json, fakeStringDataStore)
}
