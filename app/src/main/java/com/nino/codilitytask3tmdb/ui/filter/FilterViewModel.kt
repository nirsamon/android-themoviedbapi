package com.nino.codilitytask3tmdb.ui.filter

import androidx.lifecycle.ViewModel
import com.nino.codilitytask3tmdb.data.service.MovieService
import com.nino.codilitytask3tmdb.ui.filter.genres.GenreUiModelMapper
import com.nino.codilitytask3tmdb.util.Dispatchers
import com.nino.codilitytask3tmdb.util.onIO
import com.nino.codilitytask3tmdb.util.onMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val filterDataStore: FilterDataStore,
    private val movieService: MovieService,
    private val genreUiModelMapper: GenreUiModelMapper,
    private val dispatchers: Dispatchers,
) : ViewModel() {

    private val _filterState: MutableStateFlow<FilterState?> = MutableStateFlow(null)
    val filterState: StateFlow<FilterState?> = _filterState.asStateFlow().also {
        listenFilterStateChanges()
    }

    private fun listenFilterStateChanges() = dispatchers.onMain {
        val genres = try {
            movieService.fetchGenres().genres.map(genreUiModelMapper::map)
        } catch (exception: Exception) {
            emptyList()
        }

        filterDataStore.filterState
            .map { filterState -> filterState.copy(genres = genres) }
            .collect(_filterState::value::set)
    }

    fun onResetClicked() {
        dispatchers.onIO {
            filterDataStore.resetFilterState()
        }
    }

    fun onFilterStateChanged(filterState: FilterState) {
        dispatchers.onIO {
            filterDataStore.onFilterStateChanged(filterState)
        }
    }
}
