package com.nino.codilitytask3tmdb.ui.settings

import androidx.lifecycle.ViewModel
import com.nino.codilitytask3tmdb.data.service.ConfigurationService
import com.nino.codilitytask3tmdb.util.Dispatchers
import com.nino.codilitytask3tmdb.util.onIO
import com.nino.codilitytask3tmdb.util.onMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val configurationService: ConfigurationService,
    private val languageDataStore: LanguageDataStore,
    private val dispatchers: Dispatchers,
) : ViewModel() {

    val selectedLanguage = languageDataStore.language

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun fetchLanguages() {
        val canFetchLanguages = uiState.value.languages.isEmpty() && uiState.value.showLoading.not()
        if (!canFetchLanguages) return

        _uiState.value = UiState(showLoading = true)
        dispatchers.onMain {
            val languages = try {
                configurationService.fetchLanguages().sortedBy(Language::englishName)
            } catch (exception: Exception) {
                emptyList()
            }
            _uiState.value = UiState(showLoading = false, languages = languages)
        }
    }

    fun onLanguageSelected(language: Language) {
        dispatchers.onIO {
            languageDataStore.onLanguageSelected(language)
        }
    }

    data class UiState(val showLoading: Boolean = false, val languages: List<Language> = emptyList())
}
