package com.nino.codilitytask3tmdb.util.client

import com.nino.codilitytask3tmdb.data.service.ConfigurationService
import com.nino.codilitytask3tmdb.ui.settings.Language

class FakeConfigurationClient : ConfigurationService {
    var fetchLanguagesException: Exception? = null
    var languages = listOf<Language>()

    override suspend fun fetchLanguages(): List<Language> {
        return if (fetchLanguagesException == null) {
            languages
        } else {
            throw fetchLanguagesException!!.also { fetchLanguagesException = null }
        }
    }
}
