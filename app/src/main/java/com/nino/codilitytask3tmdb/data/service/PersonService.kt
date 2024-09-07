package com.nino.codilitytask3tmdb.data.service

import com.nino.codilitytask3tmdb.data.remote.ProfileResponse

interface PersonService {
    suspend fun profile(id: Int): ProfileResponse
}
