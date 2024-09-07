package com.nino.codilitytask3tmdb.data.service

import com.nino.codilitytask3tmdb.ui.settings.Language

interface ConfigurationService {
    suspend fun fetchLanguages(): List<Language>
}
