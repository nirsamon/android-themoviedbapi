package com.nino.codilitytask3tmdb.util.client

import com.nino.codilitytask3tmdb.data.remote.ProfileResponse
import com.nino.codilitytask3tmdb.data.service.PersonService
import com.nino.codilitytask3tmdb.util.parseJson

class FakePersonClient : PersonService {
    var fetchProfileException: Exception? = null
    val profileResponse = parseJson<ProfileResponse>("person.json")

    override suspend fun profile(id: Int): ProfileResponse {
        return if (fetchProfileException == null) {
            profileResponse
        } else {
            throw fetchProfileException!!.also { fetchProfileException = null }
        }
    }
}
