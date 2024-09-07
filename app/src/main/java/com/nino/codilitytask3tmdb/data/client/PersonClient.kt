package com.nino.codilitytask3tmdb.data.client

import com.nino.codilitytask3tmdb.data.remote.ProfileResponse
import com.nino.codilitytask3tmdb.data.service.PersonService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PersonClient(private val httpClient: HttpClient) : PersonService {
    override suspend fun profile(id: Int): ProfileResponse = httpClient.get("person/$id").body()
}
