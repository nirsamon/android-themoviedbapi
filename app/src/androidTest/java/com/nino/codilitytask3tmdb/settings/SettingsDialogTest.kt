package com.nino.codilitytask3tmdb.settings

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.nino.codilitytask3tmdb.R
import com.nino.codilitytask3tmdb.ui.settings.Language
import com.nino.codilitytask3tmdb.ui.settings.SettingsDialog
import com.nino.codilitytask3tmdb.ui.settings.SettingsViewModel
import com.nino.codilitytask3tmdb.util.getString
import com.nino.codilitytask3tmdb.util.setTestContent
import org.junit.Rule
import org.junit.Test

class SettingsDialogTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val defaultLanguage = Language("Turkish", "tr", "")

    @Test
    fun should_render_loading_when_uiState_showLoading_is_true(): Unit = with(composeTestRule) {
        val uiState = SettingsViewModel.UiState(showLoading = true)

        showSettingsDialog(uiState, defaultLanguage)

        onNodeWithText(getString(R.string.fetching_languages), useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun should_render_selected_language(): Unit = with(composeTestRule) {
        val uiState = SettingsViewModel.UiState()

        showSettingsDialog(uiState, defaultLanguage)

        onNodeWithText(defaultLanguage.englishName, substring = true, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun should_render_languages_when_selected_language_clicked() = with(composeTestRule) {
        val firstLanguageName = "English"
        val secondLanguageName = "Russian"
        val thirdLanguageName = "Chinese"
        val languages = listOf(
            Language(englishName = firstLanguageName, iso6391 = "en", ""),
            Language(englishName = secondLanguageName, iso6391 = "ru", ""),
            Language(englishName = thirdLanguageName, iso6391 = "ch", ""),
        )
        val uiState = SettingsViewModel.UiState(languages = languages)

        showSettingsDialog(uiState, defaultLanguage)
        onNodeWithText(defaultLanguage.englishName, substring = true, useUnmergedTree = true).performClick()

        languages.forEach {
            onNodeWithText(it.englishName, substring = true, useUnmergedTree = true).assertIsDisplayed()
        }
    }

    private fun ComposeContentTestRule.showSettingsDialog(
        uiState: SettingsViewModel.UiState,
        selectedLanguage: Language,
    ) = setTestContent {
        SettingsDialog(
            uiState = uiState,
            selectedLanguage = selectedLanguage,
            onLanguageSelected = {},
            onDialogDismissed = {},
        )
    }
}
