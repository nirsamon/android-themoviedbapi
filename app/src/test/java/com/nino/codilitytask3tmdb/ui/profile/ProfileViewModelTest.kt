package com.nino.codilitytask3tmdb.ui.profile

import androidx.lifecycle.SavedStateHandle
import com.nino.codilitytask3tmdb.ui.navigation.ARG_PERSON_ID
import com.nino.codilitytask3tmdb.util.CoroutineTestRule
import com.nino.codilitytask3tmdb.util.client.FakePersonClient
import com.nino.codilitytask3tmdb.util.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isNull
import java.io.IOException

@ExperimentalCoroutinesApi
class ProfileViewModelTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val personId = 1337
    private val savedStateHandle = SavedStateHandle(mapOf(ARG_PERSON_ID to personId.toString()))
    private val personService = FakePersonClient()
    private val profileMapper = ProfileMapper()

    private lateinit var profileViewModel: ProfileViewModel

    @Test
    fun `fetch profile success`() = runTest {
        profileViewModel = createViewModel()

        val stateValues = profileViewModel.uiState.test()

        expectThat(stateValues.last()).isEqualTo(
            ProfileViewModel.ProfileUiState(profileMapper.map(personService.profileResponse), loading = false),
        )
    }

    @Test
    fun `fetch profile error`() = runTest {
        personService.fetchProfileException = IOException()

        profileViewModel = createViewModel()
        val stateValues = profileViewModel.uiState.test()

        expectThat(stateValues.last().error).isA<IOException>()
        expectThat(stateValues.last().profile).isNull()
    }

    private fun createViewModel() = ProfileViewModel(savedStateHandle, personService, profileMapper)
}
