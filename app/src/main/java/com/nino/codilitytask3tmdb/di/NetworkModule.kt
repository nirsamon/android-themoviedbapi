package com.nino.codilitytask3tmdb.di

import android.content.Context
import com.nino.codilitytask3tmdb.R
import com.nino.codilitytask3tmdb.data.client.ConfigurationClient
import com.nino.codilitytask3tmdb.data.client.MovieClient
import com.nino.codilitytask3tmdb.data.client.PersonClient
import com.nino.codilitytask3tmdb.data.service.ConfigurationService
import com.nino.codilitytask3tmdb.data.service.MovieService
import com.nino.codilitytask3tmdb.data.service.PersonService
import com.nino.codilitytask3tmdb.ui.settings.LanguageDataStore
import com.nino.codilitytask3tmdb.util.interceptor.ApiKeyInterceptor
import com.nino.codilitytask3tmdb.util.interceptor.LanguageInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import javax.inject.Singleton

private const val BASE_URL = "https://api.themoviedb.org/3/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @IntoSet
    fun provideApiKeyInterceptor(@ApplicationContext context: Context): Interceptor {
        return ApiKeyInterceptor(context.getString(R.string.tmdb_api_key))
    }

    @Provides
    @Singleton
    @IntoSet
    fun provideLanguageInterceptor(languageDataStore: LanguageDataStore): Interceptor {
        return LanguageInterceptor(languageDataStore)
    }

    @Provides
    @Singleton
    fun provideOkHttpEngine(interceptors: Set<@JvmSuppressWildcards Interceptor>): HttpClientEngine {
        return OkHttp.create {
            config {
                interceptors().addAll(interceptors)
            }
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(json: Json, okHttpEngine: HttpClientEngine) = HttpClient(okHttpEngine) {
        install(ContentNegotiation) {
            json(json)
        }
        defaultRequest {
            url(BASE_URL)
        }
    }

    @Provides
    @Singleton
    fun provideMovieService(httpClient: HttpClient): MovieService = MovieClient(httpClient)

    @Provides
    @Singleton
    fun provideConfigurationService(httpClient: HttpClient): ConfigurationService = ConfigurationClient(httpClient)

    @Provides
    @Singleton
    fun providePersonService(httpClient: HttpClient): PersonService = PersonClient(httpClient)
}
