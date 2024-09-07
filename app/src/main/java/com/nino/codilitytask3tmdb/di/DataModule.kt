package com.nino.codilitytask3tmdb.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.nino.codilitytask3tmdb.data.local.LocalDataStore
import com.nino.codilitytask3tmdb.data.local.PreferencesDataStore
import com.nino.codilitytask3tmdb.ui.filter.FilterDataStore
import com.nino.codilitytask3tmdb.ui.settings.LanguageDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): LocalDataStore {
        return PreferencesDataStore(context.preferencesDataStore)
    }

    @Provides
    @Singleton
    fun provideLanguageDataStore(json: Json, localDataStore: LocalDataStore): LanguageDataStore {
        return LanguageDataStore(json, localDataStore)
    }

    @Provides
    @Singleton
    fun provideFilterDataStore(json: Json, localDataStore: LocalDataStore): FilterDataStore {
        return FilterDataStore(json, localDataStore)
    }
}
